package com.icss.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.bean.TbSysBasecode;

public class BaseCodeDaoTest {

	static TbSysBasecodeMapper baseCodeDao = null;

	static {
		// 得到springIOC容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		baseCodeDao = ac.getBean(TbSysBasecodeMapper.class);
	}

	/**
	 * getQuaOfSales方法测试
	 * @throws Exception
	 */
	@Test
	public void testGetQuaOfSales() throws Exception {
		List<TbSysBasecode> quaOfSales = baseCodeDao.getQuaOfSales(1);
		System.out.println(quaOfSales);
	}
}
