package com.bs.spring.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
	//jpa로 DB와 클래스를 연결하기 위해서는 
	//jpa가 제공하는 EntityManager, entityTransaction 클래스를 이용
	//Persistence클래스에서 제공하는 createEntityManagerFactory 메소드를 이용해서
	//EntityManagerFactory 클래스를 생성
	//EntityManagerFactory 클래스의 createEntityManager() 메소드를 이용해서 EntitlyManager클래스를 생성

	//생성해야할 객체들을 bean으로 등록하기
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		//persistence.xml에 설정한 <persistence-unit name="bstest"> name 값으로 연결
		//db를설정해서 생성함
		//createEntityManagerFactory메소드의 인자로 대임
		
		return Persistence.createEntityManagerFactory("bstest");
		
	}
	
	@Bean
	public EntityManager entitymanager() {
		return entityManagerFactory().createEntityManager();
	}
	
	
}
