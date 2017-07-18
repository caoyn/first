package com.icss.dao;

import java.util.List;

import com.icss.bean.TbSysApprovedetail;

public interface TbSysApprovedetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysApprovedetail record);

    int insertSelective(TbSysApprovedetail record);

    TbSysApprovedetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysApprovedetail record);

    int updateByPrimaryKey(TbSysApprovedetail record);
    
    List<TbSysApprovedetail> getApproveDetailByAid(String approveid);
    
    int delByApproveId(String approveid);
    
    List<TbSysApprovedetail> getApproveRole(String approveid);
    
    int sortcount(String approveid);
}