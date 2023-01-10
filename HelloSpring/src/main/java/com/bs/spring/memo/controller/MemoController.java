package com.bs.spring.memo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

	private MemoService service;
	
	@Autowired
	public MemoController(MemoService service) {
		this.service=service;
	}
	
	@RequestMapping(value="/memo.do", method= {RequestMethod.GET})
	public ModelAndView memoList(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1") int cPage, 
			@RequestParam(value="numPerpage", defaultValue="5") int numPerpage) {
			//cPage, numPerpage는 무조건 넘어오는 값이 아니니까 안넘어올 때를 생각해서 대체될 값을 넣어줘야함 그럴때 requestParam을 쓰자!
		

		
		List<Memo> list=service.memoList();
		log.debug("메모 하이 : {}",list);		
		
		
//		mv.addObject("memo",list);
		mv.addObject("memo",service.selectMemoListPage(Map.of("cPage",cPage,"numPerpage",numPerpage)));
		
		
		//페이징 처리하기
		int totalData=service.selectMemoListCount();
		mv.addObject("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "memo.do"));
				
		mv.setViewName("memo/memoList");
		
		return mv;
	}
	
	@RequestMapping("/writeMemo.do")
	public String writeMemo() {
		return "memo/writememo";
	}
	
	//@PostMapping("/writeMemoEnd.do")
	@RequestMapping(value="/writeMemoEnd.do", method= {RequestMethod.POST})
	public ModelAndView writeMemoEnd(ModelAndView mv,Memo m) {
		int result=service.writeMemo(m);
		if(result>0) {
			mv.addObject("msg","성공");
			mv.addObject("loc","/memo/memo.do");
		}else {
			mv.addObject("msg","작성 실패");
			mv.addObject("loc","/memo/writeMemo.do");
			
		}
		
		mv.setViewName("common/msg");
		
		return mv;
	}
}
