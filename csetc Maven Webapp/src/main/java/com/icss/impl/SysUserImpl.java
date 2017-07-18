package com.icss.impl;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbSysLog;
import com.icss.bean.TbSysUser;
import com.icss.bean.TbSysUserupd;
import com.icss.bean.TreeNode;
import com.icss.dao.TbSysLogMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserupdMapper;
import com.icss.dao.TreeBuilderMapper;
import com.icss.service.SysUserService;
import com.icss.util.GetMd5;
import com.icss.util.HandleId;
import com.icss.util.TreeBuilder;

@Service("SysUserImpl")
public  class SysUserImpl implements SysUserService {
	
	@Autowired
	public TbSysUserMapper SysUserMapper;
	
	@Autowired
	public TbSysUserupdMapper SysUserupdMapper;
	
	@Autowired
	public TreeBuilderMapper TreeBuilderMapper;
	
	@Autowired
	public TbSysLogMapper SysLogMapper;

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return SysUserMapper.deleteByPrimaryKey(id);
	}

	public int insertsui(TbSysUser record) {
		// TODO Auto-generated method stub
		return SysUserMapper.insert(record);
	}

	public int insertSelective(TbSysUser record) {
		// TODO Auto-generated method stub
		return SysUserMapper.insertSelective(record);
	}

	public TbSysUser selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return SysUserMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TbSysUser record) {
		// TODO Auto-generated method stub
		return SysUserMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TbSysUser record) {
		// TODO Auto-generated method stub
		return SysUserMapper.updateByPrimaryKey(record);
	}

	public List<TbSysUser> selectByloginname(String loginname) {
		// TODO Auto-generated method stub
		return SysUserMapper.selectByloginname(loginname);
	}
	
	//获得所有的员工信息
	@Override
	public List<TbSysUser> getAllUserData(){
		return SysUserMapper.getAllUserData();
	}

	@Override
	public List<TbSysUser> getUserByDeptid(String deptid) {
		// 根据部门编号查看是否有在该部门的员工
		return SysUserMapper.getUserByDeptid(deptid);
	}

	@Override
	public List<TbSysUser> selectAll() {
		// TODO Auto-generated method stub
		return SysUserMapper.selectAll();
	}

	@Override
	public List<TreeNode> getMenuByUserid(HttpSession session) {
		// TODO Auto-generated method stub
		String userid = (String) session.getAttribute("userid");
		//userid = "E000700301";
		List<TreeNode> list = TreeBuilderMapper.getMenuByUserid(userid);
		//获得密码状态
		session.setAttribute("passtype", SysUserMapper.passtype(userid));
		return TreeBuilder.bulid(list);
	}

	@Override
	public List<TbSysUser> deptUserData() {
		// 获得角色下拉框
		return SysUserMapper.deptUserData();
	}

	@Override
	public List<TbSysUser> allUserRole() {
		// TODO Auto-generated method stub

		return SysUserMapper.allUserRole();
	}

	@Override
	public List<TbSysUser> selectByuserid(String userid) {
		// TODO Auto-generated method stub

		return SysUserMapper.selectByuserid(userid);
	}

	@Override
	public int checkRepect(HttpServletRequest request) {
		// 检查是否有重复数据
		TbSysUser user = new TbSysUser();
		int type = Integer.parseInt(request.getParameter("type"));
		if(request.getParameter("data").length()>0 || request.getParameter("data") != null){
			System.out.println(request.getParameter("data"));
			switch(type){
				case 1:user.setLoginname(request.getParameter("data")); break;
				case 2:user.setUsername(request.getParameter("data")); break;
				case 3:user.setUserid(request.getParameter("data")); break;
			}
			return SysUserMapper.checkRepect(user);
		}else{
			return 0;
		}
		
		
	}

	@Override
	public int userUpdate(TbSysUserupd userupd) {
		// 新增员工异动数据
		return SysUserupdMapper.insert(userupd);
	}

	@Override
	public String resetPwd(HttpServletRequest request) {
		// 重置密码
		TbSysUser user = new TbSysUser();
		//生成随机密码
		String pwd = GetMd5.defultPwd();
		//密码加密
		user.setId(request.getParameter("id"));
		user.setUserpassword(GetMd5.md5(pwd));
		user.setPasstype("1");//临时密码
		user.setStatus(1);//启用用户
		user.setLoginfailedcount(0);//登录失败次数变成0
		user.setPassexpire(HandleId.getdate(90));
		//更改数据
		SysUserMapper.updateByPrimaryKeySelective(user);
		return pwd;
	}

	@Override
	public int userchange(TbSysUserupd sysUserupd) {
		// 新增员工异动信息
		return SysUserupdMapper.insert(sysUserupd);
	}

	@Override
	public String userUpdData() {
		// 查看员工异动信息
		return JSON.toJSONString(SysUserupdMapper.userUpdData());
	}

	/*@Override
	public String getLogData() {
		// 查看系统日志信息
		return JSON.toJSONString(SysLogMapper.getLogData());
	}*/
	@Override
	public Map<String,Object> getLogData(TbSysLog log) {
		// 查看系统日志信息(服务端分页)
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", SysLogMapper.getLogDataCount());
		result.put("rows", SysLogMapper.getLogData(log));
		return result;
	}

	@Override
	public String getRolenameByUserid(String userid) {
		// 根据用户获得角色名称
		return SysUserMapper.getRolenameByUserid(userid);
	}

	@Override
	public String updPwd(HttpSession session, HttpServletRequest request) {
		// 修改自己的密码
		TbSysUser user = new TbSysUser();
		user.setUserid((String) session.getAttribute("userid"));
		user.setUserpassword(GetMd5.md5(request.getParameter("userpassword")));
		user.setCardid(GetMd5.md5(request.getParameter("oldpwd")));
		return SysUserMapper.updPwd(user) + "";
	}

	@Override
	public String userdata(HttpSession session) {
		// 查看自己的资料
		return JSON.toJSONString(SysUserMapper.userdata((String) session.getAttribute("userid")));
	} 


	/*@Override
	public List<TreeNode> getMenuByUserid(HttpServletRequest request) {
		// 根据用户编号查看左侧菜单导航
		String userid = request.getParameter("userid");
		userid = "E000700301";
		List<TreeNode> list = TreeBuilderMapper.getMenuByUserid(userid);
		return TreeBuilder.bulid(list);
	}*/
	
	
}
