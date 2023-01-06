package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/test/")
	public void test() {
		this.service=service;
	}
	
	@RequestMapping("/loginMember.do")
	//public String loginMember(String userId,String password) {
	public String loginMember(Member m,HttpSession session) {	//m에 전체 데이터가 안들어가 있어도 됨
		//Session에 데이터를 저장하고 관리
		//HttpSession 선언만 하면 알아서 만들어주ㅠㅁ
		
		Member loginMember=service.selectmemberById(m);
		if(loginMember!=null && loginMember.getPassword().equals(m.getPassword())) {
			//로그인 성공
			session.setAttribute("loginMember", loginMember);
			
		}		
		System.out.println(loginMember);
		return "redirect:/"; //메인으로

	}
	
	@RequestMapping("/logoutMember.do")
	public String logoutMember(HttpSession session) {		
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {		
		return "/member/enrollMember";
	}
	
//	@RequestMapping("/enrollMemberend.do")
//	public String enrollMemberend(Member m) {		
//		int result=service.enrollMemberend(m);
//		System.out.println(m);
//		System.out.println(result);
//		return "redirect:/";
//	}

	@RequestMapping("/enrollMemberend.do")
	public ModelAndView enrollMemberend(Member m, ModelAndView mv) {		
		System.out.println(m);
		int result=service.enrollMemberend(m);
		if(result>0) {	
			mv.addObject("msg","회원가입 완료");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg","회원가입 실패");
			mv.addObject("loc","/member/enrollMember.do");			
		}
		
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/mypage.do")
	public String myPage() {
		return "/member/mypage";
	}
	
//	@RequestMapping("/memberview.do")
//	public String memberview(Member m,Model model) {
//		Member viewMember=service.selectmemberById(m);
//		model.addAttribute("member",viewMember);
//		return "member/mypage";
//	}
	
}
