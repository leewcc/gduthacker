<%@page import="com.hackerspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看注册人详细信息</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户管理 > 查看注册人详细信息</h1>
				<div id="col_area">
<%
	User u = (User)request.getAttribute("user");
%>
<%--
		<h2>用户注册信息</h2>
--%>	
					<div id="col_area_user_header">
						<h1>用户具体信息</h1>
						<a href="#" onclick="window.history.go(-1)">返回</a>
					</div>
		
					<div id="col_area_user_message">
						<div id="user_message_other">
							<p>姓名: <%=u.getName() %></p>
							
							<p>性别: <%=u.isGender()? "男" : "女"%></p>
							
							<p>学号: <%=u.getCard() %></p>
							
							<p>学院: <%=u.getInstitute() %></p>
							
							<p>专业: <%=u.getMajor() %></p>
							
							<p>联系方式: <%=u.getContact() %></p>
							
							<p>身份证: <%=u.getIdCard() %></p>
						</div>
						 <div id="user_message_picture">
							<img id="cardPic" name="cardPic" src="<%=u.getCardPic() %>"/>
							<span>学号扫描件</span>
						</div>
						<div id="user_message_button">
							<button type="button" class="pass" onclick="window.location.href='HandleRegister?id=<%=u.getId() %>&pass=true'">通过</button>
							<button type="button" class="no_pass" onclick="window.location.href='HandleRegister?id=<%=u.getId() %>&pass=true'">不通过</button>
						</div>
					</div>
				</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</body>
</html>