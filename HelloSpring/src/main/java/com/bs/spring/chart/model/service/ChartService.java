package com.bs.spring.chart.model.service;

import java.util.List;

import com.bs.spring.chart.model.vo.OrderSheet;

public interface ChartService {
	
	List<OrderSheet> getDailySales();

	int selectFemCount();

	int selectMaleCount();

}
