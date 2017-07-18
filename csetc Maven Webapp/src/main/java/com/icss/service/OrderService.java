package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbOrderChanged;
import com.icss.bean.TbOrderOrderdetail;
import com.icss.bean.TbOrderOrders;
import com.icss.bean.TbSysBasecode;

public interface OrderService {
	int insertAndUpdateCustomerOrder(TbOrderOrders orders, List<TbOrderOrderdetail> orderdetails,HttpSession session);

	List<TbOrderOrders> getOrderData(HttpSession session); 
	
	String updOrderUser(TbOrderOrders orders,HttpServletRequest request, HttpSession session);
	
	String updOrderData(TbOrderOrders orders,HttpServletRequest request, HttpSession session);
	
	String updOrderStatus(TbOrderChanged changed,HttpServletRequest request);
	
	String getOrderDataByOid(HttpServletRequest request);
	
	String notPayOrder();
	
	String getNotPayOrderDataByOid(HttpServletRequest request);
	
	String getOrderDataByCid(HttpServletRequest request);
	
	// 获取上传客户纸质至少需要的交款金额
	Integer getAtLeastMoneyForQua();
	
	// 获取销售要上传的客户纸质
	List<TbSysBasecode> getQuaOfSales(int i);
		
}
