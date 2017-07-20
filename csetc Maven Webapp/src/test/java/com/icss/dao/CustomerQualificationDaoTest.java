package com.icss.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.bean.CustomerQualification;

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
	
	/**
	 * save方法测试
	 */
	@Test
	public void testSave() throws Exception {
		CustomerQualification cq = new CustomerQualification();
		cq.setId("20170720000001");
		cq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		cq.setCreator("E000700301");
		cq.setCustomerId("customerId");
		cq.setQualificationMemo("qualificationMemo");
		cq.setQualificationTypeId("20170316000001");
		cq.setQualificationUrl("qualificationUrl");
		customerQualificationDao.save(cq);
	}
	
	/**
	 * getCurDateMaxId方法测试
	 */
	@Test
	public void testgetCurDateMaxId() throws Exception {
		String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String curDateMaxId = customerQualificationDao.getCurDateMaxId(curDate);
		System.out.println(curDateMaxId);
		
	}
	
	/**
	 * getQua方法测试
	 */
	@Test
	public void testGetQua() throws Exception {
		List<CustomerQualification> qua = customerQualificationDao.getQua(new CustomerQualification());
		System.out.println(qua);
	}
	
	/**
	 * update方法测试
	 */
	@Test
	public void testUpdate() throws Exception {
		CustomerQualification cq = new CustomerQualification();
		cq.setId("20170720000001");
		cq.setStatus("0");
		System.out.println(customerQualificationDao.update(cq));
	}
}
