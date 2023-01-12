package com.bs.spring.demo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.vo.Demo;
import com.bs.spring.member.vo.Member;

public interface DemoDao {
	
	int insertDemo(SqlSessionTemplate session,Demo demo);
	
	List<Demo> selectDemoList(SqlSessionTemplate session);


}
