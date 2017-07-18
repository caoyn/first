package com.icss.dao;

import java.util.List;

import com.icss.bean.TbSysUser;

public interface TbSysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysUser record);

    int insertSelective(TbSysUser record);

    TbSysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysUser record);

    int updateByPrimaryKey(TbSysUser record);
    
    int existUser(String userid);
    
    List<TbSysUser> selectByloginname(String loginname);
    
    List<TbSysUser> getAllUserData();
    
    List<TbSysUser> getUserByDeptid(String deptid);
    
    List<TbSysUser>  selectAll();
    
    List<TbSysUser> allUserRolePermission();
    
    List<TbSysUser> deptUserData();
    
    List<TbSysUser> allUserRole();
    
    List<TbSysUser> selectByuserid(String userid );
    
    String getDeptidByUserid(String userid);

    List<TbSysUser> getApprveUserinfo(TbSysUser user);
    
    int checkRepect(TbSysUser sysUser);
    
    String getRolenameByUserid(String userid);
    
    int  updPwd(TbSysUser user);
    
    TbSysUser userdata(String userid);
    
    String passtype(String userid);
}