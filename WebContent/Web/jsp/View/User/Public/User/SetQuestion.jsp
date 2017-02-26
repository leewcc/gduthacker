<%@ page import="com.hackerspace.model.Power"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.User" %>
<%
	User user = (User)session.getAttribute(request.getParameter("role"));
%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改密保-选择新的密保问题</title>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
<%
	if(user.getRole() >= 4){ //管理员
%>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
<%
	}else{  //用户
%>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
<%
	}
%>
	</head>
	<body>
<% 		

		Power p = user.getPower();
		System.out.print(p);
			if(user.getRole() >= 4){
%>
		<div id="wrapper">
			<jsp:include page="../../../../Manager/Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_account"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../../../Manager/Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 账户管理 > 设置密保 </h1>
				<div id="col_area">
<%
			}else{
%>
		<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
			<jsp:include page="../../Common/CommonSidebar.jsp"></jsp:include>
			<div id="user_area">
<%-- 如果是前台用户包含在这个里面--%>
			<h1 id="account_message">账户管理</h1>
			<div id="personal_message">
			<%-- 账户管理的侧边栏v2.0-修改了修改密码的路径bug--%>
				<ul id="account_ul">
					<li><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/UserMess.jsp">查看个人资料</a></li>
					<li><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/ModifyPassByPass.jsp?role=user">修改密码</a></li>
					<li  class="current"><a href="FindSecurities?role=user">修改密保</a></li>
					<li><a href=""></a></li>
				</ul>
<%
			}
%>
	<%--还没加验证功能 --%>
		<div id="col_area_form">
			<form action="SetSecurities" method="post" id="col_area_form_form">
				<div>
					<p class="illegal">${fieldErrors['question'][0]}</p>
					<label for="question1">问题1：</label>
					<select id="question1" name="question1">
						<option>你的父亲的名字是？</option>
						<option>你的母亲的名字是？</option>
						<option>你的出生地是？</option>
						<option>你的高中班主任的名字是？</option>
						<option>你的初中班主任的名字是？</option>
						<option>你的小学班主任的名字是？</option>
						<option>您的小学校名是？</option>
						<option>您父亲的生日是？</option>
						<option>您母亲的生日是？</option>
					</select>
				</div>
				<div>
					<label for="answer1">回答：</label>
					<input type="text" name="answer1" id="answer1" />
					<p class="illegal">${fieldErrors['answer1'][0]}</p>
				</div>
				<div>	
					<label for="question2">问题2：</label>
					<select id="question2" name="question2">
						<option>你的父亲的名字是？</option>
						<option>你的母亲的名字是？</option>
						<option>你的出生地是？</option>
						<option>你的高中班主任的名字是？</option>
						<option>你的初中班主任的名字是？</option>
						<option>你的小学班主任的名字是？</option>
						<option>您的小学校名是？</option>
						<option>您父亲的生日是？</option>
						<option>您母亲的生日是？</option>
					</select>
				</div>
				<div>
					<label for="answer2">回答：</label>
					<input type="text" name="answer2" id="answer2" />
					<p class="illegal">${fieldErrors['answer2'][0]}</p>
				</div>
				<div>
				<label for="question3">问题1：</label>
				<select id="question3" name="question3">
						<option>你的父亲的名字是？</option>
						<option>你的母亲的名字是？</option>
						<option>你的出生地是？</option>
						<option>你的高中班主任的名字是？</option>
						<option>你的初中班主任的名字是？</option>
						<option>你的小学班主任的名字是？</option>
						<option>您的小学校名是？</option>
						<option>您父亲的生日是？</option>
						<option>您母亲的生日是？</option>
				</select>
				</div>
				<div>
					<label for="answer3">回答：</label>
					<input type="text" name="answer3" id="answer3" />
					<p class="illegal">${fieldErrors['answer3'][0]}</p>
				</div>
				<input type="hidden" name="role" value="<%=request.getParameter("role") %>" />
				<button type="submit">提交</button>
			</form>
		</div>
<%
	if(user.getRole() >= 1){
%>
				</div>
			</div>
		</div>
		</div>
<%
	}else{

		
%>
			</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
<%

	}
%>
</body>
</html>