package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.bean.TbSysBasecode;


public interface SysBasecodeService {
	
	int addBasecodeData(TbSysBasecode basecode);
	
	List<TbSysBasecode> getALLBigTypeData();
	
	List<TbSysBasecode> getSmallMaxIdOfBigId(String bigid);
	
	List<TbSysBasecode> judgeExist(HttpServletRequest request);
	
	List<TbSysBasecode> judgeExistMax(HttpServletRequest request);
	
	List<TbSysBasecode> getAllBasecodeData();
	
	int deleteByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(HttpServletRequest request);
	
	int updBasecodeStatus(HttpServletRequest request);
	
	String getALLBasecodeByType();
	
 }
