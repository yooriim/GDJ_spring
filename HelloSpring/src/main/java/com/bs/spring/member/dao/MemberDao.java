package com.bs.spring.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.vo.Member;

public interface MemberDao {
	void test();
	
	Member selectmemberById(SqlSessionTemplate session,Member m);
	
	int enrollMemberend(SqlSessionTemplate session,Member m);
}
