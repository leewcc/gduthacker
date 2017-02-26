<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Team"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>团队列表管理</title>
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
				<h1 id="col_nav">首页 > 用户管理 > 团队管理</h1>
				
				<div id="col_area">	
					<div id="team_manager">
						<button type="button" id="add_button" onclick="window.location.href='FindTeam?id=0&way=1'">添加团队</button>
<%--获取团队信息 --%>
						
<%
			PageElem<Team> pe = (PageElem<Team>)request.getAttribute("teamsE");
			List<Team> teams = pe.getPageElem();
			if(teams.isEmpty()){
				
%>
			<p id="error_message">当前没有团队。</p>
<%
			}else {
%>
		<ul>
<%
				for(Team t : teams){
			
%>
						
							<li>
								<div class="team_picture">
									<a href="FindTeam?id=<%=t.getId() %>&way=2"><img src="<%=t.getPicture() %>" alt="团队头像" /></a>
										<h1><%=t.getName() %></h1>
									
								</div>
								<div  class="team_brief">
									<%=t.getBrief() != null ? t.getBrief() : "" %>
								</div>
							</li>
							
<%
			}
%>					
						</ul>
<%
		}
%>
					</div>
					<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		int i;
		if(cp>1) {//有页码
				%>
		<a href="SelectTeam?pageE=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="SelectTeam?pageE=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="SelectTeam?pageE=1" class="<%=selected1 %>">1</a>		
			<a href="SelectTeam?pageE=2" class="<%=selected2 %>">2</a>		
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
			<a href="SelectTeam?pageE=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
		<a href="SelectTeam?pageE=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="SelectTeam?pageE=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="SelectTeam?pageE=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
	</div>

				</div>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>