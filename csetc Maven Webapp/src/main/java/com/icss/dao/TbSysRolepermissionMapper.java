package com.icss.dao;

import com.icss.bean.TbSysRolepermission;

public interface TbSysRolepermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysRolepermission record);

    int insertSelective(TbSysRolepermission record);

    TbSysRolepermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysRolepermission record);

    int updateByPrimaryKey(TbSysRolepermission record);
    
    String getMaxId();
    
    int delOneRolePermission(String roleid);
}