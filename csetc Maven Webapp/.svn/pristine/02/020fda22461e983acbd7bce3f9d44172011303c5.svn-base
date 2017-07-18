package com.icss.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.bean.TbSysLog;
import com.icss.dao.TbSysLogMapper;
import com.icss.service.TbSysLogService;

@Service("SysLogImpl")
public class SysLogImpl implements TbSysLogService {

	@Autowired
	private TbSysLogMapper TbSysLogMapper;
	
	@Override
	public int add(TbSysLog log) {
		// TODO Auto-generated method stub
		System.out.println("------------------------------");
		return TbSysLogMapper.insert(log);
	}

	@Override
	public List<TbSysLog> selectAll() {
		// TODO Auto-generated method stub
		return TbSysLogMapper.selectAll();
	}
}