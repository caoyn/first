package com.icss.dao;

import java.util.List;

import com.icss.bean.TbOrderOrderdetail;

public interface TbOrderOrderdetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderOrderdetail record);

    int insertSelective(TbOrderOrderdetail record);

    TbOrderOrderdetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbOrderOrderdetail record);

    int updateByPrimaryKey(TbOrderOrderdetail record);
    
    List<TbOrderOrderdetail> getOrderDetailData(String orderid);
    
    int delOrderDetailByOid(String orderid);
    
    List<TbOrderOrderdetail> getNotPayOrderDataByOid(String orderid);
}