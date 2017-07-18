package com.icss.dao;

import java.util.List;

import com.icss.bean.TbSysPermission;

public interface TbSysPermissionMapper {
    int deleteByPrimaryKey(String permissionid);

    int insert(TbSysPermission record);

    int insertSelective(TbSysPermission record);

    TbSysPermission selectByPrimaryKey(String permissionid);

    int updateByPrimaryKeySelective(TbSysPermission record);

    int updateByPrimaryKey(TbSysPermission record);
    
    List<TbSysPermission> getAllPermission();
    
    List<TbSysPermission> allRolePermission();
    
    List<TbSysPermission> oneRolePermission(String roleid);
    
    List<TbSysPermission> allOperpermission();
    
    List<TbSysPermission> permissionByRole(String str);
	 
	List<TbSysPermission> operPermission();
	
	List<TbSysPermission> permissionByUser(String str);
	
	List<TbSysPermission> getPermissionDataById(String permissionid);
}