<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

#name, #age, #sex {
	width: 100px;
	height: 30px;
}
</style>
</head>
<body>
	<form action="department" method="post">
		<input type="hidden" name="type" value="doAdd" /> 
		<label>部门名:</label> 
		<input type="text" name="name" id="name" /><br /> <br /> 
		<br /> <br /> 
		<input type="submit" class="btn btn-default" value="确定" />
	</form>
</body>
</html>