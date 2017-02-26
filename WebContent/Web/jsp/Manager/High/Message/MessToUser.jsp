<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.User"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发信息给用户</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_messages"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="3"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 留言管理 > 批量发布留言管理</h1>
				<div id="col_area">
					<div id="mess_to_user">
<!-- 					<img src="" alt="固定头像" /> -->	
					<!-- <p>${content}</p> -->	
					</div>
					<div>
					<form action="PrepareUser" method="post" id="PrepareUser">
						<fieldset>
						<legend>选择用户类型</legend>
							<select name="role" id="role">
								<option value="1">普通用户</option>
								<option value="2">高级用户</option>
								<option value="3">老师</option>
							</select>
						</fieldset>
					</form>
						
					<form action="MessToUser" method="post">
						<div id="send_users_message" class="sub_f">
							<input type="hidden" name="role" value="${role}" />
							<input type="hidden" name="page" value="${page}" />
							<p class="illegal">${fieldErrors['content'][0]} ${message}</p> 
							<textarea name="content" value="${content}"></textarea>
							<button type="submit" class="button_normal fr">发送</button>
						</div>
					 <p class="illegal">${fieldErrors['user'][0]}</p> 	
						<div id="message_user_list" class="sub_f">
							<label><input type="checkbox" id="select_all">全选</label>
							<label><input type="checkbox" id="select_no_all">全不选</label>
<%
			List<User> users = (List<User>)request.getAttribute("users");
			for(User u : users) {
%>						
							
							<label><input type="checkbox" name="user" value="<%=u.getId() %>" />
							<%=u.getName() %></label>		
<%
			}
%>
						</div>
					</form>
			
					</div>
				</div>
				<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
		<script type="text/javascript">
			addLoadEvent(function(){
				var role = document.getElementById("role");
				EventUtil.addHandler(role,"change",function(event){
					var event = EventUtil.getEvent(event);
					var target = EventUtil.getTarget(event);
					var form = document.getElementById("PrepareUser");
					form.submit();
				});
			});
		</script>
	</body>
</html>