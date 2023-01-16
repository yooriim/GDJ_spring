package com.bs.spring.chart.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bs.spring.chart.model.dao.ChartDao;
import com.bs.spring.chart.model.vo.OrderSheet;

@Service
public class ChartSerivceImpl implements ChartService {
	
	private ChartDao dao;
	private SqlSessionTemplate session;
	
	public ChartSerivceImpl(ChartDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}
	
	public List<OrderSheet> getDailySales(){
		return dao.getDailySales(session);
	}
	
	//chartjs test
	@Override
	public int selectFemCount() {
		// TODO Auto-generated method stub
		int result=dao.selectFemCount(session);
		System.out.println(result);
		return dao.selectFemCount(session);
	}

	@Override
	public int selectMaleCount() {
		// TODO Auto-generated method stub
		return dao.selectMaleCount(session);
	}
	
	
}
