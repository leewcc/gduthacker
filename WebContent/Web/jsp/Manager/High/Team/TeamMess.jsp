<%@page import="com.hackerspace.model.TeamUser"%>
<%@page import="com.hackerspace.model.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看团队信息页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="3"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户管理 > 团队信息查看</h1>
				<div id="col_area">	
					<div id="col_area_team_more">
					<p>${error }</p>
<%
			Team t = (Team)request.getAttribute("team");
%>						<div id="team_more_button">
							<button type="button" onclick="window.location.href='FindTeam?id=<%=t.getId() %>&way=1'">修改</button>
							<button type="button" onclick="window.location.href='DeleteTeam?id=<%=t.getId() %>&way=2'">删除</button>
						</div>
						<div style="overflow: hidden;">
							<a id="team_picture" href="prepareTeamPicture?id=<%=t.getId() %>&imageUrl=<%=t.getPicture() %>"><img src="<%=t.getPicture() %>" alt="团队头像" /></a>
							<div id="team_basic">
								<h2><span>团队名 ：</span><%=t.getName() %></h2>
								<h2><span>团队口号</span> ：<%=t.getMotto() %></h2>
								<h2><span>创建时间 </span>：<%=t.getCreateTime().toString().substring(0,10) %></h2>
							</div>
						</div>
						
						
					<%--不知道这个修改按钮是干嘛的	<button type="button">修改</button> --%>
						<div id="team_brief">
							<h2>团队简介：</h2>
							<button type="submit" onclick="window.location.href='FindDes?id=<%=t.getId() %>'">编辑</button>
							<p><%=t.getBrief()!= null ? t.getBrief() : "" %></p>
						
						</div>
						<div id="team_more_manage">
							<button type="button" onclick="window.location.href='SelectTeamUser?page=1&pageN=1&tid=<%=t.getId()%>'">人员管理</button>
						<%-- <button type="button" onclick="window.location.href='SelectTeamNews?page=1&pageN=1&tid=<%=t.getId() %>'">公告管理</button>
							<button type="button" onclick="javascript:alert('该功能尚未开放');">项目管理</button>
							 --%>	
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