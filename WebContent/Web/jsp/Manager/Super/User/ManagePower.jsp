<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="com.hackerspace.model.Power"%>
<%@page import="com.hackerspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员详细信息页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户管理 > 权限管理</h1>
				<div id="col_area">
					<div id="col_area_table">
					

<%
	PageElem<User> pe = (PageElem<User>)request.getAttribute("users");
	List<User> users = pe.getPageElem();
	if(users.isEmpty()){
%>
						<p id="error_message">当前没有管理员</p>

<%
	}else{
%>	

						<table>
							<thead>
								<tr>
									<th><span>姓名</span></th>
									<th><span>用户管理</span></th>
									<th><span>图片管理</span></th>
									<th><span>服务管理</span></th>
									<th><span>系统管理</span></th>
									<th><span>留言管理</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>

<%
		for(User u : users){
			Power p = u.getPower();
%>
								<tr>
									<form action="" method="post">
										<input type="hidden" name="id" value="<%=u.getId() %>" />
										<td><%=u.getName() %></td>
										<td><input type="checkbox" name="canUser" value="true" <%=p.isCanUser()? "selected" : "" %> /></td>
										<td><input type="checkbox" name="canPicture" value="true" <%=p.isCanPicture()? "selected" : "" %> /></td>
										<td><input type="checkbox" name="canSerivce" value="true" <%=p.isCanSerivce()? "selected" : "" %> /></td>
										<td><input type="checkbox" name="canSystem" value="true" <%=p.isCanSystem()? "selected" : "" %> /></td>
										<td><input type="checkbox" name="canMessage" value="true" <%=p.isCanMessage()? "selected" : "" %> /></td>
										<td><button type="submit">分配</button></td>
									</form>
								</tr>
		
<%
		}
%>
							</tbody>
						</table>
<%
	}
%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>