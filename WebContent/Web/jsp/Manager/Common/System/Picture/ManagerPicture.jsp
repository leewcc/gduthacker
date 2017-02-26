<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.model.Picture"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>栏目照片管理</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_pictures"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 系统管理 > 图片管理</h1>
				<div id="col_area">
	<%-- 选择管理的栏目 --%>
<%		
	List<Picture> l=(ArrayList<Picture>)request.getAttribute("imageUrlList");
	String belong=(String)request.getAttribute("belong"); 
%>
	<div id="col_area_header">
		<a href="showManagerPhoto?belong=1" <%=belong.equals("1")?"class='current'":"" %>>首页轮播</a>
		<a href="showManagerPhoto?belong=11" <%=belong.equals("11")?"class='current'":"" %>>学院简介</a>
		<a href="showManagerPhoto?belong=12" <%=belong.equals("12")?"class='current'":"" %>>师资力量</a>
		<a href="showManagerPhoto?belong=13" <%=belong.equals("13")?"class='current'":"" %>>新闻动态</a>
		<a href="showManagerPhoto?belong=14" <%=belong.equals("14")?"class='current'":"" %>>办事服务</a>
		<a href="showManagerPhoto?belong=15" <%=belong.equals("15")?"class='current'":"" %>>创客教育</a>
		<a href="showManagerPhoto?belong=16" <%=belong.equals("16")?"class='current'":"" %>>创客项目</a>
		<a href="showManagerPhoto?belong=17" <%=belong.equals("17")?"class='current'":"" %>>创客之窗</a>
		<a href="showManagerPhoto?belong=18" <%=belong.equals("18")?"class='current'":"" %>>校企合作</a>
	</div>
<% 
	if(l.isEmpty()){
%>
					<p id="error_message">当前没有图片。</p>
<%
	}else{
%>
	<%--  图片显示 --%>
					<div id="col_area_picture">
	<% 

		for(Picture p:l) {
			%>
			<a href="prepareColumnPhoto?belong=<%=belong %>&id=<%=p.getId() %>&fileShowUrl=<%=p.getPictureUrl() %>">
			<img alt="栏目图片" src="<%=p.getPictureUrl() %>" width="400px"/>
			</a>
			<%
		}
	}
	%>
				</div>
			</div>
		<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</div>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>