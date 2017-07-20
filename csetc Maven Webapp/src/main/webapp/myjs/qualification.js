$(function(){
	
	$('#qualificationTable').bootstrapTable({
		data : "",
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
			field : 'customerName',
			title : '客户姓名',
			sortable : true
		}, {
			field : 'qualificationTypeId',
			title : '资质类型',
			sortable : true,
			formatter : function(value, row, index) {
				switch (value) {
				case '20170713000001':
					return "就业协议表";
					break;
				case '20170713000002':
					return "个人信息表";
					break;
				case '20170713000011':
					return "评测信息表";
					break;

				default:
					return "未知状态"
					break;
				}
			}
		}, {
			field : 'status',
			title : '资质状态',
			sortable : true,
			formatter : function(value, row, index) {
				switch (value) {
				case '0':
					return "已拒绝，待上传";
					break;
				case '1':
					return "已通过";
					break;
				case '2':
					return "待审核";
					break;

				default:
					return "未知状态"
					break;
				}
			}
		}, {
			field : 'qualificationMemo',
			title : '审核说明',
			sortable : true
		}, {
			field : 'creatorName',
			title : '提交人',
			sortable : true
		}, {
			field : 'createTime',
			title : '提交时间',
			sortable : true
		}]
	});
	
	$("#orderTable").bootstrapTable("hideColumn","promocode");
	$("#orderTable").bootstrapTable("hideColumn","memo");
	$("#orderTable").bootstrapTable("hideColumn","attachment");
	$("#orderTable").bootstrapTable("hideColumn","depttext");
	
});


function getOrderTableData(){
	var json;
	$.ajax({
		type:"POST",
		url:"../order/getOrderData.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json,function(i,o){
				o.createtime = new Date(o.createtime).format('yyyy-MM-dd');
			})
		},
		error:function(){
			toastr.error("获取订单数据失败！");
		}
	});
	return json;
}
