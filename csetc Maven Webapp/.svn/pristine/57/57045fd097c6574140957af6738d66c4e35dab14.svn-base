/**
 * 文件名: SysPermissionController.java
 * 描述:权限管理管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-27
 */
package com.icss.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.service.SysPermissionService;
import com.icss.service.SysRolePermissionService;
import com.icss.service.SysUserRoleDetailService;
import com.icss.util.SysControllerLog;

@Controller("SysPermissionController")
@RequestMapping("/permission")
public class SysPermissionController {

	@Autowired
	private SysPermissionService SysPermissionImpl;
	
	@Autowired
	private SysRolePermissionService SysRolePermissionImpl;
	
	@Autowired
	private SysUserRoleDetailService  SysUserRoleDetailImpl;
	
	//查看所有权限信息
	@RequestMapping("getAllPermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String getAllPermission(){
		return JSON.toJSONString(SysPermissionImpl.getAllPermission());
	}
	
	//新增角色与模块关联的数据
	@RequestMapping("addRolePermission.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000003")
	public @ResponseBody String addRolePermission(HttpServletRequest request){
		return SysRolePermissionImpl.insert(request) + "";
	}
	
	//新增用户角色表
	@RequestMapping("addUserRole.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000003")
	public String addUserRole(HttpServletRequest request){	
		//新建用户
		String userid = SysPermissionImpl.insertUser();
		
		String[] strary = request.getParameterValues("operpermission");
		String oper = "";
		for(int i = 0; i < strary.length; i++ ){
			oper += strary[i] + ",";
		}
		return "redirect:/permission/addUserRoledetail.do?roleid=" + request.getParameter("roleid") + "&userid=" + userid + "&oper=" + oper;
	}
	
	//新增用户与角色关联的详情
	@RequestMapping("addUserRoledetail.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000003")
	public @ResponseBody String a(HttpServletRequest request){
		return SysUserRoleDetailImpl.insert(request) + ""; 
	}
	
	//查看是否存在该员工编号
	@RequestMapping("checkUserid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String checkUserid(HttpServletRequest request){
		return SysPermissionImpl.existUser(request.getParameter("userid")) + "";
	}
	
	//查看所有角色和与之对应的权限
	@RequestMapping("allRolePermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String allRolePermission(){
		return JSON.toJSONString(SysPermissionImpl.allRolePermission(),SerializerFeature.WriteMapNullValue);
	}
	
	//查看所有用户和对应的角色和权限
	@RequestMapping("allUserRolePermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String allUserRolePermission(){
		return JSON.toJSONString(SysPermissionImpl.allUserRolePermission(),SerializerFeature.WriteMapNullValue);
	}
	//查看某角色和对应的角色和权限
	@RequestMapping("oneRolePermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String oneRolePermission(HttpServletRequest request){		
		return JSON.toJSONString(SysPermissionImpl.oneRolePermission(request.getParameter("roleid")),SerializerFeature.WriteMapNullValue);
	}
	//更新某角色对应的角色和权限
	@RequestMapping("updRolePermission.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	public @ResponseBody String updRolePermission(HttpServletRequest request){	
		return SysRolePermissionImpl.updRolePermission(request) + "";
	}
	
	//查看所有权限和与之对应的操作
	@RequestMapping("allOperpermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String allOperpermission(){
		return JSON.toJSONString(SysPermissionImpl.allOperpermission());
	}
	
	//新增一个用户角色权限数据
	@RequestMapping("addUserRolePermission.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000003")
	public @ResponseBody String addUserRolePermission(HttpServletRequest request){
		return SysUserRoleDetailImpl.addUserRolePermission(request) + "";
	}
	
	//查看某用户对应的角色和权限
	@RequestMapping("oneUserRolePermission.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String oneUserRolePermission(HttpServletRequest request){		
		return JSON.toJSONString(SysPermissionImpl.oneUserPermission(request),SerializerFeature.WriteMapNullValue);
	}
	
	//根据id查看权限数据
	@RequestMapping("getPermissionDataById.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String getPermissionDataById(HttpServletRequest request){		
		return JSONArray.toJSONString(SysPermissionImpl.getPermissionDataById(request),SerializerFeature.WriteMapNullValue);
	}
	
	//更改权限状态
	@RequestMapping("updPermissionStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	public @ResponseBody String updPermissionStatus(HttpServletRequest request){
		return SysPermissionImpl.updPermissionStatus(request) + "";
	}
	
	/**
	 * 查询全部为电话销售的员工
	 */
	@RequestMapping("allsale.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	@ResponseBody
	public String  allsale(){
		return JSON.toJSONString(SysUserRoleDetailImpl.selectallsale());
	}

}
