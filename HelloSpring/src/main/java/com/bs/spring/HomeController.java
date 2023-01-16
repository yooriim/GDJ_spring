package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.vo.Member;
import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"loginMember"})
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 등록되어 있는 springbean은 필드선언해서 사용
	// 메소드 내의 지역변수로는 쓸 수 없다.

	/*
	 * @Autowired
	 * 
	 * @Qualifier(value = "alonge") private Animal a;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value = "dog") private Animal b;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value = "getDongmin") private Person p;
	 * 
	 * @Autowired(required = false) // required=false : autowired하는데 필요한 bean이 없으면 걍
	 * 냅둬! 라는 의미 (false라고 안나오는건 아니고 // 있으면 나옴) private Food food;
	 */

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IllegalAccessException {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		if (1 == 1)
			throw new IllegalAccessException("내ㅔ맘대로 에러 !");

		return "home";
	}

	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		// 쿠키추가하기
		Cookie c = new Cookie("testData", "cookiedata");
		c.setMaxAge(60 * 60 * 24);
		res.addCookie(c);

		session.setAttribute("sesstionId", "admin");

		// 등록된 springbean 출력하기
//		a.setName("아롱이");
//		a.setAge(8);
//		a.setGender("여");

//		System.out.println(p);
//		System.out.println(food);
//		System.out.println(alonge);
//		System.out.println("dog : "+dog);		

		// 메인화면을 출력해주는 mapping 메소드
		// /WEB-INF/views/return값.jsp -> ==
		// request.getRequestDispatcher("/WEB-INF/views/return값.jsp").forward(req,res);

		// 23.01.09
		// Logger가 제공하는 메소드 이용해서 log 출력하기
		// 메소드 : debug, info, warn, error
		// 메소드는 출력되는 상황에 따라 결정해서 사용
		// debug : 개발 시에 사용하는 로그
		// info : 프로그램 실행 중 사용자에게 전달해야 하는 메세지 로그
		// warn : 비정상적으로 로직이 돌아갔을 때 경고 출력
		// error : 에러났을 때 ! 로그

		// logger 태그에 설정되어 있는 level 에 따라 메소드 실행여부가 결정됨.
		// debug < info < warn < error

		// logger.debug("난 debug야");
		// logger.info("난 info");
		// logger.warn("난 warn");
		// logger.error("난 error");

		// logger로 다른 타입의 값 출력하기
		// logger.debug("food {}",food);

		return "index";

	}

	@RequestMapping("/error.do")
	public String loginFail() {
		// 인증 실패 후 실행되는 메소드
		throw new AdminAccessException("로그인 실패");
	}
	
	@RequestMapping("/successLogin.do")
	public String successLogin(Model m) {
		//인증 후 실행되는 메소드
		Object o=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.debug("{}",o);
		
		//m.addAttribute("loginMember",Member.builder().userId(((User)o).getUsername()).build());
		m.addAttribute("loginMember",(Member)o);
		
		return "redirect:/";
	}
	
	
	
  //<!-- chartJs 테스트용 -->
  
	@RequestMapping("/chart") 
	public String chart() {	    
	return "chart/chartView"; 
	}
	 

}
