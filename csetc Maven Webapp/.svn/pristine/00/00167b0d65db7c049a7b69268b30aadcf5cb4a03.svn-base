<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: profile.jsp </br>
 * 描述:个人资料页面</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-06-14 </br> 
 -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>首页</title>

    <meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
    <meta name="description" content="湖南中软计算机系统服务有限公司OA系统">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    
    <link href="<%=basePath%>css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath%>css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    
	<!-- 通知插件 -->
	<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>个人资料</h5>
                    </div>
                    <div class="ibox-content" id="userdatainfo">
                    	
                    </div>  
                       
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>修改密码</h5>
                        <div class="ibox-tools">
                            
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<form class="form-horizontal" id="updPwdForm" autocomplete="weyrt">
                    		<div class="form-group">
								<label class="col-sm-4 control-label">原密码：</label>
								<div class="col-sm-6">
									<input type="password" class="form-control input-sm" name="oldPwd" autocomplete="new-password"/> 
								</div>
							</div>
                    		<div class="form-group">
								<label class="col-sm-4 control-label">新密码：</label>
								<div class="col-sm-6">
									<input type="password" class="form-control input-sm" name="userpassword" autocomplete="off"/> 
								</div>
							</div>
                    		<div class="form-group">
								<label class="col-sm-4 control-label">确认密码：</label>
								<div class="col-sm-6">
									<input type="password" class="form-control input-sm" name="password" autocomplete="off" /> 
								</div>
							</div>
                    		<div class="text-right">
	                            <input type="reset" class="btn btn-default" value="重置" />
	                            <input type="button" class="btn btn-primary" value="确定" onclick="updPassword()" />
	                           
	                        </div>
                    	</form>
                        
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script src="<%=basePath%>js/jquery.min.js?"></script>
    <script src="<%=basePath%>js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath%>js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/plugins/layer/layer.min.js"></script>
    <script src="<%=basePath%>js/hplus.min.js?v=4.1.0"></script>
    <script src="<%=basePath%>js/contabs.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/plugins/pace/pace.min.js"></script>
  	
  	<script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
  	
  	<script src="<%=basePath%>myjs/select.js"></script>
  	<script src="<%=basePath%>myjs/profile.js"></script>
</body>


</html>
