<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>欢迎回到创客空间后台管理系统</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_accoun"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页</h1>
				<div id="col_area">
					<h1 id="col_welcome">欢迎进入创客网站后台管理系统</h1>
				</div>
				<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
		<script type="text/javascript" defer src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</body>
</html>