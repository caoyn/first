/**
 * 文件名: SysBasecodeController.java
 * 描述:基础代码表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-22 
 */
package com.icss.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.bean.TbSysApprove;
import com.icss.bean.TbSysApprovedetail;
import com.icss.service.SysApproveService;
import com.icss.util.SysControllerLog;

@Controller("SysApproveController")
@RequestMapping("/approve")
public class SysApproveController {

	@Autowired
	private SysApproveService SysApproveImpl;
	
	//新增审批数据
	@RequestMapping("addApproveData.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000007")
	public @ResponseBody String addApproveData(@ModelAttribute("addApproveForm") TbSysApprove approve,HttpSession session){
		//
		approve.setUserid((String) session.getAttribute("userid"));//设置创建人
		return SysApproveImpl.addApproveData(approve) +"";
	}
	
	//获得审批数据
	@RequestMapping("getAllApproveData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000007")
	public @ResponseBody String getAllApproveData(){
		return JSON.toJSONString(SysApproveImpl.getAllApproveData(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	//新增审批流详情数据
	@RequestMapping("addApproveDetailData.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000007")
	public @ResponseBody String addApproveDetailData(HttpServletRequest request){
		return SysApproveImpl.addApproveDetailData(request) + "";
	}
	
	//查看是否有该审批流详情
	@SysControllerLog(requestoper="QRY",permid="20170329000007")
	@RequestMapping("getApproveDetailByAid.do")
	public @ResponseBody String getApproveDetailByAid(HttpServletRequest request){
		return JSON.toJSONString(SysApproveImpl.getApproveDetailByAid(request.getParameter("approveid")));
	}
	
	//删除审批
	@RequestMapping("delApproveDataById.do")
	@SysControllerLog(requestoper="DEL",permid="20170329000007")
	public @ResponseBody String delApproveDataById(HttpServletRequest request){
		return SysApproveImpl.delApproveById(request.getParameter("approveid")) + "";
	}
	
	//删除审批子节点
	@RequestMapping("delApproveSubNode.do")
	@SysControllerLog(requestoper="DEL",permid="20170329000007")
	public @ResponseBody String delApproveSubNode(HttpServletRequest request){
		return SysApproveImpl.delApproveSubNode(request.getParameter("id")) + "";
	}
	
	//编辑审批流
	@RequestMapping("updApproveData.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000007")
	public @ResponseBody String updApproveData(@ModelAttribute("a") TbSysApprove approve){
		return SysApproveImpl.updApproveData(approve) + "";
	}
	//编辑审批流节点数据
	@RequestMapping("updApproveSubNodeData.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000007")
	public @ResponseBody String updApproveSubNodeData(@ModelAttribute("ad") TbSysApprovedetail approvedetail){
		return SysApproveImpl.updApproveSubNodeData(approvedetail) + "";
	}
	
	//查看一个审批流数据
	@RequestMapping("getOneAproveData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000007")
	public @ResponseBody String getOneAproveData(HttpServletRequest request){
		return JSON.toJSONString(SysApproveImpl.getOneAproveData(request.getParameter("approveid")));
	}
	
	//查看一个审批节点数据
	@RequestMapping("getOneSubNodeData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000007")
	public @ResponseBody String getOneSubNodeData(HttpServletRequest request){
		return JSON.toJSONString(SysApproveImpl.getOneSubNodeData(request.getParameter("id")));
	}
	
	//审批流程页面跳转
	@RequestMapping("approve.do")
	public String approve(){
		return "admin/approve";
	}
	
	//根据审批的类型获得审批数据
	@RequestMapping("getApproveSelectByType.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000007")
	public @ResponseBody String getApproveSelectByType(HttpServletRequest request){
		return SysApproveImpl.getApproveSelectByType(request);
	}
}
