<%@page import="com.hackerspace.model.Security"%>
<%@page import="java.util.List"%>
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
		<title>修改密保-输入之前的密保问题</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<!--[if lt IE 8]>
		<link rel="stylesheet" type="text/css" href="/GDUTHackerSpace/Web/css/manager/IEReset.css">
		<script>
			function a(){
				alert("对不起，该网站尚未做兼容");
			}
			a();
		</script>
			<![endif]-->
<%
	if(user.getRole() >= 4){
%>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
<%
	}else{
%>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
<%
	}
%>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>

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
				<h1 id="col_nav">首页 > 账户管理 > 修改密码 </h1>
				<div id="col_area">
<%
			}else{
				
%>
<%-- 如果是前台用户包含在这个里面--%>
		<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp"></jsp:include>
		<div id="user_area">
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

						<form action="ConfirmSecurities" method="post">
<%
	List<Security> securities = (List<Security>)session.getAttribute("securities");
	Security s1 = securities.get(0);
	Security s2 = securities.get(1);
	Security s3 = securities.get(2);
%>
							<p class="illegal">${error}</p>
							<h2><%="问题1：" + s1.getQuestion() %></h2>
							<div>
								<label for="answer1">回答：</label>
								<input type="hidden" name="id1"  value="<%=s1.getId() %>"/>
								<input type="text" id="answer1" name="answer1" />
								<p class="illegal">${fieldErrors['answer1'][0]}</p>
							</div> 
							
							<h2><%="问题2：" + s2.getQuestion() %></h2>
							<div>
								<label for="answer2">回答：</label>
								<input type="hidden" name="id2"  value="<%=s2.getId() %>"/>
								<input type="text" id="answer2" name="answer2" />
								<p class="illegal">${fieldErrors['answer2'][0]}</p>
							</div> 
							
							<h2><%="问题3：" + s3.getQuestion() %></h2>
							<div>
								<label for="answer3">回答：</label>
								<input type="hidden" name="id3"  value="<%=s3.getId() %>"/>
								<input type="text" id="answer3" name="answer3" />
								<p class="illegal">${fieldErrors['answer3'][0]}</p>
							</div> 

							<input type="hidden" name="role" value="<%=request.getParameter("role") %>" />
							<button type="submit">下一步</button> 
						</form>
					</div>
<%
	if(user.getRole() >= 1){
%>
				</div>
			</div>
		</div>
<%
	}else{

		
%>
			</div>
			</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
<%

	}
%>
	</body>
</html>