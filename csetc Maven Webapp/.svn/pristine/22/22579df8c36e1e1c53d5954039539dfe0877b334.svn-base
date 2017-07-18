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
    <title>导入数据失败</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  
  <body onload="geterrorinfo()">
	<p id="errinfo"></p>
	<script type="text/javascript" src="<%=basePath%>myjs/customerinfo.js"></script>
	<script type="text/javascript">
		function geterrorinfo(){
			//window.opener.document.getElementById("userid_data");
			document.getElementById("errinfo").innerText = (window.opener.document.getElementById("uploaderrorinfo").value);
		}
	</script>
  </body>
</html>
