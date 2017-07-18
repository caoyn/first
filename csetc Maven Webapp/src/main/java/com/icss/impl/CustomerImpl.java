/**
 * 文件名: SysApproveImpl.java
 * 描述:审批管理接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-13
 */
package com.icss.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icss.bean.TbCustomer;
import com.icss.bean.TbSysBasecode;
import com.icss.bean.TbSysUser;
import com.icss.bean.TbSysUserroledetail;
import com.icss.dao.TbCustomerMapper;
import com.icss.dao.TbSysBasecodeMapper;
import com.icss.dao.TbSysUserMapper;
import com.icss.dao.TbSysUserroledetailMapper;
import com.icss.service.CustomerService;
import com.icss.util.FileUpload;
import com.icss.util.ReadExcel;


@Service("CustomerImpl")
public  class CustomerImpl implements CustomerService {
	@Autowired
	private TbCustomerMapper CustomerMapper;//客户信息

	@Autowired
	public TbSysBasecodeMapper SysBasecodeMapper;//基础代码数据
	
	@Autowired
	public TbSysUserMapper SysUserMapper;//员工数据
	
	@Autowired
	public TbSysUserroledetailMapper SysUserroledetailMapper;//用户角色关联
	
	@Override
	public int addOneCustomerInfo(TbCustomer customer) {
		// 新增单个客户信息
		return CustomerMapper.insertSelective(customer);
	}

	@Override
	public List<TbCustomer> checkPresenceExists(HttpServletRequest request) {
		// 检查电话号码是否存在
		return CustomerMapper.checkPresenceExists(request.getParameter("telphone"));
	}

	@Override
	public List<TbCustomer> customerinfo(HttpSession session ,HttpServletRequest request) {
		// 查看用户id
		String userid = (String) session.getAttribute("userid");
		System.out.println(userid);
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170327000003");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		System.out.println(ur.size());
		List<TbCustomer> clist = new ArrayList<TbCustomer>();
		TbCustomer c = new TbCustomer();
		c.setCollector(userid);
		c.setCustomerid("0");
		for (TbSysUserroledetail tbSysUserroledetail : ur) {
			//System.out.println(tbSysUserroledetail.getRoleid());
			if("20170327000001".equals(tbSysUserroledetail.getRoleid())){
//				System.out.println("这是最高管理员角色，可以查看所有数据");
//				System.out.println("页面上可以执行的擦操作："+ tbSysUserroledetail.getOperid());
				clist = CustomerMapper.getAllCustomerinfo("0");
				//clist1 = CustomerMapper.getAllCustomerinfo1("0");
				break;
			}else if("20170327000002".equals(tbSysUserroledetail.getRoleid())){
//				System.out.println("这是二级管理员角色，可以查看指定部门所有数据数据");
				//获得该角色能够看的部门数据
				c.setCollector("01,0102,010301");
				clist = CustomerMapper.getSecondCustomerinfo(c);
				//clist1 = CustomerMapper.getSecondCustomerinfo1(c);
				break;
				
			}else if("20170327000010".equals(tbSysUserroledetail.getRoleid()) || "20170327000003".equals(tbSysUserroledetail.getRoleid()) || "20170327000005".equals(tbSysUserroledetail.getRoleid())){
//				System.out.println("这里是部门主管的角色。可以查看自己部门的所有数据");
				clist = CustomerMapper.getDeptCustomerinfo(c);
				//clist1 = CustomerMapper.getDeptCustomerinfo1(c);
				break;
			}else{
				clist = CustomerMapper.getCustomerinfo(c);
				//clist1 = CustomerMapper.getCustomerinfo1(c);
				System.out.println("这里是普通员工或者其他部门员工的角色，查看自己的数据");
				
			}
		}
		//将两集合关联起来
		/*for (TbCustomer tbCustomer : clist) {
			for (TbCustomer customer : clist1) {
				if(tbCustomer.getId().equals(customer.getId())){
					tbCustomer.setSalarytext(customer.getSalarytext());
					tbCustomer.setNayionalitytext(customer.getNayionalitytext());
					tbCustomer.setWorkplacetext(customer.getWorkplacetext());
					break;
				}
			}
		}*/
		System.out.println(session.getAttribute("operstr"));
		return clist;
	}

	@Override
	public String batchImportCustomer(MultipartFile mFile,HttpServletRequest request,HttpSession session) {
		// 保存上传的文件
		
		String pathAndResult = FileUpload.uploadFile(request, mFile);
		//System.out.println(pathAndResult.substring(0, pathAndResult.indexOf(";")));
		if( "1".equals(pathAndResult.substring(0, pathAndResult.indexOf(";")))){//为1代表文件已经保存到服务器了
			String path = pathAndResult.substring( pathAndResult.indexOf(";")+1,pathAndResult.length());//获得文件存放路径
			ReadExcel xlsMain = new ReadExcel();
			try {
				List<TbCustomer> ListResult =xlsMain.ReadInExcel(path,session);//读取并判断表格数据
				if(ListResult.get(0).getCustomerid() != "" && ListResult.get(0).getCustomerid() != null){//文件中含有重复的电话号码
					return "e:发生错误;" + JSON.toJSONString(ListResult);
				}else{
					System.out.println("表格数据正确");
					//判断与数据中是否有重复的电话号码
					List<String> list = new ArrayList<String>();
					Iterator<TbCustomer> it = ListResult.iterator();
					List<String> listcount = new ArrayList<String>();
					while(it.hasNext()){
						list.add(it.next().getTelephone());
					}
					listcount = CustomerMapper.repeatdata(list);
					if(CustomerMapper.repeatdata(list).size()>0){
						for (int j = 0; j < listcount.size(); j++) {
							for (int i = 0; i < ListResult.size(); i++) {
								if(ListResult.get(i).getTelephone().equals(listcount.get(j))){
									listcount.set(j, (i+2)+"行:"+list.get(j));
//									listcount.add((i+2)+"行:"+list.get(j)); 
									break;
								}
							}
						}
						return "e:有重复的电话号码，请仔细核对以下数据 ;" + JSON.toJSONString(listcount);
					}else{
						//存入数据库
						for(int i=0;i<ListResult.size();i++){
							TbCustomer cus = ListResult.get(i);
							cus.setCustomerid("1");
							CustomerMapper.insertSelective(dealCusData(cus));
						}
						return "s:数据导入成功，共计   "+ListResult.size()+" 条数据";
					}
				}
				
			} catch (Exception e) {
				// 报异常
				e.printStackTrace();
				return "e:导入的文件不是excel文件！;"+ e.getMessage();
			}
		}else{
			return "e:上传文件失败!请先选择文件再上传";
		}
	}
	
	public TbCustomer dealCusData(TbCustomer c){
		//处理采集人数据
		boolean f = true;
		List<TbSysUser> collectors = SysUserMapper.getAllUserData();
		for (TbSysUser tbSysUser : collectors) {
			if(c.getCollector().equals(tbSysUser.getUsername())){
				c.setCollector(tbSysUser.getUserid());
				f = false;
				break;
			}
		}
		if(f){
			c.setCollector(c.getOperator());//默认采集人为操作人
		}
		
		//判断文件中各个可以选择的数据的正确性
		String[] strArray = {"01","22","05","16","17","18","19","20","21","23","24"};
		boolean[] flag = new boolean[strArray.length];
		//处理基础代码数据
		for(int i = 0; i < strArray.length; i++){
			flag[i] = true;
			List<TbSysBasecode> l = SysBasecodeMapper.getSmallTypeOfBigType(strArray[i]);//基础代码数据
			for (TbSysBasecode tbSysBasecode : l) {
				String l2name = tbSysBasecode.getLevel2name();
				String l2id = tbSysBasecode.getLevel2id();
				if(i == 0){
					if (c.getJobobjective().equals(l2name)) {
						c.setJobobjective(l2id);
						flag[i] = false;
						break;
					} 
				}
				if(i == 1){
					if (c.getCustomertype().equals(l2name)) {
						c.setCustomertype(l2id);
						flag[i] = false;
						break;
					} 
				}
				if(i == 2){
					if (c.getCourse().equals(l2name)) {
						c.setCourse(l2id);
						flag[i] = false;
						break;
					}
				}
				if(i == 3){
					if (c.getPoliticalstatus().equals(l2name)) {
						c.setPoliticalstatus(l2id);
						flag[i] = false;
						break;
					}
				}
				if(i == 4){
					if (c.getNationality().equals(l2name)) {
						c.setNationality(l2id);
						flag[i] = false;
						break;
					} 
				}
				if(i == 5){
					if (c.getSalary().equals(l2name)) {
						c.setSalary(l2id);
						flag[i] = false;
						break;
					}
				}
				if(i == 6){
					if (c.getMaritalstatus().equals(l2name)) {
						c.setMaritalstatus(l2id);
						flag[i] = false;
						break;
					} 
				}
				if(i == 7){
					if (c.getEducation().equals(l2name)) {
						c.setEducation(l2id);
						flag[i] = false;
						break;
					} 
				}
				if(i == 8){
					if (c.getSource().equals(l2name)) {
						c.setSource(l2id);
						flag[i] = false;
						break;
					}
				}
				/*if(i == 9){
					if (c.getMajor().equals(l2name)) {
						c.setMajor(l2id);
						flag[i] = false;
						break;
					}
				}*/
				if(i == 10){
					if (c.getJobobjective().equals(l2name)) {
						c.setJobobjective(l2id);
						flag[i] = false;
						break;
					} 
				}
			}
		}
		
		//处理默认值
		for(int i = 0; i < flag.length; i++){
			if(i == 0){
				if (flag[i]) {
					c.setWorkplace("1");
				} 
			}
			if(i == 1){
				if (flag[i]) {
					c.setCustomertype("1");
				} 
			}
			if(i == 2){
				if (flag[i]) {
					c.setCourse("1");
				}
			}
			if(i == 3){
				if (flag[i]) {
					c.setPoliticalstatus("1");
				}
			}
			if(i == 4){
				if (flag[i]) {
					c.setNationality("1");
				} 
			}
			if(i == 5){
				if (flag[i]) {
					c.setSalary("1");
				}
			}
			if(i == 6){
				if (flag[i]) {
					c.setMaritalstatus("1");
				} 
			}
			if(i == 7){
				if (flag[i]) {
					c.setEducation("1");
				} 
			}
			if(i == 8){
				if (flag[i]) {
					c.setSource("12");
				}
			}
			/*if(i == 9){
				if (flag[i]) {
					c.setMajor("1");
				}
			}*/
			if(i == 10){
				if (flag[i]) {
					c.setJobobjective("1");
				}
			}
		}
		return c;
	}

	@Override
	public int updCustomerinfo(TbCustomer customer) {
		// 更新客户信息
		return CustomerMapper.updateByPrimaryKeySelective(customer);
	}

	@Override
	public void operbtn(HttpSession session) {
		// 可操作的按钮
		String userid = (String) session.getAttribute("userid");
		// 根据角色查看与之对应的客户数据
	    TbSysUserroledetail ud = new TbSysUserroledetail();
	    ud.setUserid(userid);
	    ud.setPermissionid("20170327000003");
		List<TbSysUserroledetail> ur = SysUserroledetailMapper.userRoleDetailData(ud);
		session.setAttribute("operstr", ur.get(0).getOperid());
	}

	@Override
	public String condition(TbCustomer customer) {
		// 带条件查询客户数据
		System.out.println(customer);
		return JSON.toJSONString(CustomerMapper.condition(customer));
	}

	@Override
	public String qrySeeDataCount(HttpSession session) {
		// 信息专员查看数据统计
		String eid = (String) session.getAttribute("userid");
		/*List<TbCustomer> sourcelist = CustomerMapper.sourcecount(eid);
		List<TbCustomer> sexlist = CustomerMapper.sexcount(eid);
		List<TbCustomer> courselist = CustomerMapper.coursecount(eid);*/
		JSONObject jo = new JSONObject();
		jo.put("sourcelist", CustomerMapper.sourcecount(eid));
		jo.put("sexlist", CustomerMapper.sexcount(eid));
		jo.put("courselist", CustomerMapper.coursecount(eid));
		jo.put("userlist", CustomerMapper.usercount(eid));
		return jo.toJSONString();
	}

	@Override
	public String datamate(HttpSession session) {
		// 查看没有传简历的数据
		String eid = (String) session.getAttribute("userid");
 		return JSON.toJSONString(CustomerMapper.datamate(eid));
	}

	@Override
	public String resumeurl(TbCustomer customer) {
		// 添加简历照片url
		return CustomerMapper.resumeUrl(customer) +"";
	}

	@Override
	public String teamData(HttpSession session) {
		// 查看团队数据
//		SysUserMapper.getDeptidByUserid((String) session.getAttribute("userid"))
		return JSON.toJSONString(CustomerMapper.teamData(SysUserMapper.getDeptidByUserid((String) session.getAttribute("userid"))));
	} 


}










