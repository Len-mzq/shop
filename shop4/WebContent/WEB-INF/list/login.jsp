<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>京东-欢迎登录</title>
<script src="js/jquery.js"></script>
<link rel="stylesheet" href="iconfont.css/iconfont.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript">
$().ready(function(){
	
	$(".login-btn").click(function(){
		var username = $("#loginname").val();
		var password = $("#loginpsw").val();
		//location.href="doLogin.do?username="+username+"&password="+password; 
		$.ajax({
			url:"doLogin.do",
			type:"post",
			data:{username:username,password:password},
			dataType:"text",
			success:function(data){
				if(data=="true"){  
					location.href = "showIndex.do"
				}else if(username.length == 0){
					alert("用户名不能为空！");
					location.href = "showLogin.do"
// 					$("#message").html("<i></i>用户名不能为空！");
// 					setTimeout(clearMessage, 3000);
				}else if(password.length == 0){
					alert("密码不能为空！");
					location.href = "showLogin.do"
// 					$("#message").html("<i></i>密码不能为空！");
// 					setTimeout(clearMessage, 3000);
				}else{
 					alert("用户名或密码错误！");
 					location.href = "showLogin.do"
//  				$("#message").html("<i></i>用户名或密码错误！");
// 					setTimeout(clearMessage, 3000);
				}
			}
		})
	})
	
})
</script>
</head>
<body>
		<div class="w">
			<div id="logo">
				<a href="//www.jd.com/"><img src="//misc.360buyimg.com/lib/img/e/logo-201305-b.png" /></a>
				<b></b>
			</div>
			<a href="//surveys.jd.com/index.php?r=survey/index/sid/568245/lang/zh-Hans" target="_blank" class="q-link" style="outline: rgb(109, 109, 109) none 0px;">
				<b></b> 登录页面,调查问卷
			</a>
		</div>

		<div id="content">
			<div class="tips-wrapper">
				<div class="tips-inner">
					<div class="cont-wrapper">
						<i class="icon-tips"></i>
						<p>依据《网络安全法》，为保障您的账户安全和正常使用，请尽快完成手机号验证！ 新版
							<a href="https://about.jd.com/privacy/" class="block" target="_blank">《京东隐私政策》</a>
							已上线，将更有利于保护您的个人隐私。
						</p>
					</div>
				</div>
			</div>
			<div class="login-wrap">
				<div class="w">
					<div class="login-form">
						<div class="tips-wrapper">
							<div class="tips-inner">
								<div class="cont-wrapper">
									<i class="icon-tips"></i>
									<p>京东不会以任何理由要求您转账汇款，谨防诈骗。</p>
								</div>
							</div>
						</div>
						<div class="login-tab login-tab-l">
							<a>扫码登陆</a>
						</div>
						<div class="login-tab login-tab-r">
							<a class="checked">账户登陆</a>
						</div>
						<div class="login-box" style="display: block; visibility: visible;">
							<div class="mc">
								<div class="form">
									<div id="formlogin">
										<div class="item item-fore1">
											<label for="loginname" class="login-label name-label"></label>
											<input type="text" name="loginname" id="loginname" class="itxt" autofocus required placeholder="邮箱/用户名/已验证手机"  />
										</div>
										<div class="item item-fore1">
											<label for="loginpsw" class="login-label psw-label" id="psw-lable"></label>
											<input type="password" name="loginpsw" id="loginpsw" class="itxt" required placeholder="密码" />
										</div>
										<div class="forget-pw-safe">
											<a href="-------">忘记密码</a>
										</div>
										<div class="item item-fore5">
											<div class="login-btn">
												<a class="btn-img btn-entry" id="loginsubmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
											</div>
										</div>
										<div class="coagent">
											<ul>
												<li>
													<b></b>
													<a class="pdl" onclick="window.location='//qq.jd.com/new/qq/login.aspx'+window.location.search;return false;" style="window.location='//qq.jd.com/new/qq/login.aspx'+window.location.search;return false;">
														<b class="QQ-icon"></b>
														<span>QQ</span>
													</a>
													<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
												</li>
												<li><b></b>
													<a class="pdl" onclick="window.location='//qq.jd.com/new/wx/login.action'+window.location.search;return false;">
														<b class="weixin-icon"></b>
														<span>微信</span>
													</a>
												</li>
												<li class="extra-r">
													<div class="regist-link">
														<a href="http://localhost:8080/shop4/showRegister.do" target="_blank" style="outline: rgb(109, 109, 109) none 0px;">
															<b></b> 立即注册
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
				<div class="login-banner" style="background-color: #fe1f61">
					<div class="w">
						<div id="banner-bg" class="i-inner" style="background: url(img/5b727e9bN54b0d14b.jpg);"></div>
					</div>
				</div>
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
					手机京东
				</a>|
				<a rel="nofollow" target="_blank" href="//club.jd.com/links.aspx" style="outline: rgb(109, 109, 109) none 0px;">
					友情链接
				</a>|
				<a rel="nofollow" target="_blank" href="//media.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					销售联盟
				</a>|
				<a rel="nofollow" target="_blank" href="//media.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					京东社区
				</a>|
				<a rel="nofollow" target="_blank" href="//media.jd.com/" style="outline: rgb(109, 109, 109) none 0px;">
					京东公益
				</a>|
			</div>
			<div id="copyright">
				Copyright © 2004-2018 京东JD.com 版权所有
			</div>
		</div>
	</body>
</html>