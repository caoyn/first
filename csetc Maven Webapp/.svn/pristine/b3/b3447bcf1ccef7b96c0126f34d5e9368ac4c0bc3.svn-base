/**
 * 文件名: CustomerCallbackController.java
 * 描述:客户回访表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-16 
 */
package com.icss.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbCustomerCallback;
import com.icss.impl.CustomerCallbackImpl;
import com.icss.util.SysControllerLog;

@Controller("CustomerCallbackController")
@RequestMapping("/callback")
public class CustomerCallbackController {

	@Autowired
	private CustomerCallbackImpl CustomerCallbackImpl;
	
	//查看今日某客户的回访记录
	@RequestMapping("getCallbackById.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000016")
	public @ResponseBody String getCallbackById(HttpServletRequest request){
		return JSON.toJSONString(CustomerCallbackImpl.getCallbackById(request));
	}
	//新增某客户的回访记录
	@RequestMapping("addCallbackinfo.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000016")
	public @ResponseBody String addCallbackinfo(@ModelAttribute("c") TbCustomerCallback callback,HttpServletRequest request,HttpSession session) throws ParseException{
		String tipdatetime = request.getParameter("tipdatetime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		callback.setTipdate(sdf.parse(tipdatetime));
		callback.setReportuserid((String)session.getAttribute("userid"));
		callback.setCallbackid(null);
		return CustomerCallbackImpl.addCallbackinfo(callback) + "";
	}
	
	//修改某客户的回访记录
	@RequestMapping("updCallbackById.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000016")
	public @ResponseBody String updCallbackById(@ModelAttribute("c") TbCustomerCallback callback,HttpServletRequest request,HttpSession session) throws ParseException{
		String tipdatetime = request.getParameter("tipdatetime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		callback.setTipdate(sdf.parse(tipdatetime));
		return CustomerCallbackImpl.updCallbackById(callback)+ "";
	}
	
	//查看某用户对某客户的增加的回访记录
	@RequestMapping("getCallbackBySome.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000016")
	public @ResponseBody String getCallbackBySome(HttpServletRequest request,HttpSession session){
		return JSON.toJSONString(CustomerCallbackImpl.getCallbackBySome(request, session));
	}
	
	//查看待回访的记录
	@RequestMapping("getCallbackDatainfo.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000016")
	public @ResponseBody String getCallbackDatainfo(HttpSession session){
		return JSON.toJSONString(CustomerCallbackImpl.getCallbackDatainfo(session));
	}
	
	//回访数据的页面跳转
	@RequestMapping("callback.do")
	public String callback(HttpSession session){
		CustomerCallbackImpl.operbtn(session);
		return "customer/callback";
	}
	
	//更改回访数据的状态
	@RequestMapping("updCallbackStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000016")
	public @ResponseBody String updCallbackStatus(HttpServletRequest request){
		return CustomerCallbackImpl.updCallbackStatus(request) + "";
	}
	
	//查看那某客户的回访数据
	@RequestMapping("getCallbackByCid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000016")
	public @ResponseBody String getCallbackByCid(HttpServletRequest request){
		return CustomerCallbackImpl.getCallbackByCid(request);
	}
	
	//按条件查看那回访数据
	@RequestMapping("selectCallbackData.do")
	public @ResponseBody String selectCallbackData(HttpSession session,HttpServletRequest request){
		return CustomerCallbackImpl.selectCallbackData(session, request);
	}
}
