<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加员工</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="js/jquery.js"></script>
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
     margin-left: -30px;
}
#zhaopian{
	float: left;
	margin-left: 407px;
}
#upload{
    margin-left: -300px;
}
#pictureView img{
	width: 120px;
	height: 100px;
}
</style>
<script type="text/javascript">
$().ready(function() {
	
	$("#upload").click(function(){
		var formData = new FormData();
		for(var i=0;i<$("[name=myPicture]")[0].files.length;i++){
			formData.append("myPicture",$("[name=myPicture]")[0].files[i]);
		}
		$.ajax({
			url:"employee?type=upload",
			type:"post",
			data:formData,
			cache:false,
			processData:false,
			contentType:false,
			dataType:"text",
			success:function(data){
				var str = "<img src='img/"+data+"' />";
				str += " <input type='hidden' name='picture' value='"+data+"'/> ";
				$("#pictureView").append(str);
			}
		})
	})
	$(document).on("click","#pictureView img",function(){
		$(this).next().remove();
		$(this).remove();
		var name = $(this).prop("src");
		name = name.substring(name.lastIndexOf("/")+1)
		$.ajax({
			url:"delete",
			type:"post",
			data:{fileName: name},
			dataType: "text",
			success: function() {
             
            }
		})
	})
})
</script>
</head>
<body>
	<form action="employee?type=doAdd2" method="post">
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

		<label  class="col-sm-1" id="zhaopian">照片:</label>
		<input type="file" name="myPicture" value="选择照片" class="col-sm-2" id="picture" multiple>
		<input type="button" class="btn btn-default col-sm-0" id="upload" value="上传" />
		<div id="pictureView"></div>
		<br /> <br />
		<input type="submit" class="btn btn-default" value="确定" />
	</form>
</body>
</html>