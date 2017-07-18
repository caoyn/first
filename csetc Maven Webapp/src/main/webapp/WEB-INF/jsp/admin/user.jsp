<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>湖南中软计算机系统服务有限公司OA系统</title>
 <meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
 <meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
 <link rel="shortcut icon" href="favicon.ico"> 
 <link href="css/bootstrap.min14ed.css" rel="stylesheet">
 <link href="css/font-awesome.min93e3.css" rel="stylesheet">
 <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
 <link href="css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
 
 <!-- 通知插件 -->
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

 <link href="css/animate.min.css" rel="stylesheet">
 <link href="css/style.min862f.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <!-- 用户管理 -->
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                        	<div id="userToolbar">
                        		<span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="addUserShow()"><i class="fa fa-plus"></i> 新增员工</button></span>
                                <span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="upduserinfo()"><i class="fa fa-pencil"></i> 修改信息</button></span>
                                <span class="small text-center"><button type="button" class="btn btn-xs btn-warning" onclick="givepower()"><i class="fa fa-hand-grab-o"></i> 授权</button></span>
                        	</div>
                            <h2 >用户管理</h2>
                            <div class="">
                                <div class="tab-pane active">
                                    <div class="table-responsive">
                                       </div>
                                        <!-- 动态生成表格 -->
                                         <table id="exampleTableLargeColumns">
                                         </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<!-- 用户授权弹出框-->
<div class="modal inmodal" id="powerModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">授权操作</h4>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form method="post" enctype="application/x-www-form-urlencoded" class="form-horizontal" id="powerForm">
                            	<input type="hidden" name="userid">
                                <div class="form-group" >
                                     <label class="col-sm-4 text-center" >选择角色：</label>
                                     <div class="col-sm-6">
                                     	<div class="input-group">
											<input type="text" class="form-control input-sm" id="role_data" required="required" >
											<div class="input-group-btn">
												<button type="button" class="btn btn-sm btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
											 	<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
											</div>
										</div>
                                     </div>
                                </div>
                                
                                <div class="form-group" >
                                    <label class="col-sm-4 text-center" >操作权限：</label>
                                    <div class="col-sm-8">
                                       <div id="operPermissionTree"></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addUserRolePermission()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 用户授权弹出框结束 -->
          
<!-- 修改信息的弹出框 -->
<div class="modal inmodal" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">修改信息</h4>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form enctype="application/x-www-form-urlencoded" method="post" class="form-horizontal" id="formiusernfo">
                            	<input  type="hidden" id="id" name="id" >
                            	<input  type="hidden" name="oldDeptid" >
                            	<input  type="hidden" name="oldDeptname" >
                                <div class="form-group" >
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >用户id</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control"  name="userid" id="userid" readonly="readonly">
                                        </div>
                                    </div>
                                     <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >账号名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="loginname" id="loginname">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center"  >用户姓名</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="username" id="username">
                                        </div>
                                    </div>
                                     <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >固定电话</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="telephone"  id="telephone">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >手机号码</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="mobile" id="mobile">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >联系地址</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="address" id="address">
                                        </div>
                                    </div>
                                     <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >电子邮箱</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="email" id="email">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                     <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >所属部门</label>
                                        <div class="col-sm-8">
                                        	<div class="input-group">
												<input type="text" class="form-control input-sm" name="deptname" id="upddept_data" required="required">
												<div class="input-group-btn">
													<button type="button" class="btn btn-sm btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
												 	<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
												 </div>
											</div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >用户状态</label>
                                        <div class="col-sm-8" style="margin-top: 0.6em;">
                                            <div class="radio i-checks">
                                                <label>
                                                    <input type="radio" checked="" value="1" name="status"> <i></i> 启用
                                                </label>
                                                <label>
                                                    <input type="radio" value="0" name="status"> <i></i> 锁定 
                                                </label>
                                                 <label>
                                                    <input type="radio" value="2" name="status"> <i></i> 禁用
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                    <div class="col-sm-12" >
                                        <label class="col-sm-2 text-center" >描述信息</label>
                                        <div class="col-sm-10">
                                             <textarea  rows="3" class="form-control" name="description" id="description"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="upInfo()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改信息弹出框结束 -->          
<!-- 新增用户的弹出框 -->
<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">新增用户</h4>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form class="form-horizontal" id="formdata" enctype="application/x-www-form-urlencoded" method="post">
                                <div class="form-group">
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >用户id</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="userid" required="required" onblur="checkRepect(this,3)" placeholder="请输入EHR编号，确定后不能修改">
                                        </div>
                                    </div>
                                     <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >账号名</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="loginname"  required="required" onblur="checkRepect(this,1)">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group" >
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >用户姓名</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="username" required="required" onblur="checkRepect(this,2)">
                                        </div>
                                    </div>
                                     <div class="col-sm-6">
                                        <label class="col-sm-4 text-center" >固定电话</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="telephone" required="required">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >手机号码</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="mobile" required="required">
                                        </div>
                                    </div>
                                     <div class="col-sm-6" >
                                        <label class="col-sm-4 text-center" >联系地址</label>
                                        <div class="col-sm-8">
                                             <input type="text" class="form-control" name="address" required="required">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                     <div class="col-sm-6">
                                        <label class="col-sm-4 text-center" >电子邮箱</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="email" required="required" >
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="col-sm-4 text-center" >所属部门</label>
                                        <div class="col-sm-8">
                                        	<div class="input-group">
												<input type="text" class="form-control input-sm" id="dept_data" required="required">
												<div class="input-group-btn">
													<button type="button" class="btn btn-sm btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
												 <ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
												 </div>
											</div>
										</div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-6">
                                        <label class="col-sm-4 text-center">用户状态</label>
                                        <div class="col-sm-8">
                                            <div class="radio i-checks">
                                                <label>
                                                    <input type="radio" checked="" value="1" name="status"> <i></i> 启用
                                                </label>
                                                 <label>
                                                    <input type="radio" value="0" name="status"> <i></i> 锁定   
                                                </label>
                                                <label>
                                                    <input type="radio" value="2" name="status"> <i></i> 禁用
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <label class="col-sm-2 text-center" >描述信息</label>
                                        <div class="col-sm-10">
                                             <textarea class="form-control" rows="3" name="description" id="description" required="required"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="insertdata()">保存</button>
            </div>
        </div>
    </div>
</div>
            
            
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
  
    <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- 日期插件 -->
    <script src="<%=basePath%>js/plugins/layer/laydate/laydate.js"></script>
  
    <!-- bootstr表格插件 -->
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

	<!-- 表单验证插件   -->
    <script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
    <!-- 搜索建议插件 -->
    <script src="<%=basePath%>js/plugins/suggest/bootstrap-suggest.min.js"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function(){
            $(".full-height-scroll").slimScroll({height:"100%"});
            $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
        });
    
    </script>
    <!-- 通知插件  -->
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
    <!-- 树插件   -->
	<script src="<%=basePath%>js/wtree.js"></script>
	
	<script src="<%=basePath%>myjs/sys_user.js"></script>
	
</body>

</html>