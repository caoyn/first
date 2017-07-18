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
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/dept.js"></script>
  </head>
  
  <body>
  	<h2><a href="MyJsp.jsp">基础数据管理</a></h2>
  	<h2><a href="management.jsp">新增用户</a></h2>
  	<form enctype="application/x-www-form-urlencoded" id="addBasecodeForm">
  		
  		 
  		大类名称*：<input type="hidden" name="level1name" value="">
  				<select name="level1id"> </select> 
  				<a id="addlink" onclick="changeHtml()"> + 新增　</a> <br/>
  		小类名称*：<input type="text" name="level2name"><br/>
  		小类值：<input type="text" placeholder="没有不用填" name="value"><br/>
  		  状态：<input type="radio" name="status" value="1" checked="checked"/>启用  <input type="radio" name="status" value="0"/>禁用<br/>
    	<input type="button" value="确定" onclick="ADDbasecode()">
  	</form>
  
  
    <h2>查看所有部门</h2>   
    <div id="alldept"></div>  
    <h2>新建部门</h2>     
    <form enctype="application/x-www-form-urlencoded" id="addDeptForm">    	
		  是否为根级部门：<input type="radio" name="deptlevel" value="0" checked="checked"/>是  <input type="radio" name="deptlevel" value="1"/>否 <br/>
		   部门名称：<input type="text" name="deptname"/><br/>    
		  选择上级部门：
		  <select name="topdeptid">
		  	<option value="">无</option>
		  </select><br/>
		  业绩核算类型：
		  <select name="performance">		  	
		  </select><br/>
	           部门负责人:
	      <select name="userid">	      	
		  </select><br/>
		  部门类型：
		  <select name="depttype">
		  	
		  </select><br/>
		  状态：<input type="radio" name="status" value="1" checked="checked"/>启用  <input type="radio" name="status" value="0"/>禁用<br/>
    	<input type="button" value="确定" onclick="ADDdept()">
    </form>
    
  </body>
</html>
