/**
 * 文件名: SysBasecodeController.java
 * 描述:基础代码表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-22 
 */
package com.icss.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.bean.TbCustomerCallback;
import com.icss.bean.TbCustomerReservation;
import com.icss.impl.CustomerReservationImpl;
import com.icss.util.SysControllerLog;

@Controller("CustomerReservationController")
@RequestMapping("/reservation")
public class CustomerReservationController {

	@Autowired
	private CustomerReservationImpl CustomerReservationImpl;
	
	//新增预约记录
	@RequestMapping("addCustomerReservation.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000015")
	public @ResponseBody String addCustomerReservation(@ModelAttribute("reservation") TbCustomerReservation reservation, HttpServletRequest request,HttpSession session){
		reservation.setReportuserid((String) session.getAttribute("userid"));
		reservation.setReservationtime(new Date());
		return CustomerReservationImpl.addCustomerReservation(request, reservation) + "";
	}
	
	//查看某客户是否有预约记录
	@RequestMapping("getExistReservation.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000015")
	public @ResponseBody String getExistReservation(HttpServletRequest request,HttpSession session){
		return CustomerReservationImpl.getExistReservation(request,session) + "";
	}
	
	//客户预约页面跳转
	@RequestMapping("reservation.do")
	public String reservation(HttpSession session){
		CustomerReservationImpl.operbtn(session);
		return "customer/reservation";
	}
	
	//查看预约客户信息
	@RequestMapping("getReservationCustomerData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000015")
	public @ResponseBody String getReservationCustomerData(HttpSession session){
		return CustomerReservationImpl.getReservationCustomerData(session);
	}
	
	//修改客户上门状态数据
	@RequestMapping("updCustomerReservationStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000015")
	public @ResponseBody String updCustomerReservationStatus(@ModelAttribute("re") TbCustomerReservation reservation){
		return CustomerReservationImpl.updCustomerReservationStatus(reservation) + "";
	}
	
	//模糊查询客户上门数据
	@RequestMapping("getReservationDataBySome.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000015")
	public @ResponseBody String getReservationDataBySome(HttpSession session,HttpServletRequest request){
		return CustomerReservationImpl.getReservationDataBySome(session, request);
	}
	
	//查看某客户的预约记录
	@RequestMapping("getReservationByCid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000015")
	public @ResponseBody String getReservationByCid(HttpServletRequest request){
		return CustomerReservationImpl.getReservationByCid(request);
	}
	
	//修改客户上门时间
	@RequestMapping("updVisitTime.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000015")
	public @ResponseBody String updVisitTime(@ModelAttribute("r") TbCustomerReservation reservation){
		return CustomerReservationImpl.updVisitTime(reservation);
	}
	
	//客户预约后回访
	@RequestMapping("resevationCallback.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000016")
	public @ResponseBody String resevationCallback(@ModelAttribute("a") TbCustomerCallback callback, HttpServletRequest request, HttpSession session){
		return CustomerReservationImpl.resevationCallback(callback, request,session);
		
	}
}
