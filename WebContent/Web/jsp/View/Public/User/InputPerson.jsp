<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>忘记密码申述</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
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
		<div id="column_pic_re">
			<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		</div>
		<div id="user_register" class="wrapper">
			<form action="ForgetPass" method="post">
				<P class="illegal"><%=request.getAttribute("error") == null ? "" : request.getAttribute("error")  %>${fieldErrors['card'][0]}</P>
				<label for="card">请在此输入你的账号：</label>
				<input type="number" name="card" id="card"/>
				<button type="submit">下一步</button>
			</form>
		</div>
	 	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>