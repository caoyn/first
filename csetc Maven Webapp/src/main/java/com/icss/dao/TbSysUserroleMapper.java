package com.icss.dao;

import com.icss.bean.TbSysUserrole;

public interface TbSysUserroleMapper {
    int deleteByPrimaryKey(String useroleid);

    int insert(TbSysUserrole record);

    int insertSelective(TbSysUserrole record);

    TbSysUserrole selectByPrimaryKey(String useroleid);

    int updateByPrimaryKeySelective(TbSysUserrole record);

    int updateByPrimaryKey(TbSysUserrole record);
    
    String getMaxId();
    
    int deleteUserRole(String userid);
}