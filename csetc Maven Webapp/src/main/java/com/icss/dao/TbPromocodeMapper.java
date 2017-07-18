package com.icss.dao;

import java.util.List;

import com.icss.bean.TbPromocode;

public interface TbPromocodeMapper {
	int deleteByPrimaryKey(String promoid);

    int insert(TbPromocode record);

    int insertSelective(TbPromocode record);

    //根据序号从数据库表格tb_promocode查询数据
    List<TbPromocode> selectByPrimaryKey(String promoid);
    
    //从数据库表格tb_promocode查询所有数据
    public List<TbPromocode> selectAll();

    int updateByPrimaryKeySelective(TbPromocode record);

    int updateByPrimaryKey(TbPromocode record);
    
    String getPromotionRule(TbPromocode promocode);
    
    List<TbPromocode> getAllPromocodeData();

    int existPromocode(String promocode);
    
    int changePromocodeStatus(TbPromocode promocode);
}