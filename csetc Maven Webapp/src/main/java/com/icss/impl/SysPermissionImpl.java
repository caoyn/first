/**
 * 文件名: SysPermissionImpl.java
 * 描述:权限模块的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-27
 */
package com.icss.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.bean.TbSysPermission;
import com.icss.bean.TbSysUser;
import com.icss.bean.TreeNode;
import com.icss.dao.TbSysPermissionMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.dao.TreeBuilderMapper;
import com.icss.service.SysPermissionService;
import com.icss.util.TreeBuilder;

@Service("SysPermissionImpl")
public  class SysPermissionImpl implements SysPermissionService {
	
	@Autowired
	public TbSysPermissionMapper SysPermissionMapper;
	
	@Autowired 
	public TbSysUserMapper SysUserMapper;
	
	@Autowired 
	public TbSysUserroledetailMapper SysUserroledetailMapper;


	@Autowired
	public TreeBuilderMapper TreeBuilderMapper;
	
	@Override
	public List<TbSysPermission> getAllPermission() {
		// 获得所有的权限
		return SysPermissionMapper.getAllPermission();
	}

	@Override
	public int existUser(String userid) {
		// 查看是否存在该id
		//System.out.println(userid);
		return SysUserMapper.existUser(userid);
	}

	@Override
	public List<TbSysPermission> allRolePermission() {
		// 查看角色和与之对应的权限内容
		System.out.println("-------------------");
		return SysPermissionMapper.allRolePermission();
	}

	@Override
	public String insertUser() {
		// 新增用户信息
		TbSysUser user = new TbSysUser();
		user.setId("20170328000001");
		user.setUserid("E100091661");
		user.setUserpassword("111");
		user.setUsername("陈赛君");
		user.setMobile("13200001111");
		user.setPasstype("1");
		user.setCreatetime(new Date());
		user.setLastlogintime(new Date());
		user.setLogincount(0);
		user.setLoginfailedcount(0);
		user.setStatus(1);
		SysUserMapper.insert(user);
		return user.getUserid();
	}

	@Override
	public List<TbSysUser> allUserRolePermission() {
		// 查看用户角色权限
		return SysUserMapper.allUserRolePermission();
	}

	@Override
	public List<TbSysPermission> oneRolePermission(String roleid) {
		// 某角色的权限
		//1.查看角色对应的权限
		List<TbSysPermission> list1 = SysPermissionMapper.permissionByRole(roleid);
		//2。查看操作
		List<TbSysPermission> list2 = SysPermissionMapper.operPermission();		
		return mergeList(list1,list2);
	}

	@Override
	public List<TbSysPermission> allOperpermission() {
		// 所有权限和与之对应的操作
		return SysPermissionMapper.allOperpermission();
	}

	//合并两个集合,目的是方便权限及操作处理成带复选框的树的数据
	public List<TbSysPermission> mergeList(List<TbSysPermission> list1, List<TbSysPermission> list2){
		for(int i = 0; i<list1.size(); i++){
			TbSysPermission p = list1.get(i);
			for(int j = 0; j < list2.size(); j++){
				TbSysPermission pe = list2.get(j);
				if(pe.getLevel().equals(p.getPermissionid())){
					if(p.getOrdernum() != null && p.getOrdernum().contains(pe.getPermissionid())){
						pe.setOrdernum(pe.getPermissionid());
						//System.out.println(list2.get(j).getOrdernum());
					}
				}
			}
		}
		list1.addAll(list2);
		return list1;
	}

	@Override
	public List<TbSysPermission> oneUserPermission(HttpServletRequest request) {
		// 某用户的权限
		String userid = request.getParameter("userid");
//		String roleid = request.getParameter("roleid");
		//判断该用户是否授权
		List<TbSysPermission> list1 ; //1.查看用户对应的权限
//		if(SysUserroledetailMapper.userPermissionData(userid).size()>0){
			 list1 = SysPermissionMapper.permissionByUser(userid);
//		}else{
//			 list1 = SysPermissionMapper.permissionByRole(roleid);
//		}
		//2。查看操作
		List<TbSysPermission> list2 = SysPermissionMapper.operPermission();		
		return mergeList(list1,list2);
	}

	@Override
	public List<TbSysPermission> getPermissionDataById(
			HttpServletRequest request) {
		// 根据id查看权限数据
		return SysPermissionMapper.getPermissionDataById(request.getParameter("permissionid"));
	}

	@Override
	public int updPermissionStatus(HttpServletRequest request) {
		// 更改权限状态
		String[] perAry = request.getParameter("permissionid").split(",");
		for(int i = 0; i< perAry.length; i++){
			TbSysPermission permission = new TbSysPermission();
			permission.setPermissionid(perAry[i]);
			permission.setStatus(request.getParameter("status"));
			SysPermissionMapper.updateByPrimaryKeySelective(permission);
		}
		return 1;
	}
	
	@Override
	public List<TreeNode> getTreePer(){
		List<TreeNode> list = TreeBuilderMapper.getTreePer();
		return TreeBuilder.bulid(list);
	}


}
