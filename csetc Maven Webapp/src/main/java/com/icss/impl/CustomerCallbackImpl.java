/**
 * 文件名: CustomerCallbackImpl.java
 * 描述:回访记录接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-15
 */
package com.icss.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbCustomer;
import com.icss.bean.TbCustomerCallback;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbCustomerCallbackMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.CustomerCallbackService;


@Service("CustomerCallbackImpl")
public  class CustomerCallbackImpl implements CustomerCallbackService {
	 
	@Autowired
	private TbCustomerCallbackMapper CustomerCallbackMapper; 
	
	@Autowired
	private TbSysUserroledetailMapper SysUserroledetailMapper; 

	@Override
	public TbCustomerCallback getCallbackById(HttpServletRequest request) {
		// 查看某客户是否有回访（今日录入）
		return CustomerCallbackMapper.getCallbackById(request.getParameter("customerid"));
	}

	@Override
	public int addCallbackinfo(TbCustomerCallback callback) {
		// 新增一条回访记录
		callback.setStatus("0");
		return CustomerCallbackMapper.insertSelective(callback);
	}

	@Override
	public int updCallbackById(TbCustomerCallback callback) {
		// 修改一条回访记录
		return CustomerCallbackMapper.updateByPrimaryKeySelective(callback);
	}

	@Override
	public TbCustomerCallback getCallbackBySome(HttpServletRequest request,
			HttpSession session) {
		// 查看某用户的客户是否有回访记录
		TbCustomerCallback callback = new TbCustomerCallback();
		callback.setCustomerid(request.getParameter("customerid"));
		callback.setReportuserid((String) session.getAttribute("userid"));
		return CustomerCallbackMapper.getCallbackBySome(callback);
	}

	@Override
	public List<TbCustomerCallback> getCallbackDatainfo(HttpSession session) {
		// 获得今日待回访的数据
		// 根据当前登录的用户查看分配数据
		String userid = (String) session.getAttribute("userid");
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170329000013");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		List<TbCustomerCallback> calist = new ArrayList<TbCustomerCallback>(); 
		TbCustomer c = new TbCustomer();
		c.setCollector(userid);
		c.setCustomerid("0");
		if(ur.size()>0){
			String roleid = ur.get(0).getRoleid();
			
			if("20170327000001".equals(roleid)){//管理员
				calist = CustomerCallbackMapper.getAllCallbackinfo("0");
				//calist1 = CustomerCallbackMapper.getAllCallbackinfo("0");
			}else if("20170327000002".equals(roleid)){//二级管理员
				c.setCollector("010301");
				calist = CustomerCallbackMapper.getSecondCallbackinfo(c);
			}else if("20170327000010".equals(roleid)||"20170327000003".equals(roleid) || "20170327000005".equals(roleid)){//部门主管
				calist = CustomerCallbackMapper.getDeptCallbackinfo(c);
			}else if("20170327000006".equals(roleid)){//电话销售
				calist = CustomerCallbackMapper.getCallbackinfo(c);
			}else{//其他用户
				calist = CustomerCallbackMapper.getMyCallbackinfo(c);
			}
			
		}
		//将两集合关联起来
		/*for (TbCustomerCallback tbCustomerCallback : calist) {
			for (TbCustomerCallback customerCallback : calist1) {
				if(tbCustomerCallback.getCustomer().getId().equals(customerCallback.getCustomer().getId())){
					tbCustomerCallback.getCustomer().setSalarytext(customerCallback.getCustomer().getSalarytext());
					tbCustomerCallback.getCustomer().setNayionalitytext(customerCallback.getCustomer().getNayionalitytext());
					tbCustomerCallback.getCustomer().setWorkplacetext(customerCallback.getCustomer().getWorkplacetext());
					break;
				}
			}
		}*/
		return calist;
	}

	@Override
	public int updCallbackStatus(HttpServletRequest request) {
		// 更改客户回访记录的状态
		TbCustomerCallback callback = new TbCustomerCallback();
		callback.setCallbackid(Integer.parseInt(request.getParameter("callbackid")));
		callback.setStatus(request.getParameter("status"));
		return CustomerCallbackMapper.updateByPrimaryKeySelective(callback);
	}

	@Override
	public void operbtn(HttpSession session) {
		// 回访客户中可操作的数据
		String userid = (String) session.getAttribute("userid");
		TbSysUserroledetail ud = new TbSysUserroledetail();
		ud.setUserid(userid);
		ud.setPermissionid("20170329000016");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	}

	@Override
	public String getCallbackByCid(HttpServletRequest request) {
		// 根据客户编号查看回访记录
		return JSON.toJSONString(CustomerCallbackMapper.getCallbackByCid(request.getParameter("customerid")));
	}

	@Override
	public String selectCallbackData(HttpSession session,
			HttpServletRequest request) {
		// 按照回访时间查看回访数据
		String userid = (String) session.getAttribute("userid");
		TbCustomerCallback callback = new TbCustomerCallback();
		callback.setReportuserid(userid);
		callback.setCalltime(request.getParameter("startdate"));
		callback.setMemo(request.getParameter("enddate"));
		return JSON.toJSONString(CustomerCallbackMapper.selectCallbackData(callback));
	}
	


}










