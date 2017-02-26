<%@page import="com.hackerspace.model.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑常见问题</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_messages"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 常见问题管理 > 编辑问题</h1>
				<div id="col_area">
					<div id="col_area_table">
<%
	Question q = (Question)request.getAttribute("question");
	String content = "";
	String answer = "";
	int id = 0;
	boolean status = true;
	if(q != null){
		id = q.getId();
		content = q.getContent();
		answer = q.getAnswer();
		status = false;
	}
%>
						<form action="EditQuestion" method="post" id="add_question">
							<input type="hidden" name="id" value="<%=id %>" />
							<input type="hidden" name="status" value="<%=status %>" />
							<div>
								<p class="illegal">${fieldErrors['content'][0]}</p>
								<label for="content">问题：</label>
								<textarea id="content" name="content" >${question.content}</textarea>
							</div>
							<div>
								<p class="illegal">${fieldErrors['answer'][0]}</p>
								<label for="answer">回复：</label>
								<textarea id="answer" name="answer">${question.answer}</textarea>
							</div>
							<button type="submit"  class="col_area_delect">确定</button>
							<button type="button" onclick="window.location.href='SelectQuestion?page=1'" class="col_area_delect">取消</button>
						</form>
					</div>
				</div>
			</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
	</div>
</body>
</html>