package com.icss.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icss.bean.CustomerQualification;
import com.icss.service.impl.CustomerQualificationServiceImpl;
/**
 * 客户资质上传控制器
 * @author caoyanan
 * @time 2017年7月18日下午3:55:14
 * @description
 */

@Controller
@RequestMapping("/CustomerQualification")
public class CustomerQualificationController {
	
	@Autowired
	private CustomerQualificationServiceImpl customerQulificationService;

	//上传客户资质
	@RequestMapping("/UploadCusQua.do")
	//@SysControllerLog(requestoper="uploadCustomerQualification", permid="20170329000018")
	@ResponseBody
	public Map<String, String> uploadCusQua(CustomerQualification cq, 
			@RequestParam MultipartFile qualificationPicture, HttpSession session) {
		
		return customerQulificationService.uploadCusQua(cq, qualificationPicture, session);
	}
}
