package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.bean.TbSysUserroledetail;


public interface SysUserRoleDetailService {
	
	int insert(HttpServletRequest request);
	
	int addUserRolePermission(HttpServletRequest request);
	
	List<TbSysUserroledetail> selectallsale();
 }
