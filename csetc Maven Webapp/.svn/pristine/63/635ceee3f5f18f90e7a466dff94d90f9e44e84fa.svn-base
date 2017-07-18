/**
 * 文件名: SysBasecodeController.java
 * 描述:基础代码表管理类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-22 
 */
package com.icss.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbSysBasecode;
import com.icss.service.SysBasecodeService;
import com.icss.util.SysControllerLog;

@Controller("SysBasecodeController")
@RequestMapping("/basecode")
public class SysBasecodeController {
	@Autowired
	private SysBasecodeService SysBasecodeImpl;
	
	/*
	* 跳轉到基礎數據管理 
	*/
	@RequestMapping("basecode.do")
	public  String basecode(){
		return "admin/basecode";
	}
	
	
	//新增基础数据
	@RequestMapping("addBasecodeData")
	@SysControllerLog(requestoper="ADD",permid="20170329000005")
	public @ResponseBody String addBasecodeData(@ModelAttribute("basecode") TbSysBasecode basecode){
		return ""+SysBasecodeImpl.addBasecodeData(basecode);
	}
	
	//查看所有大类数据
	@RequestMapping("getALLBigTypeData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000005")
	public @ResponseBody String getALLBigTypeData(){
		System.out.println("**************************************");
//		List<TbSysBasecode> list = SysBasecodeImpl.getALLBigTypeData();
		return JSON.toJSONString(SysBasecodeImpl.getALLBigTypeData());
	}
	
	//根据大类查看小类数据
	@RequestMapping("getSmallMaxIdOfBigId.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000005")
	public @ResponseBody String getSmallMaxIdOfBigId(HttpServletRequest request){
		return JSON.toJSONString(SysBasecodeImpl.getSmallMaxIdOfBigId(request.getParameter("bigid")));
	}
	
	//检查是否存在该小类数据
	@RequestMapping("judgeExist.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000005")
	public @ResponseBody String judgeExist(HttpServletRequest request){
		return JSON.toJSONString(SysBasecodeImpl.judgeExist(request));
	}
	
	//检查是否存在该大类数据
	@RequestMapping("judgeExistMax.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000005")
	public @ResponseBody String judgeExistMax(HttpServletRequest request){
		return JSON.toJSONString(SysBasecodeImpl.judgeExistMax(request));
	}
	
	//查看所有基本数据
	@RequestMapping("getAllBasecodeData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000005")
	public @ResponseBody String getAllBasecodeData(){
		return JSON.toJSONString(SysBasecodeImpl.getAllBasecodeData());
	}
	
	//删除一条基础数据
	@RequestMapping("delOneBasecodeData.do")
	@SysControllerLog(requestoper="DEL",permid="20170329000005")
	public @ResponseBody String delOneBasecodeData(HttpServletRequest request){
		return SysBasecodeImpl.deleteByPrimaryKey(request.getParameter("id")) + "";
	}
	
	//修改一条小类数据
	@RequestMapping("updOneBasecodeData.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000005")
	public @ResponseBody String updOneBasecodeData(HttpServletRequest request){
		return SysBasecodeImpl.updateByPrimaryKeySelective(request) + "";
	}
	//修改一条小类状态
	@RequestMapping("updBasecodeStatus.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000005")
	public @ResponseBody String updBasecodeStatus(HttpServletRequest request){
		return SysBasecodeImpl.updBasecodeStatus(request) + "";
	}

	//获得所有的基础数据按类来分
	@RequestMapping("getALLBasecodeByType.do")
	@SysControllerLog(requestoper="QRY", permid="20170329000005")
	public @ResponseBody String getALLBasecodeByType(){
		return SysBasecodeImpl.getALLBasecodeByType();
	}
}
