/**
 * 文件名: SysRolePermissionImpl.java
 * 描述:角色和功能模块的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-27 
 */
package com.icss.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.bean.TbSysRolepermission;
import com.icss.dao.TbSysRolepermissionMapper;
import com.icss.service.SysRolePermissionService;
import com.icss.util.HandleId;

@Service("SysRolePermissionImpl")
public class SysRolePermissionImpl implements SysRolePermissionService {

	@Autowired
	public TbSysRolepermissionMapper SysRolePermissionMapper;

	//新增角色权限信息
	public int insertRolePermission(String roleid, String permissionid, String operid) { 
		String maxid = SysRolePermissionMapper.getMaxId();
		TbSysRolepermission rolepermission = new TbSysRolepermission(
				HandleId.createid(maxid, "%06d", 8), roleid, permissionid,
				operid);
		return SysRolePermissionMapper.insert(rolepermission); // 新增角色和权限关联信息
	}

	//新增之前的逻辑判断
	@Override
	public int insert(HttpServletRequest request) {
		String roleid = request.getParameter("roleid");
		String rolepermission = request.getParameter("rolepermission");
		// 新增之前删除原先记录
		SysRolePermissionMapper.delOneRolePermission(roleid);

		// 将字符串组成字符串数组
		String[] rpary = rolepermission.split(",");
		String str = "";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < rpary.length - 1; i++) { // 判断字符
			str += rpary[i] + ",";
			if (rpary[i].length() <= 1 && rpary[i + 1].length() == 14) {
				list.add(str);
				str = "";
			}
		}
		str += rpary[rpary.length - 1];// 最后的字符串
		list.add(str);// 将最后的字符加入集合

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String s = it.next();
			String[] sary = s.split(",");
			String id = "";
			String operid = "";
			for (int i = 0; i < sary.length - 1; i++) {
				if (sary[i].length() == 14 && sary[i + 1].length() == 14) {
					id = sary[i];
					insertRolePermission(roleid, id, "-"); // 新增角色和权限关联信息
				} else if (sary[i].length() == 14 && sary[i + 1].length() <= 2) {
					id = sary[i];
				} else {
					operid += sary[i] + "|";
				}
			}
			if (sary[sary.length - 2].length() == 14 && sary[sary.length - 1].length() == 14) {
				insertRolePermission(roleid, sary[sary.length - 1], "-"); // 新增角色和权限关联信息
			} else {
				insertRolePermission(roleid, id, operid + sary[sary.length - 1]); // 新增角色和权限关联信息
			}
		}
		return 0;
	}

	@Override
	public int updRolePermission(HttpServletRequest request) {
		// 更新某角色的权限
		String roleid = request.getParameter("roleid");
		// 1.删除该角色的所有页面的权限
		SysRolePermissionMapper.delOneRolePermission(roleid);
		// 2.新增该角色与之对应的权限
		return insert(request);
	}

}
