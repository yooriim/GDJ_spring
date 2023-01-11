package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
		log.debug(sig.getName()+" 실행전");
		log.debug(sig.getDeclaringTypeName());
		log.debug("==============================");
		
	}
	
	
	
}
