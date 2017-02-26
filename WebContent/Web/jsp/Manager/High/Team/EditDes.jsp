<%@page import="com.hackerspace.model.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑团队简介</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-form.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/config.js"></script>
	</head>
<body>
	<div id="wrapper">
		<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
			<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
			<jsp:param name="param_sidebar_sec" value="3"></jsp:param>
		</jsp:include>
		<div id="column">
			<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
			<h1 id="col_nav">首页  > 用户管理 > 编辑团队</h1>
			<div id="col_area">		
<%

	Team t = (Team)request.getAttribute("team");
%>
				<form action="EditDes" method="post" id="news">
					<input type="hidden" name="id" value="<%=t.getId() %>" />
					<textarea id="des" name="des"><%=t.getBrief()!= null ? t.getBrief() : "" %></textarea> 
					<script type="text/javascript">CKEDITOR.replace('des');</script>
					<div id="button_qun">
						<button type="submit">修改</button>
						<button type="button" onclick="window.history.go(-1)" >取消</button>	
					</div>
				</form>
		</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</div>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>