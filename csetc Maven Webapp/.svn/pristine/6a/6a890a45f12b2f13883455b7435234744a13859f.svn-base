package com.icss.dao;

import java.util.List;

import com.icss.bean.TbProdDetail;

public interface TbProdDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbProdDetail record);

    int insertSelective(TbProdDetail record);

    TbProdDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbProdDetail record);

    int updateByPrimaryKey(TbProdDetail record);
    
    List<TbProdDetail> getProdDetailById(String id);
    
    int updateByProdid(TbProdDetail prodDetail);
    
    List<TbProdDetail> getAllViableProduct();
    
    List<TbProdDetail> getAllProdData();
    
    List<TbProdDetail> getProdDetailByPid(String pid);
    
    int udlProdDetailByPid(String pid);
    
    TbProdDetail getSubProdBySid(String id);
    
    List<TbProdDetail> selectAllProdsData();
    
}