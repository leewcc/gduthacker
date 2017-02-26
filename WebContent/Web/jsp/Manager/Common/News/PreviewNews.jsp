<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>该页面废弃</title>
</head>
<body>
	<%
		String title=(String)request.getAttribute("title");
		String tag=(String)request.getAttribute("tag");
		String content=(String)request.getAttribute("content");
		Timestamp timestamp=(Timestamp) request.getAttribute("timestamp");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time= sf.format(timestamp);
	%>
	<form action="uploadNews" method="post">
		<input type="text" id="title" name="title" value="<%=title %>">
		<input type="text" id="tag" name="tag" value="<%=tag %><%=time %>">
		<textarea id="content" name="content"><%=content %></textarea>
		<button class="submit" name="option" value="publish">发布文章</button> 
		<button class="submit" name="option" value="justsave">保存草稿</button> 
		<button class="button">返回</button>
	</form>
</body>
</html>