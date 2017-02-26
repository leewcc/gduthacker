<%@page import="com.hackerspace.model.HaskerSystem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>更改系统名(暂时作废)</title>
		<link rel="shortcut icon" type="image/x-icon" href="../../../images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="../../../css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="../../../js/common/Common.js"></script>
		<script type="text/javascript" src="../../../js/manager/ManagerPage.js"></script>
		<script type="text/javascript" src="../../../js/manager/ChangeSystemName.js"></script>
	</head>
	<body>
<%
	HaskerSystem h=(HaskerSystem)request.getAttribute("HackerSystem");
%>
		<div id="wrapper">
			<jsp:include page="../../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_system"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 留言管理 > 常见问题管理</h1>
				<div id="col_area">
					<form action="saveSystemName" id="change_name" method="post">
						<label for="systemName">系统名称：</label>
						<input type="hidden" name="id" id="id" value="<%=h.getId() %>"/>
						
							<input type="text" name="systemName" id="systemName" value="<%=h.getName() %>" autofocus/>
						
						<button type="submit" class="">提交</button>
					</form>
				</div>
			</div>
		</div>
		
	</body>
</html>