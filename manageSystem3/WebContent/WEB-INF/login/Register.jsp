<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人注册</title>
<script src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/register.css" />
<script type="text/javascript">

$().ready(function(){
	
	function clearMessage() {
		$("#message").html("");
	}
	
	$("#sure").click(function(){
		var username = $("#registername").val();
		var password = $("#registerpsw").val();
		var password2 = $("#registerpsw2").val();
		if(password==password2){
			$.ajax({
				url:"user",
				type:"post",
				data:{type:"doRegister",username:username,password:password,password2:password2},
				dataType:"text",
				success:function(data){
					if(data=="username"){  
						//alert("账号不能为空！");
						$("#message").html("<i></i>用户名不能为空！");
						setTimeout(clearMessage, 3000);
					}else if(data=="password"){  
						//alert("密码不能为空！");
						$("#message").html("<i></i>密码不能为空！");
						setTimeout(clearMessage, 3000);
					}else if(data=="1"){  
						//alert("用户名已存在！");
						$("#message").html("<i></i>用户名已存在！");
						setTimeout(clearMessage, 3000);
					}else if(data=="true"){  
						location.href = "user"
					}
				}
			})
		}else{
			//alert("两次输入密码不一致！");
			$("#message").html("<i></i>两次输入密码不一致！");
			setTimeout(clearMessage, 3000);
		}
		
	})
})

</script>
</head>
<body>
		<div class="header">
			<div class="logo-title">欢迎注册</div>
			<div class="have-account">
				已有账号？
				<a href="http://localhost:8080/manageSystem3/user">请登录></a>
			</div>
		</div>
		<div class="main ">
			<div id="register-form">
				<div id="message">
						
				</div>
				<div class="form-item">
					<label>账号：</label>
					<input type="text" id="registername"/>
				</div>
				<div class="form-item">
					<label>密码：</label>
					<input type="password" id="registerpsw"/>
				</div>
				<div class="form-item">
					<label>确认密码：</label>
					<input type="password" id="registerpsw2"/>
				</div>
				<br />
				<input type="button" class="btn-register" id="sure" value="确认" />
			</div>
			<div class="reg-other clearfix">
				<a class="reg-other-item reg-other-person">
				<i class="reg-ohter-icon"></i>
				企业用户注册
				</a>
				<a class="reg-other-item reg-other-inter">
				<i class="reg-ohter-icon"></i>
				国际站注册
				</a>
			</div>
		</div>
		
		<div id="footer-2018">
			<div class="links">
				<a rel="nofollow" target="_blank" href="//www.jd.com/intro/about.aspx" style="outline: rgb(109, 109, 109) none 0px;">
					关于我们
				</a>|
				<a rel="nofollow" target="_blank" href="//www.jd.com/contact/" style="outline: rgb(109, 109, 109) none 0px;">
					联系我们
				</a>|
				<a rel="nofollow" target="_blank" href="//zhaopin.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					人才招聘
				</a>|
				<a rel="nofollow" target="_blank" href="//www.jd.com/contact/joinin.aspx" style="outline: rgb(109, 109, 109) none 0px;">
					商家入驻
				</a>|
				<a rel="nofollow" target="_blank" href="//www.jd.com/intro/service.aspx" style="outline: rgb(109, 109, 109) none 0px;">
					广告服务
				</a>|
				<a rel="nofollow" target="_blank" href="//app.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					手机思途
				</a>|
				<a rel="nofollow" target="_blank" href="//club.jd.com/links.aspx" style="outline: rgb(109, 109, 109) none 0px;">
					友情链接
				</a>|
				<a rel="nofollow" target="_blank" href="//media.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					销售联盟
				</a>|
				<a rel="nofollow" target="_blank" href="//media.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					思途社区
				</a>|
				<a rel="nofollow" target="_blank" href="//media.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					思途公益
				</a>|
			</div>
			<div id="copyright">
				Copyright © 2004-2018 思途ST.com 版权所有
			</div>
		</div>
</html>