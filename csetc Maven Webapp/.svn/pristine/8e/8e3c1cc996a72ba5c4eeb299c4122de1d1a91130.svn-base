package com.icss.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.icss.bean.TbCustomerAssign;
import com.icss.dao.TbSysUserMapper;
import com.icss.impl.SysCustomerAssionImpl;
import com.icss.util.SysControllerLog;

@Controller("TbCustomerAssignController")
@RequestMapping("TbCustomerAssign")
public class TbCustomerAssignController {
	
	@Autowired
	private SysCustomerAssionImpl SysCustomerAssionService;
	
	@Autowired
	public TbSysUserMapper SysUserMapper;//员工数据
	@RequestMapping("ctmassign.do")
	public String ctmassign(HttpSession session){
		SysCustomerAssionService.operbtn(session);
		return "customer/ctmassign";
	}
	
	/**
	 * 插入信息
	 * @param TbCustomerAssign
	 * @param request
	 * @return
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public String insert(TbCustomerAssign TbCustomerAssign,HttpServletRequest request){
		System.out.println("----------------------------------------------------------");
		Date date =new Date(System.currentTimeMillis());//获取系统时间
	    HttpSession session = request.getSession();  
		String userid=(String) session.getAttribute("userid");//获取当前登录人       
		String[] customerid=request.getParameter("customerid").split(",");
		System.out.println(customerid);
		String uid=request.getParameter("userid");
		System.out.println(uid);
		for(int i=0;i<customerid.length;i++){
				TbCustomerAssign.setCustomerid(customerid[i]);//客户序号
				TbCustomerAssign.setUserid(uid);//拥有人
				TbCustomerAssign.setAssignid("");
		        TbCustomerAssign.setAssigntime(date);//分配时间
		        TbCustomerAssign.setAssignuserid(userid);//分配人
		        TbCustomerAssign.setStatus("0");//默认分配跟进中
		        SysCustomerAssionService.insert(TbCustomerAssign);
		}
		return null;
	}
	
	//分配客户(新增和修改)
	@RequestMapping("assignCustomer.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000013")
	public @ResponseBody String assignCustomer(HttpServletRequest request, HttpSession session){
		return SysCustomerAssionService.assignCustomer(request, session) + "";
	}
	
	//根据登录者查看分配后的客户数据
	@RequestMapping("consultCustomerData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000013")
	public @ResponseBody String consultCustomerData(HttpServletRequest request, HttpSession session){
		JSONObject jo = new JSONObject();
		jo.put("customerassign", SysCustomerAssionService.consultCustomerData(request, session));
		return jo.toString();
	}
	

	
	//修改客户分配状态
	@RequestMapping("updCustomerAssignStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000013")
	public @ResponseBody String updCustomerAssignStatus(HttpServletRequest request){
		return SysCustomerAssionService.updCustomerAssignStatus(request) + "";
	}

	//查看由自己预约或者由自己采集的客户数据
	@RequestMapping("getMyCustomerSelect.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000013")
	public @ResponseBody String getMyCustomerSelect(HttpSession session){
		return SysCustomerAssionService.getMyCustomerSelect(session); 
	}
	
	//查看某客户的所有分配信息
	@RequestMapping("getAssignByCid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000013")
	public @ResponseBody String getAssignByCid(HttpServletRequest request){
		return SysCustomerAssionService.getAssignByCid(request);
	}
	
	//查看今日自己的工作数据
	@RequestMapping("workdata.do")
	public @ResponseBody String workdata(HttpSession session){
		return SysCustomerAssionService.workdata(session);
	}
	
	//查看团队客户
	@RequestMapping("teamData.do")
	public @ResponseBody String teamData(HttpSession session){
		return SysCustomerAssionService.teamData(session);
	}
}
