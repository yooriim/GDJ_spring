package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Override
	public void test() {
		System.out.println("dao - test() 실행");
		
	}
	
	@Override
	public Member selectmemberById(SqlSessionTemplate session,Member m) {
		// TODO Auto-generated method stub
		return session.selectOne("member.selectmemberById",m);
	}

	@Override
	public int enrollMemberend(SqlSessionTemplate session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.enrollMember",m);
	}

	@Override
	public List<Member> selectMemberList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("member.selectMemberList");
	}

	
	

}
