<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: order.jsp </br>
 * 描述:订单管理</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-05-23 </br> 
 -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">

<style type="text/css">
	#viewQuaImgDiv {
		border:0px solid red;
		width:920px;
		height:400px;
		text-align:center;
		 display: table-cell;
        vertical-align: middle;
	}
	#viewQuaImgDiv img {
		border:0px solid blue;
		
		height: 100%;
	} 
</style>

<link rel="<%=basePath%>shortcut icon" href="favicon.ico">

<link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min93e3.css" rel="stylesheet">
<!-- 通知插件 -->
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
<!-- 步骤向导 -->
<link href="<%=basePath%>css/plugins/steps/jquery.steps.css" rel="stylesheet">
<!-- 时间轴 -->
<link href="<%=basePath%>css/plugins/time-axis/timeaxis.css" rel="stylesheet">
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">




</head>
<body class="gray-bg">
	
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<!-- 审批流程管理   -->
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="ordertoolbar">
								<input type="hidden" value="${atLeastMoneyForQua }" id="atLeastMoneyForQua"/>
								<span class="small text-center"><button type="button"
										class="btn btn-xs btn-primary" onclick="updRemarkShow()">
										<i class="fa fa-pencil"></i>批注
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-primary" onclick="addOrderShow()">
										<i class="fa fa-plus"></i>新增
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-warning" onclick="updOrderUserShow()">
										<i class="fa fa-exchange"></i>转移
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-warning " onclick="updCancelOrderShow()">
										<i class="fa fa-remove"></i>取消订单
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-warning " onclick="updBackApprveShow()">
										<i class="fa fa-user-times"></i>返款
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-danger " onclick="updDropOutModalShow()">
										<i class="fa fa-user-times"></i>退款
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-info" onclick="addSeeHistoryRemark()">
										<i class="fa fa-eye"></i>查看历史批注
									</button></span> 
									<span class="small text-center"><button type="button" class="btn btn-xs btn-primary "
										onclick="uploadCustomerQualification()">
										<i class="fa fa-long-arrow-up"></i>上传客户资质
									</button></span> <span class="small text-center"><button type="button"
										class="btn btn-xs btn-primary " onclick="applyClass()">
										<i class="fa fa-hand-rock-o"></i>申请入班
									</button></span>
							</div>
							<h2>我的订单</h2>
							<div class="">
								<div class="tab-pane active">
									<div class="full-height-scroll">
										<div class="table-responsive">
											<!-- 动态生成表格（审批流程数据） -->
											<table id="orderTable" class="table-bordered"></table>
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

	<!-- 客户签单模态框  order -->
	<div class="modal inmodal" id="addOrderModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">客户签单</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal" id="addOrderForm"
									enctype="multipart/form-data"></form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 客户签单模态框结束  -->

	<!-- 订单转移模态框   -->
	<div class="modal inmodal" id="updOrderUserModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">订单转移</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal"
									id="updOrderUserForm">
									<input type="hidden" name="orderidstr" />
									<div class="form-group">
										<label class="col-sm-4 control-label">转移后负责人：</label>
										<div class="col-sm-6">
											<div class="input-group">
												<input name="useridtext" type="text"
													class="form-control input-sm" id="user_data"
													required="required">
												<div class="input-group-btn">
													<button type="button"
														class="btn btn-sm btn-white dropdown-toggle"
														data-toggle="dropdown">
														<span class="caret"></span>
													</button>
													<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">变更原因：</label>
										<div class="col-sm-6">
											<select class="form-control" name="reason"></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">备注：</label>
										<div class="col-sm-6">
											<textarea rows="" cols="" class="form-control" name="memo"></textarea>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="updOrderUser()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 订单转移模态框结束 -->
	<!-- 取消订单模态框   -->
	<div class="modal inmodal" id="cancelOrderModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">取消订单</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal" id="cancelOrderForm">
									<input type="hidden" name="oederid" />
									<div class="form-group">
										<label class="col-sm-4 control-label">取消原因：</label>
										<div class="col-sm-6">
											<select class="form-control" name="reason"></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">备注：</label>
										<div class="col-sm-6">
											<textarea rows="" cols="" class="form-control" name="memo"></textarea>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="cancelOrder()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 取消订单模态框结束 -->
	<!-- 客户退款、返款模态框   -->
	<div class="modal inmodal" id="dropOutOrderModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">退款</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal"
									id="dropOutOrderForm">
									<input type="hidden" name="orderid" /> <input type="hidden"
										name="prodid" /> <input type="hidden" name="approvetype" />
									<input type="hidden" name="ostatus" />
									<div class="form-group">
										<label class="col-sm-4 control-label">客户姓名：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control input-sm"
												name="customername" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">产品名称：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control input-sm"
												name="prodname" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">审批流程：</label>
										<div class="col-sm-6">
											<select class="form-control" name="approveid"></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">原因：</label>
										<div class="col-sm-6">
											<select class="form-control" name="refundtype"></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">时间：</label>
										<div class="col-sm-6">
											<input class="form-control layer-date laydate-icon input-sm"
												onclick="laydate()" name="refundtime1" required="required" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">金额：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control input-sm"
												digits="true" required="required" name="refund" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">备注：</label>
										<div class="col-sm-6">
											<textarea rows="" cols="" class="form-control" minlength="6"
												required="required" name="memo"></textarea>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="dropOutOrder()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 客户退学模态框结束 -->
	<!-- 客户批注弹出框      -->
	<div class="modal inmodal" id="customerRemarkModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">客户批注</h4>
				</div>
				<div class="timeaxis">
					<div class="container">
						<div class="row">
							<div class="col-md-9">
								<div class="main-timeline" id="mytimeaxis"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 客户批注弹出框结束   -->




	<!-- 上传客户资质模态框  order id="uploadCusQuaModalBox" -->
	<div class="modal inmodal" id="uploadCusQuaModalBox">
		<div class="modal-dialog modal-lg">
			<div class="modal-content animated fadeIn">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">上传客户资质</h4>
				</div>
				
				<c:forEach items="${quaOfSales }" var="qua" varStatus="vs">
					
				
					<div class="row">
						<div class="col-sm-12">
							<div class="ibox-content">
								
									<table class="table table-striped table-hover table-responsive table-bordered"  style="text-align: center;margin:auto;">
										<tr>
											<td colspan="2">
												<form id="picUpload${vs.count }" class="picUpload" enctype="multipart/form-data">
													<label style="float:left;">${qua.level2name }：</label>
													<input type="hidden" id="orderIdInput" name="orderId"/>
													<input type="hidden" id="quaTypeIdInput" name="qualificationTypeId" value="${qua.id }" />
													<input type="file" class="file uplQuaInp" id="uplQuaInp" name="qualificationPicture" />
													<label id="quaMemoLabel" style="color: red;float:left;"></label>
												</form>
											</td>
											<td colspan="1"> 
												
												<button class="btn btn-primary uplQuaBtn" onclick="uploadPicture(${vs.count})">上传</button>
												<button class="btn btn-info viewUplQuaBtn" onclick="viewPicture(${vs.count})">查看</button>
											</td>
										</tr>
									</table>
								
							</div>
						</div>
					</div>
				
				</c:forEach>
			</div>
					
				
		</div>
	</div>
	<!-- 上传客户资质模态框结束  -->
	
	
	
	<!-- 查看已经上传的文件模态框  order id="viewCusQuaModalBox" -->
	<!-- 上传客户资质模态框  order id="uploadCusQuaModalBox" -->
	<div class="modal inmodal" id="viewCusQuaModalBox"> <!-- class="modal inmodal" -->
		<div class="modal-dialog modal-lg">
			<div class="modal-content animated" style="border: 0px solid rgba(0, 0, 0, 0);border-radius: 4px;box-shadow: 0 0px 0px rgba(0, 0, 0, .3);">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">x</a>
					<h4 class="modal-title">资质查看</h4>
				</div>
				
			</div>
			<div id="viewQuaImgDiv" class="modal-body">
				<img id="qualificationPictureImg"
					src="${pageContext.request.contextPath }" alt=""
				/>	
				
			</div>	
		</div>
	</div>
	<!-- 查看已经上传的文件模态框结束  -->
	

	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script
		src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>js/content.min.js"></script>
	<script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
	<script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- 日期插件 -->
	<script src="<%=basePath%>js/plugins/layer/laydate/laydate.js"></script>
	 <!-- 步骤向导    -->
    <script src="<%=basePath%>js/plugins/staps/jquery.steps.js"></script>
	
	<!-- bootstr表格插件 -->
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

	<!-- 表单验证插件   -->
	<script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
	<script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
	<!-- 搜索建议插件 -->
	<script src="<%=basePath%>js/plugins/suggest/bootstrap-suggest.min.js"></script>

	<script type="text/javascript" src="<%=basePath%>myjs/select.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/order.js"></script>
	
	<script type="text/javascript">
		var user = '<%=session.getAttribute("userid")%>';
		var operstr = '<%=session.getAttribute("operstr")%>';
	</script>
	
	
	
	
</body>
</html>
