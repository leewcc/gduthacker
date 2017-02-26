<%@page import="com.hackerspace.model.TeamUser"%>
<%@page import="com.hackerspace.model.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>团队信息</title>
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
			<div id="col_area_team_more">
<%
			Team t = (Team)request.getAttribute("team");
%>						
						<div>
							<a id="team_picture" href="#"><img src="<%=t.getPicture() %>" alt="团队头像" /></a>
							<div id="team_basic">
								<h2><span>团队名 ：</span><%=t.getName() %></h2>
								<h2><span>团队口号</span> ：<%=t.getMotto() %></h2>
							</div>
						</div>
						
						
					<%--不知道这个修改按钮是干嘛的	<button type="button">修改</button> --%>
						<div id="team_brief">
							<h2>团队简介：</h2>
							<p><%=t.getBrief() %></p>
						
						</div>
						<div id="team_more_manage">
							<button type="button" onclick="window.location.href='SelectTeamUserView?page=1&pageN=1&tid=<%=t.getId() %>'">团队人员</button>
					<%--	<button type="button" onclick="window.location.href='">团队公告</button>
							<button type="button" onclick="javascript:alert('该功能尚未开放');">团队项目</button>
					 --%>	
						</div>
		
		</div>
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
</body>
</html>