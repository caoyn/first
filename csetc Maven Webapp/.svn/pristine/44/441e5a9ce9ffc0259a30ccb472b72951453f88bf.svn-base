/**
 * 文件名: promocode.js
 * 描述:促销码管理的操作
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-09
 */
$(document).ready(function(){
	fillTableData();
	//当促销码输入框数据发生改变时
	$("#promocode").bind("input propertychange",function(){
		$("#promocode").val(existPromocode($("#promocode").val()));
	});
	//促销产品发生改变时
	$("#promocodeForm select[name='promoprod']").change(function(){
		getSubProdData();
	})
});

var prods;

//查看表格数据
function fillTableData(){
	$('#promocodeData').bootstrapTable({
	    data:getAllPromocodeData(),
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
        uniqueId: "promoid",                     //每一行的唯一标识，一般为主键列
        idField:"promoid",
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
	    columns: [{
	    	checkbox:true
	    }, {
	        field: 'promoid',
	        title: '编号',
	        sortable:true
	    }, {
	    	field: 'rpomocode',
	    	title: '促销码',
	    	sortable:true
	    }, {
	    	field: 'username',
	    	title: '创建人',
	    	sortable:true
	    }, {
	    	field: 'createtime',
	    	title: '创建时间',
	    	sortable:true
	    }, {
	    	field: 'expiretime',
	    	title: '过期时间',
	    	sortable:true
	    }, {
	    	field: 'maxuse',
	    	title: '可使用最大次数',
	    	sortable:true
	    }, {
	    	field: 'count',
	    	title: '当前使用次数',
	    	sortable:true
	    }, {
	    	field: 'used',
	    	title: '是否使用',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "未使用";
	    		}else if(value == 1){
	    			return "已使用";
	    		}
	    	},
	    	sortable:true
	    }, {
	    	field: 'status',
	    	title: '是否有效',
	    	formatter:function(value,row,index){
	    		if(value == 0){
	    			return "失效";
	    		}else if(value == 1){
	    			return "有效";
	    		}
	    	},
	    	sortable:true
	    }, {
			field: 'operate',
			title: '操作'
		}],
		onDblClickRow:function(row,ele,field){//双击行
			//addApproveDetailShow(row.approveid);
		}
	});
}

//查看所有促销码数据
function getAllPromocodeData(){
	var json;
	$.ajax({
		type:"post",
		url:"../promocode/getAllPromocodeData.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
			$.each(json,function(i,o){
				o.createtime = new Date(o.createtime).format("yyyy-MM-dd hh:mm:ss");
				o.expiretime = new Date(o.expiretime).format("yyyy-MM-dd hh:mm:ss");
				o.operate = '<button class="btn btn-xs btn-info" onclick="getPromoRules(\''+o.rules+'\')">查看促销规则</button>'
			})
		},
		error:function(){
			toastr.warning("获得促销码数据失败");
		}
	});
	console.log(json);
	return json;
}

//查看促销规则
function getPromoRules(rules){
	if((typeof prods) == "undefined"){
		getAllProd();
	}
	//获得当前促销的产品
	var promoProd = rules.split(";");
	var prodname = promoProd[0];
	prodname = prodname.substring(prodname.indexOf("|") + 1, prodname.length);
	var htmlt = "<tr><td>产品名称</td><td>产品金额</td><td>产品促销金额</td></tr>";
	$.each(prods,function(i,o){
		if(o.prodid == prodname){
			for(var j = 1; j < promoProd.length; j++){
				var subprod = promoProd[j].substring(promoProd[j].indexOf("|") + 1, promoProd[j].length);
				if(o.subprodid == subprod){
					htmlt += "<tr><td>" + o.subprodname + "</td>";
					htmlt += "<td>" + o.subprodprice + "</td>";
					htmlt += "<td>" + promoProd[j].substring(0,promoProd[j].indexOf("|")) + "</td></tr>";
				}
			}
			
		}
	});
	$("#promocodeDetailTable").html(htmlt);
	$("#promocodeDetailModal").modal("show");
}

//查看所有产品
function getAllProd(){
	$.ajax({
		type:"post",
		url:"../prodbase/selectAllProdsData.do",
		dataType:"json",
		async:false,
		success:function(data){
			prods = JSON.parse(data);
		},
		error:function(){
			toastr.warning("获得产品数据失败");
		}
	})
}

//新增促销码
function addPromocodeShow(){
	if((typeof prods) == "undefined"){
		getAllProd();
	}
	//选择产品
	console.log(prods);
	var res = [];
	var ary = [];
	for(var i=1; i<prods.length;i++){
		var repeat = false;
		for(var j=0; j<res.length ; j++){
			if(prods[i].prodid == res[j]){
				repeat = true;
				break;
			}
		}
		if(!repeat){
			res.push(prods[i].prodid);
			var json = {"prodid":"","prodname":""};
			json.prodid = prods[i].prodid;
			json.prodname = prods[i].prodname;
			ary.push(json);
		}
	}
	console.log(ary);
	
	var htmls = "";
	for(var i = 0; i < ary.length; i ++){
		htmls += "<option value='"+ ary[i].prodid +"'>"+ary[i].prodname+"</option>";
	}
	$("#promocodeForm select[name='promoprod']").html(htmls);
	getSubProdData();
	
	
	
	$("#promocodeModal").modal("show");
}

//获得子产品的数据
function getSubProdData(){
	var promoprodTableHtml = "<tr><td>编号</td><td>名称</td><td>价格</td><td>促销金额</td></tr>";
	$.each(prods,function(index,obj){
		if(obj.prodid == $("#promocodeForm select[name='promoprod']").val()){
			var money = obj.subprodsaleprice-obj.subprodminprice;
			promoprodTableHtml += '<tr><td>'+ obj.subprodid +'</td>';
			promoprodTableHtml += '<td>'+ obj.subprodname +'</td>';
			promoprodTableHtml += '<td>'+ obj.subprodsaleprice +'</td>';
			if(money>0){
				promoprodTableHtml += '<td><input type="text" size="6" subprodminprice="'+money+'" value="'+ money +'"/></td></tr>';
			}else{
				promoprodTableHtml += '<td><input type="text" size="6" subprodminprice="'+money+'" value="'+ money +'" readonly="readonly"/></td></tr>';
			}
			
		}
		
	});
	$("#promoprodTable").html(promoprodTableHtml);
}

//生成促销码
function createPromocode(){
	//生成由字母和数字组成的随机数
	$("#promocode").val(existPromocode());
}

//查看促销码是否存在
function existPromocode(promocode){
	var result = "";
	if((typeof promocode) == "undefined" || promocode == ""){
		 promocode = randomWord(false, 18);
	}
	
	var falg = false;
	$.ajax({
		type:"post",
		url:"../promocode/existPromocode.do",
		data:{"promocode":promocode},
		dataType:"json",
		async:false,
		success:function(data){
			if(data==0){
				flag = true;
				result = promocode;
			}else{
				existPromocode("");
			}
		}
	});
	if(flag){
		return result;
	}
}

//生成随机数
function randomWord(randomFlag, min, max){
    var str = "",
        range = min,
        arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
 
    // 随机产生
    if(randomFlag){
        range = Math.round(Math.random() * (max-min)) + min;
    }
    for(var i=0; i<range; i++){
        pos = Math.round(Math.random() * (arr.length-1));
        str += arr[pos];
    }
    return str;
}

//新增促销码
function addPromocode(){
	if(!$("#promocodeForm").valid()){
		return false;
	}
	
	//获得促销规则
	var subprods = $("#promoprodTable").find("tbody").find("tr");
	var subprodAry = [];//子产品json数据
	var rules = "0|" + $("#promocodeForm select[name='promoprod']").val() + ";";
	var flag = false;
	$.each(subprods,function(i,o){
		if(i == 0){
			return true;
		}
		var money = $(this).find("td:eq(3)").children().val();
		var maxmoney = $(this).find("td:eq(3)").children().attr("subprodminprice");
		if(!(Number(money) <= Number(maxmoney))){
			toastr.error($(this).find("td:eq(1)").text()+"的促销金额必需小于等于"+maxmoney);
			flag = true;
			return false;
		}
		if(money>0){
			rules += money + "|" + $(this).find("td:eq(0)").text() + ";";
		}
	});
	if(flag){
		return false;
	}
	//新增促销码数据到数据库
	$.ajax({
		type:"post",
		url:"../promocode/addPromocode.do?rules="+rules,
		data:$("#promocodeForm").serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			toastr.success("新增促销码数据成功");
			$("#promocodeModal").modal("hide");
			$("#promocodeData").bootstrapTable("load",getAllPromocodeData());
		},
		error:function(){
			toastr.error("新增促销码数据失败");
		}
	});
}

//禁用促销码
function updPromocodes(){
	var data = $("#promocodeData").bootstrapTable("getSelections");
	if(data.length<1){
		toastr.warning("请选择您要禁用的数据");
		return false;
	}
	
	swal({
	    title: "您确定要禁用这"+data.length+"个促销码吗？",
	    text: "确认后不可恢复请谨慎操作！",
	    type: "warning",
	    confirmButtonColor: "#DD6B55",
	    confirmButtonText: "确定",
	    closeOnConfirm: false,
	    showCancelButton: true,
	    cancelButtonText: "取消"
	}, function () {
		var ids = "";
		$.each(data,function(i,o){
			ids += o.promoid + ",";
		});
		
		$.ajax({
			type:"post",
			url:"../promocode/changePromocodeStatus.do",
			data:{"ids":ids,"status":"0"},
			dataType:"json",
			async:false,
			success:function(d){
				//toastr.success("新增促销码数据成功");
				swal("操作成功！", "", "success");
				$("#promocodeModal").modal("hide");
				$("#promocodeData").bootstrapTable("load",getAllPromocodeData());
			},
			error:function(){
				toastr.error("新增促销码数据失败");
			}
		});
	});
	
}
