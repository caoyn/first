package com.icss.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
		
		//设置客户
		String cusId = getCusIdByOrderId(cq.getOrderId());
		System.out.println("客户的Id="+cusId);
		cq.setCustomerId(cusId);
		
		// 是否已经上传
		List<CustomerQualification> list = customerQualificationDao.getQua(cq);
		boolean isUpdate = false;
		if (list!=null && list.isEmpty()!=true) {
			CustomerQualification qua = customerQualificationDao.getQua(cq).get(0);
			//删除原来已经上传的
			String path = session.getServletContext().getRealPath(qua.getQualificationUrl());
			File file = new File(path);
			file.delete();
			isUpdate = true;
			
			cq.setId(qua.id);
		}
		//设置创建人、创建时间、状态、审核理由
		String userId = (String) session.getAttribute("userid");
		System.out.println("上传客户资质的userId="+userId);
		cq.setCreator(userId);
		cq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		cq.setStatus("2");
		cq.setQualificationMemo("-");
		
		//设置id
		if (!isUpdate) {
			cq.setId(getCurDateNextId());
		}
		
		//图片路径 保存图片到 /upload/CustomerQulification/2017-xx-xx/xxxx.jpg
		String date =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String urlPath = "/upload/CustomerQulification/"+date;
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
		
		Integer count = 0;
		String pointMsg="";
		if (isUpdate) {
			pointMsg = "更新";
			count = customerQualificationDao.update(cq);
		}else {
			pointMsg = "插入";
			count = customerQualificationDao.save(cq);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		if (count == 1) {
			map.put("success", "true");
			map.put("msg", "上传成功，请等待审核");
		} else {
			map.put("success", "false");
			map.put("msg", "上传失败(数据库"+pointMsg+"插入失败)，请联系管理员");
		}
		return map;
	}


	/**
	 * 获取下一个ID
	 * @return
	 */
	private String getCurDateNextId() {
		String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String curDateMaxId = customerQualificationDao.getCurDateMaxId(curDate);
		
		long curDateNextId = 0;
		if (curDateMaxId != null && !"0".equals(curDateMaxId)) {
			curDateNextId = Long.parseLong(curDateMaxId)+1;
		} else {
			curDateNextId = Long.parseLong(curDate + "000001");
		}
		return String.valueOf(curDateNextId);
	}
	
	
	@Override
	public String getCusIdByOrderId(String orderId) {
		return customerQualificationDao.getCusIdByOrderId(orderId);
	}

	/**
	 * 是否显示查看按钮
	 * @param cq
	 * @return
	 */
	public Map<String, String> isQuaEmpty(CustomerQualification cq) {
		//设置客户
		cq.setCustomerId(getCusIdByOrderId(cq.getOrderId()));
		List<CustomerQualification> list = customerQualificationDao.getQua(cq);
		
		Map<String, String> map = new HashMap<String, String>();
		if (list==null || list.isEmpty()==true){
			map.put("isExist", "false");
		} else {
			map.put("isExist", "true");
			map.put("status", list.get(0).getStatus());
			map.put("qualificationMemo", list.get(0).getQualificationMemo());
		}
		return map;
	}

	/**
	 * 获取资质附件地址
	 * @param cq
	 * @return
	 */
	public Map<String, String> getQuaUrl(CustomerQualification cq) {
		//设置客户
		cq.setCustomerId(getCusIdByOrderId(cq.getOrderId()));
		List<CustomerQualification> list = customerQualificationDao.getQua(cq);
		
		Map<String, String> map = new HashMap<String, String>();
		if (list==null || list.isEmpty()==true){
			map.put("success", "false");
			map.put("msg", "未找到数据");
		} else {
			map.put("success", "true");
			map.put("url", list.get(0).getQualificationUrl());
		}
		return map;
	}
	
	/**
	 * 获取资质
	 */
	public List<CustomerQualification> query(CustomerQualification cq) {
		return customerQualificationDao.getQua(cq);
	}
	
}
