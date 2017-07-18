package com.icss.dao;

import java.util.List;

import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerReservation;

public interface TbCustomerReservationMapper {
    int deleteByPrimaryKey(Integer reservationid);

    int insert(TbCustomerReservation record);

    int insertSelective(TbCustomerReservation record);

    TbCustomerReservation selectByPrimaryKey(Integer reservationid);

    int updateByPrimaryKeySelective(TbCustomerReservation record);

    int updateByPrimaryKey(TbCustomerReservation record);
    
    int getExistReservation(TbCustomerReservation reservation);
    
    List<TbCustomerReservation> getAllCustomerReservationinfo(String str);    
//    List<TbCustomerReservation> getAllCustomerReservationinfo1(String str);
    
    List<TbCustomerReservation> getSecondCustomerReservationinfo(TbCustomer customer);
//    List<TbCustomerReservation> getSecondCustomerReservationinfo1(TbCustomer customer);
    
    List<TbCustomerReservation> getDeptCustomerReservationinfo(TbCustomer customer);
//    List<TbCustomerReservation> getDeptCustomerReservationinfo1(TbCustomer customer);
    
    List<TbCustomerReservation> getCustomerReservationinfo(TbCustomer customer);
//    List<TbCustomerReservation> getCustomerReservationinfo1(TbCustomer customer);
    
    List<TbCustomerReservation> getSaleCustomerReservationinfo(TbCustomer customer);
//    List<TbCustomerReservation> getSaleCustomerReservationinfo1(TbCustomer customer);
    
    List<TbCustomerReservation> getAllCustomerReservationBysome(String str);    
    List<TbCustomerReservation> getAllCustomerReservationBysome1(String str);
    
    List<TbCustomerReservation> getSecondCustomerReservationBysome(TbCustomer customer);
    List<TbCustomerReservation> getSecondCustomerReservationBysome1(TbCustomer customer);
    
    List<TbCustomerReservation> getDeptCustomerReservationBysome(TbCustomer customer);
    List<TbCustomerReservation> getDeptCustomerReservationBysome1(TbCustomer customer);
    
    List<TbCustomerReservation> getCustomerReservationBysome(TbCustomer customer);
    List<TbCustomerReservation> getCustomerReservationBysome1(TbCustomer customer);
    
    int updCustomerReservationStatus(TbCustomerReservation customerReservation);
    
    int updCusReservationStatusByCid(TbCustomerReservation customerReservation);
    
    List<TbCustomerReservation> getReservationByCid(String cid);
    
    
}