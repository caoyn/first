/**
 * 文件名: SysApproveImpl.java
 * 描述:审批管理接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-13
 */
package com.icss.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerAssign;
import com.icss.bean.TbCustomerCallback;
import com.icss.bean.TbCustomerRemark;
import com.icss.bean.TbCustomerReservation;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbCustomerAssignMapper;
import com.icss.dao.TbCustomerCallbackMapper;
import com.icss.dao.TbCustomerReservationMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.CustomerReservationService;


@Service("CustomerReservationImpl")
public class CustomerReservationImpl implements CustomerReservationService {
	
	@Autowired 
	private TbCustomerReservationMapper CustomerReservationMapper;
	
	@Autowired 
	private TbSysUserroledetailMapper SysUserroledetailMapper;
	
	@Autowired 
	private TbCustomerAssignMapper TbCustomerAssignMapper;
	
	@Autowired 
	private TbCustomerCallbackMapper CustomerCallbackMapper;

	@Override
	public int addCustomerReservation(HttpServletRequest request,TbCustomerReservation reservation) {
		// 新增客户预约信息
		long msl = Long.parseLong(request.getParameter("expect"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date temp = null;
		String str = sdf.format(msl);
		try {
			temp = sdf.parse(str);
			reservation.setExpecttime(temp);
			reservation.setStatus("0");
			reservation.setReceiver(request.getParameter("receiver"));
			
			TbCustomerAssign assign = new TbCustomerAssign();
			assign.setAssignid(request.getParameter("assignid"));
			assign.setStatus("1");
			TbCustomerAssignMapper.updateByPrimaryKeySelective(assign);//修改分配数据
			return CustomerReservationMapper.insertSelective(reservation);//新增预约
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int getExistReservation(HttpServletRequest request,HttpSession session) {
		// 查看某客户是否有预约记录
		TbCustomerReservation reservation = new TbCustomerReservation();
		reservation.setCustomerid(request.getParameter("customerid"));
		reservation.setReportuserid((String) session.getAttribute("userid") );
		return CustomerReservationMapper.getExistReservation(reservation);
	}

	@Override
	public String getReservationCustomerData(HttpSession session) {
		// 查看预约记录
//		System.out.println("用户id"+HandleId.sysUserid);
		String userid = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000015");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		System.out.println(ur.size());
		List<TbCustomerReservation> clist = new ArrayList<TbCustomerReservation>();
		TbCustomer c = new TbCustomer();
		c.setCollector(userid);
		c.setCustomerid("0");
		for (TbSysUserroledetail tbSysUserroledetail : ur) {
			// System.out.println(tbSysUserroledetail.getRoleid());
			if ("20170327000001".equals(tbSysUserroledetail.getRoleid())) {//管理员
				clist = CustomerReservationMapper.getAllCustomerReservationinfo("0");
				//clist1 = CustomerReservationMapper.getAllCustomerReservationinfo1("0");
				break;
			} else if ("20170327000002".equals(tbSysUserroledetail.getRoleid())) {//二级管理员
				System.out.println("这是二级管理员角色，可以查看指定部门所有数据数据");
				// 获得该角色能够看的部门数据
				c.setCollector("01,0102,010301");
				clist = CustomerReservationMapper.getSecondCustomerReservationinfo(c);
				break;

			} else if ("20170327000010".equals(tbSysUserroledetail.getRoleid())
					|| "20170327000003".equals(tbSysUserroledetail.getRoleid())
					|| "20170327000005".equals(tbSysUserroledetail.getRoleid())) { //部门主管
				System.out.println("这里是部门主管的角色。可以查看自己部门的所有数据");
				clist = CustomerReservationMapper.getDeptCustomerReservationinfo(c);
				break;
			} else if ("20170327000007".equals(tbSysUserroledetail.getRoleid())) { //销售前台
				clist = CustomerReservationMapper.getSaleCustomerReservationinfo(c);
				break;
			} else {//其他
				System.out.println("这里是普通员工或者其他部门员工的角色，查看自己的数据");
				clist = CustomerReservationMapper.getCustomerReservationinfo(c);
			}
		}
		// 将两集合关联起来
		/*for (TbCustomerReservation tbCustomerReservation : clist) {
			for (TbCustomerReservation customerReservation : clist1) {
				if (tbCustomerReservation.getCustomer().getId().equals(customerReservation.getCustomer().getId())) {
					tbCustomerReservation.getCustomer().setSalarytext(customerReservation.getCustomer().getSalarytext());
					tbCustomerReservation.getCustomer().setNayionalitytext(customerReservation.getCustomer().getNayionalitytext());
					tbCustomerReservation.getCustomer().setWorkplacetext(customerReservation.getCustomer().getWorkplacetext());
					break;
				}
			}
		}*/
		return JSONObject.toJSONString(clist);
	}

	@Override
	public int updCustomerReservationStatus(TbCustomerReservation reservation) {
		// 更改客户上门状态
		return  CustomerReservationMapper.updCustomerReservationStatus(reservation);
	}

	@Override
	public void operbtn(HttpSession session) {
		// 可操作的按钮
		String userid = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000015");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	}

	@Override
	public String getReservationDataBySome(HttpSession session,HttpServletRequest request) {
		// 
		String userid = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000015");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		System.out.println(ur.size());
		List<TbCustomerReservation> clist = new ArrayList<TbCustomerReservation>();
		List<TbCustomerReservation> clist1 = new ArrayList<TbCustomerReservation>();
		TbCustomer c = new TbCustomer();
		c.setCollector(userid);
		c.setMajor(request.getParameter("some"));
		for (TbSysUserroledetail tbSysUserroledetail : ur) {
			// System.out.println(tbSysUserroledetail.getRoleid());
			if ("20170327000001".equals(tbSysUserroledetail.getRoleid())
			 || "20170327000007".equals(tbSysUserroledetail.getRoleid())) {//管理员,销售前台
				clist = CustomerReservationMapper.getAllCustomerReservationBysome("1");
				clist1 = CustomerReservationMapper.getAllCustomerReservationBysome1("1");
				break;
			} else if ("20170327000002".equals(tbSysUserroledetail.getRoleid())) {//二级管理员
				System.out.println("这是二级管理员角色，可以查看指定部门所有数据数据");
				// 获得该角色能够看的部门数据
				c.setCollector("01,0102,010301");
				clist = CustomerReservationMapper.getSecondCustomerReservationBysome(c);
				clist1 = CustomerReservationMapper.getSecondCustomerReservationBysome1(c);
				break;

			} else if ("20170327000010".equals(tbSysUserroledetail.getRoleid())
					|| "20170327000003".equals(tbSysUserroledetail.getRoleid())
					|| "20170327000005".equals(tbSysUserroledetail.getRoleid())) { //部门主管
				System.out.println("这里是部门主管的角色。可以查看自己部门的所有数据");
				clist = CustomerReservationMapper.getDeptCustomerReservationBysome(c);
				clist1 = CustomerReservationMapper.getDeptCustomerReservationBysome1(c);
				break;
			} else {//其他
				System.out.println("这里是普通员工或者其他部门员工的角色，查看自己的数据");
				clist = CustomerReservationMapper.getCustomerReservationBysome(c);
				clist1 = CustomerReservationMapper.getCustomerReservationBysome1(c);
			}
		}
		// 将两集合关联起来
		for (TbCustomerReservation tbCustomerReservation : clist) {
			for (TbCustomerReservation customerReservation : clist1) {
				if (tbCustomerReservation.getCustomer().getId().equals(customerReservation.getCustomer().getId())) {
					tbCustomerReservation.getCustomer().setSalarytext(customerReservation.getCustomer().getSalarytext());
					tbCustomerReservation.getCustomer().setNayionalitytext(customerReservation.getCustomer().getNayionalitytext());
					tbCustomerReservation.getCustomer().setWorkplacetext(customerReservation.getCustomer().getWorkplacetext());
					break;
				}
			}
		}
		return JSONObject.toJSONString(clist);
	}

	@Override
	public String getReservationByCid(HttpServletRequest request) {
		// 某客户的预约记录
		return JSON.toJSONString(CustomerReservationMapper.getReservationByCid(request.getParameter("customerid")));
	}

	@Override
	public String updVisitTime(TbCustomerReservation reservation) {
		// 修改上门时间
		return CustomerReservationMapper.updateByPrimaryKeySelective(reservation) + "";
	}

	@Override
	public String resevationCallback(TbCustomerCallback callback,
			HttpServletRequest request, HttpSession session) {
		// 预约后回访
		//1.新增回访记录
		callback.setReportuserid((String) session.getAttribute("userid"));
		callback.setStatus("0");
		try {
			callback.setTipdate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("tipdatetime")));
			CustomerCallbackMapper.insert(callback);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.更改预约状态
		TbCustomerReservation reservation =  new TbCustomerReservation();
		reservation.setStatus(request.getParameter("rstatus"));
		reservation.setReservationid(Integer.parseInt(request.getParameter("reservationid")));
		return CustomerReservationMapper.updateByPrimaryKeySelective(reservation) + "";
	}

	
	
}










