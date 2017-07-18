package com.icss.dao;

import java.util.List;

import com.icss.bean.TbOrderRefund;

public interface TbOrderRefundMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderRefund record);

    int insertSelective(TbOrderRefund record);

    TbOrderRefund selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbOrderRefund record);

    int updateByPrimaryKey(TbOrderRefund record);
    
    List<TbOrderRefund> getRefundData(String userid);
    
    int updOrderStatus(TbOrderRefund refund);
    
    List<TbOrderRefund> allRefundData();
}