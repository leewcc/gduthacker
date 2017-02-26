<%@page import="com.hackerspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css"/>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic_re">
			<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		</div>
		<div class="supwrapper">
			<div id="user_register" class="wrapper">
				<h2>用户注册</h2>
				<form action="InputBase" id="register" method="post">
<%
		User u = (User)request.getAttribute("user");
		if(u == null)
			u = new User();
%>
					<p>第一步：输入正确的信息，以便管理员在后台核对</p>
					<div>
						<label for="name">姓名：</label>
						<input id="name" name="name" type="text" value="<%=u.getName()%>" required autofocus/>
						<p class="illegal">${fieldErrors['name'][0]}</p>
					</div>
					<div id="register_gender">
						<label for="gender">性别 ：</label>
						<label><input id="gender_0" name="gender" type="radio" value="true" checked/>男</label>
						<label><input id="gender_1" name="gender" type="radio" value="false"/>女</label>
						<p class="illegal"></p>
					</div>
					<div>
						<label for="password">密码：</label>
						<input type="password" name="password" id="password"  required/>
						<p class="illegal">${fieldErrors['password'][0]}</p>
					</div>
					<div>
						<label for="card">学号 ：</label>
						<input id="card" name="card" type="text"  value="<%=u.getCard()%>" required/>
						<p class="illegal">${fieldErrors['card'][0]}${error}</p>
					</div>
					<div>
						<label for="idCard">身份证： </label>
						<input id="idCard" name="idCard" type="text" value="<%=u.getIdCard()%>" required/>
						<p class="illegal">${fieldErrors['idCard'][0]}</p>
					</div>
				
					<div>
						<label for="institute">学院：</label>
						<input id="institute" name="institute" type="text" value="<%=u.getInstitute()%>" required/>
						<p class="illegal">${fieldErrors['institute'][0]}</p>
					</div>
					<div>
						<label for="major">专业 ：</label>
						<input id="major" name="major" type="text" value="<%=u.getMajor()%>" required/>
						<p class="illegal">${fieldErrors['major'][0]}</p>
					</div>
					<div>
						<label for="contact">电话号码：</label>
						<input id="contact" name="contact" type="number" value="<%=u.getContact()%>" required/>
						<p class="illegal">${fieldErrors['contact'][0]}</p>
					</div>		
					<button type="submit" id="submit">下一步</button>
			 	</form>
		 	</div>
	 	</div>
	 	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Register.js"></script>
	</body>
</html>