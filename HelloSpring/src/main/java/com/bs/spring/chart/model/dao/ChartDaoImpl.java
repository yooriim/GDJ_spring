package com.bs.spring.chart.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.bs.spring.chart.model.vo.OrderSheet;

@Repository
public class ChartDaoImpl implements ChartDao {

	@Override
	public List<OrderSheet> getDailySales(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("orderSheet.getDailySales");
	}
	
	//chartjs
	@Override
	public int selectFemCount(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("orderSheet.selectFemCount");
	}

	@Override
	public int selectMaleCount(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("orderSheet.selectMaleCount");
	}
	
}
