<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: product.jsp </br>
 * 描述:产品管理管理</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-04-10 </br> 
 -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
<title>产品管理</title>
<link rel="shortcut icon" href="favicon.ico"> 
<link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min93e3.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="<%=basePath%>css/showBo.css" rel="stylesheet">





</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <!-- 产品管理 -->
            
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                            <h2>产品管理</h2>
                            <div class="">
                                <div class="tab-pane active">
                                    <div class="table-responsive">
                                        <div id="productToolbar">
                                            <span class="small text-center"><button type="button" class="btn btn-xs btn-primary" onclick="addProductModelShow()"><i class="fa fa-plus"></i>新增</button></span>
                                          <!--   <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-info" data-toggle="modal" data-target="#myModal"><i class="fa fa-pencil"></i>修改</button></span>
                                            <span class="small col-sm-3 text-center"><button type="button" class="btn btn btn-warning"><i class="fa fa-eraser"></i>删除</button></span> -->
                                        </div>
                                        <!-- 动态生成表格（产品数据） -->
                                         <table id="allProductData" class="table-bordered"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
	</div>

<!-- 新增产品的弹出框 -->
<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">新增产品</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form class="form-horizontal" id="product" enctype="multipart/form-data" >
                           <!-- <div class="form-group">
                              <label class="col-sm-4 control-label">产品编号：</label>
                              <div class="col-sm-6">
                                   <input type="text" name="prodid" class="form-control input-sm">
                              </div>
                           </div> -->
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">产品名称：</label>
                               <div class="col-sm-6">
                                    <input type="text" name="prodname" class="form-control input-sm" required="required">
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">产品类型：</label>
                               <div class="col-sm-6">
                               	<select name="prodtype" class="form-control"></select>
                               </div>
                           </div>
                           <!-- <div class="form-group" >
                               <label class="col-sm-4 control-label">产品版本：</label>
                               <div class="col-sm-6">
                               	<input name="prodver" placeholder="只允许输入单个数字" class="form-control input-sm"/>
                               </div>
                           </div> -->
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">产品状态：</label>
                               <div class="col-sm-6">
                               	 <div class="radio">
                                      <label>
                                      	  <input class="radio i-checks" type="radio" name="status" checked="checked" value="1"><i></i>启用 
                                      </label>
                                      <label>
                                      	  <input class="radio i-checks" type="radio" name="status" value="0"> <i></i>禁用
                                      </label>
                                  </div>
                               </div>
                           </div>
                           <div class="form-group" >
                           		<label class="col-sm-1">
                           			<a title="新增子产品" class="add-subproduct"  onclick="addSubProductShow()"> + </a>
								</label>
                            		<div class="col-sm-11" id="subproducts">
                            		</div>
                            </div>
                        </form>
                    </div>
                   </div>
                 </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <button type="button" class="btn btn-primary" onclick="addProductBaseInfo()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 新增产品弹出框结束   -->  

<!-- 产品详情(修改)弹出框    -->
<div class="modal inmodal" id="prodcutUpdModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">产品详情</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form class="form-horizontal" id="productUpd" enctype="multipart/form-data">
                       <input type="hidden" name="prodid">
                           <!-- <div class="form-group">
                              <label class="col-sm-4 control-label">产品编号：</label>
                              <div class="col-sm-6">
                                   <input type="text" name="prodid" readonly="readonly" class="form-control input-sm">
                              </div>
                           </div> -->
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">产品名称：</label>
                               <div class="col-sm-6">
                                    <input type="text" name="prodname" class="form-control input-sm" required="required">
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">产品类型：</label>
                               <div class="col-sm-6">
                               	<select name="prodtype" class="form-control"></select>
                               </div>
                           </div>
                           <!-- <div class="form-group" >
                               <label class="col-sm-4 control-label">产品版本：</label>
                               <div class="col-sm-6">
                               	<input name="prodver" placeholder="只允许输入单个数字" class="form-control input-sm"/>
                               </div>
                           </div> -->
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">产品状态：</label>
                               <div class="col-sm-6">
                               	 <div class="radio">
                                      <label>
                                      	  <input class="radio i-checks" type="radio" name="status" value="1"><i></i>启用 
                                      </label>
                                      <label>
                                      	  <input class="radio i-checks" type="radio" name="status" value="0"> <i></i>禁用
                                      </label>
                                  </div>
                               </div>
                           </div>
                           <div class="form-group" >
			               		<label class="col-sm-2">
			               			<a title="查看子产品" class="see-subproduct" > 查看子产品   </a>
								</label>
								<div class="col-sm-10" id="">
		                		</div>
			               </div>
                           <div class="form-group" >
								<div class="col-sm-12" id="subProductsTable">
		                		</div>
			               </div>
                           <div class="form-group" >
			               		<label class="col-sm-1">
			               			<a title="新增子产品" class="add-subproduct"  onclick="addProductDetail()"> + </a>
								</label>
		                		<div class="col-sm-11" id="subproducts">
		                		</div>
			               </div>
                        </form>
                   </div>
               </div>
             </div>
            </div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                 <button type="button" class="btn btn-primary" onclick="updProduct()">保存</button>
            </div>
        </div>
    </div>
</div>
 <!-- 产品详情(修改)弹出框结束    -->


<!-- 子产品详情(修改)弹出框    -->
<div class="modal inmodal" id="prodcutDetailUpdModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content animated fadeIn">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">子产品详情</h4>
            </div>
            <div class="row">
             <div class="col-sm-12">
               <div class="ibox float-e-margins">
                   <div class="ibox-content">
                       <form class="form-horizontal" id="producDetailtUpd" enctype="multipart/form-data">
                       <input type="reset" style="display:none;" /> 
                       <input type="hidden" name="subprodid">
                       <input type="hidden" name="prodid">
                           <!-- <div class="form-group">
                              <label class="col-sm-4 control-label">父产品编号：</label>
                              <div class="col-sm-6">
 								<input type="text" value="" readonly="readonly" name="prodid" class="form-control input-sm">父产品编号 
                              </div>
                           </div> -->
                           <!-- <div class="form-group">
                              <label class="col-sm-4 control-label">子产品编号：</label>
                              <div class="col-sm-6">
                                   <input type="text" name="subprodid" readonly="readonly" class="form-control input-sm">
                              </div>
                           </div> -->
                           <!-- <div class="form-group" >
                               <label class="col-sm-4 control-label">子产品名称：</label>
                               <div class="col-sm-6">
                                    <input type="text" name="subprodname" class="form-control input-sm">
                               </div>
                           </div> -->
                           <div class="form-group" >
                           		<label class="col-sm-4 control-label">子产品名称：</label>
		 						<div class="col-sm-6">
		 							<div class="input-group">
		 								<input name="subprodname" type="text" class="form-control subProdName_data"  required>
	 									<div class="input-group-btn">
	 										<button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
	 											<span class="caret"></span>
	 										</button>
	 										<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
	 									</div>
	 								</div>
	 							</div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">子产品成本价格：</label>
                               <div class="col-sm-6">
                               	<input type="text" name="subprodprice" class="form-control" number="true" required="required"/>
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">子产品销售价格：</label>
                               <div class="col-sm-6">
                               	<input name="subprodsaleprice" class="form-control input-sm" number="true" required="required"/>
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">子产品最低销售价格：</label>
                               <div class="col-sm-6">
                               	<input name="subprodminprice" class="form-control input-sm" number="true" required="required"/>
                               </div>
                           </div>
                           <div class="form-group" >
                               <label class="col-sm-4 control-label">允许折扣：</label>
                               <div class="col-sm-6">
                               	 <div class="radio">
                                      <label>
                                      	  <input class="radio i-checks" type="radio" name="discountenable" value="1"><i></i>是
                                      </label>
                                      <label>
                                      	  <input class="radio i-checks" type="radio" name="discountenable" value="0"> <i></i>否
                                      </label>
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
                 <button type="button" class="btn btn-primary" >保存</button>
            </div>
        </div>
    </div>
</div>
 <!-- 子产品详情(修改)弹出框 结束    -->

 
            



 <script src="<%=basePath%>js/jquery.min.js"></script>
 <script src="<%=basePath%>js/bootstrap.min.js"></script>
 <script src="<%=basePath%>js/move-model.js"></script>
 <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
 <script src="<%=basePath%>js/content.min.js"></script>
 <script src="<%=basePath%>js/plugins/iCheck/icheck.min.js"></script>
 <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
 <!-- 搜索建议插件 -->
 <script src="<%=basePath%>js/plugins/suggest/bootstrap-suggest.min.js"></script>
   
 <!-- Sweet Alert -->
 <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
 <script src="<%=basePath%>js/showBo.js"></script>
  <!-- bootstr表格插件 -->
  <script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
  <script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
  <script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
 <!-- 表单验证插件   -->
  <script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
  <script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
  
  <script type="text/javascript" src="<%=basePath%>myjs/select.js"></script>
  <script type="text/javascript" src="<%=basePath%>myjs/product.js"></script>
</body>
</html>
