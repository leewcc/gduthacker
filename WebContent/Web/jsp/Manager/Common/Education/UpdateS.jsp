<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>实践实训更新</title>
	</head>

<body>

	<s:form action="updateS" method="post" enctype="multipart/form-data" namespace="/manager/education">   
		<label>实践实训标题：</label><s:textfield name="education.title" ></s:textfield>
		<label>实践实训正文：</label>   	
		 	
     	<s:textarea name="education.content"></s:textarea>
     	
     	
     	<s:hidden name="education.id" ></s:hidden>
     	<s:hidden name="education.tag"></s:hidden>
     	<s:submit value="提交"/>
     	<input type="button" value="预览"/>
     	<a href="javascript:void(0)">取消</a>
    </s:form>
</body>
</html>