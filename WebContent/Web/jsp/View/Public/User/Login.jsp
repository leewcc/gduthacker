<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>前台用户登录界面</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserLogin.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="wrapper">
			<div id="subwrapper_login">
				<form action="UserLogin" method="post" id="login_form">
					<fieldset>
						<legend>登录</legend>
						<p class="illegal"><%=request.getAttribute("error") == null ? "" :  request.getAttribute("error")%></p>
						<div>
							<label for="card">学号：</label>
							<input type="text" id="card" name="card" placeholder="学号" value="<%=request.getAttribute("card") == null ? "" :  request.getAttribute("card")%>"  required autofocus/>
							<p class="illegal">${fieldErrors['card'][0]}</p>
						</div>
						<div>
							<label for="password">密码：</label>
							<input type="password" id="password" name="password" placeholder="密码" required/>
							<p class="illegal">${fieldErrors['password'][0]}</p>
						</div>
						<div id="code_check">
						<label for="code">验证码：</label><input type="text" name="code" id="code" placeholder="验证码"/>
							<img id="vimg"  title="点击更换" onclick="changeCode();" src="Code">
							<p class="illegal">${fieldErrors['code'][0]} ${codeE}</p>
						</div>
						<button type="submit" id="login">登&nbsp;陆</button>
						<a href="InputPerson.jsp" id="forget">忘记密码？</a>
					</fieldset>
				</form>
			</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/ViewModel.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/ViewControl.js"></script>
	</body>
</html>