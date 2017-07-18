package com.icss.dao;

import com.icss.bean.TbSysUserpermission;

public interface TbSysUserpermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysUserpermission record);

    int insertSelective(TbSysUserpermission record);

    TbSysUserpermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysUserpermission record);

    int updateByPrimaryKey(TbSysUserpermission record);
}