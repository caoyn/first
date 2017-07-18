package com.icss.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icss.bean.CustomerQualification;
import com.icss.dao.ICustomerQualificationDao;
import com.icss.service.ICustomerQualificationService;

/**
 * 客户资质service实现类
 * @author caoyanan
 * @time 2017年7月18日下午4:02:37
 * @description
 */
@Service
public class CustomerQualificationServiceImpl implements ICustomerQualificationService{
	
	@Autowired
	private ICustomerQualificationDao customerQualificationDao;

	@Override
	public Map<String, String> uploadCusQua(CustomerQualification cq, MultipartFile pic, HttpSession session) {
		
		//设置创建人、创建时间
		String userId = (String) session.getAttribute("userid");
		System.out.println("上传客户资质的userId="+userId);
		cq.setCreator(userId);
		cq.setCreateTime(new Date().toString());
		
		//设置客户
		String cusId = getCusIdByOrderId(cq.getOrderId());
		System.out.println("客户的Id="+cusId);
		cq.setCustomerId(cusId);
		
		//图片路径 保存图片到 /upload/CustomerQulification/2017-xx-xx/xxxx.jpg
		String date =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String urlPath = "/WEB-INF/upload/CustomerQulification/"+date;
		String path = session.getServletContext().getRealPath(urlPath);
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//目录不存在");
			file.mkdirs();//创建该目录
		}
		
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		file = new File(file.getPath() + '/' + name + "." + ext);
		try {
			pic.transferTo(file);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		cq.setQualificationUrl(urlPath + '/' + name + "." + ext);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		map.put("msg", "上传成功，请等待审核");
		return map;
	}

	@Override
	public String getCusIdByOrderId(String orderId) {
		return customerQualificationDao.getCusIdByOrderId(orderId);
	}

	
}
