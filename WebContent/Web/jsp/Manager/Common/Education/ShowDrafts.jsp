<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hackerspace.model.News"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示草稿箱</title>
</head>
<body>
	<%
			List<News> nl=(ArrayList<News>)request.getAttribute("newsList");
			if(nl.size()>=0) {
			for(News n:nl) {
				Timestamp timestamp=n.getTime();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time= sf.format(timestamp);
				%>
		<a href="showOneNews?id=<%=n.getId() %>">
			<span><%=n.getTitle() %></span>
			<span><%=n.getAuthorName() %></span>
			<span><%=time %></span>
		</a>		
		<a href="deleteOneNews?id="<%=n.getId() %>">删除</a>
		<br/>
				<%
			}
		}
		%>
</body>
</html>