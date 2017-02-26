<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输入成员学号页面</title>
</head>
<body>
	<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp"></jsp:include>
		<div id="user_area">
			<form action="FindMember" method="post">
				<input type="hidden" name="tid" value="<%=request.getParameter("tid") %>" />
				<p class="illegal">${fieldErrors['card'][0]} ${error}</p>
				<label for="card">请输入成员学号</label>
				<input type="text" id="card" name="card" value="${card}"/>
				<button type="submit">确定</button>
			</form>
		</div>
</body>
</html>