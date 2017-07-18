package com.icss.dao;

import com.icss.bean.TbApproveMain;

public interface TbApproveMainMapper {
    int deleteByPrimaryKey(String approveid);

    int insert(TbApproveMain record);

    int insertSelective(TbApproveMain record);

    TbApproveMain selectByPrimaryKey(String approveid);

    int updateByPrimaryKeySelective(TbApproveMain record);

    int updateByPrimaryKey(TbApproveMain record);
    
    int updcurruserid(TbApproveMain main);
    
}