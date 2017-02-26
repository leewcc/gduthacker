<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>前台个人主页</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
		<jsp:include page="../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="CommonSidebar.jsp">
			<jsp:param name="sidebar" value="0"></jsp:param>
		</jsp:include>
		<div id="user_area">
			<%-- 内容区 --%>
			<p id="welcome">欢迎回来创客空间。</p>
		</div>
		<jsp:include page="../../Public/HomePage/Footer.jsp"></jsp:include>
	</body>
</html>