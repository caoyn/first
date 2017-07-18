/**
 * 文件名: SysDeptImpl.java
 * 描述:部门接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-21 
 */
package com.icss.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.bean.TbSysDept;
import com.icss.bean.TreeNode;
import com.icss.dao.TbSysDeptMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TreeBuilderMapper;
import com.icss.service.SysDeptService;
import com.icss.util.HandleId;
import com.icss.util.TreeBuilder;

@Service("SysDeptImpl")
public  class SysDeptImpl implements SysDeptService {
	
	@Autowired
	public TbSysDeptMapper SysDeptMapper;
	
	@Autowired	
	public TbSysUserMapper SysuserMapper;
	
	@Autowired
	public TreeBuilderMapper TreeBuilderMapper;

	//新增一条部门数据
	@Override
	public int addDept(TbSysDept dept){		
		//获得部门编号等所需数据
//		System.out.println(dept.getUserid());
		String id = "0";
		String deptid;
		if(SysDeptMapper.getMaxId() != null){			
			id = SysDeptMapper.getMaxId();
		}
		System.out.println(dept.getTopdeptid());
		if(SysDeptMapper.getMaxDeptId(dept.getTopdeptid()) != null){	//查询当前部门最大的id(有上级部门的按照上级ID查，没有就查根节点部门)		
			deptid = SysDeptMapper.getMaxDeptId(dept.getTopdeptid());
			System.out.println(deptid);
		}else{
			deptid = dept.getTopdeptid()+"00";
		}
		if(dept.getTopdeptid() == ""){
			dept.setDeptlevel("0");	
			dept.setTopdeptid("0");
		}else{
			dept.setDeptlevel("1");	
		}
		
		//生成序号和部门编号
		dept.setId(HandleId.createid(id,"%06d",8));
		dept.setDeptid(HandleId.createdeptid(deptid,"%02d",0));			
		return SysDeptMapper.insertSelective(dept);//新增操作
	}
	
	//获得部门所有数据
	@Override
	public List<TbSysDept> allDeptData(){
		System.out.println(TreeBuilder.bulid(TreeBuilderMapper.getAllDeptData()));//将部门数据弄成json形式的字符串
		return SysDeptMapper.allDeptData();
	}
	
	@Override 
	public List<TbSysDept> juniorDeptData(String topdeptid) {
		// 根据上级部门编号查看所有下级的部门
		return SysDeptMapper.juniorDeptData(topdeptid);
	}

	//查看部门是否存在
	@Override
	public List<TbSysDept> judgeExistDept(String deptname) {
		// TODO Auto-generated method stub
		return SysDeptMapper.judgeExistDept(deptname);
	}

	@Override
	public int updDeptName(HttpServletRequest request) {
		// 更改部门名称
		TbSysDept dept = new TbSysDept();
		dept.setId(request.getParameter("id"));
		dept.setDeptname(request.getParameter("deptname"));
		return SysDeptMapper.updateByPrimaryKeySelective(dept);
	}

	@Override
	public int updDeptStatus(HttpServletRequest request) {
		// 更改部门状态
		String[] deptAry =  request.getParameter("deptid").split(",");
		for(int i = 0; i < deptAry.length; i++){
			TbSysDept dept = new TbSysDept();
			dept.setDeptid(deptAry[i]);
			dept.setStatus(request.getParameter("status"));
			SysDeptMapper.updDeptStatus(dept);
		}
		return 1;
	}

	@Override
	public int delDept(HttpServletRequest request) {
		// 删除部门
		String deptid = request.getParameter("id");
		// 1.判断该部门下是否有员工（用户）
		if(SysuserMapper.getUserByDeptid(deptid).size()>0){
			return 0;
		}else{
			return SysDeptMapper.deleteByPrimaryKey(deptid);
		}
	}

	@Override
	public List<TbSysDept> getDeptDataById(HttpServletRequest request) {
		// 根据编号获得该部门的信息
		System.out.println(request.getParameter("deptid"));
		return SysDeptMapper.getDeptDataById(request.getParameter("deptid"));
	}

	@Override
	public List<TreeNode> getAllJsonDeptData() {
		// 以json形式获得部门所有数据
		return TreeBuilder.bulid(TreeBuilderMapper.getAllDeptData());
	}

	@Override
	public int updDept(TbSysDept dept) {
		// 修改部门数据
		return SysDeptMapper.updateByPrimaryKeySelective(dept);
	}

	@Override
	public TbSysDept recursiveTree(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		TbSysDept node=	SysDeptMapper.datafordeptid();
		List<TbSysDept> childTreeNodes=SysDeptMapper.datafortopdeptid(id);
		for(TbSysDept child : childTreeNodes){
			TbSysDept dept =recursiveTree(request);
			node.getNodes().add(dept);
		}		
		return node;
		
	}



}
