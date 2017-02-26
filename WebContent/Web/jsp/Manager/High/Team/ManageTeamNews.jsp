<%@page import="com.hackerspace.model.Team"%>
<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.TeamNews"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理团队公告</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="3"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户管理 > 团队公告管理</h1>
<%
				Team t = (Team)request.getAttribute("team");
%>
				<div id="col_area">	
					<div id="come_back">
						<a href="FindTeam?id=<%=t.getId() %>&way=2">返回上一层</a>
					</div>
					<div id="col_area_table"  class="team_menber">
							

						<h1>已发布</h1>
						<table>
							<thead>
								<tr>
									<th><span>标题</span></th>
									<th><span>内容</span></th>
									<th><span>时间</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">删除</button>
									</td>
								</tr>
<%
						PageElem<TeamNews> pe = (PageElem<TeamNews>)request.getAttribute("news");
						List<TeamNews> news = pe.getPageElem();
						for(TeamNews n : news) {
%>
								<tr>
									<td><%=n.getTitle() %></td>
									<td><%=n.getContent() %></td>
									<td><%=n.getTime() %></td>
									<td>
										<button type="button" >删除</button>
									</td>
								</tr>
<%
						}
%>
							</tbody>
						</table>
						
						<h1>待审核</h1>
						<table>
							<thead>
								<tr>
									<th><span>标题</span></th>
									<th><span>内容</span></th>
									<th><span>时间</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
								<tr>
									<td>XXX团队的公告</td>
									<td>XX团队要改名为xxx...</td>
									<td>20分钟前</td>
									<td>
										<button type="button">通过</button>
										<button type="button">不通过</button>
									</td>
								</tr>
<%
						PageElem<TeamNews> peN = (PageElem<TeamNews>)request.getAttribute("newsN");
						List<TeamNews> newsN = peN.getPageElem();
						for(TeamNews n : newsN) {
%>
								<tr>
									<td><%=n.getTitle() %></td>
									<td><%=n.getContent() %></td>
									<td><%=n.getTime() %></td>
									<td>
										<button type="button" onclick="window.location.href='HandleTeamNews?id=<%=n.getId() %>&pass=true&tid=<%=t.getId()%>'">通过</button>
										<button type="button" onclick="window.location.href='HandleTeamNews?id=<%=n.getId() %>&pass=false&tid=<%=t.getId()%>'">不通过</button>
									</td>
								</tr>
<%
						}
%>
							</tbody>
						</table>
						</div>
					</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
</body>
</html>