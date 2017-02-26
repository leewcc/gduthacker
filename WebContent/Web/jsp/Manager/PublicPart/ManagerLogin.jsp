<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员登陆界面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" type="text/css" href="/GDUTHackerSpace/Web/css/manager/ManagerLogin.css">
		<script type="text/javascript">  
			 
			function changeCode() {    
				var imgNode = document.getElementById("vimg");                    
  				imgNode.src = "Code?t=" + Math.random();  // 防止浏览器缓存的问题       
			}      
    	</script>  
	</head>
	<body>

<%--2016/3/26 致电给维护者，你辛苦了,修改保存密码的功能 --%>
		<div id="wrap_bg"></div> <%--一个背景的大色块，好坑 --%>
		<div id="wrapper">
			<div id="subwrapper">
				<a href="showHomePage" id="image"></a>
				<h1>创客网站后台管理系统</h1>
				<form action="ManagerLogin" method="post" id="login_form">
				<p class="illegal"><%=request.getAttribute("error") == null ? "" :  request.getAttribute("error")%></p>
				<div>
					<label for="card">学号/工号</label>
					<input type="text" id="card" name="card" placeholder="学号/工号" value="${card}" required autofocus/>
					<p class="illegal">${fieldErrors['card'][0]}</p>
				</div>
				<div>
					<label for="password">密码</label><!-- 5-15位 -->
					<input type="password" id="password" name="password" placeholder="密码" required/>
					<p class="illegal">${fieldErrors['password'][0]}</p>
				</div>
				<div id="code_check">
					<label for="code">验证码：</label>
					<input type="text" name="code" placeholder="验证码"/>
					<img id="vimg"  title="点击更换" onclick="changeCode();" src="Code">
					<p class="illegal">${fieldErrors['code'][0]} ${codeE}</p>
				</div>
<!-- 				<div class="sub_f"> -->
<!-- 					<label id="cookie" class="fl"><input type="checkbox" name="cookieFlag" id="cookieFlag" value="1" />保存密码</label> -->
<!-- 				</div> -->
					<button type="submit" id="login">登&nbsp;陆</button>
				</form>
			</div>	
		</div>
		<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerLogin.js"></script>
	</body>
</html>