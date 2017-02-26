<%@page import="com.hackerspace.model.TeamUser"%>
<%@page import="com.hackerspace.model.Team"%>
<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>团队简介页面</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
	<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp">
			<jsp:param name="sidebar" value="3"></jsp:param>
		</jsp:include>
		<div id="user_area">
			<%-- 内容区 --%>
			<table id="user_team_list">
<%
		List<Team> teams = (List<Team>)request.getAttribute("teams");
		for(Team t : teams){
%>
			<tr>
				<td class="team_brief">
				<a href="SeeMyTeam?id=<%=t.getId() %>">
					<img src="<%=t.getPicture() %>" alt="团队头像" /></a>
					<span><%=t.getName() %></span>
				
				</td>
				<td><%=t.getBrief() %></td>
			</tr>
<%
		}
%>
			</table>
		
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
</body>
</html>