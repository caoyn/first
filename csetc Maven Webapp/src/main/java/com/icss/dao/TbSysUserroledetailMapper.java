package com.icss.dao;

import java.util.List;

import com.icss.bean.TbSysUserroledetail;

public interface TbSysUserroledetailMapper {
    int deleteByPrimaryKey(String userroleid);

    int insert(TbSysUserroledetail record);

    int insertSelective(TbSysUserroledetail record);

    TbSysUserroledetail selectByPrimaryKey(String userroleid);

    int updateByPrimaryKeySelective(TbSysUserroledetail record);

    int updateByPrimaryKey(TbSysUserroledetail record);
    
    String getMaxId();
    
    int deleteUserData(String userid);
    
    List<TbSysUserroledetail> userRoleDetailData(TbSysUserroledetail userroledetail);
    
    List<TbSysUserroledetail> selectallsale();
    
    List<TbSysUserroledetail> getApproveRole(String deptids);
    
}