/**
 * 文件名: SysProdBaseController.java
 * 描述:产品基础表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-10
 */
package com.icss.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.bean.TbProdBase;
import com.icss.bean.TbProdDetail;
import com.icss.service.SysProdBaseService;
import com.icss.util.SysControllerLog;

@Controller("SysProdBaseController")
@RequestMapping("/prodbase")
public class SysProdBaseController {

	@Autowired
	private SysProdBaseService SysProdBaseImpl;
	
	//新增一条产品基础信息
	@RequestMapping(value="addProductBaseInfo.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@SysControllerLog(requestoper="ADD",permid="20170329000006")
	public @ResponseBody String addProductBaseInfo(HttpServletRequest request){
		//System.out.println("************************************");
		//System.out.println(request.getParameter("products"));
		List<TbProdDetail> list = JSON.parseArray(request.getParameter("products"), TbProdDetail.class);  
		return SysProdBaseImpl.addProductBaseInfo(request,list) + "";
	}
	
	//查看所有的产品信息
	@RequestMapping("getAllProductData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getAllProductData(){
		return JSON.toJSONString(SysProdBaseImpl.getAllProductData(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	//更改某产品数据
	@RequestMapping("updProdBase.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000006")
	public @ResponseBody String updProdBase(@ModelAttribute("producDetailtUpd") TbProdDetail prodDetail,@ModelAttribute("a") TbProdBase prodBase){
		return SysProdBaseImpl.updProdBase(prodDetail,prodBase) + "";
				
	}
	
	//删除某产品
	@RequestMapping("delProdBase.do")
	@SysControllerLog(requestoper="DEL",permid="20170329000006")
	public @ResponseBody String delProdBase(HttpServletRequest request){
		return SysProdBaseImpl.delProdBase(request) + "";
		
	}
	
	//查看是否存在该产品或者编号
	@RequestMapping("judgeExistProduct.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String judgeExistProduct(HttpServletRequest request){
		return JSON.toJSONString(SysProdBaseImpl.judgeExistProduct(request));
	}
	
	//根据产品编号获得子产品信息
	@RequestMapping("getProductByProductid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getProductByProductid(HttpServletRequest request){
		return JSON.toJSONString(SysProdBaseImpl.getProductByProductid(request));
	}
	
	//根据子产品编号获得产品信息
	@RequestMapping("getProductBySubid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getProductBySubid(HttpServletRequest request){
		return JSON.toJSONString(SysProdBaseImpl.getProductBySubid(request.getParameter("subprodid")),SerializerFeature.WriteDateUseDateFormat);
	}
	
	//产品数据的页面跳转
	@RequestMapping("prodbase.do")
	public String prodbase(){
		return "admin/product";
	}
	
	//子产品的数据
	@RequestMapping("proddetail.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String proddetail(){
		return JSONArray.toJSONString(SysProdBaseImpl.getAllProdData());
	}

	//根据产品编号查看子产品
	@RequestMapping("getProdDetailById.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getProdDetailById(HttpServletRequest request){
		return JSONArray.toJSONString(SysProdBaseImpl.getProdDetailById(request));
	}
	
	//新增子产品
	@RequestMapping("addSubProduct.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000006")
	public @ResponseBody String addSubProduct(@ModelAttribute("sub") TbProdDetail prodDetail){
		return SysProdBaseImpl.addSubProduct(prodDetail)+"";
	} 
	
	//查看某产品数据
	@RequestMapping("getProdByPid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getProdByPid(HttpServletRequest request){
		return JSON.toJSONString(SysProdBaseImpl.getProdByPid(request));
	}
	
	//修改某产品数据
	@RequestMapping("updProdByPid.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000006")
	public @ResponseBody String updProdByPid(@ModelAttribute("a") TbProdBase prodBase){
		return "" + SysProdBaseImpl.updProdByPid(prodBase); 
	}
	
	//查看某子产品数据
	@RequestMapping("getSubProdBySid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getSubProdBySid(HttpServletRequest request){
		return JSON.toJSONString(SysProdBaseImpl.getSubProdBySid(request));
	}
	
	//修改某子产品数据
	@RequestMapping("updSubProdBySid.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000006")
	public @ResponseBody String updSubProdBySid(@ModelAttribute("b") TbProdDetail prodDetail){
		return "" + SysProdBaseImpl.updateByProdid(prodDetail);
	}
	
	//可使用的产品数据
	@RequestMapping("getAllEnableProd.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String getAllEnableProd(){
		return JSON.toJSONString(SysProdBaseImpl.getAllEnableProd());
	}
	
	//产品详情（是否允许折扣）
	@RequestMapping("selectAllProdsData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000006")
	public @ResponseBody String selectAllProdsData(){
		return SysProdBaseImpl.selectAllProdsData();
	}
}
