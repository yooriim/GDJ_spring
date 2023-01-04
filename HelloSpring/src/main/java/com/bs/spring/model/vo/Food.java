package com.bs.spring.model.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
//어노테이션을 이요해서 SpringBean으로 등록하기 -> @Component
@Component
public class Food {
	private String name;
	private int price;
	private String type;
	private Person p;
	
	@Autowired
	@Qualifier(value="yeonji") //person이 여러개일 땐 qualifier로 지정할 수 있음 
	public void setPerson(Person p) {
		this.p=p;
	}
	
}
