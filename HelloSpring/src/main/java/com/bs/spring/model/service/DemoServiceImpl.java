package com.bs.spring.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.vo.Demo;
import com.bs.spring.member.vo.Member;
import com.bs.spring.model.dao.DemoDao;

@Service
public class DemoServiceImpl implements DemoService{
	
	private DemoDao dao;
	private SqlSessionTemplate session;

	@Autowired
	public DemoServiceImpl(DemoDao dao,SqlSessionTemplate session) {		
		this.dao = dao;
		this.session=session;
	}
	
	@Override
	public int insertDemo(Demo demo) {
		return dao.insertDemo(session, demo);
	}
	
	@Override
	public List<Demo> selectDemoList(){
		return dao.selectDemoList(session);
	}


	
}
