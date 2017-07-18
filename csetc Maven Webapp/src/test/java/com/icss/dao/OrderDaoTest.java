package com.icss.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderDaoTest {

	static TbOrderOrdersMapper orderDao = null;

	static {
		// 得到springIOC容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		orderDao = ac.getBean(TbOrderOrdersMapper.class);
	}

	/**
	 * getAtLeastMoneyForQua方法测试
	 * @throws Exception
	 */
	@Test
	public void testGetAtLeastMoneyForQua() throws Exception {
		Integer atLeastMoneyForQua = orderDao.getAtLeastMoneyForQua();
		System.out.println(atLeastMoneyForQua);
	}
}
