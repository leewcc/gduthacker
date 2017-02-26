<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hackerspace.model.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
<%
		ProjectShow ps = (ProjectShow) request.getAttribute("ps");
		if (ps != null) {
%>
		<title><%=ps.getTitle()%></title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		
	</head>
	<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
			<img src="/GDUTHackerSpace/Web/images/column/pictures1.png" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2>主页 > 创客项目</h2>
					<h1>项目路演</h1>
				</div>
			</div>
		</div>
		<div id="section" class="wrapper">
			<div id="sec_sidebar">
				<div id="sec_sidebar_head">
					<h2>创客项目</h2>
					<div id="sec_temp"></div>
				</div>
				<ul>
					<li><a href="/GDUTHackerSpace/user/project/query?tag=0">创新项目</a></li>
					<li><a href="/GDUTHackerSpace/user/project/query?tag=1">创业项目</a></li>
					<li class="current"><a href="/GDUTHackerSpace/user/projectshow/querys">项目路演</a></li>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			
			<div id="new_article">
				<h1><%=ps.getTitle()%></h1>
				<p class="show_title">
					<span class="temp_mess">编辑者:<%=ps.getAuthor() %></span>
					<span class="temp_mess">编辑时间：<%=ps.getTime().toString().substring(0, 16) %></span>
				</p>
				<div id="new_art_con"><%=ps.getContent() %></div>
			</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
		<%} else {%>
		<title>该系统出错</title>
		</head>
		<body>
		<p>服务器忙, 请等待</p>
		<%} %>
	</body>
</html>