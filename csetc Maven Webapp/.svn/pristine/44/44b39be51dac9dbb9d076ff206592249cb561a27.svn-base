/**
 * 文件名: proofile.js
 * 描述:个人资料
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-06-15
 */
$(document).ready(function(){
	//查看个人资料
	userdatashow();
});

var profile;
//修改密码
function updPassword(){
	//验证两次输入的新密码是否一致
	var userpassword = $("#updPwdForm input[name='userpassword']").val();
	var password = $("#updPwdForm input[name='password']").val();
	if(password != userpassword){
		toastr.warning("两次密码输入不一致");
		return false;
	}
	if(userpassword.length<8){
		toastr.warning("密码长度不能少于8位");
		return false;
	}
	
	//修改密码操作
	$.ajax({
		type:"post",
		url:"../SysUser/updPwd.do",
		data:{"userpassword":userpassword,"oldpwd":$("#updPwdForm input[name='oldPwd']").val()},
		dataType:"json",
		async:false,
		success:function(data){
			if(data>0){
				toastr.success("修改密码成功,请用新的密码登录");
				//退出并跳到登录的页面
				window.location.href="../SysUser/loginout.do";
			}else{
				toastr.warning("原密码错误");
			}
			
		},
		error:function(){
			toastr.error("修改密码操作失败");
		}
	});
}

//查看个人资料
function userdata(){
	$.ajax({
		type:"post",
		url:"../SysUser/userdata.do",
		dataType:"json",
		async:false,
		success:function(data){
			profile = JSON.parse(data);
		},
		error:function(){
			toastr.error("获取数据失败");
		}
	});
}

//展示个人资料
function userdatashow(){
	userdata();
	var tabelhtml = '<table class="table table-bordered table-striped">';
	tabelhtml += "<tr><td>EHR编号</td><td>"+profile.userid+"</td></tr>";
	tabelhtml += "<tr><td>账号名</td><td>"+profile.loginname+"</td></tr>";
	tabelhtml += "<tr><td>用户名</td><td>"+profile.username+"</td></tr>";
	tabelhtml += "<tr><td>密码过期时间</td><td>"+new Date(profile.passexpire).format("yyyy-MM-dd")+"</td></tr>";
	tabelhtml += "<tr><td>身份证号</td><td>"+profile.cardid+"</td></tr>";
	tabelhtml += "<tr><td>手机号</td><td>"+profile.mobile+"</td></tr>";
	tabelhtml += "<tr><td>固定电话</td><td>"+profile.telephone+"</td></tr>";
	tabelhtml += "<tr><td>邮箱</td><td>"+profile.email+"</td></tr>";
	tabelhtml += "<tr><td>联系地址</td><td>"+profile.address+"</td></tr>";
	tabelhtml += "<tr><td>入职时间</td><td>"+new Date(profile.joinustime).format("yyyy-MM-dd")+"</td></tr>";
	tabelhtml += "<tr><td>最近登录时间</td><td>"+new Date(profile.lastlogintime).format("yyyy-MM-dd hh:mm:ss")+"</td></tr>";
	tabelhtml += "<tr><td>登录次数</td><td>"+profile.logincount+"</td></tr>";
	tabelhtml += "</table>";

	tabelhtml += "<div class='text-right'>";
	tabelhtml += "<input type='button' class='btn btn-primary' value='修改' onclick='updUserDataShow()' />";
	tabelhtml += "</div>";
	
	$("#userdatainfo").html(tabelhtml);
}


//修改个人资料展示
function updUserDataShow(){
	var formHtml = "";
	formHtml += '<form class="form-horizontal" id="updUserDataForm">'+
					'<input type="hidden" value="'+ profile.id +'" name="id" />'+
					'<div class="form-group">'+
						'<label class="col-sm-4 control-label">账号名：</label>'+
						'<div class="col-sm-6">'+
							'<input type="text" class="form-control input-sm" name="loginname" value="'+ profile.loginname +'"/>'+ 
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label class="col-sm-4 control-label">身份证号：</label>'+
						'<div class="col-sm-6">'+
							'<input type="text" class="form-control input-sm" name="cardid" value="'+ profile.cardid +'" /> '+
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label class="col-sm-4 control-label">手机号：</label>'+
						'<div class="col-sm-6">'+
							'<input type="text" class="form-control input-sm" name="mobile" value="'+ profile.mobile +'" required="required" />'+ 
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label class="col-sm-4 control-label">固定电话：</label>'+
						'<div class="col-sm-6">'+
							'<input type="text" class="form-control input-sm" name="telephone" value="'+ profile.telephone +'" />'+ 
						'</div>'+
					'</div>'+
					'<div class="form-group">'+ 
						'<label class="col-sm-4 control-label">邮箱：</label>'+
						'<div class="col-sm-6">'+
							'<input type="email" class="form-control input-sm" name="email" value="'+ profile.email +'" />'+ 
						'</div>'+
					'</div>'+
					'<div class="form-group">'+
						'<label class="col-sm-4 control-label">联系地址：</label>'+
						'<div class="col-sm-6">'+
							' <textarea rows="" cols="" class="form-control input-sm" name="address">'+ profile.address +'</textarea>'+
						'</div>'+
					'</div>'+
					'<div class="text-right">'+
				        '<input type="button" class="btn btn-default" value="取消" onclick="userdatashow()"/> '+
				        '<input type="button" class="btn btn-primary" value="确定" onclick="updUserData()" />'+
				    '</div>'+
				'</form>';
	
	$("#userdatainfo").html(formHtml);
	
}

//修改个人资料
function updUserData(){
	$.ajax({
		type:"post",
		url:"../SysUser/updateone.do",
		dataType:"json",
		data:$("#updUserDataForm").serialize(),
		async:false,
		success:function(data){
			toastr.success("修改信息成功");
			userdatashow();
		},
		error:function(){
			toastr.error("修改信息失败");
		}
	})
}