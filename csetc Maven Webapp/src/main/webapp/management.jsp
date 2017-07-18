<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理</title>
<link rel="StyleSheet" href="css/dtree.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/zTreeStyle.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/wtree.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="<%=basePath%>myjs/management.js"></script>
<script type="text/javascript"></script>
<body>
	<h1>权限管理</h1>
	<div>
	   <ul id="permissionTree" class="ztree"></ul>
	</div>
	<p>********************************</p>
	<h1>用户管理</h1>
	<table id="userTable" border="1"></table>
	<form id="operid">
	<input type="hidden" name="userid" >
		选择角色：<select name="roleid"></select>
		<div id="userOperPermissionTree"></div><!-- 模块与操作的展示 -->
		<table id="selectOneRolePermission">
			<thead>
				<tr>
					<th></th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<input type="button" value="确定" onclick="addUserRolePermission()">
	</form>
	
	<h2><a onclick="getAllUser()">查看用户权限</a></h2>
	<table id="selectAllUser" border="1">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>角色名称</th>
				<th>权限</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<p>********************************</p>
	
	<!-- *************************************************我是分割线*********************************************************** -->

	<input type="hidden" id="roleidstr" value="">
	<h1>角色管理</h1>
	<form id="b">
		<h2 onclick="allOperpermission()">新建角色</h2><h2><a onclick="getAllRole()">查看角色</a></h2>
		角色名称：xx,角色描述：xx，启用状态xx
		<h2>为角色增加功能模块权限</h2>
		<div id="permissions"></div><!-- 功能模块复选框 -->
		<div id="operpermissionTree"></div><!-- 模块与操作的展示 -->
		<input type="button" value="确定" onclick="b()">
	</form>
	<table id="selectAllRole" border="1">
		<thead>
			<tr>
				<th>角色名称</th>
				<th>角色权限</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="operpermissionTree1"></div>
	<form id="editRolePermission"></form>
	
	
</body>
</html>