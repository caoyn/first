package com.icss.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbSysDept;
import com.icss.service.SysDeptService;
import com.icss.service.SysProdBaseService;
import com.icss.util.SysControllerLog;

@Controller("SysDeptController")
@RequestMapping("/dept")
public class SysDeptController {

	@Autowired
	private SysDeptService SysDeptImpl;//部门
	
	@Autowired
	private SysProdBaseService SysProdBaseImpl;//产品
	
	
	//跳转到部門
	@RequestMapping("dept.do")	
	public String dept(){
	//	System.out.println("111111111");
		return "admin/dept";
	}

	
	 //新增一个部门
	@RequestMapping("ADDdept.do")	
	@SysControllerLog(requestoper="ADD",permid="20170329000008")
	public @ResponseBody String ADDdept(@ModelAttribute("adddept") TbSysDept dept,HttpServletRequest request){
		dept.setUserid(request.getParameter("deptUser_data"));
		return SysDeptImpl.addDept(dept)+"";
	}
	
	//根据上级编号查询所属下级
	@RequestMapping("juniorDeptData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String juniorDeptData(HttpServletRequest request){
		String str =JSON.toJSONString(SysDeptImpl.juniorDeptData(request.getParameter("topdeptid")));
		return str;
	}
	
	//查看该部门是否存在
	@RequestMapping("judgeExistDept.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String judgeExistDept(HttpServletRequest request){
		String str = JSON.toJSONString(SysDeptImpl.judgeExistDept(request.getParameter("deptname")));
		return str;
	}
	
	//查看所有部门
	@RequestMapping("getAllDept.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String getAllDept(){
		return JSON.toJSONString(SysDeptImpl.allDeptData());
	}
	
	//修改部门名称
	@RequestMapping("updDeptName.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000008")
	public @ResponseBody String updDeptName(HttpServletRequest request){
		System.out.println("这里修改部门信息");
		return SysDeptImpl.updDeptName(request) + "";
	}
	
	//更改部门状态
	@RequestMapping("updDeptStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000008")
	public @ResponseBody String updDeptStatus(HttpServletRequest request){
		return SysDeptImpl.updDeptStatus(request) + "";
	}
	
	//删除部门
	@RequestMapping("delDetpById.do")
	@SysControllerLog(requestoper="DEL",permid="20170329000008")
	public @ResponseBody String delDetpById(HttpServletRequest request){
		return SysDeptImpl.delDept(request) + "";
	}
	
	//查看部门详情
	@RequestMapping("getDeptDataById.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String getDeptDataById(HttpServletRequest request){
		return JSON.toJSONString(SysDeptImpl.getDeptDataById(request));
	}
	
	//查看可以使用的产品（大产品-包含了子产品）
	@RequestMapping("getAllViableProduct.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String getAllViableProduct(){
		return JSON.toJSONString(SysProdBaseImpl.getAllViableProduct());
	}
	
	//查看某部门的产品权限
	@RequestMapping("getDeptProductPermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String getDeptProductPermission(HttpServletRequest request){
		return JSON.toJSONString(SysProdBaseImpl.getDeptProductPermission(request.getParameter("deptid")));
	}
	
	//新增或者更新部门权限信息
	@RequestMapping("addOrUpdProdPermission.do")
	@SysControllerLog(requestoper="ADD/UPD",permid="20170329000008")
	public @ResponseBody String addOrUpdProdPermission(HttpServletRequest request){
		return SysProdBaseImpl.addOrUpdProdPermission(request) + "";
	}
	
	//查看部门以json形式的所有数据
	@RequestMapping("getAllJsonDeptData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public @ResponseBody String getAllJsonDeptData(){
		return JSON.toJSONString(SysDeptImpl.getAllJsonDeptData());
	}
	//修改部门信息
	@RequestMapping("updDept.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000008")
	public @ResponseBody String updDept(@ModelAttribute("dept") TbSysDept dept,HttpServletRequest request){
		//System.out.println("/*/*/*/*/*/*/*/*/*/");
		if(! "undefined".equalsIgnoreCase(request.getParameter("deptUser_data"))){
			dept.setUserid(request.getParameter("deptUser_data"));
		}
		return "" + SysDeptImpl.updDept(dept);
	}
	
	//生成部门树
	@RequestMapping("loadTree.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000008")
	public void loadTree(HttpServletRequest request) {
		System.out.println(SysDeptImpl.recursiveTree(request));
	}

}

