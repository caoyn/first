/**
 * 文件名: RefundController.java
 * 描述:订单退返款申请表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-19 
 */
package com.icss.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.bean.TbOrderRefund;
import com.icss.impl.RefundImpl;
import com.icss.util.SysControllerLog;

@Controller("RefundController")
@RequestMapping("/refund")
public class RefundController {
	
	@Autowired
	private RefundImpl RefundImpl;
	
	//新增退返款信息
	@RequestMapping("addRefundinfo.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000020")
	public @ResponseBody String addRefundinfo(@ModelAttribute("r") TbOrderRefund refund, HttpSession session,HttpServletRequest request){
		return RefundImpl.addRefundinfo(refund, session,request);
	}
	
	//退返款数据页面跳转
	@RequestMapping("refund.do")
	public String refund(HttpSession session){
		RefundImpl.operbtn(session);
		return "order/refund";
	}
	
	//退返款数据展示
	@RequestMapping("getRefundData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000020")
	public @ResponseBody String getRefundData(HttpSession session,HttpServletRequest request){
		return RefundImpl.getRefundData(session);
	}
	
	//同意该申请
	@RequestMapping("adoptApprove.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000020")
	public @ResponseBody String adoptApprove(HttpServletRequest request, HttpSession session){
		return RefundImpl.adoptApprove(request, session);
	}
	
	//驳回该申请
	@RequestMapping("rejectApprove.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000020")
	public @ResponseBody String rejectApprove(HttpServletRequest request, HttpSession session){
		return RefundImpl.rejectApprove(request, session);
	}
	
	//查看审批流详情
	@RequestMapping("approveDetail.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000020")
	public @ResponseBody String approveDetail(HttpServletRequest request){
		return RefundImpl.approveDetail(request);
	}
}
