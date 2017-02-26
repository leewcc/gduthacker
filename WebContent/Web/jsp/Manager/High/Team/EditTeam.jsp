<%@page import="com.hackerspace.model.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>创建、编辑团队基本信息页面</title>
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
				<h1 id="col_nav">首页  > 用户管理 > 编辑团队</h1>
				<div id="col_area">	
				<div id="col_area_form">	
<%
				Team t = (Team)request.getAttribute("team");
				boolean create = true;
				String name = "";
				String des = "";
				String motto = "";
				int id = 0;
				if(t != null){
					name = t.getName();
					des = t.getBrief();
					id = t.getId();
					motto = t.getMotto();
					
					create = false;
				}
%>
					<form action="EditTeam" method="post">
						<input type="hidden" name="id" value="<%=id %>" />
						<input type="hidden" name="create" value="<%=create %>" />
						<div>
							<p class="illegal">${fieldErrors['name'][0]}</p>
							<label for="name">团队名称：</label>
							<input type="text" name="name" id="name" value="<%=name%>"/>
						</div>
						<div>
							<p class="illegal">${fieldErrors['motto'][0]}</p>
							<label for="motto">口号：</label>
							<textarea name="motto" id="motto" value="<%=motto%>"></textarea>
						</div>
						<div>
							<p class="illegal">${fieldErrors['createTime'][0]}</p>
							<label for="creatTime">创建时间：</label>
							<input type="date" name="createTime" id="createTime" />
						</div>
						
						<%--这里缺少一个上传团队logo和设置团队负责人的功能 --%>
						
						<button type="submit">确定</button>
						<button type="button" onclick="window.history.go(-1);">取消</button>
					</form>
					</div>
				</div>
				</div>
				</div>
			<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</body>
</html>