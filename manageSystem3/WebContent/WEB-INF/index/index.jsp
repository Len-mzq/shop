<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理系统</title>
<link rel="stylesheet" href="iconfont.css/iconfont.css" />
<link rel="stylesheet" href="iconfont.css/demo.css" />
<style type="text/css">
#right {
	height: 600px;
	width: 1000px; float：left;
	margin-left: 80px;
}

#left {
	height: 600px;
	width: 250px;
	float: left;
	text-align: center;
	background-color: #c0c8df;
}

#top, #bottom {
	clear: both;
	background: #eaeaea;
	height: 100px;
}

#top {
	height: 80px;
	font-size: 28px;
	text-align: center;
	
}

#top #life{
text-align: right;
font-size: 23px;
margin-right: 60px;
}
#top #count{
float:left;
text-align: left;
font-size: 13px;
margin-left: 60px;
}
.menu {
	height: 40px;
	width: 150px;
	background: #eaeaea;
	margin-top: 20px;
	text-align: center;
	line-height: 40px;
	border-radius: 5px;
	font-size: 18px;
	margin-left: 50px;
	cursor: pointer;
}

a {
	text-decoration: none;
}

.er li {
	height: 25px;
	width: 100px;
	background: #eaeaea;
	margin-top: 5px;
	text-align: center;
	line-height: 25px;
	border-radius: 5px;
	font-size: 14px;
	margin-left: 70px;
	cursor: pointer;
}

#footer-2018 {
	padding-bottom: 30px;
	text-align: center;
	position: relative;
	top: 25px;
}

#footer-2018 .copyright {
	margin: 10px 0;
}

#copyright {
	font-size: 12px;
	position: relative;
	top: 15px;
}

#footer-2018 .links a, #footer-2018 .links span {
	margin: 0 10px;
	font-size: 15px;
}

#footer-2018 .links a:hover {
	color: red;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".menu").click(function() {
			$(this).next().slideToggle();

		})
	})
</script>
</head>
<body>
	<div id="container">
		<div id="top">
			员工管理系统<br />
			<li id="count">	本网站共有<%=application.getAttribute("num")%>人访问</li> 
			<li id="life">--让努力感动生命</li>
		</div>
		<div id="main">
			<div id="left">
				<div class="menu">
					<i class=" iconfont icon-yuangong"></i>&nbsp;员工管理
				</div>
				<ul class="er">
					<li><a href="employee" target="right">管理员工</a></li>
					<li><a href="employee?type=showAdd" target="right">添加员工</a></li>
				</ul>
				<div class="menu">
					<i class=" iconfont icon-bumen"></i>&nbsp;部门管理
				</div>
				<ul class="er">
					<li><a href="department" target="right">管理部门</a></li>
					<li><a href="department?type=showAdd" target="right">添加部门</a></li>
				</ul>
				<div class="menu">
					<i class=" iconfont icon-xiangmu"></i>&nbsp;项目管理
				</div>
				<ul class="er">
					<li><a href="project" target="right">管理项目</a></li>
					<li><a href="project?type=showAdd" target="right">添加项目</a></li>
				</ul>
				<div class="menu">
					<i class=" iconfont icon-jixiao"></i>&nbsp;绩效管理
				</div>
				<ul class="er">
					<li><a href="score" target="right">管理绩效</a></li>
					<li><a href="score?type=manage" target="right">修改绩效</a></li>
				</ul>
			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0"
				src="employee"></iframe>
		</div>
		<div id="bottom">
			<div id="footer-2018">
				<div class="links">
					<a rel="nofollow" target="_blank"
						href="//www.jd.com/intro/about.aspx"
						style="outline: rgb(109, 109, 109) none 0px;"> 关于我们 </a>| <a
						rel="nofollow" target="_blank" href="//www.jd.com/contact/"
						style="outline: rgb(109, 109, 109) none 0px;"> 联系我们 </a>| <a
						rel="nofollow" target="_blank" href="//zhaopin.jd.com/"
						style="outline: rgb(109, 109, 109) none 0px;"> 人才招聘 </a>| <a
						rel="nofollow" target="_blank"
						href="//www.jd.com/contact/joinin.aspx"
						style="outline: rgb(109, 109, 109) none 0px;"> 学生报名 </a>| <a
						rel="nofollow" target="_blank"
						href="//www.jd.com/intro/service.aspx"
						style="outline: rgb(109, 109, 109) none 0px;"> 广告服务 </a>| <a
						rel="nofollow" target="_blank" href="//app.jd.com/"
						style="outline: rgb(109, 109, 109) none 0px;"> 手机思途 </a>| <a
						rel="nofollow" target="_blank" href="//club.jd.com/links.aspx"
						style="outline: rgb(109, 109, 109) none 0px;"> 友情链接 </a>| <a
						rel="nofollow" target="_blank" href="//media.jd.com/"
						style="outline: rgb(109, 109, 109) none 0px;"> 销售联盟 </a>| <a
						rel="nofollow" target="_blank" href="//media.jd.com/"
						style="outline: rgb(109, 109, 109) none 0px;"> 思途社区 </a>| <a
						rel="nofollow" target="_blank" href="//media.jd.com/"
						style="outline: rgb(109, 109, 109) none 0px;"> 思途公益 </a>|
				</div>
				<div id="copyright">Copyright © 2004-2018 思途st.com 版权所有</div>
			</div>
		</div>
	</div>
</body>
</html>