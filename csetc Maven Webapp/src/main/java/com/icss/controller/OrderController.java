/**
 * 文件名: OrderController.java
 * 描述:订单表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-19 
 */
package com.icss.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.icss.bean.CustomerQualification;
import com.icss.bean.TbOrderChanged;
import com.icss.bean.TbOrderOrderdetail;
import com.icss.bean.TbOrderOrders;
import com.icss.bean.TbSysBasecode;
import com.icss.impl.OrderImpl;
import com.icss.util.FileUpload;
import com.icss.util.SysControllerLog;

@Controller("OrderController")
@RequestMapping("/order")
public class OrderController {
	
	
	@Autowired
	private OrderImpl OrderImpl;
	
	//新增签单信息
	@RequestMapping(value="addorder",produces = "text/plain;charset=UTF-8")
	@SysControllerLog(requestoper="ADD",permid="20170329000018")
	public @ResponseBody String addorder(@ModelAttribute("o") TbOrderOrders orders,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException{
		List<TbOrderOrderdetail> orderdetails = JSON.parseArray(request.getParameter("orderdetail"), TbOrderOrderdetail.class);  
		return OrderImpl.insertAndUpdateCustomerOrder(orders, orderdetails,session) + "";
	}

	//订单页面跳转
	@RequestMapping("order.do")
	public String order(HttpSession session, Model m){
		OrderImpl.operbtn(session);//用户可操作的按钮
		
		//获取销售要上传的客户纸质(根据设计，基础代表表的附件类型大类中level2id为1表示销售要上传的资质，后期可以维护)
		List<TbSysBasecode> quaOfSales = OrderImpl.getQuaOfSales(1);
		m.addAttribute("quaOfSales", quaOfSales);
		
		//获取上传客户纸质至少需要的交款金额
		Integer atLeastMoneyForQua = OrderImpl.getAtLeastMoneyForQua();
		m.addAttribute("atLeastMoneyForQua", atLeastMoneyForQua);
		return "order/order";
	}
	
	//查看签单信息
	@RequestMapping("getOrderData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000018")
	public @ResponseBody String getOrderData(HttpSession session){
		return JSON.toJSONString(OrderImpl.getOrderData(session));
	} 
	
	//更改订单转移信息
	@RequestMapping("updOrderUser.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000018")
	public @ResponseBody String updOrderUser(@ModelAttribute("o") TbOrderOrders orders,HttpServletRequest request,HttpSession session){
		return OrderImpl.updOrderUser(orders, request, session);
	}
	
	//更改订单数据
	@RequestMapping("updOrderData.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000018")
	public @ResponseBody String updOrderData(@ModelAttribute("o") TbOrderOrders orders,HttpServletRequest request,HttpSession session){
		//1.orderid 2.reason 3.memo
		return OrderImpl.updOrderData(orders, request, session);
	}
	//更改订单状态
	@RequestMapping("updOrderStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000018")
	public @ResponseBody String updOrderStatus(@ModelAttribute("oc") TbOrderChanged changed,HttpServletRequest request,HttpSession session){
		changed.setChangeuserid((String) session.getAttribute("userid"));
		return OrderImpl.updOrderStatus(changed, request);
	}
	
	//根据订单编号查看订单详情
	@RequestMapping("getOrderDataByOid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000018")
	public @ResponseBody String getOrderDataByOid(HttpServletRequest request){
		return OrderImpl.getOrderDataByOid(request);
	}
	
	//根据订单编号查看未付款订单详情
	@RequestMapping("getNotPayOrderDataByOid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000018")
	public @ResponseBody String getNotPayOrderDataByOid(HttpServletRequest request){
		return OrderImpl.getNotPayOrderDataByOid(request);
	}
	
	//获得待收款的订单数据
	@RequestMapping("notPayOrder.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000018")
	public @ResponseBody String notPayOrder(){
		return OrderImpl.notPayOrder();
	}
	
	//根据客户编号查看订单
	@RequestMapping("getOrderDataByCid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000018")
	public @ResponseBody String getOrderDataByCid(HttpServletRequest request){
		return OrderImpl.getOrderDataByCid(request);
	}
	
	//上传订单附件
	@RequestMapping("uploadAttachment.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000018")
	public @ResponseBody String uploadAttachment(@RequestParam(value="attachment1") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
//		FileUpload.uploadCommon(request, file);
		return FileUpload.uploadCommon(request, file);
	}
}
