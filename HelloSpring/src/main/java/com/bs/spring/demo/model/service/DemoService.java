package com.bs.spring.demo.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.member.vo.Demo;
import com.bs.spring.member.vo.Member;

public interface DemoService {
	
	int insertDemo(Demo demo);
	
	List<Demo> selectDemoList();
	

	
}
