/**
 * 文件名: CustomerRemarkController.java
 * 描述:客户批注表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-13 
 */
package com.icss.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.bean.TbCustomerRemark;
import com.icss.impl.CustomerRemarkImpl;
import com.icss.util.SysControllerLog;

@Controller("CustomerRemarkController")
@RequestMapping("/remark")
public class CustomerRemarkController {

	@Autowired
	private CustomerRemarkImpl CustomerRemarkImpl;
	
	//新增客户批注
	@RequestMapping("addRemark.do")
	@SysControllerLog(requestoper="ADD",permid="20170327000002")
	public @ResponseBody String addRemark(@ModelAttribute("r") TbCustomerRemark remark, HttpSession session){
		return CustomerRemarkImpl.addRemark(remark, session);
	}
	
	//查看某客户的批注信息
	@RequestMapping("qryCustomerRemrk.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000002")
	public @ResponseBody String qryCustomerRemrk(HttpServletRequest request){
		return CustomerRemarkImpl.qryCustomerRemrk(request);
	}
	
	//查看某客户是否有自己批注信息
	@RequestMapping("checkRemark.do")
	public @ResponseBody String checkRemark(HttpServletRequest request, HttpSession session){
		return CustomerRemarkImpl.checkRemark(request, session);
	}
}
