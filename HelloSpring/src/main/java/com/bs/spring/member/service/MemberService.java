package com.bs.spring.member.service;

import java.util.List;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();
	
	Member selectmemberById(Member m);
	
	int enrollMemberend(Member m);
	
	List<Member> selectMemberList();
	
}
