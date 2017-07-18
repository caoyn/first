package com.icss.dao;

import java.util.List;

import com.icss.bean.TbCustomerRemark;

public interface TbCustomerRemarkMapper {
    int deleteByPrimaryKey(Integer remarkid);

    int insert(TbCustomerRemark record);

    int insertSelective(TbCustomerRemark record);

    TbCustomerRemark selectByPrimaryKey(Integer remarkid);

    int updateByPrimaryKeySelective(TbCustomerRemark record);

    int updateByPrimaryKey(TbCustomerRemark record);
    
    List<TbCustomerRemark> qryCustomerRemrk(String customerid);
    
    int checkRemark(TbCustomerRemark remark);
}