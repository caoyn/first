package com.icss.dao;

import java.util.List;

import com.icss.bean.CustomerQualification;

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

	/**
	 * 保存一张资质图片
	 * @param 客户资质pojo实体类
	 * @return 影响的行数
	 */
	Integer save(CustomerQualification cq);

	/**
	 * 得到指定日期的最大id
	 * @param curDate
	 * @return 最大curDate id
	 */
	String getCurDateMaxId(String curDate);

	/**
	 * 通过制定条件获取资质(附件)记录
	 * @param cq
	 * @return
	 */
	List<CustomerQualification> getQua(CustomerQualification cq);

	/**
	 * 更新资质(附件)保证唯一！
	 * @param cq
	 * @return
	 */
	Integer update(CustomerQualification cq);
}
