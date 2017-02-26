<%@page import="com.hackerspace.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>留言查看页面</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
	</head>
	<body>
		<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp">
			<jsp:param name="sidebar" value="2"></jsp:param>
		</jsp:include>
<%
	HttpSession ses = request.getSession();
	User u = (User)ses.getAttribute("user");
%>
		
		<div id="user_area">
<%
			Message m = (Message)request.getAttribute("message");
			List<Message> replys = m.getReply();
			if(replys.isEmpty()){}
%>
		<div class="one_question">
			<div class="one_question_user">
				<img src="<%=u.getPicture() %>" alt="用户"/>
				<p><%=u.getName() %></p>
			</div>
			<div class="one_question_content">
				<h1><%=m.getContent() %></h1>
			<%--	<span class="time">编辑时间：<%=m.getDate().toString().substring(0, 19) %></span> --%>
				<%--下main这个是管理员回复的 --%>
				
<%
			for(Message r : replys) {
%>		
				<div class="one_question_answer">
					<div class="one_question_answer_user">
						<img src="/GDUTHackerSpace/Web/images/temp/peopleBig.jpg" alt="用户"/>
						<p>管理员</p>
					</div>
					<div class="one_question_answer_con">
						<p>答复：<%=r.getContent() %></p>
						<p><span class="time">编辑时间：<%=r.getDate().toString().substring(0, 19) %></span></p>
					</div>
				</div>
<%
			}
%>	
				</div>
			</div>
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</body>
</html>