<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册请求管理页面</title>
	</head>

<body>                                                                             
	<s:form action="create" method="post" enctype="multipart/form-data" namespace="/manager/education">   
		<label>实践实训标题：</label><s:textfield id = "" name="education.title" />
		<label>实践实训正文：</label>   	
		 	
     	<s:textarea name="education.content"/>
     	<s:hidden name="education.tag" value="1"></s:hidden>
     	<s:submit value="提交"/>
     	<input type="button" value="预览"/>
     	<a href="javascript:void(0)">取消</a>
    </s:form>
</body>
</html>