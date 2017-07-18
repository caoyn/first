package com.icss.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerAssign;
import com.icss.bean.TbCustomerReservation;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbCustomerAssignMapper;
import com.icss.dao.TbCustomerMapper;
import com.icss.dao.TbCustomerReservationMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.SysCustomerAssionService;

@Service("SysCustomerAssionService")
public class SysCustomerAssionImpl implements SysCustomerAssionService {

	@Autowired
	private TbCustomerAssignMapper TbCustomerAssignMapper;

	@Autowired
	private TbCustomerMapper TbCustomerMapper;
	
	@Autowired
	public TbSysUserroledetailMapper SysUserroledetailMapper;//用户角色关联
	
	@Autowired
	public TbCustomerReservationMapper CustomerReservationMapper;//用户角色关联
	
	@Autowired
	public TbSysUserMapper SysUserMapper;//员工数据
	
	
	@Override
	public int insert(TbCustomerAssign TbCustomerAssign) {
		// TODO Auto-generated method stub
		return TbCustomerAssignMapper.insert(TbCustomerAssign);
	}

	@Override
	public int assignCustomer(HttpServletRequest request, HttpSession session) {
		// 分配客户（新增客户的同时更改客户分配状态）
		int count = 0;
		String[] customerids = request.getParameter("customerid").split(","); 
		String assignuserid = (String) session.getAttribute("userid"); //获取当前登录人  (分配人)  
		String userid = request.getParameter("userid"); //获取当前登录人  (分配人)     
		for (int i = 0; i < customerids.length; i++) {
//			System.out.println(customerids[i]);
			if(request.getParameter("reservationid") != null && !(request.getParameter("reservationid")).equals("null")){
				TbCustomerReservation reservation = new TbCustomerReservation();
				reservation.setStatus("1");
				reservation.setReservationid(Integer.parseInt(request.getParameter("reservationid")));
				CustomerReservationMapper.updCustomerReservationStatus(reservation);
			}
			
			TbCustomerAssign customerAssign = new TbCustomerAssign(customerids[i], new Date(), assignuserid, userid);
			count = count + TbCustomerAssignMapper.insertSelective(customerAssign);
			
		}
		return count;
	}

	@Override
	public List<TbCustomerAssign> consultCustomerData(
			HttpServletRequest request, HttpSession session) {
		// 根据当前登录的用户查看分配数据
		String userid = (String) session.getAttribute("userid");
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170329000013");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		List<TbCustomerAssign> calist = new ArrayList<TbCustomerAssign>(); 
		//List<TbCustomerAssign> calist1 = new ArrayList<TbCustomerAssign>(); 
		TbCustomer c = new TbCustomer();
		c.setCollector(userid);
		c.setCustomerid("0");
		if(ur.size()>0){
			String roleid = ur.get(0).getRoleid();
			
			if("20170327000001".equals(roleid)){//管理员
				calist = TbCustomerAssignMapper.getAllCustomerAssigninfo("0");
			}else if("20170327000002".equals(roleid)){//二级管理员
				c.setCollector("010301");
				calist = TbCustomerAssignMapper.getSecondCustomerAssigninfo(c);
			}else if("20170327000010".equals(roleid)||"20170327000003".equals(roleid) || "20170327000005".equals(roleid) || "20170327000009".equals(roleid)){//部门主管
				calist = TbCustomerAssignMapper.getCustomerAssigninfo(c);
				
			}else if("20170327000006".equals(roleid)||"20170327000008".equals(roleid)){//电话销售、课程顾问
				calist = TbCustomerAssignMapper.getCustomerAssigninfo(c); 
				
			}else if("20170327000004".equals(roleid)||"20170327000003".equals(roleid)){//信息专员，信息主管
				calist = TbCustomerAssignMapper.getCustomerAssignData(c); 
				
			}else{//其他用户
				calist = TbCustomerAssignMapper.getMyCustomerAssigninfo(c);
			}
		}
		return calist;
	}

	@Override
	public int updCustomerAssignStatus(HttpServletRequest request) {
		// 更改客户分配状态（一条或者多条）
		String status = request.getParameter("status");
		String customerids = request.getParameter("customerids");
		if(customerids.endsWith(",")){
			customerids = customerids.substring(0, customerids.length()-1);
		}
		customerids = "(" + customerids + ")";
		TbCustomerAssign assign = new TbCustomerAssign();
		assign.setStatus(status);
		assign.setCustomerid(customerids);
		if("2".equals(status)){
			TbCustomerMapper.updCustomerStatus(assign);
		}
		return TbCustomerAssignMapper.updCustomerAssignStatus(assign);
	}

	@Override
	public void operbtn(HttpSession session) {
		// 可以操作的按钮
		TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid((String) session.getAttribute("userid"));
	    ud.setPermissionid("20170329000013");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	}
	
	@Override
	public String getMyCustomerSelect(HttpSession session) {
		// 查看自己预约的客户或者由自己采集的客户信息
		String userid  = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000015");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		
		TbCustomerAssign assign = new TbCustomerAssign();
		if(ur.size()>0){
			if("20170327000008".equals(ur.get(0).getRoleid())){
				assign.setUserid(userid);
			}else{
				assign.setAssignuserid(userid);
			}
		}
		return JSON.toJSONString(TbCustomerAssignMapper.getMyCustomerByRid(assign));
	}

	@Override
	public String getAssignByCid(HttpServletRequest request) {
		// 查看某客户的分配数据
		return JSON.toJSONString(TbCustomerAssignMapper.getAssignByCid(request.getParameter("customerid")));
	}

	@Override
	public String workdata(HttpSession session) {
		// 查看自己的工作状况
		String userid  = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000015");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		List<TbCustomerAssign> list = new ArrayList<TbCustomerAssign>();
		if(ur.size()>0){
			if("20170327000008".equals(ur.get(0).getRoleid()) || "20170327000009".equals(ur.get(0).getRoleid())){ //课程顾问,主管
				list.add(TbCustomerAssignMapper.reservationTMK(userid));
				list.add(TbCustomerAssignMapper.visitTMK(userid));
				list.add(TbCustomerAssignMapper.visitedTMK(userid));
				list.add(TbCustomerAssignMapper.callbackTMK(userid));
				list.add(TbCustomerAssignMapper.callbackedTMK(userid));
				list.add(TbCustomerAssignMapper.assignTMK(userid));
				list.add(TbCustomerAssignMapper.assignfollowTMK(userid));
				list.add(TbCustomerAssignMapper.paymentCC(userid));
				list.add(TbCustomerAssignMapper.refundCC(userid));
			}else if("20170327000006".equals(ur.get(0).getRoleid()) || "20170327000005".equals(ur.get(0).getRoleid())){ //电话销售,主管
				list.add(TbCustomerAssignMapper.reservationTMK(userid));
				list.add(TbCustomerAssignMapper.visitTMK(userid));
				list.add(TbCustomerAssignMapper.visitedTMK(userid));
				list.add(TbCustomerAssignMapper.callbackTMK(userid));
				list.add(TbCustomerAssignMapper.callbackedTMK(userid));
				list.add(TbCustomerAssignMapper.assignTMK(userid));
				list.add(TbCustomerAssignMapper.assignfollowTMK(userid));
				list.add(TbCustomerAssignMapper.paymentTMK(userid));
				list.add(TbCustomerAssignMapper.refundTMK(userid));
			}
		}
		
		return JSON.toJSONString(list);
	}

	@Override
	public String teamData(HttpSession session) {
		// 团队数据
		return JSON.toJSONString(TbCustomerAssignMapper.teamData(SysUserMapper.getDeptidByUserid((String) session.getAttribute("userid"))));
	}

}
