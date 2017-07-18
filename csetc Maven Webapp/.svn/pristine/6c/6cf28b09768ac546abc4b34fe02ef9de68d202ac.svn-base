package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbCustomerAssign;


public interface SysCustomerAssionService {
	int insert(TbCustomerAssign customerAssign);
	
	int assignCustomer(HttpServletRequest request, HttpSession session);
	
	List<TbCustomerAssign> consultCustomerData(HttpServletRequest request,HttpSession session);
	
	int updCustomerAssignStatus(HttpServletRequest request);
	
	void operbtn(HttpSession session);
	
	String getMyCustomerSelect(HttpSession session);
	
	String getAssignByCid(HttpServletRequest request);
	
	String workdata(HttpSession session);
	
	String teamData(HttpSession session);
}
