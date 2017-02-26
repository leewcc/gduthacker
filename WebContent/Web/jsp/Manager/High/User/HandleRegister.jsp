<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="com.hackerspace.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册请求管理页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户管理 > 用户注册审核</h1>
				<div id="col_area">		
					<div id="col_area_table">
<%
	PageElem<User> pe = (PageElem<User>)request.getAttribute("users");
	List<User> users = pe.getPageElem();
%>	

						<table>
							<thead>
								<tr>
									<th><span>姓名</span></th>
									<th><span>学号</span></th>
									<th><span>身份证</span></th>
									<th><span>学院</span></th>
									<th><span>是否通过注册</span></th>
								</tr>
							</thead>
							<tbody>
			
<%
			for(User u : users){
%>			
								<tr>
									<td><a href="GetUser?id=<%=u.getId() %>"><%=u.getName() %></a></td>
									<td><%=u.getCard() %></td>
									<td><%=u.getIdCard() %></td>
									<td><%=u.getInstitute() %></td>
									<td>
										<button type="button" class="col_area_delect" onclick="window.location.href='HandleRegister?id=<%=u.getId() %>&pass=true'">通过</button>
										<button type="button" class="col_area_delect"  onclick="window.location.href='HandleRegister?id=<%=u.getId() %>&pass=false'">不通过</button>
									</td>
								</tr>
<%
			}
%>
							</tbody>
						</table>
					</div>
								<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		int i;
		if(cp>1) {//有页码
				%>
		<a href="SelectRegister?page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="SelectRegister?page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="SelectRegister?page=1" class="<%=selected1 %>">1</a>		
			<a href="SelectRegister?page=2" class="<%=selected2 %>">2</a>		
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
			<a href="SelectRegister?page=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
		<a href="SelectRegister?page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="SelectRegister?page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="SelectRegister?page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
					</div>
				</div>
				<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</body>
</html>