/**
 * 文件名: dept.js
 * 描述:与基础代码有关的操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-22 
 */
package com.icss.dao;

import java.util.List;

import com.icss.bean.TbSysBasecode;

public interface TbSysBasecodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysBasecode record);

    int insertSelective(TbSysBasecode record);

    TbSysBasecode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysBasecode record);

    int updateByPrimaryKey(TbSysBasecode record);
    
    List<TbSysBasecode> getSmallTypeOfBigType(String id);
    
    String getSmallMaxIdOfBigId(String id);
    
    String getMaxId();
    
    String getBidMaxId();
    
    List<TbSysBasecode> getALLBigTypeData();
    
    List<TbSysBasecode> judgeExist(TbSysBasecode basecode);
    
    List<TbSysBasecode> judgeExistMax(TbSysBasecode basecode);
    
    List<TbSysBasecode> getAllBasecodeData();
    
    // 获取销售要上传的客户纸质
 	List<TbSysBasecode> getQuaOfSales(Integer i);
    
}