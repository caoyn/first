package com.icss.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.bean.QualifiedStudent;

/**
 * 销售申请学员入班持久层dao测试
 * @author caoyanan
 * @time 2017年7月14日上午11:14:43
 * @description
 */
public class ApplyClassDaoTest {
	
	static ApplyClassDao applyClassDao = null;

	static {
		// 得到springIOC容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		applyClassDao = ac.getBean(ApplyClassDao.class);
	}

	/**
	 * getQualifiedStudents方法测试
	 */
	@Test
	public void testQualifiedStudents() throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("1013");
		status.add("1014");
		List<QualifiedStudent> qualifiedStudents = applyClassDao.getQualifiedStudents(status);
		System.out.println(qualifiedStudents);
	}
	
	
	
}
