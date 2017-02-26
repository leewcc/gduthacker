<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hackerspace.model.User"%>
<%
	HttpSession ses = request.getSession();
	User u = (User)ses.getAttribute("user");
	String temp = request.getParameter("sidebar");
%>
	<div id="user_message">
		<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		<a id="user_link" href="/GDUTHackerSpace/Web/jsp/View/User/Common/CommonUserPage.jsp">
			<%-- 这里放用户的头像--%>
			<img src=<%=(u.getPicture().trim().equals(""))?"/GDUTHackerSpace/Web/images/teacher/Yanqing_Cai.png":u.getPicture() %> alt="用户头像" />
		</a>
		<%--h1:用户的名字 --%>
		<h1><%=u.getName() %></h1>
		<a id="user_account" href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/UserMess.jsp">账户管理</a>
		<!-- 
			<a id="user_account" href="/GDUTHackerSpace/Web/jsp/View/User/Public/ModifyPassByQues.jsp">账户管理</a>
		 -->
	</div>
	<div id="user_sidebar" class="subwrapper">
		<ul class="wrapper">
		<%--这个链接之后要改 --%>
			<li><a <%="1".equals(temp)?"class='current'":"" %> href="/GDUTHackerSpace/Web/jsp/View/User/Common/Course/MyCourse.jsp">我的课程</a></li>
			<li><a <%="2".equals(temp)?"class='current'":"" %> href="SelectMyMessage?page=1">我的留言</a>
<% 
	if(u.getRole() == 2){
%>
			<li><a <%="3".equals(temp)?"class='current'":"" %> href="SelectMyTeam">我的团队</a></li>
<% 
	}
%>
		</ul>
	</div>