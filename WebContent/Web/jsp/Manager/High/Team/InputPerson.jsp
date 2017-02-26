<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>输入负责人学号</title>
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
		<h1 id="col_nav">首页 > 用户管理 > 团队人员管理</h1>
		<div id="col_area">
			<form action="FindPerson" method="post" id="col_area_upadte_form">
				<input type="hidden" name="tid" value="<%=request.getParameter("tid") %>" />
				<p class="illegal">${fieldErrors['card'][0]} ${error}</p>
				<label for="card">请输入负责人学号:</label>
				<input type="text" id="card" name="card" value="${card}"/>
				<button type="submit">确定</button>
			</form>	
		</div>				
		<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
	</div>
</div>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>
