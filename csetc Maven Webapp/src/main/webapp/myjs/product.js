/**
 * 文件名: product.js
 * 描述:与产品数据相关的展示和操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-04-10
 */
$(document).ready(function(){
	fillTableData();
	
	//这个监听方法是无效的
	/*$(".updsubproduct").click(function() {
		alert();
	    // $(selector)通过选择器表示要删除的元素，remove()函数用以删除元素
	});*/
	//应该使用(处理jquery append 加入的页面元素,普通绑定无效的办法)
	$("body").on("click",".updsubproduct",function(){
		$(this).parent().remove(); //移除子产品
	});
	
	//为输入框绑定监听方法
	$("body").on("input propertychange","input[name='prodid'],input[name='prodname'],input[name^=subprodid]",function(){
		var obj = $(this);
		$.ajax({
			type:"POST",
			url:"../prodbase/judgeExistProduct.do",
			dataType:"json",
			async:false,
			data:{"str":obj.val()},
			success:function(data){
				if((JSON.parse(data)).length>0){
					obj.val("");
					toastr.warning('该产品已经存在');
				}
			}
			
		})
		
	})
});



//新增产品基础信息
function addProductBaseInfo(){
	
	if(!$("#product").valid()){
		return false;
	}
	var products = [];
	for(var i = 0; i < $(".subproduct").length; i++){ 
		var json = {"subprodid":"","subprodname":"","subprodprice":"","subprodsaleprice":"","subprodminprice":"","discountenable":""}
		json.subprodid = $(".subproduct").eq(i).find("input[name^=subprodid]").val();
		json.subprodname = $(".subproduct").eq(i).find("input[name^=subprodname]").val();
		json.subprodprice = $(".subproduct").eq(i).find("input[name^=subprodprice]").val();
		json.subprodsaleprice = $(".subproduct").eq(i).find("input[name^=subprodsaleprice]").val();
		json.subprodminprice = $(".subproduct").eq(i).find("input[name^=subprodminprice]").val();
		json.discountenable = $(".subproduct").eq(i).find("input[name^=discountenable]:checked").val();
		products.push(json);
	}
	
	$.ajax({
		type:"POST",
		url:"../prodbase/addProductBaseInfo.do?"+$("#product").serialize(),
		dataType:"json",
		async:false,
		data:{"products":window.JSON.stringify(products)},
		success:function(data){
			if(data>0){
				toastr.success("新增产品数据成功");
				 $("#allProductData").bootstrapTable("load",loadTableData());
				$("#myModal").modal("hide");
			}
		},error:function(){
			toastr.warning('数据加载失败');
		}
	});
}

//查看所有产品数据
function getAllProductData(){
	$.ajax({
		type:"POST",
		url:"../prodbase/getAllProductData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var prodataHtml = "<table border='1'><tr><td>产品编号</td><td>产品名称</td><td>产品类型</td><td>产品版本</td><td>创建时间</td><td>状态</td>" +
					"<td>产品成本价格</td><td>产品销售价格</td><td>产品最低销售价格</td><td>是否允许折扣</td><td>操作</td><tr>";
			$.each(json,function(i,obj){
				var proddetail =  obj.proddetail;
				$.each(proddetail,function(j,o){
					prodataHtml += "<tr><td>" + o.subprodid + "</td>";
					prodataHtml += "<td>" + o.subprodname + "</td>";
					prodataHtml += "<td>" + obj.prodtype + "</td>";
					prodataHtml += "<td>" + obj.prodver + "</td>";
					prodataHtml += "<td>" + obj.createtime + "</td>";
					prodataHtml += "<td>" + obj.status + "</td>";
					prodataHtml += "<td>" + o.subprodprice + "</td>";
					prodataHtml += "<td>" + o.subprodsaleprice + "</td>";
					prodataHtml += "<td>" + o.subprodminprice + "</td>";
					prodataHtml += "<td>" + o.discountenable + "</td>";
					prodataHtml += "<td><a onclick='updProductShow(\""+obj.prodid+"\")'>编辑</a> <a onclick='delProduct(\""+obj.id+"\",\"" + o.did + "\")'>删除</a></td></tr>";
					//alert(o.did);
				});
			});
			prodataHtml += "</table>";
			$("#allProductData").html(prodataHtml);
		},error:function(){
			toastr.warning('数据加载失败');
		}
	});
}


//新增子产品显示（jsp模态框中展示）
function addSubProductShow(){
	var count = new Date().getTime();//为控件名称生成后缀避免重复
	//1.子产品需要的数据
	var subhtml = '<div class="subproduct"><a title="移除子产品" class="updsubproduct"> - </a>';
	
		subhtml += '<div class="form-group" ><label class="col-sm-4 control-label">子产品名称：</label>'
		 	+ '<div class="col-sm-6"><div class="input-group"><input name="subprodname'+count+'" type="text" class="form-control subProdName_data" required="required">'
		 	+ '<div class="input-group-btn"><button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>'
		 	+ '<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul></div></div></div>'
	
		subhtml += '<div class="form-group"><label class="col-sm-4 control-label">子产品成本价格：</label>'+
				   '<div class="col-sm-6"><input name="subprodprice'+count+'" number="true" class="form-control input-sm" required="required"/></div></div>';
		
		subhtml += '<div class="form-group"><label class="col-sm-4 control-label">子产品销售价格：</label>'+
				   '<div class="col-sm-6"><input name="subprodsaleprice"'+count+'" number="true" class="form-control input-sm" required="required"/></div></div>';
		
		subhtml += '<div class="form-group"><label class="col-sm-4 control-label">子产品最低销售价格：</label>'+
				   '<div class="col-sm-6"><input name="subprodminprice"'+count+'" number="true" class="form-control input-sm" required="required"/></div></div>';
		
		subhtml += '<div class="form-group"><label class="col-sm-4 control-label">允许折扣：</label>'+
					'<div class="col-sm-6"><div class="radio"><label><input type="radio"  class="radio i-checks" name="discountenable'+count+'" value="0" checked="checked"/><i></i>否 &nbsp;</label>' +
					'<label><input type="radio" class="radio i-checks"  name="discountenable'+count+'" value="1"><i></i>是</label></div></div></div>';
	 
		subhtml += '<div class="hr-line-dashed"></div>';
	$("#subproducts").append(subhtml);	
	subsuggest();

}

//子产品搜索下拉框
function subsuggest(){
	//子产品数据
	var array = [];
	$.each($("#allProductData").bootstrapTable('getData'),function(i,o){
		//alert(o.username);
		var deptuser = {"id":"","word":"","description":""};
		deptuser.word = o.prodname;
		array.push(deptuser);
	});
	$(".subProdName_data").bsSuggest({
		indexId:1,
		indexKey:1,
		data:{
			"value":array,
		}
	});

}

//删除产品
function delProduct(id){
	swal({
        title: "确定要删除该产品吗？该产品的子产品也会被删除",
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
    		url:"../prodbase/delProdBase.do",
    		dataType:"json",
    		async:false,
    		data:{"prodid":id},
    		success:function(data){
    			if(data>0){
    				swal("删除成功！", "您已经永久删除了这条信息。", "success");
    			    $("#allProductData").bootstrapTable("load",loadTableData());
    			}else if(data == -1){
    				toastr.warning('含有子产品不能删除');
    			}
    		},
    		error:function(){
    			toastr.warning('数据加载错误');
    		}
    	});
    })
}

//编辑产品页面展示
function updProductShow(prodid){
	$.ajax({
		type:"POST",
//		url:"../prodbase/getProductByProductid.do",
		url:"../prodbase/getProdByPid.do",
		dataType:"json",
		async:false,
		data:{"prodid":prodid},
		success:function(data){
			var json = JSON.parse(data);
			console.log(json);
			$("#prodcutUpdModal input[name='prodid']").val(json.prodid);
			$("#prodcutUpdModal input[name='prodname']").val(json.prodname);
			getSmallMaxIdOfBigId("02","#prodcutUpdModal select[name='prodtype']",json.prodtype);
			if(json.status == 0){
				$("#prodcutUpdModal input[name=status][value='0']").prop("checked",true);
			}else{
				$("#prodcutUpdModal input[name=status][value='1']").prop("checked",true);
			}
			$("#prodcutUpdModal .see-subproduct").attr("onclick","seeSubProduct(\""+json.prodid+"\")");
			$("#prodcutUpdModal .add-subproduct").attr("onclick","addProductDetail(\""+json.prodid+"\")");
			$("#prodcutUpdModal").modal("show");	
			return false;
			/*if(json.length == 1)*/
			$.each(json,function(i,obj){
				var proddetail =  obj.proddetail;
				$.each(proddetail,function(j,o){
					if(o.prodid == o.subprodid){
						$("#prodcutUpdModal input[name='prodid']").val(obj.prodid);
						$("#prodcutUpdModal input[name='prodname']").val(obj.prodname);
						getSmallMaxIdOfBigId("02","#prodcutUpdModal select[name='prodtype']",obj.prodtype);
						$("#prodcutUpdModal input[name='prodver']").val(obj.prodver);
						if(obj.status == 0){
							$("#prodcutUpdModal input[name=status][value='0']").prop("checked",true);
						}else{
							$("#prodcutUpdModal input[name=status][value='1']").prop("checked",true);
						}
						$("#prodcutUpdModal .see-subproduct").attr("onclick","seeSubProduct(\""+obj.prodid+"\")");
						$("#prodcutUpdModal .add-subproduct").attr("onclick","addProductDetail(\""+obj.prodid+"\")");
						$("#prodcutUpdModal").modal("show");	
					}else{
						$("#prodcutDetailUpdModal input[name='prodid']").val(o.prodid);
//						$("#prodcutDetailUpdModal input[name='subprodid']").val(o.subprodid);
						$("#prodcutDetailUpdModal input[name='subprodname']").val(o.subprodname);
						$("#prodcutDetailUpdModal input[name='subprodprice']").val(o.subprodprice);
						$("#prodcutDetailUpdModal input[name='subprodsaleprice']").val(o.subprodsaleprice);
						$("#prodcutDetailUpdModal input[name='subprodminprice']").val(o.subprodminprice);
						if(o.discountenable == 0){
							$("#prodcutDetailUpdModal input[name='discountenable'][value='0']").prop("checked","checked");
						}else{
							$("#prodcutDetailUpdModal input[name='discountenable'][value='1']").prop("checked","checked");
						}
						$("#prodcutDetailUpdModal input[name='subprodid']").attr("readonly",true);
						$("#prodcutDetailUpdModal .modal-footer .btn-primary").attr("onclick","updProduct('producDetailtUpd','prodcutDetailUpdModal')");

						$("#prodcutDetailUpdModal").modal("show");	
					}
				});
			});
		}
	})
}
//子产品详情弹出框
function updSubProductShow(prodid){
	$.ajax({
		type:"POST",
//		url:"../prodbase/getProductByProductid.do",
		url:"../prodbase/getSubProdBySid.do",
		dataType:"json",
		async:false,
		data:{"subprodid":prodid},
		success:function(data){
			var json = JSON.parse(data);
			console.log(json);
			$("#prodcutDetailUpdModal input[name='subprodid']").val(json.subprodid);
			$("#prodcutDetailUpdModal input[name='subprodname']").val(json.subprodname);
			$("#prodcutDetailUpdModal input[name='subprodprice']").val(json.subprodprice);
			$("#prodcutDetailUpdModal input[name='subprodsaleprice']").val(json.subprodsaleprice);
			$("#prodcutDetailUpdModal input[name='subprodminprice']").val(json.subprodminprice);
			if(json.discountenable == 0){
				$("#prodcutDetailUpdModal input[name='discountenable'][value='0']").prop("checked","checked");
			}else{
				$("#prodcutDetailUpdModal input[name='discountenable'][value='1']").prop("checked","checked");
			}
			$("#prodcutDetailUpdModal .modal-footer .btn-primary").attr("onclick","updSubProduct()");
			
			$("#prodcutDetailUpdModal").modal("show");	
		}
	});
}

//修改子产品信息
function updSubProduct(){
	if(!$("#producDetailtUpd").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../prodbase/updSubProdBySid.do",
		dataType:"json",
		async:false,
		data:$("#producDetailtUpd").serialize(),
		success:function(data){
			if(data>0){
				toastr.success('修改成功');
				$("#prodcutDetailUpdModal").modal("hide");
				$("#allProductData").bootstrapTable("load",loadTableData());//刷新表格
			}
		},
		error:function(){
			toastr.warning('数据加载错误');
		}
	})

	
}

//修改产品信息
function updProduct(){
	if(!$("#productUpd").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
//		url:"../prodbase/updProdBase.do",
		url:"../prodbase/updProdByPid.do",
		dataType:"json",
		async:false,
		data:$("#productUpd").serialize(),
		success:function(data){
			if(data>0){
				toastr.success('修改成功');
				$("#prodcutUpdModal").modal("hide");
				$("#allProductData").bootstrapTable("load",loadTableData());//刷新表格
			}
		},
		error:function(){
			toastr.warning('数据加载错误');
		}
	})
}

//新增产品的显示
function addProductModelShow(){
	getSmallMaxIdOfBigId("02","#product select[name='prodtype']");
	
	$("#myModal").modal("show");
}

//查看表格数据
function fillTableData(){
	var json = loadTableData();
	$('#allProductData').bootstrapTable({
	    data:json,
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#productToolbar",
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
        height: 660,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "subprodid",                     //每一行的唯一标识，一般为主键列
        idField:"subprodid",
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
	    columns: [{
	        field: 'prodid',
	        title: '产品编号',
	        sortable:true
	    }, {
	    	field: 'prodname',
	    	title: '产品名称',
	    	sortable:true
	    }, {
	    	field: 'subprodname',
	    	title: '子产品名称',
	    	sortable:true
	    }, {
	    	field: 'subprodprice',
	    	title: '产品成本价格',
	    	sortable:true
	    }, {
	    	field: 'subprodsaleprice',
	    	title: '产品销售价格',
	    	sortable:true
	    }, {
	    	field: 'subprodminprice',
	    	title: '产品最低销售价格',
	    	sortable:true
	    }, {
	    	field: 'status',
	    	title: '是否禁用',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "禁用";
	    		}else if(value == 1){
	    			return "启用";
	    		}
	    		
	    	},
	    	sortable:true
	    }, {
	    	field: 'discountenable',
	    	title: '允许折扣',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "否";
	    		}else if(value == 1){
	    			return "是";
	    		}else{
	    			return "未设定";
	    		}
	    		
	    	},
	    	sortable:true
	    	
	    }, {
			field: 'operate',
			title: '操作'
		}]
	});
}


//查看产品详情
function qeyDetail(subprodid){
	$.ajax({
		type:"POST",
		url:"../prodbase/getProductBySubid.do",
		dataType:"json",
		async:false,
		data:{"subprodid":subprodid},
		success:function(data){
			var json = JSON.parse(data);
			var status = "";
			if(json.status == 1){
				status = "启用";
			}else{
				status = "禁用";
			}
			var msg = "产品编号："+ json.prodid +"<br/>";
				msg += "产品名称："+ json.prodname +"<br/>";
			 	msg += "创建时间："+ json.createtime +"<br/>";
			 	msg += "产品版本："+ json.prodver +"<br/>";
			 	msg += "产品状态："+ status +"<br/>";
			
			toastr.options = {
			  "closeButton": true, //是否显示关闭按钮
			  "debug": true,//是否使用debug模式
			  "progressBar": true,
			  "positionClass": "toast-top-right",//弹出窗的位置
			  "onclick": null,//点击时发生的事件
			  "showDuration": "400",//显示的动画时间
			  "hideDuration": "1000",//消失的动画时间
			  "timeOut": "7000",//展现时间
			  "extendedTimeOut": "1000",//加长展示时间
			  "showEasing": "swing",//显示时的动画缓冲方式
			  "hideEasing": "linear",//消失时的动画缓冲方式
			  "showMethod": "fadeIn",//显示时的动画方式
			  "hideMethod": "fadeOut"//消失时的动画方式
			}
  
            var $toast = toastr['success'](msg, '产品详情');  
		
			
			
		},
		error:function(){
			toastr.warning('数据加载错误');
		}
	})
}

//刷新表格数据
function loadTableData(){
	var json;
	$.ajax({
		type:"POST",
		url:"../prodbase/proddetail.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			console.log(json);
			$.each(json,function(i,o){
				//subprodname, subprodprice, subprodsaleprice, subprodminprice, discountenable
				if(o.proddetail.length == 0){
					return true;
				}
				o.subprodname = o.proddetail[0].subprodname;
				o.subprodprice = o.proddetail[0].subprodprice;
				o.subprodsaleprice = o.proddetail[0].subprodsaleprice;
				o.subprodminprice = o.proddetail[0].subprodminprice;
				o.discountenable = o.proddetail[0].discountenable;
				o.operate = '<span class="small text-center"><button type="button" class="btn btn-xs btn-info" onclick="updProductShow(\''+o.prodid+'\')"><i class="fa fa-pencil"></i>修改</button></span> '
//				  + '<span class="small text-center"><button type="button" class="btn btn-xs btn-warning" onclick="qeyDetail(\''+o.prodid+'\')"><i class="fa fa-book"></i>详情</button></span>'
				  + '<span class="small text-center"><button type="button" class="btn btn-xs btn-danger" onclick="delProduct(\''+o.prodid+'\')"><i class="fa fa-eraser"></i>删除</button></span>';
			});
		},
		error:function(){
			toastr.warning("出错啦");
		}
	});
	return json;
}

//在修改时新增子产品
function addProductDetail(prodid){
	$("#producDetailtUpd input[type=reset]").trigger("click");//重置表单
	$("#producDetailtUpd input[name='prodid']").val(prodid);
//	$("#prodcutDetailUpdModal input[name='subprodid']").removeAttr("readonly");
	$("#producDetailtUpd input[name=discountenable][value='0']").attr("checked",true);
	$("#producDetailtUpd input[name=discountenable][value='1']").attr("checked",false);
	$("#prodcutDetailUpdModal .modal-footer .btn-primary").attr("onclick","addSubProduct()");
	subsuggest();
	$("#prodcutDetailUpdModal").modal("show");
}

//查看子产品
function seeSubProduct(prodid) {
	$.ajax({
		type:"POST",
		url:"../prodbase/getProdDetailById.do",
		dataType:"json",
		async:false,
		data:{"prodid":prodid},
		success:function(data){
			//alert(data);
			var subProductsTableHtml = "<table class='table'><tr><td>编号</td><td>名称</td><td>成本价格</td><td>销售价格</td><td>最低销售价格</td><td>允许折扣</td><td>操作</td></tr>";
			var  json =  JSON.parse(data);
			$.each(json,function(i,o){
				var discountenable ='';
				if(o.discountenable == 1){
					discountenable = "是";
				}else{
					discountenable = "否";
				}
				subProductsTableHtml += "<tr><td>"+o.subprodid+"</td>";
				subProductsTableHtml += "<td>"+o.subprodname+"</td>";
				subProductsTableHtml += "<td>"+o.subprodprice+"</td>";
				subProductsTableHtml += "<td>"+o.subprodsaleprice+"</td>";
				subProductsTableHtml += "<td>"+o.subprodminprice+"</td>";
				subProductsTableHtml += "<td>"+discountenable+"</td>";
				subProductsTableHtml += "<td><a onclick='updSubProductShow(\""+o.subprodid+"\")'>修改</a></td></tr>";
			});
			subProductsTableHtml += "<tr><td><a onclick='retractSubProduct()'>收起</a></td></tr></table>"
			$("#subProductsTable").html(subProductsTableHtml);
			$("#productUpd .see-subproduct").hide();
		}
	});
}

//收起子产品
function retractSubProduct(){
	$("#productUpd .see-subproduct").show();
	$("#subProductsTable").html("");
}
//新增子产品
function addSubProduct(){
	if(!$("#producDetailtUpd").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../prodbase/addSubProduct.do",
		dataType:"json",
		async:false,
		data:$("#producDetailtUpd").serialize(),
		success:function(data){
			if(data>0){
				toastr.success("新增成功");
				$("#prodcutDetailUpdModal").modal("hide");
			}else{
				toastr.warning("新增失败");
			}
			$("#allProductData").bootstrapTable("load",loadTableData());
		},
		error:function(){
			toastr.danger("出错啦");
		}
	});
}
