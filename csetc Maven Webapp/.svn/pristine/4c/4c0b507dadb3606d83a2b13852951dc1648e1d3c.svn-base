<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: customer.jsp </br>
 * 描述:客户数据</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-06-15 </br> 
 -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户数据</title>
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

<!-- 时间轴 -->
<link href="<%=basePath%>css/plugins/time-axis/timeaxis.css" rel="stylesheet">
<!-- 可编辑表格 -->
<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>


<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">
<style type="text/css">
#conditionForm input, #conditionForm select{
	height: 23px;
}
</style>
	
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <!-- 客户数据  -->
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                                <div id="customertoolbar" class="pull-left">
                                    <!-- <span class="small text-center"><button type="button" class="btn btn-xs btn-info"  onclick="uploadCustomerinfo()" data-toggle="modal" data-target="#uploadCustomerModal"><i class="fa fa-upload"></i>导入</button></span> 
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="addCustomerShow()"><i class="fa fa-plus"></i>新增</button></span>
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-warning" onclick="assignCustomerShow()"><i class="fa fa-tag"></i>分配</button></span>
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-success" onclick="updRemarkShow()"><i class="fa fa-pencil"></i>批注</button></span> -->
<!--                                     <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-warning del" onclick="delDeptData()"><i class="fa fa-remove"></i>删除</button></span> -->
                                </div>
                                <!-- <h2>客户数据</h2> -->
                                <div>
                                	<form action="">
                                		
                                	</form>
								</div>
                            <div class="">
                                <div  class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                        	<form id="conditionForm">
	                                        	<div>
	                                        	 	<input type="text" name="telephone" placeholder="电话号码">
	                                        		<input type="text" name="customername" placeholder="客户姓名">
	                                        		<input type="text" name="school" placeholder="学校">
	                                        		<input type="text" name="email" placeholder="邮件">
	                                        		<input type="button" value="查询" onclick="fillTableData()" />
                                        			<input type="reset" value="重置">
	                                        	</div>
	                                        	<h6></h6>
                                        		<div>
                                        			<select name="collector">
	                                        			<option value="">采集人</option>
	                                        		</select>
	                                        		<select name="education">
	                                        			<option value="">学历</option>
	                                        		</select>
	                                        		<select name="major">
	                                        			<option value="">学历</option>
	                                        		</select>
	                                        		<select name="source">
	                                        			<option value="">来源渠道</option>
	                                        		</select>
	                                        		<select name="customertype">
	                                        			<option value="">客户类型</option>
	                                        		</select>
	                                        		<select name="course">
	                                        			<option value="">意向课程</option>
	                                        		</select>
	                                        		<select name="jobobjective">
	                                        			<option value="" >求职意向</option>
	                                        		</select>
                                        		</div>
                                        		
                                        		
                                        	</form>
                                             <!-- 动态生成表格 --> 
                                         	<table id="customerDataTable"></table>
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
<!-- 客户详情弹出框      -->
<div class="modal inmodal" id="CustomerInfoModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">客户详情</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                   		<input type="hidden" name="customerid"/>
                   		<ul id="myTab" class="nav nav-tabs">
							<li class="active"><a href="#basicData" data-toggle="tab">基本数据</a></li>
							<li><a href="#assignData" data-toggle="tab">分配数据</a></li>
							<li><a href="#reservationData" data-toggle="tab">预约数据</a></li>
							<li><a href="#callbackData" data-toggle="tab">回访数据</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="basicData">
								<table class="table table-striped table-hover table-responsive table-bordered" id="customerTable">
								</table>
							</div>
							<div class="tab-pane fade" id="assignData">
								<table class="table table-striped table-hover table-responsive table-bordered" id="assignTable"></table>
							</div>
							<div class="tab-pane fade" id="reservationData">
								<table class="table table-striped table-hover table-responsive table-bordered" id="reservationTable"></table>
							</div>
							<div class="tab-pane fade" id="callbackData">
								<table class="table table-striped table-hover table-responsive table-bordered" id="callbackTable"></table>
							</div>
						</div>
                   
						
                    </div>
                   </div>
                 </div>
             </div>
             <!-- <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <button type="button" class="btn btn-primary" onclick="upload()">上传</button>
            </div> -->
        </div>
    </div>
</div>
<!-- 客户详情弹出框结束   -->  
<!-- 客户订单数据弹出框      -->
<div class="modal inmodal" id="orderModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">订单数据</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
						<table id="orderTable" class="table table-bordered">
						</table>
                    </div>
                   </div>
                 </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <!-- <button type="button" class="btn btn-primary" onclick="assignCustomer()">确定</button> -->
            </div>
        </div>
    </div>
</div>
<!-- 客户订单数据模态框结束 -->
	
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
	<script type="text/javascript" src="<%=basePath%>myjs/customerdata.js"></script>
	<script type="text/javascript">
	var user = '<%=session.getAttribute("userid")%>';
	var operstr = '<%=session.getAttribute("operstr")%>';
	</script>
</body>
</html>
