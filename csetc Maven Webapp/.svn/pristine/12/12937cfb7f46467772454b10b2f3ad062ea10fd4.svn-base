package com.icss.dao;

import java.util.List;

import com.icss.bean.TbSysDept;

public interface TbSysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysDept record);

    int insertSelective(TbSysDept record);

    TbSysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysDept record);

    int updateByPrimaryKey(TbSysDept record);
    
    String getMaxId();
    
    String getMaxDeptId(String topdeptid);
    
    List<TbSysDept> allDeptData();
    
    List<TbSysDept> juniorDeptData(String topdeptid);
    
    List<TbSysDept> judgeExistDept(String deptname);
    
    List<TbSysDept> getDeptDataById(String id);
    
    int updDeptStatus(TbSysDept dept);
    
    //根据fordeptid获取节点对象()
    TbSysDept datafordeptid();
    
    //查询cid下的所有子节点
	List<TbSysDept>  datafortopdeptid(String topdeptid);
}