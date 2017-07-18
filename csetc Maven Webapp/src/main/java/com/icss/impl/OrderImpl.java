/**
 * 文件名: SysApproveImpl.java
 * 描述:审批管理接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-13
 */
package com.icss.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.bean.TbCustomerAssign;
import com.icss.bean.TbCustomerReservation;
import com.icss.bean.TbOrderChanged;
import com.icss.bean.TbOrderOrderdetail;
import com.icss.bean.TbOrderOrders;
import com.icss.bean.TbPromocodeUse;
import com.icss.bean.TbSysBasecode;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbCustomerAssignMapper;
import com.icss.dao.TbCustomerMapper;
import com.icss.dao.TbCustomerReservationMapper;
import com.icss.dao.TbOrderChangedMapper;
import com.icss.dao.TbOrderOrderdetailMapper;
import com.icss.dao.TbOrderOrdersMapper;
import com.icss.dao.TbPromocodeUseMapper;
import com.icss.dao.TbSysBasecodeMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.OrderService;


@Service("OrderImpl")
public  class OrderImpl implements OrderService {

	@Autowired
	private TbOrderOrdersMapper OrderOrdersMapper;
	
	@Autowired
	private TbOrderOrderdetailMapper OrderOrderdetailMapper;
	
	@Autowired
	private TbSysUserMapper SysUserMapper;
	
	@Autowired
	private TbCustomerMapper CustomerMapper;
	
	@Autowired
	private TbCustomerAssignMapper CustomerAssignMapper;
	
	@Autowired
	private TbCustomerReservationMapper CustomerReservationMapper;
	
	@Autowired
	private TbPromocodeUseMapper PromocodeUseMapper;
	
	@Autowired
	private TbSysUserroledetailMapper SysUserroledetailMapper;
	
	@Autowired
	private TbOrderChangedMapper OrderChangedMapper;
	
	@Autowired
	private TbSysBasecodeMapper baseCodeDao;
	
	@Override
	public int insertAndUpdateCustomerOrder(TbOrderOrders orders,
			List<TbOrderOrderdetail> orderdetails,HttpSession session) {
		int result = 0;
		String userid = (String)session.getAttribute("userid");
		//新增订单表
		orders.setDeptid(SysUserMapper.getDeptidByUserid(userid));
		orders.setUserid(userid);
		orders.setOrdertype("1");
		orders.setStatus("1011");
		result = OrderOrdersMapper.addOrder(orders);
		//更改客户信息（表格数据的状态）
		CustomerMapper.updCustomerStatus(new TbCustomerAssign("("+orders.getCustomerid()+")","9"));//客户信息表中变成已完结
		CustomerAssignMapper.updCustomerAssignStatus(new TbCustomerAssign("("+orders.getCustomerid()+")","1"));//客户分配表中变成处理完毕
		CustomerReservationMapper.updCusReservationStatusByCid(new TbCustomerReservation(orders.getCustomerid(),"2"));//客户预约表中变成已签单
		//获得当前插入的订单编号
		String orderid = OrderOrdersMapper.getOrderid();
		
		//新增订单改变表
		TbOrderChanged record = new TbOrderChanged();
		record.setDeptid(orders.getDeptid());
		record.setUserid(userid);
		record.setChangeuserid(userid);
		record.setOederid(orderid);
		record.setStatus("0");
		record.setReason("1");
		record.setMemo(orders.getMemo());
		OrderChangedMapper.insert(record);
		
		//促销码是否使用
		if(orders.getDiscount() != null && orders.getDiscount()>0){
			//新增促销码使用信息
			TbPromocodeUse promocodeUse = new TbPromocodeUse();
			promocodeUse.setUserid(userid);
			promocodeUse.setOrderid(orderid);
			promocodeUse.setPromocode(orders.getPromocode());
			PromocodeUseMapper.insert(promocodeUse);
		}
		//新增订单子表
		if(orderdetails.size()>0){
			for (TbOrderOrderdetail tbOrderOrderdetail : orderdetails) {
				tbOrderOrderdetail.setOrderid(orderid);
				tbOrderOrderdetail.setStatus("0");
				result += OrderOrderdetailMapper.insert(tbOrderOrderdetail);
			}
		}
		return result;
	}

	@Override
	public List<TbOrderOrders> getOrderData(HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000018");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		List<TbOrderOrders> list = new ArrayList<TbOrderOrders>();
		if(ur.size()>0){
			if("20170327000001".equals(ur.get(0).getRoleid())){
				list = OrderOrdersMapper.getAllOrderData();
			}else if("20170327000002".equals(ur.get(0).getRoleid())){
				System.out.println("这是二级管理员角色，可以查看指定部门所有数据数据");
				//获得该角色能够看的部门数据
				list = OrderOrdersMapper.getDeptOrderData(SysUserMapper.getDeptidByUserid(userid));
			}else if("20170327000010".equals(ur.get(0).getRoleid()) || "20170327000003".equals(ur.get(0).getRoleid())){
				System.out.println("这里是部门主管的角色。可以查看自己部门的所有数据");
				list = OrderOrdersMapper.getDeptOrderData(SysUserMapper.getDeptidByUserid(userid));
			}else if("20170327000005".equals(ur.get(0).getRoleid())){//电销主管
				list = OrderOrdersMapper.getTelDeptOrderData(userid);
			}else if("20170327000006".equals(ur.get(0).getRoleid())){//电话销售
				list = OrderOrdersMapper.getTelOrderData(userid);
			}else{
				list = OrderOrdersMapper.getMyOrderData(userid);
			}
		}
		// 查看自己签单信息
		return list;
	}

	@Override
	public String updOrderUser(TbOrderOrders orders, HttpServletRequest request, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		orders.setDeptid(SysUserMapper.getDeptidByUserid(userid));
		TbOrderChanged record = new TbOrderChanged();
		record.setChangeuserid(userid);
		record.setUserid(orders.getUserid());
		record.setDeptid(orders.getDeptid());
		record.setStatus("1");
		record.setReason("2");
		record.setMemo(orders.getMemo());
		//需要变更的订单
		String[] orderids =request.getParameter("orderidstr").split(",");
		for (int i = 0; i < orderids.length; i++) {
			orders.setOrderid(orderids[i]);
			record.setOederid(orderids[i]);
			OrderOrdersMapper.updateByOrderid(orders);// 更改订单负责人
			OrderChangedMapper.insert(record);//新增一条变更消息
		}
		return "1";
	}

	@Override
	public String updOrderData(TbOrderOrders orders,
			HttpServletRequest request, HttpSession session) {
		// 更改订单数据
		//1.更改订单数据
		OrderOrdersMapper.updateByOrderid(orders);
		
		//2.新增订单子表
		String userid = (String) session.getAttribute("userid");
		TbOrderChanged record = new TbOrderChanged();
		record.setOederid(orders.getOrderid());
		record.setChangeuserid(userid);
		record.setDeptid(orders.getDeptid());
		record.setUserid(orders.getUserid());
		record.setMemo(orders.getMemo());
		record.setReason(request.getParameter("reason"));
		OrderChangedMapper.updChangeStatus(record.getOederid());//修改订单改变表状态
		OrderChangedMapper.insert(record);//新增订单改变表
		return "1";
	}

	@Override
	public String updOrderStatus(TbOrderChanged changed,HttpServletRequest request) {
		//更改订单状态
		TbOrderOrders orders = new TbOrderOrders();
		orders.setOrderid(changed.getOederid());
		orders.setStatus(request.getParameter("ostatus"));
		OrderOrdersMapper.updateByOrderid(orders); 
		//获得订单信息
		orders = OrderOrdersMapper.getOrderDataByOid(orders.getOrderid());
		//新增订单改变表信息
		//获得操作者编号
		changed.setDeptid(orders.getDeptid());
		changed.setUserid(orders.getUserid());
		OrderChangedMapper.insert(changed);
		return "1";
	}

	@Override
	public String getOrderDataByOid(HttpServletRequest request) {
		String orderid = request.getParameter("orderid");
		// 根据订单编号查看订单
		TbOrderOrders orders = OrderOrdersMapper.getOrderDataByOid(orderid);
		//查看订单详情
		List<TbOrderOrderdetail> list = OrderOrderdetailMapper.getOrderDetailData(orderid);
		JSONObject jo = new JSONObject();
		jo.put("order", orders);
		jo.put("orderdetail", list);
		return JSON.toJSONString(jo,SerializerFeature.WriteNullStringAsEmpty);
	}

	@Override
	public String notPayOrder() {
		// 未收款或者未全款的订单
		return JSON.toJSONString(OrderOrdersMapper.notPayOrder());
	}

	@Override
	public String getNotPayOrderDataByOid(HttpServletRequest request) {
		// 查看订单详情信息（含是否付款）
		return JSON.toJSONString(OrderOrderdetailMapper.getNotPayOrderDataByOid(request.getParameter("orderid")));
	}
	
	
	//用户权限
	public void operbtn(HttpSession session) {
		// 可操作的按钮
		String userid = (String) session.getAttribute("userid");
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170329000018");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	}

	@Override
	public String getOrderDataByCid(HttpServletRequest request) {
		// 根据客户编号查看客户详情
		return JSON.toJSONString(OrderOrdersMapper.getOrderDataByCid(request.getParameter("customerid")));
	}

	@Override
	public Integer getAtLeastMoneyForQua() {
		// 获取上传客户纸质至少需要的交款金额
		return OrderOrdersMapper.getAtLeastMoneyForQua();
	}

	public List<TbSysBasecode> getQuaOfSales(int i) {
		// 获取销售要上传的客户纸质
		return baseCodeDao.getQuaOfSales(i);
	}
}










