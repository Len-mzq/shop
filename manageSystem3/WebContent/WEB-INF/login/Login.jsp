<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎登录</title>
<script src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript">
$().ready(function(){
	
	function clearMessage() {
		$("#message").html("");
	}
	
	$(".login-btn").click(function(){
		var username = $("#loginname").val();
		var password = $("#loginpsw").val();
		var verification = $("#loginver").val();//验证码
		//location.href = "user?type=doLogin&username="+username+"&password="+password;
		$.ajax({
			url:"user",
			type:"post",
			data:{type:"doLogin",username:username,password:password,verification:verification},
			dataType:"text",
			success:function(data){
				if(data=="true"){  
					location.href = "index"
				}else if(username.length == 0){
					//alert("<i></i>用户名不能为空！");
					$("#message").html("<i></i>用户名不能为空！");
					setTimeout(clearMessage, 3000);
				}else if(password.length == 0){
					//alert("密码不能为空！");
					$("#message").html("<i></i>密码不能为空！");
					setTimeout(clearMessage, 3000);
				}else if(verification.length == 0){
					//alert("验证码不能为空！");
					$("#message").html("<i></i>验证码不能为空！");
					setTimeout(clearMessage, 3000);
				}else if(data=="-1"){
					//alert("验证码不正确！");
					$("#message").html("<i></i>验证码不正确！");
					setTimeout(clearMessage, 3000);
				}else{
 					//alert("用户名或密码错误！");
 					$("#message").html("<i></i>用户名或密码错误！");
					setTimeout(clearMessage, 3000);
				}
			}
		})
	})
	
	$("#image").click(function(){
		$(this).attr("src","user?type=randomImage&"+Math.random());
	})
})
</script>
</head>
<body>
	<div id="content">
		<div class="login-wrap">
			<div class="w">
				<div class="login-form">
					<div class="tips-wrapper">
						<div class="tips-inner">
							<div class="cont-wrapper">
								<i class="icon-tips"></i>
								<p>我们不会以任何理由要您的账号密码，谨防诈骗。</p>
							</div>
						</div>
					</div>
					
					<div class="login-tab login-tab-r">
						<a class="checked">账户登陆</a>
					</div>
					<div id="message">
							
					</div>
					<div class="login-box" style="display: block; visibility: visible;">
						<div class="mc">
							<div class="form">
								<div id="formlogin">
									<div class="item item-fore1">
										<label for="loginname" class="login-label name-label"></label>
										<input type="text" name="loginname" id="loginname" class="itxt" autofocus required placeholder="邮箱/用户名/已验证手机"  value="${name}"/>
									</div>
									<div class="item item-fore1">
										<label for="loginpsw" class="login-label psw-label" id="psw-lable"></label> 
										<input type="password" name="loginpsw" id="loginpsw" class="itxt" required placeholder="密码" }/>
									</div>
									<div class="item item-fore1 item-forever">
										<label for="loginpsw" class="login-label psw-label" id="psw-lable"></label> 
										<input type="text" name="loginver" id="loginver" class="itxt itxtver" required placeholder="验证码" }/>
										
									</div>
									<div>
									<img id="image" src="user?type=randomImage"/>
									</div>
									
									<div class="forget-pw-safe">
										<a href="Fail.jsp">忘记密码</a>
									</div>
									<div class="item item-fore5">
										<div class="login-btn">
											<a class="btn-img btn-entry"
												id="loginsubmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										</div>
									</div>
									<div class="coagent">
										<ul>
											<li><b></b> <a class="pdl"
												onclick="window.location='//qq.jd.com/new/qq/login.aspx'+window.location.search;return false;"
												style=""> <b class="QQ-icon"></b> <span>QQ</span>
											</a> <span>&nbsp;&nbsp;|&nbsp;&nbsp;</span></li>
											<li><b></b> <a class="pdl"
												onclick="window.location='//qq.jd.com/new/wx/login.action'+window.location.search;return false;">
													<b class="weixin-icon"></b> <span>微信</span>
											</a></li>
											<li class="extra-r">
												<div class="regist-link">
													<a
														href="http://localhost:8080/manageSystem3/user?type=showRegister"
														style="outline: rgb(109, 109, 109) none 0px;"> <b></b>
														立即注册
													</a>
												</div>
											</li>
										</ul>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="login-banner">
				<div class="w">
					<div id="banner-bg" class="i-inner" style="background: url(img/login.jpg);"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>