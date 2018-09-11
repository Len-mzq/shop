<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="entity.Employee,java.util.List,org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统一修改员工信息</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
#main {
	width: 400px;
	margin: 20px auto;
}

.emp {
	width: 400px;
	float: left;
	margin: 10px 20px 10px 50px;
}
#one{
	border: 1px solid #C3C3C3;
	width: 400px;
	float: left;
	margin: 20px 50px 10px 0;
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

#name, #age, #sex {
	width: 100px;
	height: 30px;
}
</style>
</head>
<body>
<div id="main">
	<%
		String pageNoStr = request.getParameter("pageNo"); // 接收参数(当前页)
		int pageNo = 1;
		if (StringUtils.isNotEmpty(pageNoStr)) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		Employee emp = (Employee)request.getAttribute("emp");
		String ids = (String)request.getAttribute("ids");
	%>
	
		<form action="employee" method="post" class="emp">
			<input type="hidden" name="type" value="doUpdate2" /> 
			<input type="hidden" name="ids" value="<%=ids%>" /> 
			<input type="hidden" name="pageNo" value="<%=pageNo %>" />
			<div id="one">
			<br />
			<label>姓名:</label>
			<input type="text" name="name" id="name" value="<%=emp.getName()%>" /><br /> <br /> 
			<label>性别:</label>
			<select name="sex" id="sex">
				<option <%if (emp.getSex().equals("男")) {%>
					selected="selected" <%}%>>男</option>
				<option <%if (emp.getSex().equals("女")) {%>
					selected="selected" <%}%>>女</option>
			</select><br /> <br /> 
			<label>年龄:</label> 
			<input type="text" name="age" id="age" value="<%=emp.getAge()%>" /><br /> <br />
			</div>
			
			<div id="sure">
				<input type="submit" class="btn btn-default" value="确定" />
			</div>
		</form>
	</div>
</body>
</html>