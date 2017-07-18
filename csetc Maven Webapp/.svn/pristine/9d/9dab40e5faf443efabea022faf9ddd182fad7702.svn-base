/**
 * 文件名: PromocodeController.java
 * 描述:促销码表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-22 2017-06-09
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

import com.icss.bean.TbPromocode;
import com.icss.impl.PromocodeImpl;
import com.icss.util.SysControllerLog;

@Controller("PromocodeController")
@RequestMapping("/promocode")
public class PromocodeController {
	
	@Autowired
	private PromocodeImpl PromocodeImpl;
	//根据产品编号和促销码查询促销规则
	@RequestMapping("getPromotionRule.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000011")
	public @ResponseBody String getPromotionRule(HttpServletRequest request){
		return PromocodeImpl.getPromotionRule(request);
	}
	
	//促销码页面跳转
	@RequestMapping("promocode.do")
	public String promocode(){
		return "admin/promocode";
	}
	
	//查看所有的促销码数据
	@RequestMapping("getAllPromocodeData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000011")
	public @ResponseBody String getAllPromocodeData(){
		return PromocodeImpl.getAllPromocodeData();
	} 
	
	//查看某促销码是否存在
	@RequestMapping("existPromocode.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000011")
	public @ResponseBody String existPromocode(HttpServletRequest request){
		return PromocodeImpl.existPromocode(request);
	}
	
	//新增促销码数据
	@RequestMapping("addPromocode.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000011")
	public @ResponseBody String addPromocode(@ModelAttribute("p") TbPromocode promocode,HttpSession session,HttpServletRequest request){
		try {
			promocode.setExpiretime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiretimes")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PromocodeImpl.addPromocode(promocode, session);
	}
	
	//更改一个或者多个促销码状态
	@RequestMapping("changePromocodeStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000011")
	public @ResponseBody String changePromocodeStatus(HttpServletRequest request){
		return PromocodeImpl.changePromocodeStatus(request);
	}
	
}
