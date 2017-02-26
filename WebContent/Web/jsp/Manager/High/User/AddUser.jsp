<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加用户</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 用户管理 > 添加用户</h1>
				<div id="col_area">		
					<div id="col_area_form">
						<form action="AddUser" method="post" id="col_area_form_form">
							<div>
								<p class="illegal">${fieldErrors['name'][0]}</p>
								<label for="name">姓名：</label>
								<input type="text" id="name" name="name" value="${name}" />
							</div>
							<div>
								<p class="illegal">${fieldErrors['card'][0]} ${card}</p>
								<label for="card">工号：</label>
								<input type="text" id="card" name="card" value="${card}" />
							</div> 
							<div>
								<p class="illegal">${fieldErrors['idCard'][0]}</p>
								<label for="idCard">身份证：</label>
								<input type="text" id="idCard" name="idCard" value="${idCard}" />
							</div>
							<div>
								<p class="illegal">${fieldErrors['password'][0]}</p>
								<label for="password">密码：</label>
								<input type="text" id="password" name="password" value="${password}" />
							</div>
							<div>
							<p class="illegal">${fieldErrors['role'][0]}</p>
							<label for="role">用户身份：</label>
							<select name="role" id="role">
								<option value="3">老师</option>
								<option value="4">录入员</option>
								<option value="5">管理员</option>
							</select>
							</div>
							<div class="button_qun">
								<button type="submit">提交</button>
								<button type="button" onclick="window.history.go(-1)">取消</button>
							</div>
						</form>
					</div>
				</div>
				<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</body>
</html>