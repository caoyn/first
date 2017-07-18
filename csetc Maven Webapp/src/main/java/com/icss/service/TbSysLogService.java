package com.icss.service;

import java.util.List;

import com.icss.bean.TbSysLog;

public interface TbSysLogService {
	List<TbSysLog>  selectAll();
	
	int add(TbSysLog log);
}
