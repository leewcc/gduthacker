<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.hackerspace.model.User" %>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>填写课室申请信息</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
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
					<li><a href="javascript:alert('该功能尚未开放');">课程申请</a></li>
					<li class="current"><a href="/GDUTHackerSpace/Web/jsp/View/User/Services/ApplyForClass/ChooseClassrom.jsp">课室申请</a></li>
					<li><a href="javascript:alert('该功能尚未开放');">工作室申请</a></li>
					<li><a href="javascript:alert('该功能尚未开放');">资讯留言</a></li>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="select_classroom">
	<%
		String id = request.getParameter("id") != null ? request.getParameter("id") : (String) request.getAttribute("id");
		String status = request.getParameter("s") != null ? request.getParameter("s") : (String)request.getAttribute("status");
		String date = request.getParameter("d") != null ? request.getParameter("d") : (String)request.getAttribute("date");
		String classroomNum = request.getParameter("n") != null ? request.getParameter("n") : (String) request.getAttribute("num");
		User user = (User)session.getAttribute("user");
 %>

				<s:form id="apply_classroom_form" action="apply" method="post" enctype="multipart/form-data" namespace="/user/classapply">
				<fieldset>
				<legend>在此填写课室 申请必备信息</legend>
				<div class="fl">
				<%--内置的标签不能使用required和autofocus = = --%>
					<s:property value="#request.eTeam"/>
					<label for="classroomApply.team">申请单位：</label><s:textfield id ="classroomApply.team" name="classroomApply.team" placeholder="申请单位" />
					<s:property value="#request.eUser"/>
					<label for="classroomApply.user">申请人：</label><s:textfield id = "classroomApply.user" name="classroomApply.user"  placeholder="申请人" />
					<s:property value="#request.eReason"/>
					<label>申请理由：</label><s:textarea id = "classroomApply.reason" name="classroomApply.reason" />
				</div>
				<div class="fr">
					<s:property value="#request.eContact"/>
					<label for="classroomApply.contact">申请人电话：</label><s:textfield id = "classroomApply.contact" name="classroomApply.contact"  placeholder="申请人电话" />
					<label>申请日期：</label>
					<input type="hidden" name="date" value="<%=date%>">
					<p><%=date %>
					<%if(status.equals("1")){%>
					<span>上午</span>
					<%}else if(status.equals("2")) {%>
					<span>下午</span>
					<%}else if(status.equals("3")) {%>
					<span>晚上</span>
					<%}else if (status.equals("4")) {%>
					<span>全天</span>
					<%} %>
					</p>
				<!-- <label>申请课室：</label><p><%--=position --%><%--=classroomNum --%></p> -->
				<label>申请课室：</label><p><%=classroomNum %></p>	
				<input type="hidden" name="classroomNum" value="<%=classroomNum %>"/>
				</div>
					<input type="hidden" name="status" value="<%=status%>">
					<input type="hidden" name="roomId" value="<%=id%>">
				<div class="button_qun">
					
					<button type="button" onclick="window.history.go(-1)">取消</button>
					<button type="submit" >提交</button>
				</div>
					</fieldset>
				</s:form>
			</div>
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
	</body>
</html>