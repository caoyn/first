/**
 * 文件名: dept.js
 * 描述:与部门相关的页面展示及相关处理
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-21 
 */

   var chooseDepartment = "";//被选中的部门
   var did = "";//被选中的部门序号
   var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {
	   data: {
		    key: {
				name: "deptname"
			},
			simpleData: {
				enable: true,
				idKey: "deptid",
				pIdKey: "topdeptid",
				rootPId: 0
			}
		},
		view : {  
            addHoverDom: addHoverDom,       // 用于当鼠标移动到节点上时，显示用户自定义控件。务必与 setting.view.removeHoverDom 同时使用  
            removeHoverDom: removeHoverDom, // 用于当鼠标移出节点时，隐藏用户自定义控件。务必与 addHoverDom 同时使用  
            //dblClickExpand : false,  
            //selectedMulti : false  
        }, 
		callback: {
			//beforeEditName: zTreeBeforeEditName,//编辑节点名字前
			onClick: zTreeOnClick, //点击节点发生的事件
			beforeRename: zTreeBeforeRename, //修改节点名称前发生的事件
			onRename: zTreeOnRename, //修改节点名称时发生的事件
			beforeRemove: zTreeBeforeRemove,//删除某节点之前的操作
			onRemove: zTreeOnRemove, //删除某节点时发生的事件
			beforeDrag:function(){return false;}//托拽之前的事件（不允许拖拽）
		}
   };
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   var zNodes ;
  




$(document).ready(function(){
	getAllJsonDeptData();//获得所有部门的json数据用于生成树
//	getAllUserData();//获得所有员工
//	getTopDeptData("");//获得上级部门
//	getALLBigTypeData();//获得基础代码中所有的大类
	
//	
	
	//当大类名称的下拉框的值发生改变就接着改变小类下拉框的值
	$("#addBasecodeForm select[name='level1id']").change(function(){		
		getSmallMaxIdOfBigId($(this).val(),"#addBasecodeForm select[name='level2id']");
	});
	
	$("#addDeptForm input[name='deptname']").bind("input propertychange",function(){
		judgeExistDept();//判断输入的部门是否存在
	});
	$("#addBasecodeForm input[name='level2name']").bind("input propertychange",function(){
		judgeExist();//判断输入的小类是否存在
	});
	$("#addBasecodeForm input[name='level1name']").bind("input propertychange",function(){		
		judgeExistMax();//判断输入的大类是否存在
	});
	
	//当单选按钮发生改变时
	$("#addDeptForm input:radio[name='deptlevel']").change(function(){
		getTopDeptData("");//获得上级部门
	})
	
	
});

//树数据(部门)
function treeData(){
	$.ajax({
		type:"POST",
		url:"dept/getAllDept.do",
		async:false,
		dataType:"json",
		success:function(data){
			zNodes = JSON.parse(data);
		},
		error:function(){
			toastr.warning("出错了，加载数据失败");
		}
	});
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	zTreeObj.setEditable(true);
}




//新增部门展示
function ADDdeptShow(){
	$("#addDeptForm input[type=reset]").trigger("click");//重置表单
	if(chooseDepartment.length>0){
		//指定下拉框只有一条数据
		$("#addDeptForm select[name='topdeptid']").html("<option value='"+chooseDepartment.substring(0,chooseDepartment.indexOf(":"))+"'>"+chooseDepartment.substring(chooseDepartment.indexOf(":")+1,chooseDepartment.length)+"</option>");
	}else{
		deptDataSelectData();
	}
	getSmallMaxIdOfBigId("12","#addDeptForm select[name='depttype']");//查看部门类型
	getSmallMaxIdOfBigId("13","#addDeptForm select[name='performance']");//查看业务核算类型
	deptUser();//部门负责人下拉框
	$("#addDeptModal .btn-primary").attr("onclick","ADDdept()");
	$("#addDeptModal .modal-title").html("新增部门信息");
	$("#addDeptModal").modal("show");
	
}
//新增部门
function ADDdept(){
	if(!$("#addDeptForm").valid()){
		return false;
	}
	alert($("#addDeptForm").valid());
	$.ajax({
		type:"POST",
		url:"dept/ADDdept.do?deptUser_data="+$("#deptUser_data").attr("data-id"),
		data:$("#addDeptForm").serialize(),
		async:false,
		dataType:"json",
		success:function(data){
			if(data>0){
				toastr.success('新增部门成功');
				getAllJsonDeptData();
				$("#addDeptModal").modal("hide");
			}
		},
		error:function(){
			toastr.warning("出错了，新增部门失败");
		}
	});
}

//判断输入的部门是否存在
function judgeExistDept(){
	$.ajax({
		type:"POST",
		url:"dept/judgeExistDept.do",
		dataType:"json",
		async:false,
		data:{"deptname":$("#addDeptForm input[name='deptname']").val()},
		success:function(data){
			if(JSON.parse(data).length != 0){
				$("#addDeptForm input[name='deptname']").attr("placeholder","不能输入已有数据");
				$("#addDeptForm input[name='deptname']").val("");			
			}
		}
	});
}

//获得所有的员工信息
function getAllUserData(){
	$.ajax({
		type:"POST",
		url:"SysUser/getAllUserData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			//alert(json.length);
			$("#addDeptForm select[name='userid']").html("");
			 $.each(json,function(index, obj) {
                 $("#addDeptForm select[name='userid']").append(
                         "<option value="+obj.userid+">" +obj.username+ "</option>");  
             });		
		},error:function(){
			toastr.warning("数据加载错误");
		}
	});
}

//获得上级部门
function getTopDeptData(topdeptid){
	$.ajax({
		type:"POST",
		url:"dept/juniorDeptData.do",
		dataType:"json",
		async:false,
		data:{"topdeptid":topdeptid},
		success:function(data){
			var json = JSON.parse(data);
			//alert(json.length);
			//判断选中的是单选按钮组中的哪一个
			$("#addDeptForm select[name='topdeptid']").html("");
			if($('#addDeptForm input:radio[name="deptlevel"]:checked').val() == 0){
				$("#addDeptForm select[name='topdeptid']").html("<option value=''>无</option>");
			}else{
				 $.each(json,function(index, obj) {
	                 $("#addDeptForm select[name='topdeptid']").append(
	                         "<option value="+obj.deptid+">" +obj.deptname+ "</option>");  
	            });	
			}
			
				
		},error:function(){
			toastr.warning("数据加载错误");
		}
		
	});
}



//获得基础代码的所有大类
function getALLBigTypeData(){
	$.ajax({
		type:"POST",
		url:"basecode/getALLBigTypeData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			//alert(json.length);
			$("#addBasecodeForm select[name='level1id']").html("");
			 $.each(json,function(index, obj) {
                 $("#addBasecodeForm select[name='level1id']").append(
                         "<option value="+obj.level1id+">" +obj.level1name+ "</option>");  
             });		
		},error:function(){
			toastr.warning("数据加载错误^_^");
		}
	});
}



//根据大类id获得所有小类
function getSmallMaxIdOfBigId(bigid,selectStr,id){
	$.ajax({
		type:"POST",
		url:"basecode/getSmallMaxIdOfBigId.do",
		dataType:"json",
		async:false,
		data:{"bigid":bigid},
		success:function(data){
			var json = JSON.parse(data);
			$(selectStr).html("");
			 $.each(json,function(index, obj) {
				 if(obj.level2id == id || obj.level2name == id){
					 $(selectStr).append(
	                         "<option selected='selected' value="+obj.level2id+">" +obj.level2name+ "</option>");  
				 }else{
					 $(selectStr).append(
	                         "<option value="+obj.level2id+">" +obj.level2name+ "</option>");  
				 }
             });		
		},error:function(){
			toastr.warning("数据加载错误");
		}
	});
}

//修改部门名称
function updDeptName(deptid,deptname){
	$.ajax({
		type:"POST",
		url:"dept/updDeptName.do",
		dataType:"json",
		async:false,
		data:{"deptid":deptid, "deptname": deptname},
		success:function(data){
			if(data>0){
				toastr.success("修改部门名称成功！");
			}
		},error:function(){
			toastr.warning("数据加载错误");
		}
	});
}

//修改部门状态
function updDeptStatus(deptid,status){
	return false;
	$.ajax({
		type:"POST",
		url:"dept/updDeptStatus.do",
		dataType:"json",
		async:false,
		data:{"deptid":deptid, "status": status},
		success:function(data){
			if(data>0){
				toastr.success("修改部门状态成功");
			}	
		},error:function(){
			toastr.warning("数据加载错误3");
		}
	});
}

//点击节点发生的事件
function zTreeOnClick(event, treeId, treeNode) {
	//查看被点击的节点的相关数据
	/*$.ajax({
		type:"POST",
		url:"dept/getDeptDataById.do",
		dataType:"json",
		async:false,
		data:{"deptid":treeNode.deptid},
		success:function(data){
			var json = JSON.parse(data);
			if(json.length>0){
				 $.each(json,function(index, obj) {
	                 alert("名称："+ obj.deptname +"级别："+obj.deptlevel+"业绩核算类型："+obj.performance+"部门负责人："+obj.userid+"部门类型："+obj.depttype+"状态："+obj.status)
	             });		
			}
		},error:function(){
			alert("数据加载错误");
		}
	});*/
   // alert(treeNode.tId + ", " + treeNode.name);
   //alert(treeNode.deptid+ ", " + treeNode.deptname);
}
//修改节点名称前发生的事件
function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
	if(!isCancel){
		if(treeNode.deptname == newName || newName.length == 0){
			return false;
		}
	}
}
//修改节点名称时发生的事件
function zTreeOnRename(event, treeId, treeNode) {
	//alert(treeNode.deptid+ ", " + treeNode.deptname);
	$.ajax({
		type:"POST",
		url:"dept/updDeptName.do",
		dataType:"json",
		async:false,
		data:{"id":treeNode.id, "deptname": treeNode.deptname},
		success:function(data){
			if(data>0){
				toastr.success("修改部门名称成功")
			}
		},error:function(){
			toastr.warning("数据加载错误3");
		}
	});
}
//删除某节点前发生的事件
function zTreeBeforeRemove(treeId, treeNode) {
	if(typeof(treeNode.children) == "object"){
		alert("其下含有子部门不允许删除");
		return false;
	}else{
		$.ajax({
			type:"POST",
			url:"SysUser/getUserByDeptid.do",
			dataType:"json",
			async:false,
			data:{"id":treeNode.id},
			success:function(data){
				var json = JSON.parse(data);
				if(json.length>0){
					toastr.warning("该部门含有员工不允许删除");
					return false;
				}
			}
		});
	}	
	if(!confirm("确定要删除  ["+ treeNode.deptname +" ] 部门吗？")){
		return false;
	}
	
}
//删除某节点时发生的事件
function zTreeOnRemove(event, treeId, treeNode) {
	$.ajax({
		type:"POST",
		url:"dept/delDetpById.do",
		dataType:"json",
		async:false,
		data:{"id":treeNode.id},
		success:function(data){
			if(data>0){
				toastr.success("删除成功！")
			}else{
				toastr.warning("该部门下还有员工，不允许删除！")
			}
		},error:function(){
			toastr.warning("数据加载错误!");
		}
	});
}

//当鼠标移到节点上显示用户自定义控件
function addHoverDom(treeId, treeNode) {
	var status;
	var statustext; 
	$.ajax({
		type:"POST",
		url:"dept/getDeptDataById.do",
		dataType:"json",
		async:false,
		data:{"deptid":treeNode.deptid},
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
			toastr.warning("数据加载错误");
		}
	});
	var aObj = $("#" + treeNode.tId + "_a");
	if ($("#diyBtn_"+treeNode.id).length>0) return;
	var editStr = "<span id='diyBtn_space_" +treeNode.id+ "' > </span>"
		+ "<button type='button' class='diyBtn1' id='diyBtn_" + treeNode.id
		+ "' title='"+treeNode.name+"' onfocus='this.blur();'> " + statustext+ "</button>"
	//	+ "<span id ='diySpan_"+ treeNode.id +"' onfocus='this.blur();'>部门可卖产品</span>";
	aObj.append(editStr);
	var btn = $("#diyBtn_"+treeNode.id);
	//var a = $("#diySpan_"+treeNode.id)
	if (btn) btn.bind("click", function(){
		if(confirm("确定要"+statustext+"此部门吗？其下的部门也会被"+statustext+"哦")){
			 var str = treeNode.deptid ;
		     if(treeNode.isParent){
		         str = getAllChildrenNodes(treeNode,str);
		     }else{
		         str = treeNode.deptid;
		     }
			updDeptStatus(str,status);
		}
	})/*;
	if (a) a.bind("click", function(){
		authorize(treeNode.deptid);
	});*/
};
//当鼠标移出节点移除自定义控件
function removeHoverDom(treeId, treeNode) {
	$("#diyBtn_"+treeNode.id).unbind().remove();
	$("#diyBtn_space_" +treeNode.id).unbind().remove();
};
//
/*function zTreeBeforeEditName(treeId, treeNode) {
	return !treeNode.isParent;
}*/

//使用了递归，得到叶子节点的数据
function getAllChildrenNodes(treeNode,result){
    if (treeNode.isParent) {
      var childrenNodes = treeNode.children;
      if (childrenNodes) {
          for (var i = 0; i < childrenNodes.length; i++) {
           if(childrenNodes[i].isParent){
        	   result += getAllChildrenNodes(childrenNodes[i], result);
           }else{
        	   result += ',' + childrenNodes[i].deptid;
           }
          }
      }
    }
    return result;
}

//部门可卖的产品(查看所有的产品)
function authorize(deptid){
	var deptid ;
	if(chooseDepartment.length>0){
		deptid = chooseDepartment.substring(0,chooseDepartment.indexOf(":"));
		var prodenable = getDeptProductPermission(deptid);
		var products = [];
		if(prodenable != ""){
			products = prodenable.split("|");
		}
		$.ajax({
			type:"POST",
			url:"dept/getAllViableProduct.do",
			dataType:"json",
			async:false,
			success:function(data){
				var json = JSON.parse(data);
				var productHtml = "";
				$.each(json,function(index,o){
					var flag = true;
					for(var i = 0; i<products.length;i++){
						if(products[i] == o.prodid){
							flag = false;
							break;
						}
					}
					if(flag){
						productHtml += "<div class='checkbox checkbox-inline checkbox-success'><input type='checkbox' name='prodenable' value='"+ o.prodid +"' id='"+ o.prodid +"'/><label for='"+o.prodid+"'>"+ o.subprodname +"</label></div>";
					}else{
						productHtml += "<div class='checkbox checkbox-inline checkbox-success'><input type='checkbox' checked='checked' name='prodenable' value='"+ o.prodid +"' id='"+ o.prodid +"'/><label for='"+o.prodid+"'>"+ o.subprodname +"</label></div> ";
					}
					
				});
				$("#allViableProduct").html(productHtml);//添加复选框
				$("#deptAuthorizeModal .btn-primary").attr("onclick","addOrUpdProdPermission(\""+deptid+"\")");//保存事件
				$("#deptAuthorizeModal").modal("show");//展示模态框
			},
			error:function(){
				toastr.warning("出错啦！");
			}
		});
		
	}else{
		toastr.warning("请先选中一个部门再授权");
	}
	
	
	
	
}

//某部门可卖产品的数据
function getDeptProductPermission(deptid){
	var prodenable;
	$.ajax({
		type:"POST",
		url:"dept/getDeptProductPermission.do",
		dataType:"json",
		async:false,
		data:{"deptid":deptid},
		success:function(data){
			if(data == null || data == "null"){
				prodenable = "";
			}else{
				var json = JSON.parse(data);
				prodenable = json.prodenable;
			}
		},
		error:function(){
			toastr.warning("出错啦");
		}
	});
	return prodenable;
}

//为部门新增授权产品
function addOrUpdProdPermission(deptid){
	var checkboxs = $("#allViableProduct input[type='checkbox']:checked");
	var prodenable = "";
	$.each(checkboxs,function(i,o){
		prodenable += $(this).val() + "|";
	})
	$.ajax({
		type:"POST",
		url:"dept/addOrUpdProdPermission.do",
		dataType:"json",
		async:false,
		data:{"deptid":deptid,"prodenable":prodenable},
		success:function(data){
			if(data>0){
				toastr.success("授权成功!");
				$("#deptAuthorizeModal").modal("hide");
			}
		},
		error:function(){
			toastr.warning("出错啦");
		}
	});
}

//获得部门的json数据
function getAllJsonDeptData(){
	$.ajax({
		type:"POST",
		url:"dept/getAllDept.do",
		dataType:"json",
		async:false,
		success:function(data){
			//画出部门树
			$("#treeview12").treeview(
				{
					color:"#428bca",
					data:fn(JSON.parse(data),0),
					showTags:['available'],
					onNodeSelected:function(event,data){
				     	//当内容被选中时发生事件
						$(".del").removeAttr("disabled");//将按钮可用
			     		chooseDepartment = data.href + ":" + data.text;//或者:chooseDepartment = data.href;
			     		did = data.id
			     		if(data.nodes){//alert("有子节点")
			     			 $(".del").attr({"disabled":"disabled"});
			     		}
		  			},
					onNodeUnselected:function(event,data){
						//当内容没被选中时发生事件
						//console.log(data);
						//console.log(data.text);
						chooseDepartment = "";//或者:chooseDepartment = data.href;
						did = "";
					}
				});
       
		},
		error:function(){
			toastr.warning("出错啦");
		}
	});
}


//递归json数据 (根据list和上级id)
function fn(data, pid) {
    var result = [], temp;
   
    for (var i = 0; i < data.length; i++) {
        if (data[i].topdeptid == pid) {
        	var status;
        	if(data[i].status == 1){
        		status = "<a onclick='updDeptStatus(\""+data[i].topdeptid+"\",\""+data[i].status+"\")'>去禁用</a>";
        	}else{
        		status = "<a>去启用</a>";
        	}
            var obj = {"text": data[i].deptname,"href": data[i].deptid,"tags":[status],"id":data[i].id};
            temp = fn(data, data[i].deptid);
            if (temp.length > 0) {
                obj.nodes = temp;
            }
            result.push(obj);
        }
    }
    return result;
} 

//部门下拉框
function deptDataSelectData(deptid){
	$.ajax({
		type:"POST",
		url:"dept/getAllDept.do",
		dataType:"json",
		async:false,
		success:function(data){
			var html = "<option value=''>无</option>";
			var json = JSON.parse(data);
			$.each(json,function(i,o){
				if(deptid == '0'){
					html = "<option value=''>无</option>";
					return false;
				}
				if(o.deptid == deptid){
					html = "<option value='"+o.deptid+"'>"+o.deptname+"</option>";
					return false;
				}
				html += "<option value='"+o.deptid+"'>"+o.deptname+"</option>";
				
			});
			$("#addDeptModal select[name='topdeptid']").html(html);
		}
	});
}

//部门负责人下拉框
function deptUser(defaults){
	$.ajax({
		type:"POST",
		url:"SysUser/deptUserData.do",
		dataType:"json",
		async:false,
		success:function(data){
			//alert(data);
			var json = JSON.parse(data);
			var array = [];
			$.each(json,function(i,o){
				//alert(o.username);
				var deptuser = {"id":"","word":"","description":""};
				deptuser.id = o.loginname;
				deptuser.word = o.username;
				deptuser.description = o.userid;
				array.push(deptuser);
			});
			var testdataBsSuggest=$("#deptUser_data").bsSuggest(
					{
					indexId:2,
					indexKey:1,
					data:{
						"value":array,
						"defaults":defaults}
					});
			
		},error:function(){
			toastr.warning("出错啦");
		}
	});
}

//修改部门展示模态框
function updDeptModalShow(){
	if(chooseDepartment.length>0){
		var deptid = chooseDepartment.substring(0,chooseDepartment.indexOf(":"));
		var json ;
		$.ajax({
			type:"POST",
			url:"dept/getDeptDataById.do",
			dataType:"json",
			data:{"deptid":deptid},
			async:false,
			success:function(data){
				json = JSON.parse(data);
			},error:function(){
				toastr.warning("出错啦");
			}
		});
		deptDataSelectData(json[0].topdeptid);
		$("#addDeptModal input[name='deptname']").val(json[0].deptname);
		getSmallMaxIdOfBigId("12","#addDeptForm select[name='depttype']",json[0].depttype);//查看部门类型
		getSmallMaxIdOfBigId("13","#addDeptForm select[name='performance']",json[0].performance);//查看业务核算类型
		
		$("#addDeptModal input[name='username']").val(json[0].userid);
		deptUser(json[0].userid);//部门负责人下拉框
		if(json[0].status == "0"){
			$("#addDeptModal input[name='status'][value='0']").prop("checked",true);
		}else{
			$("#addDeptModal input[name='status'][value='1']").prop("checked",true);
		}
		$("#addDeptModal .btn-primary").attr("onclick","updDept()");
		$("#addDeptModal .modal-title").html("修改部门信息");
		$("#addDeptForm input[name='id']").remove();
		$("#addDeptForm").append("<input type='hidden' name='id' value='"+json[0].id+"'/>");
		$("#addDeptModal").modal("show");
	
	}else{
		toastr.warning("请先选中一个部门再修改");
	}
}

//修改部门信息
function updDept(){
	$.ajax({
		type:"POST",
		url:"dept/updDept.do?deptUser_data="+$("#deptUser_data").attr("data-id"),
		dataType:"json",
		data:$("#addDeptForm").serialize(),
		async:false,
		success:function(data){
			toastr.success("修改成功");
			getAllJsonDeptData();
			$("#addDeptModal").modal("hide");
		},error:function(){
			toastr.warning("修改失败");
		}
	});
}

//删除部门数据
function delDeptData(){
	 swal({
	        title: "您确定要删除这条信息吗",
	        text: "删除后将无法恢复，请谨慎操作！",
	        type: "warning",
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "删除",
	        closeOnConfirm: false,
	        showCancelButton: true,
	        cancelButtonText: "取消"
	    }, function () {
	    	$.ajax({
				type:"POST",
				url:"dept/delDetpById.do",
				dataType:"json",
				async:false,
				data:{"id":did},
				success:function(data){
					if(data>0){
						 swal("删除成功！", "您已经永久删除了这条信息。", "success");
					}
					getAllJsonDeptData();
					
				},error:function(){
					toastr.warning("删除失败！请先移除该部门下的所有员工数据");
				}
			});
	       
	    });
}

