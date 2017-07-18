/**
 * 文件名: customerinfo.js
 * 描述:客户信息
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-28
 */
var winOpen;
$(document).ready(function(){
	operBtn();
	fillTableData();
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
	
	/**
	 *检查该用户是否存在（电话号码）
	 */
	$("#customerModal").on("input propertychange","input",function(){//为输入框添加监听事件
		var input = $(this);
		$.ajax({
			type:"POST",
			url:"../customer/checkPresenceExists.do",
			dataType:"json",
			async:false,
			data:{"telphone":input.val()},
			success:function(data){
				var json = JSON.parse(data);
				if(json.length>0){
					input.val("");
					toastr.success("该电话号码的用户已经存在！");
				}
			},
			error:function(){
				toastr.error("出错啦");
			}
		});
	})
});

/**
 * 新增单个客户信息模态框展示
 */
function addCustomerShow(){
	$("#customerForm").html("");
	//新增的客户信息表单html
	var cushtml = '<form class="form-horizontal" id="customerForm" enctype="multipart/form-data">' 
		+ '<h3>必填数据</h3><section>' 
		+ '<div class="form-group"><label class="col-sm-4 control-label">*客户名称：</label>' 
		+ '<div class="col-sm-6"><input type="text" required="required" name="customername" maxlength="20" class="form-control input-sm" required></div></div>'
		+ '<div class="form-group"> <label class="col-sm-4 control-label">*性别：</label>'
		+ '<div class="col-sm-6"><div class="radio radio-info radio-inline"><input type="radio" id="male" value="1" name="sex" checked=""><label for="male"> 男  </label></div>'
		+ '<div class="radio radio-inline"><input type="radio" id="female" value="0" name="sex"><label for="female"> 女 </label></div></div></div>'
		+ '<div class="form-group" ><label class="col-sm-4 control-label">*电话：</label>'
		+ '<div class="col-sm-6"><input type="text" name="telephone" minlength="11" maxlength="11" digits="true" class="form-control input-sm" required></div></div>'
		+ '<div class="form-group" ><label class="col-sm-4 control-label">电子邮件/qq：</label>'
		+ '<div class="col-sm-6"><input type="text" name="email" class="form-control input-sm"></div></div>'
		
	 	+ '<div class="form-group" ><label class="col-sm-4 control-label">*采集人：</label>'
	 	+ '<div class="col-sm-6"><div class="input-group"><input name="collector_" type="text" class="form-control input-sm" value="'+ $("#username").val()+'" id="collector_data" required>'
	 	+ '<div class="input-group-btn"><button type="button" class="btn btn-sm btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>'
	 	+ '<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul></div></div></div>'
	 	
	 	+ '<div class="form-group" ><label class="col-sm-4 control-label">*来源渠道：</label>'
	 	+ '<div class="col-sm-6"><select name="source" class="form-control"></select></div></div>'
	 	+ '<div class="form-group"><label class="col-sm-4 control-label">*客户类型：</label>'
	 	+ '<div class="col-sm-6"><select name="customertype" class="form-control"></select></div></div>'
	 	+ '<div class="form-group" ><label class="col-sm-4 control-label">*意向课程：</label>'
        + '<div class="col-sm-6"><select name="course" class="form-control"></select></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">紧急联系人方式：</label>'
        + '<div class="col-sm-6"><input name="ecp" type="text" maxlength="11" digits="true" class="form-control input-sm" /></div></div></section>'
        + '<h3>基本信息</h3><section>'
        + '<div class="form-group"><label class="col-sm-4 control-label">学校：</label>'
        + '<div class="col-sm-6"><input type="text" name="school" class="form-control input-sm"></div></div>'
       /* + '<div class="form-group" ><label class="col-sm-4 control-label">专业：</label>'
        + '<div class="col-sm-6"><select type="text" name="major" class="form-control"></select></div></div>'*/
        + '<div class="form-group"><label class="col-sm-4 control-label">学历：</label>'
        + '<div class="col-sm-6"><select name="education" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">出生年月：</label>'
        + '<div class="col-sm-6"><input class="form-control layer-date laydate-icon" onclick="laydate()" name="birthdaytime" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">户口所在地：</label>'
        + '<div class="col-sm-6"><input name="placeofbirth" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">现居住地：</label>'
        + '<div class="col-sm-6"><input name="address" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">政治面貌：</label>'
        + '<div class="col-sm-6"><select name="politicalstatus" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">民族：</label>'
        + '<div class="col-sm-6"><select name="nationality" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">婚育状况：</label>'
        + '<div class="col-sm-6"><select name="maritalstatus" class="form-control"></select></div></div></section>'
        + '<h3>其他信息</h3><section>'
        + '<div class="form-group"><label class="col-sm-4 control-label">求职意向：</label>'
        + '<div class="col-sm-6"><select name="jobobjective" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">工作年限：</label>'
        + '<div class="col-sm-6"><input name="workservice" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">工作经历：</label>'
        + '<div class="col-sm-6"><textarea rows="" cols="" style="height:32px;" name="workexp" class="form-control"></textarea></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">期望薪资：</label>'
        + '<div class="col-sm-6"><select name="salary" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">期望工作地点：</label>'
        + '<div class="col-sm-6"><select name="workplace" class="form-control"></select></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">开户行：</label>'
        + '<div class="col-sm-6"><input name="bank" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">银行卡号：</label>'
        + '<div class="col-sm-6"><input name="bankno" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">备注：</label>'
        + '<div class="col-sm-6"><textarea rows="" cols="" name="memo" class="form-control"></textarea></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">状态：</label>'
        + '<div class="col-sm-6"><div class="radio radio-info radio-inline"><input type="radio" id="inlineRadio1" value="1" name="status" checked=""><label for="inlineRadio1"> 启用  </label></div>'
        + '<div class="radio radio-inline"><input type="radio" id="inlineRadio2" value="0" name="status"><label for="inlineRadio2"> 废弃 </label></div></div></div></section>';
		+ '</form>';
		
	$("#customerModal .ibox-content").html(cushtml);    //将html放入表单中 

   $("#customerForm").steps({//设置步骤向导基础数据
		headerTag: "h3",
	    bodyTag: "section",
	    transitionEffect: "slideLeft",
	    autoFocus: true,
	    labels:{
	    	next: "下一步",                                                                      
		    previous: "上一步",
		    finish: "完成"
	    },
	    enableKeyNavigation: true,
	    onStepChanging: function(wizard, options, state){//点击下一步时进行表单验证
			return $(this).valid();
	    },
	    onFinished : function(event,currentIndex){//点击完成时发生的事件
	    	addOneCustomerInfo();
	    	return false;
	    }
	});	
   
	//采集人 来源渠道 客户类型 意向课程 政治面貌 民族 婚育状况 期望薪资 
    selectUser("#collector_data",user+",信息专员,信息主管");
	fillselectData("source","#customerForm select[name='source']");
	fillselectData("custype","#customerForm select[name='customertype']");
	fillselectData("cuscourse","#customerForm select[name='course']");
	fillselectData("politicalstatus","#customerForm select[name='politicalstatus']");
	fillselectData("nationality","#customerForm select[name='nationality']");
	fillselectData("maritalstatus","#customerForm select[name='maritalstatus']");
	fillselectData("salary","#customerForm select[name='salary']",2);
	fillselectData("jobobjective","#customerForm select[name='jobobjective']");
	fillselectData("cussourcearea","#customerForm select[name='workplace']");
	fillselectData("education","#customerForm select[name='education']",4);
	fillselectData("major","#customerForm select[name='major']");
	
	$("#customerForm").validate();//表单验证
	$("#customerModal").modal("show");//弹出模态框
	
}


/**
 * 新增单个客户信息
 */
function addOneCustomerInfo(){
	if(!$("#customerForm").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../customer/addOneCustomerInfo.do?birthdaytime="+$("#customerForm input[name='birthdaytime']").val()+"&collector_data="+$("#collector_data").attr("data-id"),
		dataType:"json",
		async:false,
		data:$("#customerForm").serialize(),
		success:function(data){
			if(data==1){
				toastr.success("新增成功！");
				$("#customerModal").modal("hide");//隐藏模态框
				$("#customerData").bootstrapTable("load",getAllCustomerData());
			}
		},
		error:function(){
			toastr.error("出错啦");
		}
	});
	
	
}

/**
 * 上传客户数据
 */
function addUpload(){
	
	var formData = new FormData($( "#uploadcustomer" )[0]);
	$.ajax({
		type:"post",
		url:"../customer/importcustomer.do",
		data:formData,
		dataType:"json",
		cache: false,  
		contentType: false,
        processData: false,
		async:false,
		success:function(data){
			if(data.indexOf(".")<0){
				alert(data);
			}else{
				var error = data.substring(0,data.indexOf("."));
				var json = data.substring(data.indexOf(".")+1,data.length);
				var etext = "";
				$.each(JSON.parse(json),function(i,o){
					etext+= o +"\n";
				})
				console.log(json);
				alert(error+"\n"+etext);
			}
			
			/*
			if(data.indexOf("e") == 0){
				$("#uploaderrorinfo").val(data);
				if ((typeof winOpen) != "undefined"){
					//关闭子窗口
					winOpen.close();
				}
				winOpen = window.open ("../uploadfail.jsp",'newwindow','modal=yes,height=400,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			}else{
				alert(data);
			}*/
			
			//
		},
		error:function(){
			toastr.error("上传附件失败");
			
		}
	
	});
	
	return false;
	/* var options = {
	            success: function (data) {
	                if(data.substring(0,2) == "s:"){
	                	toastr.success(data.substring(2,data.indexOf(";"))+"<br/>"+ data.substring(data.indexOf(";")+1,data.length));
	                }else{
	                	toastr.error(data.substring(2,data.indexOf(";"))+"<br/>"+ data.substring(data.indexOf(";")+1,data.length));
	                }
	                $("#uploadCustomerModal").modal("hide");
	            },
	            error:function(){
	    			toastr.error("上传文件失败!请先选择文件再上传");
	    		}
	        };
	// $("#uploadcustomer").ajaxForm(options);
	  $("#uploadcustomer").submit();
	 */
}



//获得表格数据
function getAllCustomerData(){
	var json 
	$.ajax({
		type:"POST",
		url:"../customer/getCustomerinfoData.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json, function(i, o) {
				var c = o.customer;
				o.majortext = selectText("major",o.major);
				o.educationtext = selectText("education",o.education);
				o.sourcetext = selectText("source",o.source);
				o.customertypetext = selectText("custype",o.customertype);
				o.coursetext = selectText("cuscourse",o.course);
				o.jobobjectivetext = selectText("jobobjective",o.jobobjective);
				o.politicalstatustext = selectText("politicalstatus",o.politicalstatus);
				o.nationalitytext = selectText("nationality",o.nationality);
				o.maritalstatustext = selectText("maritalstatus",o.maritalstatus);
				o.workplacetext = selectText("maritalstatus",o.workplace);
				o.salarytext = selectText("salary",o.salary);
            });
		},
		error:function(){
			toastr.error("出错啦");
		}
	});
	return json;
}

//客户信息展示
//查看表格数据
function fillTableData(){
	var json = getAllCustomerData();
	$('#customerData').bootstrapTable({
	    data:json,
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
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 10,             //最少允许的列数
        //clickToSelect: true,                //是否启用点击选中行
        //height: 670,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "customerid",                     //每一行的唯一标识，一般为主键列
        idField:"customerid",
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        rowStyle: function (row, index) {
            //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
            var strclass = "";
            if (row.assigntype == 2) {
                strclass = 'warning';//还有一个active
            } else {
                return {};
            }
            return { classes: strclass }
        },
	    columns: [{
	    	checkbox:true,
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
	    	field: 'status',
	    	title: '状态',
	    	sortable:true
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
	    			return "已完结"
	    		}
	    	},
	    	sortable:true
		}],
		onDblClickRow:function(row,ele,field){//双击行
			customerinfoUpdAndShow(row);//更改客户信息
		}
	});
	
	hideTableCol();
}

//隐藏表格列
function hideTableCol(){
	$("#customerData").bootstrapTable("hideColumn","customerid");
	$("#customerData").bootstrapTable("hideColumn","email");
	$("#customerData").bootstrapTable("hideColumn","operator");
	$("#customerData").bootstrapTable("hideColumn","collector");
	$("#customerData").bootstrapTable("hideColumn","education");
	$("#customerData").bootstrapTable("hideColumn","source");
	$("#customerData").bootstrapTable("hideColumn","customertype");
	$("#customerData").bootstrapTable("hideColumn","course");
	$("#customerData").bootstrapTable("hideColumn","jobobjectivetext");
	$("#customerData").bootstrapTable("hideColumn","placeofbirthtext");
	$("#customerData").bootstrapTable("hideColumn","address");
	$("#customerData").bootstrapTable("hideColumn","politicalstatustext");
	$("#customerData").bootstrapTable("hideColumn","nationalitytext");
	$("#customerData").bootstrapTable("hideColumn","maritalstatustext");
	$("#customerData").bootstrapTable("hideColumn","workservice");
	$("#customerData").bootstrapTable("hideColumn","workexp");
	$("#customerData").bootstrapTable("hideColumn","workplacetext");
	$("#customerData").bootstrapTable("hideColumn","salarytext");
	$("#customerData").bootstrapTable("hideColumn","ecp");
	$("#customerData").bootstrapTable("hideColumn","bank");
	$("#customerData").bootstrapTable("hideColumn","bankno");
	$("#customerData").bootstrapTable("hideColumn","memo");
	$("#customerData").bootstrapTable("hideColumn","status");
	//$("#customerData").bootstrapTable("hideColumn","birthday");
	$("#customerData").bootstrapTable("hideColumn","placeofbirth");
	$("#customerData").bootstrapTable("hideColumn","customertypetext");
}

//展示修改客户信息
function customerinfoUpdAndShow(row){
	//保证数据的正确性清空之前的数据
	$("#customerModal .ibox-content").html("");
	//新增的客户信息表单html
	var cushtml = '<form class="form-horizontal" id="customerUpdForm" enctype="multipart/form-data">' 
		+ '<h3>必填数据</h3><section>' 
		+ '<input type="hidden" name="id" value="'+row.id+'" /><input type="hidden" name="customerid" value="'+row.customerid+'" />' 
		+ '<div class="form-group"><label class="col-sm-4 control-label">*客户名称：</label>' 
		+ '<div class="col-sm-6"><input type="text" required="required" name="customername" maxlength="20" class="form-control input-sm"></div></div>'
		+ '<div class="form-group"> <label class="col-sm-4 control-label">*性别：</label>'
		+ '<div class="col-sm-6"><div class="radio radio-info radio-inline"><input type="radio" id="male" value="1" name="sex" checked=""><label for="male"> 男  </label></div>'
		+ '<div class="radio radio-inline"><input type="radio" id="female" value="0" name="sex"><label for="female"> 女 </label></div></div></div>'
		+ '<div class="form-group" ><label class="col-sm-4 control-label">*电话：</label>'
		+ '<div class="col-sm-6"><input type="text" name="telephone" minlength="11" maxlength="11" digits="true" class="form-control input-sm" required></div></div>'
		+ '<div class="form-group" ><label class="col-sm-4 control-label">电子邮件/qq：</label>'
		+ '<div class="col-sm-6"><input type="text" name="email" class="form-control input-sm"></div></div>'
		
	 	+ '<div class="form-group" ><label class="col-sm-4 control-label">*采集人：</label>'
	 	+ '<div class="col-sm-6"><div class="input-group"><input name="collector_" type="text" class="form-control input-sm" id="collector_data" required>'
	 	+ '<div class="input-group-btn"><button type="button" class="btn btn-sm btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>'
	 	+ '<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul></div></div></div>'
	 	
	 	+ '<div class="form-group" ><label class="col-sm-4 control-label">*来源渠道：</label>'
	 	+ '<div class="col-sm-6"><select name="source" class="form-control"></select></div></div>' 
	 	+ '<div class="form-group"><label class="col-sm-4 control-label">*客户类型：</label>'
	 	+ '<div class="col-sm-6"><select name="customertype" class="form-control"></select></div></div>'
	 	+ '<div class="form-group" ><label class="col-sm-4 control-label">*意向课程：</label>'
        + '<div class="col-sm-6"><select name="course" class="form-control"></select></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">紧急联系人方式：</label>'
        + '<div class="col-sm-6"><input name="ecp" type="text" digits="true" class="form-control input-sm" /></div></div></section>'
        + '<h3>基本信息</h3><section>'
        + '<div class="form-group"><label class="col-sm-4 control-label">学校：</label>'
        + '<div class="col-sm-6"><input type="text" name="school"  class="form-control input-sm"></div></div>'
        /*+ '<div class="form-group" ><label class="col-sm-4 control-label">专业：</label>'
        + '<div class="col-sm-6"><select type="text" name="major" class="form-control"></select></div></div>'*/
        + '<div class="form-group"><label class="col-sm-4 control-label">学历：</label>'
        + '<div class="col-sm-6"><select name="education" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">出生年月：</label>'
        + '<div class="col-sm-6"><input class="form-control layer-date laydate-icon" onclick="laydate()" name="birthdaytime"  /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">户口所在地：</label>'
        + '<div class="col-sm-6"><input name="placeofbirth" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">现居住地：</label>'
        + '<div class="col-sm-6"><input name="address" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">政治面貌：</label>'
        + '<div class="col-sm-6"><select name="politicalstatus" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">民族：</label>'
        + '<div class="col-sm-6"><select name="nationality" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">婚育状况：</label>'
        + '<div class="col-sm-6"><select name="maritalstatus" class="form-control"></select></div></div></section>'
        + '<h3>其他信息</h3><section>'
        + '<div class="form-group"><label class="col-sm-4 control-label">求职意向：</label>'
        + '<div class="col-sm-6"><select name="jobobjective" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">工作年限：</label>'
        + '<div class="col-sm-6"><input name="workservice" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">工作经历：</label>'
        + '<div class="col-sm-6"><textarea rows="" cols="" name="workexp"  style="height:32px;" class="form-control"></textarea></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">期望薪资：</label>'
        + '<div class="col-sm-6"><select name="salary" class="form-control"></select></div></div>'
        + '<div class="form-group"><label class="col-sm-4 control-label">期望工作地点：</label>'
        + '<div class="col-sm-6"><select name="workplace" class="form-control"></select></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">开户行：</label>'
        + '<div class="col-sm-6"><input name="bank" type="text" class="form-control input-sm" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">银行卡号：</label>'
        + '<div class="col-sm-6"><input name="bankno" type="text"  class="form-control input-sm" /></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">备注：</label>'
        + '<div class="col-sm-6"><textarea rows="" cols="" name="memo"  class="form-control"></textarea></div></div>'
        + '<div class="form-group" ><label class="col-sm-4 control-label">状态：</label>'
        + '<div class="col-sm-6"><div class="radio radio-info radio-inline"><input type="radio" id="inlineRadio1" value="1" name="status" checked=""><label for="inlineRadio1"> 启用  </label></div>'
        + '<div class="radio radio-inline"><input type="radio" id="inlineRadio2" value="0" name="status"><label for="inlineRadio2"> 废弃 </label></div></div></div></section>';
   
	$("#customerModal .ibox-content").html(cushtml);    //将html放入表单中 

    $("#customerUpdForm").steps({//设置步骤向导基础数据
		headerTag: "h3",
	    bodyTag: "section",
	    transitionEffect: "slideLeft",
	    autoFocus: true,
	    labels:{
	    	next: "下一步",                                                                      
		    previous: "上一步",
		    finish: "完成"
	    },
	    enableKeyNavigation: true,
	    onStepChanging: function(wizard, options, state){//点击下一步时进行表单验证
			return $(this).valid();
	    },
	    onFinished : function(event,currentIndex){//点击完成时发生的事件
	    	updCustomerinfo();
	    	return false;
	    }
	});	
   
	//采集人 来源渠道 客户类型 意向课程 政治面貌 民族 婚育状况 期望薪资 
    selectUser("#collector_data",',信息专员,信息主管' );
    $("#collector_data").val(row.collectortext)
	fillselectData("source","#customerUpdForm select[name='source']",row.source);
	fillselectData("custype","#customerUpdForm select[name='customertype']",row.customertype);
	fillselectData("cuscourse","#customerUpdForm select[name='course']",row.course);
	fillselectData("politicalstatus","#customerUpdForm select[name='politicalstatus']",row.politicalstatus);
	fillselectData("nationality","#customerUpdForm select[name='nationality']",row.nationality);
	fillselectData("maritalstatus","#customerUpdForm select[name='maritalstatus']",row.maritalstatus);
	fillselectData("salary","#customerUpdForm select[name='salary']",row.salary);
	fillselectData("jobobjective","#customerUpdForm select[name='jobobjective']",row.jobobjective);
	fillselectData("cussourcearea","#customerUpdForm select[name='workplace']",row.workplace);
	fillselectData("education","#customerUpdForm select[name='education']",row.education);
	fillselectData("major","#customerUpdForm select[name='major']",row.major);
	//默认选择的单选按钮
	if(row.status == 0){
		$("#customerUpdForm input[name=status][value='0']").prop("checked",true);
	}else{
		$("#customerUpdForm input[name=status][value='1']").prop("checked",true);
	}
	if(row.sex == 0){
		$("#customerUpdForm input[name=sex][value='0']").prop("checked",true);
	}else{
		$("#customerUpdForm input[name=sex][value='1']").prop("checked",true);
	}
	//输入框默认值
	if(typeof (row.customername) != 'undefined'){
		$("#customerUpdForm input[name='customername']").val(row.customername);
	}
	if(typeof (row.telephone) != 'undefined'){
		$("#customerUpdForm input[name='telephone']").val(row.telephone);
	}
	if(typeof (row.email) != 'undefined'){
		$("#customerUpdForm input[name='email']").val(row.email);
	}
	if(typeof (row.ecp) != 'undefined'){
		$("#customerUpdForm input[name='ecp']").val(row.ecp);
	}
	if(typeof (row.school) != 'undefined'){
		$("#customerUpdForm input[name='school']").val(row.school);
	}
	if(typeof (row.major) != 'undefined'){
		$("#customerUpdForm input[name='major']").val(row.major);
	}
	if(typeof (row.birthday) != 'undefined'){
		$("#customerUpdForm input[name='birthdaytime']").val(row.birthday);
	}
	if(typeof (row.placeofbirth) != 'undefined'){
		$("#customerUpdForm input[name='placeofbirth']").val(row.placeofbirth);
	}
	if(typeof (row.workservice) != 'undefined'){
		$("#customerUpdForm input[name='workservice']").val(row.workservice);
	}
	if(typeof (row.bank) != 'undefined'){
		$("#customerUpdForm input[name='bank']").val(row.bank);
	}
	if(typeof (row.bankno) != 'undefined'){
		$("#customerUpdForm input[name='bankno']").val(row.bankno);
	}
	//表单验证
	$("#customerUpdForm").validate();
	$("#customerModal").modal("show");//弹出模态框
}

//修改客户信息
function updCustomerinfo(){
	$.ajax({
		type:"POST",
		url:"../customer/updCustomerinfo.do?birthdaytime="+$("#customerUpdForm input[name='birthdaytime']").val()+"&collector_data="+$("#collector_data").attr("data-id"),
		dataType:"json",
		async:false,
		data:$("#customerUpdForm").serialize(),
		success:function(data){
			if(data==1){
				toastr.success("修改成功！");
				$("#customerModal").modal("hide");//隐藏模态框
				$("#customerData").bootstrapTable("load",getAllCustomerData());
			}
		},
		error:function(){
			toastr.error("出错啦");
		}
	});
}

//页面上可操作的按钮
function operBtn(){
//	operstr//可页面上可操作的按钮
	//将所有操作按钮禁用
	$("#customertoolbar button").attr("disabled", true);
	var operbtns = operstr.split("|");//可操作的按钮
	var btns = $("#customertoolbar button"); //页面上所按钮
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

//分配客户模态框
function assignCustomerShow(){
	var bychecked = $("#customerData input[name^='btSelectItem']:checkbox:checked");
	if(bychecked.length==0){
		toastr.warning("请先选择待分配的数据");
	}else{
		//获得所有的员工信息（当前拥有者）
		selectUser("#userid_data",user+"电话销售,课程顾问,客顾主管");
		//展示对话框
		$("#assignCustomerModal").modal("show");
	}
	
}

//客户分配操作
function assignCustomer(){
	if(!$("#assigncustomer").valid()){
		return false;
	}
	var bychecked= $('#customerData').bootstrapTable('getSelections');
	swal({
        title: "您确定要分配这 "+bychecked.length+" 条客户数据吗",
       // text: "删除后将无法恢复，请谨慎操作！",
        type: "info",
        showCancelButton: true,
        cancelButtonText:"取消",
        //confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        closeOnConfirm: false
    }, function () {
    	var ctm = "";
    	$.each(bychecked,function(index, i){//客户序号
			ctm += i.customerid + ",";
		});
    	
		$.ajax({
			type:"POST",
			url:"../TbCustomerAssign/assignCustomer.do",
			data:{"customerid":ctm,"userid":$("#userid_data").attr("data-id")},
			dataType:"json",
			async:false,
			success:function(data){
				swal("分配成功！", "已分配好"+ data + "条数据", "success");
				$("#customerData").bootstrapTable("load",getAllCustomerData());
				$("#assignCustomerModal").modal("hide");
			},
			error:function(){
				toastr.error("分配失败，请重新登录再分配");
			}
		});		
    });
}

//新增客户批注展示
function updRemarkShow(){
	
	var tabledata = $("#customerData").bootstrapTable("getSelections");
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
	addressInit("servicetype","remarktype","1001","03");
	
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

//弹出导入框、
function uploadCustomerinfo(){
	$("#uploadCustomerModal").modal("show");
}


//查看历史批注
function qrySeeHistoryRemark(){
	var d= $('#customerData').bootstrapTable('getSelections');
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

//查看分配数据
function　qrySeeHistoryAssign(){
	
}

//查看数据统计
function qrySeeDataCount(){
	$.ajax({
		type:"post",
		url:"../customer/qrySeeDataCount.do",
//		data:{"customerid":d[0].customerid},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var coursehtml = "";
			var sexhtml = "";
			var sourcehtml = "";
			var userhtml = "";
			$.each(json.courselist,function(i,o){
				coursehtml += "<tr><td>"+ selectText("cuscourse",o.course) +"</td>";
				coursehtml += "<td>"+ o.id +"</td></tr>";
			});
			$.each(json.sexlist,function(i,o){
				var sex = "";
				if(o.sex == "0"){
					sex = "女";
				}else{
					sex = "男";
				}
				sexhtml += "<tr><td>"+ sex +"</td>";
				sexhtml += "<td>"+ o.id +"</td></tr>";
			});
			$.each(json.sourcelist,function(i,o){
				sourcehtml += "<tr><td>"+  selectText("source",o.source) +"</td>";
				sourcehtml += "<td>"+ o.id +"</td></tr>";
			});
			$.each(json.userlist,function(i,o){
				userhtml += "<tr><td>"+  o.customername +"</td>";
				userhtml += "<td>"+ o.id +"</td></tr>";
			});
			$("#cousecount").find("tbody").html(coursehtml);
			$("#sexcount").find("tbody").html(sexhtml);
			$("#sourcecount").find("tbody").html(sourcehtml);
			$("#usercount").find("tbody").html(userhtml);
			$("#dataCountModal").modal("show");
		},
		error:function(){
			toastr.error("获得数据失败");
		}
	});
}

//上传简历照片页面跳转
function uploadDatamate(){
	 window.location.href="../customer/datamate.do";//成功后跳转去首页的链接
}

//查看简历详情
function qryResumeDetail(){
	var d = $("#customerData").bootstrapTable("getSelections");
	if(d.length!=1){
		toastr.error("请选择一条数据再操作");
		return false;
	}
	if(d[0].resumeurl == "" || (typeof d[0].resumeurl) == "undefined" || d[0].resumeurl == 'null' ||  d[0].resumeurl == null){
		toastr.warning("该客户还没有上传简历照片，无法查看");
		return false;
	}
	var imghtml = '<img alt="" src="'+ basepath + d[0].resumeurl+'">';
	//宽为100，高为400，距屏顶0象素，屏左0象素，无工具条，无菜单条，无滚动条，不可调整大小，无地址栏，无状态栏。
	window.open (basepath + d[0].resumeurl,'newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	/*window.open(basepath + d[0].resumeurl);*/
	return false;
	$("#resumeImage").html(imghtml);
	$("#resumeImageModal").modal("show");
}

