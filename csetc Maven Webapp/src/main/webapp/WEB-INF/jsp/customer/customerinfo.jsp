<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: customerinfo.jsp </br>
 * 描述:客户信息</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-04-28 </br> 
 -->
<!DOCTYPE HTML >
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户信息</title>
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
<input type="hidden" value="" id="uploaderrorinfo">
<input type="hidden" value="<%=session.getAttribute("username") %>" id="username">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <!-- 客户信息   -->
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                                <div id="customertoolbar" class="">
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-info"  onclick="uploadCustomerinfo()"><i class="fa fa-upload"></i>导入</button></span> 
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="addCustomerShow()"><i class="fa fa-plus"></i>新增</button></span>
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-warning" onclick="assignCustomerShow()"><i class="fa fa-tag"></i>分配</button></span>
									<span class="small text-center"><button type="button" class="btn btn-xs btn-success" onclick="updRemarkShow()"><i class="fa fa-pencil"></i>批注</button></span>
<!--                                     <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-warning del" onclick="delDeptData()"><i class="fa fa-remove"></i>删除</button></span> -->
                               		<span class="small text-center"><button type="button" class="btn btn-xs btn-danger" onclick="qrySeeHistoryRemark()"><i class="fa fa-eye"></i>查看历史批注</button></span>
                               		<!-- <span class="small text-center"><button type="button" class="btn btn-xs btn-danger" onclick="qrySeeHistoryAssign()"><i class="fa fa-eye"></i>查看今日分配</button></span> -->
                               		<span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="qrySeeDataCount()"><i class="fa fa-eye"></i>查看数据统计</button></span>
                               		<span class="small text-center"><button type="button" class="btn btn-xs btn-waring" onclick="uploadDatamate()"><i class="fa fa-upload"></i>上传简历数据</button></span>
                                	<span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="qryResumeDetail()"><i class="fa fa-eye"></i>查看简历详情</button></span>
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
                                             <!-- 动态生成表格（审批流程数据） -->
                                         	<table id="customerData" style="cursor:pointer"></table>
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
<!-- 导入客户数据弹出框      -->
<div class="modal inmodal" id="uploadCustomerModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">导入客户数据</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
						<form id="uploadcustomer" method="post" class="form-horizontal" enctype="multipart/form-data">
							<table class="table table-striped table-hover table-responsive table-bordered">
								<tr>
									<td><input id="myFile" type="file" class="file" name="mFile" required="required"></td>
									<td><button class="btn btn-primary" onclick="addUpload()">上传</button></td>
									<td><a class="btn btn-info" href="../customer/exporttemplate.do?fileName=modal.xlsx">下载模板</a></td>
								</tr>
							</table>
						</form>
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
<!-- 导入客户数据弹出框结束   -->  
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
	 	 								<input name="collector" type="text" class="form-control input-sm" id="userid_data" required="required">
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
<!-- 客户数据统计弹出框    -->
	<div class="modal inmodal" id="dataCountModal" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content animated fadeIn">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	                </button>
	                <h4 class="modal-title">客户数据统计</h4>
	            </div>
				<div class="container">
					<div class="row">
						<div class="col-md-3">
							<table class="table table-bordered" id="sexcount">
								<thead>
									<tr><td colspan="2">今日男女比例数据</td></tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="col-md-3">
							<table class="table table-bordered" id="sourcecount">
								<thead>
									<tr><td colspan="2">今日各项来源渠道数据</td></tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<table class="table table-bordered" id="cousecount">
								<thead>
									<tr><td colspan="2">今日各项意向课程数据</td></tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<table class="table table-bordered" id="usercount">
								<thead>
									<tr><td colspan="2">今日分配的各项数据</td></tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
	            
	            <div class="modal-footer">
	                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 客户数据统计弹出框结束   -->
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
	<script type="text/javascript" src="<%=basePath%>myjs/customerinfo.js"></script>
	<script type="text/javascript">
	var user = '<%=session.getAttribute("userid")%>';
	var operstr = '<%=session.getAttribute("operstr")%>';
	var basepath = '<%=basePath%>';
	</script>
</body>
</html>
