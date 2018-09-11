<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改员工信息</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
#main {
	width: 1100px;
	margin: 20px auto;
}

.emp {
	width: 1100px;
	float: left;
	margin: 10px 20px 10px 50px;
}
#one{
	border: 1px solid #C3C3C3;
	width: 300px;
	float: left;
	margin: 20px 20px 10px 0;
	border-radius: 10px;
	background-color:ghostwhite;
}
#sure{
	clear:both;
}
form {
	text-align: center;
	margin-top: 100px;
	font-size: large;
}

select option {
	font-size: large;
}

#sex {
	width: 50px;
}

#name, #age, #sex, #department{
	width: 100px;
	height: 30px;
}
</style>
</head>
<body>
<div id="main">
	
	<c:forEach items="${empList}" var="empList" >
		<form action="employee" method="post" class="emp">
			<input type="hidden" name="type" value="doUpdate" /> 
			<input type="hidden" name="id" value="${empList.id}" /> 
			<input type="hidden" name="pageNo" value="${pageNo}" />
			<div id="one">
			<br />
			<label>姓名:</label>
			<input type="text" name="name" id="name" value="${empList.name}" /><br /> <br /> 
			<label>性别:</label>
			<select name="sex" id="sex">
				<option value="男" <c:if test="${empList.sex =='男'}">selected</c:if>>男</option>
				<option value="女" <c:if test="${empList.sex =='女'}">selected</c:if>>女</option>
			</select><br /> <br /> 
			<label>年龄:</label> 
			<input type="text" name="age" id="age" value="${empList.age}" /><br /> <br />
			<label>部门:</label> 
			<select name="department" id="department">
				<c:forEach items="${depList}" var="dep" >
				<option value="${dep.name}" <c:if test="${dep.name == empList.dep.name}">selected</c:if>>${dep.name}</option>
				</c:forEach>
			</select><br /> <br />
			</div>
			</c:forEach>
			<div id="sure">
				<input type="submit" class="btn btn-default" value="确定" />
			</div>
		</form>
	</div>
</body>
</html>