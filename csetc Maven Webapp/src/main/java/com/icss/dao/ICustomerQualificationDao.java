package com.icss.dao;

/**
 * 客户资质持久层接口设计
 * @author caoyanan
 * @time 2017年7月18日下午4:33:18
 * @description
 */
public interface ICustomerQualificationDao {

	/**
	 * 通过订单号匹配出客户id
	 * @param orderId 订单号
	 * @return 客户编号
	 */
	String getCusIdByOrderId(String orderId);
}
