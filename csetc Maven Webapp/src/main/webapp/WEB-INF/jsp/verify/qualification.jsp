<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学员入班</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">

<link rel="/csetc/shortcut icon" href="favicon.ico">

<link href="${pageContext.request.contextPath }/css/bootstrap.min14ed.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/font-awesome.min93e3.css" rel="stylesheet">
<!-- 通知插件 -->
<link href="${pageContext.request.contextPath }/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="${pageContext.request.contextPath }/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
<!-- 步骤向导 -->
<link href="${pageContext.request.contextPath }/css/plugins/steps/jquery.steps.css" rel="stylesheet">

<link href="${pageContext.request.contextPath }/css/animate.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/style.min862f.css" rel="stylesheet">

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<!-- 审批流程管理   -->
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="ordertoolbar">
								<span class="small text-center">
									<button type="button" class="btn btn-xs btn-primary" onclick="quaVerifyPass()">
										<i class="fa fa-hand-grab-o"></i>通过
									</button>
								</span> 
								<span class="small text-center">
									<button type="button" class="btn btn-xs btn-danger" onclick="quaVerifyRefuse()">
										<i class="fa fa-close"></i>拒绝
									</button>
								</span> 
								<span class="small text-center">
									<button type="button" class="btn btn-xs btn-info" onclick="quaVerifyView()">
										<i class="fa fa fa-eye"></i>查看
									</button>
								</span> 
							</div>
							</div>
							<h2>客户资质审核</h2>
							<div class="qualifiedStudentsDiv">
								<div class="tab-pane active">
									<div class="full-height-scroll">
										<div class="table-responsive">
											<!-- 动态生成表格（审批流程数据） -->
											<table id="qualificationTable" class="table-bordered"></table>
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





	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/content.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/plugins/toastr/toastr.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- 日期插件 -->
	<script
		src="${pageContext.request.contextPath }/js/plugins/layer/laydate/laydate.js"></script>
	<!-- 步骤向导    -->
	<script
		src="${pageContext.request.contextPath }/js/plugins/staps/jquery.steps.js"></script>
	<!-- bootstr表格插件 -->
	<script
		src="${pageContext.request.contextPath }/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

	<!-- 表单验证插件   -->
	<script
		src="${pageContext.request.contextPath }/js/plugins/validate/jquery.validate.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/plugins/validate/messages_zh.min.js"></script>
	<!-- 搜索建议插件 -->
	<script
		src="${pageContext.request.contextPath }/js/plugins/suggest/bootstrap-suggest.min.js"></script>



	<!-- 页面js控制 -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/myjs/qualification.js"></script>
</body>
</html>
