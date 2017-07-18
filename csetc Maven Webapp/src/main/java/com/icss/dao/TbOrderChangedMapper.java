package com.icss.dao;

import com.icss.bean.TbOrderChanged;

public interface TbOrderChangedMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderChanged record);

    int insertSelective(TbOrderChanged record);

    TbOrderChanged selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbOrderChanged record);

    int updateByPrimaryKey(TbOrderChanged record);
    
    int updChangeStatus(String orderid);
}