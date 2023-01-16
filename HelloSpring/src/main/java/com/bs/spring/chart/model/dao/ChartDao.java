package com.bs.spring.chart.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.chart.model.vo.OrderSheet;

public interface ChartDao {
	//chartjs
	int selectFemCount(SqlSessionTemplate session);
	int selectMaleCount(SqlSessionTemplate session);
	
	
	List<OrderSheet> getDailySales(SqlSessionTemplate session);

}
