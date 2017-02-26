<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.User" %>
<%    User user = (User)session.getAttribute("manager"); %>
<div id="head_nav">
	<span>欢迎你！&nbsp;<a href="/GDUTHackerSpace/Web/jsp/Manager/Common/Common/ManagerPage.jsp"><%=user.getName() %></a>|&nbsp;</span>
	<a href="ManagerLogout">注销</a>
</div>
<div id="head_img">
	<div></div>
</div>