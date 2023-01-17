package com.bs.spring.jpa.model.entity;

import java.io.Serializable;

import lombok.Data;

//복합키 식별자 클래스로
//복합키 식별자 클래스는 조건이 있음
//1. 기본생성자가 있어야 한다.
//2. 클래스가 public으로 선언되어야 한다.
//3. Serializable인터페이스를 구현해야 한다.
//4. equals, hashcode 메소드가 오버라이딩 되어 있어야 한다.

//equals, hashcode @Data로 해결
@Data 
public class StudentClubsId implements Serializable {
	//student, club을 복합키로 연결하는 클래스
	private long student; //StudentClub 클래스의 Student클래스 필드명  재네가 사용하는 key값이 long타입이니까 거기에 맞춰서 long타입으로
	private long club;	//StudentClub 클래스의 Club 클래스의 필드명
}
