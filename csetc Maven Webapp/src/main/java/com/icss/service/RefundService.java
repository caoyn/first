package com.icss.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbOrderRefund;

public interface RefundService {
	String addRefundinfo(TbOrderRefund refund,HttpSession session,HttpServletRequest request);
	
	String getRefundData(HttpSession session);
	
	String adoptApprove(HttpServletRequest request,HttpSession session);
	
	String rejectApprove(HttpServletRequest request, HttpSession session);
	
	String approveDetail(HttpServletRequest request);
}
