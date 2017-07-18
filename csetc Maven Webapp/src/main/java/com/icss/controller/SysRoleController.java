/**
 * 文件名: SysBasecodeController.java
 * 描述:基础代码表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-22 
 */
package com.icss.controller;



import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbSysRole;
import com.icss.service.SysRoleService;
import com.icss.util.SysControllerLog;

@Controller("SysRoleController")
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private SysRoleService SysRoleImpl;
	
	//查看所有的角色
	@RequestMapping("getAllRoleData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000002")
	public @ResponseBody String getAllRoleData(){
		return JSON.toJSONString(SysRoleImpl.getAllRoleData());
	}
	
	/**
	 * 添加
	 * @param SysRole
	 * @return
	 */
	@RequestMapping("insert.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000002")
	@ResponseBody
	public String insert(@ModelAttribute("r") TbSysRole SysRole){
		SysRoleImpl.insert(SysRole);
		JSONObject  jsonArray=new JSONObject();
		jsonArray.put("status", "success");
		return jsonArray.toString();
	}
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("delete.do")
	@SysControllerLog(requestoper="DEL",permid="20170329000002")
	@ResponseBody
	public String delete(HttpServletRequest request){
		String roleid=request.getParameter("roleid");
		SysRoleImpl.deleteByPrimaryKey(roleid);
		JSONObject  jsonArray=new JSONObject();
		jsonArray.put("status", "success");
		return jsonArray.toString();
	}
	/**
	 * 修改
	 * @param SysRole
	 * @return
	 */
	@RequestMapping("update.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000002")
	@ResponseBody
	public String update(@ModelAttribute("r") TbSysRole SysRole){
		SysRoleImpl.updateByPrimaryKeySelective(SysRole);
		JSONObject  jsonArray=new JSONObject();
		jsonArray.put("status", "success");
		return jsonArray.toString();
	}
	/**
	 *查询单个
	 * @param SysRole
	 * @return
	 */
	@RequestMapping("selecte")
	@SysControllerLog(requestoper="QRY",permid="20170329000002")
	@ResponseBody
	public String selecte(HttpServletRequest request){
		String roleid=request.getParameter("roleid");
		SysRoleImpl.selectByPrimaryKey(roleid);
		JSONObject  jsonArray=new JSONObject();
		jsonArray.put("status", "success");
		return jsonArray.toString();
	}
	
	//权限页面跳转
	@RequestMapping("role.do")
	public String role(){
		return "admin/role";
	}

	//获得所有启用的角色
	@RequestMapping("getRoleBystatus.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000002")
	public @ResponseBody String getRoleBystatus(){
		return SysRoleImpl.getRoleBystatus();
	}

}
