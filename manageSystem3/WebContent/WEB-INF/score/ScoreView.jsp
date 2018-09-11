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
float: left;
    position: relative;
    left: 250px;
}
.sou{
float:left;
margin-left: 10px;
margin-bottom: 7px;
}
#sex {
	width: 50px;
}

#name, #project, #grade,#department {
	width: 65px;
	height: 30px;
}

#view{
height: 225px;
}
#proId{
type:hidden
}
</style>

<script type="text/javascript">
	$().ready(function() {
				
					$("tr:not(#table-head)").on("dblclick",function(){
					// 检测此td是否已经被替换了，如果被替换直接返回
					if($(this).children().eq(1).children("input").length>0){
						return false;
					}
					// 获取被点击的td
					var tdDom4 = $(this).children().eq(4);
					//保存初始值,获取被点击的td中的文本内容
					var tdPreText4 = tdDom4.text();
 					//给td设置宽度
					tdDom4.width(80)
					// 创建替换的input对象
					var $input4 = $("<input type='text' class=namech>").width(80);
					// 给input对象设置value值
					$input4.val(tdPreText4);
					// 清除td中的文本内容
					$(this).children().eq(4).html("");
					$(this).children().eq(4).append($input4);
				}) 
				
				 $("#update").click(function(){
					 var lists = "";
					 $(".namech").parent().parent().each(function(){
						 var empId = $(this).data("empid");
						 var proId = $(this).data("proid");
						 var scoId = $(this).children().eq(0).html();
						 var value = $(this).children().eq(4).children().val();
						if(value!=""){
							 lists += scoId+","+value+","+empId+","+proId+";";
						}
					});
					 lists = lists.substring(0,lists.length-1);
					 if(lists!=""){
					 location.href = "score?type=doUpdate&lists="+ lists+"&pageNo="+${pagevo.pageNo};
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
					location.href = "score?pageNo="+pageNum;
				})
				
			$("#search").click(function(){
				var name = $("#name").val();
				var grade = $("#grade").val();
				var project = $("#project").val();
				var department = $("#department").val();
				var value = $("#value").val();
				location.href = "score?type=search&name="+name+"&grade="+grade+"&project="+project+"&department="+department+"&value="+value;
			})	
			
		$("#department").change(function(){
			var department = $("#department").val();
			$.ajax({
				url:"score",
				type:"post",
				data:{type:"search",department:department},
				dataType:"json",
				success:function(data){
					
				}
			})
		})	
			
});
</script>
</head>
<body>
	<div id="main">	
		<div class="sou">
			<div class="sou">
				<label>姓名:</label> 
				<input type="text" name="name" id="name" value="${sco.emp.name}"/> 
			</div>
			<div class="sou">
				<label>等级:</label>
				<select name="grade" id="grade">
					<option></option>
					<option value="优秀" <c:if test="${sco.grade =='优秀'}">selected</c:if>>优秀</option>
					<option value="良好" <c:if test="${sco.grade =='良好'}">selected</c:if>>良好</option>
					<option value="一般" <c:if test="${sco.grade =='一般'}">selected</c:if>>一般</option>
					<option value="及格" <c:if test="${sco.grade =='及格'}">selected</c:if>>及格</option>
					<option value="不及格" <c:if test="${sco.grade =='不及格'}">selected</c:if>>不及格</option>
				</select>
			</div>
			<div class="sou">
				<label>部门:</label> 
				<select name=""department"" id="department">
					<option></option>
					<c:forEach items="${depList}" var="depList" >
					<option value="${depList.name}" <c:if test="${depList.name == sco.dep.name}">selected</c:if>>${depList.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="sou">
				<label>项目：</label> 
				<select name="project" id="project">
					<option></option>
					<c:forEach items="${proList}" var="proList" >
					<option value="${proList.name}" <c:if test="${proList.name == sco.pro.name}">selected</c:if>>${proList.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="sou">
				<button type="button" class="btn btn-default sou" id="search">搜索</button>
			</div>
		</div>
		
		<input type="hidden" name="type" value="select" />
		<table class="table table-striped table-bordered table-hover">
			<tr id="table-head">
				<th>ID</th>
				<th>姓名</th>
				<th>部门</th>
				<th>项目</th>
				<th>成绩</th>
				<th>等级</th>
			</tr>
		
			<c:forEach items="${pagevo.records}" var="list">
			<tr data-empid="${list.emp.id}" data-proid="${list.pro.id}" id="content">
				<td>${list.id}</td>
				<td>${list.emp.name}</td>
				<td>${list.dep.name}</td>
				<td>${list.pro.name}</td>
				<td>${list.value}</td>
				<td>${list.grade}</td>
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
			<li><a href="${pagevo.url}type=search&pageNo=${1}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">首页</a></li>
			<li><a href="${pagevo.url}type=search&pageNo=${pagevo.pageNo - 1}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">上一页</a></li>
			</c:if>
			
			<!--显示页数超链接 -->
			<!--如果总页数大于5页 -->
			<c:if test="${pagevo.totalPageSize > 5}">
				<!--如果当前页数大于等与1并小于等与3（这里表示点击前3页的链接，都显示的是1到5页的链接） -->
				<c:if test="${pagevo.pageNo>=1 && pagevo.pageNo<=3}">
					<c:forEach begin="${1 }" end="${5 }" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=search&pageNo=${status.index}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				
				<!--如果当前页数大于3，并且小于等与总页数减2；则循环显示当前页减2，到当前页加2的链接 -->
				<c:if test="${pagevo.pageNo > 3 && pagevo.pageNo <= pagevo.totalPageSize-2}">
					<c:forEach begin="${pagevo.pageNo-2 }" end="${pagevo.pageNo+2 }" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=search&pageNo=${status.index}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				
				<!--如果当前页大于总页数-2，并且小于总页数（意思就是点击最末尾的2个链接，就显示最后5个链接） -->
				<c:if test="${pagevo.pageNo > pagevo.totalPageSize-2 && pagevo.pageNo <= pagevo.totalPageSize-1}">
					<c:forEach begin="${pagevo.pageNo-3 }" end="${pagevo.totalPageSize}" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=search&pageNo=${status.index}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${pagevo.pageNo > pagevo.totalPageSize-1 && pagevo.pageNo <= pagevo.totalPageSize}">
					<c:forEach begin="${pagevo.pageNo-4 }" end="${pagevo.totalPageSize}" varStatus="status">
						<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=search&pageNo=${status.index}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">${status.index}</a>
						</li>
					</c:forEach>
				</c:if>
			</c:if>
			<!--如果总页数小于等与5就直接把所有链接循环输出。 -->
			<c:if test="${pagevo.totalPageSize <= 5}">
				<c:forEach begin="${1}" end="${pagevo.totalPageSize}" varStatus="status">
					<li <c:if test="${pagevo.pageNo == status.index}"> class="active"</c:if> >
						<a href="${pagevo.url}type=search&pageNo=${status.index}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">${status.index}</a>
					</li>
				</c:forEach>
			</c:if>			
			
			<!--如果当前页小于总页数，就显示下一页和尾页 -->
			<c:if test="${pagevo.pageNo < pagevo.totalPageSize}">
				<li><a href="${pagevo.url}type=search&pageNo=${pagevo.pageNo + 1}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">下一页</a></li>
				<li><a href="${pagevo.url}type=search&pageNo=${pagevo.totalPageSize}&name=${sco.emp.name}&grade=${sco.grade}&project=${sco.pro.name}&department=${sco.dep.name}&value=${sco.value}">尾页</a></li>
			</c:if>
		</ul>
		
		<div>
		<button type="button" class="btn btn-default" id="update">保存修改</button>
		</div>
</div>
</body>
</html>
