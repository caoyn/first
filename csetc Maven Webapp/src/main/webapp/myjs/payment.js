/**
 * 文件名: payment.js
 * 描述:订单收款相关数据展示及相关处理
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-27
 */
$(document).ready(function(){
	//订单数据
	fillRefundTable();
	operBtn();
});

//获得表格数据
function tablefdata(){
	var json;
	$.ajax({
		type:"POST",
		url:"../order/notPayOrder.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			console.log(json);
			$.each(json,function(i,o){
				o.createtime = new Date(o.createtime).format("yyyy-MM-dd");
				o.oper = '<button type="button" class="btn btn-xs btn-primary" onclick="addPaymentModalShow(\''+o.orderid+'\',\''+o.paymethod+'\',\''+o.paymethodtext+'\',\''+o.bankingbook+'\',\''+o.bankingbookname+'\')"><i class="fa fa-yen"></i> 收款</button>';
			})
		},
		error:function(){
			toastr.warning("获取数据出错");
		}
	});
	return json;
}

//填充表格数据
function fillRefundTable(){
	$('#notPayOrderTable').bootstrapTable({
	    data:tablefdata(),
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#notPayOrdertoolbar",
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 12,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 10,             //最少允许的列数
        height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "orderid",                     //每一行的唯一标识，一般为主键列
        idField:"orderid",
        showToggle:true,
	    columns: [{
	    	checkbox:true,
	    },
	    {
	        field: 'orderid',
	        title: '订单编号',
	        sortable:true
	    }, {
	    	field: 'customertext',
	    	title: '客户姓名',
	    	sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '下单时间',
	    	sortable:true
	    }, {
	    	field: 'prodname',
	    	title: '产品名称'
	    }, {
	    	field: 'price',
	    	title: '产品价格'
	    }, {
	    	field: 'deposit',
	    	title: '定金'
	    }, {
	    	field: 'discount',
	    	title: '折扣金额'
	    }, {
	    	field: 'amount',
	    	title: '应收款金额'
	    }, {
	    	field: 'bankingbookname',
	    	title: '银行账簿' 
	    }, {
	    	field: 'usertext',
	    	title: '销售',
	    	sortable:true
	    }, {
	    	field: 'status',
	    	title: '状态',
	    	sortable:true,
	    	formatter:function(value,row,index){
	    		if(value == 1011){
	    			return "待收款";
	    		}else if(value == 1012){
	    			return "已收，未全款";
	    		}
	    	}
		},{
			field: 'oper',
	    	title: '操作',
		}],
		onDblClickRow:function(row,ele,field){//双击行查看审批流详情
//			approveDetail(row.mapproveid);
		}
	});
}

//收款信息展示
function addPaymentModalShow(orderid,paymethod,paymethodtext,bankingbook,bankingbookname){
	if((typeof orderid) == "undefined"){
		var tmp = $("#notPayOrderTable").bootstrapTable("getSelections");
		if(tmp.length != 1){
			toastr.warning("请选择一条数据再操作");
			return false;
		}else{
			orderid = tmp[0].orderid;
			paymethodtext = tmp[0].paymethodtext;
			bankingbookname = tmp[0].bankingbookname;
			paymethod = tmp[0].paymethod;
			bankingbook = tmp[0].bankingbook;
			
		}
	}
	//获得订单详情数据
	var pay = '<input type="hidden" name="orderid"/><input type="hidden" name="paymethod"/><input type="hidden" name="bankingbook"/>';
	
	pay += '<div class="form-group"><label class="col-sm-2">付款方式：</label><div class="col-sm-2 text-left text-warning">'+paymethodtext+'</div>';
	if(bankingbookname != "undefined" &&　(typeof bankingbookname) != "undefined"){
		pay += '<label class="col-sm-2">银行账簿：</label><div class="col-sm-4 text-left text-warning">'+bankingbookname+'</div>';
	}
	pay += '</div>'	
	//将数据填充到表格
	$("#addPaymentForm").html(pay+orderDetailTabel(orderdetailData(orderid)));
	$("#addPaymentForm input[name='orderid']").val(orderid);
	$("#addPaymentForm input[name='paymethod']").val(paymethod);
	if(bankingbook != 'undefined'){
		$("#addPaymentForm input[name='bankingbook']").val(bankingbook);
	}
	
	 $("#addPaymentModal .btn-primary").removeAttr("disabled"); //设置收款按钮可用
	//不可用 $("#submit-button").attr("disabled", true);
	$("#addPaymentModal").modal("show");
}

//根据订单编号查看订单详情
function orderdetailData(orderid){
	var result;
	$.ajax({
		type:"post",
		url:"../order/getNotPayOrderDataByOid.do",
		data:{"orderid":orderid},
		dataType:"json",
		async:false,
		success:function(data){
			result = JSON.parse(data);
		},
		error:function(){
			toastr.error("获得订单详情失败");
		}
	});
	return result;
}

//处理订单详情表格数据，用于展示给收款人
function orderDetailTabel(orderDetail){
	var html = "<table id='orderDetailTable' class='table'><tr><td>子产品编号</td><td>子产品名称</td><td>应交金额</td><td>已交金额</td><td>此次收款金额</td></tr>";
	$.each(orderDetail,function(i,o){
		html += "<tr><td>"+ o.prodid  +"</td>";
		html += "<td>"+ o.prodname  +"</td>";
		html += "<td>"+ o.amount  +"</td>";
		html += "<td>"+ o.amountcount  +"</td>";
		if(o.amount == o.amountcount){
			html += "<td><input type='text' size='10' name='amount' placeholder='该款项已收完' readonly='readonly'/></td></tr>";
		}else{
			html += "<td><input type='text' size='10' name='amount'/></td></tr>";
		}
		
	});
	html += "</table>"
	return html;
}

//新增收款信息
function addOrderPayment(){
	$("#addPaymentModal .btn-primary").attr("disabled", true);
	//获得表格中的数据
	var subprods = $("#orderDetailTable").find("tbody").find("tr");
	var subprodAry = [];//子产品json数据
	var flag = true;
	$.each(subprods,function(i,o){
		if(i == 0){
			return true;
		}
		var json = {"prodid":"",
					"prodname":"",
					"price":"0",
					"amount":"0",
					"orderid":"",
					"paymethod":"",
					"bankingbook":""};
		
		json.prodid = $(this).find("td:eq(0)").text();
		json.prodname = $(this).find("td:eq(1)").text();
		json.price = $(this).find("td:eq(2)").text();
		json.amount = $(this).find("td:eq(4)").children().val(); 
		json.orderid = $("#addPaymentForm input[name='orderid']").val();
		json.paymethod = $("#addPaymentForm input[name='paymethod']").val();
		json.bankingbook = $("#addPaymentForm input[name='bankingbook']").val();
		if(isNaN(json.amount)){
			toastr.warning("您输入的不是数字");
			flag = false;
			$(this).find("td:eq(4)").children().val("");
			return false;
		}
		if(json.price - ($(this).find("td:eq(3)").text()) < json.amount){
			toastr.warning("您输入的金额超出范围");
			flag = false;
			$(this).find("td:eq(4)").children().val("");
			return false;
		}
		subprodAry.push(json);
	});
	if(!flag){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../payment/addPaymentInfo.do",
		data:{"payment":window.JSON.stringify(subprodAry)},
		dataType:"json",
		async:false,
		success:function(data){
			toastr.success("新增收款成功");
			$("#addPaymentModal").modal("hide");
			$("#notPayOrderTable").bootstrapTable("load",tablefdata());
			operBtn();
		},
		error:function(){
			toastr.error("新增收款失败");
		}
	});
	 
}

//页面上可操作的按钮
function operBtn(){
//	operstr//可页面上可操作的按钮
	//将所有操作按钮禁用
	$("button").attr("disabled", true);
	
	var operbtns = operstr.split("|");//可操作的按钮
	console.log(operbtns);
	var btns = $("button"); //页面上所按钮
	$.each(btns,function(i,o){
		var fun = $(this).attr("onclick");//获得每个按钮的onclick属性值
		if(typeof fun !="undefined"){
			for(var i = 0; i < operbtns.length; i++){
				//alert(fun.toLocaleLowerCase().substring(0,6));
				if(operbtns[i] == "1" && fun.toLocaleLowerCase().substring(0,3).indexOf("add") == 0){
					$(this).removeAttr("disabled");
				}
				if(operbtns[i] == "2" && fun.toLocaleLowerCase().substring(0,3).indexOf("upd") == 0){
					$(this).removeAttr("disabled");
				}
				if(operbtns[i] == "3" && fun.toLocaleLowerCase().substring(0,3).indexOf("del") == 0){
					$(this).removeAttr("disabled");
				}
				if(operbtns[i] == "4" && fun.toLocaleLowerCase().substring(0,3).indexOf("qry") == 0){
					$(this).removeAttr("disabled");
				}
				if(operbtns[i] == "5" && fun.toLocaleLowerCase().substring(0,6).indexOf("upload") == 0){
					$(this).removeAttr("disabled");
				}
				if(operbtns[i] == "6" && fun.toLocaleLowerCase().substring(0,6).indexOf("assign") == 0){
					
					$(this).removeAttr("disabled");
				}
			}
		}
	})
}

//新增客户批注展示
function updRemarkShow(){
	
	var tabledata = $("#notPayOrderTable").bootstrapTable("getSelections");
	if(tabledata.length!=1){
		toastr.warning("请选择一条数据再进行此操作");
		return false;
	}
	//判断页面中是否有客户批注模态框
	if((typeof $("#remarkModal").html()) != "undefined"){
		$("#remarkModal").remove();
	}
	//构造模态框代码
	var modalHtml = 
	'<div class="modal inmodal" id="remarkModal" tabindex="-1" role="dialog" aria-hidden="true">'+
    '<div class="modal-dialog ">'+
        '<div class="modal-content animated fadeIn">'+
            '<div class="modal-header">'+
                '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>'+
                '</button>'+
                '<h4 class="modal-title">标记客户</h4>'+
            '</div>'+
            '<div class="row">'+
             '<div class="col-sm-12">'+
               '<div class="ibox float-e-margins">'+
                   '<div class="ibox-content">'+
						'<form id="remarkcustomerForm" enctype="multipart/form-data" method="post" class="form-horizontal">'+
							'<input type="hidden" name="customerid" value="'+tabledata[0].customerid+'">'+
							'<div class="form-group" >'+
							 	'<label class="col-sm-3 control-label">客户所属阶段：</label>'+
	 	 						'<div class="col-sm-6">'+
	 	 							'<select name="servicetype" id="servicetype" class="form-control">'+
	 	 							'</select>'+
	 							'</div>'+
	 						'</div>'+
							'<div class="form-group">'+
							 	'<label class="col-sm-3 control-label">阶段对应类型：</label>'+
	 	 						'<div class="col-sm-6">'+
	 	 							'<select name="remarktype" id="remarktype" class="form-control"></select>'+
	 							'</div>'+
	 						'</div>'+
							'<div class="form-group" >'+
							 	'<label class="col-sm-3 control-label">附件：</label>'+
	 	 						'<div class="col-sm-6">'+
	 	 							'<input type="text" name="attachment" class="form-control">'+
	 							'</div>'+
	 						'</div>'+
							'<div class="form-group" >'+
							 	'<label class="col-sm-3 control-label">备注：</label>'+
	 	 						'<div class="col-sm-6">'+
	 	 							'<textarea class="form-control" rows="" cols="" name="memo" required="required"></textarea>'+
	 							'</div>'+
	 						'</div>'+
						'</form>'+
                    '</div>'+
                   '</div>'+
                 '</div>'+
             '</div>'+
             '<div class="modal-footer">'+
                ' <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>'+
                ' <button type="button" class="btn btn-primary" onclick="addRemark()">确定</button>'+
            '</div>'+
        '</div>'+
    '</div>'+
'</div>';
	
	
	//将模态框放入页面中
	$("body").append(modalHtml);
	//客户所属阶段的对应的类型
	//fillselectData("remarktype","#remarkcustomerForm select[name='remarktype']");
	//客户所属阶段联动阶段类型
	addressInit("servicetype","remarktype","1007","");
	//显示模态框
	$("#remarkModal").modal("show");
}

//新增客户批注
function addRemark(){
	if(!$("#remarkcustomerForm").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../remark/addRemark.do",
		data:$("#remarkcustomerForm").serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			toastr.success("新增客户批注成功");
			$("#remarkModal").modal("hide");
		},
		error:function(){
			toastr.error("新增客户数据失败");
		}
	})
}

