/**
 * 文件名: CustomerController.java
 * 描述:客户基础信息表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-28
 */
package com.icss.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.bean.TbCustomer;
import com.icss.impl.CustomerImpl;
import com.icss.util.FileUpload;
import com.icss.util.SysControllerLog;

@Controller("CustomerController")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerImpl CustomerImpl;
	
	//客户信息页面跳转,登录用户信息的判断//根据登录者的角色查看客户信息
	@RequestMapping("customer.do")
	public String customer(HttpSession session,HttpServletRequest request){
		CustomerImpl.operbtn(session);
		return "customer/customerinfo";
	}
	
	//新增单个客户信息
	@RequestMapping("addOneCustomerInfo.do")
	@SysControllerLog(requestoper="ADD",permid="20170327000003")
	public @ResponseBody String addOneCustomerInfo(@ModelAttribute("customer") TbCustomer customer,HttpServletRequest request,HttpSession session){
		customer.setOperator((String) session.getAttribute("userid"));
		//System.out.println(request.getParameter("collector_data"));
		if(request.getParameter("collector_data").equals("undefined")){
			customer.setCollector(customer.getOperator());
		}else{
			customer.setCollector(request.getParameter("collector_data"));
		}
		try {
			System.out.println(request.getParameter("birthdaytime"));
			if(!"".equals(request.getParameter("birthdaytime")) && request.getParameter("birthdaytime") != "null" && request.getParameter("birthdaytime").length() >= 10 ){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(request.getParameter("birthdaytime"));
				customer.setBirthday(date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CustomerImpl.addOneCustomerInfo(customer) + "";//新增一个客户信息
	}
	
/*	//批量导入客户信息
	@RequestMapping(value="batchImportCustomer.do",produces = "text/plain;charset=UTF-8", method=RequestMethod.POST)
	public @ResponseBody String batchImportCustomer(@RequestParam(value="mFile") CommonsMultipartFile  mFile,HttpServletRequest request,HttpSession session,HttpServletResponse response){
		System.out.println("1234564");
		return CustomerImpl.batchImportCustomer(mFile, request, session);
	}*/
	
	//批量导入客户信息
	@RequestMapping(value="importcustomer.do",produces = "text/plain;charset=UTF-8")
	@SysControllerLog(requestoper="ADD",permid="20170327000003")
	public @ResponseBody String importcustomer(@RequestParam(value="mFile") MultipartFile mFile,HttpServletRequest request,HttpSession session,HttpServletResponse response) throws IOException{
		return CustomerImpl.batchImportCustomer(mFile, request, session);
		
	}
	//根据客户电话检查是否有该客户
	@RequestMapping("checkPresenceExists.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000003")
	public @ResponseBody String checkPresenceExists(HttpServletRequest request){
		return JSON.toJSONString(CustomerImpl.checkPresenceExists(request));
	}
	
	//导出客户信息模板
	@RequestMapping("exporttemplate.do")
	@SysControllerLog(requestoper="DOWM",permid="20170327000003")
	public String exporttemplate(HttpServletRequest request,HttpServletResponse response){
		System.out.println("下载文件");
		try {
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/down/导入模板.xlsx");//获取文件名 的路径
			File file =  new File(path);
			if (file.exists()) {
				response.setContentType("application/octet-stream");
				String filename = request.getParameter("fileName");
				filename = new String(filename.getBytes("UTF-8"), "ISO_8859_1");
				System.out.println(filename);
				response.addHeader("Content-Disposition", "attachment; filename=" + filename);
				FileInputStream fileInputStream = new FileInputStream(file);
				
				byte[] by = new byte[fileInputStream.available()];
				fileInputStream.read(by);
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(by);
				fileInputStream.close();
				outputStream.close();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
	
	//查看表格数据的展示
	@RequestMapping("getCustomerinfoData.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000003")
	public @ResponseBody String getCustomerinfoData(HttpSession session,HttpServletRequest request){
		System.out.println("查看表格数据");
		return JSON.toJSONString(CustomerImpl.customerinfo(session, request), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
	} 
	
	//修改客户信息
	@RequestMapping("updCustomerinfo.do")
	@SysControllerLog(requestoper="UPD",permid="20170327000003")
	public @ResponseBody String updCustomerinfo(@ModelAttribute("customer") TbCustomer customer, HttpServletRequest request){
//		customer.setCollector(request.getParameter("collector_data"));
		if(!request.getParameter("collector_data").equals("undefined")){
			customer.setCollector(request.getParameter("collector_data"));
		}
		try {
			System.out.println(request.getParameter("birthdaytime"));
			if(!"".equals(request.getParameter("birthdaytime")) && request.getParameter("birthdaytime") != "null" && request.getParameter("birthdaytime").length() >= 10 ){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(request.getParameter("birthdaytime"));
				customer.setBirthday(date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CustomerImpl.updCustomerinfo(customer) + "";
	}
	
	//按条件查找客户数据
	@RequestMapping("lookupCustomer.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000003")
	public @ResponseBody String lookupCustomer(){
		return "";
	}
	
	//客户数据页面跳转
	@RequestMapping("customerdata.do")
	public String customerdata(HttpSession session,HttpServletRequest request){
		CustomerImpl.operbtn(session);
		return "customer/customer";
	}
		
	//条件客户
	@RequestMapping("condition.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000003")
	public @ResponseBody String condition(@ModelAttribute("c") TbCustomer customer){
		return CustomerImpl.condition(customer);
	}
	
	//查看数据统计（意向课程、来源渠道、男女比例）
	@RequestMapping("qrySeeDataCount.do")
	public @ResponseBody String qrySeeDataCount(HttpSession session){
		return CustomerImpl.qrySeeDataCount(session);
	}
	
	//查看未导入简历照片的客户数据页面跳转
	@RequestMapping("datamate.do")
	public String datamate(HttpSession session){
		return "customer/datamate";
	}
	//查看未导入简历照片的客户数据
	@RequestMapping("datamateinfo.do")
	public @ResponseBody String datamateinfo(HttpSession session){
		return CustomerImpl.datamate(session);
	}
	//上传订单附件
	@RequestMapping("uploadResume.do")
//	@SysControllerLog(requestoper="ADD",permid="20170329000018")
	public @ResponseBody String uploadResume(@RequestParam(value="myFile") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
		TbCustomer customer = new TbCustomer();
		customer.setCustomerid(request.getParameter("customerid"));
		customer.setResumeurl(FileUpload.uploadResume(request, file));
		return CustomerImpl.resumeurl(customer);
	}
	
	//团队客户
	@RequestMapping("teamData.do")
	public @ResponseBody String teamData(HttpSession session){
		return CustomerImpl.teamData(session);
	}
	
	//查看所有被放弃的客户
	@RequestMapping("abandonedData.do")
	public @ResponseBody String abandonedData(){
		return "";
	}
}
