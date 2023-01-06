package com.bs.spring.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.vo.Demo;
import com.bs.spring.member.vo.Member;
import com.bs.spring.model.service.DemoService;

@Controller
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	@Autowired
	public DemoController(DemoService service) {
		this.service=service;
	}
	
	//클라이언트가 요청한 서비스를 실행해주는 기능
	//클라이언트가 요청한 서비스 주소(url)에 맞는 메소드를 구현
	//메소드 구현할 때 서비스 주소와 연결해주는 어노테이션을 선언
	//@RequestMapping(연결주소[,추가 옵션 설정])
	// - Rest방식의 서비스 구현시 사용 @GetMappin, @PostMapping... 어노테이션 사용
	
	//demo페이지는 연결하는 메소드 구현
	//반환형은 String -> SpringBean으로 등록된 ViewResolver를 통해 페이지를 지정된 위치에서 찾아  응답
	//매개변수는 없음
	//메소명은 알아서 해!!
	@RequestMapping("/demo/demo.do")
	public String demoPage() {
		//InternalResourceViewResolver가 처리함.
		//InternalResourceViewResolver에 저장된 prefix, suffix의 내용을 반환값이랑 연결해서 view화면을 찾음
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		return "demo/demo";
		// -> /WEB-INF/views/demo/demo.jsp -> RequestDispatcher.forward()를 실행함.
		// request.getRequestDispatcher("/WEB-INF/views/demo/demo.jsp").forward(request,response)
	}
	
	//요청매핑 메소드의 매개변수와 반환형
	//1. 반환형
	//	- String : ViewResolver에 의해서 view화면ㅇ르 반환할 때 사용
	//	- void : HttpResponse로 직접 응답메세지를 작성할 때 사용(upload,download...)
	//	- ModelAndView : ModelAndView객체를 반환 (화면정보, view에 전송할 데이터를 가지고 있음)
	//	- 클래스타입(ReffernceType) : 생성된 객체를 반환 -> json으로 반환~!~!
	
	//2. 매개변수 -> Spring이 알아서 넣어줘욤
	//	- HttpServletRequest : 서블릿의 그녀석?? 아! 
	//	- HttpServletResponse : 얘도 서블릿의 걔
	//	- HttpSession : 서블릿의 걔
	//	- java.util.Locale
	//	- InputStream/Reader : 파일 IO할떄 사용하는 stream객체
	//	- OutputStream/Writer : 파일 IO할떄 사용하는 stream객체
	//	- 기본 자료형 변수 : 클라이언트가 보낸 name 명칭과 변수명이 일치하면 대입해줌
	//		- name과 일치하지 안흥ㄴ 변수명을 사용했을 때는 @RequestParam 어노테이션을 이용해서 mapping할 수 있음
	//		- 추가적인 설정이 필요할때도 @RequestParam어노테이션을 사용할수 있음
	//		- **주의** 기본자료형 변수를 선언했을 때는 반드시 모든 변수에 연결되는 값을 전달해야 함 
	//	- 클래스(RefferenceType) 변수 : command라고 하고 클라이언트가 보낸 데이터를 지정한 타입의 클래스를 생성해서 대입
	//		- 필드명과 클라이언트가 보낸 name명이 일치하는 값
	//	- java.util.Map : 클라이언트가 보낸 데이터를 map으로 대입해줌
	
	//	- Model : 서버에서 데이터를 저장하는 용도의 객체 (request.setParameter 대체재. 얘보다 쌈 ㅋ) / 1회용 데이터 저장
	//	- ModelAndView : 저장할 데이터, 화면 정보를 한번에 저장하는 객체 1회용
	
	//	- 기본자료형 변수 선언 시 @RequestHeader, @CookieValue 어노테이션을 이용하면 
	//	header나 cookie의 값을 바로 저장할수 있음
	
	//추가 어노테이션
	//@ResponseBody -> json형태로 반환할 때 사용
	//@RequestBody -> json형태로 전달된 데이터를 vo객체와 mapping 해주는 것
	
	//서블릿처럼 이용하기
	@RequestMapping("/demo/demo1.do")
//	public String demo1(HttpServletRequest req, HttpServletResponse res) {
		public void demo1(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println(req);
		System.out.println(res);
		
		String name=req.getParameter("devName");
		int age=Integer.parseInt(req.getParameter("devAge"));
		String email=req.getParameter("devEmail");
		String gender=req.getParameter("devGender");
		String[] devLang=req.getParameterValues("devLang");
		//System.out.println(name+age+gender+email);
		for(String d : devLang) {
			System.out.println(d);
		}
		
		Demo d=Demo.builder().devName(name).devAge(age).devEmail(email).devGender(gender).devLang(devLang).build();
		
		req.setAttribute("demo", d);
//		req.getRequestDispatcher("/WEB-INF/demo/demoresult.jsp").forward(req, res);
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		out.print("<h1>데이터</h1>");
		//return "demo/demoresult";
		
	}
	
	
	//기본자료형으로 데이터 받아오기
	@RequestMapping("/demo/demo2.do")
	public String basicType(String devName, int devAge, String devEmail, String devGender,String[] devLang, Model model) {
		System.out.println(devName+" "+devAge+" "+devEmail+" "+devGender);
		for(String l:devLang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder().devName(devName).devAge(devAge)
				.devGender(devGender).devEmail(devEmail).devLang(devLang).build();
		
		//model 객체에 데이터 저장하기
		//key,value형식으로 데이터를 저장함
		//addAttribute() 메소드를 이용
		model.addAttribute("demo",d);
		
		return "demo/demoresult";
	}
	
	@RequestMapping("/demo/demo3.do")
	public String requestParamTest(
			@RequestParam(value="devName") String name, 
			@RequestParam(value="devAge",defaultValue = "1") int age, // defaultValue : 기본값 설정
			@RequestParam(value="devEmail") String email, 
			@RequestParam(value="devGender") String gender,
			@RequestParam(value="devLang",required=false) String[] lang,  //required=false 있으면 받고 없으면 넘어가라고 주는 옵션
			Model model) {
		System.out.println(name+age+gender+email);
		if(lang!=null) {
			for(String d:lang) {
					System.out.println(d);
			}
		}
		Demo d=Demo.builder().devName(name).devAge(age)
				.devGender(gender).devEmail(email).devLang(lang).build();
		
		model.addAttribute("demo",d);
		
		return "demo/demoresult";
	}
	
	@RequestMapping("/demo/demo4.do")
	public String commandmappingTest(Demo demo,Model model) {
		System.out.println(demo);
		model.addAttribute("demo",demo);
		return "demo/demoresult";
	}
	
	@RequestMapping("/demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param,String[] devLang,Model model) { //@RequestParam 이거 꼭 써줘야 함~~
		param.put("devLang", devLang);
		System.out.println(param);
		model.addAttribute("demo",param);
		return "demo/demoresult";
	}
	
	@RequestMapping("/demo/demo6.do")
	public String extraTest(@CookieValue(value="testData", required = false) String testData,
			@RequestHeader(value="User-agent") String userAgent,
			@SessionAttribute(value="sessionId") String id,
			@RequestHeader(value="Referer") String referer
			) {
		
		System.out.println(testData);
		
		System.out.println(userAgent);
		
		System.out.println(id);
		
		return "demo/demoresult";	
	
	}
	
	//ModelAndView로 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewTest(ModelAndView mv,Demo demo) {
		//ModelAndView객체는 view 설정과, 데이터 저장을 같이 할수 이는 객체
		//data 저장 : addObject() 메소드 이용
		//view 설정 : setViewName("view이름")
		
		mv.addObject("demo",demo);
		mv.setViewName("demo/demoresult");
		
		return mv;
	}
	//restful하게 서비스를 구현할 때 사용 -> @RestController
	//@GetMapping
	//@PostMapping	
	//@PutMapping
	//@DeleteMapping
	//@PathVariable
	
	@RequestMapping("/demo/responsetest.do")
	@ResponseBody
	public List<String> responseTest() {
		return List.of("1","2","3","4");
	}
	
	@RequestMapping("demo/insertDemo.do")
	public String insertDemo(Demo demo) {
		int result=service.insertDemo(demo);
		//spring에서 redirect 처리하기
		return "redirect:/demo/demo.do";
	}
	
	
	@RequestMapping("demo/selectDemoList.do")
	public ModelAndView demoList(ModelAndView mv) {
		List<Demo> list=service.selectDemoList();
		System.out.println(list);
		mv.addObject("demos",list);
		mv.setViewName("demo/demolist");
		return mv;
	
		//	public String selectDemoList() {		
//		List<Demo> list=service.selectDemoList();
//		System.out.println(list);
//		return "";

	}
	

	
	

}
	

