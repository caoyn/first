package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.bean.TbSysDept;
import com.icss.bean.TreeNode;

public interface SysDeptService {

	//List<SysUser> selectByloginname(String loginname);

	int addDept(TbSysDept dept);
	
	List<TbSysDept> juniorDeptData(String topdeptid);
	
	List<TbSysDept> judgeExistDept(String deptname);
	
	List<TbSysDept> allDeptData();
	
	int updDeptName(HttpServletRequest request);
	
	int updDeptStatus(HttpServletRequest request);
	
	int delDept(HttpServletRequest request);
	
	List<TbSysDept> getDeptDataById(HttpServletRequest request);
	
	List<TreeNode> getAllJsonDeptData();
	
	int updDept(TbSysDept dept);
	
	TbSysDept recursiveTree(HttpServletRequest request);

  }
