/**
 * 文件名: basecode.js
 * 描述:与基础代码数据相关的展示和操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 & miemie
 * 创建时间：2017-03-31 
 */
$(document).ready(function(){
	getALLBigTypeData();//获得基础代码中所有的大类
	getSmall("01");//cq
});

//查看与大类对应的小类数据
function getSmall(bigid,bigname){
		$.ajax({//根据大类id查小类
		type:"POST",
		url:"basecode/getSmallMaxIdOfBigId.do",
		dataType:"json",
		data:{"bigid":bigid},
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var htmlcontext = "";				
			$.each(json,function(index, obj) {	
				htmlcontext += "<tr><td>" + obj.level2id + "</td>";		                 
				htmlcontext += "<td>" + obj.level2name + "</td>";
				htmlcontext += "<td><button type='button' class='btn btn-info btn-xs' onclick='updBasecodeInfo(\"" + obj.id + "\",\"" + obj.level2name + "\",\"" + obj.level1id + "\")'>修改</button> <button type='button' class='btn btn-danger btn-xs' onclick='delBasecodeInfo(\"" + obj.id + "\",\"" + obj.level2name + "\",\"" + obj.level1id + "\")'>删除</button></td></tr>";	
				document.getElementById("getbasevodeLevel").value = obj.level1name;
            });	
			if((typeof bigname) != "undefind"){
				$("#small").text(bigname);
			}
			$("#adddata input[name='level1id']").val(bigid);
			$("#basecodeLevel2Table").find("tbody").html(htmlcontext);  
		}
	});
}


//修改基础代码信息
function updBasecodeInfo(id,name,bigid){
	$("#myModal .btn-primary").attr("onclick","updSmallInfo(\""+id+"\",\""+bigid+"\")");
	$("#myModal").modal("show");
	$("#adddata input[name='level2name']").val(name);
}

//更改小类信息
function updSmallInfo(id,bigid){
	var newname = $("#adddata input[name='level2name']").val();
	if(newname == '' || newname== null || newname.length == 0 || (typeof newname) == "undefind"){
		alert("请先填写小类名称");
		return false;
	}
	$.ajax({//根据大类id查小类
		type:"POST",
		url:"basecode/updOneBasecodeData.do",
		dataType:"json",
		data:{"id":id,"newname":newname},
		async:false,
		success:function(data){
			if(data>0){
				alert("修改成功");
				$("#myModal").modal("hide");
				getSmall(bigid);
			}else{
				alert("修改失败！");
			}
		}
	});
}
//删除小类中基础代码信息
function delBasecodeInfo(id,name,bigid){
	swal({
        title: "确定要删除   [ " + name + " ] 这条数据吗？",
        text: "确认后不能更改，请谨慎操作",
        type: "warning",
        showCancelButton: true,
        cancelButtonText:"取消",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        closeOnConfirm: false
    }, function () {
    	//新增退费数据
    	$.ajax({//根据大类id查小类
			type:"POST",
			url:"basecode/delOneBasecodeData.do",
			dataType:"json",
			data:{"id":id},
			async:false,
			success:function(data){
				if(data>0){
					swal("操作成功！", "该小类已经删除。", "success");
					$("#basecodeLevel2Table").html(getSmall(bigid))
				}else{
					alert("删除失败！");
				}
			}
		});
    });
}

//新增基础代码
function ADDbasecode(){
//	return false;
	//如果新增小类
	$.ajax({
		type:"POST",
		url:"basecode/addBasecodeData.do",
		data:$("#adddata").serialize(),
		async:false,
		dataType:"text",
		success:function(data){
			alert("新增成功");
			getSmall($("#adddata input[name='level1id']").val());
			$("#myModal").modal("hide");
		},
		error:function(){
			alert("出错了，新增基础代码失败");
		}
	})
}


//获得基础代码的所有大类
function getALLBigTypeData(){
	//var getbasevodeLevel1=document.getElementById("getbasevodeLevel1").value;
	$.ajax({
		type:"POST",
		url:"basecode/getALLBigTypeData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			//alert(json.length);
			var htmlcontext = "";
			 $.each(json,function(index, obj) {
				 htmlcontext += "<tr><td><a data-toggle='tab' class='client-link' >"+obj.level1name+"</a></td><td style='float: right;'><button type='button' id="+obj.level1id+" class='btn btn btn-primary' onclick='getSmall(\"" + obj.level1id + "\",\"" + obj.level1name + "\")'>查看小类</button></td></tr>"	 
             });
		
			 $("#addBasecodeForm").find("tbody").html(htmlcontext);
		},error:function(){
			alert("数据加载错误");
		}
	});
}


//判断该小类是否存在
function judgeExist(){
	var level1id;
	if(typeof($("#addBasecodeForm select[name='level1id']").attr("disabled"))=="undefined"){//设为可用
		level1id = $("#addBasecodeForm select[name='level1id']").val();
	}else{//设为不可以
		level1id = "";
	}
	$.ajax({
		type:"POST",
		url:"basecode/judgeExist.do",
		dataType:"json",
		async:false,
		data:{
			"level1id":level1id,
			"level2name":$("#addBasecodeForm input[name='level2name']").val()
		},
		success:function(data){
			if(JSON.parse(data).length != 0){
				$("#addBasecodeForm input[name='level2name']").attr("placeholder","不能输入已有数据");
				$("#addBasecodeForm input[name='level2name']").val("");				
			}
			
		}
	});
}
//判断该大类是否存在
function judgeExistMax(){
	$.ajax({
		type:"POST",
		url:"basecode/judgeExistMax.do",
		dataType:"json",
		async:false,
		data:{"level1name":$("#addBasecodeForm input[name='level1name']").val()},
		success:function(data){
			if(JSON.parse(data).length != 0){
				$("#addBasecodeForm input[name='level1name']").attr("placeholder","不能输入已有数据");
				$("#addBasecodeForm input[name='level1name']").val("");			
			}
		}
	});
}

//根据大类id获得所有小类
function getSmallMaxIdOfBigId(bigid,selectStr){
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
                 $(selectStr).append(
                         "<option value="+obj.level2id+">" +obj.level2name+ "</option>");  
             });		
		},error:function(){
			alert("数据加载错误");
		}
	});
}

//更改某类的状态
function updBasecodeStatus(id,status,bigid){
	if(confirm("确定要更改这数据的状态吗？")){
		$.ajax({
			type:"POST",
			url:"basecode/updBasecodeStatus.do",
			dataType:"json",
			async:false,
			data:{"id":id,"status":status},
			success:function(data){
				if(data>0){
					$("#basecodeLevel1").html(getSmall(bigid));//及时跟新修改后的状态
				}
			},error:function(){
				alert("数据加载错误");
			}
		});
	}
	
}

//cq
function upd(name,id){
	document.getElementById("levname").value=id;
	document.getElementById("newname").value=name;
	$('#myModaltwo').modal('show');
}

//新增小类信息
function addLevel2(){
	$("#myModal .btn-primary").attr("onclick","ADDbasecode()");
	$("#myModal").modal("show");
}
