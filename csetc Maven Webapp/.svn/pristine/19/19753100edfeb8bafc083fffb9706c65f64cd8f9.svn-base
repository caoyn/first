<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: role.jsp </br>
 * 描述:角色管理</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-06-05 </br> 
 -->
<!DOCTYPE HTML >
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
<link rel="<%=basePath%>shortcut icon" href="favicon.ico"> 
<link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min93e3.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">





</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <!-- 角色管理   -->
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                                <div id="roletoolbar">
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="addRoleShow()"><i class="fa fa-plus"></i>新增</button></span>
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="updRoleModalShow()"><i class="fa fa-pencil"></i>授权</button></span>
<!--                                     <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-warning del" onclick="delDeptData()"><i class="fa fa-remove"></i>删除</button></span> -->
                                </div>
                                <h2>角色管理</h2>
                            <div class="">
                                <div  class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                             <!-- 动态生成表格（审批流程数据） -->
                                         	<table id="allRoleData" class="table-bordered"></table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </div>
</div>


<!-- 角色授权弹出框    -->
<div class="modal inmodal" id="operpermissionModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">角色授权</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form class="form-horizontal" id="operpermissionForm" enctype="multipart/form-data">
                           <!-- <div class="form-group">
                              <label class="col-sm-4 control-label">审批流名称：</label>
                              <div class="col-sm-6">
                                   <input type="text" name="flowname" class="form-control input-sm">
                              </div>
                           </div> -->
                           <input type="hidden" id="roleidstr">
                           <div id="operpermissionTree"></div>
                           
                        </form>
                    </div>
                   </div>
                 </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <button type="button" class="btn btn-primary" onclick="rolePermission()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 角色授权弹出框结束   -->  
<!-- 角色详情弹出框      -->
<div class="modal inmodal" id="roleModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">角色详情</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form class="form-horizontal" id="roleForm" enctype="application/x-www-form-urlencoded">
                           <div class="form-group">
                              <label class="col-sm-4 control-label">角色名称：</label>
                              <div class="col-sm-6">
                                   <input type="text" name="rolename" class="form-control input-sm" required="required">
                              </div>
                           </div>
                           <div class="form-group">
                              <label class="col-sm-4 control-label">角色状态：</label>
                              <div class="col-sm-6">
                                   <div class="radio radio-info radio-inline">
                                       <input type="radio" id="inlineRadio1" value="1" name="status" checked="">
                                       <label for="inlineRadio1"> 启用 </label>
                                   </div>
                                   <div class="radio radio-inline">
                                       <input type="radio" id="inlineRadio2" value="0" name="status">
                                       <label for="inlineRadio2"> 停用 </label>
                                   </div>
                              </div>
                           </div>
                           <div class="form-group">
                              <label class="col-sm-4 control-label">角色描述：</label>
                              <div class="col-sm-6">
                                   <textarea rows="" cols="" class="form-control" name="roledesc" required="required"></textarea>
                              </div>
                           </div>
                           
						</form>
                    </div>
                   </div>
                 </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 角色详情弹出框结束   -->
	
	<script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
    <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- bootstr表格插件 -->
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	
	<!-- 表单验证插件   -->
    <script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
	<!-- 树插件   -->
	<script src="<%=basePath%>js/wtree.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/select.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/role.js"></script>
</body>
</html>
