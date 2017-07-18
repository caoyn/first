<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: promovode.jsp </br>
 * 描述:促销码管理</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-06-09 </br> 
 -->
<!DOCTYPE HTML >
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>促销码管理</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
<link rel="<%=basePath%>shortcut icon" href="favicon.ico"> 
<link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">

<!-- 可编辑插件 -->
<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>

<link href="<%=basePath%>css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min93e3.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
<!-- 步骤向导 -->
<link href="<%=basePath%>css/plugins/steps/jquery.steps.css" rel="stylesheet">

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
                                <div id="approvetoolbar">
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="addPromocodeShow()"><i class="fa fa-plus"></i>新增</button></span>
                                    <span class="small text-center"><button type="button" class="btn btn-xs btn-warning" onclick="updPromocodes()"><i class="fa fa-close"></i>禁用</button></span>
<!--                                     <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-warning del" onclick="delDeptData()"><i class="fa fa-remove"></i>删除</button></span> -->
                                </div>
                                <h2>促销码管理</h2>
                            <div class="">
                                <div  class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                             <!-- 动态生成表格（促销码数据） -->
                                         	<table id="promocodeData" ></table>
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


<!-- 新增促销码弹出框      -->
<div class="modal inmodal" id="promocodeModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">新增促销码</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form class="form-horizontal" id="promocodeForm" enctype="multipart/form-data">
                           <div class="form-group">
                              <label class="col-sm-3 control-label">促销码：</label>
                              <div class="col-sm-6">
                              	   <input type="text" class="form-control input-sm" name="rpomocode" id="promocode" required="required"/>
                              </div>
                              <div class="col-sm-3">
                              	   <input type="button" class="btn btn-sm" value="生成促销码" onclick="createPromocode()" />
                              </div>
                           </div>
                           <div class="form-group">
                              <label class="col-sm-3 control-label">过期时间：</label>
                              <div class="col-sm-6">
                              	   <input type="text" class="form-control layer-date laydate-icon" onclick="laydate()" name="expiretimes" required="required"/>
                              </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-3 control-label">使用最大次数：</label>
                               <div class="col-sm-6">
                               		<input type="text" name="maxuse" class="form-control input-sm" digits="true" required="required"/> 
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-3 control-label">促销规则：</label>
                               <div class="col-sm-8">
                               		<div class="panel panel-default">
									  <!-- Default panel contents -->
									  <div class="panel-heading"><select name="promoprod" class="form-control"></select></div>
									  <!-- Table -->
									  <table id="promoprodTable" class="table">
									  </table>
									</div>
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-3 control-label">是否有效：</label>
                               <div class="col-sm-6">
                               	 <div class="radio radio-info radio-inline">
                                     <input type="radio" id="inlineRadio1" value="1" name="status" checked=""/>
                                     <label for="inlineRadio1"> 是 </label>
                                 </div>
                                 <div class="radio radio-inline">
                                     <input type="radio" id="inlineRadio2" value="0" name="status"/>
                                     <label for="inlineRadio2"> 否  </label>
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
                 <button type="button" class="btn btn-primary" onclick="addPromocode()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 新增促销码弹出框结束   -->  
<!-- 促销详情弹出框      -->
<div class="modal inmodal" id="promocodeDetailModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">促销详情</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <table id="promocodeDetailTable" class="table table-bordered"></table>
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
<!-- 促销详情弹出框  -->	

	<script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
    <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- 日期插件 -->
    <script src="<%=basePath%>js/plugins/layer/laydate/laydate.js"></script>
    <!-- 表单验证插件   -->
    <script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
    
    <!-- 可编辑插件-->
	<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	
    <!-- bootstr表格插件 -->
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	
	<!-- 可编辑表格 -->
	<script type="text/javascript" src="<%=basePath%>js/bootstrap-table-editable.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/select.js"></script>
	<script type="text/javascript" src="<%=basePath%>myjs/promocode.js"></script>
</body>
</html>
