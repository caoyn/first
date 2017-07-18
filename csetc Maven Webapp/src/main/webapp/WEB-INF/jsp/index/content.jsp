<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 
 * 文件名: content.jsp </br>
 * 描述:登录首页中间内容</br>
 * 所属:湖南中软计算机系统服务有限公司</br>
 * 开发人员：李敏,只做复制整合 </br>
 * 创建时间：2017-04-17 </br> 
 -->
<!DOCTYPE HTML >
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>首页</title>

    <meta name="keywords" content="湖南中软计算机系统服务有限公司OA系统">
    <meta name="description" content="湖南中软计算机系统服务有限公司OA系统">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    
    <link href="<%=basePath%>css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="<%=basePath%>css/font-awesome.min93e3.css" rel="stylesheet">

	<!-- 通知插件 -->
	<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
	
    <link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.min862f.css" rel="stylesheet">

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>总收入</h5>
                        <div class="pull-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-xs btn-white" onclick="getGrossIncome('day',this)">天</button>
                                <button type="button" class="btn btn-xs btn-white active" onclick="getGrossIncome('month',this)">月</button>
                                <button type="button" class="btn btn-xs btn-white" onclick="getGrossIncome('year',this)">年</button>
                            </div>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins" id="income">40,886,200</h1>
                        <!-- <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i>
                        </div>
                        <small>总收入</small> -->
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>产品收入</h5>
                        <div class="pull-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-xs btn-white" onclick="getIncomeByProd('day',this)">天</button>
                                <button type="button" class="btn btn-xs btn-white active" onclick="getIncomeByProd('month',this)">月</button>
                                <button type="button" class="btn btn-xs btn-white" onclick="getIncomeByProd('year',this)">年</button>
                            </div>
                        </div>
                    </div>
                    <div class="ibox-content">
                    <table class="table table-bordered" id="prodIncome"></table>
                       <!--  <h1 class="no-margins">275,800</h1> -->
                       <!--  <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i>
                        </div>
                        <small>新订单</small> -->
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>订单</h5>
                        <div class="pull-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-xs btn-white active">天</button>
                                <button type="button" class="btn btn-xs btn-white">月</button>
                                <button type="button" class="btn btn-xs btn-white">年</button>
                            </div>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-9">
                                <div class="flot-chart">
                                    <!-- <div class="flot-chart-content" id="flot-dashboard-chart"></div>
                                    <div id="main"></div> -->
                                    
                                    <div class="echarts" id="echarts-bar-chart"></div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <ul class="stat-list">
                                    <li>
                                        <h2 class="no-margins">2,346</h2>
                                        <small>本月收款占全年收入比</small>
                                        <div class="stat-percent">48% <i class="fa fa-level-up text-navy"></i>
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 48%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <h2 class="no-margins ">4,422</h2>
                                        <small>本月返款占本月收款比</small>
                                        <div class="stat-percent">60% <i class="fa fa-level-down text-navy"></i>
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 60%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <h2 class="no-margins ">9,180</h2>
                                        <small>本月退款占本月收款比</small>
                                        <div class="stat-percent">22% <i class="fa fa-bolt text-navy"></i>
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 22%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>


       
    </div>

	<script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <!-- 百度echarts -->
    <script src="<%=basePath%>js/plugins/echarts/echarts-all.js"></script>
    <script src="<%=basePath%>js/content.min.js"></script>
    <!-- 通知插件 -->
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>  
    <script src="<%=basePath%>myjs/content.js"></script>
</body>


</html>
