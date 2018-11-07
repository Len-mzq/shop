<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品详情</title>
<link rel="stylesheet" href="iconfont.css/iconfont.css" />
<link rel="stylesheet" href="iconfont.css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/details.css" />
<script src="js/jquery.js"></script>
<style>
#group li:hover {
	color: red;
	cursor: pointer;
}

.wrap-input a:hover {
	cursor: pointer;
}

.item .activity-type .activity-summary a:hover {
	color: red;
}

.li .item .size {
	border: 1px solid red;
}
</style>
<script>
	$().ready(function() {

		$("#spec-list .lh img").click(function() {
			index = $(this).index();

			$("#spec-list .lh img").fadeOut();
			$("#spec-list .lh img").eq(index).fadeIn();

			$("#spec-list .lh img").removeClass("select");
			$("#spec-list .lh img").eq(index).addClass("select");
		})

		$(".btn-add").click(function() {
			var value = parseInt($("#buy-num").val()) + 1;
			$("#buy-num").val(value);
		})

		$(".btn-reduce").click(function() {
			var num = $("#buy-num").val()
			if (num > 1) {
				$("#buy-num").val(num - 1);
			}
		})

		$("#size .item a").click(function() {
			$("#size .item a").removeClass('size');
			$(this).addClass('size');
		})
		
		$("#dandu").click(function(){
			var productid = ${pro.id};
			var amount = $("#buy-num").val(); 
			var price= ${pro.price}; 
			var subtotal = amount*price;
			if(${sessionScope.username==null}){  
				location.href="showLogin.do"; 
			}else{ 
				location.href="addAndShowCart.do?product_id="+productid+"&amount="+amount+"&price="+price+"&subtotal="+subtotal;
			}
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
			<a href="//www.jd.com/" class="logo"><img
				src="img/jdlogo-dog.png" /></a>
		</div>

		<div id="search">
			<div class="form">
				<input type="text" id="souText" /> <input type="button" value="搜索"
					class="button" />
			</div>
		</div>

		<div id="shopCar" class="dorpdown">
			<div class="sc">
				<i class="iconfont icon-shoppingcart-over"></i> <a target="_blank">我的购物车</a>
			</div>
		</div>

		<div id="hotewords" class="haveline">
			<a class="fore">新品尚新</a> <b>|</b> <a>篮球鞋&nbsp;男</a> <b>|</b> <a>运动鞋</a>
			<b>|</b> <a>篮球</a> <b>|</b> <a>休闲鞋</a> <b>|</b> <a>AJ 11</a>
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

	<div class="crumb-wrap" id="crumb-wrap">
		<div class="w">
			<div class="crumb fl clearfix">
				<div class="item frist">
					<a href="//channel.jd.com/shoes.html" style="font-style: normal;">鞋靴</a>
				</div>
				<div class="iconfont icon-zuoyoujiantou"></div>
				<div class="item">
					<a href="//list.jd.com/list.html?cat=11729,11730">流行男鞋</a>
				</div>
				<div class="iconfont icon-zuoyoujiantou"></div>
				<div class="item">
					<a href="//list.jd.com/list.html?cat=11729,11730,6908"
						style="font-style: normal;">休闲鞋</a>
				</div>
				<div class="iconfont icon-zuoyoujiantou"></div>
				<div class="item">
					<a
						href="//list.jd.com/list.html?cat=11729,11730,6908&ev=exbrand_35247">其他品牌</a>
				</div>
				<div class="iconfont icon-zuoyoujiantou"></div>
				<div class="item">
					<a
						href="//list.jd.com/list.html?cat=11729,11730,6908&ev=exbrand_35247">${pro.name}</a>
				</div>
			</div>

			<div class="contact fr clearfix">
				<div class="item frist">
					<a href="//mall.jd.com/index-843554.html"
						style="font-style: normal;">${pro.store}</a> <a
						style="color: red;">-----&nbsp;&nbsp;</a> <a style="color: red;">☎</a>
					<a
						href="//list.jd.com/list.html?cat=11729,11730,6908&ev=exbrand_35247"
						style="font-style: normal;">留言咨询&nbsp;&nbsp;</a> <a
						style="color: red;">☆</a> <a
						href="//list.jd.com/list.html?cat=11729,11730,6908&ev=exbrand_35247"
						style="font-style: normal;">关注店铺</a>
				</div>
			</div>
		</div>
	</div>

	<div class="w">
		<div class="product-intro clearfix">
			<div class="preview-wrap">
				<div class="preview" id="preview">
					<div id="spec-n1" class="jqzoom main-img">
						<ul class="preview-btn J-preview-btn">

						</ul>
						<div id="box" style="position: relative;">
							<img id="spec-img" width="350" src="${pro.imgs[0].path }" />
						</div>
					</div>

					<div class="spec-items" id="spec-list">
						<ul class="lh">
							<li><img src="${pro.imgs[1].path }" width="50" height="50" />
							</li>
							<li><img src="${pro.imgs[2].path }" width="50" height="50" />
							</li>
							<li><img src="${pro.imgs[3].path }" width="50" height="50" />
							</li>
							<li><img src="${pro.imgs[4].path }" width="50" height="50" />
							</li>
							<li class="img-hover"><img src="${pro.imgs[5].path }"
								width="50" height="50" /></li>
						</ul>
					</div>

					<div class="preview-info">
						<div class="left-btns">
							<a class="follow J-follow"> 举报 </a>
						</div>
						<div class="right-btns">
							<a class="follow J-follow"> <em>❤</em> <em>关注 </em> <em>☍</em>
								<em>分享</em>
							</a>
						</div>
					</div>

				</div>

			</div>

			<div class="itemInfo-wrap">
				<div class="sku-name">${pro.name}</div>
				<div class="news">
					<div class="iten hide" id="p-ad" title="★参与拼团也可发货★即刻下单即刻发货★退换无忧 ★">
						★参与拼团也可发货★即刻下单即刻发货★退换无忧 ★</div>
				</div>
				<div id="pingou-banner-new" class="activity-banner">
					<div class="activity-type">
						<li class="iconfont icon-ren" style="font-size: 25px;"></li> <strong>&nbsp;京东团购</strong>
					</div>
				</div>
				<div id="summary summary-first" class="activity-summary">
					<div class="activity-type">
						<li style="color: red; float: left;">2&nbsp;&nbsp;</li>
						<li>人 拼&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li style="color: red; font-size: 20px;">￥${pro.price}&nbsp;&nbsp;</li>
						<li style="text-decoration: line-through;">[￥${pro.price+128}]</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;</li> <a>降价通知</a>
						<li id="pingjia">| 累计评价:${pro.sales_volume}+</li>
					</div>
				</div>
				<div class="summary p-choose-wrap">
					<div class="summary-stock">
						<div class="dt">配 送 至&nbsp;</div>
						<div class="ui-area-text-wrap">
							<div class="ui-area-text">
								山东青岛市城阳区城阳街道 <b class="iconfont icon-icon" id="jiantou"></b>
							</div>
						</div>
						<div class="dh">有货</div>
						<div class="dm">
							免运费 <a class="iconfont icon-wenhao" id="wenhao" title="了解配送费收取标准"
								href="//help.jd.com/user/issue/109-188.html"></a>
						</div>
					</div>
				</div>

				<div class="li" id="summary-supply">
					<div class="dt"></div>
					<div class="dd">
						<li>由&nbsp;</li>
						<li style="color: red;">${pro.store}</li>
						<li>&nbsp;负责发货, 并提供售后服务.</li>
					</div>
				</div>

				<div class="summary-line"></div>
				<div id="choose-attrs">
					<div id="choose-attr-2" class="li p-choose">
						<div>
							<br />
						</div>
						<div class="dt" style="position: relative; top: 8px;">选择尺码</div>
						<div class="dd" id="size">
							<div class="item" title="更大码" data-value="更大码">
								<b></b> <a>更大码</a>
							</div>
							<div class="item" title="36" data-value="36">
								<b></b> <a>36</a>
							</div>
							<div class="item" title="37" data-value="37">
								<b></b> <a>37</a>
							</div>
							<div class="item" title="38" data-value="38">
								<b></b> <a>38</a>
							</div>
							<div class="item" title="39" data-value="39">
								<b></b> <a>39</a>
							</div>
							<div class="item" title="40" data-value="40">
								<b></b> <a>40</a>
							</div>
							<div class="item" title="41" data-value="41">
								<b></b> <a>41</a>
							</div>
							<div class="item" title="42" data-value="42">
								<b></b> <a>42</a>
							</div>

							<div class="item" title="43" data-value="43">
								<b></b> <a>43</a>
							</div>
							<div class="item" title="44" data-value="44">
								<b></b> <a>44</a>
							</div>
							<div class="item" title="45" data-value="45">
								<b></b> <a>45</a>
							</div>
							<div class="item" title="36.5" data-value="36.5">
								<b></b> <a>36.5</a>
							</div>
							<div class="item" title="37.5" data-value="37.5">
								<b></b> <a>37.5</a>
							</div>
							<div class="item" title="41.5" data-value="41.5">
								<b></b> <a>4.15</a>
							</div>
							<div class="item" title="42.5" data-value="42.5">
								<b></b> <a>42.5</a>
							</div>
							<div>
								<br />
							</div>
							<div>
								<br />
							</div>
							<div>
								<br />
							</div>
							<div class="dt" style="position: relative; left: -67px;">增值保障</div>
							<div class="dt" style="position: relative; left: -67px;">
								<input type="checkbox" style="position: relative; top: 2.5px;" />鞋靴奢护￥159&nbsp;&nbsp;
								<input type="checkbox" style="position: relative; top: 2.5px;" />首月意外保￥9.9
							</div>

							<div class="summary-line"></div>
							<div class="choose-btns clearfix" id="choose-btns">
								<div class="choose-amount " id="amount">
									<div class="wrap-input">
										<input type="text buy-num" id="buy-num" value="1" data-max="200" />
										 <a class="btn-reduce">-</a>
										  <a class="btn-add">+</a>
									</div>

								</div>
								<div id="tuangou">
									<span>￥${pro.price}</span><br /> <strong>我要开团</strong>
								</div>
								<div id="dandu">
									<strong>加入购物车</strong>
								</div>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${sessionScope.username==null }"> --%>
<!-- 										<a href="showLogin.do"> -->
<!-- 											<div id="dandu"> -->
<!-- 												<strong>加入购物车</strong> -->
<!-- 											</div> -->
<!-- 										</a> -->
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<%-- 										<a href="addAndShowCart.do?proId="+${pro.id}> --%>
<!-- 											<div id="dandu" class="cart">  -->
<!-- 												<strong>加入购物车</strong> -->
<!-- 											</div> -->
<!-- 										</a> -->
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="detail">
		<div class="ETab" id="detail">
			<div class="tab-main large">
				<ul style="float: left;">
					<li class="current">商品介绍</li>
					<li class="current">规格与包装</li>
					<li class="current">质检报告</li>
					<li class="current">售后保障</li>
					<li class="current">本店好评商品</li>
				</ul>
			</div>
		</div>

		<div id="shangpinxinxi">
			<img src="${pro.imgs[6].path }" />
		</div>
		<div>
			<img src="${pro.imgs[7].path }" /> <img src="${pro.imgs[8].path }" />
			<img src="${pro.imgs[9].path }" /> <img src="${pro.imgs[10].path }" />
			<img src="${pro.imgs[11].path }" /> <img src="${pro.imgs[12].path }" />
			<img src="${pro.imgs[13].path }" /> <img src="${pro.imgs[14].path }" />
			<img src="img/shouhou.png" id="shouhou" /> <img
				src="img/banquan.png" id="banquan" /> <img src="img/end.png"
				id="end" />
		</div>
	</div>

</body>
</html>