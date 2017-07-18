package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbCustomerCallback;

public interface CustomerCallbackService {
	
	TbCustomerCallback getCallbackById(HttpServletRequest request);
	
	int addCallbackinfo(TbCustomerCallback callback);
	
	int updCallbackById(TbCustomerCallback callback);
	
	TbCustomerCallback getCallbackBySome(HttpServletRequest request, HttpSession session);
	
	List<TbCustomerCallback> getCallbackDatainfo(HttpSession session);
	
	int updCallbackStatus(HttpServletRequest request);
	
	void operbtn(HttpSession session);
	
	String getCallbackByCid(HttpServletRequest request);
	
	String selectCallbackData(HttpSession session,HttpServletRequest request);
}
