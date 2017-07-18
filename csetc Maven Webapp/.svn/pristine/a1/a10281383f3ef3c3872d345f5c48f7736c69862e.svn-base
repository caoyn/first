package com.icss.dao;

import java.util.List;

import com.icss.bean.TbApproveMain;
import com.icss.bean.TbSysApprove;

public interface TbSysApproveMapper {
    int deleteByPrimaryKey(String approveid);

    int insert(TbSysApprove record);

    int insertSelective(TbSysApprove record);

    TbSysApprove selectByPrimaryKey(String approveid);

    int updateByPrimaryKeySelective(TbSysApprove record);

    int updateByPrimaryKey(TbSysApprove record);
    
    List<TbSysApprove> getAllApproveData();
    
    List<TbApproveMain> getApproveSelectByType(String type);
}