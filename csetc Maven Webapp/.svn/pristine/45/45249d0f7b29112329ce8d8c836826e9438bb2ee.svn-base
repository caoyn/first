package com.icss.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbCustomerCallback;
import com.icss.bean.TbCustomerReservation;

public interface CustomerReservationService {
	
	int addCustomerReservation(HttpServletRequest request,TbCustomerReservation reservation);
	
	int getExistReservation(HttpServletRequest request,HttpSession session);
	
	String getReservationCustomerData(HttpSession session);
	
	int updCustomerReservationStatus(TbCustomerReservation reservation);
	
	void operbtn(HttpSession session);
	
	String getReservationDataBySome(HttpSession session,HttpServletRequest request);
	
	String getReservationByCid(HttpServletRequest request);
	
	String updVisitTime(TbCustomerReservation reservation);
	
	String resevationCallback(TbCustomerCallback callback, HttpServletRequest request, HttpSession session);
	
}
