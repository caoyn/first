/**
 * JS名: userupd.js </br>
 * 描述:员工异动文件</br>
 * 所属:长沙中软计算机系统服务有限公司</br>
 * 开发人员：李敏 </br>
 * 创建时间：2017-06-06 </br>
 */
/*-----------------------------------相关参数--------------------------------------------*/
$(document).ready(function(){
	fillTableData();// 加载表格数据
});

/*-----------------------------------页面加载处理---------查询----------------------------------------*/

function fillTableData(){
	$('#userUpdTable').bootstrapTable({
		data:getALLData(),
		striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#userToolbar",
     //   queryParams: oTableInit.queryParams,//传递参数（*）
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 8,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
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
		        title: '异动员工'
		    }, {
		        field: 'createtime',
		        title: '时间'
		    },{
		        field: 'memo',
		        title: '描述'
		    },{
		        field: 'updtype',
		        title: '变动类型',
		        formatter:function(value,row,index){
		    		if(value == 1){
		    			return "入职";
		    		}else if(value == 2){
		    			return "离职";
		    		}else if(value == 3){
		    			return "转岗";
		    		}
		    	}
		    }]
	});
}

function  getALLData() {
	var obj;
	$.ajax({
		type:"POST",
		url:"SysUser/userUpdData.do ",
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
