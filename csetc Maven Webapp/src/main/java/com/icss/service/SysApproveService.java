package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.bean.TbSysApprove;
import com.icss.bean.TbSysApprovedetail;


public interface SysApproveService {
	
	int addApproveData(TbSysApprove approve);

	List<TbSysApprove> getAllApproveData();
	
	int addApproveDetailData(HttpServletRequest request);
	
	List<TbSysApprovedetail> getApproveDetailByAid(String approveid);
	
	int delApproveById(String approveid);
	
	int delApproveSubNode(String id);
	
	int updApproveData(TbSysApprove approve); 
	
	int updApproveSubNodeData(TbSysApprovedetail approvedetail);
	
	TbSysApprove getOneAproveData(String approveid);
	
	TbSysApprovedetail getOneSubNodeData(String id);
	
	String getApproveSelectByType(HttpServletRequest request);
}
