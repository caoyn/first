package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbOrderPayment;


public interface PaymentService {
	
	String addPaymentInfo(List<TbOrderPayment> list,HttpSession session);
	
	String addOrderRefund(TbOrderPayment orderPayment, HttpSession session);
	
	String getGrossIncome(HttpServletRequest request);
	
	String getIncomeByProd(HttpServletRequest request);
	
	String orderData();
}
