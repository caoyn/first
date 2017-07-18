/**
 * 文件名: SysBasecodeImpl.java
 * 描述:基础代码接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-21 
 */
package com.icss.impl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icss.bean.TbSysBasecode;
import com.icss.dao.TbSysBasecodeMapper;
import com.icss.service.SysBasecodeService;
import com.icss.util.HandleId;

@Service("SysBasecodeImpl")
public  class SysBasecodeImpl implements SysBasecodeService {
	
	@Autowired
	public TbSysBasecodeMapper SysBasecodeMapper;
	
	

	@Override
	public int addBasecodeData(TbSysBasecode basecode) {
		// 根据当前选择的大类编号获得小类编号的最大值		
		String smallMaxid = SysBasecodeMapper.getSmallMaxIdOfBigId(basecode.getLevel1id());
		// 获得当前表格的最大id
		String maxid = SysBasecodeMapper.getMaxId();
		
		basecode.setId(HandleId.createid(maxid,"%06d",8));//生成编号
		basecode.setLevel2id(HandleId.createbasecodeid(smallMaxid));//计算小类编号
		if(basecode.getLevel1id() == null || basecode.getLevel1id() == "" || basecode.getLevel1id().length() == 0){
			basecode.setLevel1id((Integer.parseInt(SysBasecodeMapper.getBidMaxId()) + 1) + "");//计算大类编号
		}
		System.out.println(basecode);
		// 新增基础代码数据
		return SysBasecodeMapper.insertSelective(basecode);
	}

	@Override
	public List<TbSysBasecode> getALLBigTypeData() {
		// 获得基础代码表中的所有大类信息
		return SysBasecodeMapper.getALLBigTypeData();
	}

	@Override
	public List<TbSysBasecode> getSmallMaxIdOfBigId(String bigid) {
		// 根据大类id查看小类数据
		return SysBasecodeMapper.getSmallTypeOfBigType(bigid);
	}

	@Override
	public List<TbSysBasecode> judgeExist(HttpServletRequest request) {
		// 检查要输入小类的值是否存在
		TbSysBasecode basecode = new TbSysBasecode();
		basecode.setLevel1id(request.getParameter("level1id"));
		basecode.setLevel2name(request.getParameter("level2name"));
		return SysBasecodeMapper.judgeExist(basecode);
	}

	@Override
	public List<TbSysBasecode> judgeExistMax(HttpServletRequest request) {
		// 检查要输入大类的值是否存在
		TbSysBasecode basecode = new TbSysBasecode();
		basecode.setLevel1name(request.getParameter("level1name"));		
		return SysBasecodeMapper.judgeExistMax(basecode);
	}

	@Override
	public List<TbSysBasecode> getAllBasecodeData() {
		
		// 获得所有基本数据
		List<TbSysBasecode> list = SysBasecodeMapper.getAllBasecodeData();		
		return list;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// 删除基本代码数据
		return SysBasecodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HttpServletRequest request) {
		// 修改基础代码数据
		TbSysBasecode b = new TbSysBasecode();
		b.setId(request.getParameter("id"));
		b.setLevel2name(request.getParameter("newname"));
		return SysBasecodeMapper.updateByPrimaryKeySelective(b);
	}

	@Override
	public int updBasecodeStatus(HttpServletRequest request) {
		// 修改基础代码状态
		TbSysBasecode b = new TbSysBasecode();
		b.setId(request.getParameter("id"));
		if( request.getParameter("status").equals("1")){
			b.setStatus("0");
		}else{
			b.setStatus("1");
		}
		
		return SysBasecodeMapper.updateByPrimaryKeySelective(b);
	}

	@Override
	public String getALLBasecodeByType() {
		// 查看所有的基础信息
		List<TbSysBasecode> list = SysBasecodeMapper.getAllBasecodeData();
		JSONObject jo = new JSONObject();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		JSONArray ja3 = new JSONArray();
		JSONArray ja4 = new JSONArray();
		JSONArray ja5 = new JSONArray();
		JSONArray ja6 = new JSONArray();
		JSONArray ja7 = new JSONArray();
		JSONArray ja8 = new JSONArray();
		JSONArray ja9 = new JSONArray();
		JSONArray ja10 = new JSONArray();
		JSONArray ja11 = new JSONArray();
		JSONArray ja12 = new JSONArray();
		JSONArray ja13 = new JSONArray();
		JSONArray ja14 = new JSONArray();
		JSONArray ja15 = new JSONArray();
		JSONArray ja16 = new JSONArray();
		JSONArray ja17 = new JSONArray();
		JSONArray ja18 = new JSONArray();
		JSONArray ja19 = new JSONArray();
		JSONArray ja20 = new JSONArray();
		JSONArray ja21 = new JSONArray();
		JSONArray ja22 = new JSONArray();
		JSONArray ja23 = new JSONArray();
		JSONArray ja24= new JSONArray();
		JSONArray ja25= new JSONArray();
		JSONArray ja26= new JSONArray();
		JSONArray ja27= new JSONArray();
		JSONArray ja28= new JSONArray();
		JSONArray ja29= new JSONArray();
		Iterator<TbSysBasecode> it = list.iterator();
		while(it.hasNext()){
			TbSysBasecode b = it.next();
			switch (b.getLevel1id()) {
			case "01": ja1.add(b);break;
			case "02": ja2.add(b);break;
			case "03": ja3.add(b);break;
			case "04": ja4.add(b);break;
			case "05": ja5.add(b);break;
			case "06": ja6.add(b);break;
			case "07": ja7.add(b);break;
			case "08": ja8.add(b);break;
			case "09": ja9.add(b);break;
			case "10": ja10.add(b);break;
			case "11": ja11.add(b);break;
			case "12": ja12.add(b);break;
			case "13": ja13.add(b);break;
			case "14": ja14.add(b);break;
			case "15": ja15.add(b);break;
			case "16": ja16.add(b);break;
			case "17": ja17.add(b);break;
			case "18": ja18.add(b);break;
			case "19": ja19.add(b);break;
			case "20": ja20.add(b);break;
			case "21": ja21.add(b);break;
			case "22": ja22.add(b);break;
			case "23": ja23.add(b);break;
			case "24": ja24.add(b);break;
			case "25": ja25.add(b);break;
			case "26": ja26.add(b);break;
			case "27": ja27.add(b);break;
			case "28": ja28.add(b);break;
			case "29": ja29.add(b);break;
			default:
				break;
			}
		}
		jo.put("cussourcearea", ja1);
		jo.put("cusbussinesstype", ja2);
		jo.put("cuslabel", ja3);
		jo.put("cusstauts", ja4);
		jo.put("cuscourse", ja5);
		jo.put("cusreservation", ja6);
		jo.put("notcusreservation", ja7);
		jo.put("notsignreason", ja8);
		jo.put("arriveaddress", ja9);
		jo.put("arrivestatus", ja10);
		jo.put("cuseffe", ja11);
		jo.put("depttype", ja12);
		jo.put("performancetype", ja13);
		jo.put("opertype", ja14);
		jo.put("approvetype", ja15);
		jo.put("politicalstatus", ja16);
		jo.put("nationality", ja17);
		jo.put("salary", ja18);
		jo.put("maritalstatus", ja19);
		jo.put("education", ja20);
		jo.put("source", ja21);
		jo.put("custype", ja22);
		jo.put("major", ja23);
		jo.put("jobobjective", ja24);
		jo.put("paymethod", ja25);
		jo.put("bankingbook", ja26);
		jo.put("orderchangereason", ja27);
		jo.put("dropoutreason", ja28);
		jo.put("remarktype", ja29);
		return jo.toJSONString();
	}

	
	

	

}
