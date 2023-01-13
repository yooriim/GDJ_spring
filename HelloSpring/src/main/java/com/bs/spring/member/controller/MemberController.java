package com.bs.spring.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
@Slf4j
public class MemberController {
	//Slf4j 롬복으로 요걸 추가하면 멤버변수로 log가 생성됨 (outline가서 확인 가넝)
	
	//로거! 
	//private final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	   public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
	      this.service=service;
	      this.passwordEncoder=passwordEncoder;
	   }
	
//	@RequestMapping("/test/")
//	public void test() {
//		this.service=service;
//		this.passwordEncoder=passwordEncoder;
//	}
	
	@RequestMapping("/loginMember.do")
	//public String loginMember(String userId,String password) {
	public String loginMember(Member m,HttpSession session) {	//m에 전체 데이터가 안들어가 있어도 됨
		
//	public String loginMember(Member m,Model model) {	
		//Session에 데이터를 저장하고 관리
		//HttpSession 선언만 하면 알아서 만들어주ㅠㅁ
		
		Member loginMember=service.selectmemberById(m);
		
		//암호화된 패스워드를 원본값이랑 비교하기 위해서는
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동등비교를 해야 한다.
		//matches("원본값",암호화값) 메소드를 이용
		
		if(loginMember!=null && 
			//	loginMember.getPassword().equals(m.getPassword())) { 
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword() )) {
			
			//로그인 성공
			session.setAttribute("loginMember", loginMember);
			//model.addAttribute("loginMember",loginMember);
			
		}		
		System.out.println(loginMember);
		return "redirect:/"; //메인으로

	}
	
	@RequestMapping("/logoutMember.do")
//	public String logoutMember(HttpSession session) {		
	public String logoutMember(SessionStatus session) {		
		//session.invalidate();
		
		if(!session.isComplete()) { //세션확인
			session.setComplete(); //session을 삭제!
		}
		
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
		//System.out.println(m);
		//이젠 이렇게 쓸 수 있다 ↓
		log.debug("파라미터로 전달된 member :  {}",m);
		
		//password암호화 처리하기
		String encodePassword=passwordEncoder.encode(m.getPassword()); //암호화 처리해주는 메소드
		m.setPassword(encodePassword);
		
		
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
	
	
	//response로 응답하는 json 데이터 가져오기
	@RequestMapping("/duplicateId.do")
	public void duplicateId(String userId,HttpServletResponse response)throws IOException {
		Member m=service.selectmemberById(Member.builder().userId(userId).build());
		
		response.setContentType("application/json;charset=utf-8");		
		Gson g=new Gson();
		g.toJson(m,response.getWriter());
		
//		response.setContentType("text/csv;charset=utf-8");
//		response.getWriter().print((m==null?false:true));
		
	}
	
	//jackson 라이브러리 이용하기
	// 메소드에 @ResponseBody 어노테이션 적용
	@RequestMapping("/duplicateConverter.do")
	@ResponseBody
	public Member duplicateUserId(Member m) {
		Member result=service.selectmemberById(m);
		return result;
	}
	
	
	@RequestMapping(value="/memberList.do")	
	public @ResponseBody List<Member> selectMemberList(){
		List<Member> list=service.selectMemberList();
		//log.debug("{}",list);
		
		return list;
	}
	
	
	@RequestMapping(value="/ajax/insert",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean insertTest(@RequestBody Member m) {
		log.debug("{}",m);
		
		return true;	//얘가 fetch body로 감
		
	}
		
	
	@RequestMapping("/loginpage.do")
	public String loginpage() {
		return "member/loginpage";
	}
	
	
	
}
