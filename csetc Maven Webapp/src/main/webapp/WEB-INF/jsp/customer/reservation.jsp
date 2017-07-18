<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: reservation.jsp </br>
 * 描述:已经预约的客户</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-05-11 </br> 
 -->
<!DOCTYPE HTML >
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户分配信息</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
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
<!-- 可编辑表格 -->
<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<!-- 时间轴 -->
<link href="<%=basePath%>css/plugins/time-axis/timeaxis.css" rel="stylesheet">

<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <!-- 客户信息   -->
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                            <h2>预约客户</h2>
                            <div class="">
                                <div  class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
		                                    <div id="customerreservationtoolbar" class="pull-left">
			                                    <!-- <span class="small text-center search"><input class="form-control input-sm" type="text" placeholder="搜索"></span> -->
			                                    <!-- <span class="small text-center"><button type="button" class="btn btn-xs btn-info"  onclick="updCustomerReservationData('1')"><i class="fa fa-check"></i>上门</button></span> --> 
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-success" onclick="addRemarkShow()"><i class="fa fa-pencil"></i>批注</button></span>
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-danger" onclick="assignCustomerShow()"><i class="fa fa-hand-o-right"></i>分配</button></span>
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="updReservationStatus()"><i class="fa fa-reply"></i>放弃</button></span> 
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-warning" onclick="assignCustomerReservationData('3')"><i class="fa fa-remove"></i>流失</button></span>
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="updVisitTimeShow()"><i class="fa fa-clock-o"></i>更改上门时间</button></span>
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="updScrapShow()"><i class="fa fa-reply"></i>继续回访</button></span>
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-danger" onclick="addSeeHistoryRemark()"><i class="fa fa-eye"></i>查看历史批注</button></span>
			                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="qryResumeDetail()"><i class="fa fa-eye"></i>查看简历详情</button></span>
			                                </div>
                                             <!-- 动态生成表格（客户分配数据） -->
                                         	<table id="customerReservationData" style="cursor:pointer"></table>
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


<!-- 客户数据弹出框      -->
<div class="modal inmodal" id="customerModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">客户数据</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       
                    </div>
                   </div>
                 </div>
             </div>
             <!-- <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <button type="button" class="btn btn-primary">保存</button>
            </div> -->
        </div>
    </div>
</div>
<!-- 客户数据弹出框结束   -->  

<!-- 分配客户数据弹出框      -->
<div class="modal inmodal" id="assignCustomerModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">分配客户</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
						<form id="assigncustomer" enctype="multipart/form-data" method="post">
							 <div class="form-group" >
							 	<label class="col-sm-3 control-label">分配给：</label>
	 	 						<div class="col-sm-6">
	 	 							<div class="input-group">
	 	 								<input name="collector" type="text" class="form-control input-sm" id="userid_data2" required="required">
	 	 								<div class="input-group-btn">
	 	 									<button type="button" class="btn btn-sm btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
	 										 <ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
	 										 </div>
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
                 <button type="button" class="btn btn-primary" onclick="assignCustomer()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 客户分配数据模态框结束 -->	
 <!-- 回访客户模态框  callback -->
	<div class="modal inmodal" id="callbackModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">待回访客户</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal" id="callbackForm">
									<input type="hidden" name="callbackid">
									<input type="hidden" name="resourceid">
									<input type="hidden" name="callbacktype">
									<input type="hidden" name="customerid">
									<div class="form-group">
										<label class="col-sm-4 control-label">回访原因：</label>
										<div class="col-sm-6">
											<select class="form-control" name="reseaon"></select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">意向度：</label>
										<div class="col-sm-6">
											<div class="radio radio-info radio-inline">
												<input type="radio" id="intention1" value="1" name="intention" checked=""><label for="intention1"> 有 </label>
											</div>
       										<div class="radio radio-inline">
       											<input type="radio" id="intention2" value="0" name="intention"><label for="intention2"> 无 </label>
       										</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">是否测试：</label>
										<div class="col-sm-6">
											<div class="radio radio-info radio-inline">
												<input type="radio" id="test1" value="1" name="testing" checked=""><label for="test1"> 是 </label>
											</div>
       										<div class="radio radio-inline">
       											<input type="radio" id="test2" value="0" name="testing"><label for="test2"> 否 </label>
       										</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">提醒内容：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="tips">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">提醒时间：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control layer-date laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name="tipdatetime" required="required"/>
										</div>
									</div>
									<!-- <div class="form-group">
										<label class="col-sm-4 control-label">沟通内容：</label>
										<div class="col-sm-6">
											<textarea rows="" cols="" class="form-control" name="memo" minlength="2" required="required"></textarea> 
										</div>
									</div> --> 
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="updScrap()"">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 回访客户模态框结束  -->	
 <!-- 更改上门时间模态框 -->
	<div class="modal inmodal" id="updVisitTimeModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">更改上门时间</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<form method="post" class="form-horizontal" id="updVisitTimeForm">
									<input type="hidden" name="reservationid" >
									<div class="form-group">
										<label class="col-sm-4 control-label">预计到达时间：</label>
										<div class="col-sm-6">
											<input type="text" class="form-control layer-date laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="expecttimes" required="required"/>
										</div>
									</div>
									
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="updVisitTime()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 更改上门时间模态框结束  -->	
	<!-- 客户批注弹出框      -->
	<div class="modal inmodal" id="customerRemarkModal" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content animated fadeIn">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	                </button>
	                <h4 class="modal-title">客户批注</h4>
	            </div>
	            <div class="timeaxis">
					<div class="container">
						<div class="row">
							<div class="col-md-9">
								<div class="main-timeline" id="mytimeaxis">
								</div>
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
	<!-- 客户简历照片弹出框      -->
	<div class="modal inmodal" id="resumeImageModal" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content animated fadeIn">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	                </button>
	                <h4 class="modal-title">客户简历</h4>
	            </div>
	            <div class="timeaxis">
					<div class="container" id="resumeImage" >
					</div>
				</div>
	            
	            <div class="modal-footer">
	                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 客户简历照片弹出框结束   -->  
	
	<script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
    <!-- 日期插件 -->
    <script src="<%=basePath%>js/plugins/layer/laydate/laydate.js"></script>
    <!-- 通知插件 -->
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
    <!-- 弹窗插件 -->
    <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- 步骤向导    -->
    <script src="<%=basePath%>js/plugins/staps/jquery.steps.js"></script>
    <!-- bootstr表格插件 -->
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<!-- 可编辑表格 -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/bootstrap-table-editable.js"></script>
	
	<!-- 搜索建议插件 -->
    <script src="<%=basePath%>js/plugins/suggest/bootstrap-suggest.min.js"></script>
   
    <!-- 表单验证插件   -->
	<script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
    
	<script type="text/javascript" src="<%=basePath%>myjs/select.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/reservation.js"></script>
	<script type="text/javascript">
	var user = '<%=session.getAttribute("userid")%>';
	var operstr = '<%=session.getAttribute("operstr")%>';
	var basepath = '<%=basePath%>';
	</script>
</body>
</html>
