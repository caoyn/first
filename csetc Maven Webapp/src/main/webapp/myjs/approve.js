/**
 * 文件名: approve.js
 * 描述:审批相关的操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-13
 */
$(document).ready(function(){
	fillTableData();
	
	$("body").on("click",".delSubNode",function(){//删除某步骤
		$(this).parent().remove(); //移除审批子节点
		wizard.steps("remove",wizard.steps("getCurrentIndex"));//删除指定步骤
		wizard.steps("previous");//跳到上一步
	});
	
});

var wizard;

//新增审批信息展示
function addApproveShow(){
	/*var approveForm = '<form id="addApproveForm">';
		approveForm += '<label>审批流名称：</label><input type="text" name="flowname"><br/>';
		approveForm += '<label>审批流类型：</label><select name="flowtype"></select><br/>';
		approveForm += '<label>版本：</label><input type="text" name="flowversion"><br/>';
		approveForm += '<label>状态：</label><input type="radio" name="status" value="1" checked="checked"><label>启用</label><input type="radio" name="status" value="0"><label>禁用</label><br/>';
		approveForm += '<input type="button" value="确定" onclick="addApprove()"></form>';
	$("#addApprove").html(approveForm);//将审批表单放入页面
*/	
	getSmallMaxIdOfBigId("15","#approveForm select[name='flowtype']");//为下拉框填值
	$("#approveForm input[name=status][value='1']").prop("checked",true);
	$("#approveModal .btn-primary").attr("onclick","addApprove()");
	$("#approveModal").modal("show");
}

//新增审批信息
function addApprove(){
	$.ajax({
		type:"POST",
		url:"../approve/addApproveData.do",
		dataType:"json",
		async:false,
		data:$("#approveForm").serialize(),
		success:function(data){
			if(data>0){
				toastr.success("新增成功");
				$('#allApproveData').bootstrapTable("load",getAllApproveData());
				$("#approveModal").modal("hide");
			}
		},error:function(){
			toastr.warning('出错啦！');
		}
	});
}

//查看审批信息
function getAllApproveData(){
	var json ;
	$.ajax({
		type:"POST",
		url:"../approve/getAllApproveData.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json,function(i,o){
				o.operate = '<span class="small col-sm-3 text-center"><button type="button" class="btn btn-xs btn-info" onclick="editApproveDataShow('+o.approveid+')"><i class="fa fa-pencil"></i>修改</button></span>'
						  + '<span class="small col-sm-3 text-center"><button type="button" class="btn btn-xs btn-danger" onclick="delApproveDataById('+o.approveid+')"><i class="fa fa-eraser"></i>删除</button></span>';
			});
			if(json.length == 0){
				toastr.success("没有数据！");
				$("#approveTable").html("");
				return false;
			}
			/*var approveTable = '<tr><td>编号</td><td>审批名称</td><td>类型</td><td>版本</td><td>创建人</td><td>创建时间</td><td>状态</td><td>详情</td><td>操作</td></tr>';
			$.each(json,function(i,o){
				approveTable += '<tr><td>'+o.approveid+'</td>';
				approveTable += '<td>'+ o.flowname +'</td>';
				approveTable += '<td>'+ o.flowtype +'</td>';
				approveTable += '<td>'+ o.flowversion +'</td>';
				approveTable += '<td>'+ o.userid +'</td>';
				approveTable += '<td>'+ o.createtime +'</td>';
				approveTable += '<td>'+ o.status +'</td>';
				approveTable += '<td><a onclick="addApproveDetailShow('+o.approveid+')">新增/查看详情</a></td>';
				approveTable += '<td><a onclick="editApproveDataShow('+o.approveid+')">编辑 </a> <a onclick="delApproveDataById('+o.approveid+')">删除 </a></td></tr>';
			});
			$("#approveTable").html(approveTable);*/
		},error:function(){
			toastr.warning('出错啦！');
		}
	});
	return json;
}


//新增审批详情展示
function addApproveDetailShow(approveid){
	//alert("审批流详情");
	$("#addApproveDetail").html("");//将审批详情清空防止数据展示错误
	//查看是否有审批详情有则显示
	$.ajax({
		type:"POST",
		url:"../approve/getApproveDetailByAid.do",
		dataType:"json",
		async:false,
		data:{"approveid":approveid},
		success:function(data){
			var json = JSON.parse(data);
			if(json.length>0){
				$("#addApproveDetail").html("<table id='approveDetailTable'></table>");
				$.each(json,function(i,o){
					var a = '';
					o.count = (i+1);
					if(o.nodetype == 1){
						a = ' <a onclick="delApproveDetailSubNode('+o.id+','+o.approveid+')">删除</a>';
					}
					o.operate = /*'<a onclick="editApproveSubNodeDataShow('+o.id+')">编辑</a> ' + */ a ;
				});
				approveDetailTableData(json);
				
				/*var tableHtml = '<table  class="table table-striped"><tr><td>编号</td><td>节点名称</td><td>节点类型</td><td>处理角色</td><td>操作</td></tr>';
				$.each(json,function(i,o){
					var nodetype = "";
					var delHtml = ""
					tableHtml += '<tr><td>' + (i+1) + '</td>';
					tableHtml += '<td>' + o.nodename + '</td>';
					if(o.nodetype == 0){
						nodetype = "开始节点";
					}else if(o.nodetype == 1){
						nodetype = "中间节点";
						delHtml = ' <a onclick="delApproveDetailSubNode('+o.id+','+o.approveid+')">删除</a>';
					}else if(o.nodetype == 2){
						nodetype = "结束节点";
					}
					tableHtml += '<td>' + nodetype + '</td>';
					tableHtml += '<td>' + o.roleid + '</td>';
				
					tableHtml += '<td><a onclick="editApproveSubNodeDataShow('+o.id+')">编辑</a> ' + delHtml + ' </td><tr>';
				});
				tableHtml += '</table>';*/
				
				$("#approveDetailModal .modal-title").html("查看审批详情");
				$("#approveDetailModal .modal-footer").show();
				//$("#addApproveDetail").html(tableHtml);
			}else{
				//没有则新增
				/*var approveForm = '<form id="addApproveDetailForm"><input type="hidden" value="'+approveid+'" name="approveid"/>';
				approveForm += subNodeHtml(0,"开始节点");
				approveForm += subNodeHtml(2,"结束节点");
				approveForm += '<a onclick="addApproveDetailCenterNode()">添加中间节点 +</a> <div id="centerNode"></div>';
				approveForm += '<input type="button" value="确定" onclick="addApproveDetail()"></form>';
				$("#addApproveDetail").html(approveForm);
				getAllRoleData(".approveSubNode select[name='roleid']");//获得所有角色的下拉数据
*/			
				var step = '<input type="hidden" value="'+approveid+'" name="approveid"/><h3>开始节点</h3><section>';
				step += subNodeHtml(0,"开始节点");
				step += '<a onclick="addApproveDetailCenterNode()">添加中间节点 +</a> <div id="centerNode"></div></section>';
				step +='<h3>结束节点</h3><section>';
				step += subNodeHtml(2,"结束节点");
				step +='<section>';

			
			
				$("#addApproveDetail").html(step);
				getAllRoleData(".approveSubNode select[name='roleid']");//获得所有角色的下拉数据
				wizard = $("#addApproveDetail").steps({
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
				    onFinished : function(event,currentIndex){
				    	addApproveDetail();
				    }
				});	
				
				$("#approveDetailModal .modal-title").html("新增审批详情");
				$("#approveDetailModal .modal-footer").hide();
			}
			
			$("#approveDetailModal").modal("show");
		},
		error:function(){
			toastr.warning('出错啦！');
		}
	})
	
	
	
}

//新增审批详情中间节点
function addApproveDetailCenterNode(){
    //wizard.steps("getCurrentIndex");//获得当前步骤的索引 
    wizard.steps("insert", wizard.steps("getCurrentIndex")+1,{
        title: "中间节点", 
        content: subNodeHtml(1,"中间节点")
    });//在指定位置插入步骤
    
	getAllRoleData(".approveSubNode select[name='roleid']");//获得所有的角色下拉数据
}

//子节点的html
function subNodeHtml(flowtype,type){
	var nodehtml = '';

	nodehtml +='<div class="approveSubNode">';
	nodehtml +='<div class="form-group"><label class="col-sm-4 control-label">审批流节点名称：</label><div class="col-sm-6"><input type="text" name="nodename" class="form-control input-sm"></div></div>';
	nodehtml +='<div class="form-group"><label class="col-sm-4 control-label">节点类型：</label><div class="col-sm-6"><input type="hidden" name="nodetype" value="'+ flowtype +'">'+type+'</div></div>';
	nodehtml +='<div class="form-group"><label class="col-sm-4 control-label">参与角色：</label><div class="col-sm-6"><select name="roleid"></select></div></div>';
	if(flowtype == 1){
		nodehtml += '<a class="delSubNode">删除此子节点 (步骤)</a>';
	}
	nodehtml += "</div>";
	return nodehtml;
}

//新增子节点详情
function addApproveDetail(){
	//alert($("#addApproveDetailForm").serialize());
	var approve = [];
	var approveProcess1 = "";
	var approveProcess2 = "";
	var approveProcess3 = "";
	var flag = true;
	for(var i = 0; i < $(".approveSubNode").length; i++){ 
		var json = {"approveid":"","nodename":"","nodetype":"","roleid":"","sort":""}//子节点所需数据
		json.approveid = $("#approveDetailForm").find("input[name=approveid]").val();
		json.nodename = $(".approveSubNode").eq(i).find("input[name=nodename]").val();
		json.nodetype = $(".approveSubNode").eq(i).find("input[name=nodetype]").val();
		json.roleid = $(".approveSubNode").eq(i).find("select[name=roleid]").val();
		if(json.nodetype == 1){//中间节点
			json.sort = i;//为中间节点的时候才增加排序序号
			approveProcess2 += json.nodename + ":" + $(".approveSubNode").eq(i).find("select[name=roleid]").find("option:selected").text() + "->";
		}else if(json.nodetype == 0){//开始节点
			approveProcess1 = json.nodename + ":" + $(".approveSubNode").eq(i).find("select[name=roleid]").find("option:selected").text()  + "->";
			json.sort = 0;//否则排序序号为1
		}else if(json.nodetype == 2){//结束节点
			approveProcess3 = json.nodename + ":" + $(".approveSubNode").eq(i).find("select[name=roleid]").find("option:selected").text()  ;
			json.sort = $(".approveSubNode").length - 1;//否则排序序号为节点长度
		}
		approve.push(json);
	}
	$.each(approve,function(i,o){//防止提交空数据
		if(o.nodename == '' || o.nodename == 'null' ||o.nodename.length == 0 ){
			toastr.warning('不能有空的节点名称！');
			//wizard.steps(0); --error //跳到第一步
			flag = false;
			return false;
		}else if(o.roleid == "请选择" || o.roleid == "" || o.roleid == "null" || o.roleid.length == 0){
			toastr.warning('请选择参与角色！');
			flag = false;
			return false;
		}
	})
	
	if((approveProcess1+approveProcess2+approveProcess3).length<=0 || !flag){//防止二次提交
		return false;
	}
	if(confirm("该审批流程为:"+approveProcess1+approveProcess2+approveProcess3+" 确定吗？")){
		//新增审批流详情
		$.ajax({
			type:"POST",
			url:"../approve/addApproveDetailData.do",
			data:{"approveid":$("#approveDetailForm input[name='approveid']").val(),"subApprove":window.JSON.stringify(approve)},
			dataType:"json",
			success:function(data){
				if(data>0){
					toastr.success("新增审批详情数据成功");
					$("#addApproveDetail").html("");
					$("#approveDetailModal").modal("hide");
				}
			},
			error:function(){
				toastr.warning('出错啦！');
			}
		});
	}
}

//删除审批流程
function delApproveDataById(approveid){
	swal({
        title: "您确定要删除这条信息吗",
        text: "删除后将无法恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        cancelButtonText:"取消",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除",
        closeOnConfirm: false
    }, function () {
    	$.ajax({
			type:"POST",
			url:"../approve/delApproveDataById.do",
			data:{"approveid":approveid},
			dataType:"json",
			success:function(data){
				if(data>0){
					swal("删除成功！", "您已经永久删除了这条信息。", "success");
					$('#allApproveData').bootstrapTable("load",getAllApproveData());
				}
			},
			error:function(){
				toastr.warning('出错啦！');
			}
		})
    });
}

//删除审批流程中的子节点
function delApproveDetailSubNode(id,approveid){
	//delApproveSubNode
	swal({
        title: "您确定要删除这条信息吗",
        text: "删除后将无法恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "删除",
        cancelButtonText: "取消",
        closeOnConfirm: false
    }, function () {
    	$.ajax({
			type:"POST",
			url:"../approve/delApproveSubNode.do",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data>0){
					//toastr.success("删除数据成功");
					swal("删除成功！", "您已经永久删除了这条信息。", "success");
					addApproveDetailShow(approveid);
				}
			},
			error:function(){
				toastr.warning('出错啦！');
			}
		})
       
    });
}

//编辑审批流展示
function editApproveDataShow(approveid){
	//编辑
	$.ajax({
		type:"POST",
		url:"../approve/getOneAproveData.do",
		data:{"approveid":approveid},
		dataType:"json",
		success:function(data){
			var json = JSON.parse(data);
			/*var approveForm = '<form id="updApproveForm"><input type="hidden" name="approveid" value="'+approveid+'"/>';
			approveForm += '<label>审批流名称：</label><input type="text" name="flowname" value="'+json.flowname+'"><br/>';
			approveForm += '<label>审批流类型：</label><select name="flowtype"></select><br/>';
			approveForm += '<label>版本：</label><input type="text" name="flowversion" value="'+json.flowversion+'"><br/>';
			if(json.status == 1){
				approveForm += '<label>状态：</label><input type="radio" name="status" value="1" checked="checked"><label>启用</label><input type="radio" name="status" value="0"><label>禁用</label><br/>';
			}else{
				approveForm += '<label>状态：</label><input type="radio" name="status" value="1"><label>启用</label><input type="radio" name="status" value="0"  checked="checked"><label>禁用</label><br/>';
			}
			approveForm += '<input type="button" value="确定" onclick="updApproveData()"></form>';
			$("#updApprove").html(approveForm);//将审批表单放入页面
*/			
			$("#approveForm input[name='flowname']").val(json.flowname);
			getSmallMaxIdOfBigId("15","#approveForm select[name='flowtype']",json.flowtype);//为下拉框填值
			$("#approveForm input[name='flowversion']").val(json.flowversion);
			if(json.status == "1"){
				$("#approveForm input[name=status][value='1']").prop("checked",true);
			}else{
				$("#approveForm input[name=status][value='0']").prop("checked",true);
			}
			$("#approveModal .modal-title").html("修改审批流程");
			$("#approveModal .btn-primary").attr("onclick","updApproveData()");
			$("#approveForm input[name='approveid']").remove();//移除后追加
			$("#approveForm").append('<input type="hidden" name="approveid" value="'+approveid+'"/>');
			$("#approveModal").modal("show");
			
		},
		error:function(){
			toastr.warning('出错啦！');
		}
	})
	
	
	 
	}
//更新审批流
function updApproveData(){
	$.ajax({
		type:"POST",
		url:"../approve/updApproveData.do",
		data:$("#approveForm").serialize(),
		dataType:"json",
		success:function(data){
			if(data>0){
				toastr.success("更新数据成功");
				$("#approveModal").modal("hide");
				$('#allApproveData').bootstrapTable("load",getAllApproveData());
				//addApproveDetailShow(approveid);
			}
		},
		error:function(){
			toastr.warning('出错啦！');
		}
	})
}
//编辑审批流节点展示
function editApproveSubNodeDataShow(id){
	$.ajax({
		type:"POST",
		url:"../approve/getOneSubNodeData.do",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			var json = JSON.parse(data);
			var approveForm = '<form id="updApproveDetailForm"><input type="hidden" value="'+json.approveid+'" name="approveid"/>';
			approveForm += '<input type="hidden" value="'+json.id+'" name="id"/>';
			approveForm +='<label>审批流节点名称：</label><input type="text" name="nodename" value="'+json.nodename+'"><br/>';
			approveForm +='<label>参与角色：</label><select name="roleid"></select>';
			approveForm += '<input type="button" value="确定" onclick="updApproveSubNodeData('+json.approveid+')"></form>';
			$("#addApproveDetail").html(approveForm);
			getAllRoleData("#updApproveDetailForm select[name='roleid']",json.roleid);//获得所有角色的下拉数据
		},
		error:function(){
			toastr.warning('出错啦！');
		}
	})
}
//更新审批流节点
function updApproveSubNodeData(approveid){
	$.ajax({
		type:"POST",
		url:"../approve/updApproveSubNodeData.do",
		data:$("#updApproveDetailForm").serialize(),
		dataType:"json",
		success:function(data){
			if(data>0){
				toastr.success("更新数据成功");
				addApproveDetailShow(approveid);
			}
		},
		error:function(){
			toastr.warning('出错啦！');
		}
	})
}


//填充表格数据
//查看表格数据
function fillTableData(){
	var json = getAllApproveData();
	$('#allApproveData').bootstrapTable({
	    data:json,
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#approvetoolbar",
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
        height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "approveid",                     //每一行的唯一标识，一般为主键列
        idField:"approveid",
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
	    columns: [{
	        field: 'approveid',
	        title: '编号',
	        sortable:true
	    }, {
	    	field: 'flowname',
	    	title: '审批名称',
	    	sortable:true
	    }, {
	    	field: 'flowtype',
	    	title: '类型',
	    	sortable:true
	    }, {
	    	field: 'flowversion',
	    	title: '版本',
	    	sortable:true
	    }, {
	    	field: 'userid',
	    	title: '创建人',
	    	sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '创建时间',
	    	sortable:true
	    }, {
	    	field: 'status',
	    	title: '状态',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "禁用";
	    		}else if(value == 1){
	    			return "启用";
	    		}else{
	    			return "未设定";
	    		}
	    	},
	    	sortable:true
	    }, {
			field: 'operate',
			title: '操作'
		}],
		onDblClickRow:function(row,ele,field){//双击行
			addApproveDetailShow(row.approveid);
		}
	});
}


//审批详情数据
function approveDetailTableData(json){
	$('#approveDetailTable').bootstrapTable({
	    data:json,
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        idField:"id",
        rowStyle: function (row, index) {
        	//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
	        var strclass = "";
	        if (row.nodetype == "2") {
	            strclass = 'success';//还有一个active
	        }
	        else if (row.nodetype == "0") {
	            strclass = 'danger';
	        }
	        else {
	        	strclass = 'info';
	        }
	        return { classes: strclass }
	    },
	    onEditableSave: function (field, row, oldValue, $el) {
	    	if(row.nodename == '' || row.nodename.length == 0 ){
	    		toastr.warning('节点名称不能为空！');
	    		return false;
	    	}
            $.ajax({
                type: "post",
                url: "../approve/updApproveSubNodeData.do",
                data: row,
                dataType:"json",
                success: function (data) {
                    if (data>0) {
                    	toastr.success("编辑成功");
                    }
                },
                error: function () {
                	toastr.warning('出错啦！');
                }
            });
        },
	    columns: [{
	        field: 'count',
	        title: '编号',
	    }, {
	    	field: 'nodename',
	    	title: '节点名称',
	    	editable:true
	    }, {
	    	field: 'nodetype',
	    	title: '节点类型',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "开始节点";
	    		}else if(value == 1){
	    			return "中间节点";
	    		}else{
	    			return "结束节点";
	    		}
	    	},
	    }, {
	    	field: 'roleid',
	    	title: '处理角色',
	    	sortable:true,
	    	editable: {
                type: 'select',
                title: '处理角色',
                source: function () {
                    var result = [];
                    $.ajax({//查看所有角色
                		type:"POST",
                		url:"../role/getAllRoleData.do",
                		dataType:"json",
                		async:false,
                		success:function(data){
                			var json = JSON.parse(data);
                			console.log(json)
                			$.each(json,function(index, obj) {
                				result.push({ value: obj.roleid, text: obj.rolename });
                			 })
                		}
                    });
                    return result;
                }
            }
	    }, {
			field: 'operate',
			title: '操作'
		}]
	});
}