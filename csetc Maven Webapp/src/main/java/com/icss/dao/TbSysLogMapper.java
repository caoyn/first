package com.icss.dao;

import java.util.List;
import com.icss.bean.TbSysLog;

public interface TbSysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysLog record);

    int insertSelective(TbSysLog record);

    TbSysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysLog record);

    int updateByPrimaryKey(TbSysLog record);
    
    List<TbSysLog>  selectAll();
    
    int  getLogDataCount();
    
    List<TbSysLog> getLogData(TbSysLog sysLog);
}