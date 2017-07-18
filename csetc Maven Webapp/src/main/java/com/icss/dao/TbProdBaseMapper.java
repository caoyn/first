package com.icss.dao;

import java.util.List;

import com.icss.bean.TbProdBase;

public interface TbProdBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbProdBase record);

    int insertSelective(TbProdBase record);

    TbProdBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbProdBase record);

    int updateByPrimaryKey(TbProdBase record);
    
    List<TbProdBase> getAllProductData();
    
    List<TbProdBase> judgeExistProduct(String str);
    
    List<TbProdBase> getProductByProductid(String str);
    
    int updateByProdid(TbProdBase prodBase);
    
    int delProdByPid(String prodid);
    
    TbProdBase getProductBySubid(String subid);
    
    String tb_prod_base_id();
    
    List<TbProdBase> getAllProdData();

    TbProdBase getProdByPid(String prodid);
    
    int updProdByPid(TbProdBase prodBase);
    
    List<TbProdBase> getAllEnableProd();

}
