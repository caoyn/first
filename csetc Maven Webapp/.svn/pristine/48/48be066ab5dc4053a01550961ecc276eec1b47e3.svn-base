package com.icss.dao;

import java.util.List;

import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerAssign;

public interface TbCustomerMapper {
    int deleteByPrimaryKey(TbCustomer key);

    int insert(TbCustomer record);

    int insertSelective(TbCustomer record);

    TbCustomer selectByPrimaryKey(TbCustomer key);

    int updateByPrimaryKeySelective(TbCustomer record);

    int updateByPrimaryKey(TbCustomer record);
    
    List<TbCustomer> checkPresenceExists(String telephone);
    
    List<String> repeatdata(List<String> list);
    
    List<TbCustomer> getAllCustomerinfo(String id);
    
    List<TbCustomer> getSecondCustomerinfo(TbCustomer customer);
    
    List<TbCustomer> getDeptCustomerinfo(TbCustomer customer);
    
    List<TbCustomer> getCustomerinfo(TbCustomer customer);
    
    List<TbCustomer> selectall();
    
    int updCustomerStatus(TbCustomerAssign assign);
    
    List<TbCustomer> condition(TbCustomer customer);
    
    List<TbCustomer> sourcecount(String eid);
    
    List<TbCustomer> sexcount(String eid);
    
    List<TbCustomer> coursecount(String eid);
    
    List<TbCustomer> usercount(String eid);
    
    List<TbCustomer> datamate(String eid);
    
    int resumeUrl(TbCustomer customer);
    
    List<TbCustomer> teamData(String deptid);
    
    
}