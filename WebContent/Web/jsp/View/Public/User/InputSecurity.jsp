<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>设置密保</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic_re">
			<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		</div>
		<div id="user_register" class="wrapper">
		<h2>用户注册</h2>
		<form action="InputSecurities" method="post">
			<div>
				<p>第四步：设置密保问题</p>
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
			
			<button type="submit">提交</button>
		</form>
	</div>
	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>