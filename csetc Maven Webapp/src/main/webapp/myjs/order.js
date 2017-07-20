/**
 * 文件名: order.js
 * 描述:订单相关数据展示及相关处理
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-05-17
 */
$(document).ready(function(){
	operBtn();
	//订单数据
	fillOrderTableData();
	//为输入框绑定获得事件
	$("body").on("focus","input",function(){
		$(this).select();
	});
	

});

//当产品下拉框数据改变时更改子产品数据
function changeSubProd(formid){
	fillSubProdData($("#"+formid+" select[name='prodid']").val(),formid);
}

//客户签单的步骤向导
function customerOrderStep(modalid,cid,cname,customerHtml){
	var stepHtml = '';
	if((typeof customerHtml) == 'undefined'){
		customerHtml = '<input type="hidden" name="customerid" value="'+ cid +'"/><input class="form-control" name="customername" readonly="readonly" value="'+cname+'">';
	}
		
	 stepHtml += '<h3>购买产品</h3><section>'
		+ '<div class="form-group"><label class="col-sm-2 control-label">客户姓名：</label>'
		+ '<div class="col-sm-4">'+customerHtml+'</div>'
		
		+ '<label class="col-sm-2 control-label">购买产品：</label>'
		+ '<div class="col-sm-4"><select class="form-control" name="prodid" id="buyprodid" onchange="changeSubProd(\''+modalid+'\')"></select></div></div>'
		
		+ '<div class="form-group"><label class="col-sm-2 control-label">产品价格：</label>'
		+ '<div class="col-sm-4"><input class="form-control" name="price" readonly="readonly"></div>'
		+ '<label class="col-sm-2 control-label">定金：</label>'
		+ '<div class="col-sm-4"><input class="form-control" name="deposit"></div></div>'
			
		+ '<div class="form-group"><label class="col-sm-2 control-label">是否折扣：</label>'
		+ '<div class="col-sm-6"><div class="radio radio-inline">'
		+ '<input type="radio" id="discount22" value="0" name="discountstatus" checked="" onclick="discountChange(0,\''+modalid+'\')"><label for="discount22"> 否 </label></div>'
		+ '<div class="radio radio-info radio-inline">'
		+ '<input type="radio" id="discount11" value="1" name="discountstatus" onclick="discountChange(1,\''+modalid+'\')"><label for="discount11"> 是</label></div></div></div>'
		+ '<div class="form-group"><label class="col-sm-2 control-label">促销码：</label>'
		+ '<div class="col-sm-4"><input type="text" class="form-control" name="promocode" readonly="readonly" onchange="discountMountByDiscountID(this.value,\''+modalid+'\')"/></div>'
		+ '<label class="col-sm-2 control-label">折扣金额：</label>'
		+ '<div class="col-sm-4"><input class="form-control" name="discount" readonly="readonly" /></div></div>'
		
		+ '<div class="form-group"><div class="col-sm-11 col-sm-offset-1"><div class="panel panel-default">'
		+ '<div class="panel-heading"><h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><a>子产品</a></h4></div>'
		+ '<div id="collapseOne" class="panel-collapse collapse in"><table class="table" id="customerOrderTable"></table></div></div></div></div></section>';
		
	
	stepHtml += '<h3>付款方式</h3><section>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">付款方式：</label>'
		+ '<div class="col-sm-6"><select class="form-control" name="paymethod" onchange="paymethodChange(this.value,\''+ modalid +'\')"></select></div></div>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">银行账簿：</label>'
		+ '<div class="col-sm-6"><select class="form-control" name="bankingbook" ></select></div></div>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">附件：</label>'			
		+ '<div class="col-sm-6"><input type="file" class="form-control" name="attachment1"></div></div>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">备注：</label>'
		+ '<div class="col-sm-6"><textarea rows="" cols="" class="form-control" name="memo" ></textarea></div></div></section>';
	
	$("#"+modalid).html(stepHtml);	
	
	fillProdData(modalid);//获得产品数据
	//获得付款方式
	fillselectData("paymethod","#"+modalid+" select[name='paymethod']");
	var count = 0;
	
	$("#"+modalid).steps({//设置步骤向导基础数据
		headerTag: "h3",
	    bodyTag: "section",
	    transitionEffect: "slideLeft",
	    autoFocus: true,
	    labels:{
	    	next: "下一步",                                                                      
		    previous: "上一步",
		    finish: "提交"
	    },
	    enableKeyNavigation: true,
	    onStepChanging: function(wizard, options, state){//点击下一步时进行表单验证
	    	var flag = true;
	    	var s = $("#customerOrderTable input");
	    	if(s.length>0){
	    		//console.log("验证每个值是否正确");
	    		$.each(s,function(i,o){
	    			var input = $(this).attr("style");
	    			if((typeof input) == 'undefined'){
	    				return true;
	    			}
	    			if(input.indexOf("border") != -1){
	    				flag = false;
	    				return false;
	    			}
	    			console.log(flag);
	    		});
	    	}
	    	if(options>0){
	    		flag = $("#"+modalid).valid();
	    	}
	    	return flag;
	    },
	    onFinished : function(event,currentIndex){//点击完成时发生的事件
	    	count++;
	    	if(count==1){
	    		addOrder(modalid,"../order/addorder.do");
	    	}
	    	
	    	return true;
	    }
	});
}

//弹出客户签单模态框
function assignShowOrderModal(){
	var selectcustomer = $('#customerassignData').bootstrapTable('getSelections');
	if(selectcustomer.length != 1){
		toastr.error("请先选择一个客户再签单！");
		return false;
	}
	if(!checkHaveCustomer(selectcustomer[0].userid)){
		return false;
	}
//	console.log(selectcustomer);
	customerOrderStep("orderForm",selectcustomer[0].customerid,selectcustomer[0].customername);//模态框的数据
	$("#orderModal").modal("show");
}

//获得产品数据
function fillProdData(formid,defult,subProds){
	$.ajax({
		type:"POST",
		url:"../prodbase/getAllEnableProd.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var prodid = json[0].prodid;
//			console.log(json);
			$.each(json,function(i,o){
				 var html = ""; 
				 if(o.prodid == defult){
					 html = "<option selected value="+o.prodid+">" +o.prodname+ "</option>";
					 prodid = o.prodid;
				 }else{
					 html = "<option value="+o.prodid+">" +o.prodname+ "</option>";
				 }
                 $("#"+formid+" select[name='prodid']").append(html);//下拉框数据填充  
			});
			//获得子产品数据
			fillSubProdData(prodid,formid,subProds);
		}
	});
}

//获得子产品数据
function fillSubProdData(prodid,formid,subProds){
	if((typeof subProds) != "undefined"){
		var price = 0;
		var customerOrderTableHtml = "<tr><td>子产品编号</td><td>子产品名称</td><td>价格</td><td>售出价格</td><td>已交金额</td></tr>";
		$.each(subProds,function(index,obj){
			price += Number(obj.price);
			customerOrderTableHtml += '<tr><td>'+ obj.orderno +'</td>';
			customerOrderTableHtml += '<td>'+ obj.prodname +'</td>';
			customerOrderTableHtml += '<td>'+ obj.price +'</td>';
			customerOrderTableHtml += '<td><input onblur="amountDataCheck(this)" readonly="readonly" class="input-xs" type="text" size="6" value="'+ obj.amount +'"/></td>';
			customerOrderTableHtml += '<td><input onblur="amountDataCheck(this)" type="text" size="6" value="'+ obj.amountcount +'"/></td></tr>';
		});
		$("#"+formid+" input[name='price']").val(price);
		$("#customerOrderTable").html(customerOrderTableHtml);
	}else{
		$.ajax({
			type:"POST",
			url:"../prodbase/getProductByProductid.do",
			data:{"prodid":prodid},
			dataType:"json",
			async:false,
			success:function(d){
				var subprods = JSON.parse(d);
				var price = 0;
				var customerOrderTableHtml = "<tr><td>子产品编号</td><td>子产品名称</td><td>价格</td><td>售出价格</td><td>已交金额</td></tr>";
				$.each(subprods,function(index,obj){
					price += Number(obj.subprodsaleprice);
					customerOrderTableHtml += '<tr><td>'+ obj.subprodid +'</td>';
					customerOrderTableHtml += '<td>'+ obj.subprodname +'</td>';
					customerOrderTableHtml += '<td>'+ obj.subprodsaleprice +'</td>';
					customerOrderTableHtml += '<td><input onblur="amountDataCheck(this)" readonly="readonly" class="input-xs" type="text" size="6" value="'+ obj.subprodsaleprice +'" minprice="' + obj.subprodminprice + '"/></td>';
					customerOrderTableHtml += '<td><input onblur="amountDataCheck(this)" type="text" size="6" value="'+ obj.subprodsaleprice +'"/></td></tr>';
				});
				$("#"+formid+" input[name='price']").val(price);
				$("#customerOrderTable").html(customerOrderTableHtml);
				
			}
		});
	}
	
}

//检查子产品输入框的值
function amountDataCheck(input){
	input.setAttribute("style","");
	if (isNaN(input.value)) {//如果不为数字
		 input.setAttribute("style","border:1px solid red");
		return false;
	}
	if(Number(input.value) < Number(input.getAttribute("minprice"))){
		input.setAttribute("style","border:1px solid red");
		return false;
	}
}

//设置是否需要填写促销码
function discountChange(v,formid){
	if(v == 1){
		$("#"+formid+" input[name=promocode]").attr("required","required");
		$("#"+formid+" input[name=promocode]").removeAttr("readonly");
		$("#"+formid+" input[name=discount]").attr("required","required");
	}else{
		$("#"+formid+" input[name=promocode]").attr("readonly","readonly");
		$("#"+formid+" input[name=promocode]").removeAttr("required");
		$("#"+formid+" input[name=promocode]").val("");
		$("#"+formid+" input[name=discount]").val("");
		$("#"+formid+" input[name=discount]").removeAttr("required");
		defaultval(formid);
	}
}

//设置金额的输入框数据为默认
function defaultval(formid){
	$("#"+formid+" input[name=discount]").val("");
	//更改子产品销售金额
	var subprods = $("#customerOrderTable").find("tbody").find("tr");
	$.each(subprods,function(i,o){
		$(this).find("td:eq(3)").children().val($(this).find("td:eq(2)").text());
		$(this).find("td:eq(4)").children().val($(this).find("td:eq(2)").text());
	});
}

//根据促销码查看促销金额
function discountMountByDiscountID(promocode,formid){
	$.ajax({
		type:"POST",
		url:"../promocode/getPromotionRule.do",
		data:{"promocode":promocode,"rules":$("#"+formid+" select[name='prodid']").val()},
		dataType:"json",
		async:false,
		success:function(d){
			//用分号分成字符串数组
			if(d.length>0){
				var promotion = d.split(";");
//				console.log(promotion);
				var subprods = $("#customerOrderTable").find("tbody").find("tr");
				var discounts = 0;
				$.each(subprods,function(i,o){
					var promoprod = $(this);
					for(var j = 0; j<promotion.length; j++){
						var promopid = promotion[j].substring(promotion[j].indexOf("|")+1,promotion[j].length)
						if(promoprod.find("td:eq(0)").text() == promopid){
							var discount = Number(promotion[j].substring(0,promotion[j].indexOf("|")))
							var newPrice = Number(promoprod.find("td:eq(3)").children().val()) - Number(promotion[j].substring(0,promotion[j].indexOf("|")));
							discounts += discount;
							promoprod.find("td:eq(3)").children().val(newPrice);
							promoprod.find("td:eq(4)").children().val(newPrice);
						}
					}
				});
				$("#"+formid+" input[name='discount']").val(discounts);
			}else{
				toastr.warning("您输入的促销码不能在该产品上使用，请重新输入");
				$("#"+formid+" input[name='promocode']").val("");
				defaultval(formid);
			}
			
		}
	});
	
}

//付款方式改变时是否需要银行账簿
function paymethodChange(v,formid){
	if(v!=1){
		fillselectData("bankingbook","#"+formid+" select[name='bankingbook']");
	}else{
		$("#"+formid+" select[name='bankingbook']").html("");
	}
}


//新增签单
function addOrder(formid,url){
	
	//获得子产品的数据
	//1.进行子产品数据拼接
	var subprods = $("#customerOrderTable").find("tbody").find("tr");
	var subprodAry = [];//子产品json数据
	var price = 0;
	var amount = 0;
	var amountcount = 0
	$.each(subprods,function(i,o){
		if(i == 0){
			return true;
		}
		var json = {"prodid":"",
					"prodname":"",
					"price":"",
					"amount":"",
					"amountcount":""};
		json.prodid = $(this).find("td:eq(0)").text();
		json.prodname = $(this).find("td:eq(1)").text();
		json.price = $(this).find("td:eq(2)").text();
		json.amount = $(this).find("td:eq(3)").children().val(); 
		json.amountcount = $(this).find("td:eq(4)").children().val();
		price += Number(json.price);
		amount += Number(json.amount);
		amountcount += Number(json.amountcount);
		subprodAry.push(json);
//		alert(json.prodname);
	});
	//如果产品价格为空
	if($("#"+formid+" input[name='price']").val().length == 0){
		$("#"+formid+" input[name='price']").val(price);
	}
	
	var formData = new FormData($( "#"+formid )[0]);
	var flag = false;
	var attachment = "";
	$.ajax({
		type:"post",
		url:"../order/uploadAttachment.do",
		data:formData,
		dataType:"json",
		cache: false,  
		contentType: false,
        processData: false,
		async:false,
		success:function(data){
			attachment = data;
		},
		error:function(){
			toastr.error("上传附件失败");
			flag = true;
			
		}
	
	});
	if(flag){
		return false;
	}
	$.ajax({
		type:"POST",
		url:url+"?orderdetail="+encodeURI(window.JSON.stringify(subprodAry))+"&prodname="+encodeURI($("#"+formid+" select[name='prodid'] option:selected").text())+"&amount="+amount+"&amountcount="+amountcount+"&attachment="+attachment,
		data:$("#"+formid).serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			if(data>=1){
				toastr.success("签单成功");
				$("#orderModal").modal("hide");
				$("#addOrderModal").modal("hide");
				$("#orderTable").bootstrapTable("load",getOrderTableData());
				$("#customerassignData").bootstrapTable("load",getAssignCustomerData());
				
				
			}
		},
		error:function(){
			toastr.error("新增签单失败");
		}
	});
}


//查看订单数据
function getOrderTableData(){
	var json;
	$.ajax({
		type:"POST",
		url:"../order/getOrderData.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json,function(i,o){
				o.createtime = new Date(o.createtime).format('yyyy-MM-dd');
			})
		},
		error:function(){
			toastr.error("获取订单数据失败！");
		}
	});
	return json;
}

//填充表格数据
function fillOrderTableData(){
	$('#orderTable').bootstrapTable({
	    data:getOrderTableData(),
	    striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar:"#ordertoolbar",
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 20,                       //每页的记录行数（*）
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
	    },
	    {
	        field: 'orderid',
	        title: '编号',
	        sortable:true
	    }, {
	    	field: 'customertext',
	    	title: '客户姓名',
	    	sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '签单时间',
	    	sortable:true
	    }, {
	    	field: 'usertext',
	    	title: '负责人',
	    	sortable:true
	    }, {
	    	field: 'depttext',
	    	title: '负责人所属部门',
	    	sortable:true
	    }, {
	    	field: 'prodname',
	    	title: '产品名称',
	    	sortable:true
	    }, {
	    	field: 'price',
	    	title: '产品价格',
	    	sortable:true
	    }, {
	    	field: 'deposit',
	    	title: '定金',
	    	sortable:true
	    }, {
			field: 'discount',
			title: '折扣金额'
	    }, {
	    	field: 'amount',
	    	title: '应收款金额'
	    }, {
	    	field: 'amountcount',
	    	title: '已收款金额'
	    }, {
	    	field: 'promocode',
	    	title: '促销码'
	    }, {
	    	field: 'discount',
	    	title: '促销金额'
	    }, {
	    	field: 'memo',
	    	title: '备注'
	    }, {
	    	field: 'attachment',
	    	title: '附件'
	    }, {
	    	field: 'status',
	    	title: '状态',
	    	sortable:true,
	    	formatter:function(value,row,index){
	    		if(value == 1011){
	    			return "待收款";
	    		}else if(value == 1012){
	    			return "已收款，未全款";
	    		}else if(value == 1013){
	    			return "已全款"
	    		}else if(value == 1014){
	    			return "订单废弃"
	    		}else if(value == 1015){
	    			return "客户取消订单"
	    		}else if(value == 1021){
	    			return "退款处理中"
	    		}else if(value == 1022){
	    			return "退款完成"
	    		}else if(value == 1023){
	    			return "退款取消"
	    		}else if(value == 1031){
	    			return "返款已申请"
	    		}else if(value == 1032){
	    			return "返款财务已处理"
	    		}else if(value == 1033){
	    			return "返款申请取消"
	    		}
	    	}
		}],
		onClickRow:function(row,ele,field){//单击行插入回访数据
			//看该客户是否有回访记录（今日）有就修改没有就新增
			/*if(getExistReservation(row.customerid,"该客户有未处理完的预约，暂时不能新增待回访记录")){
				showCallbackModal(getCallbackBySome(row.customerid),JSON.parse(JSON.stringify(row)));
			}*/
			
		},
		onDblClickRow:function(row,ele,field){//双击行预约客户
		}
	});
	
	hideTableColumn();//隐藏列
}

//隐藏列 
function hideTableColumn(){
	$("#orderTable").bootstrapTable("hideColumn","promocode");
	$("#orderTable").bootstrapTable("hideColumn","memo");
	$("#orderTable").bootstrapTable("hideColumn","attachment");
	$("#orderTable").bootstrapTable("hideColumn","depttext");
}

//新增签单新增展示
function addOrderShow(){
	var customerSelect = "<select name='customerid' class='form-control'>";
	//获得可以新增的客户信息
	$.ajax({ 
		type:"POST",
		url:"../TbCustomerAssign/getMyCustomerSelect.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length == 0){
				toastr.warning("您目前没有可以新签单的用户");
			}else{
				$.each(json,function(i,o){
					customerSelect +="<option value='"+o.customerid+"'>"+ o.usertext+"</option>"
				});
				customerSelect += "</select>";
				customerOrderStep("addOrderForm",'','',customerSelect);
				$("#addOrderModal").modal("show");
			}
			
		},
		error:function(){
			toastr.error("获得客户数据失败");
		}
	});
}

//订单所有者变更模态框
function updOrderUserShow(){
	var orders = $("#orderTable").bootstrapTable('getSelections');
	if(orders.length == 0){
		toastr.warning("请先选择订单后再进行此操作");
		return false;
	}
	//
	var orderidstr ="";
	$.each(orders,function(i,o){
		orderidstr += o.orderid + ",";
	})
	$("#updOrderUserForm input[name='orderidstr']").val(orderidstr);
	//获得可以转移的用户信息
	selectUser("#user_data");
	//订单变更原因
	fillselectData("orderchangereason","#updOrderUserForm select[name='reason']");

	$("#updOrderUserModal").modal("show");
}

//订单所有者转移
function updOrderUser(){
	 swal({
	        title: "您确定要将这些订单转交给"+$("#user_data").val()+"吗？",
	        text: "请谨慎操作！",
	        type: "warning",
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "确定",
	        closeOnConfirm: false,
	        showCancelButton: true,
	        cancelButtonText: "取消"
	    }, function () {
	    	$.ajax({ 
				type:"POST",
				url:"../order/updOrderUser.do?userid="+$("#user_data").attr("data-id"),
				dataType:"json",
				async:false,
				data:$("#updOrderUserForm").serialize(),
				success:function(data){
					if(data>0){
						 swal("订单转移成功！", "", "success");
						 $("#updOrderUserModal").modal("hide");
						 $("#orderTable").bootstrapTable("load",getOrderTableData());
					}
				},error:function(){
					toastr.warning("订单转移失败");
				}
			});
	       
	    });
}


//取消订单模态框展示
function updCancelOrderShow(){
	var orders = $("#orderTable").bootstrapTable('getSelections');
	if(orders.length != 1){
		toastr.warning("请先选择一条订单后再进行此操作");
		return false;
	}
	if(orders[0].status != 1011){
		toastr.warning("只有未确认收款的订单才能取消");
		return false;
	}
	$("#cancelOrderForm input[name='oederid']").val(orders[0].orderid);
	//获得原因
	fillselectData("orderchangereason","#cancelOrderForm select[name='reason']",3);
	$("#cancelOrderForm textarea[name='memo']").text("客户取消订单");
	$("#cancelOrderModal").modal("show");
}

//取消订单
function cancelOrder(){
	$.ajax({ 
		type:"POST",
		url:"../order/updOrderStatus.do?ostatus=1015&status=0",
		dataType:"json",
		async:false,
		data:$("#cancelOrderForm").serialize(),
		success:function(data){
			 $("#orderTable").bootstrapTable("load",getOrderTableData());
			 $("#cancelOrderModal").modal("hide");
		},error:function(){
			toastr.warning("取消失败");
		}
	});
}

//退款模态框
function updDropOutModalShow(){
	//获得订单号，产品编号。产品名称，客户名字
	var orders = $("#orderTable").bootstrapTable('getSelections');
	if(orders.length != 1){
		toastr.warning("请先选择一条订单后再进行此操作");
		return false;
	}
	if(orders[0].status != 1013 && orders[0].status != 1012 && orders[0].status != 1023 && orders[0].status != 1033 && orders[0].status != 1032){
		toastr.warning("该订单数据不允许退款");
		return false;
	}
	$("#dropOutOrderModal .modal-title").text("退款");
	$("#dropOutOrderForm input[name='orderid']").val(orders[0].orderid);
	$("#dropOutOrderForm input[name='prodid']").val(orders[0].prodid);
	$("#dropOutOrderForm input[name='prodname']").val(orders[0].prodname);
	$("#dropOutOrderForm input[name='customername']").val(orders[0].customertext);
	$("#dropOutOrderForm input[name='refund']").val(orders[0].amountcount);
	$("#dropOutOrderForm input[name='approvetype']").val("1");
	$("#dropOutOrderForm input[name='ostatus']").val("1021");
	//退款原因
	fillselectData("dropoutreason","#dropOutOrderForm select[name='refundtype']",3);
	//审批流选择
	approveSelect(1);
	$("#dropOutOrderModal").modal("show");
}

//审批流程下拉框
function approveSelect(type){
	$.ajax({
		type:"POST",
		url:"../approve/getApproveSelectByType.do",
		dataType:"json",
		async:false,
		data:{"type":type},//type为1代表退款
		success:function(data){
			var approveSelectHtml = "";
			$.each(JSON.parse(data),function(i,o){
				approveSelectHtml +="<option value='"+o.approveid+"'>"+o.flowname+"</option>"
			});
			$("#dropOutOrderForm select[name='approveid']").html(approveSelectHtml);//审批下拉框数据填充
		},error:function(){
			toastr.warning("流程数据获得失败");
		}
	})
}

//退学操作
function dropOutOrder(){
	if(!$("#dropOutOrderForm").valid()){
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../refund/addRefundinfo.do",
		dataType:"json",
		async:false,
		data:$("#dropOutOrderForm").serialize(),
		success:function(data){
			if(isNaN(data)){//如果不是数字证明审批流程不通过
				toastr.warning(data);
				return false;
			}
			toastr.success("退学申请成功");
			$("#dropOutOrderModal").modal("hide");
			$("#orderTable").bootstrapTable("load",getOrderTableData())
		},
		error:function(){}
	});
}

//返款
function updBackApprveShow(){
	var orders = $("#orderTable").bootstrapTable('getSelections');
	if(orders.length != 1){
		toastr.warning("请先选择一条订单后再进行此操作");
		return false;
	}
	if(orders[0].status != 1013 && orders[0].status != 1033){
		toastr.warning("交完全款的客户和申请取消的才能申请返款");
		return false;
	}
	var refund = 0;
	//查看该订单产品中是否有返款的子产品
	$.ajax({
		type:"POST",
		url:"../prodbase/getProductByProductid.do",
		dataType:"json",
		async:false,
		data:{"prodid":orders[0].prodid},
		success:function(data){
			//subprodname
			$.each(JSON.parse(data),function(i,o){
//				console.log(o)
				if(o.subprodname == "补贴"){
					refund = o.subprodsaleprice
					$("#dropOutOrderForm input[name='prodid']").val(o.subprodid);
					$("#dropOutOrderForm input[name='prodname']").val(o.subprodname);
//					console.log(o.subprodid)
				}
			})
		},
		error:function(){}
	});
	if(refund == 0){
		toastr.warning("该订单不能返款");
		return false;
	}
	
	$("#dropOutOrderModal .modal-title").text("返款");
	$("#dropOutOrderForm input[name='orderid']").val(orders[0].orderid);
//	$("#dropOutOrderForm input[name='prodid']").val(orders[0].prodid);
//	$("#dropOutOrderForm input[name='prodname']").val(orders[0].prodname);
	$("#dropOutOrderForm input[name='customername']").val(orders[0].customertext);
	$("#dropOutOrderForm input[name='refund']").val(refund);
	$("#dropOutOrderForm input[name='approvetype']").val("2");
	$("#dropOutOrderForm input[name='ostatus']").val("1031");
	//退款原因
	fillselectData("dropoutreason","#dropOutOrderForm select[name='refundtype']",1);
	//审批流选择
	approveSelect(2);
	$("#dropOutOrderModal").modal("show");
}



//订单详情/修改模态框
function updOrderDataModal(){
	var orders = $("#orderTable").bootstrapTable('getSelections');
	if(orders.length != 1){
		toastr.warning("请先选择一条订单后再修改");
		return false;
	}
	if(orders[0].status > 1013){
		toastr.warning("该订单数据不允许修改");
		return false;
	}
	//1.根据订单编号获得订单详情数据
	$.ajax({ 
		type:"POST",
		url:"../order/getOrderDataByOid.do",
		dataType:"json",
		async:false,
		data:{"orderid":orders[0].orderid},
		success:function(data){
			customerUpdOrderStep("addOrderForm",orders[0].orderid,orders[0].customertext,JSON.parse(data));//模态框数据
		},error:function(){
			toastr.warning("删除失败！请先移除该部门下的所有员工数据");
		}
	});
	//3.展示模态框
	$("#addOrderModal").modal("show");
}


function customerUpdOrderStep(modalid,oid,cname,json){
	var discountHtml ;
	//折扣选择
	if(json.order.promocode == ""){
		discountHtml = '<div class="form-group"><label class="col-sm-2 control-label">是否折扣：</label>'
			+ '<div class="col-sm-6"><div class="radio radio-inline">'
			+ '<input type="radio" id="discount22" value="0" name="discountstatus" checked="" onclick="discountChange(0,\''+modalid+'\')"><label for="discount22"> 否 </label></div>'
			+ '<div class="radio radio-info radio-inline">'
			+ '<input type="radio" id="discount11" value="1" name="discountstatus" onclick="discountChange(1,\''+modalid+'\')"><label for="discount11"> 是</label></div></div></div>'
			+ '<div class="form-group"><label class="col-sm-2 control-label">促销码：</label>'
			+ '<div class="col-sm-4"><input type="text" class="form-control" name="promocode" readonly="readonly" onchange="discountMountByDiscountID(this.value,\''+modalid+'\')"/></div>'
			+ '<label class="col-sm-2 control-label">折扣金额：</label>'
			+ '<div class="col-sm-4"><input class="form-control" name="discount" readonly="readonly"/></div></div>';
	}else{
		discountHtml = '<div class="form-group"><label class="col-sm-2 control-label">是否折扣：</label>'
		+ '<div class="col-sm-6"><div class="radio radio-inline">'
		+ '<input type="radio" id="discount22" value="0" name="discountstatus" onclick="discountChange(0,\''+modalid+'\')"><label for="discount22"> 否 </label></div>'
		+ '<div class="radio radio-info radio-inline">'
		+ '<input type="radio" id="discount11" value="1" name="discountstatus" checked="" onclick="discountChange(1,\''+modalid+'\')"><label for="discount11"> 是</label></div></div></div>'
		+ '<div class="form-group"><label class="col-sm-2 control-label">促销码：</label>'
		+ '<div class="col-sm-4"><input type="text" class="form-control" name="promocode" value="'+json.order.promocode+'" onchange="discountMountByDiscountID(this.value,\''+modalid+'\')"/></div>'
		+ '<label class="col-sm-2 control-label">折扣金额：</label>'
		+ '<div class="col-sm-4"><input class="form-control" name="discount" readonly="readonly" value="'+json.order.discount+'"/></div></div>'
	}
		
	var stepHtml = '<h3>购买产品</h3><section>'
		+ '<div class="form-group"><label class="col-sm-2 control-label">客户姓名：</label>'
		+ '<div class="col-sm-4"><input type="hidden" name="orderid" value="'+ oid +'"/><input class="form-control" name="customername" readonly="readonly" value="'+cname+'"></div>'
		
		+ '<label class="col-sm-2 control-label">购买产品：</label>'
		+ '<div class="col-sm-4"><select class="form-control" name="prodid" id="buyprodid" onchange="changeSubProd(\''+modalid+'\')"></select></div></div>'
		
		+ '<div class="form-group"><label class="col-sm-2 control-label">产品价格：</label>'
		+ '<div class="col-sm-4"><input class="form-control" name="price" value="'+json.order.price+'"></div>'
		+ '<label class="col-sm-2 control-label">定金：</label>'
		+ '<div class="col-sm-4"><input class="form-control" name="deposit"></div></div>'
			
		+ discountHtml
		
		+ '<div class="form-group"><div class="col-sm-11 col-sm-offset-1"><div class="panel panel-default">'
		+ '<div class="panel-heading"><h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><a>子产品</a></h4></div>'
		+ '<div id="collapseOne" class="panel-collapse collapse in"><table class="table" id="customerOrderTable"></table></div></div></div></div></section>';

	stepHtml += '<h3>付款方式</h3><section>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">付款方式：</label>'
		+ '<div class="col-sm-6"><select class="form-control" name="paymethod" onchange="paymethodChange(this.value,\''+ modalid +'\')"></select></div></div>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">银行账簿：</label>'
		+ '<div class="col-sm-6"><select class="form-control" name="bankingbook" ></select></div></div>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">附件：</label>'			
		+ '<div class="col-sm-6"><textarea rows="" cols="" class="form-control" name="attachment" value="'+json.order.attachment+'"></textarea></div></div></section>';
		
	stepHtml += '<h3>其他</h3><section>'
		+ '<div class="form-group"><label class="col-sm-4 control-label">备注：</label>'
		+ '<div class="col-sm-6"><textarea rows="" cols="" class="form-control" name="memo" value="'+json.order.memo+'"></textarea></div></div></section>';
	
	$("#"+modalid).html(stepHtml);	

	
	fillProdData(modalid,json.order.prodid,json.orderdetail);//获得产品数据
	//获得付款方式
	fillselectData("paymethod","#"+modalid+" select[name='paymethod']",json.order.paymethod);
	//获得银行账簿 
	fillselectData("bankingbook","#"+modalid+" select[name='bankingbook']",json.order.bankingbook);
	
		
	$("#"+modalid).steps({//设置步骤向导基础数据
		headerTag: "h3",
	    bodyTag: "section",
	    transitionEffect: "slideLeft",
	    autoFocus: true,
	    labels:{
	    	next: "下一步",                                                                      
		    previous: "上一步",
		    finish: "提交"
	    },
	    enableKeyNavigation: true,
	    onStepChanging: function(wizard, options, state){//点击下一步时进行表单验证
	    	var flag = true;
	    	var s = $("#customerOrderTable input");
	    	if(s.length>0){
	    		//console.log("验证每个值是否正确");
	    		$.each(s,function(i,o){
	    			var input = $(this).attr("style");
	    			if((typeof input) == 'undefined'){
	    				return true;
	    			}
	    			if(input.indexOf("border") != -1){
	    				flag = false;
	    				return false;
	    			}
//	    			console.log(flag);
	    		});
	    	}
	    	if(options>0){
	    		flag = $("#"+modalid).valid();
	    	}
	    	return flag;
	    },
	    onFinished : function(event,currentIndex){//点击完成时发生的事件
	    	addOrder(modalid,"../order/updOrderUser.do");//更改订单数据
	    	return false;
	    }
	});
	
	
}

//订单变更
function updOrderData(){
	$.ajax({ 
		type:"POST",
		url:"../.do",
		dataType:"json",
		async:false,
		data:{"id":did},
		success:function(data){
			if(data>0){
			}
			
		},error:function(){
			toastr.warning("删除失败！请先移除该部门下的所有员工数据");
		}
	});
}

//页面上可操作的按钮
function operBtn(){
//	operstr//可页面上可操作的按钮
	//将所有操作按钮禁用
	$("#ordertoolbar button").attr("disabled", true);
	var operbtns = operstr.split("|");//可操作的按钮
	//console.log(operbtns);
	var btns = $("#ordertoolbar button"); //页面上所按钮
	$.each(btns,function(i,o){
		var fun = $(this).attr("onclick");//获得每个按钮的onclick属性值
		if(typeof fun !="undefined"){
			for(var i = 0; i < operbtns.length; i++){
				//alert(fun.toLocaleLowerCase().substring(0,6));
				//console.log(fun);
				if(operbtns[i] == "1" && fun.toLocaleLowerCase().substring(0,3).indexOf("add") == 0){
					$(this).removeAttr("disabled");
				}else if(operbtns[i] == "2" && fun.toLocaleLowerCase().substring(0,3).indexOf("upd") == 0){
					$(this).removeAttr("disabled");
				}else if(operbtns[i] == "3" && fun.toLocaleLowerCase().substring(0,3).indexOf("del") == 0){
					$(this).removeAttr("disabled");
				}else if(operbtns[i] == "4" && fun.toLocaleLowerCase().substring(0,3).indexOf("qry") == 0){
					$(this).removeAttr("disabled");
				}else if(operbtns[i] == "5" && fun.toLocaleLowerCase().substring(0,6).indexOf("upload") == 0){
					$(this).removeAttr("disabled");
				}else if(operbtns[i] == "6" && fun.toLocaleLowerCase().substring(0,6).indexOf("assign") == 0){
					$(this).removeAttr("disabled");
				}else if(operbtns[i] == '7' && fun=='uploadCustomerQualification()') {
					// 7是系统操作类型下小类的第7个，也就是上传客户资质按钮，它的名字是uploadCustomerQualification
					$(this).removeAttr("disabled");
				}
			}
		}
	})
}


//新增客户批注展示
function updRemarkShow(){
	
	var tabledata = $("#orderTable").bootstrapTable("getSelections");
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
	addressInit("servicetype","remarktype","1006","");
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

//查看历史批注
function addSeeHistoryRemark(){
	var d= $('#orderTable').bootstrapTable('getSelections');
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


//上传客户资质按钮
function uploadCustomerQualification(){
	/*
	//alert($("#atLeastMoneyForQua").val());这时可以获取3000块
	//这个判断应该是申请入班的时候进行的判断，并且条件是交款大于指定钱数
	if(!(orders[0].status == 1013 || orders[0].status == 1012)){
		toastr.warning("订单状态为\"已全款\"或者\"已收款,未全款\"的订单才允许上传客户资质");
		return false;
	}*/
	var orders= $('#orderTable').bootstrapTable('getSelections');
	if(orders.length != 1){
		toastr.warning("请选择一条数据再操作");
		return false;
	}

	$(".picUpload").each(function(index, obj){
		var qualificationTypeId = $(this).children("#quaTypeIdInput").val();
	    //console.log(orders[0].orderid+"----"+qualificationTypeId)
	    
	    var viewBtn = $(this).parent().next().children(".viewUplQuaBtn");
	    var uploadBtn = $(this).parent().next().children(".uplQuaBtn");
	    var fileInput = $(this).children(".uplQuaInp");
	    //如果没有图片，将查看按钮置为不可点击
		$.ajax({
			url : "../CustomerQualification/IsQuaEmpty.do",
			type : "POST",
			data: {"orderId":orders[0].orderid,"qualificationTypeId":qualificationTypeId},
			success : function(data) {
				if (data["isExist"]=="false") {//如果没有上传图片，也就是不存在，查看按钮置为不可用
					//全部
					//$(".viewUplQuaBtn").attr("disabled", true); 
					viewBtn.attr("disabled",true);
				} else {
					if (data["status"]=="2"){//已上传，待审核
						//上传按钮变成了提示信息
						$(uploadBtn).text("待审核");//按钮是用text函数
						//不可再点
						$(uploadBtn).attr("disabled", true);
						$(fileInput).attr("disabled", true);
					}
				}
			},
			error: function() {
				toastr.error("获取是否显示查看按钮发生错误，请联系管理员");
			},
			dataType : "json"
		});
	});
	
	//设置orderid
	var inputs = document.getElementsByTagName('input');
    for(var i = 0; i < inputs.length; i++){
        if(inputs[i].name == 'orderId'){
        	inputs[i].value = orders[0].orderid;
        }   
    }
	$("#uploadCusQuaModalBox").modal("show");
}

//上传客户资质图片
function uploadPicture(sid) {
	var theId = "#picUpload"+sid;
	var theFile = theId + " .uplQuaInp";
	var viewBtn = $(theFile).parent().parent().next().children(".viewUplQuaBtn");
	var uploadBtn = $(theFile).parent().parent().next().children(".uplQuaBtn");
	console.log(viewBtn);
	if ($(theFile).val()=="") {
		toastr.warning("请选择文件后再上传");
		return false;
	}
	var fdata = new FormData($(theId)[0]);
	$.ajax({
		url : "../CustomerQualification/UploadCusQua.do",
		type : "post",
		data : fdata,
		dataType : "json",
		contentType : false,
		processData : false,
		success : function(data) {
			if (data.success == "true") {
				$(viewBtn).attr("disabled", false);
				//上传按钮变成了提示信息
				$(uploadBtn).text("待审核");//按钮是用text函数
				//不可再点
				$(uploadBtn).attr("disabled", true);
				$(theFile).attr("disabled", true);
			} else {
				
			} 
			toastr.success(data['msg']);
		},
		error : function() {
			toastr.error("发生错误，请联系管理员");
		}
	});
}

//查看图片
function viewPicture(sid){
	var formId = "#picUpload"+sid;
	var orderId = $(formId).children("#orderIdInput").val();
	var qualificationTypeId = $(formId).children("#quaTypeIdInput").val();
	//console.log(orderId+"----"+qualificationTypeId);
	
	//请求图片地址
	$.ajax({
		url : "../CustomerQualification/GetQuaUrl.do",
		type : "POST",
		data: {"orderId":orderId,"qualificationTypeId":qualificationTypeId},
		success : function(data) {
			if (data["success"]=="true") {
				$("#qualificationPictureImg").attr('src',$("#qualificationPictureImg")[0].src+data['url']); 
			} else {
				toastr.error(data['msg']);
			}
		},
		error: function() {
			toastr.error("获取资质附件地址发生错误，请联系管理员");
		},
		dataType : "json"
	});
	$("#viewCusQuaModalBox").modal("show");
}