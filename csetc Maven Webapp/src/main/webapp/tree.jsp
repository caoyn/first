<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 
 * 文件名: index.js </br>
 * 描述:新建部门页面</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-03-21 </br> -->
<!DOCTYPE HTML>
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="StyleSheet" href="css/dtree.css" type="text/css"/>
	<script type="text/javascript" src="js/wtree.js"></script>

  </head>
  
  <body>
  
<div id="systree">
</div>
  <!-- <center>
	<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
	<input type="button" value="确定" onclick="sel()" />　
	<input type="button" value="关闭" onclick="window.close()" />
</center> -->
    <script type="text/javascript">
	function sel(){
		var selids=d.getCheckedNodes();
		//var selids1=d.getSelectedNodes();
		/* var str="";
		for(var n=0; n<selids.length; n++){
			str+=selids[n]+";";
		} */
		//alert(selids);
	}
	
	var d = new dTree('d','images/system/menu/');
// 	d.config.folderLinks=true;
// 	d.config.useCookies=true;
		d.config.check=true;
		
	 	d.add(20170327000001,-1,'首页',"javascript:;",'提示');		
	 	d.add(20170327000002,-1,'客户管理',"javascript:;",'提示');		
	 	d.add(20170327000003,20170327000002,'客户信息',"javascript:;",'提示');		
	 	d.add(20170329000001,-1,'管理员管理',"javascript:;",'提示');		
	 	d.add(20170329000002,20170329000001,'角色管理',"javascript:;",'提示');		
	 	d.add(20170329000003,20170329000001,'用户管理',"javascript:;",'提示');		
	 	d.add(20170329000004,20170329000001,'权限管理',"javascript:;",'提示');		
	 	d.add(20170329000005,20170329000001,' 基本数据管理',"javascript:;",'提示');		
	 	d.add(1,20170327000001,'add',"javascript:;",'提示');		
	 	d.add(1,20170327000003,'add',"javascript:;",'提示');		
	 	d.add(2,20170327000003,'upd',"javascript:;",'提示');		
	 	d.add(3,20170327000003,'del',"javascript:;",'提示');		
	 	d.add(4,20170327000003,'qey',"javascript:;",'提示');		
	 	d.add(1,20170329000002,'add',"javascript:;",'提示');		
	 	d.add(2,20170329000002,'upd',"javascript:;",'提示');		
	 	d.add(3,20170329000002,'del',"javascript:;",'提示');		
	 	d.add(4,20170329000002,'qey',"javascript:;",'提示');		
	 	d.add(1,20170329000003,'add',"javascript:;",'提示');		
	 	d.add(2,20170329000003,'upd',"javascript:;",'提示');		
	 	d.add(3,20170329000003,'del',"javascript:;",'提示');		
	 	d.add(4,20170329000003,'qey',"javascript:;",'提示');		
	 	d.add(1,20170329000004,'add',"javascript:;",'提示');		
	 	d.add(2,20170329000004,'upd',"javascript:;",'提示');		
	 	d.add(3,20170329000004,'del',"javascript:;",'提示');		
	 	d.add(4,20170329000004,'qey',"javascript:;",'提示');		
	 	d.add(1,20170329000005,'add',"javascript:;",'提示');		
	 	d.add(2,20170329000005,'upd',"javascript:;",'提示');		
	 	d.add(3,20170329000005,'del',"javascript:;",'提示');		
	 	d.add(4,20170329000005,'qey',"javascript:;",'提示');		
	/* 	d.add(0,-1,'系统菜单',"javascript:;",'提示');		
		
	
		d.add(100,0,'系统管理',"javascript:;",'所有系统管理功能');
	
		d.add(790,100,'菜单管理',"javascript:;",'菜单管理');
	
		d.add(800,100,'组织机构',"javascript:;",'');
	
		d.add(810,100,'用户管理',"javascript:;",'');
		
		
		
		d.add(102,0,'系统机构',"javascript:;",'');
	
		d.add(300,102,'人员管理',"javascript:;",'');
	
		d.add(301,102,'部门管理',"javascript:;",'');
		
		d.add(302,102,'机构管理',"javascript:;",'');
	
		d.add(103,0,'权限管理',"javascript:;",'');
	
		d.add(400,103,'角色维护',"javascript:;",'');
	
		d.add(401,103,'功能分配',"javascript:;",'');
	
		d.add(402,103,'角色分配',"javascript:;",'');
		
		d.add(3,810,'用户名',"javascript:;",''); */

	document.getElementById('systree').innerHTML = d;
//	var funcs = eval("("+"{funcs:[{menudm:'0'},{menudm:'100'},{menudm:'790'},{menudm:'800'},{menudm:'810'}]}"+")");
//	for(var n=0; n<funcs.funcs.length;n++){
//		d.co(funcs.funcs[n].menudm).checked=true;
//	}

</script>
    
  </body>
</html>
