<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加员工</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
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

#name, #age, #sex,#department{
	width: 100px;
	height: 30px;
}
#picture{
    margin-left: 475px;
}
#zhaopian{
	float: left;
	margin-left: 428px;
}
</style>
</head>
<body>
	<form action="employee?type=doAdd" method="post" enctype="multipart/form-data">
		<label>姓名:</label> 
		<input type="text" name="name" id="name" /><br /> <br /> 
		<label>性别:</label>
		<select name="sex" id="sex">
			<option>男</option>
			<option>女</option>
		</select><br /> <br /> 
		<label>年龄:</label> 
		<input type="text" name="age" id="age" /><br /> <br /> 
		<label>部门:</label> 
		<select name="department" id="department">
			<option></option>
			<c:forEach items="${depList}" var="dep" >
			<option value="${dep.name}">${dep.name}</option>
			</c:forEach>
		</select><br /> <br />
		<label id="zhaopian">照片:</label> 
		<input type="file" name="myPicture" value="选择照片" id="picture">
		<br /> <br />
		<input type="submit" class="btn btn-default" value="确定" />
	</form>
</body>
</html>