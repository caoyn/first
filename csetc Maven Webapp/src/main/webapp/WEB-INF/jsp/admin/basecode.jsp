<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: basecode.jsp </br>
 * 描述:基础信息代码管理</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-03-31 </br> 
 -->
<!DOCTYPE HTML >
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖南中软计算机系统服务有限公司OA系统</title>
<meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
<meta name="description" content="湖南中软计算机系统服务有限公司OA系统">
<link rel="shortcut icon" href="favicon.ico"> 
<link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min93e3.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="tab-content">
                            <h2>大类信息</h2>
                            <div class="clients-list">
                                <div  class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                            <!-- 动态生成表格 -->
                                            <table class="table table-striped table-hover" id="addBasecodeForm">
                                                <tbody>
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="ibox ">
                    <div class="ibox-content">
                        <div class="tab-content">
                        <span class="text-muted small pull-right"><button type="button" class="btn btn btn-primary" onclick="addLevel2()"><i class="fa fa-plus"></i> 新增</button></span>
                        <h3 id="small">客户来源区域</h3>
                            <div id="contact-1" class="tab-pane active" style="margin-top: 2.5em;">
                                <!-- 动态生成表格 -->
                                <table class="table table-striped table-hover table-bordered" id="basecodeLevel2Table" >
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-radio="true">编号</th>
                                            <th data-field="price">信息详情</th>
                                            <th data-field="column1">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <!-- 小类修改的弹出框 -->
              <div class="modal inmodal" id="myModaltwo" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated fadeIn">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                            </button>
                            <h4 class="modal-title">修改小类信息</h4>
                        </div>
                        <div class="row">
                                    <div class="col-sm-8">
                                        <div class="ibox float-e-margins">
                                            <div class="ibox-content">
                                                <form method="post" class="form-horizontal" id="updatedata">                                                 
                                                    <input hidden="hidden" value="" id="levname" name="levname">
                                                    <div class="form-group">
                                                       <label class="col-sm-3 control-label">详细信息</label>
                                                        <div class="col-sm-5">
                                                            <input type="text" placeholder="" class="form-control" id="newname" name="newname" value="">
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" onclick="updBasecodeInfo()">保存</button>
                            </div>
                        </div>
              </div>
            </div>
            <!-- 小类修改弹出框结束 -->
            
            <!-- 新增小类的弹出框 -->
            <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated fadeIn">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                            </button>
                            <h4 class="modal-title">小类信息</h4>
                            </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="ibox float-e-margins">
                                            <div class="ibox-content">
                                                <form method="get" class="form-horizontal" id="adddata">
                                                	<input type="hidden" name="level1id"/>
                                                    <div class="form-group">
                                                       <label class="col-sm-2 control-label">大类名称</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" readonly="" class="form-control"  id="getbasevodeLevel" name="level1name" value="">
                                                        </div>
                                                    </div>
                                                    <div class="hr-line-dashed"></div>
                                                    <div class="form-group">
                                                       <label class="col-sm-2 control-label">小类名称</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control"  name="level2name" >
                                                        </div>
                                                    </div>
                                                    <div class="hr-line-dashed"></div>
                                                    <div class="form-group">
                                                       <label class="col-sm-2 control-label">小类值</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" placeholder="没有可不填" class="form-control" name="value"	>
                                                        </div>
                                                    </div>
                                                    <div class="hr-line-dashed"></div>
                                                    <div class="form-group">
                                                       <label class="col-sm-2 control-label">是否启用</label>
                                                        <div class="col-sm-10">
                                                            <div class="radio i-checks">
                                                                <label>
                                                                    <input type="radio" value="option1" name="status" value="0"> <i></i>否
                                                                </label>
                                                            </div>
                                                            <div class="radio i-checks">
                                                                <label>
                                                                    <input type="radio" checked="" name="status" value="1"> <i></i>是（选中）    
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="hr-line-dashed"></div>
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
        </div>
    </div>
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
    <script src="<%=basePath%>js/plugins/iCheck/icheck.min.js"></script>
    <script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
        $(function(){
            $(".full-height-scroll").slimScroll({height:"100%"});
            $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
        });
    </script>
	<script type="text/javascript" src="<%=basePath%>myjs/basecode.js"></script>
</body>
</html>
