package com.bs.spring.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.member.vo.Demo;
import com.bs.spring.model.service.DemoUpdateService;

@Controller
public class DemoUpdateController {
	
	@Autowired
	private DemoUpdateService service;
	
	@Autowired
	public DemoUpdateController(DemoUpdateService service) {
		this.service=service;
	}
	
	
	@RequestMapping("demo/updatedemo.do")
	public String updateDemo(int no,Model m) {
		Demo d=service.selectDemo(no);
		m.addAttribute("demo",d);
		return "demo/demoupdate";
		
	}

	@RequestMapping("/demo/updatedemoend.do")
	public String updateDemo(Demo demo) {
		int result=service.updateDemo(demo);
		return "redirect:/demo/selectDemoList.do";
		
	}
	
}
