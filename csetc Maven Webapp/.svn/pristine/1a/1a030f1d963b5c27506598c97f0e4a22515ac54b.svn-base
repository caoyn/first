/**
 * 文件名: CustomerRemarkImpl.java
 * 描述:客户批注接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-13
 */
package com.icss.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbCustomerRemark;
import com.icss.dao.TbCustomerRemarkMapper;
import com.icss.service.CustomerRemarkService;


@Service("CustomerRemarkImpl")
public  class CustomerRemarkImpl implements CustomerRemarkService {
	
	@Autowired
	private TbCustomerRemarkMapper CustomerRemarkMapper;
	
	@Override
	public String addRemark(TbCustomerRemark remark, HttpSession session) {
		//新增客户批注
		String userid = (String) session.getAttribute("userid");
		remark.setRemarkuserid(userid);
		return "" + CustomerRemarkMapper.insert(remark);
	}

	@Override
	public String qryCustomerRemrk(HttpServletRequest request) {
		// 查看某客户批注
		return JSON.toJSONString(CustomerRemarkMapper.qryCustomerRemrk(request.getParameter("customerid")));
	}

	@Override
	public String checkRemark(HttpServletRequest request, HttpSession session) {
		// 查看自己是否有给客户批注
		TbCustomerRemark remark = new TbCustomerRemark();
		remark.setRemarkuserid((String) session.getAttribute("usreid"));
		remark.setCustomerid(request.getParameter("customerid"));
		return CustomerRemarkMapper.checkRemark(remark)+ "";
	}
	


}










