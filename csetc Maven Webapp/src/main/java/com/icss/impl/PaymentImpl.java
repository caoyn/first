/**
 * 文件名: PaymentImpl.java
 * 描述:收款的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-07
 */
package com.icss.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.bean.TbOrderPayment;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbOrderPaymentMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.PaymentService;


@Service("PaymentImpl")
public class PaymentImpl implements PaymentService {

	@Autowired 
	private TbOrderPaymentMapper OrderPaymentMapper;
	
	@Autowired 
	private TbSysUserMapper SysUserMapper;
	
	@Autowired 
	private TbSysUserroledetailMapper SysUserroledetailMapper;

	@Override
	public String addPaymentInfo(List<TbOrderPayment> list, HttpSession session) {
		// 新增收款信息
		String userid = (String) session.getAttribute("userid");
		String deptid = SysUserMapper.getDeptidByUserid(userid);
		for (TbOrderPayment tbOrderPayment : list) {
			if(tbOrderPayment.getAmount() == null || tbOrderPayment.getAmount() == 0){
				continue;
			}
			tbOrderPayment.setUserid(userid);
			tbOrderPayment.setUserdept(deptid);
			OrderPaymentMapper.insert(tbOrderPayment);//新增收款
			OrderPaymentMapper.changeOrdersStatus(tbOrderPayment);//更改订单状态
		}
		return "1";
	}

	@Override
	public String addOrderRefund(TbOrderPayment orderPayment,
			HttpSession session) {
		// 新增退款信息
		String userid = (String) session.getAttribute("userid");
		String deptid = SysUserMapper.getDeptidByUserid(userid);
		orderPayment.setUserid(userid);
		orderPayment.setUserdept(deptid);
		OrderPaymentMapper.insert(orderPayment);
		return  OrderPaymentMapper.changeOrdersStatus(orderPayment)+"" ;
	}
	
	//权限操作
	public void operbtn(HttpSession session) {
		// 可操作的按钮
		String userid = (String) session.getAttribute("userid");
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170329000019");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	} 
	
	@Override
	public String getGrossIncome(HttpServletRequest request) {
		// 查看总收入
//		JSONArray jo = new JSONArray();
//		jo.add(OrderPaymentMapper.getGrossIncome(request.getParameter("type")));
		return OrderPaymentMapper.getGrossIncome(request.getParameter("type")) + "";
	}

	@Override
	public String getIncomeByProd(HttpServletRequest request) {
		// 查看总收入按产品分组
		return JSON.toJSONString(OrderPaymentMapper.getIncomeByProd(request.getParameter("type")));
	}

	@Override
	public String orderData() {
		String[] monthAry = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		// 查看订单数据用于生成图
		//1.获得总收入
		List<TbOrderPayment> list1 = OrderPaymentMapper.getMonthlyIncome();
		//2.获得总支出
		List<TbOrderPayment> list2 = OrderPaymentMapper.getMonthlyExpenditure();
		//3.获得返款支出
		List<TbOrderPayment> list3 = OrderPaymentMapper.getMonthlyBack();
		JSONObject jsonObject = new JSONObject();
		for(int i = 0; i < monthAry.length; i++){
			boolean flag = true;
			TbOrderPayment tbOrderPayment = new TbOrderPayment();
			tbOrderPayment.setAmount(0.0f);
			tbOrderPayment.setId(monthAry[i]);
			for (TbOrderPayment tbOrderPayment1 : list1) {
				if(tbOrderPayment1.getId().equals(monthAry[i])){
					flag = false;
					break;
				}
			}
			if(flag){
				list1.add(tbOrderPayment);
			}
			flag = true;
			for (TbOrderPayment tbOrderPayment2 : list2) {
				if(tbOrderPayment2.getId().equals(monthAry[i])){
					flag = false;
					break;
				}
			}
			if(flag){
				list2.add(tbOrderPayment);
			}
			flag = true;
			for (TbOrderPayment tbOrderPayment3 : list3) {
				if(tbOrderPayment3.getId().equals(monthAry[i])){
					flag = false;
					break;
				}
			}
			if(flag){
				list3.add(tbOrderPayment);
			}
		}
		
		Collections.sort(list1);  
		Collections.sort(list2);  
		Collections.sort(list3);  
		
		jsonObject.put("income", list1);
		jsonObject.put("expenditure", list2);
		jsonObject.put("back", list3);
		return JSON.toJSONString(jsonObject,SerializerFeature.DisableCircularReferenceDetect);
	} 

}










