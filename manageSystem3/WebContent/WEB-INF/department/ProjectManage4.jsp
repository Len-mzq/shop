<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<style type="text/css"> 
#main {
	width: 600px;
	margin: 20px auto;
}

#title {
	font-size: 25px;
	list-style: none;
	margin-bottom: 20px;
	text-align: center;
}
#project,#projectNo{
	border: 1px solid #ccc;
    border-radius: 6px;
	height:200px;
	font-size: 20px;
}
 .pro{
    float: left;
    list-style: none;
    font-size: 20px;
    margin-left: 25px;
    background: ghostwhite;
    margin-top: 10px;
    cursor: pointer;
    border-radius: 5px;
}
#btn{
font-size: ;
text-align: center;
margin: 20px 40px 7px 40px;
}

#Add,#Delete{
margin:0 70px;
}
#main .hint{
list-style: none;
font-size: large;
color: darkslateblue;
}
.select{
	background: #ccc;
	cursor: pointer;
}
</style>

<script type="text/javascript">
	$().ready(function() {

			// 当点击另外一个列表中的item时当前列表取消所有选中
			$(".pro").click(function() {
				var parent = $(this).parent().prop('id');
				var select = $(".select");
				select.each(function() {
					if($(this).parent().prop('id') != parent) {
						$(this).removeClass("select");
					}
				})
				$(this).toggleClass("select");
			})

			$("#Delete").click(function() {
				if ($("#project").find(".select").length > 0) {
					var msg = "您真的确定要移除吗？";
					if (confirm(msg) == true) {
						var proIds = "";
						 $("#project").find(".select").each(function(){
							proIds += $(this).data("id")+",";
						 })
						 proIds = proIds.substring(0,proIds.length-1);//把最后一个逗号去掉 
						$.ajax({
							url : "department",
							type : "post",
							data : {
									type : "deletePro2",
									selectId : proIds,//选择的项目id 
									depName : "${depName}"//选择的部门的名
									},
							dataType : "text",
							success : function(data) {
								if (data == "true") {
									var pro = $("#project").find(".select");
									$("#projectNo").append(pro);
									pro.removeClass("select");
								}
							}
						})
					}
				} else {
					alert("请至少选中一条数据！");
				}
			})

			$("#Add").click(function() {
				if($("#projectNo").find(".select").length>0){
					var proIds = "";
						 $("#projectNo").find(".select").each(function(){
							 proIds += $(this).data("id")+",";//选择的项目id
						 })
					$.ajax({
						url : "department",
						type : "post",
						data : {
							type : "addProToDep2", 
							selectId : ${d_id},//选择的部门的id       
							proId : proIds//选择的项目名id 
						},
						dataType : "text",
						success : function(data) { 
							if (data == "true") {
								var pro = $("#projectNo").find(".select");
								$("#project").append(pro);
								pro.removeClass("select");
							}
						}
  
					}) 
				}else{
					alert("请选中一条数据！");
				}
			});	
			
			var proIds = "";
			// 设置列表中item可调换位置
			$("ul").sortable({
				revert: true
			});
			
			// 设置project列表中item可拖拽
			$("#project .pro").draggable({
				connectToSortable: "ul",
				revert: "invalid", // 当未被放置指定位置时，条目会还原回它的初始位置
				stop:function(){
					//删除
					proIds = $(this).data("id")+",";//选择的项目id
					proIds = proIds.substring(0,proIds.length-1);//把最后一个逗号去掉 
					$.ajax({
						url : "department",
						type : "post",
						data : {
								type : "deletePro2",
								selectId : proIds,//选择的项目id 
								depName : "${depName}"//选择的部门的名
								},
						dataType : "text",
						success : function(data) {
							if (data == "true") {
								$(".pro").removeClass("select")// 当item被拖拽以后取消选中
								//location.reload(true);//刷新页面
							}
						}
					})
				}
			});
			
			// 设置projectNo列表中item可拖拽
			$("#projectNo .pro").draggable({
				connectToSortable: "ul",
				revert: "invalid", // 当未被放置指定位置时，条目会还原回它的初始位置
				stop:function(){
					//增加
					proIds = $(this).data("id")+",";//选择的项目id
					$.ajax({
						url : "department",
						type : "post",
						data : {
							type : "addProToDep2",  
							selectId : ${d_id},//选择的部门的id    
							proId : proIds//选择的项目名id 
						},
						dataType : "text",
						success : function(data) { 
							if (data == "true") {
								$(".pro").removeClass("select")// 当item被拖拽以后取消选中
								//location.reload(true);//刷新页面 
							}
						}

					})
				}
			});
			
// 			//拖拽删除 
// 			$("#project .pro").on("dragstop", function(event, ui) {
// 				proIds += $(this).data("id")+",";//选择的项目id
// 				proIds = proIds.substring(0,proIds.length-1);//把最后一个逗号去掉 
// 					$.ajax({
// 						url : "department",
// 						type : "post",
// 						data : {
// 								type : "deletePro2",
// 								selectId : proIds,//选择的项目id 
// 								depName : "${depName}"//选择的部门的名
// 								},
// 						dataType : "text",
// 						success : function(data) {
// 							if (data == "true") {
// 								$(".pro").removeClass("select")// 当item被拖拽以后取消选中
// 							}
// 						}
// 					})
// 			});
			
	
//			var proIds = -1;
			 // 让已选项目的框可放置，接受未选的项目
//			$("#project").droppable({
//				accept: "#projectNo > .pro",//表示确定哪些可拖拽元素将被接受（#projectNo下的.pro可以放置在#project中）
// 				onDragEnter:function(e,source){ //当被鼠标拖动的对象进入其容器范围内时触发此事件
//				}
//			})
	 
});	
</script>
</head>
<body>
	<div id="main">
	<button type="button" class="close" data-dismiss="modal">×</button>
		<div id="title">
			<li>${depName}</li>
		</div>
		
		<span class="hint">已有项目：</span>
		<ul id="project" class="project">
			<c:forEach items="${pagevo.records}" var="pro">
				<li class="pro" data-id="${pro.id}">${pro.name}</li>
			</c:forEach>
		</ul> 
		
		<div id="btn">
			<button type="button" class="btn btn-default" id="Add">⇧</button>
			<button type="button" class="btn btn-default" id="Delete">⇩</button>
		</div>
			
			<span class="hint">未选择项目：</span>
			<ul id="projectNo">
				<c:forEach items="${noList}" var="noList">
					<li class="pro" data-id="${noList.id}">${noList.name}</li>
				</c:forEach>
			</ul>
	</div>
</body>
</html>
