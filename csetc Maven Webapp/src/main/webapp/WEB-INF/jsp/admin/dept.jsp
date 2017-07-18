<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>湖南中软计算机系统服务有限公司OA系统</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
<link rel="<%=basePath%>shortcut icon" href="favicon.ico"> 
<link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">
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
            <!-- 部门管理 -->
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                                <div class="pull-right">
<!--                                     <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-success" onclick="authorize()"><i class="fa fa-shield"></i>授权</button></span> -->
                                    <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-primary" onclick="ADDdeptShow()"><i class="fa fa-plus"></i>新增</button></span>
                                    <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-info" onclick="updDeptModalShow()"><i class="fa fa-pencil"></i>修改</button></span>
                                    <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-warning del" onclick="delDeptData()"><i class="fa fa-remove"></i>删除</button></span>
                                </div>
                                <h2 >部门管理</h2>
                            <div class="clients-list">
                                <div  class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                            <!-- 动态生成部门树 -->
                                            <div id="treeview12" class="test"></div>
                                        </div>
                                        <div id="yourChoose">
                                            
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
<!-- 新增部门的弹出框 -->
<div class="modal inmodal" id="addDeptModal" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated fadeIn">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增部门信息</h4>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-content">
							<form id="addDeptForm" class="form-horizontal">
								<input type="reset" style="display:none;" />
								<div class="form-group">
									<label class="col-sm-3 control-label">上级部门：</label>
									<div class="col-sm-8">
										<select class="form-control m-b" name="topdeptid">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">部门名称：</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="deptname" required="required">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">业绩核算类型：</label>
									<div class="col-sm-8">
										<select class="form-control" name="performance"></select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">部门负责人：</label>
									<div class="col-sm-8">
										<div class="row">
											<div class="col-lg-6">
												<div class="input-group">
													<input type="text" class="form-control" id="deptUser_data"
														name="username" required="required">
													<div class="input-group-btn">
														<button type="button"
															class="btn btn-white dropdown-toggle"
															data-toggle="dropdown">
															<span class="caret"></span>
														</button>
														<ul class="dropdown-menu dropdown-menu-right" role="menu">
														</ul>
													</div>
													<!-- /btn-group下拉框组 -->
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">部门类型：</label>
									<div class="col-sm-8">
										<select class="form-control m-b" name="depttype">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">是否启用:</label>
									<div class="col-sm-8">
										<div class="radio radio-info radio-inline">
	                                        <input type="radio" id="inlineRadio1" value="1" name="status" checked="">
	                                        <label for="inlineRadio1"> 是 </label>
	                                    </div>
	                                    <div class="radio radio-inline">
	                                        <input type="radio" id="inlineRadio2" value="0" name="status">
	                                        <label for="inlineRadio2"> 否 </label>
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
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
<!-- 部门信息弹出框结束    -->

<!-- 部门授权弹出框     -->
<div class="modal inmodal" id="deptAuthorizeModal" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated fadeIn">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">部门授权</h4>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-content">
							<form id="deptAuthorizeForm" class="form-horizontal">
								<input type="reset" style="display:none;" />
								<div class="form-group">
									<label class="col-sm-3 control-label">部门可卖产品：</label>
									<div class="col-sm-8">
										<div id="allViableProduct">
											
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
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
<!-- 部门授权弹出框结束 -->
	<script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
    <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- 搜索建议插件 -->
    <script src="<%=basePath%>js/plugins/suggest/bootstrap-suggest.min.js"></script>
    <!-- 表单验证插件   -->
    <script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
    <!-- 树插件 -->
    <script src="<%=basePath%>js/plugins/treeview/bootstrap-treeview.js"></script>
    <script src="<%=basePath%>myjs/dept.js"></script>
    <script src="<%=basePath%>myjs/sys_dept.js"></script>
  
</body>
</html>