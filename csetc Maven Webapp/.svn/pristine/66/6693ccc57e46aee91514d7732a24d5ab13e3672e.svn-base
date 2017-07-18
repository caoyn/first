/**
 * 文件名: SysDeptImpl.java
 * 描述:部门接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-21 
 */
package com.icss.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.bean.TbSysUserrole;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbSysUserroleMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.SysUserRoleDetailService;
import com.icss.util.HandleId;

@Service("SysUserRoleDetailImpl")
public class SysUserRoleDetailImpl implements SysUserRoleDetailService {
	
	@Autowired
	public TbSysUserroledetailMapper SysUserRoleDetailMapper;
	
	@Autowired
	public TbSysUserroleMapper SysUserroleMapper;

	@Override
	public int insert(HttpServletRequest request) {
		//新增用户角色
		/*String maxurid = SysUserroleMapper.getMaxId();
		System.out.println(maxurid+"------------------------------------");
		TbSysUserrole userrole = new TbSysUserrole(HandleId.createid(maxurid, "%06d" ,8),request.getParameter("userid"),request.getParameter("roleid"));		
		SysUserroleMapper.insert(userrole);*/		
		
		// 新增用户角色详情(可能一次要增加多个)
		String str = request.getParameter("oper");
		//System.out.println(str);
		str.substring(0, str.length()-2);
		String[] strary = str.split(",");
		if(strary != null){
			for(int i = 0; i < strary.length; i++){
			//	String maxid = SysUserRoleDetailMapper.getMaxId();
			//	TbSysUserroledetail userRoleDetail = new TbSysUserroledetail(HandleId.createid(maxid, "%06d" ,8),request.getParameter("userid"),strary[i]);
				//SysUserRoleDetailMapper.insert(userRoleDetail);//此处新增用户角色详情			
			}
		}else{
			return -1;
		}
		return 0;
	}

	@Override
	public int addUserRolePermission(HttpServletRequest request) {
		// 新增用户角色操作详情
		//1.获得用户编号
		String userid = request.getParameter("userid");
		//2.获得角色编号
		String roleid = request.getParameter("roleid");		
		//3.获得权限编号（多个），4.获得权限对应的操作 （多个拼接）
		String rolepermission = request.getParameter("rolepermission");
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
		
		//5.新增前删除该用户的所有用户角色的数据
		SysUserRoleDetailMapper.deleteUserData(userid);	
		
		//6.新增前删除所有用户与角色的关系
		SysUserroleMapper.deleteUserRole(userid);
		//7.新增用户与角色的关系
		SysUserroleMapper.insert(new TbSysUserrole(userid, roleid));
		
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String s = it.next();
			String[] sary = s.split(",");
			String id = "";
			String operid = "";
			for (int i = 0; i < sary.length - 1; i++) {
				if (sary[i].length() == 14 && sary[i + 1].length() == 14) {//权限编号
					id = sary[i];
					insertUserRolePermission(userid, roleid, id, "-"); // 新增角色和权限关联信息
				} else if (sary[i].length() == 14 && sary[i + 1].length() <= 2) { // 权限编号
					id = sary[i];
				} else { // 操作编号
					operid += sary[i] + "|";
				}
			}
			if (sary[sary.length - 2].length() == 14 && sary[sary.length - 1].length() == 14) {
				insertUserRolePermission(userid, roleid, sary[sary.length - 1], "-"); // 新增角色和权限关联信息
			} else {
				insertUserRolePermission(userid, roleid, id, operid + sary[sary.length - 1]); // 新增角色和权限关联信息
			}
		}
		return 0;
	}
	
	//插入操作
	public int insertUserRolePermission(String userid, String roleid, String permissionid, String operid){
		String maxid = SysUserRoleDetailMapper.getMaxId();
		TbSysUserroledetail urp = new TbSysUserroledetail(HandleId.createid(maxid, "%06d", 8), userid, roleid, permissionid, operid);
		System.out.println(userid + "; " + roleid + "; " + permissionid + "; " + operid);
		
		return  SysUserRoleDetailMapper.insert(urp);
	}

	/**
	 * 查询所有电话销售员工
	 */
	@Override
	public List<TbSysUserroledetail> selectallsale() {
		// TODO Auto-generated method stub
		return SysUserRoleDetailMapper.selectallsale();
	}

}
