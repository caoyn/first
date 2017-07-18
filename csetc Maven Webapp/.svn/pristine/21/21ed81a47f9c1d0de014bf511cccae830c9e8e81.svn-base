/**
 * 文件名: refund.js
 * 描述:订单相关数据展示及相关处理
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-27
 */
$(document).ready(function(){
	operBtn();
	//订单数据
	fillRefundTable();
});

//获得表格数据
function tablefdata(){
	var json;
	$.ajax({
		type:"POST",
		url:"../refund/getRefundData.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json,function(i,o){
				o.orderids = "<a onclick='approveDetail(\""+ o.mapproveid+"\")'>"+ o.orderid +"</a>";
				o.createtime = new Date(o.createtime).format("yyyy-MM-dd");
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
	$('#refundTable').bootstrapTable({
	    data:tablefdata(),
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#refundtoolbar",
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 20,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 10,             //最少允许的列数
        height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        idField:"id",
        showToggle:true,
	    columns: [{
	    	checkbox:true,
	    },
	    {
	        field: 'orderids',
	        title: '编号',
	        sortable:true
	    }, {
	    	field: 'customername',
	    	title: '客户姓名',
	    	sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '申请时间',
	    	sortable:true
	    }, {
	    	field: 'useridtext',
	    	title: '申请人',
	    	sortable:true
	    }, {
	    	field: 'prodname',
	    	title: '产品名称'
	    }, {
	    	field: 'amount',
	    	title: '应收款金额'
	    }, {
	    	field: 'amountcount',
	    	title: '已收款金额'
	    }, {
	    	field: 'refund',
	    	title: '申请金额' 
	    }, {
	    	field: 'memo',
	    	title: '备注'
	    }, {
	    	field: 'curruseridtext',
	    	title: '当前审批人'
	    }, {
	    	field: 'nextuseridtext',
	    	title: '下一审批人'
	    }, {
	    	field: 'astatus',
	    	title: '状态',
	    	sortable:true,
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "审批中";
	    		}else if(value == 1){
	    			return "审批完结";
	    		}else if(value == 2){
	    			return "取消审批" 
	    		}
	    	}
		}],
		onDblClickRow:function(row,ele,field){//双击行查看审批流详情
//			approveDetail(row.mapproveid);
		}
	});
	

}

//通过审批
function updAdoptApprove(){
	var checkedTableData = $("#refundTable").bootstrapTable("getSelections");
	if(checkedTableData.length!=1){
		toastr.warning("请先选择一条数据再操作");
		return false;
	}
	if(checkedTableData[0].curruserid != userid){
		toastr.warning("您不能操作该数据哦！");
		return false;
	}
	if(checkedTableData[0].astatus != 0){
		toastr.warning("该审批已经操作完毕，不能再进行操作！");
		return false;
	}
	$("#agreeApproveModal").modal("show");
}

function updAdoptApproveData(){
	var checkedTableData = $("#refundTable").bootstrapTable("getSelections");
	$.ajax({
		type:"POST",
		url:"../refund/adoptApprove.do",
		dataType:"json",
		data:{"approveid":checkedTableData[0].approveid,"mapproveid":checkedTableData[0].mapproveid,"memo":$("#agreeForm textarea[name='memo']").val()},
		async:false,
		success:function(data){
			if(data.indexOf("error")!=-1){
				toastr.warning(data);
			}else{
				toastr.success("操作成功");
				$("#refundTable").bootstrapTable("load",tablefdata());
				$("#agreeApproveModal").modal("hide");
			}
		},
		error:function(){
			toastr.warning("获取数据出错");
		}
	});
}

//驳回备注
function updRejectApproveShow(){
	var checkedTableData = $("#refundTable").bootstrapTable("getSelections");
	if(checkedTableData.length!=1){
		toastr.warning("请先选择一条数据再操作");
		return false;
	}
	if(checkedTableData[0].curruserid != userid){
		toastr.warning("您不能操作该数据哦！");
		return false;
	}
	if(checkedTableData[0].astatus != 0){
		toastr.warning("该审批已经操作完毕，不能再进行操作！");
		return false;
	}
	$("#rejectApproveModal").modal("show");
}

//驳回
function rejectApprove(){
	var checkedTableData = $("#refundTable").bootstrapTable("getSelections");
	if(!$("#rejectForm").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../refund/rejectApprove.do",
		dataType:"json",
		data:{"orderid":checkedTableData[0].orderid,"mapproveid":checkedTableData[0].mapproveid,"memo":$("#rejectForm textarea[name='memo']").val()},
		async:false,
		success:function(data){
			if(data.indexOf("error")!=-1){
				toastr.warning(data);
			}else{
				toastr.success("操作成功");
				$("#rejectApproveModal").modal("hide");
				$("#refundTable").bootstrapTable("load",tablefdata());
			}
			
		},
		error:function(){
			toastr.warning("获取数据出错");
		}
	});
}

//查看审批流详情
function approveDetail(mapproveid){
	$.ajax({
		type:"POST",
		url:"../refund/approveDetail.do",
		dataType:"json",
		data:{"mapproveid":mapproveid},
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			console.log(json);
			var approveDetailHtml = "<table class='table table-bordered'><tr><td>时间</td><td>审批人</td><td>备注</td><td>结果</td></tr>";
			$.each(json,function(i,o){
				var approveresult = "";
				if( o.approveresult == "0"){
					approveresult = "不同意";
				}else{
					approveresult = "同意";
				}
				approveDetailHtml +="<tr><td>"+new Date(o.approvetime).format("yyyy-MM-dd hh:mm:ss")+"</td>";
				approveDetailHtml +="<td>"+ o.useridtext +"</td>";
				approveDetailHtml +="<td>"+ o.memo +"</td>";
				approveDetailHtml +="<td>"+ approveresult +"</td></tr>";
				
			});
			approveDetailHtml += "</table>";
			
			toastr.options = {
					"closeButton": true, //是否显示关闭按钮
					"debug": false, //是否使用debug模式
					"positionClass": "toast-bottom-full-width",//弹出窗的位置
					"showDuration": "300",//显示的动画时间
					"hideDuration": "1000",//消失的动画时间
					"timeOut": "5000", //展现时间
					"extendedTimeOut": "1000",//加长展示时间
					"showEasing": "swing",//显示时的动画缓冲方式
					"hideEasing": "linear",//消失时的动画缓冲方式
					"showMethod": "fadeIn",//显示时的动画方式
					"hideMethod": "fadeOut" //消失时的动画方式
					};
			toastr.info(approveDetailHtml,"审批流程详情");
			
		},
		error:function(){
			toastr.warning("获取数据出错");
		}
	})
}


//退费弹出框
function addRefundModalShow(){
	var t = $("#refundTable").bootstrapTable("getSelections");
	console.log(t[0].status);
	if(t.length != 1){
		toastr.warning("请选择一条数据再操作");
		return false;
	}
	if(t[0].astatus != 1){
		toastr.warning("审批还没完成，您不能进行退款操作");
		return false;
	}else if(!(t[0].status == 1021 || t[0].status == 1031)){
		toastr.warning("退款已经完成，不能再次操作");
		return false;
	}
	$("#refundForm input[name='orderid']").val(t[0].orderid);
	$("#refundForm input[name='prodname']").val(t[0].prodname);
	$("#refundForm input[name='prodid']").val(t[0].prodid);
	$("#refundForm input[name='amount']").val((t[0].refund)*-1);
	//获得付款方式
	fillselectData("paymethod","#refundForm select[name='paymethod']");
	$("#refundModal").modal("show");
}


//付款方式
function paymethodChange(v){
	if(v!=1){
		fillselectData("bankingbook","#refundForm select[name='bankingbook']");
	}else{
		$("#refundForm select[name='bankingbook']").html("");
	}
}

//退费数据插入
function orderRefund(){
	swal({
        title: "确定该项退款操作吗",
        text: "确认后不能更改，请谨慎操作",
        type: "warning",
        showCancelButton: true,
        cancelButtonText:"取消",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        closeOnConfirm: false
    }, function () {
    	//新增退费数据
    	$.ajax({
    		type:"post",
    		url:"../payment/addOrderRefund.do",
    		data:$("#refundForm").serialize(),
    		dataType:"json",
    		async:false,
    		success:function(data){
    			swal("操作成功！", "该款项已退。", "success");
    			$("#refundTable").bootstrapTable("load",tablefdata());
    			$("#refundModal").modal("hide");
    		}
    	})
    });
	
}

//页面上可操作的按钮
function operBtn(){
//	operstr//可页面上可操作的按钮
	//将所有操作按钮禁用
	$("#refundtoolbar button").attr("disabled", true);
	var operbtns = operstr.split("|");//可操作的按钮
	console.log(operbtns);
	var btns = $("#refundtoolbar button"); //页面上所按钮
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
function addRemarkShow(){
	
	var tabledata = $("#refundTable").bootstrapTable("getSelections");
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
	addressInit("servicetype","remarktype","1008","");
	addressInit("servicetype","remarktype","1009","");
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

function addSeeHistoryRemark(){
	var d= $('#refundTable').bootstrapTable('getSelections');
	if(d.length != 1){
		toastr.warning("请选择一条数据再操作");
		return false;
	}
	$.ajax({
		type:"post",
		url:"../remark/qryCustomerRemrk.do",
		data:{"customerid":d[0].customerid},
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
