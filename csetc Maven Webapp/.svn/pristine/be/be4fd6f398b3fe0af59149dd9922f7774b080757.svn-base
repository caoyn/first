/**
 * 文件名: datamate.js
 * 描述:客户信息(未上传简历照片)
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-07-05
 */
$(document).ready(function(){
	fillTableData();
	
});





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
			alert(data);
		},
		error:function(){
			toastr.error("上传附件失败");
			
		}
	
	});
	
	return false;
}



//获得表格数据
function getAllCustomerData(){
	var json 
	$.ajax({
		type:"POST",
		url:"../customer/datamateinfo.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json, function(i, o) {
				var c = o.customer;
//				o.majortext = selectText("major",o.major);
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
				o.oper = "<button class='btn btn-xs' onclick='uploadResumeShow(\""+o.customerid+"\")'>上传简历</button>";
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
	$('#datamateTable').bootstrapTable({
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
        pageSize: 20,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 10,             //最少允许的列数
        //clickToSelect: true,                //是否启用点击选中行
        height: 670,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
			title: '学历'
	    }, {
	    	field: 'createtime',
	    	title: '创建时间'
	    }, {
	    	field: 'sourcetext',
	    	title: '来源渠道'
	    }, {
	    	field: 'customertypetext',
	    	title: '客户类型'
	    }, {
	    	field: 'coursetext',
	    	title: '意向课程'
	    }, {
	    	field: 'jobobjectivetext',
	    	title: '求职意向'
	    }, {
	    	field: 'birthday',
	    	title: '出生年月'
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
	    	title: '备注'
	    }, {
	    	field: 'status',
	    	title: '状态'
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
	    	}
	    }, {
	    	field: 'oper',
	    	title: '操作'
		}],
		onDblClickRow:function(row,ele,field){//双击行
			customerinfoUpdAndShow(row);
		}
	});
	
	hideTableCol();
}

//隐藏表格列
function hideTableCol(){
	$("#datamateTable").bootstrapTable("hideColumn","operator");
	$("#datamateTable").bootstrapTable("hideColumn","collector");
	$("#datamateTable").bootstrapTable("hideColumn","education");
	$("#datamateTable").bootstrapTable("hideColumn","source");
	$("#datamateTable").bootstrapTable("hideColumn","customertype");
	$("#datamateTable").bootstrapTable("hideColumn","course");
	$("#datamateTable").bootstrapTable("hideColumn","jobobjectivetext");
	$("#datamateTable").bootstrapTable("hideColumn","placeofbirthtext");
	$("#datamateTable").bootstrapTable("hideColumn","address");
	$("#datamateTable").bootstrapTable("hideColumn","politicalstatustext");
	$("#datamateTable").bootstrapTable("hideColumn","nationalitytext");
	$("#datamateTable").bootstrapTable("hideColumn","maritalstatustext");
	$("#datamateTable").bootstrapTable("hideColumn","workservice");
	$("#datamateTable").bootstrapTable("hideColumn","workexp");
	$("#datamateTable").bootstrapTable("hideColumn","workplacetext");
	$("#datamateTable").bootstrapTable("hideColumn","salarytext");
	$("#datamateTable").bootstrapTable("hideColumn","ecp");
	$("#datamateTable").bootstrapTable("hideColumn","bank");
	$("#datamateTable").bootstrapTable("hideColumn","bankno");
	$("#datamateTable").bootstrapTable("hideColumn","memo");
	$("#datamateTable").bootstrapTable("hideColumn","status");
	$("#datamateTable").bootstrapTable("hideColumn","birthday");
	$("#datamateTable").bootstrapTable("hideColumn","placeofbirth");
	$("#datamateTable").bootstrapTable("hideColumn","customertypetext");
}


//照片上传弹出框
function uploadResumeShow(cid){
	$("#uploadResume input[name='customerid']").val(cid);
	$("#uploadResumeModal").modal("show");
}
//照片上传
function addUploadResume(){
	var formData = new FormData($( "#uploadResume" )[0]);
	$.ajax({
		type:"post",
		url:"../customer/uploadResume.do?",
		data:formData,
		dataType:"json",
		cache: false,  
		contentType: false,
        processData: false,
		async:false,
		success:function(data){
			alert("上传成功！");
			$("#datamateTable").bootstrapTable("load",getAllCustomerData);
		},
		error:function(){
			toastr.error("上传附件失败");
			
		}
	
	});
	
}