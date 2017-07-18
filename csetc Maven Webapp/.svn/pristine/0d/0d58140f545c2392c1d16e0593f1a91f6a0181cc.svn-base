/**
 * 文件名: SysApproveImpl.java
 * 描述:审批管理接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-13
 */
package com.icss.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbPromocode;
import com.icss.dao.TbPromocodeMapper;
import com.icss.service.PromocodeService;


@Service("PromocodeImpl")
public class PromocodeImpl implements PromocodeService {

	@Autowired
	private TbPromocodeMapper PromocodeMapper;
	@Override
	public String getPromotionRule(HttpServletRequest request) {
		// TODO Auto-generated method stub
		TbPromocode promocode = new TbPromocode();
		promocode.setRules(request.getParameter("rules"));
		promocode.setRpomocode(request.getParameter("promocode"));
		String rule =  PromocodeMapper.getPromotionRule(promocode);
		if(rule == null){
			rule = "";
		}
		return rule;
	}
	
	@Override
	public String getAllPromocodeData() {
		// 查看所有的促销码数据
		return JSON.toJSONString(PromocodeMapper.getAllPromocodeData());
	}
	
	@Override
	public String existPromocode(HttpServletRequest request) {
		// 查看是否存在该促销码
		return "" + PromocodeMapper.existPromocode(request.getParameter("promocode"));
	}

	@Override
	public String addPromocode(TbPromocode promocode, HttpSession session) {
		// 新增促销码
		String userid = (String) session.getAttribute("userid");
		promocode.setUserid(userid);
		return "" + PromocodeMapper.insert(promocode);
	}

	@Override
	public String changePromocodeStatus(HttpServletRequest request) {
		// 改变促销码状态
		String ids = request.getParameter("ids");
		if(ids.endsWith(",")){
			ids = ids.substring(0, ids.length()-1);
		}
		String status = request.getParameter("status");
		TbPromocode promocode = new TbPromocode();
		promocode.setPromoid("(" + ids +")");
		promocode.setStatus(status);
		return PromocodeMapper.changePromocodeStatus(promocode)+ "";
	}
	
}










