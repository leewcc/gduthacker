<%@page import="com.hackerspace.model.TeamUser"%>
<%@page import="com.hackerspace.model.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
<%
		Team t = (Team)request.getAttribute("team");
%>	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><%=t.getName() %> - 校内创客  - 创客空间</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
		<jsp:include page="../../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
			<img src="/GDUTHackerSpace/Web/images/column/pictures1.png" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2>主页  >  校内创客</h2>
					<h1><%=t.getName() %></h1>
				</div>
			</div>
		</div>
		<div id="team_message" class="wrapper">
			<div id="team_more_button">
			</div>
			<div id="team_basic">
				<div id="team_picture" >
					<a href="#"><img src="<%=t.getPicture() %>" alt="团队头像"/></a>
					<h2><%=t.getName() %></h2>
				</div>
				<div id="team_mes_con">
					<h2><span>团队口号</span> ：<%=t.getMotto() %></h2>
				</div>
			</div>
					
					
			<div id="team_brief">
				<h2>团队简介：</h2>
				<p><%=t.getBrief() %></p>
			</div>
		</div>
	<jsp:include page="../../HomePage/Footer.jsp"></jsp:include>
</body>
</html>