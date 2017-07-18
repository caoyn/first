package com.icss.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icss.bean.TbSysLog;
import com.icss.bean.TbSysUser;
import com.icss.bean.TbSysUserupd;
import com.icss.bean.TreeNode;
import com.icss.service.SysUserService;
import com.icss.util.GetMd5;
import com.icss.util.SysControllerLog;
@Controller("SysUserController")
@RequestMapping("/SysUser")
public class SysUserController {

	@Autowired
	private SysUserService SysUserImpl;
	
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("loginout.do")
	@SysControllerLog(requestoper="exit",permid="退出")
	public String loginout(HttpSession session){
		session.invalidate();//销毁session
		return "redirect:/login.jsp";
	}
	
	/**
	 * 用户管理
	 * @return
	 */
	@RequestMapping("user.do")
	public String user(){
		return "admin/user";
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="Login.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000001")
	//dda33298bf2f6b201732aaba9d8850fa gxza@b6u&5
	public @ResponseBody String SysUserLogin(HttpServletRequest request,HttpSession session){
		System.out.println("---------");
		String loginname=request.getParameter("username").trim();
		String userpassword= GetMd5.md5(request.getParameter("password"));
		System.out.println(loginname+"123456"+userpassword);
		List<TbSysUser> list=SysUserImpl.selectByloginname(loginname);
		Date date =new Date(System.currentTimeMillis());//系統時間
		TbSysUser SysUser=new TbSysUser();
		JSONObject  jsonArray=new JSONObject();
		if(list.size()==1){//有一條信息
				String pwd=null;
				Date passexpire=null;
			//	Date lastlogindtime=null;
				Integer logincount=0; 
				Integer loginfalledcount=0;
				Integer Status=0;
				String username=null;
				String userid=null;
				String id=null;
				pwd=list.get(0).getUserpassword();
				logincount=list.get(0).getLogincount();
				loginfalledcount=list.get(0).getLoginfailedcount();
				passexpire=list.get(0).getPassexpire();
				username=list.get(0).getUsername();
				userid=list.get(0).getUserid();
				id=list.get(0).getId();
				Status=list.get(0).getStatus();
				System.out.println(passexpire);
				System.out.println(date);
				if(passexpire.getTime()<=date.getTime()){//密碼過期時間與現在時間對比；過期時間如果小於現在時間就不能登錄。
					SysUser.setStatus(0);
					SysUser.setId(id);
					System.out.println("密码过期了");
					SysUserImpl.updateByPrimaryKeySelective(SysUser);
					jsonArray.put("status", "guoqi");
					return jsonArray.toString();
				}
				if(Status!=1){//只有狀態為1的情況下才能登錄，其他的0和2都都不能。
					System.out.println("用户被禁了");
					jsonArray.put("status", "error");
					return jsonArray.toString();
				}
				if(userpassword.equals(pwd)){	//登录成功				
					System.out.println(userid);
					session.setAttribute("userid", userid);
					session.setAttribute("username", username);
					//查询该用户对应的角色名称
					session.setAttribute("rolename", SysUserImpl.getRolenameByUserid(userid));
					logincount++;//成功次數自增
					SysUser.setLogincount(logincount);//更新登錄成功次數
					SysUser.setLastlogintime(new Date());//更新最近登錄時間
					SysUser.setId(id);
					SysUser.setLoginfailedcount(0);
					SysUserImpl.updateByPrimaryKeySelective(SysUser);
					jsonArray.put("username", username);//返回用戶名
					jsonArray.put("userid", userid);//返回用戶id
					jsonArray.put("status", "success");
					jsonArray.put("passtype", list.get(0).getPasstype());
					return jsonArray.toString();
				}else{
					loginfalledcount++;//失敗次數自增
					if(loginfalledcount==1){//判斷失敗次數來提醒相關信息;
						SysUser.setLoginfailedcount(loginfalledcount);
						SysUser.setId(id);
						SysUserImpl.updateByPrimaryKeySelective(SysUser);
						System.out.println(pwd+"1------"+userpassword);
						jsonArray.put("status", "error1");
						return jsonArray.toString();
					}
					if(loginfalledcount==2){//當失敗次數大於或等與2的時候鎖定。
						SysUser.setLoginfailedcount(loginfalledcount);
						SysUser.setId(id);
						SysUserImpl.updateByPrimaryKeySelective(SysUser);
						System.out.println(pwd+"2------"+userpassword);
						jsonArray.put("status", "error2");
						return jsonArray.toString();
					}					
					if(loginfalledcount>=3){//當失敗次數大於或等與2的時候鎖定。
						SysUser.setLoginfailedcount(loginfalledcount);
						SysUser.setId(id);
						SysUser.setStatus(0);
						SysUserImpl.updateByPrimaryKeySelective(SysUser);
						System.out.println(pwd+"3------"+userpassword);
						jsonArray.put("status", "error3");
						return jsonArray.toString();
					}					
					
					
				}
		}else{
			jsonArray.put("status", "fail");
			return jsonArray.toString();
		}
		return null;
	}
	
	/**
	 * 逻辑删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="delete.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	public @ResponseBody String delete(HttpServletRequest request){
		TbSysUser SysUser=new TbSysUser();
		String id=request.getParameter("id");
		int status = Integer.parseInt(request.getParameter("status"));
		SysUser.setId(id);
		SysUser.setStatus(status);//邏輯刪除,并不是真正意義的刪除。
		if(status == 2){//禁用时用户离职
			TbSysUserupd userupd = new TbSysUserupd();
			userupd.setUserid(request.getParameter("userid"));
			userupd.setUpdtype("2");
			String memo =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			userupd.setMemo(memo + " 离职");
			SysUserImpl.userchange(userupd);
		}
		return SysUserImpl.updateByPrimaryKeySelective(SysUser)+"";
	}



	/**
	 * 更改密码
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("updatepwd.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	@ResponseBody
	public String updatepwd(){
		 int len=10;
		 String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz&$@";
		 String rtnStr = "";
		 TbSysUser SysUser=new TbSysUser();
		 JSONObject  jsonArray=new JSONObject();
		 Date date =new Date(System.currentTimeMillis());
		 for (int i = 0; i < len; i++) {
		        //循环随机获得当次字符，并移走选出的字符
		        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
		        rtnStr += nowStr;
		        generateSource = generateSource.replaceAll(nowStr, "");
		  }
		 SysUser.setUserpassword(rtnStr);
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(cal.DAY_OF_MONTH, 90);
	  	 date= cal.getTime();
	  	 SysUser.setPassexpire(date);//設置新的密碼過期時間
         SysUser.setUserpassword(rtnStr);//設置新的密碼；
         SysUser.setPasstype("1");//设置为临时密码
         SysUser.setStatus(1);//设置用户启用
         SysUserImpl.updateByPrimaryKeySelective(SysUser);
		 jsonArray.put("pwd", rtnStr);
		 return jsonArray.toString();
	}
	/**
	 * 查询所有员工
	 * @return
	 */
	@RequestMapping("selectAll.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	@ResponseBody
	public String selectall(){
		List<TbSysUser> list=SysUserImpl.selectAll();
		JSONArray jsonObject=JSONArray.fromObject(list);
		return jsonObject.toString();
	}

	
	/**
	 * 查询单个员工信息
	 * @param request
	 * @return
	 */
	@RequestMapping("selectone.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	@ResponseBody
	public String selectone(HttpServletRequest request){
		String id=request.getParameter("id"); 
		TbSysUser SysUser=SysUserImpl.selectByPrimaryKey(id);
		JSONArray jsonObject=JSONArray.fromObject(SysUser);
		return jsonObject.toString();

	}
	/**
	 * 修改单个员工信息
	 * @param SysUser
	 * @return
	 */
	@RequestMapping("updateone.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	public @ResponseBody String updateone(@ModelAttribute("u") TbSysUser SysUser,HttpServletRequest request ){
		
		SysUser.setDeptid(request.getParameter("deptid"));
		SysUserImpl.updateByPrimaryKeySelective(SysUser);
		JSONObject  jsonArray=new JSONObject();
		jsonArray.put("status", "success");
		//如果部门发生的改变需要新增员工异动信息
		String oldDeptid = request.getParameter("oldDeptid");
		System.out.println(oldDeptid);
		if(oldDeptid == null || oldDeptid == "null"){
			return jsonArray.toString();
		}else if(!oldDeptid.equals(SysUser.getDeptid())){
			//新增员工异动信息
			TbSysUserupd userupd = new TbSysUserupd();
			userupd.setUserid(SysUser.getUserid());
			userupd.setUpdtype("3");
			String memo =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			userupd.setMemo(memo+ " 由 "+oldDeptid +": "+ request.getParameter("oldDeptname") +"转至  "+SysUser.getDeptid() +": "+ request.getParameter("deptname"));
			SysUserImpl.userchange(userupd);
		}
		return jsonArray.toString();
		
	}
	/**
	 * 添加用户
	 * @param SysUser
	 * @return
	 */
	@RequestMapping("insert.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000003")
	public @ResponseBody String insert(@ModelAttribute("u") TbSysUser SysUser){
		JSONObject  jsonArray=new JSONObject();
		 int len=10;
		 String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz&$@";
		 String rtnStr = "";
		 Date date =new Date(System.currentTimeMillis());
		 Date datetwo =new Date(System.currentTimeMillis());
		 for (int i = 0; i < len; i++) {
		        //循环随机获得当次字符，并移走选出的字符
		        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
		        rtnStr += nowStr;
		        generateSource = generateSource.replaceAll(nowStr, "");
		  }
		 SysUser.setUserpassword(GetMd5.md5(rtnStr));//加密后插入数据库
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(cal.DAY_OF_MONTH, 90);
	  	 date= cal.getTime();
         SysUser.setPassexpire(date);
         SysUser.setLastlogintime(datetwo);
         SysUser.setCreatetime(datetwo);
         SysUser.setId("");
         SysUserImpl.insertSelective(SysUser);//新增员工表
         //新增员工异动表
         String memo =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
         TbSysUserupd userupd = new TbSysUserupd(SysUser.getUserid(),"1",memo +" 入职");
         SysUserImpl.userUpdate(userupd);
	 	 jsonArray.put("status", "success");
	 	 jsonArray.put("password", rtnStr);
		 return jsonArray.toString();	
	}
	
	//lm 20170322 获得所有员工数据
	@RequestMapping("getAllUserData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String  getAllUserData() {
		String str = JSON.toJSONString(SysUserImpl.getAllUserData(),SerializerFeature.WriteMapNullValue);
		System.out.println(str);
		return str;
	}

	//判断某部门下是否有员工
	@RequestMapping("getUserByDeptid.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String getUserByDeptid(HttpServletRequest request){
		return SysUserImpl.getUserByDeptid(request.getParameter("id")) + "";
	}
	
	//登录成功的进入首页
	@RequestMapping("index.do")
	@SysControllerLog(requestoper="QRY",permid="20170327000001")
	public ModelAndView index(HttpSession session){
		//1.用户登录校验
		//2.页面跳转和用户导航栏数据的传递
		List<TreeNode> list = SysUserImpl.getMenuByUserid(session);
		return new ModelAndView("index/index","menu",list);
		//return "";
	}
	
	//登录成功的进入首页内容页
	@RequestMapping("content.do")
	public String content(){
		return "index/content";
	}
	
	//查看用户角色下拉框
	@RequestMapping("deptUserData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String deptUserData(){
		return JSON.toJSONString(SysUserImpl.deptUserData());
	}
	
	/*
	 *  查询所有用户对应的角色
	 */
	@RequestMapping("allUserRole.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	@ResponseBody
	public String allUserRole(){
		List<TbSysUser> list = SysUserImpl.allUserRole();
		return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue);		
	}
	
	//校验有无重复的数据
	@RequestMapping("checkRepect.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String checkRepect(HttpServletRequest request){
		return SysUserImpl.checkRepect(request) + "";
	}
	
	//重置密码
	@RequestMapping("resetPwd.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	public @ResponseBody String resetPwd(HttpServletRequest request){
		return SysUserImpl.resetPwd(request);
	}
	
	//员工异动页面展示
	@RequestMapping("updjob.do")
	public String updjob(){
		return "admin/userupd";
	}
	
	//员工异动数据
	@RequestMapping("userUpdData.do")
	@SysControllerLog(requestoper="ADD",permid="20170329000009")
	public @ResponseBody String userUpdData(){
		return SysUserImpl.userUpdData();
	}

	//系统日志页面展示
	@RequestMapping("Syslog.do")
	public String Syslog(){
		return "admin/syslog";
	}
	
	//系统日志数据
	@RequestMapping("getLogData.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000010")
	public @ResponseBody Map<String,Object> getLogData(int pageSize,int pageNumber,String username){
		TbSysLog sysLog = new TbSysLog();
		sysLog.setId((pageNumber-1)*pageSize+"");
		sysLog.setPermid(pageSize+"");
		sysLog.setUsername(username);
 		return SysUserImpl.getLogData(sysLog);
	}
	
	//个人资料页面跳转
	@RequestMapping("profile.do")
	public String profile(){
		return "index/profile";
	}
	
	//修改密码
	@RequestMapping("updPwd.do")
	@SysControllerLog(requestoper="UPD",permid="20170329000003")
	public @ResponseBody String updPwd(HttpSession session,HttpServletRequest request){
		return SysUserImpl.updPwd(session, request);
	}
	
	//个人资料数据
	@RequestMapping("userdata.do")
	@SysControllerLog(requestoper="QRY",permid="20170329000003")
	public @ResponseBody String userdata(HttpSession session){
		return SysUserImpl.userdata(session);
	}
	
}
