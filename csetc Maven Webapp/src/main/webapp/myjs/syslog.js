/**
 * JS名: syslog.js </br>
 * 描述:系统日志文件</br>
 * 所属:长沙中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-06-06 </br>
 */
/*-----------------------------------相关参数--------------------------------------------*/
$(document).ready(function(){
	fillTableData();// 加载表格数据
});

/*-----------------------------------页面加载处理---------查询----------------------------------------*/
/*
function fillTableData(){
	$('#sysLogTable').bootstrapTable({
		data:getALLData(),
		striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#userToolbar",
       // queryParams: queryParams,//传递参数（*）
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 12,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
     //   showRefresh: true,                  //是否显示刷新按钮
     //   minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
     //  height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: 'id',                     //每一行的唯一标识，一般为主键列
     //   showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
     //   cardView: false,                    //是否显示详细视图
     //   detailView: false,                   //是否显示父子表
			columns: [{
				field: 'id',
				title: '编号'
			}, {
		        field: 'userid',
		        title: 'EHR编号'
		    }, {
		        field: 'username',
		        title: '操作人'
		    }, {
		        field: 'createtime',
		        title: '时间'
		    },{
		        field: 'remoteip',
		        title: '远程ip'
		    },{
		    	field: 'permid',
		    	title: '访问的功能编号'
		    },{
		    	field: 'permissionname',
		    	title: '访问的功能名称'
		    },{
		    	field: 'requesturl',
		    	title: '请求的url'
		    },{
		    	field: 'requestoper',
		    	title: '请求的操作'
		    }]
	});
}*/
function fillTableData(){
	$('#sysLogTable').bootstrapTable({
		url:"SysUser/getLogData.do ",
		method: 'post',
        contentType: "application/x-www-form-urlencoded",
		striped: true,                      //是否显示行间隔色
		cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true,                   //是否显示分页（*）
		sortable: false,                     //是否启用排序
		sortOrder: "asc",                   //排序方式
		toolbar:"#userToolbar",
		queryParamsType:'',					//默认值为limit(传递的参数为offset,limit,sort)设置为''(传递的参数为pageSize,pageNumber)
		queryParams: queryParams,//传递参数（*）
		sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		pagination:true,					//是否开启分页
		pageNumber:1,                       //初始化加载第一页，默认第一页
		pageSize: 12,                       //每页的记录行数（*）
		pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
		//search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		//strictSearch: false,
		showColumns: true,                  //是否显示所有的列
		//   showRefresh: true,                  //是否显示刷新按钮
		//   minimumCountColumns: 2,             //最少允许的列数
		clickToSelect: true,                //是否启用点击选中行
		//  height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId: 'id',                     //每一行的唯一标识，一般为主键列
		/*  showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,   */                 //是否显示详细视图
		//   detailView: false,                   //是否显示父子表
		columns: [{
			field: 'id',
			title: '编号'
		}, {
			field: 'userid',
			title: 'EHR编号'
		}, {
			field: 'username',
			title: '操作人'
		}, {
			field: 'createtime',
			title: '时间',
			formatter:function(value,row,index){
	    		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	    	}
		},{
			field: 'remoteip',
			title: '远程ip'
		},{
			field: 'permid',
			title: '访问的功能编号'
		},{
			field: 'permissionname',
			title: '访问的功能名称'
		},{
			field: 'requesturl',
			title: '请求的url'
		},{
			field: 'requestoper',
			title: '请求的操作'
		}]
	});
}

function  getALLData() {
	var obj;
	$.ajax({
		type:"POST",
		url:"SysUser/getLogData.do ",
		dataType:"JSON",
		async:false,
		success:function(data){
			obj = JSON.parse(data);
			console.log(obj);
			$.each(obj,function(index, i){
				i.createtime = new Date(i.createtime).format("yyyy-MM-dd hh:mm:ss")
			});
			
		},
		error:function(){
			toastr.error("出错了，获得用户权限数据失败！");
		}
	});
	return obj;
}

//查询的参数
function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageNumber: params.pageNumber,   //页码
        pageSize: params.pageSize,	//每页显示多少条
    };
    if(params.search != null && params.search != ""){
    	temp.username = params.search;
    }
    console.log();
    return temp;
}

//日期时间戳格式化
Date.prototype.format =function(format){
var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(), //day
	"h+" : this.getHours(), //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
	"S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4- RegExp.$1.length));
for(var k in o)if(new RegExp("("+ k +")").test(format))
	format = format.replace(RegExp.$1,RegExp.$1.length==1? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	return format;
}

