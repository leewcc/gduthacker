<%@page import="com.hackerspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看个人头像</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
	<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp">
			<jsp:param name="sidebar" value="0"></jsp:param>
		</jsp:include>
<%
	User user=(User)session.getAttribute("user");
%>
		<div id="user_area">
		<%--账户管理的模板页 --%>
			<h1 id="account_message">账户管理</h1>
				<div id="personal_message">
				<%-- 账户管理的侧边栏v2.0-修改了修改密码的路径bug--%>
					<ul id="account_ul">
						<li class="current"><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/UserMess.jsp">查看个人资料</a></li>
						<li><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/ModifyPassByPass.jsp?role=user">修改密码</a></li>
						<li><a href="FindSecurities?role=user">修改密保</a></li>
						<li><a href=""></a></li>
					</ul>
				<div id="see_personal_mess">
<%-- 					<a href="prepareUserPicture?id=<%=user.getId() %>&imageUrl=<%=user.getPicture() %>&way=1" id="update_picture"> --%>
<%-- 						<img src="<%=user.getPicture() %>" alt="个人头像"/> --%>
<%-- 					</a> --%>
					<img src="<%=user.getPicture() %>" alt="个人头像"/>
					<p>姓名：<%=user.getName()  %></p>
					<p>学号：<%=user.getCard()  %></p>
					<p>学院：<%=user.getInstitute()  %></p>
					<p>专业：<%=user.getMajor()  %></p>
					<p>联系方式：<%=user.getContact()  %></p>
				</div>
			</div>
		</div>

		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
	</body>
</html>