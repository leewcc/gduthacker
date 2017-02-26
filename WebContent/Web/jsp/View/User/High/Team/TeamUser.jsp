<%@page import="com.hackerspace.model.Team"%>
<%@page import="com.hackerspace.model.User"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="com.hackerspace.model.TeamUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>团队人员</title>
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
		<div id="col_area_team_more">
			<%-- 内容区 --%>
			<%
				Team t = (Team)request.getAttribute("team");
%>	
					<div id="come_back">
						<a href="SeeMyTeam?id=<%=t.getId() %>">返回上一层</a>
					</div>
					<div id="col_area_table" class="team_menber">
					<h1>现任成员</h1>
					<button type="button" id="add_button" onclick="window.location.href='/GDUTHackerSpace/Web/jsp/View/User/High/Team/InputPerson.jsp?tid=<%=t.getId() %>'">添加成员</button>
						<table>
							<thead>
								<tr>
									<th><span>姓名</span></th>
									<th><span>学号</span></th>
									<th><span>学院</span></th>
									<th><span>身份</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
<%
						PageElem<TeamUser> pe = (PageElem<TeamUser>)request.getAttribute("members");
						List<TeamUser> members = pe.getPageElem();
						for(TeamUser m : members){
							User u = m.getUser();
%>		
							<tr>
								<td><%=u.getName() %></td>
								<td><%=u.getCard() %></td>
								<td><%=u.getInstitute() %></td>
								<td><%=m.getUserStatus() %></td>
								<td><button type="button" onclick="window.location.href='LeaveTeam?id=<%=m.getId() %>&tid=<%=t.getId() %>'">退出</button></td>
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
									<th><span>姓名</span></th>
									<th><span>学号</span></th>
									<th><span>学院</span></th>
									<th><span>状态</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>	
<%
						PageElem<TeamUser> peN = (PageElem<TeamUser>)request.getAttribute("membersN");
						List<TeamUser> membersN = peN.getPageElem();
						for(TeamUser m : membersN){
							User u = m.getUser();
%>		
							<tr>
								<td><%=u.getName() %></td>
								<td><%=u.getCard() %></td>
								<td><%=u.getInstitute() %></td>
								<td><%=m.getUserStatus() %></td>
								<td>
									<button type="button" onclick="window.location.href='Cancel?id=<%=m.getId() %>&tid=<%=t.getId() %>'">取消</button>
								</td>
							</tr>
<%
						}
%>			
							</tbody>
						</table>
				</div>
			</div>
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
</body>
</html>