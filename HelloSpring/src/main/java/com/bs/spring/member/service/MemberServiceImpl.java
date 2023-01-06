package com.bs.spring.member.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	
	private MemberDao dao;
	private SqlSessionTemplate session;
	
	
	//alt shift s o 
	@Autowired
	public MemberServiceImpl(MemberDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}

	@Override
	public void test() {
		System.out.println("service test()");
		dao.test();
	}
	
	@Override
	public Member selectmemberById(Member m) {
		// TODO Auto-generated method stub
		return dao.selectmemberById(session,m);
	}

	@Override
	public int enrollMemberend(Member m) {
		// TODO Auto-generated method stub
		return dao.enrollMemberend(session,m);
	}
}
