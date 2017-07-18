/**
 * 文件名: customerdata.js
 * 描述:客户数据
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-15
 */
$(document).ready(function(){
//	operBtn();
//	fillTableData();
	
	//获得各下拉框数据
	//获得付款方式
	fillselectData("education","#conditionForm select[name='education']","","学历");
	fillselectData("major","#conditionForm select[name='major']","","专业");
	fillselectData("paymethod","#conditionForm select[name='collector']","","采集人");
	fillselectData("source","#conditionForm select[name='source']","","来源渠道");
	fillselectData("custype","#conditionForm select[name='customertype']","","客户类型");
	fillselectData("cuscourse","#conditionForm select[name='course']","","意向课程");
	fillselectData("jobobjective","#conditionForm select[name='jobobjective']","","求职意向");
	
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		var tabText = $(e.target).text();//当前激活的标签页
		switch (tabText) {
		case "分配数据": assingdata(); break;
		case "预约数据": reservationdata(); break;
		case "回访数据": callbackdata(); break;
		default:
			break;
		}
	});
	
});

//按条件查询客户信息
function condition(){
	var json;
	$.ajax({
		type:"post",
		url:"../customer/condition.do",
		data:$("#conditionForm").serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			if(json.length == 0){
				toastr.info("没有相关客户数据");
			}else{
				$.each(json,function(i,o){
					var year = new Date().getFullYear();
					var byear = new Date(o.birthday).format("yyyy");
					o.age = year - byear;
					o.oper = '<select cid="'+ o.customerid +'" name="remarkOper" onchange="selectoperation(this)">'+
		        			'<option value="">请选择操作</option>'+
		        			'<option value="1">查看客户批注</option>'+
		        			'<option value="2">查看客户详情</option>'+
		        			'<option value="3">查看客户订单</option>'+
		        		'</select>';
				})
			}
		},
		error:function(){
			toastr.error("获得数据失败");
		}
	});
	$("#customerDataTable").bootstrapTable("load",json);
	return json;
}

//填充表格数据
function fillTableData(){
	$('#customerDataTable').bootstrapTable({
	    data:condition(),
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#customertoolbar",
       // queryParams: oTableInit.queryParams,//传递参数（*）
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        //clickToSelect: true,                //是否启用点击选中行
        //height: 670,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "customerid",                     //每一行的唯一标识，一般为主键列
        idField:"customerid",
	    columns: [
	    {
	        field: 'customerid',
	        title: '编号',
	        sortable:true
	    }, {
	    	field: 'customername',
	    	title: '姓名',
	    	sortable:true
	    }, {
	    	field: 'sex',
	    	title: '性别',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "女";
	    		}else{
	    			return "男";
	    		}
	    	},
	    	sortable:true
	    }, {
	    	field: 'telephone',
	    	title: '电话',
	    	sortable:true
	    }, {
	    	field: 'email',
	    	title: 'qq/邮箱',
	    	sortable:true
	    }, {
	    	field: 'school',
	    	title: '学校',
	    	sortable:true
	    }, {
	    	field: 'age',
	    	title: '年龄'
	    }, {
	    	field: 'address',
	    	title: '现居住地'
	    }, {
	    	field: 'memo',
	    	title: '备注'
	    }, {
	    	field: 'assigntype',
	    	title: '分配状态',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "待分配";
	    		}else if(value == 1){
	    			return "已分配";
	    		}else if(value == 2){
	    			return "待重新分配"
	    		}else if(value == 9){
	    			return "已完结";
	    		}
	    	}
		}, {
			field: 'oper',
	    	title: '操作',
		}],
		onDblClickRow:function(row,ele,field){//双击行
			customerinfoUpdAndShow(row);
		}
	});
}

//操作信息
function selectoperation(select){
//	alert(select.value);
//	alert(select.attributes['cid'].nodeValue);
	switch (select.value) {
	case '1':
		qryCustomerRemrk(select.attributes['cid'].nodeValue);
		break;
	case '2':
		customerinfodata(select.attributes['cid'].nodeValue);
		break;
	case '3':
		customerOrder(select.attributes['cid'].nodeValue);
		break;
	default:
		break;
	}
}

//查看批注信息
function qryCustomerRemrk(cid){
	$.ajax({
		type:"post",
		url:"../remark/qryCustomerRemrk.do",
		data:{"customerid":cid},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length == 0){
				toastr.info("没有相关客户数据");
			}else{
				var stageslist = servicetype.stagesList;
				$.each(json,function(i,o){
					$.each(stageslist,function(j,p){
						if(o.servicetype == p.value){
							o.servicetypetext = p.stage;
							$.each(p.stageList,function(k,b){
								if(o.remarktype == b.value){
									o.remarktypetext = b.stage;
								}
							});
						}
					});
					o.time = new Date(o.remarktime).format("yyyy-MM-dd hh:mm:ss");
				});
				//console.log(json);
				createTimeaxisData(json);
			}
		},
		error:function(){
			toastr.error("获得数据失败");
		}
	});
}

//生成时间轴的数据
function createTimeaxisData(data){
	var ta = "";
	$.each(data,function(i,o){ 
		ta += 	'<div class="timeline">'+
					'<div class="timeline-content">'+
						'<span class="date">'+
							'<span class="month">'+ o.time +'</span>'+
						'</span>'+
						'<h4 class="title">'+ o.servicetypetext +'</h4>'+
						'<p class="description">'+o.memo+'</p>'+
						'<div class="footer text-right"> <span>'+o.username+'</span></div>'+
					'</div>'+
				'</div>';
	});
	$("#mytimeaxis").html(ta);
	$("#customerRemarkModal").modal("show");
}

//客户信息展示
function customerinfodata(cid){
	var data = $("#customerDataTable").bootstrapTable("getData");
	var cus = "";
	$.each(data,function(i,o){
		if(cid == o.customerid){
			o.educationtext = selectText("education",o.education);
			o.majortext = selectText("major",o.major);
			o.sourcetext = selectText("source",o.source);
			o.customertypetext = selectText("custype",o.customertype);
			o.coursetext = selectText("cuscourse",o.course);
			o.politicalstatustext = selectText("politicalstatus",o.politicalstatus);
			o.nationalitytext = selectText("nationality",o.nationality);
			o.maritalstatustext = selectText("maritalstatus",o.maritalstatus);
			o.workplacetext = selectText("cussourcearea",o.workplace);
			o.jobobjectivetext = selectText("jobobjective",o.jobobjective);
			o.salarytext = selectText("salary",o.salary);
			if(o.sex == 1){
				o.sextext = "男";
			}else{
				o.sextext = "女";
			}
			cus = o;
			return false;
		}
	});
	var tablehtml = "<tr><td>姓名</td><td>"+ cus.customername +"</td><td>性别</td><td>"+ cus.sextext +"</td></tr>";
	    tablehtml += "<tr><td>电话</td><td>"+ cus.telephone +"</td><td>邮箱</td><td>"+ cus.email +"</td></tr>";
	    tablehtml += "<tr><td>学校</td><td>"+ cus.school +"</td><td>专业</td><td>"+ cus.majortext +"</td></tr>";
	    tablehtml += "<tr><td>学历</td><td>"+ cus.educationtext +"</td><td>来源</td><td>"+ cus.sourcetext +"</td></tr>";
	    tablehtml += "<tr><td>类型</td><td>"+ cus.customertypetext +"</td><td>意向课程</td><td>"+ cus.coursetext +"</td></tr>";
	    tablehtml += "<tr><td>求职意向</td><td>"+ cus.jobobjectivetext +"</td><td>出生年月</td><td>"+ new Date(cus.birthday).format("yyyy-MM-dd") +"</td></tr>";
	    tablehtml += "<tr><td>户口所在地</td><td>"+ cus.placeofbirth +"</td><td>现居住地</td><td>"+ cus.address +"</td></tr>";
	    tablehtml += "<tr><td>政治面貌</td><td>"+ cus.politicalstatustext +"</td><td>民族</td><td>"+ cus.nationalitytext +"</td></tr>";
	    tablehtml += "<tr><td>婚育状况</td><td>"+ cus.maritalstatustext +"</td><td>工作年限</td><td>"+ cus.workservice+"</td></tr>";
	    tablehtml += "<tr><td>工作经历</td><td>"+ cus.workexp +"</td><td>期望工作地点</td><td>"+ cus.workplacetext +"</td></tr>";
	    tablehtml += "<tr><td>期望薪资</td><td>"+ cus.salarytext +"</td><td>紧急联系人方式</td><td>"+ cus.ecp +"</td></tr>";
	    tablehtml += "<tr><td>开户行</td><td>"+ cus.bank +"</td><td>银行卡号</td><td>"+ cus.bankno +"</td></tr>";
	    tablehtml += "<tr><td>操作者</td><td>"+ cus.bank +"</td><td>采集人</td><td>"+ cus.bankno +"</td></tr>";
	$("#customerTable").html(tablehtml);
	$("#CustomerInfoModal input[name='customerid']").val(cid);
	 $('#myTab li:eq(0) a').tab('show');
	$("#CustomerInfoModal").modal("show");
	//1.基本信息
	//2.分配
	//3.预约
	//4.回访
}

//客户订单展示
function customerOrder(cid){
	//1.查询数据
	$.ajax({
		type:"post",
		url:"../order/getOrderDataByCid.do",
		data:{"customerid":cid},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length == 0){
				toastr.info("没有该客户的订单数据");
			}else{
				console.log(json);
				var orderhtml = "<tr><td>编号</td><td>产品名称</td><td>产品价格</td><td>应收金额</td><td>已收金额</td><td>状态</td>";
				$.each(json,function(i,o){
					console.log(o.status);
					var status = "";
					switch(o.status){
					case '1011': status = "待收款"; break;
					case '1012': status = "已收款，未全款"; break;
					case '1013': status = "已全款"; break;
					case '1014': status = "订单废弃"; break;
					case '1015': status = "客户取消订单"; break;
					case '1021': status = "退款处理中"; break;
					case '1022': status = "退款完成"; break;
					case '1023': status = "退款取消"; break;
					case '1031': status = "返款处理中"; break;
					case '1032': status = "返款财务已处理"; break;
					case '1033': status = "返款申请取消"; break;
					default:
						break;
					}
					orderhtml +="<tr><td>"+o.orderid+"</td>";
					orderhtml +="<td>"+o.prodname+"</td>";
					orderhtml +="<td>"+o.price+"</td>";
					orderhtml +="<td>"+o.amount+"</td>";
					orderhtml +="<td>"+o.amountcount+"</td>";
					orderhtml +="<td>"+status+"</td></tr>";
				});
				
				$("#orderTable").html(orderhtml);
				$("#orderModal").modal("show");
			}
		},
		error:function(){
			toastr.error("获得数据失败");
		}
	});
}

//分配数据
function assingdata(){
	$.ajax({
		type:"post",
		url:"../TbCustomerAssign/getAssignByCid.do",
		data:{"customerid":$("#CustomerInfoModal input[name='customerid']").val()},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length==0){
				toastr.info("没有相关数据");
				$("#assignTable").html("");
			}else{
				
				var assignhtml = "<tr><td>分配时间</td><td>处理时间</td><td>分配人</td><td>拥有者</td><td>分配状态</td></tr>";
				$.each(json,function(i,o){
					var status = "";
					switch (o.status) {
					case '0': status = '分配跟进中';break;
					case '1': status = '处理完毕';break;
					case '2': status = '主动放弃';break;
					case '3': status = '被动放弃';break;
					default:
						break;
					}
					assignhtml += '<tr><td>'+ new Date(o.assigntime).format("yyyy-MM-dd hh:mm") +'</td>';
					assignhtml += '<td>'+ new Date(o.processtime).format("yyyy-MM-dd hh:mm") +'</td>';
					assignhtml += '<td>'+ o.assigntext +'</td>';
					assignhtml += '<td>'+ o.usertext +'</td>';
					assignhtml += '<td>'+ status +'</td></tr>';
				});
				
				$("#assignTable").html(assignhtml);
			}
		},
		error:function(){
			roastr.error("数据加载出错");
		}
	});
}

//预约数据
function reservationdata(){
	//alert($("#CustomerInfoModal input[name='customerid']").val());
	$.ajax({
		type:"post",
		url:"../reservation/getReservationByCid.do",
		data:{"customerid":$("#CustomerInfoModal input[name='customerid']").val()},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length==0){
				toastr.info("没有相关数据");
				$("#reservationTable").html("");
			}else{
				console.log(json);
				var html = "<tr><td>预约目的</td><td>预约地点</td><td>预约人</td><td>接待人</td><td>预约时间</td><td>预计到达时间</td><td>上门时间</td><td>沟通内容</td><td>状态</td></tr>";
				$.each(json,function(i,o){
					var status = "";
					switch (o.status) {
					case '0': status = '待预约';break;
					case '1': status = '上门咨询';break;
					case '2': status = '已签单';break;
					case '3': status = '流失';break;
					case '4': status = '回到回访';break;
					default:
						break;
					}
					html += '<tr><td>'+ selectText("cusreservation",o.purpose) +'</td>';
					html += '<td>'+ selectText("arriveaddress",o.zone) +'</td>';
					html += '<td>'+ o.reporttext +'</td>';
					html += '<td>'+ o.receivertext +'</td>';
					html += '<td>'+ new Date(o.reservationtime).format("yyyy-MM-dd hh:mm") +'</td>';
					html += '<td>'+ new Date(o.expecttime).format("yyyy-MM-dd hh:mm") +'</td>';
					html += '<td>'+ new Date(o.arrivetime).format("yyyy-MM-dd hh:mm") +'</td>';
					html += '<td>'+ o.memo +'</td>';
					html += '<td>'+ status +'</td></tr>';
				});
				$("#reservationTable").html(html);
			}
		},
		error:function(){
			roastr.error("数据加载出错");
		}
	});
}

//回访数据
function callbackdata(){
	$.ajax({
		type:"post",
		url:"../callback/getCallbackByCid.do",
		data:{"customerid":$("#CustomerInfoModal input[name='customerid']").val()},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length==0){
				toastr.info("没有相关数据");
				$("#callbackTable").html("");
			}else{
				console.log(json);
				var html = "<tr><td>回访原因</td><td>回访时间</td><td>报告人</td><td>意向度</td><td>是否测试</td><td>提醒内容</td><td>回访来源</td><td>状态</td></tr>";
				$.each(json,function(i,o){
					var status = "";
					switch (o.status) {
					case '0': status = '初始状态，未操作';break;
					case '1': status = '已提交预约';break;
					case '2': status = '已提交再次回访';break;
					case '3': status = '关闭';break;
					default:
						break;
					}
					var callbacktype = "";
					switch (o.callbacktype) {
					case '1': callbacktype = '分配后回访';break;
					case '2': callbacktype = '预约后回访';break;
					case '3': callbacktype = '咨询后回访';break;
					case '4': callbacktype = '回访后再回访';break;
					default:
						break;
					}
					html += '<tr><td>'+ selectText("notcusreservation",o.reseaon) +'</td>';
					html += '<td>'+ new Date(o.calltime).format("yyyy-MM-dd hh:mm") +'</td>';
					html += '<td>'+ o.reropttext +'</td>';
					html += '<td>'+ (o.intention == '0' ? "无":"有") +'</td>';
					html += '<td>'+ (o.testing == '0' ? "否":"是") +'</td>';
					html += '<td>'+ o.tips +'</td>';
					html += '<td>'+ callbacktype +'</td>';
					html += '<td>'+ status +'</td></tr>';
				});
				$("#callbackTable").html(html);
			}
		},
		error:function(){
			roastr.error("数据加载出错");
		}
	});
}