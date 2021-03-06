<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Message"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的消息箱</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
	</head>
	<body>
		<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
			<jsp:include page="../../Common/CommonSidebar.jsp">
				<jsp:param name="sidebar" value="2"></jsp:param>
			</jsp:include>
			<div id="user_area">

<%
	PageElem<Message> pe = (PageElem<Message>)request.getAttribute("messages");
	List<Message> mess = pe.getPageElem();
	for(Message m : mess) {
%>
	<div class="one_question">
		<div class="one_question_user">
			<img src="/GDUTHackerSpace/Web/images/temp/peopleBig.jpg" alt="用户"/>
			<p>管理员</p>
		</div>
		<div class="one_question_content">
			<h1><%=m.getContent() %></h1>
			<p><span class="time"><%=m.getDate().toString().substring(0,19) %></span></p>
		</div>
	</div>
<%
	}
%>
					<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		int i;
		if(cp>1) {//有页码
				%>
		<a href="MessageBox?page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="MessageBox?page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="MessageBox?page=1" class="<%=selected1 %>">1</a>		
			<a href="MessageBox?page=2" class="<%=selected2 %>">2</a>		
				<%
				i=cp-2;
				if(i>3) {//出现省略号
					%>
				<span>...</span>	
					<%
				}else {
				i=3;		
					}
				for(;i<cp+2&&i<tp-1;i++) {
				selected =(i==cp)?"selected":"";
				%>
			<a href="MessageBox?page=<%=i %>" class="<%=selected %>"><%=i %></a>				
				<%
				}
				if(i<tp-1) {
					%>
			<span>...</span>
					<%
				}
			String selectedt1=(cp==tp-1) ?"selected":"";
			String selectedt2=(tp==cp)?"selected":"";
			%>
		<a href="MessageBox?page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="MessageBox?page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="MessageBox?page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
			</div>
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>

</body>
</html>