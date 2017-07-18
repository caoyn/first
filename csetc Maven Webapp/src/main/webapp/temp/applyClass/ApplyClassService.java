package com.icss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icss.bean.QualifiedStudent;
import com.icss.dao.ApplyClassDao;
import com.icss.service.IApplyClassService;

/**
 * 销售申请学员入班service实现类
 * @author caoyanan
 * @time 2017年7月13日下午4:11:35
 * @description
 */
@Service
public class ApplyClassService implements IApplyClassService {

	@Autowired
	private ApplyClassDao applyClassDao;

	@Override
	public List<QualifiedStudent> getQualifiedStudents(List<String> status) {
		return applyClassDao.getQualifiedStudents(status);
	}
}
