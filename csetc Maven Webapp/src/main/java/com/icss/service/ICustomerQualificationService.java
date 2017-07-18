package com.icss.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.icss.bean.CustomerQualification;

/**
 * 客户资质业务逻辑层接口设计
 * @author caoyanan
 * @time 2017年7月18日下午4:00:18
 * @description
 */
public interface ICustomerQualificationService {

	/**
	 * 上传客户资质
	 * @param cq	客户资质pojo实体类
	 * @param qualificationPicture	资质附件
	 * @return	上传信息(成功，失败)
	 */
	Map<String, String> uploadCusQua(CustomerQualification cq, MultipartFile qualificationPicture, HttpSession session);

	/**
	 * 通过订单号匹配出客户id
	 * @param orderId 订单号
	 * @return 客户编号
	 */
	String getCusIdByOrderId(String orderId);

}
