/**
 * JS名: department.js </br>
 * 描述:部门管理处理文件</br>
 * 所属:长沙中软计算机系统服务有限公司</br>
 * 开发人员：王梓 </br>
 * 创建时间：2017-04-17 </br>
 */
/*-----------------------------------相关参数--------------------------------------------*/
//部门负责人下拉框中的数据
//var testdataBsSuggest=$("#test_data").bsSuggest(
//	{
//	indexId:2,
//	indexKey:1,
//	data:{
//		"value":
//		[
//		{"id":"0","word":"余舟","description":"总经理"},
//		{"id":"1","word":"赖伟","description":"副总经理"},
//		{"id":"2","word":"罗玉凤","description":"副总经理"},
//		{"id":"3","word":"青旻升","description":"技术总监"}
//		],
//		"defaults":"余舟"}
//	});
//部门树的json数据
var t=[
{
	text:"长沙市中软计算机培训中心",
	href:"changshaETC",
	nodes:[
	{
		text:"人力资源部",
		href:"renli"
		
	},{
		text:"总经办部",
		href:"zongjingban"
	},{
		text:"财务部",
		href:"caiwu"
	},{
		text:"行政部",
		href:"xingzheng"
	},{
		text:"销售部",
		href:"xiaoshou",
		nodes:[
		{
			text:"社招部",
			href:"shezhao",
			nodes:[
			{
				text:"社招一部",
				href:"shezhao1",
			},{
				text:"社招二部",
				href:"shezhao2",
			}]
		},{
			text:"校招部",
			href:"xiaozhao",
		}]
	},{
		text:"研发部",
		href:"yanfa"
	},{
		text:"市场部",
		href:"shichang"
	}]
},{
	text:"南昌市中软计算机培训中心",
	href:"parent2",
}];
//选中的部门
var chooseDepartment;
/*-----------------------------------页面加载处理----------------------------------------*/
$(function () {
	//画出部门树
	/*$("#treeview12").treeview(
		{
			color:"#428bca",
			data:t,
			onNodeSelected:function(event,data){
		     	//当内容被选中时发生事件
		     	console.log(data);
		     	console.log(data.text);
	     		chooseDepartment = data.text;//或者:chooseDepartment = data.href;
  			}
		});
*/
});
/*-----------------------------------实例化类函数----------------------------------------*/
/*-----------------------------------操作相关函数----------------------------------------*/
/*-----------------------------------辅助工具函数----------------------------------------*/
/*-----------------------------------数据加载函数----------------------------------------*/

