package com.icss.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerQualificationDaoTest {

	static ICustomerQualificationDao customerQualificationDao = null;

	static {
		// 得到springIOC容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		customerQualificationDao = ac.getBean(ICustomerQualificationDao.class);
	}

	/**
	 * getCusIdByOrderId方法测试
	 * @throws Exception
	 */
	@Test
	public void testGetCusIdByOrderId() throws Exception {
		String cusIdByOrderId = customerQualificationDao.getCusIdByOrderId("DD201706290001");
		System.out.println(cusIdByOrderId);
	}
}
