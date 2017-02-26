<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.User"%>
<%@page import="com.hackerspace.model.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>回复留言</title>
	<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_messages"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户留言管理 > 回复留言</h1>
				<div id="col_area">
					<div class="sub_f" id="come_back">
						<a class="fr" href="#" onclick="window.history.go(-1)">返回上一层</a>
					</div>
					<div id="reply_area">
<%--第一步：	获取留言数据 --%>
<%
	Message m = (Message)request.getAttribute("message");
	User u = m.getUser();
%>
						<div id="user" class="sub_f">
							<img id="picture" src="<%=u.getCardPic() %>" alt="用户的头像"/>
							<div class="fl">
								<p id="name">姓名：<%=u.getName() %></p>
								<p id="card">学号：<%=u.getCard() %></p>
								<p id="institute">学院：<%=u.getInstitute() %></p>
								<p id="idCard">专业：<%=u.getMajor() %></p>
							</div>
							<div class="fl">
								<p id="idCard">身份证：<%=u.getIdCard() %></p>
								<p id="contact">联系方式：<%=u.getIdCard() %></p>
								<p id="gender">性别：<%=u.isGender()? "男" : "女" %></p>
							</div>
						</div>
						<div id="reply_message">
							<form action="ReplyMessage" method="post" class="sub_f">
								<input type="hidden" name="id" value="<%=m.getId() %>" />						
								<p id="reply_content"><%=m.getContent() %></p>
<%
						List<Message> replys = m.getReply();
						for(Message r : replys) {
%>
								<p><%=r.getContent() %></p>
<%
						}
%>
								<div id="sub_textarea"><textarea name="content">${content}</textarea></div>
								<p class="illegal">${error}</p>
								<button class="button_normal fr" type="submit">回复</button>
							</form>
						</div>
						
					</div>
				</div>
			</div>
	</div>
</body>
</html>