<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="entity.Department,java.util.List,vo.PageVo,org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改部门信息</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
#main {
	width: 1100px;
	margin: 20px auto;
}

.dep {
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
		List<Department> depList = (List<Department>) request.getAttribute("depList");
		for (int i = 0; i < depList.size(); i++) {
	%>
	
		<form action="department" method="post" class="dep">
			<input type="hidden" name="type" value="doUpdate" /> 
			<input type="hidden" name="id" value="<%=depList.get(i).getId()%>" /> 
			<input type="hidden" name="pageNo" value="<%=pageNo %>" />
			<div id="one">
			<br />
			<label>部门名:</label>
			<input type="text" name="name" id="name" value="<%=depList.get(i).getName()%>" /><br /> <br /> 
			</div>
			<%
				}
			%>
			<div id="sure">
				<input type="submit" class="btn btn-default" value="确定" />
			</div>
		</form>
	</div>
</body>
</html>