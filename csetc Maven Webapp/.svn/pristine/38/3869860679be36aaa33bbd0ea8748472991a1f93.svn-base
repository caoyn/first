package com.icss.dao;

import java.util.List;

import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerCallback;

public interface TbCustomerCallbackMapper {
    int deleteByPrimaryKey(Integer callbackid);

    int insert(TbCustomerCallback record);

    int insertSelective(TbCustomerCallback record);

    TbCustomerCallback selectByPrimaryKey(Integer callbackid);

    int updateByPrimaryKeySelective(TbCustomerCallback record);

    int updateByPrimaryKey(TbCustomerCallback record);
    
    TbCustomerCallback getCallbackById(String customerid);
    
    TbCustomerCallback getCallbackBySome(TbCustomerCallback callback);
    
    List<TbCustomerCallback> getAllCallbackinfo(String str);    
    
    List<TbCustomerCallback> getSecondCallbackinfo(TbCustomer customer);
    
    List<TbCustomerCallback> getDeptCallbackinfo(TbCustomer customer);
    
    List<TbCustomerCallback> getCallbackinfo(TbCustomer customer);
    
    List<TbCustomerCallback> getMyCallbackinfo(TbCustomer customer);
    
    List<TbCustomerCallback> getCallbackByCid(String cid);
    
    List<TbCustomerCallback> selectCallbackData(TbCustomerCallback callback);
    
}