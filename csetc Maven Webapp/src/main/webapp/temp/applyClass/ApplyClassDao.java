package com.icss.dao;

import java.util.List;

import com.icss.bean.QualifiedStudent;

/**
 * 销售申请学员入班持久层dao接口
 * @author caoyanan
 * @time 2017年7月14日上午10:03:25
 * @description
 */
public interface ApplyClassDao {

	/**
	 * 获取得到许可入班的学员
	 */
	List<QualifiedStudent> getQualifiedStudents(List<String> status); 
}
