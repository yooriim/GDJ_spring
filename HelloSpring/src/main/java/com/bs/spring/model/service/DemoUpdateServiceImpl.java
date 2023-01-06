package com.bs.spring.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.vo.Demo;
import com.bs.spring.model.dao.DemoUpdateDao;

@Service
public class DemoUpdateServiceImpl implements DemoUpdateService {
	
	private DemoUpdateDao dao;
	private SqlSessionTemplate session;
	
	@Autowired	
	public void setDao(DemoUpdateDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}
	
	@Override
	public Demo selectDemo(int no) {
		// TODO Auto-generated method stub
		return dao.selectDemo(session,no);
	}
	
	@Override
	public int updateDemo(Demo d) {
		// TODO Auto-generated method stub
		return dao.updateDemo(session,d);
	}



	
	

}
