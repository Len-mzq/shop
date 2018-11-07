<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品列表</title>
<link rel="stylesheet" href="iconfont.css/iconfont.css" />
<link rel="stylesheet" href="iconfont.css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/list.css" />
<script src="js/jquery.js"></script>
<style>
#group li:hover {
	color: red;
	cursor: pointer;
}

#j_feature .box:hover {
	color: red;
	cursor: pointer;
}

.goodslist .sp-list .gl-item .gl-i-wrap:hover {
	background-color: ghostwhite;
}

.pageMsg {
	float: left;
	margin-left: 140px;
	margin-bottom: 40px;
	line-height: 38px;
	font-size: 14px;
	color: #337ab7;
}

#pagination {
	font-size: 14px;
	left: 550px;
	position: relative;
}

.pagination>li>a, .pagination>li>span {
	position: relative;
	float: left;
	padding: 6px 12px;
	margin-left: -1px;
	line-height: 1.42857143;
	color: #337ab7;
	text-decoration: none;
	background-color: #fff;
	border: 1px solid #ddd;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	z-index: 3;
	color: #fff;
	cursor: default;
	background-color: #337ab7;
	border-color: #337ab7;
}

.page {
	margin-bottom: 10px;
}

#J_bottomPage {
	float: left;
    height: 50px;
    width: 1300px;
}
</style>
<script type="text/javascript">
	$().ready(function() {
		$(".button").click(function() {
			var name = $("#souText").val();
			location.href = "search.do?name=" + name;
		})
		 
		$("#tiaozhuan").click(function(){
			var pageNum = $ ("#sub").val();
			if(isNaN(parseInt(pageNum))||parseInt(pageNum)<=0){//获取的值pageNum 不是数字或者小于等于0的时候跳到第一页
				pageNum=1;
			}
			location.href = "search.do?pageNo="+pageNum;
		})
		
		$("#j-goodslist ul").click(function(){
 			var id = $(this).data("id");
			location.href = "showDetails.do?id="+id;
		})
		
		$("#shopCar").click(function(){
			if(${sessionScope.username==null}){        
				location.href="showLogin.do"; 
			}else{
				location.href="showCart.do";
			}
		})
		
	})
</script>
</head>

<body>
	<!--daoHangLan start-->
	<div id="daoHangLan">
		<ul class="c1">
			<li id="home"><i class=" iconfont icon-weibiaoti-"></i> <a
				href="//www.jd.com/" target="_blank">京东首页</a></li>
			<div id="mycity">
				<li style="float: left;" class="iconfont icon-dibiao"></li>
				<li style="float: left;">山东</li>
			</div>
		</ul>

		<ul class="c2">
			<li id="login"><a class="link-login" style="float: left;">你好，请登录</a>
				<a class="link-regist">免费注册</a></li>

			<li class="iconfont icon-shuxian" id="shuxian1"></li>
			<li id="dingdan"><a>我的订单</a></li>

			<li class="iconfont icon-shuxian" id="shuxian2"></li>
			<li id="myjd"><a>我的京东</a>
			<li class="iconfont icon-icon" id="xiangxia1"></li>
			</li>

			<li class="iconfont icon-shuxian" id="shuxian3"></li>
			<li id="member"><a>京东会员</a></li>

			<li class="iconfont icon-shuxian" id="shuxian4"></li>
			<li id="buy"><a>企业采购</a>
			<li class="iconfont icon-icon" id="xiangxia2"></li>
			</li>

			<li class="iconfont icon-shuxian" id="shuxian5"></li>
			<li id="servies"><a>客户服务</a>
			<li class="iconfont icon-icon" id="xiangxia3"></li>
			</li>

			<li class="iconfont icon-shuxian" id="shuxian6"></li>
			<li id="wangdao"><a>网站导航</a>
			<li class="iconfont icon-icon" id="xiangxia4"></li>
			</li>

			<li class="iconfont icon-shuxian" id="shuxian7"></li>
			<li id="phone"><a>手机京东</a></li>
		</ul>
	</div>

	<div class="w">
		<div id="logo-2018">
			<a href="//www.jd.com/" class="logo"> <img
				src="img/jdlogo-dog.png" /></a>
		</div>

		<div id="search">
			<div class="form">
				<input type="text" value="${pro.name}" id="souText" /> 
				<input type="button" value="搜索" class="button" />
			</div>
		</div>

		<div id="shopCar" class="dorpdown">
			<div class="sc">
				<i class="iconfont icon-shoppingcart-over"></i> <a target="_blank">我的购物车</a>
			</div>
		</div>

		<div id="hotewords" class="haveline">
			<a class="fore">篮球鞋京东自营</a> <b>|</b> <a>篮球鞋&nbsp;男</a> <b>|</b> <a>运动鞋</a>
			<b>|</b> <a>篮球</a> <b>|</b> <a>篮球服</a> <b>|</b> <a>欧文4</a>
		</div>
	</div>

	<div id="shoplist">
		<div class="w">
			<div id="categorys" class="dropdown">
				<li>全部商品分类</li>
			</div>
			<ul id="group">
				<li>京东服饰</li>
				<li>美妆馆</li>
				<li>超市</li>
				<li>生鲜</li>
				<li>全球购</li>
				<li>闪购</li>
				<li>拍卖</li>
				<li>金融</li>
			</ul>
		</div>
	</div>

	<div id="j_filter" class="filter">
		<div class="f-store">
			<div class="to">配送至</div>
		</div>
		<div id="j-store" style="float: left; margin-right: 10px;">
			<li id="address">山东青岛市城阳区城阳街道</li>
			<li class="iconfont icon-icon" id="xiangxia5"></li>
		</div>

		<div id="j_feature">
			<input type="checkbox" class="box" />京东物流 <input type="checkbox"
				class="box" />自营211 <input type="checkbox" class="box" />货到付款 <input
				type="checkbox" class="box" />仅显示有货 <input type="checkbox"
				class="box" />全球购 <input type="checkbox" class="box" />可配送全球 <input
				type="checkbox" class="box" />新品
		</div>
	</div>

	<c:forEach items="${pagevo.records}" var="pro">
		<div id="j-goodslist" class="goodslist">
			<ul data-id="${pro.id}" class="sp-list">
				<li class="gl-item">
					<div class="gl-i-wrap">
						<div class="p-img">
							<a target="_blank" title="${pro.title}" 
								onclick="searchlog(1,30065831253,0,2,'','flagsClk=1094713992')">
								<img width="220px" height="220px" src="${pro.imgs[0].path}">
							</a>
						</div>

						<div class="p-price">
							<em>￥</em> <i style="font-style: normal;" id="i-price">${pro.price}</i>
						</div>

						<div class="p-name">
							<a target="_blank" title="${pro.title}" 
								onclick="searchlog(1,30065831253,0,1,'','flagsClk=1094713992')">
								<em> ${pro.name} </em>
							</a>
						</div>

						<div class="p-commit">
							<a target="_blank" href="//item.jd.com/30065831250.html#comment"
								onclick="searchlog(1,30065831253,0,3,'','flagsClk=1094713992')">
								${pro.sales_volume}+</a>条评价
						</div>

						<span class="J_im_icon"> <a target="_blank"
							href="//mall.jd.com/index-23912.html" title="${pro.store}">${pro.store}</a>
						</span>

						<div class="p-icons">
							<i id="i1">${pro.icons}</i>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</c:forEach>

	<div id="page clearfix">
		<div id="J_bottomPage" class="p-wrap">
			<div class="pageMsg">
				第${pagevo.pageNo}页,共${pagevo.totalPageSize}页,${pagevo.totalRecords}条数据
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳到&nbsp; <input
					id="sub" type="text" size="3" /> &nbsp;页&nbsp;&nbsp;
				<button id="tiaozhuan">跳转</button>
			</div>

			<ul class="pagination" id="pagination">
				<!--如果当前页大于1，就显示上一页和首页 -->
				<c:if test="${pagevo.pageNo >1}">
					<li><a href="search.do?pageNo=${1}&name=${pro.name}">首页</a></li>
					<li><a
						href="search.do?pageNo=${pagevo.pageNo - 1}&name=${pro.name}">上一页</a></li>
				</c:if>

				<!--显示页数超链接 -->
				<!--如果总页数大于5页 -->
				<c:if test="${pagevo.totalPageSize > 5}">
					<!--如果当前页数大于等与1并小于等与3（这里表示点击前3页的链接，都显示的是1到5页的链接） -->
					<c:if test="${pagevo.pageNo>=1 && pagevo.pageNo<=3}">
						<c:forEach begin="${1 }" end="${5 }" varStatus="status">
							<li
								<c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if>>
								<a href="search.do?pageNo=${status.index}&name=${pro.name}">${status.index}</a>
							</li>
						</c:forEach>
					</c:if>

					<!--如果当前页数大于3，并且小于等与总页数减2；则循环显示当前页减2，到当前页加2的链接 -->
					<c:if
						test="${pagevo.pageNo > 3 && pagevo.pageNo <= pagevo.totalPageSize-2}">
						<c:forEach begin="${pagevo.pageNo-2 }" end="${pagevo.pageNo+2 }"
							varStatus="status">
							<li
								<c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if>>
								<a href="search.do?pageNo=${status.index}&name=${pro.name}">${status.index}</a>
							</li>
						</c:forEach>
					</c:if>

					<!--如果当前页大于总页数-2，并且小于总页数（意思就是点击最末尾的2个链接，就显示最后5个链接） -->
					<c:if
						test="${pagevo.pageNo > pagevo.totalPageSize-2 && pagevo.pageNo <= pagevo.totalPageSize-1}">
						<c:forEach begin="${pagevo.pageNo-3 }"
							end="${pagevo.totalPageSize}" varStatus="status">
							<li
								<c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if>>
								<a href="search.do?pageNo=${status.index}&name=${pro.name}">${status.index}</a>
							</li>
						</c:forEach>
					</c:if>
					<c:if
						test="${pagevo.pageNo > pagevo.totalPageSize-1 && pagevo.pageNo <= pagevo.totalPageSize}">
						<c:forEach begin="${pagevo.pageNo-4 }"
							end="${pagevo.totalPageSize}" varStatus="status">
							<li
								<c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if>>
								<a href="search.do?pageNo=${status.index}&name=${pro.name}">${status.index}</a>
							</li>
						</c:forEach>
					</c:if>
				</c:if>
				<!--如果总页数小于等与5就直接把所有链接循环输出。-->
				<c:if test="${pagevo.totalPageSize <= 5}">
					<c:forEach begin="${1}" end="${pagevo.totalPageSize}"
						varStatus="status">
						<li
							<c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if>>
							<a href="search.do?pageNo=${status.index}&name=${pro.name}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>

				<!--如果当前页小于总页数，就显示下一页和尾页 -->
				<c:if test="${pagevo.pageNo < pagevo.totalPageSize}">
					<li><a
						href="search.do?pageNo=${pagevo.pageNo + 1}&name=${pro.name}">下一页</a></li>
					<li><a
						href="search.do?pageNo=${pagevo.totalPageSize}&name=${pro.name}">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>