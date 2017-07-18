/**
 * 文件名: role.js
 * 描述:角色管理相关的操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-05
 */
$(document).ready(function(){
	fillTableData();
	
});
var d;

//填充表格数据
function fillTableData(){
	$('#allRoleData').bootstrapTable({
	    data:getRoleData(),
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#roletoolbar",
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
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
	    }, {
	    	field: 'permissionid',
	    	title: '角色名称',
	    	sortable:true
	    }, {
	    	field: 'permissionname',
	    	title: '角色权限',
	    	sortable:true
	    }, {
	    	field: 'status',
	    	title: '状态',
	    	sortable:true,
	    	formatter:function(value,row,index){
	    		if(value == 1){
	    			return "启用";
	    		}else if(value == 0){
	    			return "停用";
	    		}
	    	}
		},{
			field: 'oper',
	    	title: '操作',
		}]
		
	});
}

//获得角色数据
function getRoleData(){
	var json;
	$.ajax({
		type:"POST",
		url:"../permission/allRolePermission.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json,function(i,o){
				if(o.status == 1){
					o.oper = "<span class='small text-center'><button class='btn btn-xs btn-danger' onclick='updRoleStatus(" + o.actionurl + ",0)'><i class='fa fa-close'></i>停用</button></span>";
				}else{
					o.oper = "<span class='small text-center'><button class='btn btn-xs btn-primary' onclick='updRoleStatus(" + o.actionurl + ",1)'><i class='fa fa-check'></i>启用</button></span>";
				}
			})
		},
		error:function(){
			toastr.error("获得角色数据失败");
		}
	});
	console.log(json);
	return json;
}

//修改角色权限展示
function updRoleModalShow(){
	var tableData = $("#allRoleData").bootstrapTable("getSelections");
	if(tableData.length!=1){
		toastr.warning("请选择一条数据再操作！")
		return false;
	}
	getRolePermissionInfo(tableData[0].actionurl)//角色id
	$("#operpermissionModal").modal("show");
}

//获得角色权限信息
function getRolePermissionInfo(roleid){
	d = new dTree('d','../images/system/menu/');
	d.config.check=true;
	
	$.ajax({//根据角色查看该角色对应的可以看的权限数据
		type:"POST",
		url:"../permission/oneRolePermission.do",
		dataType:"json",
		async:false,
		data:{"roleid":roleid},
		success:function(data){
			var json = JSON.parse(data);
			$.each(json,function(index,obj){	
				if(obj.ordernum != null && obj.ordernum != "null"){
					d.add(obj.permissionid, obj.level, obj.permissionname, "javascript:;", obj.permissionanme,"","","",true,"");
				}else{
					d.add(obj.permissionid, obj.level, obj.permissionname, "javascript:;", obj.permissionanme,"","","",false,"");
				}
			});
		},
		error:function(){
		}
	});
	document.getElementById('operpermissionTree').innerHTML = d;
	d.closeAll();
	$("#roleidstr").val(roleid);
}

//角色授权
function rolePermission(){
	$.ajax({//查看操作
		type:"POST",
		url:"../permission/addRolePermission.do",
		dataType:"json",
		async:false,
		data:{"roleid":$("#roleidstr").val(),"rolepermission":nodeCheckboxValue("#operpermissionTree .dTreeNode :checkbox:checked")},
		success:function(data){
			if(data == "-1"){
				toatsr.error("为角色新增模块权限失败");
			}else if(data == "0"){
				toastr.success("为角色新增模块权限成功");
				$("#operpermissionModal").modal("hide");//隐藏模态框
				$("#allRoleData").bootstrapTable("load",getRoleData());//重新加载表格数据
			}
		},error:function(){
			toastr.error("数据加载错误");
		}
	});
}

//选中树复选框的值
function nodeCheckboxValue(checkboxStr){
	//获得b表单中的checkbox "#operpermissionTree .dTreeNode :checkbox:checked"
	var checkboxs = $(checkboxStr);
	var checkboxarray ="";
	if(checkboxs.length>0){		
		checkboxs.each(function(){ //由于复选框一般选中的是多个,所以可以循环输出 
			checkboxarray += $(this).val() + ",";
		});
	}
	return checkboxarray;
}

//新增角色详情展示
function addRoleShow(){
	$("#roleModal .btn-primary").attr("onclick","insert()")
	$("#roleModal").modal("show");
}

//新增角色
function insert(){
	if(!$("#roleForm").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../role/insert.do",
		data:$("#roleForm").serialize(),
		dataType:"json",
		success:function(data){
			toastr.success("新增角色成功");
			$("#roleModal").modal("hide");
			$("#allRoleData").bootstrapTable("load",getRoleData());
		},
		error:function(){
			toastr.error("新增角色失败");
		}
	})
}

//启用或停用该角色
function updRoleStatus(roleid,status){
	$.ajax({
		type:"post",
		url:"../role/update.do",
		data:{"roleid":roleid,"status":status},
		dataType:"json",
		async:false,
		success:function(data){
			toastr.success("操作成功");
			$("#allRoleData").bootstrapTable("load",getRoleData());
		},
		erroe:function(){
			toastr.error("操作失败！");
		}
	})
}