var basecode; //基础代码全局变量

$(document).ready(function(){
	basecode = getALLBasecodeByType();
})

//根据大类id查看所有小类（基础信息表）
function getSmallMaxIdOfBigId(bigid,selectStr,flowtype){
	$.ajax({
		type:"POST",
		url:"../basecode/getSmallMaxIdOfBigId.do",
		dataType:"json",
		async:false,
		data:{"bigid":bigid},
		success:function(data){
			var json = JSON.parse(data);
			$(selectStr).html("");
			 $.each(json,function(index, obj) {
				 var html = ""; 
				 if(obj.level2id == flowtype){
					 html = "<option selected value="+obj.level2id+">" +obj.level2name+ "</option>";
				 }else{
					 html = "<option value="+obj.level2id+">" +obj.level2name+ "</option>";
				 }
                 $(selectStr).append(html);  
             });		
		},error:function(){
			toastr.warning('数据加载失败');
		}
	});
}
//角色下拉框
function getAllRoleData(selectStr,roleid){
	
	$.ajax({//查看所有角色
		type:"POST",
		url:"../role/getAllRoleData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var sel = $(selectStr);
			$.each(sel,function(i,o){
				if($(this).children("option").size() <= 1 ){//如果该下拉框小于等于一个选项就为该下拉框赋新的选项
					var html = "<option>请选择</option>";			
					$.each(json,function(index, obj) {
						if(obj.status == "1"){
							if(roleid == obj.roleid){
								html += "<option selected value='" + obj.roleid + "'>" + obj.rolename + "</option>";   
							}else{
								html += "<option value='" + obj.roleid + "'>" + obj.rolename + "</option>";
							}
						}
		            });	
					$(this).append(html);  
				}
			});
		}
	});	
}

//可输入的员工下拉选项
function selectUser(conid,defaults){
	$.ajax({
		type:"POST",
		url:"../SysUser/deptUserData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			var array = [];
			
			$.each(json,function(i,o){
				//alert(o.username);
				var deptuser = {"id":"","word":"","description":""};
				deptuser.id = o.loginname;
				deptuser.word = o.username;
				deptuser.description = o.userid;
				if((typeof defaults) == "undefined" || defaults == ''){
					array.push(deptuser);
				}else if(defaults.indexOf(deptuser.id) >= 0 ){
					array.push(deptuser);
				}else if(defaults.indexOf(deptuser.description) >= 0){
					array.push(deptuser);
				}
				
			});
			$(conid).bsSuggest(
					{
					indexId:2,
					indexKey:1,
					data:{
						"value":array}
					});
			
		},error:function(){
			toastr.warning("出错啦");
		}
	});
}

//将数字转成相应的中文
function basecodeText(bigid,para){
	$.ajax({
		type:"POST",
		url:"../basecode/getSmallMaxIdOfBigId.do",
		dataType:"json",
		async:false,
		data:{"bigid":bigid},
		success:function(data){
			var json = JSON.parse(data);
			 $.each(json,function(index, obj) {
				 if(obj.level2id == para){
					 para =  obj.level2name;
				 }
             });		
		},error:function(){
			toastr.warning('基础数据加载失败');
		}
	});
	return para;
}

//将员工编号转成姓名
function useridToName(id){
	$.ajax({
		type:"POST",
		url:"../SysUser/getAllUserData.do",
		dataType:"json",
		async:false,
		success:function(data){
			var json = JSON.parse(data);
			 $.each(json,function(index, obj) {
				 if(obj.userid == id){
					 id = obj.username;
				 }
             });		
		},error:function(){
			toastr.warning('员工数据加载失败');
		}
	});
	return id;
}

//获得基础代码数据
function getALLBasecodeByType(){
	var json;
	$.ajax({
		type:"POST",
		url:"../basecode/getALLBasecodeByType.do",
		dataType:"json",
		async:false,
		success:function(data){
			json = JSON.parse(data);
		},
		error:function(){
			toastr.error("基础代码数据获取出错");
		}
	});	
	return json;
}

//填充下拉框数据
function fillselectData(property,selectStr,flowtype,pretendTitle){
	var json = basecode[property];
	if((typeof pretendTitle) != "undefined"){
		$(selectStr).html("<option value=''>"+ pretendTitle +"</option>");
	}else{
		$(selectStr).html("");
	}
	 $.each(json,function(index, obj) {
		 var html = ""; 
		 if(obj.level2id == flowtype){
			 html = "<option selected value="+obj.level2id+">" +obj.level2name+ "</option>";
		 }else{
			 html = "<option value="+obj.level2id+">" +obj.level2name+ "</option>";
		 }
         $(selectStr).append(html);  
     });		
}

//日期时间戳格式化
Date.prototype.format =function(format){
var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(), //day
	"h+" : this.getHours(), //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
	"S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4- RegExp.$1.length));
for(var k in o)if(new RegExp("("+ k +")").test(format))
	format = format.replace(RegExp.$1,RegExp.$1.length==1? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	return format;
}

//客户批注的各个阶段
var servicetype = {stage:'客户所属阶段的对应类型', stagesList:[    
{stage:'客户录入阶段', value:'1001', stageList:[{stage:'数据校验',value:'01'},{stage:'数据校验2',value:'02'},{stage:'数据校验3',value:'03'},{stage:'数据校验4',value:'04'}]},    
{stage:'客户分配后阶段', value:'1002', stageList:[{stage:'电话沟通',value:'01'},{stage:'电话咨询1',value:'02'},{stage:'电话咨询2',value:'03'},{stage:'电话咨询3',value:'04'}]},    
{stage:'预约客户阶段', value:'1003', stageList:[{stage:'电话沟通',value:'01'},{stage:'了解情况1',value:'02'},{stage:'了解情况2',value:'03'},{stage:'了解情况3',value:'04'}]},    
{stage:'回访客户阶段', value:'1004', stageList:[{stage:'面谈',value:'01'},{stage:'说服跟进2',value:'02'},{stage:'说服跟进3',value:'03'},{stage:'说服跟进4',value:'04'}]},    
{stage:'客户上门阶段', value:'1005', stageList:[{stage:'面谈',value:'01'},{stage:'接待',value:'02'},{stage:'分配',value:'03'},{stage:'流失',value:'04'}]},    
{stage:'签单阶段', value:'1006', stageList:[{stage:'面谈',value:'01'},{stage:'转化2',value:'02'},{stage:'转化3',value:'03'},{stage:'转化4',value:'04'}]},    
{stage:'收款阶段', value:'1007', stageList:[{stage:'收部分款',value:'01'},{stage:'收全款',value:'02'},{stage:'收现金',value:'03'},{stage:'转账',value:'04'}]},    
{stage:'退款阶段', value:'1008', stageList:[{stage:'退部分款',value:'01'},{stage:'退全款',value:'02'},{stage:'退款申请中',value:'03'},{stage:'退款申请成功',value:'04'}]},    
{stage:'返款阶段', value:'1009', stageList:[{stage:'返补贴',value:'01'},{stage:'返款申请中',value:'02'},{stage:'返款申请成功',value:'03'},{stage:'返款完成后',value:'04'}]},    
{stage:'学习阶段', value:'2001', stageList:[{stage:'预科',value:'01'},{stage:'考试',value:'02'},{stage:'访谈',value:'03'},{stage:'考勤',value:'04'}]},    
{stage:'工作阶段', value:'3001', stageList:[{stage:'在职',value:'01'},{stage:'离职',value:'02'},{stage:'换职业',value:'03'},{stage:'无业',value:'04'}]}   
]};

var addressInit = function(_cmbProvince, _cmbArea, defaultProvince, defaultArea)
{
    var cmbProvince = document.getElementById(_cmbProvince);
    var cmbArea = document.getElementById(_cmbArea);
    function cmbSelect(cmb, str)
    {
        for(var i=0; i<cmb.options.length; i++)
        {
            if(cmb.options[i].value == str)
            {
                cmb.selectedIndex = i;
                return;
            }
        }
    }
    function cmbAddOption(cmb, str, value, obj)
    {
        var option = document.createElement("OPTION");
        cmb.options.add(option);
        option.innerText = str;
        option.value = value;
        option.obj = obj;
    }
    function changeProvince()
    {
    	cmbArea.options.length = 0;
    	cmbArea.onchange = null;
        if(cmbProvince.selectedIndex == -1)return;
        var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
        for(var i=0; i<item.stageList.length; i++)
        {
            cmbAddOption(cmbArea, item.stageList[i].stage, item.stageList[i].value, item.stageList[i]);
        }
        cmbSelect(cmbArea, defaultArea);
    }
    
    for(var i=0; i<servicetype.stagesList.length; i++)
    {
    	if(servicetype.stagesList[i].value == defaultProvince){
    		cmbAddOption(cmbProvince, servicetype.stagesList[i].stage, servicetype.stagesList[i].value, servicetype.stagesList[i]);
    	}
        
    }
    cmbSelect(cmbProvince, defaultProvince);
    changeProvince();
    cmbProvince.onchange = changeProvince;
}

//根据基础代码获得小类id获得小类值
function selectText(property,levelid){
	var json = basecode[property];
	var result = "";
	 $.each(json,function(index, obj) {
		 var html = ""; 
		 if(obj.level2id == levelid){
			 result = obj.level2name;
			 return false;
		 }
     });	
	return result;
}

//根据出生年月计算年龄
function computeAge(startDate) {
	if((typeof startDate) == "undefined" || startDate == "" || startDate == null || startDate == "null"){
		return "-";
	}
    // 获得今天的时间
    var date = new Date();
    startDate = new Date(startDate);
    var newDate = date.getTime() - startDate.getTime();
    // 向下取整  例如 10岁 20天 会计算成 10岁
    // 如果要向上取整 计算成11岁，把floor替换成 ceil
    return Math.floor(newDate / 1000 / 60 / 60 / 24 / 365);
}

//检查某客户在某阶段是否有当前用户的批注信息
function checkRemark(cid,servicetype){
	var flag = true;
	$.ajax({
		type:"post",
		url:"../remark/checkRemark.do",
		dataType:"json",
		data:{"customerid":cid,"servicetype":servicetype},
		async:false,
		success:function(data){
			if(data == 0){
				flag = false;
			}
		},
		error:function(){
			toastr.error("获得批注数据失败");
		}
	});
	return flag;
}