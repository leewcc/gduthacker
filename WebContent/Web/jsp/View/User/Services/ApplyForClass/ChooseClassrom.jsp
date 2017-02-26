<%@page import="com.hackerspace.model.Classroom"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>选择课室页面</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<jsp:include page="../../../../Result/iehack.jsp"></jsp:include>
		
	</head>
	<body>
		<jsp:include page="../../../../View/Public/HomePage/Nav.jsp"></jsp:include>
			<div id="column_pic">
			<%--这里还没弄图片管理 --%>
				<img src="/GDUTHackerSpace/Web/images/column/pictures1.png" alt="栏目图片"/>
				<div id="header_column">
					<div class="wrapper">
						<h2>主页 > 办事服务</h2>
						<h1>课室申请</h1>
					</div>
				</div>
			</div>
		<div id="section" class="wrapper">
			<div id="sec_sidebar">
				<div id="sec_sidebar_head">
					<h2>办事服务</h2>
					<div id="sec_temp"></div>
				</div>
				<ul>
					<li><a href="/GDUTHackerSpace/Web/jsp/View/Public/Services/LiveIn.jsp#sec_article">入驻申请</a></li>
					<li class="current"><a href="/GDUTHackerSpace/Web/jsp/View/User/Services/ApplyForClass/ChooseClassrom.jsp#select_classroom">课室申请</a></li>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="select_classroom">
				<s:if test="#request.msg != null">
					<script>alert('<%=request.getAttribute("msg")%>')</script>
				</s:if>
				
				<form action="/GDUTHackerSpace/user/classapply/query" method="post" id="select_classroom_form">
					<fieldset>
						<legend>请在此选择选择申请时间</legend>
						
						<label>申请日期：<input type="date" id="date" name="date" required autofocus /></label>
						<label>申请时间段：
						<select name="status" id="status">
							<option value="1">早上</option>
							<option value="2">下午</option>
							<option value="3">晚上</option>
							<option value="4">全天</option>
						</select></label>
						<input type="submit" value="查询" id="submit" class="button_normal"/>
					</fieldset>
				</form>
<%
		List<Classroom> classrooms = (List<Classroom>)request.getAttribute("classrooms");
		String status = (String)request.getAttribute("status");
		String date = (String)request.getAttribute("date");

		if (classrooms != null && classrooms.size() > 0 ) {
%>
				<div id="select_classroom_room">
					<h2>请在此选择你要的课室</h2>
				
				<%
				for(Classroom c : classrooms) {
				%>
					<div class="one_classroom">
						<span class="position">课室：<%=c.getPosition() %></span>
						<span class="num"><%=c.getNum() %></span>

					<a href="/GDUTHackerSpace/Web/jsp/View/User/Services/ApplyForClass/ApplyClassroom.jsp?id=<%=c.getId() %>&s=<%=status%>&d=<%=date %>&n=<%=c.getNum()%>">预约</a>	
					</div>
<%          	}} else { %>
					<div class="no_classroom"><span>当前时间内无可预约教室</span></div>
			<%} 
%>
			</div>

				</div>
			</div>
	<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	<script type="text/javascript">
		addLoadEvent(function(){
			EventUtil.addHandler(document.getElementById("select_classroom_form"),"submit",function(event){
					var event = EventUtil.getEvent(event);
					var target = EventUtil.getEvent(event);
				
			});
		});
	</script>
</body>
</html>