<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Message"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>留言管理</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_messages"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 留言管理 > 用户留言管理</h1>
				<div id="col_area">
					<div id="col_area_table">
<%--第一步：	获取留言数据 --%>
<%
	PageElem<Message> pe = (PageElem<Message>)request.getAttribute("messages");
	List<Message> mess = pe.getPageElem();
%>
						<table>
							<thead>
								<tr>
									<th><span>用户名</span></th>
									<th><span>留言信息</span></th>
									<th><span>留言时间</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							
<%
							for(Message m : mess){
%>
								<tr>
									<td class="title"><%=m.getUser().getName() %></td>
									<td><%=m.getContent() %></td>
									<td><%=m.getDate().toString().substring(0,19) %></td>
									<td>
										<button type="button" class="col_area_delect" onclick="window.location.href='FindMessage?id=<%=m.getId() %>'">回复</button>
										<button type="button" class="col_area_delect" onclick="window.location.href='DeleteMessage?id=<%=m.getId() %>'">删除</button>
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
		<a href="SelectMessage?page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="SelectMessage?page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="SelectMessage?page=1" class="<%=selected1 %>">1</a>		
			<a href="SelectMessage?page=2" class="<%=selected2 %>">2</a>		
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
			<a href="SelectMessage?page=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
		<a href="SelectMessage?page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="SelectMessage?page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="SelectMessage?page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
	</div>
				</div>
			</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</body>
</html>