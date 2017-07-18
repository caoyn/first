/**
 * 文件名: SysDeptImpl.java
 * 描述:部门接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-21 
 */
package com.icss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbSysRole;
import com.icss.dao.TbSysRoleMapper;
import com.icss.service.SysRoleService;

@Service("SysRoleImpl")
public  class SysRoleImpl implements SysRoleService {
	
	@Autowired
	public TbSysRoleMapper SysRoleMapper;

	@Override
	public List<TbSysRole> getAllRoleData() {
		// TODO Auto-generated method stub
		return SysRoleMapper.getAllRoleData();
	}
	
	@Override
	public int deleteByPrimaryKey(String roleid) {
		// TODO Auto-generated method stub
		return SysRoleMapper.deleteByPrimaryKey(roleid);
	}

	@Override
	public int insert(TbSysRole record) {
		// TODO Auto-generated method stub
		return SysRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(TbSysRole record) {
		// TODO Auto-generated method stub
		return SysRoleMapper.insertSelective(record);
	}

	@Override
	public TbSysRole selectByPrimaryKey(String roleid) {
		// TODO Auto-generated method stub
		return SysRoleMapper.selectByPrimaryKey(roleid);
	}

	@Override
	public int updateByPrimaryKeySelective(TbSysRole record) {
		// TODO Auto-generated method stub
		return SysRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TbSysRole record) {
		// TODO Auto-generated method stub
		return SysRoleMapper.updateByPrimaryKey(record);
	}

	@Override
	public String getRoleBystatus() {
		// 获得所有启用的角色
		return JSON.toJSONString(SysRoleMapper.getRoleBystatus());
	}

}
