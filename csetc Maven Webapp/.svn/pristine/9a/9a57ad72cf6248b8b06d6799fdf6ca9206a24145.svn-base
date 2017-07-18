/**
 * 文件名: PaymentController.java
 * 描述:订单收款表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-07
 */
package com.icss.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbOrderPayment;
import com.icss.impl.PaymentImpl;
import com.icss.util.SysControllerLog;

@Controller("PaymentController")
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentImpl PaymentImpl;
	
	
	//订单收款的页面跳转
	@RequestMapping("payment.do")
	public String payment(HttpSession session){
		PaymentImpl.operbtn(session);
		return "order/payment";
	}
	
	//新增收款信息
	@RequestMapping("addPaymentInfo.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000019")
	public @ResponseBody String addPaymentInfo(HttpServletRequest request, HttpSession session){
		List<TbOrderPayment> list = JSON.parseArray(request.getParameter("payment"), TbOrderPayment.class); 
		return PaymentImpl.addPaymentInfo(list, session);
	}
	
	//新增退费数据
	@RequestMapping("addOrderRefund.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000019")
	public @ResponseBody String addOrderRefund(@ModelAttribute("p") TbOrderPayment payment,HttpSession session){
		return PaymentImpl.addOrderRefund(payment, session);
	}
	
	//按条件查看总收入
	@RequestMapping("getGrossIncome.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000019")
	public @ResponseBody String getGrossIncome(HttpServletRequest request){
		return PaymentImpl.getGrossIncome(request);
	}
	//按条件查看每个产品总收入
	@RequestMapping("getIncomeByProd.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000019")
	public @ResponseBody String getIncomeByProd(HttpServletRequest request){
		return PaymentImpl.getIncomeByProd(request);
	}
	
	//查看订单数据
	@RequestMapping("orderData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000019")
	public @ResponseBody String orderData(){
		return PaymentImpl.orderData();
	}
}
