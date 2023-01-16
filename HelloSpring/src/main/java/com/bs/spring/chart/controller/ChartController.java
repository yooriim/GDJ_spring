package com.bs.spring.chart.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.chart.model.service.ChartService;
import com.bs.spring.chart.model.vo.OrderSheet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

//@RequestMapping("/chart")
@Controller
@Slf4j
public class ChartController {
	private ChartService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public ChartController(ChartService service, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.service = service;
		this.passwordEncoder = passwordEncoder;
	} 
	
	//	<!-- chartJs 테스트용 -->	
	
	  @RequestMapping("/chart/genderRatio.do") 
	  @ResponseBody
	  public String showChart() { 
		  int femCount=service.selectFemCount(); 
		  int maleCount=service.selectMaleCount();
	  
		  // System.out.println(femCount); 
		  // System.out.println(maleCount);	  
		  
		  Gson gson=new Gson();
		  JsonArray jarray=new JsonArray();
		  JsonObject object=new JsonObject();
		  
		  object.addProperty("femCount",femCount);
		  object.addProperty("maleCount",maleCount);
		  jarray.add(object);
		  
		  String json=gson.toJson(jarray);
		  
		  log.debug("성별 {}",json);
		  
		  return json;
	  
	  }
	 
	
	@RequestMapping("/chart/chart.do")
	@ResponseBody
	public String dailySales() {
		
		//데이터 가져오기
		List<OrderSheet> list=service.getDailySales();
		
		//gson 객체 생성
		Gson gson=new Gson();
		JsonArray jarray=new JsonArray();
		
		//date->string
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		
		Iterator<OrderSheet> it=list.iterator();
		while(it.hasNext()) {
			OrderSheet orderSheet=it.next();
			JsonObject object=new JsonObject();
			
			int sale=orderSheet.getTotalPrice();
			//log.debug("sale : {}",sale);
			
			
			Date dt=orderSheet.getOrderDate();
			String date=df.format(dt);
			//log.debug("date : {}",date);
			
			object.addProperty("sale", sale);
			object.addProperty("date", date);
			jarray.add(object);
		}
		
		String json=gson.toJson(jarray);		
		log.debug("json변환: "+json);
		
		return json;
		
	}
	
}
