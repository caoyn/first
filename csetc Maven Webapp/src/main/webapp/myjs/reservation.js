/**
 * 文件名: reservation.js
 * 描述:客户分配后的数据
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-11
 */
$(document).ready(function(){
	operBtn();
	fillTableData();
	$(".search input").attr("onkeydown","keyDownGetReservationData(event)");//为表格搜索框的内容添加回车事件
	var messageOpts = {
			closeButton : true, //是否显示关闭按钮
			debug : false, //是否使用debug模式
			positionClass: "toast-top-right",  
			//onclick : null,
			showDuration : "40", //显示的动画时间
			hideDuration : "1000", //消失的动画时间
			timeOut: "10000", //展现时间
			extendedTimeOut : "1000", //加长展示时间
			showEasing : "swing", //显示时的动画缓冲方式
			hideEasing : "linear", //消失时的动画缓冲方式
			showMethod : "fadeIn", //显示时的动画方式
			hideMethod : "fadeOut" //消失时的动画方式
		};
	toastr.options = messageOpts; //消息通知插件配置
	
});

//获得表格数据
function getAllCustomerData(){
	var json = [];
	$.ajax({
		type:"POST",
		url:"../reservation/getReservationCustomerData.do",
		dataType:"json",
		async:false,
		success:function(data){
			//处理json数据
			console.log(JSON.parse(data));
			$.each(JSON.parse(data), function(i, o) {
				var c = o.customer;
				
				c.majortext = selectText("major",c.major);
				c.educationtext = selectText("education",c.education);
				c.sourcetext = selectText("source",c.source);
				c.customertypetext = selectText("custype",c.customertype);
				c.coursetext = selectText("cuscourse",c.course);
				c.jobobjectivetext = selectText("jobobjective",c.jobobjective);
				c.politicalstatustext = selectText("politicalstatus",c.politicalstatus);
				c.nationalitytext = selectText("nationality",c.nationality);
				c.maritalstatustext = selectText("maritalstatus",c.maritalstatus);
				c.workplacetext = selectText("maritalstatus",c.workplace);
				c.salarytext = selectText("salary",c.salary);
				c.purposetext = selectText("cusreservation",o.purpose);
				c.zonetext = selectText("arriveaddress",o.zone);
				c.moeor = o.memo;
				c.receivertext = o.receivertext;
				c.reporttext = o.reporttext;
				c.statusrep = o.status;
				c.reservationid = o.reservationid
				c.createtime = new Date(c.createtime).format('yyyy-MM-dd');
				c.expecttime = new Date(o.expecttime).format('yyyy-MM-dd hh:mm:ss');
				c.reservationtime = new Date(o.reservationtime).format('yyyy-MM-dd hh:mm:ss');
				c.receiver = o.receiver;
				c.reportuserid = o.reportuserid;
				json.push(c);
            });
		},
		error:function(){
			toastr.error("出错啦");
		}
	});
	console.log(json);
	return json;
}

//客户信息展示
//查看表格数据
function fillTableData(){
	var json = getAllCustomerData();
	$('#customerReservationData').bootstrapTable({
	    data:json,
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#customerreservationtoolbar",
//        queryParams: oTableInit.queryParams,//传递参数（*）
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 5,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 670,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "customerid",                     //每一行的唯一标识，一般为主键列
        idField:"customerid",
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
	    columns: [{
	    	radio:true,
	    },
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
	    	field: 'major',
	    	title: '专业',
	    	sortable:true
	    }, {
			field: 'educationtext',
			title: '学历',
			sortable:true
	    }, {
	    	field: 'operatortext',
	    	title: '操作者',
			sortable:true
	    }, {
	    	field: 'collectortext',
	    	title: '采集人',
			sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '创建时间',
			sortable:true
	    }, {
	    	field: 'sourcetext',
	    	title: '来源渠道',
			sortable:true
	    }, {
	    	field: 'customertypetext',
	    	title: '客户类型',
			sortable:true
	    }, {
	    	field: 'coursetext',
	    	title: '意向课程',
	    	sortable:true
	    }, {
	    	field: 'jobobjectivetext',
	    	title: '求职意向',
	    	sortable:true
	    }, {
	    	field: 'birthday',
	    	title: '年龄',
	    	formatter:function(value,row,index){
	    		return computeAge(value);
	    	}
	    }, {
	    	field: 'placeofbirth',
	    	title: '户口所在地'
	    }, {
	    	field: 'address',
	    	title: '现居住地'
	    }, {
	    	field: 'politicalstatustext',
	    	title: '政治面貌'
	    }, {
	    	field: 'nationalitytext',
	    	title: '民族'
	    }, {
	    	field: 'maritalstatustext',
	    	title: '婚育状况'
	    }, {
	    	field: 'workservice',
	    	title: '工作年限'
	    }, {
	    	field: 'workexp',
	    	title: '工作经历'
	    }, {
	    	field: 'workplacetext',
	    	title: '期望工作地点'
	    }, {
	    	field: 'salarytext',
	    	title: '期望薪资'
	    }, {
	    	field: 'ecp',
	    	title: '紧急联系人方式'
	    }, {
	    	field: 'bank',
	    	title: '开户行'
	    }, {
	    	field: 'bankno',
	    	title: '银行卡号'
	    }, {
	    	field: 'memor',
	    	title: '备注'
	    }, {
	    	field: 'statusrep',
	    	title: '状态',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "已预约";
	    		}else if(value == 1){
	    			return "上门咨询";
	    		}else if(value == 2){
	    			return "已签单";
	    		}else if(value == 3){
	    			return "已流失";
	    		}else if(value == 4){
	    			return "回到回访";
	    		}
	    	},
	    	sortable:true
	    }, {
	    	field: 'reporttext',
	    	title: '预约人'
	    }, {
	    	field: 'reservationtime',
	    	title: '预约时间',
	        sortable:true
	    }, {
	    	field: 'expecttime',
	    	title: '预计到达时间',
	        sortable:true
	    }, {
	    	field: 'purposetext',
	    	title: '预约目的'
	    }, {
	    	field: 'zonetext',
	    	title: '预约校区',
	    	sortable:true
	    }],
		onClickRow:function(row,ele,field){//单击行预约客户
		}
	});
	
	hideTableCol();
}

//隐藏表格列
function hideTableCol(){
	$("#customerReservationData").bootstrapTable("hideColumn","customerid");
	$("#customerReservationData").bootstrapTable("hideColumn","operatortext");
	$("#customerReservationData").bootstrapTable("hideColumn","collectortext");
	$("#customerReservationData").bootstrapTable("hideColumn","educationtext");
	$("#customerReservationData").bootstrapTable("hideColumn","sourcetext");
	$("#customerReservationData").bootstrapTable("hideColumn","customertype");
	$("#customerReservationData").bootstrapTable("hideColumn","coursetext");
	$("#customerReservationData").bootstrapTable("hideColumn","jobobjectivetext");
	$("#customerReservationData").bootstrapTable("hideColumn","placeofbirthtext");
	$("#customerReservationData").bootstrapTable("hideColumn","address");
	$("#customerReservationData").bootstrapTable("hideColumn","politicalstatustext");
	$("#customerReservationData").bootstrapTable("hideColumn","nationalitytext");
	$("#customerReservationData").bootstrapTable("hideColumn","maritalstatustext");
	$("#customerReservationData").bootstrapTable("hideColumn","workservice");
	$("#customerReservationData").bootstrapTable("hideColumn","workexp");
	$("#customerReservationData").bootstrapTable("hideColumn","workplacetext");
	$("#customerReservationData").bootstrapTable("hideColumn","salarytext");
	$("#customerReservationData").bootstrapTable("hideColumn","ecp");
	$("#customerReservationData").bootstrapTable("hideColumn","bank");
	$("#customerReservationData").bootstrapTable("hideColumn","bankno");
	$("#customerReservationData").bootstrapTable("hideColumn","memo");
	$("#customerReservationData").bootstrapTable("hideColumn","status");
	//$("#customerReservationData").bootstrapTable("hideColumn","birthday");
	$("#customerReservationData").bootstrapTable("hideColumn","placeofbirth");
	$("#customerReservationData").bootstrapTable("hideColumn","customertypetext");
	$("#customerReservationData").bootstrapTable("hideColumn","createtime");
}

//修改客户上门数据（状态）
function assignCustomerReservationData(status){
	var bychecked= $('#customerReservationData').bootstrapTable('getSelections');
	console.log(bychecked[0].reportuserid);
	console.log(bychecked[0].receiver);
	if(bychecked.length != 1){
		toastr.warning("请先选择一条您要操作的数据");
		return false;
	}else{
		if(!checkHaveCustomer(bychecked[0].reportuserid) && !checkHaveCustomer(bychecked[0].receiver)){//该客户是否是属于自己
			toastr.error("该客户不属于您，您不能操作");
			return false;
		}
		if(!checkRemark(bychecked[0].customerid,'1003') && !checkRemark(bychecked[0].customerid,'1005')){//检查该客户是否有自己的批注信息
			addRemarkShow();
			toastr.warning("您还没有对该客户批注，请先填写批注后再操作");
			return false;
		}
		/*if(status == '1'){
			console.log(bychecked)
			console.log(typeof bychecked[0].statusrep)
			if(bychecked[0].statusrep != '0'){
				toastr.warning("只有预约后还没上门的客户才能进行上门操作！");
				return false;
			}
		}else if(status == '3'){
			if(bychecked[0].statusrep != '1'){
				toastr.warning("只有已经上门的数据才能进行流失操作！");
				return false;
			}
		}else if(status == '0'){
			if(bychecked[0].statusrep != '1' && bychecked[0].statusrep != '3'){
				toastr.warning("只有已经上门的数据或者流失的数据才能进行撤销操作！");
				return false;
			}
		}*/
	}
	var str = "";
	switch(status){
	case '0': str = "撤销客户上门数据"; break;
	case '1': str = "标记该客户为上门"; break;
	case '3': str = "标记该客户为流失"; break;
	case '5': str = "放弃该客户"; break;
	default:
	}
	//提交数据
	swal({
        title: "您确定要"+ str + "吗？",
        type: "info",
        showCancelButton: true,
        cancelButtonText:"取消",
        confirmButtonText: "确定",
        closeOnConfirm: false
    }, function () {
    	$.ajax({
    		type:"POST",
    		url:"../reservation/updCustomerReservationStatus.do",
    		dataType:"json",
    		async:false,
    		data:{"reservationid":bychecked[0].reservationid,"status":status,"customerid":bychecked[0].customerid,"reportuserid":bychecked[0].reportuserid},
    		success:function(data){
    			swal( str+"成功！", "共计"+ data + "条数据", "success");
    			$("#customerReservationData").bootstrapTable("load",getAllCustomerData());
    		},
    		error:function(){
    			toastr.error(str + "失败");
    		}
    	});
    });
}

//分配客户模态框
function assignCustomerShow(){
	var bychecked = $("#customerReservationData").bootstrapTable('getSelections');
	if(bychecked.length != 1){
		toastr.warning("请选择一条分配的数据");
		return false;
	}else if(bychecked[0].statusrep != "0"){
		toastr.warning("您不能分配该数据");
		return false;
	}
	//获得所有的员工信息（当前拥有者）
	selectUser("#userid_data2","课程顾问,客顾主管");
	//展示对话框
	$("#assignCustomerModal").modal("show");
}

//客户分配操作
function assignCustomer(){
//	console.log($("#assigncustomer").valid());
	if(!$("#assigncustomer").valid()){
		return false;
	}
	var bychecked= $('#customerReservationData').bootstrapTable('getSelections');
	console.log(bychecked);
	swal({
      title: "您确定要分配这条客户数据吗",
     // text: "删除后将无法恢复，请谨慎操作！",
      type: "info",
      showCancelButton: true,
      cancelButtonText:"取消",
      //confirmButtonColor: "#DD6B55",
      confirmButtonText: "确定",
      closeOnConfirm: false
  }, function () {
		$.ajax({
			type:"POST",
			url:"../TbCustomerAssign/assignCustomer.do",
			data:{"customerid":bychecked[0].customerid,"userid":$("#userid_data2").attr("data-id"),"reservationid":bychecked[0].reservationid},
			dataType:"json",
			async:false,
			success:function(data){
				swal("分配成功！", "这条数据已经成功分配给"+$("#userid_data2").val(), "success");
				$("#customerReservationData").bootstrapTable("load",getAllCustomerData());
				$("#assignCustomerModal").modal("hide");
			},
			error:function(){
				toastr.error("分配失败，请重新登录再分配");
			}
		});		
  });
	
}

//页面上可操作的按钮
function operBtn(){
//	operstr//可页面上可操作的按钮
	//将所有操作按钮禁用
	$("#customerreservationtoolbar button").attr("disabled", true);
	var operbtns = operstr.split("|");//可操作的按钮
	var btns = $("#customerreservationtoolbar button"); //页面上所按钮
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
	});
}

//回车查询数据
function keyDownGetReservationData(e){
	 var ev= window.event||e;
	  //13是键盘上面固定的回车键
	  if (ev.keyCode == 13) {
		  if($(".search input").val().length>0){
			  $.ajax({
					type:"POST",
					url:"../reservation/getReservationDataBySome.do",
					data:{"some":$(".search input").val()},
					dataType:"json",
					async:false,
					success:function(data){
						//处理json数据
						var json = [];
						$.each(JSON.parse(data), function(i, o) {
							var c = o.customer;
							console.log(o.customer);
							c.moeor = o.memo;
							c.purposetext = o.purposetext;
							c.receivertext = o.receivertext;
							c.reporttext = o.reporttext;
							c.zonetext = o.zonetext;
							c.statusrep = o.status;
							c.reservationid = o.reservationid
							c.createtime = new Date(c.createtime).format('yyyy-MM-dd');
							c.expecttime = new Date(o.expecttime).format('yyyy-MM-dd hh:mm:ss');
							c.reservationtime = new Date(o.reservationtime).format('yyyy-MM-dd hh:mm:ss');
							json.push(c);
			            });
						console.log(json);
						$("#customerReservationData").bootstrapTable("load",json);
					},
					error:function(){
						toastr.error("数据加载错误");
					}
			  });
		  }
		 
	  }
}


//新增客户批注展示
function addRemarkShow(){
	
	var tabledata = $("#customerReservationData").bootstrapTable("getSelections");
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
	addressInit("servicetype","remarktype","1003","");
	addressInit("servicetype","remarktype","1005","");
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

//更改上门时间展示
function updVisitTimeShow(){
	var d = $("#customerReservationData").bootstrapTable("getSelections");
	if(d.length!=1){
		toastr.warning("请选择一条数据再操作");
		return false;
	}
	if(!checkRemark(d[0].customerid,'1003') && !checkRemark(d[0].customerid,'1005')){
		addRemarkShow();
		toastr.warning("您还没有对该客户批注，请先填写批注后再操作");
		return false;
	}
	$("#updVisitTimeForm input[name='reservationid']").val(d[0].reservationid);
	$("#updVisitTimeModal").modal("show");
}
//更改上门时间
function updVisitTime(){
	if(!$("#updVisitTimeForm").valid()){
		return false;
	}
	$.ajax({
		type:"post",
		url:"../reservation/updVisitTime.do",
		data:{"reservationid":$("#updVisitTimeForm input[name='reservationid']").val(),"expecttime": new Date($("#updVisitTimeForm input[name='expecttimes']").val())},
		dataType:"json",
		success:function(data){
			toastr.success("修改成功！");
			$("#customerReservationData").bootstrapTable("load",getAllCustomerData());//刷新表格数据
			$("#updVisitTimeModal").modal("hide");
		},
		error:function(){
			toastr.error("更改失败");
		}
	});
}

//继续回访预约数据(咨询后回访)
function updScrapShow(){
	var d = $("#customerReservationData").bootstrapTable("getSelections");
	if(d.length!=1){
		toastr.warning("请选择一条数据再操作");
		return false;
	}
	$("#callbackForm input[name='resourceid']").val(d[0].reservationid);
	$("#callbackForm input[name='callbacktype']").val("3");
	$("#callbackForm input[name='customerid']").val(d[0].customerid);
	fillselectData("notcusreservation","#callbackForm select[name='reseaon']",12);
	$("#callbackModal").modal("show");
	
}
//继续回访预约数据（咨询后回访）
function updScrap(){
	//1.更改预约状态，2.新增回访信息
	$.ajax({
		type:"post",
		url:"../reservation/resevationCallback.do?"+$("#callbackForm").serialize(),
		data:{"reservationid":$("#callbackForm input[name='resourceid']").val(),"rstatus":"4"},
		dataType:"json",
		success:function(data){
			toastr.success("操作成功！");
			$("#customerReservationData").bootstrapTable("load",getAllCustomerData());//刷新表格数据
			$("#callbackModal").modal("hide");
		},
		error:function(){
			toastr.error("更改失败");
		}
	});
}

//查看历史批注
function addSeeHistoryRemark(){
	var d= $('#customerReservationData').bootstrapTable('getSelections');
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

//查看简历详情
function qryResumeDetail(){
	var d = $("#customerReservationData").bootstrapTable("getSelections");
	if(d.length!=1){
		toastr.error("请选择一条数据再操作");
		return false;
	}
	if(d[0].resumeurl == "" || (typeof d[0].resumeurl) == "undefined" || d[0].resumeurl == 'null' ||  d[0].resumeurl == null){
		toastr.warning("该客户还没有上传简历照片，无法查看");
		return false;
	}
	var imghtml = '<img alt="" src="'+ basepath + d[0].resumeurl+'">';
	window.open (basepath + d[0].resumeurl,'newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
}

//放弃客户
function updReservationStatus(){
	assignCustomerReservationData('5');
}

//获得当前用户是否是拥有客户着
function checkHaveCustomer(haveuid){
	console.log(user);
	var flag = true
	if(haveuid != user){
		flag = false;
	}
	return flag;
	
}