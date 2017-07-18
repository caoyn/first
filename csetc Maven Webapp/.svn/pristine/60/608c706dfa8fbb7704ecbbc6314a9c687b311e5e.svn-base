/**
 * 文件名: content.js
 * 描述:数据统计
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-21
 */

$(document).ready(function() {
	$('.active').click(); //激活指定要激活的数据

	showOrdersEcharts() // 画订单收支图
	
});


//查看统计的总收入
function getGrossIncome(type,btn){
	$.ajax({
		type:"post",
		url:"../payment/getGrossIncome.do",
		data:{"type":type},
		dataType:"json",
		async:false,
		success:function(data){
			$("#income").text(thousandBitSeparator(data));
			
		},
		error:function(){
			toastr.error("获取数据出错");
		}
	});
	//清楚所有的active
	$(btn).siblings().removeClass("active");//当前节点的兄弟节点
	//新增一个active
	$(btn).addClass("active");
}

//按产品查看收入
function getIncomeByProd(type,btn){
	$.ajax({
		type:"post",
		url:"../payment/getIncomeByProd.do",
		data:{"type":type},
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			if(json.length == 0){
				$("#prodIncome").html("没有相关数据");
				return false;
			}
			var prodname = "<td>产品名称</td>";
			var money = "<td>产品销售总额</td>";
			$.each(json,function(i,o){
				prodname += "<td>"+ o.prodname +"</td>";
				money += "<td>"+ o.amount +"</td>";
			})
			$("#prodIncome").html("<tr>" + prodname + "</tr><tr>"+ money +"</tr>" );
		},
		error:function(){
			toastr.error("获取数据出错");
		}
	});
	
	//清楚所有的active
	$(btn).siblings().removeClass("active");//当前节点的兄弟节点
	//新增一个active
	$(btn).addClass("active");
	
}

//制作千分位符
 function thousandBitSeparator(num) {
    return num && (num.toString().indexOf('.') != -1 ? num.toString().replace(/(\d)(?=(\d{3})+\.)/g, function($0, $1) {
        return $1 + ",";
     }) : num.toString().replace(/(\d)(?=(\d{3}))/g, function($0, $1) {
         return $1 + ",";
     }));
 }
 
 //查看订单数据
 function orderData(){
	 var json;
	 $.ajax({
			type:"post",
			url:"../payment/orderData.do",
			dataType:"json",
			async:false,
			success:function(data){
				json = JSON.parse(data)
				//console.log(json);
			},
			error:function(){
				toastr.error("获取数据出错");
			}
		});
	 return json;
 }
 
 //画订单图
 function showOrdersEcharts(){
		var json = orderData();//获得订单数据
		
		var monthary = ['1','2','3','4','5','6','7','8','9','10','11','12'];
		var date = new Date();
		var thismonth = { "value":"","textStyle": ""};
		var textstyle = { "color":"red"}              
		thismonth.value = date.getMonth() + 1;
		thismonth.textStyle = textstyle;
		monthary.splice(date.getMonth(),1, thismonth);// 处理当前月份
		
		var incomeary = [];
		var count = 0;//总数
		var amount = 0;//除数
		$.each(json.income,function(i,o){ //处理收入数组
			incomeary[i] = o.amount;
			if(i==date.getMonth()){
				amount = o.amount; //获得当月收入
			}
			count += o.amount;//累计每月收入
		});
		//
		$(".stat-list .no-margins:eq(0)").text(amount.toLocaleString());
		$(".stat-list .stat-percent:eq(0)").text(Math.round(amount / count * 10000) / 100.00 + "%");
		$(".stat-list .progress-bar:eq(0)").width(Math.round(amount / count * 10000) / 100.00 + "%");
		var expenditureary = [];
		$.each(json.expenditure,function(i,o){ // 处理支出数据
			expenditureary[i] = o.amount;
		})
		var backary = [];
		$.each(json.back,function(i,o){ //处理返款数据
			backary[i] = o.amount;
			if(i==date.getMonth()){
				$(".stat-list .no-margins:eq(1)").text(o.amount.toLocaleString());
				$(".stat-list .stat-percent:eq(1)").text(Math.round(Math.abs(o.amount / amount) * 10000) / 100.00 + "%");
				$(".stat-list .progress-bar:eq(1)").width(Math.round(Math.abs(o.amount / amount) * 10000) / 100.00 + "%");
			}
		})
		var refundary = [];
		$.each(expenditureary,function(i,o){ // 处理退款数据
			refundary[i] = expenditureary[i] - backary[i];
			if(i==date.getMonth()){
				$(".stat-list .no-margins:eq(2)").text(refundary[i].toLocaleString());
				$(".stat-list .stat-percent:eq(2)").text(Math.round(Math.abs(refundary[i] / amount) * 10000) / 100.00 + "%");
				$(".stat-list .progress-bar:eq(2)").width(Math.round(Math.abs(refundary[i] / amount) * 10000) / 100.00 + "%");
			}
		})
		
		var t = echarts.init(document.getElementById("echarts-bar-chart")), n = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['收款金额','退款金额','返款金额']
		    },
		    toolbox: {
		        show : true,
		        feature : {
//		            mark : {show: true},
		            dataView : {show: true},
//		            magicType : {show: true, type: ['line', 'bar']},
		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    xAxis : [//x轴
		        {
		            type : 'category',
		            position: 'bottom',
		            boundaryGap: true,
		            axisLine : {    // 轴线
		                show: true,
		                lineStyle: {
		                    color: 'green',
		                    type: 'solid',
		                    width: 2
		                }
		            },
		            axisTick : {    // 轴标记
		                show:true,
		                length: 10,
		                lineStyle: {
		                    color: 'red',
		                    type: 'solid',
		                    width: 2
		                }
		            },
		            axisLabel : { //坐标轴文本标签
		                show:true,
		                interval: 'auto',    // {number}标签显示挑选间隔
//		                rotate: 30, //标签旋转角度默认0
		                margin: 8, //标签与坐标轴之间的间隙
		                formatter: '{value}月', //标签名称格式器
		                textStyle: {
		                    color: 'blue',
		                    fontFamily: 'sans-serif',
		                    fontSize: 15,
		                    fontStyle: 'italic',
		                    fontWeight: 'bold'
		                }
		            },
		            splitLine : { //分隔线
		                show:true,
		                lineStyle: {
		                    color: '#483d8b',
		                    type: 'dashed',
		                    width: 1
		                }
		            },
		            splitArea : { //分隔区域
		                show: true,
		                areaStyle:{
		                    color:['rgba(144,238,144,0.3)','rgba(135,200,250,0.3)']
		                }
		            },
		            data : monthary
		        }
		    ],
		    yAxis : [//y轴
		        {
		            type : 'value',
		            position: 'left',//纵轴标识位置
		            //min: 0,
		            //max: 300,
		            //splitNumber: 5,
		            axisLine : {    // 轴线
		                show: true,
		                lineStyle: {
		                    color: 'red',
		                    type: 'dashed',
		                    width: 2
		                }
		            },
		            axisTick : {    // 轴标记
		                show:true,
		                length: 10,
		                lineStyle: {
		                    color: 'green',
		                    type: 'solid',
		                    width: 2
		                }
		            },
		            axisLabel : {
		                show:true,
		                interval: 'auto',    // {number}
		                rotate: -45,
		                margin: 18,
		                formatter: '{value} ￥',    // Template formatter!
		                textStyle: {
		                    color: '#1e90ff',
		                    fontFamily: 'verdana',
		                    fontSize: 10,
		                    fontStyle: 'normal',
		                    fontWeight: 'bold'
		                }
		            },
		            splitLine : {
		                show:true,
		                lineStyle: {
		                    color: '#483d8b',
		                    type: 'dotted',
		                    width: 2
		                }
		            },
		            splitArea : {
		                show: true,
		                areaStyle:{
		                    color:['rgba(205,92,92,0.3)','rgba(255,215,0,0.3)']
		                }
		            }
		        }
		    ],
		    series : [
		        {
		            name: '收款金额',
		            type: 'bar',
		            data:incomeary
		        },
		        {
		            name: '退款金额',
		            type: 'line',
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data: refundary
		        },
		        {
		            name:'返款金额',
		            type: 'line',
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
//		            yAxisIndex: 1,
		            data: backary
		        }/*,
		        {
		            name:'最高气温',
		            type: 'line',
//		            xAxisIndex: 1,
		            yAxisIndex: 1,
		            data: [12.0, 12.2, 13.3, 14.5, 16.3, 18.2, 28.3, 33.4, 31.0, 24.5, 18.0, 16.2]
		        }*/
		    ]
		};
		t.setOption(n), window.onresize = t.resize;
 }