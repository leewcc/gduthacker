<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Security"%>
<%@ page import="com.hackerspace.model.Power"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.User" %>
<%
	User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>使用密保修改密码</title>
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
	</head>
	<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="user_message">
			<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		</div>
	<%--还没加验证功能 --%>
		<div id="col_area_form"  class="wrapper mt">
<%
		List<Security> securities = (List<Security> )session.getAttribute("recurities");
		int num = request.getParameter("num") == null ? 0 : Integer.parseInt(request.getParameter("num"));
		Security s = securities.get(num);
%>
			<form action="ConfirmSecurity"  method="post" id="modify_by_ques">
				<button type="button" onclick="window.location.href='ModifyPassByQues.jsp?num=<%=(num + 1) % 3%>'">换一个</button>
				<div>
					<p>问题：<%=s.getQuestion() %></p>					
				</div>
				<div>
					<label for="answer">回答：</label>
					<input type="text" name="answer" id="answer" />
					<p class="illegal">${fieldErrors['answer'][0]} <%=request.getAttribute("error") == null ? "" : request.getAttribute("error") %></p>
					<input type="hidden" name="num" value="<%=num %>" />
				</div>	
				<button type="submit">下一步</button>
			</form>
		</div>

		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>

</body>
</html>