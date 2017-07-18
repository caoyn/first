package com.icss.dao;

import java.util.List;

import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerAssign;
import com.icss.bean.TbCustomerReservation;

public interface TbCustomerAssignMapper {
    int deleteByPrimaryKey(Integer assignid);

    int insert(TbCustomerAssign record);

    int insertSelective(TbCustomerAssign record);

    TbCustomerAssign selectByPrimaryKey(Integer assignid);

    int updateByPrimaryKeySelective(TbCustomerAssign record);

    int updateByPrimaryKey(TbCustomerAssign record);
    
    List<TbCustomerAssign> getAllCustomerAssigninfo(String str);    
    
    List<TbCustomerAssign> getSecondCustomerAssigninfo(TbCustomer customer);
    
    List<TbCustomerAssign> getDeptCustomerAssigninfo(TbCustomer customer);
    
    List<TbCustomerAssign> getCustomerAssigninfo(TbCustomer customer);
    
    List<TbCustomerAssign> getMyCustomerAssigninfo(TbCustomer customer);
    
    int updCustomerAssignStatus(TbCustomerAssign assign);
    
    List<TbCustomerReservation> getMyCustomerByRid(TbCustomerAssign assign);
    
    List<TbCustomerAssign> getAssignByCid(String cid); 
    
    List<TbCustomerAssign> getCustomerAssignData(TbCustomer customer);
    
    TbCustomerAssign visitTMK(String eid);
    
    TbCustomerAssign visitedTMK(String eid);
    
    TbCustomerAssign callbackTMK(String eid);
    
    TbCustomerAssign callbackedTMK(String eid);
        TbCustomerAssign paymentTMK(String eid);
    
    TbCustomerAssign refundTMK(String eid);
    
    TbCustomerAssign paymentCC(String eid);
    
    TbCustomerAssign refundCC(String eid);
    
    TbCustomerAssign reservationTMK(String eid);
    
    TbCustomerAssign assignTMK(String eid);
    
    TbCustomerAssign assignfollowTMK(String eid);
    
    List<TbCustomerAssign> teamData(String deptid);
    
    
}