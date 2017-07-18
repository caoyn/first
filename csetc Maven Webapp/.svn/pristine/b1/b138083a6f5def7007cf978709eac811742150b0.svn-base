package com.icss.dao;

import java.util.List;

import com.icss.bean.TbPromocodeUse;

public interface TbPromocodeUseMapper {
	int deleteByPrimaryKey(String promuseid);

    int insert(TbPromocodeUse record);

    int insertSelective(TbPromocodeUse record);

    //根据序列号对数据库表tb_promocode_use进行查询
    List<TbPromocodeUse> selectByPrimaryKey(String promuseid);
    //查询数据库tb_promocode_use所有数据
    List<TbPromocodeUse> selectAll();

    int updateByPrimaryKeySelective(TbPromocodeUse record);

    int updateByPrimaryKey(TbPromocodeUse record);
}