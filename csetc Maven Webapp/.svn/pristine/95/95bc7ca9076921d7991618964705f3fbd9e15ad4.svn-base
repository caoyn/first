package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.icss.bean.TbCustomer;



public interface CustomerService {
	int addOneCustomerInfo(TbCustomer customer);
	
	List<TbCustomer> checkPresenceExists(HttpServletRequest request);
	
	List<TbCustomer> customerinfo(HttpSession session, HttpServletRequest request);
	
	String batchImportCustomer(MultipartFile file,HttpServletRequest request,HttpSession session);
	
	int updCustomerinfo(TbCustomer customer);
	
	void operbtn(HttpSession session);
	
	String condition(TbCustomer customer);
	
	String qrySeeDataCount(HttpSession session);
	
	String datamate(HttpSession session);
	
	String resumeurl(TbCustomer customer);
	
	String teamData(HttpSession session);
}
