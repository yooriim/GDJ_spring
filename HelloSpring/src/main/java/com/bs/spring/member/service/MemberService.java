package com.bs.spring.member.service;

import java.util.Map;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();
	
	Member selectmemberById(Member m);
	
	int enrollMemberend(Member m);
	
}
