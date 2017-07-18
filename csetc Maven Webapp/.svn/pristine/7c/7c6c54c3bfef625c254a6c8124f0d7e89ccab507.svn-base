/**
 * 文件名: managment.js
 * 描述:与角色和用户权限相关的展示和操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-01
 */
var d = new dTree('d','images/system/menu/');
d.config.check=true;
	
$(function() {
	getAllUserTable();//获得所有员工数据
	permissionTree();//权限树
	//getSmallMaxIdOfBigId(14,"#operid");
	$("input[name='userid']").on("input propertychange",function(){
		$.ajax({
			type:"POST",
			url:"../permission/checkUserid.do",
			dataType:"json",
			async:false,
			data:{"userid":$("input[name='userid']").val()},
			success:function(data){		
				if(data != "0") {
	                 $("input[name='userid']").attr("placeholder","不能输入已有数据");
	                 $("input[name='userid']").val("");		                 
	            }   
			},
			error:function(){
				alert("出错了")
			}
		});
	});
	$("#operid select[name='roleid']").change(function(){//当操作表单中角色下拉框数据发生了改变
		oneUserRolePermission($("#operid input[name='userid']").val(),$("#operid select[name='roleid']").val(),"permission/oneRolePermission.do");
	});
	$("#editRolePermission :checkbox").click(function(){
		alert($(this).val());
	});
	
});

function getAllRoleData(){
	$.ajax({//查看所有角色
		type:"POST",
		url:"../role/getAllRoleData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			//if(json.length);	
			$("select[name='roleid']").html("<option>请选择</option>");			
			$.each(json,function(index, obj) {
				if(obj.status == "1"){
					$("select[name='roleid']").append(
	                         "<option value='" + obj.roleid + "'>" + obj.rolename + "</option>"      
	                 );  
				}
            });	            
		}
	});	
}
function getAllPermission(){
	$.ajax({//查看所有权限
		type:"POST",
		url:"../permission/getAllPermission.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			//alert(json.length);
			$.each(json,function(index, obj) {
                 $("#permissions").append(
                         "<input id='"+obj.permissionid+"' type='checkbox' name='permissionid' value='"+obj.permissionid+"'/><label for='"+obj.permissionid+"'>"+obj.permissionname+"</label>  "      
                 );
            });	            
		}
	});	
}

function getSmallMaxIdOfBigId(bigid,selectStr){
	$.ajax({//查看操作
		type:"POST",
		url:"../basecode/getSmallMaxIdOfBigId.do",
		dataType:"json",
		async:false,
		data:{"bigid":bigid},
		success:function(data){
			var json = JSON.parse(data);
			$.each(json,function(index, obj) {
                $(selectStr).append(
                         "<input id='"+obj.level2id+"' type='checkbox' name='operpermission' value='"+obj.level2id+"'/><label for='"+obj.level2id+"'>"+obj.level2name+"</label>  " );  
            });		
		},error:function(){
			alert("数据加载错误3");
		}
	});
}

function a(){
	$.ajax({//
		type:"POST",
		url:"../permission/addUserRole.do",
		dataType:"json",
		async:false,
		data:$("#operid").serialize(),
		success:function(data){
			/* var json = JSON.parse(data);
			$(selectStr).html("");
			$.each(json,function(index, obj) {
                $(selectStr).append(
                         "<input id='"+obj.level2id+"' type='checkbox' name='operpermission' value='"+obj.level2id+"'/><label for='"+obj.level2id+"'>"+obj.level2name+"</label>  " );  
            });		 */
		},error:function(){
			alert("error!");
		}
	});
}




function b(){
		$.ajax({//查看操作
		type:"POST",
		url:"../permission/addRolePermission.do",
		dataType:"json",
		async:false,
		data:{"roleid":$("#roleidstr").val(),"rolepermission":nodeCheckboxValue("#operpermissionTree .dTreeNode :checkbox:checked")},
		success:function(data){
			if(data == "-1"){
				alert("为角色新增模块权限失败");
			}else if(data == "0"){
				alert("为角色新增模块权限成功");
			}
		},error:function(){
			alert("数据加载错误3");
		}
	});
	getAllRole();
}

function getAllRole(){
	$.ajax({//获得所有角色详情
		type:"POST",
		url:"../permission/allRolePermission.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var tablehtml = "";
			$.each(json,function(index, obj) {
				if(obj.status == '1'){
					tablehtml += "<tr><td>" + obj.permissionid + "</td>";
					tablehtml += "<td>" + (obj.permissionname == null ? "-" : obj.permissionname) + "</td>";
					tablehtml += "<td><a onclick='editRolePermission("+obj.actionurl+")'>编辑</a></td></tr>";
				}
            });	
            
            $("#selectAllRole").find("tbody").html(tablehtml);	
		},error:function(){
			alert("数据加载错误!");
		}
	});

}
function getAllUser(){
	$.ajax({//获得所有用户详情
		type:"POST",
		url:"../permission/allUserRolePermission.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var tablehtml = "";
			$.each(json,function(index, obj) {
				tablehtml += "<tr><td>" + obj.username + "</td>";
				tablehtml += "<td>" + (obj.loginname == null ? "-" : obj.loginname) + "</td>";
				tablehtml += "<td>" + (obj.userpassword == null ? "-" : obj.userpassword) + "</td>";
				tablehtml += "<td> </td></tr>";
            });	
            $("#selectAllUser").find("tbody").html(tablehtml);	
		},error:function(){
			alert("数据加载错误!");
		}
	});
}

function editRolePermission(roleid){//编辑角色对应的权限
	oneRolePermission(roleid);
}

function oneRolePermission(roleid){
	d = new dTree('d','images/system/menu/');
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
		},error:function(){ 
			alert("数据加载错误!");
		}
	});
	document.getElementById('operpermissionTree').innerHTML = d;
	d.openAll();
	$("#roleidstr").val(roleid);
}

function editRolePermissionFormFill(data,roleid){
	var json = JSON.parse(data);//将数据填充到修改角色的表单中
	var checkboxhtml = "<input type='hidden' value='"+ roleid +"' name='roleid'>";
	$.each(json,function(index, obj) {  
		if(obj.actionurl == null){
			checkboxhtml += "<input type='checkbox' value='"+ obj.permissionid +"' name='permissionid' id=ed'"+ obj.permissionid +"'><label for=ed'"+ obj.permissionid +"'>"+obj.permissionname+"</label> ";
		}else{
			checkboxhtml += "<input type='checkbox' checked='checked' value='"+ obj.permissionid +"' name='permissionid' id=ed'"+ obj.permissionid +"'><label for=ed'"+ obj.permissionid +"'>"+obj.permissionname+"</label> ";
		}					
    });
    checkboxhtml += "<input type='button' onclick='updRolePermission()' value='修改'>"	
    $("#editRolePermission").html(checkboxhtml);	
}

function updRolePermission(){
	$.ajax({//根据角色查看该角色对应的可以看的权限
		type:"POST",
		url:"../permission/updRolePermission.do",
		dataType:"json",
		async:false,
		data:$("#editRolePermission").serialize(),
		success:function(data){
			alert("修改成功!");
            getAllRole();
		},error:function(){
			alert("数据加载错误!");
		}
	});
}

function fillRolePermission(data){//将角色数据填充到角色下拉框后面
	var json = JSON.parse(data);
	var tablehtml = "<table border='1'><tr><th>角色名</th><th>页面权限</th><th>操作</th></tr>";
	$.each(json,function(index, obj) { 
		if(obj.permissiondesc != null){
			tablehtml += "<tr><td>"+obj.permissiondesc+"</td>";
			tablehtml += "<td>"+obj.permissionname+"</td>";
			tablehtml += "<td><a>编辑</a></td></tr>";
		}		
    });
    tablehtml += "</table>";
	$("#selectOneRolePermission").html(tablehtml);
}

//获得所有的权限和与之对应的操作
function allOperpermission(){
	$.ajax({
		type:"POST",
		url:"../permission/allOperpermission.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			$.each(json,function(index,obj){
				d.add(obj.permissionid, obj.level, obj.permissionname, "javascript:;", obj.permissionanme);
			});
		},error:function(){
			alert("数据加载错误!");
		}
	});
	document.getElementById('operpermissionTree').innerHTML = d;
	d.openAll();
}
	

//获得所有用户
function getAllUserTable(){
	$.ajax({
		type:"POST",
		url:"../SysUser/getAllUserData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var tablehtml = "";
			var json = JSON.parse(data);
			$.each(json,function(index,obj){
				//alert();
				tablehtml += "<tr><td>" + (index + 1) + "</td>";
				tablehtml += "<td>" + obj.userid + "</td>";
				tablehtml += "<td>" + obj.username + "</td>";
				tablehtml += "<td>" + obj.loginname + "</td>";
				tablehtml += "<td>" + obj.deptid + "</td>";
				tablehtml += "<td>" + obj.status + "</td>";
				if( obj.status == '1'){
					tablehtml += "<td><a onclick='authorizeShow(\""+obj.userid+"\")'>授权</a></td><tr>";
				}else{
					tablehtml += "<td></td><tr>";
				}
				
				
			});
			$("#userTable").html(tablehtml);
		},error:function(){
			alert("数据加载错误!--");
		}
	});
}

//给用户授权展示
function authorizeShow(userid){
//	alert(userid);
	getAllRoleData();//显示所有角色（下拉框）
	oneUserRolePermission(userid,"","../permission/oneUserRolePermission.do","1");
	$("#operid input[name='userid']").val(userid);//
}

//用户关联角色展示
function oneUserRolePermission(userid,roleid,url,type){
	d = new dTree('d','../images/system/menu/');
	d.config.check=true;
	$.ajax({//根据角色查看该角色对应的可以看的权限数据
		type:"POST",
		url:url,
		dataType:"json",
		async:false,
		data:{"userid" : userid, "roleid" : roleid},
		success:function(data){
			var json = JSON.parse(data);
			var count = 0;
			$.each(json,function(index,obj){	
				if(obj.ordernum != null && obj.ordernum != "null"){
					d.add(obj.permissionid, obj.level, obj.permissionname, "javascript:;", obj.permissionanme,"","","",true,"");
					count ++;
				}else{
					d.add(obj.permissionid, obj.level, obj.permissionname, "javascript:;", obj.permissionanme,"","","",false,"");
				}
			});
			if(count>0 && type == "1"){
				alert("该用户已经授权");
			}
		},error:function(){ 
			alert("数据加载错误!");
		}
	});
	document.getElementById('userOperPermissionTree').innerHTML = d;
	d.openAll();
	$("#operid input[name='userid']").val(userid);
}


//给某用户新增角色和权限关系
function addUserRolePermission(){
	
	$.ajax({
		type:"POST",
		url:"../permission/addUserRolePermission.do",
		dataType:"json",
		async:false,
		data:{"userid":$("#operid input[name='userid']").val(),"roleid":$("#operid select[name='roleid']").val(),"rolepermission":nodeCheckboxValue("#userOperPermissionTree .dTreeNode :checkbox:checked")},
		success:function(data){
			alert("新增成功");
			getAllUserTable();//获得所有员工数据
		},error:function(){
			alert("error!");
		}
	});
	
}

//选中树复选框的值
function nodeCheckboxValue(checkboxStr){
	//获得b表单中的checkbox "#operpermissionTree .dTreeNode :checkbox:checked"
	var checkboxs = $(checkboxStr);
	//alert(checkboxs.length);
	var checkboxarray ="";
	if(checkboxs.length>0){		
		checkboxs.each(function(){ //由于复选框一般选中的是多个,所以可以循环输出 
			checkboxarray += $(this).val() + ",";
		});
	}
	return checkboxarray;
}




//权限树
function permissionTree(){
	var settings = {
			   async: {
					enable: true,
					url: "permission/getAllPermission.do",
					dataFilter: ajaxDataFilter,					
					autoParam: ["id"]
			   },
			   data: {
				    key: {
						name: "permissionname"
					},
					simpleData: {
						enable: true,
						idKey: "permissionid",
						pIdKey: "level",
						rootPId: 0
					}
				},
			   view : {  
		            addHoverDom: addHoverDom,       // 用于当鼠标移动到节点上时，显示用户自定义控件。务必与 setting.view.removeHoverDom 同时使用  
		            removeHoverDom: removeHoverDom, // 用于当鼠标移出节点时，隐藏用户自定义控件。务必与 addHoverDom 同时使用  
		        }
			};
	$.fn.zTree.init($("#permissionTree"), settings);
}

//格式化权限管理数据
function ajaxDataFilter(treeId, parentNode, responseData) {
    return eval(responseData);
};


//当鼠标移到节点上显示用户自定义控件
function addHoverDom(treeId, treeNode) {
	var status;
	var statustext; 
	$.ajax({
		type:"POST",
		url:"../permission/getPermissionDataById.do",
		dataType:"json",
		async:false,
		data:{"permissionid":treeNode.permissionid},
		success:function(data){
			var json = JSON.parse(data);
			if(json.length>0){
				 $.each(json,function(index, obj) {
	                // alert("名称："+ obj.deptname +"级别："+obj.deptlevel+"业绩核算类型："+obj.performance+"部门负责人："+obj.userid+"部门类型："+obj.depttype+"状态："+obj.status)
					 if(obj.status == "0"){
						 statustext = "去启用";
						 status = 1;
					 }else{
						 statustext =  "去禁用";
						 status = 0;
					 }
				 });		
			}
		},error:function(){
			alert("数据加载错误");
		}
	});
	var aObj = $("#" + treeNode.tId + "_a");
	if ($("#diyBtn_"+treeNode.id).length>0) return;
	var editStr = "<span id='diyBtn_space_" +treeNode.id+ "' > </span>"
		+ "<button type='button' class='diyBtn1' id='diyBtn_" + treeNode.id
		+ "' title='"+treeNode.name+"' onfocus='this.blur();'> " + statustext+ "</button>";
	aObj.append(editStr);
	var btn = $("#diyBtn_"+treeNode.id);
	if (btn) btn.bind("click", function(){
		if(confirm("确定要"+statustext+"此部门吗？其下的部门也会被"+statustext+"哦")){
			 var str = treeNode.permissionid ;
			// alert(str);
		     if(treeNode.isParent){
		         str = getAllChildrenNodes(treeNode,str);
		     }else{
		         str = treeNode.permissionid;
		     }
			updPermissionStatus(str,status);
		}
	});
};
//当鼠标移出节点移除自定义控件
function removeHoverDom(treeId, treeNode) {
	$("#diyBtn_"+treeNode.id).unbind().remove();
	$("#diyBtn_space_" +treeNode.id).unbind().remove();
};

//更改权限状态
function updPermissionStatus(permissionid,status){
	$.ajax({
		type:"POST",
		url:"../permission/updPermissionStatus.do",
		dataType:"json",
		async:false,
		data:{"permissionid":permissionid,"status":status},
		success:function(data){
			if(data>0){
				alert("修改状态成功！");
			}
		},error:function(){
			alert("数据加载错误");
		}
	});
}

//使用了递归，得到叶子节点的数据
function getAllChildrenNodes(treeNode,result){
    if (treeNode.isParent) {
      var childrenNodes = treeNode.children;
      if (childrenNodes) {
          for (var i = 0; i < childrenNodes.length; i++) {
           if(childrenNodes[i].isParent){
        	   result += getAllChildrenNodes(childrenNodes[i], result);
           }else{
        	   result += ',' + childrenNodes[i].permissionid;
           }
          }
      }
    }
    return result;
}


