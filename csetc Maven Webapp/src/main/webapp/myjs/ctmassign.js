$(document).ready(function(){
	operBtns();
	fillTableData();
	/*$("body").on("focus","input",function(){
		$(this).select();
	});*/
	
});



//-----------------------------客户分配弹出框操作--获取所有电话销售信息-------------------------------------------------------------------//
function showmodel(){
	var bychecked = $("input[name^='btSelectItem']:checkbox:checked");
	if(bychecked.length==0){
		alert('请选择数据'); 
		return false;
	}else{
		$.ajax({
			type:"POST",
			url:"permission/allsale.do",
			dataType:"JSON",
			success:function(data){
				alert(data);
				var obj=JSON.parse(data);
				$.each(obj,function(index, i){
					console.log(i.userid)
					$("#sale").append("<option value='"+i.userid+"'  id='userid'>"+i.roleid+"</option>");
				})
				$('#myModal').modal('show');		
			}
		})
		
	}
}



//获得分配给自己的数据
function getAssignCustomerData(){
	var tableDataAry = [];
	$.ajax({
		type:"POST",
		url:"../TbCustomerAssign/consultCustomerData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data).customerassign;
			//处理json数据
			console.log(json);
			tableDataAry = HeandleJOSNData(json)
			/*$.each(json, function(i, o) {
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
				c.usertext = o.usertext;
				c.assignid = o.assignid;
				c.assigntext = o.assigntext;
				c.assigntime = o.assigntime;
				c.assignuserid = o.assignuserid;
				c.statuscus = o.status;
				c.userid = o.userid;
				c.createtime = new Date(c.createtime).format('yyyy-MM-dd');
				c.assigntime = new Date(c.assigntime).format('yyyy-MM-dd');
				tableDataAry.push(c);
            });*/
			console.log(tableDataAry);
		},error:function(){
			toastr.error("获得客户数据失败");
		}
	});
	return tableDataAry;
}

//获得表格中的数据
function fillTableData(){
	$('#customerassignData').bootstrapTable({
	    data:getAssignCustomerData(),
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#customerassigntoolbar",
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        clickToSelect: true,                //是否启用点击选中行
        minimumCountColumns: 10,             //最少允许的列数
        //height: 670,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "assignid",                     //每一行的唯一标识，一般为主键列
        idField:"assignid",
        showToggle:true,
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
	    	field: 'assigntext',
	    	title: '分配人',
	    	sortable:true
	    }, {
	    	field: 'usertext',
	    	title: '当前拥有者',
	    	sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '创建时间'
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
	    	sortable:true,
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
	    	field: 'memo',
	    	title: '备注',
	    	sortable:true
	    }, {
	    	field: 'statuscus',
	    	title: '状态',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "分配跟进中";
	    		}else if(value == 1){
	    			return "处理完毕";
	    		}else if(value == 2){
	    			return "主动放弃"
	    		}else if(value == 9){
	    			return "被动放弃"
	    		}
	    	}
	    } ,{
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
	    			return "已完结"
	    		}
	    	}
		}],
		onClickRow:function(row,ele,field){//单击行插入回访数据
			//看该客户是否有回访记录（今日）有就修改没有就新增
			
			
		},
		onDblClickRow:function(row,ele,field){//双击行预约客户
			/*if(getExistReservation(row.customerid,"")){//检查该客户是否有未处理完的预约记录
				customerReservationModalShow(row.customerid);//展示预约客户模态框
			}; */
			if(!checkHaveCustomer(row.userid)){
				return false;
			}
			if(getExistReservation(row.customerid,"该客户有未处理完的预约，暂时不能新增待回访记录")){
				showCallbackModal(getCallbackBySome(row.customerid),JSON.parse(JSON.stringify(row)));
			}
		}
	});
	
	hideTableCol();//隐藏列
}


//隐藏表格列
function hideTableCol(){
	$("#customerassignData").bootstrapTable("hideColumn","customerid");
	$("#customerassignData").bootstrapTable("hideColumn","email");
	$("#customerassignData").bootstrapTable("hideColumn","operator");
	$("#customerassignData").bootstrapTable("hideColumn","collector");
	$("#customerassignData").bootstrapTable("hideColumn","education");
	$("#customerassignData").bootstrapTable("hideColumn","source");
	$("#customerassignData").bootstrapTable("hideColumn","customertype");
	$("#customerassignData").bootstrapTable("hideColumn","course");
	$("#customerassignData").bootstrapTable("hideColumn","jobobjectivetext");
	$("#customerassignData").bootstrapTable("hideColumn","placeofbirthtext");
	$("#customerassignData").bootstrapTable("hideColumn","address");
	$("#customerassignData").bootstrapTable("hideColumn","politicalstatustext");
	$("#customerassignData").bootstrapTable("hideColumn","nationalitytext");
	$("#customerassignData").bootstrapTable("hideColumn","maritalstatustext");
	$("#customerassignData").bootstrapTable("hideColumn","workservice");
	$("#customerassignData").bootstrapTable("hideColumn","workexp");
	$("#customerassignData").bootstrapTable("hideColumn","workplacetext");
	$("#customerassignData").bootstrapTable("hideColumn","salarytext");
	$("#customerassignData").bootstrapTable("hideColumn","ecp");
	$("#customerassignData").bootstrapTable("hideColumn","bank");
	$("#customerassignData").bootstrapTable("hideColumn","bankno");
	//$("#customerassignData").bootstrapTable("hideColumn","memo");
	$("#customerassignData").bootstrapTable("hideColumn","assigntype");
	//$("#customerassignData").bootstrapTable("hideColumn","birthday");
	$("#customerassignData").bootstrapTable("hideColumn","placeofbirth");
	$("#customerassignData").bootstrapTable("hideColumn","customertypetext");
}

//勾选预约人数
function addcheckReservationNum(){
	var bychecked= $('#customerassignData').bootstrapTable('getSelections');
	if(bychecked.length != 1){
		toastr.error("请选择一个您要预约的人");
	}else{
		if(!checkHaveCustomer(bychecked[0].userid)){
			return false;
		}
		if(getExistReservation(bychecked[0].customerid,"该客户有未处理完的预约记录，不能再次预约")){//检查该客户是否有未处理完的预约记录
			customerReservationModalShow(bychecked[0].customerid,bychecked[0].assignid);//展示预约客户模态框
		}; 
	}
}
//预约客户展示
function customerReservationModalShow(customerid,assignid){
	//return checkRemark(customerid);
	$("#reservationForm input[name='customerid']").val(customerid);
	$("#reservationForm input[name='assignid']").val(assignid);
	fillselectData("cusreservation","#reservationForm select[name='purpose']");
	fillselectData("arriveaddress","#reservationForm select[name='zone']");
	selectUser("#receiver_data",'销售前台,课程顾问，客顾主管');
	$("#reservationModal").modal("show")
}
//预约客户
function addCustomerReservation(){
	if(!$("#reservationForm").valid()){
		return false;
	}
   //将日期数据转成时间戳	
   var date = $("#reservationForm input[name='expect']").val();
   date = new Date(Date.parse(date.replace(/-/g, "/")));
   date = date.getTime();
	$.ajax({
		type:"POST",
		url:"../reservation/addCustomerReservation.do?expect=" + date +"&receiver=" + $("#receiver_data").attr("data-id"),
		dataType:"json",
		data:$("#reservationForm").serialize(),
		success:function(data){
			if(data == 1){
				toastr.success("预约客户成功"); 
				$("#reservationModal").modal("hide");
				$("#customerassignData").bootstrapTable("load",getAssignCustomerData());
			}
		},
		error:function(){
			toastr.error("预约数据录入失败");
		}
	})
}

//检查某客户是否已经有未处理完的预约记录
function getExistReservation(customerid,str){
	var resule = false;
	$.ajax({
		type:"POST",
		url:"../reservation/getExistReservation.do?customerid=" + customerid ,
		dataType:"json",
		async:false,
		success:function(data){
			if(data > 0){
				toastr.warning(str); 
			}else{
				resule = true;
			}
		},error:function(){
			toastr.error("数据处理失败哦");
		}
	});
	return resule;
}

//修改客户分配状态
function updCustomerAssignStatus(status){
//	console.log(status);
	var bychecked= $('#customerassignData').bootstrapTable('getSelections');
	if(bychecked.length != 1){
		toastr.warning("请选择一条你想要操作的客户数据");
	}else{
		if(!checkHaveCustomer(bychecked[0].userid)){
			return false;
		}
		if(!checkRemark(bychecked[0].customerid,'1002')){
			toastr.warning("您还没有对该客户批注，请批注后再操作");
			updaddRemarkShow();
			return false;
		}
		//return checkRemark(bychecked[0].customerid);
		/*var customerids = "";
		$.each(bychecked,function(i,o){
			customerids += o.customerid + ",";
		});*/
		var str = '';
		if(status == '1'){
			str = "完结";
		}else if(status == '2'){
			str = "放弃";
		}
		swal({
	        title: "您确定要"+ str +"这 "+bychecked.length+" 条客户数据吗",
	        type: "info",
	        showCancelButton: true,
	        cancelButtonText:"取消",
	        confirmButtonText: "确定",
	        closeOnConfirm: false
	    }, function () {
			$.ajax({
				type:"POST",
				url:"../TbCustomerAssign/updCustomerAssignStatus.do",
				dataType:"json",
				data:{"customerids":bychecked[0].customerid,"status":status},
				success:function(data){
					if(data>0){
						swal("操作客户数据成功！", "", "success");
						$("#customerassignData").bootstrapTable("load",getAssignCustomerData());
					}
				},
				error:function(){
					toastr.error("操作客户数据失败");
				}
			});
	    });
		
	}
}

//页面上可操作的按钮
function operBtns(){
//	operstr//可页面上可操作的按钮
	//将所有操作按钮禁用
	$("#customerassigntoolbar button").attr("disabled", true);
	var operbtns = operstr.split("|");//可操作的按钮
	var btns = $("#customerassigntoolbar button"); //页面上所按钮
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

//查看某客户是否有回访记录（今日新增）
function getCallbackBySome(customerid){
	var json ;
	$.ajax({
		type:"POST",
		url:"../callback/getCallbackBySome.do",
		dataType:"json",
		data:{"customerid":customerid},
		async:false,
		success:function(data){
			json = JSON.parse(data);
			if(json == null || json == 'null'){
				json = "";
			}
		}
	});
	return json;
}
//新增某客户的回访记录
function addCallbackinfo(){
	if($("#callbackForm").valid()){
		$.ajax({
			type:"POST",
			url:"../callback/addCallbackinfo.do",//新增回访记录
			dataType:"json",
			data:$("#callbackForm").serialize(),
			async:false,
			success:function(data){
				$.ajax({
					type:"POST",
					url:"../TbCustomerAssign/updCustomerAssignStatus.do",//更改分配状态
					dataType:"json",
					data:{"customerids":$("#callbackForm input[name='customerid']").val(),"status":"1"},
					async:false,
					success:function(data){
						toastr.success("新增客户回访数据成功");
						$("#customerassignData").bootstrapTable("load",getAssignCustomerData());
						$("#callbackModal").modal("hide");//隐藏回回访模态框
					}
				});
				
			}
		});
		
	}
	
}
//修改某客户的回访记录
function updCallbackById(){
	if($("#callbackForm").valid()){
		$.ajax({
			type:"POST",
			url:"../callback/updCallbackById.do",
			dataType:"json",
			async:false,
			data:$("#callbackForm").serialize(),
			success:function(data){
				toastr.success("修改客户回访数据成功");
				$("#callbackModal").modal("hide");
			}
		});
	}
}

//弹出客户回访数据模态框
function showCallbackModal(json,status){
	if(json!=null && json != ""){//修改
		$("#callbackModal .modal-title").html("修改回访信息");
		$("#callbackForm input[name='callbackid']").val(json.callbackid);
		$("#callbackForm input[name='resourceid']").val("");
		$("#callbackForm input[name='callbacktype']").val("");
		$("#callbackForm input[name='customerid']").val("");
		$("#callbackForm input[name='tips']").val(json.tips);
		$("#callbackForm input[name='tipdatetime']").val(new Date(json.tipdate).format('yyyy-MM-dd'));
		$("#callbackForm textarea[name='memo']").text(json.memo);
		fillselectData("notcusreservation","#callbackForm select[name='reseaon']",json.reseaon);
		//默认选择的单选按钮
		if(json.intention == 0){//意向度
			$("#callbackForm input[name=intention][value='0']").prop("checked",true);
		}else{
			$("#callbackForm input[name=intention][value='1']").prop("checked",true);
		}
		if(json.testing == 0){//是否测评
			$("#callbackForm input[name=testing][value='0']").prop("checked",true);
		}else{
			$("#callbackForm input[name=testing][value='1']").prop("checked",true);
		}
		$("#callbackModal .btn-primary").attr("onclick","updCallbackById()")
	}else{//新增
		$("#callbackModal .modal-title").html("新增回访信息");
		$("#callbackForm input[name='resourceid']").val(status.assignid);
		$("#callbackForm input[name='callbacktype']").val("1");
		$("#callbackForm input[name='customerid']").val(status.customerid);
		$("#callbackModal .btn-primary").attr("onclick","addCallbackinfo()");
		fillselectData("notcusreservation","#callbackForm select[name='reseaon']");
		$("#callbackForm .canceltext").val("")//清空表单数据
	}
	$("#callbackForm").valid();
	$("#callbackModal").modal("show");
}

//新增客户批注展示
function updaddRemarkShow(){
	
	var tabledata = $("#customerassignData").bootstrapTable("getSelections");
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
                ' <button type="button" class="btn btn-primary" onclick="addRemarks()">确定</button>'+
            '</div>'+
        '</div>'+
    '</div>'+
'</div>';
	
	
	//将模态框放入页面中
	$("body").append(modalHtml);
	//客户所属阶段的对应的类型
	//fillselectData("remarktype","#remarkcustomerForm select[name='remarktype']");
	//客户所属阶段联动阶段类型
	addressInit("servicetype","remarktype","1002","");
	//显示模态框
	$("#remarkModal").modal("show");
}

//新增客户批注
function addRemarks(){
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
			toastr.error("新增客户批注数据失败");
		}
	})
}
 
//查看历史批注
function qrySeeHistoryRemark(){
	var d= $('#customerassignData').bootstrapTable('getSelections');
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
	var d = $("#customerassignData").bootstrapTable("getSelections");
	if(d.length!=1){
		toastr.error("请选择一条数据再操作");
		return false;
	}
	if(d[0].resumeurl == "" || (typeof d[0].resumeurl) == "undefined" || d[0].resumeurl == 'null' ||  d[0].resumeurl == null){
		toastr.warning("该客户还没有上传简历照片，无法查看");
		return false;
	}
	var imghtml = '<img alt="" src="'+ basepath + d[0].resumeurl+'">';
	window.open (basepath + d[0].resumeurl,'newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	/*$("#resumeImage").html(imghtml);
	$("#resumeImageModal").modal("show");*/
}

//查看工作情况
function qryWorkdata(){
	$.ajax({
		type:"post",
		url:"../TbCustomerAssign/workdata.do",
		dataType:"json",
		async:false,
		success:function(data){
			console.log(JSON.parse(data));
			var json = JSON.parse(data);
			var tablehtml = "<tr><td>描述</td><td>数量</td><td>具体</td></tr>";
				tablehtml += "<tr><td>今日预约数据</td><td>"+ json[0].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>今日预计上门</td><td>"+ json[1].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>今日已上门</td><td>"+ json[2].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>今日待回访</td><td>"+ json[3].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>今日已回访</td><td>"+ json[4].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>今日新分配</td><td>"+ json[5].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>今日新量跟进</td><td>"+ json[6].assignid+"</td><td></td></tr>";
				tablehtml += "<tr><td>本月签单数据</td><td>"+ json[7].assignid+"</td><td>"+json[7].assigntext+"</td></tr>";
				tablehtml += "<tr><td>本月退款数据</td><td>"+ json[8].assignid+"</td><td>"+json[8].assigntext+"</td></tr>";
			$("#workTable").html(tablehtml);
			$("#workdataModal").modal("show");
		},
		error:function(){
			toastr.error("获得数据失败");
		}
	});
}

//查看某客户是否已经写过批注
/*function checkRemark(cid){
	//
	var flag = true;
	$.ajax({
		type:"post",
		url:"../remark/checkRemark.do",
		dataType:"json",
		data:{"customerid":cid},
		async:false,
		success:function(data){
			if(data == 0){
				flag = false;
				toastr.warning("您还没有对该客户批注，请先填写批注后再操作");
				updaddRemarkShow();
			}
		},
		error:function(){
			toastr.error("获得批注数据失败");
		}
	});
	return flag;
}*/

//查看团队数据
function uploadTeamdata(){
	$.ajax({
		type:"post",
		url:"../TbCustomerAssign/teamData.do",
		dataType:"json",
		async:false,
		success:function(data){
//			alert(data);
//			console.log(JSON.parse(data));
			
			$("#customerassignData").bootstrapTable("load",HeandleJOSNData(JSON.parse(data)));
		},
		error:function(){
			toastr.error("获得批注数据失败");
		}
	});
}

//处理json数据
function HeandleJOSNData(datas){
	var tableDataAry = [];
	$.each(datas, function(i, o) {
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
		c.usertext = o.usertext;
		c.assignid = o.assignid;
		c.assigntext = o.assigntext;
		c.assigntime = o.assigntime;
		c.assignuserid = o.assignuserid;
		c.statuscus = o.status;
		c.userid = o.userid;
		c.createtime = new Date(c.createtime).format('yyyy-MM-dd');
		c.assigntime = new Date(c.assigntime).format('yyyy-MM-dd');
		tableDataAry.push(c);
	});
	return tableDataAry;
}

//获得当前用户是否是拥有客户着
function checkHaveCustomer(haveuid){
	var flag = true
	if(haveuid != user){
		toastr.error("该客户不属于您，您不能操作");
		flag = false;
	}
	return flag;
	
}