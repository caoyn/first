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
							<div id="ordertoolbar"></div>
							<h2>具备入班审核许可的学员</h2>
							<div class="qualifiedStudentsDiv">
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

	<script type="text/javascript">
		$('#orderTable').bootstrapTable({
			data : "${qualifiedStudents}",
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, //是否显示分页（*）
			sortable : true, //是否启用排序
			sortOrder : "asc", //排序方式
			toolbar : "#ordertoolbar",
			sidePagination : "client", //分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 20, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : false,
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 10, //最少允许的列数
			height : 600, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", //每一行的唯一标识，一般为主键列
			idField : "id",
			showToggle : true,
			columns : [ {
				checkbox : true,
			}, {
				field : 'stuName',
				title : '学员姓名',
				sortable : true
			}, {
				field : 'stuNo',
				title : '学员编号',
				sortable : true
			}, {
				field : 'orderId',
				title : '订单号',
				sortable : true
			}, {
				field : 'salesName',
				title : '所属销售',
				sortable : true
			}, {
				field : 'orderStatus',
				title : '订单状态',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '1012':
						return "已收款，未全款";
						break;
					case '1013':
						return "已全款";
						break;

					default:
						return "未知状态"
						break;
					}
				}
			}]
		});
	</script>
</body>
</html>
