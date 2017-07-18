package com.icss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.bean.QualifiedStudent;
import com.icss.service.IApplyClassService;

/**
 * 销售申请学员入班控制器
 * 
 * @author caoyanan
 * @time 2017年7月13日上午9:57:20
 * @description
 */

@Controller
@RequestMapping("ApplyClass")
public class ApplyClassController {

	@Autowired
	private IApplyClassService applyClassService;
	
	/**
	 * 客户入班模块入口展示
	 */
	@RequestMapping("page")
	public String page() {
		// 指定视图路径 /WEB-INF/jsp/class/apply.jsp
		return "class/apply";
	}

	/**
	 * 得到入班审核许可的学员
	 */
	@ResponseBody
	@RequestMapping("query")
	public List<QualifiedStudent> query(Model m) {

		//设置符合入班审核许可学员的订单状态
		List<String> status = new ArrayList<String>();
		/**后期维护的时候就在status中加入或者删除状态就可以！，此时注意维护对应视图中状态转换(switch)*/
		status.add("1013");//已全款
		status.add("1012");//已收款，未全款 ,具体数据在数据字典中查询

		return applyClassService.getQualifiedStudents(status);
	}
}
