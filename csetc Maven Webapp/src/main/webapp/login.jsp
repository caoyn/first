<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico"> 
<link href="css/bootstrap.min14ed.css"  rel="stylesheet">
<link href="css/font-awesome.min93e3.css"  rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css" rel="stylesheet"><base target="_blank">
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css" rel="stylesheet">
<title>登录</title>
<script>
if(window.top !== window.self){
	 window.top.location = window.location;
}
</script>
</head>
<body  class="gray-bg">
	 <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div class="m-t">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名/EHR/姓名" required="" 
                    	id="username" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required=""
                    id="password" name="password" onkeydown="keyDown(event)"
                    >
                </div>
                <button class="btn btn-primary block full-width m-b" onclick="login()">登 录</button>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <!-- 通知插件 -->
    <script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>
    <script type="text/javascript">
     $(function() {
     
      	var messageOpts = {
    			closeButton : true, //是否显示关闭按钮
    			debug : false, //是否使用debug模式
    			positionClass: "toast-top-center",  
    			onclick : null,
    			showDuration : "400", //显示的动画时间
    			hideDuration : "1000", //消失的动画时间
    			timeOut: "1000", //展现时间
    			extendedTimeOut : "1000", //加长展示时间
    			showEasing : "swing", //显示时的动画缓冲方式
    			hideEasing : "linear", //消失时的动画缓冲方式
    			showMethod : "fadeIn", //显示时的动画方式
    			hideMethod : "fadeOut" //消失时的动画方式
    		};
    		toastr.options = messageOpts;//消息通知提示
      })
    
    
    function login(){
   		var username=document.getElementById("username").value;
   		var password=document.getElementById("password").value;  
   		var url='SysUser/Login.do';
   		$.ajax({
			type : 'POST',
    		url : url,
    		dataType :'json',
    		data : {
   					 "username" :username,
    				 "password":password
   			},
    		error: function(){
    			alert("出错啦");
    		},
    		success : function(data){
    			var status=null;
    			var obj=JSON.parse(data);
				for(var tmp in obj){
					status=obj.status;
				} 
				console.log(obj);
    			if(status == "success"){
    			 	 //toastr.success("登录成功");
    			 	 window.location.href="SysUser/index.do";//成功后跳转去首页的链接
                     return true;
                  }else if(status == "error1"){
                  			toastr.warning("密码错误，您还有两次机会重试");
                            return false;
                  }else if(status =="error2"){
                            toastr.warning("密码错误，您还有一次机会，如果密码继续输入错误即将锁定用户");
                            return false;
                  }else if(status =="error3"){
                            toastr.error("用户被锁定");
                            return false;
                  }else if(status =="error"){
                            toastr.error("用户被锁定");
                            return false;
                   }else if(status == "guoqi"){
                            toastr.error("密码过期了");
                            return false;
                   }
                   else{
                            return false;
                   }
                
    		}
    		});		
    }
    
    function keyDown(e) {//回车登录
	  var ev= window.event||e;
	  //13是键盘上面固定的回车键
	  if (ev.keyCode == 13) {
		//你要执行的方法
	   	login();
	  }
	 }
    </script>
</body>
</html>