package com.icss.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//查看按钮是否可用
	@RequestMapping("/IsQuaEmpty.do")
	@ResponseBody
	public Map<String, String> isQuaEmpty(CustomerQualification cq) {
		return customerQulificationService.isQuaEmpty(cq);
	}
	
	
	//获取资质附件地址
	@RequestMapping("/GetQuaUrl.do")
	@ResponseBody
	public Map<String, String> getQuaUrl(CustomerQualification cq) {
		return customerQulificationService.getQuaUrl(cq);
	}
	
	/**
	 * 资质上传模块入口展示
	 */
	@RequestMapping("/page.do")
	public String page() {
		// 指定视图路径 /WEB-INF/jsp/class/apply.jsp
		return "verify/qualification";
	}
	
	//获得待审核数据
	@RequestMapping("/query.do")
	public List<CustomerQualification> query (CustomerQualification cq) {
		return customerQulificationService.query(cq);
	}
}
