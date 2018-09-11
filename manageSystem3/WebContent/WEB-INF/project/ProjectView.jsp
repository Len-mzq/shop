<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<style type="text/css">
#main {
	width: 600px;
	margin: 20px auto;
}
#allselect{
width:109px;
}
.btn {
	margin-right:55px;
}
#update{
margin-right:0;
}
.sou{
float:left;
margin-left: 35px;
margin-bottom: 7px;
}
#sex {
	width: 50px;
}

#name, #count{
	width: 100px;
	height: 30px;
}

#view{
height: 225px;
}
</style>

<script type="text/javascript">
	$().ready(function() {
		
				function modify() {
					var allcheckbox = "";
					var becheckbox = "";
					$("input[name=selectId]").each(function() { //遍历table里的全部checkbox
						allcheckbox += $(this).val() + ","; //获取所有checkbox的值
						if ($(this).prop("checked")) { //如果被选中
							becheckbox += $(this).val() + ","; //获取被选中的值
						}
					});

					if (becheckbox.length > 0) { //如果获取到
						becheckbox = becheckbox.substring(0,
								becheckbox.length - 1); //把最后一个逗号去掉
						location.href = "project?type=showUpdate&selectId="
							+ becheckbox;
					}	
				}

					//反选   each遍历
					$("input[name='duoXuan']").click(function() {
						$("input[name='selectId']").each(function() {
							$(this).prop("checked", !$(this).prop("checked"));
						});
					});

					$("input[name='selectId']").click(function() {
						if($(this).prop("checked") == false) {
							$("#all").prop("checked", false)
						} else {
							var flag = true;
							$("[name='selectId']").each(function(index, element) {
								if($(this).prop("checked") == false) {
									flag = false;
									return false;
								}

							})
							if(flag) {
								$("#all").prop("checked", true)
							}
						}
					})
					

				var selectId = -1;
				$("#showAdd").click(function() {
					location.href = "project?type=showAdd"
				})

				$("#doDelete").click(function() {
					var allcheckbox = "";
					var becheckbox = "";
					$("input[name=selectId]").each(function() { //遍历table里的全部checkbox
						allcheckbox += $(this).val() + ","; //获取所有checkbox的id值
						if ($(this).prop("checked")) { //如果被选中
							becheckbox += $(this).val() + ","; //获取被选中的id值
						}
					});
					
					if (becheckbox.length > 0) {
						var msg = "您真的确定要删除吗？";
						if (confirm(msg) == true) {
						/* location.href = "project?type=doDelete&selectId=" + selectId; */
						becheckbox = becheckbox.substring(0,
								becheckbox.length - 1); //把最后一个逗号去掉
						location.href = "project?type=doDelete&selectId="+ becheckbox;
					  } 
					}else {
						alert("请至少选中一条数据！");
					}
					
				})

				$("#showUpdate").click(function() {
					var allcheckbox = "";
					var becheckbox = "";
					$("input[name=selectId]").each(function() { //遍历table里的全部checkbox
						allcheckbox += $(this).val() + ","; //获取所有checkbox的id值
						if ($(this).prop("checked")) { //如果被选中
							becheckbox += $(this).val() + ","; //获取被选中的id值
						}
					});
					if (becheckbox.length > 0) {
						becheckbox = becheckbox.substring(0,becheckbox.length - 1); //把最后一个逗号去掉
						location.href = "project?type=showUpdate&selectId="+ becheckbox+"&pageNo="+${pagevo.pageNo};
					} else {
						alert("请至少选中一条数据！");
					}
				})
				
					$("tr:not(#table-head)").on("dblclick",function(){
					// 检测此td是否已经被替换了，如果被替换直接返回
					if($(this).children().eq(1).children("input").length>0){
						return false;
					}
					// 获取被点击的td
					var tdDom1 = $(this).children().eq(1);
					//保存初始值,获取被点击的td中的文本内容
					var tdPreText1 = tdDom1.text();
 					//给td设置宽度
					tdDom1.width(300)
					// 创建替换的input对象
					var $input1 = $("<input type='text' class='namech'>").width(300);
					// 给input对象设置value值
					$input1.val(tdPreText1);
					// 清除td中的文本内容
					$(this).children().eq(1).html("");
					$(this).children().eq(1).append($input1);
				}) 
				
				 $("#update").click(function(){
					 var pros = "";
					 $(".namech").parent().parent().each(function(){
						 	var id = $(this).children().eq(0).html();
							var name = $(this).children().eq(1).children().val();
							pros += id+","+name+";";
					});
					 pros = pros.substring(0,pros.length-1);
					 if(pros!=""){
					 location.href = "project?type=doUpdate3&pros="+ pros+"&pageNo="+${pagevo.pageNo};
					 }
					 $("input:not(#check)").each(function(){  //遍历每一个input
							var newText = $(this).val();  //获取input中的值
							var td=$(this).parent("td");  //获得input对应的td
							td.html(newText);  //把input中的值放到td中显示
					});
					 
				 });
				
				$("#tiaozhuan").click(function(){
					var pageNum = $ ("#sub").val();
					if(isNaN(parseInt(pageNum))||parseInt(pageNum)<=0){//获取的值pageNum 不是数字或者小于等于0的时候跳到第一页
						pageNum=1;
					}
					location.href = "project?pageNo="+pageNum;
				})
				
			$("#search").click(function(){
				var name = $("#name").val();
				
				location.href = "project?type=search&name="+name;
			})	
					
});
</script>
</head>
<body>
	<div id="main">	
		<div class="sou">
			<div class="sou">
				<label>项目:</label> 
				<input type="text" name="name" id="name" value="${pro.name}"/> 
			</div>
			
			<div class="sou">
				<button type="button" class="btn btn-default sou" id="search">搜索</button>
			</div>
		</div>
		
		<input type="hidden" name="type" value="select" />
		<table class="table table-striped table-bordered table-hover">
			<tr id="table-head">
				<th>ID</th>
				<th>项目</th>
				<th id="allselect"><input type="checkbox" name="duoXuan" id="all" class="btn checkbox"/>全选/反选 </th>
			</tr>
		
			<c:forEach items="${pagevo.records}" var="pro">
			<tr data-id="${pro.id}" id="content">
				<td>${pro.id}</td>
				<td>${pro.name}</td>
				<td><input type="checkbox" name="selectId" id="check" value="${pro.id}" /></td>
			</tr>
			</c:forEach>
		</table>
		
		<div class="pageMsg">
		第${pagevo.pageNo}页,共${pagevo.totalPageSize}页,${pagevo.totalRecords}条数据
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳到&nbsp;
		<input id="sub" type="text" size="3" />
		&nbsp;页&nbsp;&nbsp;
		<button id="tiaozhuan">跳转</button>
		</div>
		
		<ul class="pagination" id="pagination">
			<!--如果当前页大于1，就显示上一页和首页 -->
			<c:if test="${pagevo.pageNo >1}">
			<li><a href="${pagevo.url}type=search&pageNo=${1}&name=${dep.name}&count=${dep.count!=-1?dep.count:''}">首页</a></li>
			<li><a href="${pagevo.url}type=search&pageNo=${pagevo.pageNo - 1}&name=${dep.name}&count=${dep.count!=-1?dep.count:''}">上一页</a></li>
			</c:if>
			
		<!--显示页数超链接 -->
			<!--如果总页数大于5页 -->
			<c:if test="${pagevo.totalPageSize > 5}">
				<!--如果当前页数大于等与1并小于等与3（这里表示点击前3页的链接，都显示的是1到5页的链接） -->
				<c:if test="${pagevo.pageNo>=1 && pagevo.pageNo<=3}">
					<c:forEach begin="${1 }" end="${5 }" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}${status.index}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				
				<!--如果当前页数大于3，并且小于等与总页数减2；则循环显示当前页减2，到当前页加2的链接 -->
				<c:if test="${pagevo.pageNo > 3 && pagevo.pageNo <= pagevo.totalPageSize-2}">
					<c:forEach begin="${pagevo.pageNo-2 }" end="${pagevo.pageNo+2 }" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}${status.index}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				
				<!--如果当前页大于总页数-2，并且小于总页数（意思就是点击最末尾的2个链接，就显示最后5个链接） -->
				<c:if test="${pagevo.pageNo > pagevo.totalPageSize-2 && pagevo.pageNo <= pagevo.totalPageSize-1}">
					<c:forEach begin="${pagevo.pageNo-3 }" end="${pagevo.totalPageSize}" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}${status.index}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${pagevo.pageNo > pagevo.totalPageSize-1 && pagevo.pageNo <= pagevo.totalPageSize}">
					<c:forEach begin="${pagevo.pageNo-4 }" end="${pagevo.totalPageSize}" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}${status.index}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
			</c:if>
			<!--如果总页数小于等与5就直接把所有链接循环输出。 -->
			<c:if test="${pagevo.totalPageSize <= 5}">
				<c:forEach begin="${1}" end="${pagevo.totalPageSize}" varStatus="status">
					<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=search&pageNo=${status.index}">${status.index}</a>
					</li>
				</c:forEach>
			</c:if>			
			
			<!--如果当前页小于总页数，就显示下一页和尾页 -->
			<c:if test="${pagevo.pageNo < pagevo.totalPageSize}">
				<li><a href="${pagevo.url}type=search&pageNo=${pagevo.pageNo + 1}&name=${dep.name}&count=${dep.count!=-1?dep.count:''}">下一页</a></li>
				<li><a href="${pagevo.url}type=search&pageNo=${pagevo.totalPageSize}&name=${dep.name}&count=${dep.count!=-1?dep.count:''}">尾页</a></li>
			</c:if>
		</ul>
		
		<div>
		<br>
		<button type="button" class="btn btn-default" id="showAdd">增加</button>
		<button type="button" class="btn btn-default" id="doDelete">删除</button>
		<button type="button" class="btn btn-default" id="showUpdate">批量修改</button>
		<button type="button" class="btn btn-default" id="update">保存修改</button>
		</div>
</div>
</body>
</html>
