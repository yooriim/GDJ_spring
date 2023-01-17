package com.bs.spring.jpa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//생성한 클래스를 jpa와 연동하는 Entity로 등록하려면 어노테이션을을 이용
//@Entity 어노테이션 -> jpa관리하는 DB와 연동되는 객체를 의미함

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "major")
@Entity
@Table(name="jpa_member") 
//uniqueConstraints 컬럼에 대한 unique 제약조건 설정( 테이블에서)
//db테이블을 설정. schema, catalog 속성 설정, 
@SequenceGenerator(name = "seq_jpamemberno", sequenceName="seq_jpamemberno",initialValue = 1, allocationSize = 1) //sequence 생성해주는 어노테이션
public class JpaMember {
	
	@Id	//컬럼으로 생성할때 pk값 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_jpamemberno") //자동생성된 값을 대입해주는 설정! *오라클에서 sequence 이용 
	@Column(name = "member_no") //컬럼설정
	private long memberNo;
	
	@Column(name="member_id",nullable=false,unique=true,length=80)	//nullable=false = NOT NULL
	private String memberId;
	
	@Column(name="member_pwd",nullable=false,length=80)	//nullable=false = NOT NULL
	private String memberPwd;
	
	private int age;
	private double height;
	
	@Column
	@Enumerated(EnumType.STRING) //enumtype에 대한 설정
	private MemberLevel memberLevel;
	
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
	
	@Lob
	private String intro;
	
//	@Lob
//	private byte[] data;
	
	
	//연관관계를 표시하는 어노테이션을 작성
	// @oneToMany, @ManyToOne, @OneToOne, @ManyToMany
	@ManyToOne
	@JoinColumn(name="majorNo")
	private Major major;
}
