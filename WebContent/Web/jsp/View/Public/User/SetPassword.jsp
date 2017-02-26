<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html ">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>设置密码</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
</head>
<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="user_message">
			<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		</div>
		<div id="col_area_form"  class="wrapper mt">
			<form action="SetPassword" method="post" id="modify_by_ques">
				<label for="passwordN" id="passwordNL">新密码：</label>
				<input type="password" name="passwordN" id="passwordN" />
				<p class="illegal">${fieldErrors['passwordN'][0]}</p>
				<label for="passwordNA" id="passwordNAL">再次输入：</label>
				<input type="password" name="passwordNA" id="passwordNA" />
				<p class="illegal">${fieldErrors['passwordNA'][0]}</p>
				<button type="submit">确定</button>
			</form>
		</div>
	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>