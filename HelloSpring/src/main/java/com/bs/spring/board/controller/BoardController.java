package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service=service;
	}
	
	@RequestMapping("/board.do")
	public ModelAndView boardList(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1") int cPage,
			@RequestParam(value="numPerpage", defaultValue="5") int numPerpage) {
		List<Board> list=service.boardList();
		
		//log.debug("list : {}",list);
		
		//mv.addObject("board",list);
		mv.addObject("board",service.boardListPage(Map.of("cPage",cPage,"numPerpage",numPerpage)));
		
		int totalData=service.boardListCount();
		mv.addObject("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "board.do"));	//상대경로로 써주기
		mv.addObject("totalContents",totalData);
		
		
		mv.setViewName("board/boardList");
		
		
		return mv;
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard() {
		return "board/insertBoard";
	}
	
	@RequestMapping("/insertBoardEnd.do")
	public ModelAndView insertBoardEnd(ModelAndView mv,MultipartFile[] upFile, //파일 여러개 받아오려고 배열로 선언
				String boardTitle, String boardContent, String boardWriter,
				HttpSession session) {
//		log.debug(upFile[0].getName());
//		log.debug(upFile[0].getOriginalFilename());
//		log.debug(upFile[1].getName());
//		log.debug(upFile[1].getOriginalFilename());
		log.debug(boardTitle+" "+boardContent+" "+boardWriter);
		
		// 파일 업로드 처리하기 
		// 1. 저장위치 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		
		// 2. 폴더있는지 확인하고 폴더를 생성해주기
		File dir=new File(path);
		if(!dir.exists()) dir.mkdirs(); //폴더가 없으면 만드러주기
		
		List<Attachment> files=new ArrayList(); // renamed파일이름을 여기에 저장해야지!
		
		
		for(MultipartFile f : upFile) { //파일 여러개 받아오기
			//3. 리네임드 규칙을 생성하기
			if(!f.isEmpty()) {
				//전송된 파일이 있다면...
				//파일 리네임처리 직접하기
				//String originalFileName=upFile.getOriginalFilename();
				String originalFileName=f.getOriginalFilename();
				String ext=originalFileName.substring(originalFileName.lastIndexOf("."));
				
				//중복되지 않는 이름 설정하는 값 지정하기
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd=(int)(Math.random()*10000)+1;
				String renameFile=sdf.format(System.currentTimeMillis())+"_"+rnd+ext; 
				//currentTimeMillis():현재 서버의 시간을 가져와서 86번에서 설정한 형식으로 나오도록 설정
				
				//파일 업로드 하기
				try {
					
					//MultipartFile 클래스가 제공해주는 메소드 이용해서 저장처리
					//upFile.transferTo(new File(path+renameFile));
					f.transferTo(new File(path+renameFile));
					files.add(new Attachment().builder()
							.originalFilename(f.getOriginalFilename())
							.renamedFilename(renameFile).build()); //바뀐 이름 여기서 위에 만든 list에 넣기!
												
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Board b=Board.builder().boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(Member.builder().userId(boardWriter).build())
				.files(files)
				.build();
		
		int result=service.insertBoard(b);
		
		mv.addObject("msg",result>0?"게시글 등록 성공":"게시글 등록실패");
		mv.addObject("loc","/board/board.do");		
		
//		if(result>0) {
//			mv.addObject("msg","등록성공");
//			mv.addObject("loc","/board/board.do");
//		}else {
//			mv.addObject("msg","등록실패");
//			mv.addObject("loc","/board/insertBoard.do");
//			
//		}
		
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/viewBoard.do")
	public ModelAndView viewBoard(ModelAndView mv,int boardNo) {
		
		Board b=service.viewBoard(boardNo);
		mv.addObject("content",b);
		mv.setViewName("board/boardView");	
		//setViewName을 설정하지 않으면 얘가 메소드에 매핑된 주소로 연결해줌!
		
		return mv;
		
	}
	
	@RequestMapping("/filedown.do")
	public void fileDownload(String ori,String re, 
			HttpServletResponse response,
			HttpSession session,
			@RequestHeader(value="User-agent") String header) {
			//인코딩까지 해주기
		
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+re);
		try(FileInputStream fis=new FileInputStream(downloadFile);
				BufferedInputStream bis=new BufferedInputStream(fis);){
			ServletOutputStream sos=response.getOutputStream();
			
			//파일명 인코딩하기
			boolean isMS=header.contains("Trident")||header.contains("MSIE");
			String encodeFilename="";
			if(isMS) {
				encodeFilename=URLEncoder.encode(ori,"UTF-8");
				encodeFilename=encodeFilename.replaceAll("\\+", "%20");
			}else {
				encodeFilename=new String(ori.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition","attachment;filename=\""+encodeFilename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
