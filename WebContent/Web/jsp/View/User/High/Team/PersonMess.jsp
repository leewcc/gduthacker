<%@page import="com.hackerspace.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成员信息页面</title>
</head>
<body>
	<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp"></jsp:include>
		<div id="user_area">
<%
	User u = (User)request.getAttribute("user");
%>
			<p class="illegal">${message}</p>
			<p>姓名: <%=u.getName() %></p>
							
			<p>性别: <%=u.isGender()? "男" : "女"%></p>
							
			<p>学号: <%=u.getCard() %></p>
							
			<p>学院: <%=u.getInstitute() %></p>
							
			<p>专业: <%=u.getMajor() %></p>
							
			<p>联系方式: <%=u.getContact() %></p>
							
			<p>身份证: <%=u.getIdCard() %></p>
			
			<div id="user_message_button">
				<button type="button" class="pass" onclick="window.location.href='AddMember?tid=${tid}&card=${card }'">确定</button>
				<button type="button" class="no_pass" onclick="window.location.href='InputPerson.jsp'">取消</button>
			</div>
		</div>
</body>
</html>