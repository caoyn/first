package com.icss.service;

import java.util.List;

import com.icss.bean.QualifiedStudent;

/**
 * 销售申请学员入班service接口
 * @author caoyanan
 * @time 2017年7月13日上午10:07:34
 * @description
 */
public interface IApplyClassService {

	/**
	 * 获取得到许可入班的学员
	 */
	List<QualifiedStudent> getQualifiedStudents(List<String> status); 
}
