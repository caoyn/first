/**
 * 文件名: RefundImpl.java
 * 描述:退返款申请的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-27
 */
package com.icss.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbApproveDetail;
import com.icss.bean.TbApproveMain;
import com.icss.bean.TbOrderChanged;
import com.icss.bean.TbOrderOrders;
import com.icss.bean.TbOrderRefund;
import com.icss.bean.TbSysUser;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbApproveDetailMapper;
import com.icss.dao.TbApproveMainMapper;
import com.icss.dao.TbOrderChangedMapper;
import com.icss.dao.TbOrderOrdersMapper;
import com.icss.dao.TbOrderRefundMapper;
import com.icss.dao.TbSysApprovedetailMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.RefundService;


@Service("RefundImpl")
public class RefundImpl implements RefundService {

	@Autowired 
	private TbOrderRefundMapper OrderRefundMapper;

	@Autowired
	private TbSysUserMapper SysUserMapper;
	
	@Autowired
	private TbOrderOrdersMapper OrderOrdersMapper;
	
	@Autowired
	private TbOrderChangedMapper OrderChangedMapper;
	
	@Autowired
	private TbSysApprovedetailMapper SysApprovedetailMapper;
	
	@Autowired
	private TbApproveDetailMapper ApproveDetailMapper;
	
	@Autowired
	private TbSysUserroledetailMapper SysUserroledetailMapper;
	
	@Autowired
	private TbApproveMainMapper ApproveMainMapper;
	
	@Override
	public String addRefundinfo(TbOrderRefund refund, HttpSession session,HttpServletRequest request) {
		//申请退学有关联的表a。订单表   b。订单变更表 c.订单退款表  d.审批表
		
		//操作人编号
		String userid = (String) session.getAttribute("userid");
		//操作人的部门
		String deptid = SysUserMapper.getDeptidByUserid(userid);
		//获得审批配置数据
		TbApproveMain approveMain = new TbApproveMain();
		String curruserid = approveinfo(new TbSysUser(request.getParameter("approveid"),deptid,"0"));
		if(curruserid.indexOf("error") == -1){
			approveMain.setCurruserid(curruserid); // 当前审批人
			String nextuserid = approveinfo(new TbSysUser(request.getParameter("approveid"),deptid.substring(0,deptid.length()-2),"1"));
			if(nextuserid.indexOf("error") == -1){
				approveMain.setNextuserid(nextuserid); // 下一审批人
			}else{
				return nextuserid;
			}
		}else{
			return curruserid;
		}
		
		//新增一条审批数据  d
	
		approveMain.setOrderid(refund.getOrderid());
		approveMain.setApprovetype(request.getParameter("approvetype"));
		approveMain.setStatus("0");
		
		ApproveMainMapper.insert(approveMain);
		
		// 新增退返款申请  c
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		refund.setUserid(userid);
		refund.setDeptid(deptid);
		try {
			refund.setRefundtime(sdf.parse(request.getParameter("refundtime1")));
		} catch (ParseException e) {
			// 时间转换出错
			e.printStackTrace();
		}
		OrderRefundMapper.insert(refund);
		
		//更改订单数据  a
		TbOrderOrders orders = new TbOrderOrders();
		orders.setOrderid(refund.getOrderid());
		orders.setStatus(request.getParameter("ostatus"));
		orders.setApproveid(request.getParameter("approveid"));
		OrderOrdersMapper.updateByOrderid(orders); 
		
		//更改订单变更表状态
		OrderChangedMapper.updChangeStatus(orders.getOrderid());//修改订单改变表状态
		//新增订单变更表数据 b		
		TbOrderChanged changed = new TbOrderChanged();
		changed.setOederid(refund.getOrderid());
		changed.setChangeuserid(userid);
		changed.setUserid(userid);
		changed.setDeptid(deptid);
		changed.setReason("3");
		changed.setMemo(refund.getMemo());
		changed.setStatus("0");
		OrderChangedMapper.insert(changed);
		return "1";
	}
	

	//确定审批人信息
	public String approveinfo(TbSysUser user){
		List<TbSysUser> list = SysUserMapper.getApprveUserinfo(user);
		String result = "";
		if(list.size() == 0 || list == null){
			result = "error:没有找到匹配的审批人，请重新选择审批流程。请联系管理员修改审批配置或者选择其他审批流程";
		}else if(list.size()>1){
			String s = "error:该审批角色有多个用户，系统不知道应该由哪个用户来审批 这些用户是：";
			for (TbSysUser tbSysUser : list) {
				s += tbSysUser.getUsername() + ",";
			}
			result =  s+"请联系管理员修改审批配置或者选择其他审批流程";
		}else{
			result =  list.get(0).getUserid();
		}
		System.out.println(result);
		return result;
	}


	@Override
	public String getRefundData(HttpSession session) {
		// 获得当前登录者的退返款申请记录
		String userid = (String) session.getAttribute("userid");
		List<TbOrderRefund> list =  new ArrayList<TbOrderRefund>();
		TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170329000020");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		if(ur.size() == 1){
			if("20170609000009".equals(ur.get(0).getRoleid())){
				list = OrderRefundMapper.allRefundData();
			}else{
				list = OrderRefundMapper.getRefundData(userid);
			}
		}
		return JSON.toJSONString(list);
	}


	@Override
	public String adoptApprove(HttpServletRequest request, HttpSession session) {
		// 同意退返款申请
		TbApproveDetail detail = new TbApproveDetail();
		TbApproveMain main = new TbApproveMain();
		//1.审批配置表获得下一审批人的编号
		String userid = (String) session.getAttribute("userid");
		String approveid = request.getParameter("approveid");//审批配置主表ID
		String mapproveid = request.getParameter("mapproveid");//审批主表ID
		String deptid = SysUserMapper.getDeptidByUserid(userid);
		
		main.setApproveid(mapproveid);
		int approvecount = ApproveDetailMapper.approvecount(mapproveid);
		int sortcount = SysApprovedetailMapper.sortcount(approveid);
		if(sortcount - approvecount == 2){//没有下一审批人
			main.setNextuserid("");
			ApproveMainMapper.updcurruserid(main);//3.审批表中更新当前审批人和下一审批人
		}else if(sortcount - approvecount == 1){//更改审批表状态，（完成）
			main.setStatus("1");//审批完成
			ApproveMainMapper.updateByPrimaryKeySelective(main);
		}else{
			String nextuserid = approveinfo(new TbSysUser(approveid,deptid.substring(0, deptid.length()-4),(approvecount+2)+""));
			if(nextuserid.indexOf("error") == -1){
				main.setNextuserid(nextuserid);
			}else{
				return nextuserid;
			}
			ApproveMainMapper.updcurruserid(main);//3.审批表中更新当前审批人和下一审批人
		}
		
		//2.审批详情表中新增审批信息
		detail.setApproveid(mapproveid);
		detail.setUserid(userid);
		detail.setApproveresult("1");
		detail.setMemo(request.getParameter("memo"));
		ApproveDetailMapper.insert(detail);
		
		return "1";
	}


	@Override
	public String rejectApprove(HttpServletRequest request, HttpSession session) {
		// 驳回退返款申请 
		//1.修改订单退返款申请表
		OrderRefundMapper.updOrderStatus(new TbOrderRefund(request.getParameter("orderid"),"2"));
		//2.修改审批表
		ApproveMainMapper.updateByPrimaryKeySelective(new TbApproveMain(request.getParameter("mapproveid"),"2"));
		//3.新增审批详情表
		String userid = (String) session.getAttribute("userid");
		ApproveDetailMapper.insert(new TbApproveDetail(request.getParameter("mapproveid"),userid,"0",request.getParameter("memo")));
		//修改订单数据（取消退返款申请）
		OrderOrdersMapper.cancelRefund(request.getParameter("orderid"));
		return "1";
	}


	@Override
	public String approveDetail(HttpServletRequest request) {
		// 审批流程查看
		List<TbApproveDetail> list = ApproveDetailMapper.approveDetail(request.getParameter("mapproveid"));
		return JSON.toJSONString(list);
	}
	
	//可操作的权限
	public void operbtn(HttpSession session) {
		// 可操作的按钮
		String userid = (String) session.getAttribute("userid");
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170329000020");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	} 
}










