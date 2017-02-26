<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hackerspace.model.Cooperation"%>
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
			List<Cooperation> nl=(ArrayList<Cooperation>)request.getAttribute("newsList");
			if(nl.size()>=0) {
			for(Cooperation n:nl) {
				Timestamp timestamp=n.getDate();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time= sf.format(timestamp);
				%>
		<a href="showOneCooperation?id=<%=n.getId() %>">
			<span><%=n.getName() %></span>
			<span><%=n.getAuthor().getName() %></span>
			<span><%=time %></span>
		</a>		
		<a href="deleteOneCooperation?id="<%=n.getId() %>">删除</a>
		<br/>
				<%
			}
		}
		%>
</body>
</html>