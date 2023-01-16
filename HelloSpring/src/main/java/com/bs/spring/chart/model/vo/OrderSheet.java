package com.bs.spring.chart.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSheet {
	private int orderNo;
	private String payment;
	private int totalPrice;
	private Date orderDate;
}
