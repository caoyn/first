package com.icss.dao;

import java.util.List;

import com.icss.bean.TbOrderPayment;

public interface TbOrderPaymentMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderPayment record);

    int insertSelective(TbOrderPayment record);

    TbOrderPayment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbOrderPayment record);

    int updateByPrimaryKey(TbOrderPayment record);
    
    Float getGrossIncome(String type);
    
    List<TbOrderPayment> getIncomeByProd(String type);
    
    List<TbOrderPayment> getMonthlyIncome();
    
    List<TbOrderPayment> getMonthlyExpenditure();
    
    List<TbOrderPayment> getMonthlyBack();
    
    int changeOrdersStatus(TbOrderPayment payment);
}