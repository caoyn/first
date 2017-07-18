/**
 * 文件名: SysApproveImpl.java
 * 描述:审批管理接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-13
 */
package com.icss.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbSysApprove;
import com.icss.bean.TbSysApprovedetail;
import com.icss.dao.TbSysApproveMapper;
import com.icss.dao.TbSysApprovedetailMapper;
import com.icss.service.SysApproveService;

@Service("SysApproveImpl")
public  class SysApproveImpl implements SysApproveService {
	
	@Autowired
	public TbSysApproveMapper SysApproveMapper;
	
	@Autowired
	public TbSysApprovedetailMapper SysApproveDetailMapper;

	@Override
	public int addApproveData(TbSysApprove approve) {
		// 新增审批数据
		return SysApproveMapper.insert(approve);
	}

	@Override
	public List<TbSysApprove> getAllApproveData() {
		// 所有的审批数据
		return SysApproveMapper.getAllApproveData();
	}

	@Override
	public int addApproveDetailData(HttpServletRequest request) {
		// 审批详情数据
		int result = 0;
		String approveid = request.getParameter("approveid");
		List<TbSysApprovedetail> list = JSON.parseArray(request.getParameter("subApprove"), TbSysApprovedetail.class);
		for(TbSysApprovedetail d : list){
			d.setApproveid(approveid);
			result = SysApproveDetailMapper.insert(d);
		}
		return result;
	}

	@Override
	public List<TbSysApprovedetail> getApproveDetailByAid(String approveid) {
		// 查看是否有审批流程细节
		return SysApproveDetailMapper.getApproveDetailByAid(approveid);
	}

	@Override
	public int delApproveById(String approveid) {
		// 删除审批
		//1.删除详情
		SysApproveDetailMapper.delByApproveId(approveid);
		//2.删除基础
		return SysApproveMapper.deleteByPrimaryKey(approveid);
	}

	@Override
	public int delApproveSubNode(String id) {
		// 删除审批子节点
		return SysApproveDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updApproveData(TbSysApprove approve) {
		// 更新审批流信息
		return SysApproveMapper.updateByPrimaryKeySelective(approve);
	}

	@Override
	public int updApproveSubNodeData(TbSysApprovedetail approvedetail) {
		// 更新审批子节点数据
		return SysApproveDetailMapper.updateByPrimaryKeySelective(approvedetail);
	}

	@Override
	public TbSysApprove getOneAproveData(String approveid) {
		// 查看一个审批流数据
		return SysApproveMapper.selectByPrimaryKey(approveid);
	}

	@Override
	public TbSysApprovedetail getOneSubNodeData(String id) {
		// 查看一个审批节点数据
		return SysApproveDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public String getApproveSelectByType(HttpServletRequest request) {
		// 根据审批类型查看审批数据
		return JSON.toJSONString(SysApproveMapper.getApproveSelectByType(request.getParameter("type")));
	}
	


}
