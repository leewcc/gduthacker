<%@page import="com.hackerspace.model.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>（该页面已经废弃）</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
<%
	Question q = (Question)request.getAttribute("question");
%>
	
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
	<div id="column_pic_re">
		<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
	</div>
		<div id="question_message" class="wrapper">
		<img src="#" />
		<span><%=q.getContent() %></span>
	</p>
	
	<p>
		<img src="#" />
		<span><%=q.getAnswer() %></span>
	</p>
	</div>
	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>