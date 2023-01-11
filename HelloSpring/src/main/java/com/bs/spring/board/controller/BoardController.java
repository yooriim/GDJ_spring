package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;

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
		mv.addObject("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "board.do"));
		mv.addObject("totalContents",totalData);
		
		
		mv.setViewName("board/boardList");
		
		
		return mv;
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard() {
		return "board/insertBoard";
	}
	
	@RequestMapping("/insertBoardEnd.do")
	public ModelAndView insertBoardEnd(ModelAndView mv,Board b) {
		
		int result=service.insertBoard(b);
		if(result>0) {
			mv.addObject("msg","등록성공");
			mv.addObject("loc","/board/board.do");
		}else {
			mv.addObject("msg","등록실패");
			mv.addObject("loc","/board/insertBoard.do");
			
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/viewBoard.do")
	public ModelAndView viewBoard(ModelAndView mv,int boardNo) {
		
		Board b=service.viewBoard(boardNo);
		
		mv.addObject("content",b);
		mv.setViewName("board/boardView");
		
		return mv;
		
	}
	
	
}
