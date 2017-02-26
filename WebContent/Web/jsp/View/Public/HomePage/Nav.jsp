<%@ page import="com.hackerspace.model.Program"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.User" %>
<%    User user = (User)session.getAttribute("user"); %>
<div class="supwrapper">
	<div class="wrapper">
		<div id="header">
			<a href="showHomePage" id="head_logo">
			 <img src="/GDUTHackerSpace/Web/images/coin/images/Logo_01.png" alt="创客logo" /> 
			</a>
			<div id="head_other">
				<a href="SelectQuestionView?page=1">留言板</a> 
<% 
	if(user != null){
		int count = (Integer)request.getAttribute("unread");
		if(count > 0){
%>
				<a href="/GDUTHackerSpace/Web/jsp/View/User/Message/MessageBox?page=1">你有<%=count %>条未读信息</a>
<%}%>
				<a href="/GDUTHackerSpace/Web/jsp/View/User/Common/CommonUserPage.jsp"><%=user.getName() %></a>
				<a href="/GDUTHackerSpace/Web/jsp/View/User/UserLogout">注销</a>
<%}else{%>
				<a href="/GDUTHackerSpace/Web/jsp/View/Public/User/Login.jsp">登录</a>
				<a href="/GDUTHackerSpace/Web/jsp/View/Public/User/Register.jsp">注册</a> 
<%}%>
			</div>
		</div>
	</div>
</div>
<div id="nav" class="supwrapper">
	<div class="wrapper">
		<ul id="nav_fir_ul">
<%
	ArrayList<Program> l=(ArrayList<Program>) request.getServletContext().getAttribute("programList");
	for(Program p:l) {
%>
			<li class="nav_first">
				<a href="<%=p.getUrl() %>"><%=p.getName() %></a>
				<ul class="nav_sub">
<%
	for(Program pp:p.getPrograms()) {
%>
						<li><a href="<%=pp.getUrl() %>"><%=pp.getName() %></a></li>
<%}%>
				</ul>
			</li>				
<%}%>
		
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="/GDUTHackerSpace/Web/View/College/AboutCollege.jsp">学院简介</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="/GDUTHackerSpace/Web/View/College/AboutCollege.jsp#sec_article">学院介绍</a></li> --%>
<%-- 					<li><a href="/GDUTHackerSpace/Web/View/College/AboutOrganization.jsp#sec_article">学院领导</a></li> --%>
<%-- 					<li><a href="#">机构设置</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="/GDUTHackerSpace/Web/View/Teacher/AboutTeacher.jsp">师资力量</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="/GDUTHackerSpace/Web/View/Teacher/AboutTeacher.jsp#sec_article">导师列表</a></li> --%>
<%-- 					<li><a href="#">导师资源</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="#">新闻动态</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="showPublishedNews?tag=0">学校新闻</a></li> --%>
<%-- 					<li><a href="showPublishedNews?tag=1">活动新闻</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="#">办事服务</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="#">入驻申请</a></li> --%>
<%-- 					<li><a href="#">课程申请</a></li> --%>
<%-- 					<li><a href="#">课室申请</a></li> --%>
<%-- 					<li><a href="#">咨询留言</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="#">创客教育</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="#">课程体系</a></li> --%>
<%-- 					<li><a href="#">讲座论坛</a></li> --%>
<%-- 					<li><a href="#">实践实训</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="#">创客项目</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="#">创新项目</a></li> --%>
<%-- 					<li><a href="#">创业项目</a></li> --%>
<%-- 					<li><a href="#">项目路演</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first"> --%>
<%-- 				<a href="#">校企合作</a> --%>
<%-- 				<ul class="nav_sub"> --%>
<%-- 					<li><a href="#">合作单位</a></li> --%>
<%-- 					<li><a href="#">合作项目</a></li> --%>
<%-- 				</ul> --%>
<%-- 			</li> --%>
<%-- 			<li class="nav_first" id="nav_no_fir"> --%>
<%-- 				<a href="#">创客之窗</a> --%>
<%-- 			</li> --%>
		</ul>
	</div>
</div>
	