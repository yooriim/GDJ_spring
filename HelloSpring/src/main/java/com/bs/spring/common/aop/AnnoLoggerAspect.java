package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

//어노테이션 방식으로 aop 적용하기
@Component
@Aspect
@Slf4j
public class AnnoLoggerAspect {
	
	//pointcut 등록하기
	@Pointcut("execution(* com.bs.spring.member..*(..))")
	public void memberLogger() {}
	
	//advisor 등록
	@Before("memberLogger()")
	public void loggerBefore(JoinPoint jp) {
		Signature sig=jp.getSignature();
		//log.debug(sig.getName()+" 실행 전");
		//log.debug(sig.getDeclaringTypeName());
		//log.debug("파라미터 데이터 받아오기");
//		Object[] params=jp.getArgs();
//		if(params!=null) {
//			for(Object o:params) {
//				log.debug("파라미터 : {}",o);
//			}
//		}
		
		//log.debug("==============================");
		
	}
	
	@After("memberLogger()")
	public void loggerAfter(JoinPoint jp) {
		Signature sig=jp.getSignature();
		//log.debug(sig.getName()+" 실행 후");
		//log.debug(sig.getDeclaringTypeName());
		//log.debug("==============================");
	}
	
	//실행 전후 모두 실행하는 메소드 구현하기
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demoLogger() {}
	
	@Around("demoLogger()")
	public Object demoLoggerAround(ProceedingJoinPoint join) throws Throwable {
		
		//실행 전, 후를 구분하는 메소드 -> join.proceed();
		StopWatch stop=new StopWatch();
		stop.start();
		
		Object[] params=join.getArgs();
		Signature sig=join.getSignature();
		
		Object obj=join.proceed(); //*************얘 기준!으로 before after가 나뉨 
		
		stop.stop();
		//log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		
		return obj; //반환을 해줘야 정상적으로 돌아감
			
		
		//실행 전, 후를 구분하는 메소드 -> join.proceed();
//		StopWatch stop=new StopWatch();
//		stop.start();
//		stop.stop();
//		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
//
//		return join.proceed(); //proceed가 기준인디 호출하자 마자 반환해버린다?  얘 위에 있는ㅇ ㅐ들은 before다
		
		
		//실행 전, 후를 구분하는 메소드 -> join.proceed();
//		Object obj=join.proceed();  // 얘 밑에 있는 코드들은 after와 같다
//		StopWatch stop=new StopWatch();
//		stop.start();
//		stop.stop();
//		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
//
//		return obj; 

	
	}
	
	//어떤 exception이 발생하면 이걸 처리해! 하는 역할 ㅋ ..?
	@AfterThrowing("execution(* com.bs.spring..*(..))")
	public void exceptionTest(JoinPoint jp) {
		log.debug("에러 발생");
	}
	
	
	
	
	
}
