package com.icss.dao;

import com.icss.bean.TbSysOperpermission;

public interface TbSysOperpermissionMapper {
    int deleteByPrimaryKey(String permissionid);

    int insert(TbSysOperpermission record);

    int insertSelective(TbSysOperpermission record);

    TbSysOperpermission selectByPrimaryKey(String permissionid);

    int updateByPrimaryKeySelective(TbSysOperpermission record);

    int updateByPrimaryKey(TbSysOperpermission record);
}