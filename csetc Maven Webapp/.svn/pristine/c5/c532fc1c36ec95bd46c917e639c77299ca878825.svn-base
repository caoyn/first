package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.bean.TbSysPermission;
import com.icss.bean.TbSysUser;
import com.icss.bean.TreeNode;



public interface SysPermissionService {
	
	 List<TbSysPermission> getAllPermission();
	
	 int existUser(String userid);
	 
	 List<TbSysPermission> allRolePermission();
	 
	 List<TbSysPermission> oneRolePermission(String roleid);
	 
	 String insertUser();
	 
	 List<TbSysUser> allUserRolePermission();
	
	 List<TbSysPermission> allOperpermission();
	 
	 List<TbSysPermission> oneUserPermission(HttpServletRequest request);
	 
	 List<TbSysPermission> getPermissionDataById(HttpServletRequest request);
	 
	 int updPermissionStatus(HttpServletRequest request);

	 List<TreeNode> getTreePer();
}
