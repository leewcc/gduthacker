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
		<title>管理团队用户   </title>
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
				<h1 id="col_nav">首页 > 用户管理 > 团队人员管理</h1>
				<div id="col_area">
<%
				Team t = (Team)request.getAttribute("team");
%>	
					<div id="come_back">
						<a href="FindTeam?id=<%=t.getId() %>&way=2">返回上一层</a>
					</div>
					<div id="col_area_table" class="team_menber">
					<h1>正式成员</h1>
					<button type="button" onclick="window.location.href='/GDUTHackerSpace/Web/jsp/Manager/High/Team/InputPerson.jsp?tid=<%=t.getId() %>'">添加负责人</button>
						<table>
							<thead>
								<tr>
									<th><span>姓名</span></th>
									<th><span>学号</span></th>
									<th><span>学院</span></th>
									<th><span>身份</span></th>
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
							</tr>
<%
						}
%>			
							</tbody>
						</table>
									<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		int i;
		if(cp>1) {//有页码
				%>
		<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=1" class="<%=selected1 %>">1</a>		
			<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=2" class="<%=selected2 %>">2</a>		
				<%
				i=cp-2;
				if(i>3) {//出现省略号
					%>
				<span>...</span>	
					<%
				}else {
				i=3;		
					}
				for(;i<cp+2&&i<tp-1;i++) {
				selected =(i==cp)?"selected":"";
				%>
			<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=<%=i %>" class="<%=selected %>"><%=i %></a>				
				<%
				}
				if(i<tp-1) {
					%>
			<span>...</span>
					<%
				}
			String selectedt1=(cp==tp-1) ?"selected":"";
			String selectedt2=(tp==cp)?"selected":"";
			%>
		<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="SelectTeamUser?pageN=${pageN}&tid=<%=t.getId()%>&page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
	</div>
						
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
									<button type="button" onclick="window.location.href='HandleTeamUser?id=<%=m.getId() %>&pass=true&tid=<%=t.getId()%>'">通过</button>
									<button type="button" onclick="window.location.href='HandleTeamUser?id=<%=m.getId() %>&pass=false&tid=<%=t.getId()%>'">不通过</button>
								</td>
							</tr>
<%
						}
%>			
							</tbody>
						</table>
					</div>
					<%
		cp=peN.getCurrentPage();
		tp=peN.getTotalPage();
		if(cp>1) {//有页码
				%>
		<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=1" class="<%=selected1 %>">1</a>		
			<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=2" class="<%=selected2 %>">2</a>		
				<%
				i=cp-2;
				if(i>3) {//出现省略号
					%>
				<span>...</span>	
					<%
				}else {
				i=3;		
					}
				for(;i<cp+2&&i<tp-1;i++) {
				selected =(i==cp)?"selected":"";
				%>
			<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=<%=i %>" class="<%=selected %>"><%=i %></a>				
				<%
				}
				if(i<tp-1) {
					%>
			<span>...</span>
					<%
				}
			String selectedt1=(cp==tp-1) ?"selected":"";
			String selectedt2=(tp==cp)?"selected":"";
			%>
		<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="SelectTeamUser?page=${page}&tid=<%=t.getId()%>&pageN=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
	</div>
		<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
</body>
</html>