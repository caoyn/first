package com.icss.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.TbSysLog;
import com.icss.bean.TbSysUser;
import com.icss.bean.TbSysUserupd;
import com.icss.bean.TreeNode;

public interface SysUserService {
		/*int deleteByPrimaryKey(String id);

	    int insert(SysUser record);

	    int insertSelective(SysUser record);

	    SysUser selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(SysUser record);

	    int updateByPrimaryKey(SysUser record);
	    */
		List<TbSysUser> selectByloginname(String loginname);
	    
	    int updateByPrimaryKeySelective(TbSysUser record);
	    
	    int insertSelective(TbSysUser record);
	    
	    List<TbSysUser>  selectAll();
	    
	    TbSysUser selectByPrimaryKey(String id);
	    
	    List<TbSysUser> getAllUserData();
	    
	    List<TbSysUser> getUserByDeptid(String deptid);
	    
	    List<TreeNode> getMenuByUserid(HttpSession session);
	    
	    List<TbSysUser> deptUserData();
	    
	    List<TbSysUser>   selectByuserid(String userid );
	    
	    List<TbSysUser> allUserRole();
	    
	    int checkRepect(HttpServletRequest request);
	    
	    int userUpdate(TbSysUserupd userupd);
	    
	    String resetPwd(HttpServletRequest request);
	    
	    int userchange(TbSysUserupd sysUserupd);
	    
	    String userUpdData();
	    
	    Map<String,Object> getLogData(TbSysLog sysLog);
	    
	    String getRolenameByUserid(String userid);
	    
	    String updPwd(HttpSession session,HttpServletRequest request);

	    String userdata(HttpSession session);
}
