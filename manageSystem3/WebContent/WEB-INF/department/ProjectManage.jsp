<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="bootstrap/js/bootstrap.min.js"></script>
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
#title{
	font-size: 25px;
    list-style: none;
    margin-bottom: 20px;
    text-align: center;
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

#project{
	font-size: 16px;
    float: left;
    margin-right: 55px;
}
#departmentNo{
height: 32px;
}
</style>

<script type="text/javascript">
	$().ready(function() {

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
						becheckbox = becheckbox.substring(0,
								becheckbox.length - 1); //把最后一个逗号去掉
						location.href = "department?type=deletePro&selectId="+ becheckbox+"&depName="+"${depName}";
					  } 
					}else {
						alert("请至少选中一条数据！");
					}
					
				})			
				
				$("#Add").click(function(){
					var proName = $("#departmentNo").val();
					var d_id = ${d_id};
					location.href = "department?type=addProToDep&selectId="+d_id+"&proName="+proName;
				})
				
				$("#tiaozhuan").click(function(){
					var pageNum = $ ("#sub").val();
					if(isNaN(parseInt(pageNum))||parseInt(pageNum)<=0){//获取的值pageNum 不是数字或者小于等于0的时候跳到第一页
						pageNum=1;
					}
					var d_id = ${d_id};
					location.href = "department?type=ProjectManage&pageNo="+pageNum+"&selectId="+d_id;
				})	
					
});
</script>
</head>
<body>
	<div id="main">	
		<div id="title">
			<li>${depName}</li>
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
			<li><a href="${pagevo.url}type=ProjectManage&pageNo=${1}&selectId=${d_id}">首页</a></li>
			<li><a href="${pagevo.url}type=ProjectManage&pageNo=${pagevo.pageNo - 1}&selectId=${d_id}">上一页</a></li>
			</c:if>
			
		<!--显示页数超链接 -->
			<!--如果总页数大于5页 -->
			<c:if test="${pagevo.totalPageSize > 5}">
				<!--如果当前页数大于等与1并小于等与3（这里表示点击前3页的链接，都显示的是1到5页的链接） -->
				<c:if test="${pagevo.pageNo>=1 && pagevo.pageNo<=3}">
					<c:forEach begin="${1 }" end="${5 }" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=ProjectManage&pageNo=${status.index}&selectId=${d_id}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				
				<!--如果当前页数大于3，并且小于等与总页数减2；则循环显示当前页减2，到当前页加2的链接 -->
				<c:if test="${pagevo.pageNo > 3 && pagevo.pageNo <= pagevo.totalPageSize-2}">
					<c:forEach begin="${pagevo.pageNo-2 }" end="${pagevo.pageNo+2 }" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=ProjectManage&pageNo=${status.index}&selectId=${d_id}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				
				<!--如果当前页大于总页数-2，并且小于总页数（意思就是点击最末尾的2个链接，就显示最后5个链接） -->
				<c:if test="${pagevo.pageNo > pagevo.totalPageSize-2 && pagevo.pageNo <= pagevo.totalPageSize-1}">
					<c:forEach begin="${pagevo.pageNo-3 }" end="${pagevo.totalPageSize}" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=ProjectManage&pageNo=${status.index}&selectId=${d_id}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${pagevo.pageNo > pagevo.totalPageSize-1 && pagevo.pageNo <= pagevo.totalPageSize}">
					<c:forEach begin="${pagevo.pageNo-4 }" end="${pagevo.totalPageSize}" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=ProjectManage&pageNo=${status.index}&selectId=${d_id}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
			</c:if>
			<!--如果总页数小于等与5就直接把所有链接循环输出。 -->
			<c:if test="${pagevo.totalPageSize <= 5}">
				<c:forEach begin="${1}" end="${pagevo.totalPageSize}" varStatus="status">
					<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=ProjectManage&pageNo=${status.index}&selectId=${d_id}">${status.index}</a>
					</li>
				</c:forEach>
			</c:if>			
			
			<!--如果当前页小于总页数，就显示下一页和尾页 -->
			<c:if test="${pagevo.pageNo < pagevo.totalPageSize}">
				<li><a href="${pagevo.url}type=ProjectManage&pageNo=${pagevo.pageNo + 1}&selectId=${d_id}">下一页</a></li>
				<li><a href="${pagevo.url}type=ProjectManage&pageNo=${pagevo.totalPageSize}&selectId=${d_id}">尾页</a></li>
			</c:if>
		</ul>
		
		<div>
		<br>
			<div id="project">
				<label>未选择项目:</label> 
				<select name="departmentNo" id="departmentNo">
					<c:forEach items="${noList}" var="noList" >
						<option value="${noList.name}">${noList.name}</option>
					</c:forEach>
				</select>
			</div>
			<button type="button" class="btn btn-default" id="Add">增加</button>
			<button type="button" class="btn btn-default" id="doDelete">删除</button>
		</div>
</div>
</body>
</html>
