<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>上传</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		 <div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_things"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="4"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 办事服务管理 > 入驻申请管理</h1>
				<div id="col_area">
					<div id="col_area_apply_classroom" class="sub_f">
					<div class="fl">
						<p>申请单位：<s:property value="craApply.team"/></p>
						<p>申请人：<s:property value="craApply.user"/></p>
						<p>申请课室：<s:property value="craApply.classroom.num"/>
					</div>
					<div class="fr">
						<p>申请人电话：<s:property value="craApply.contact"/></p>
						<p>申请日期：<s:property value="craApply.date"/>&nbsp;<%=(String) request.getAttribute("status")%></p>
					</div>
					<p id="reason">申请理由：<s:property value="craApply.reason"/></p>	
						<div class="button_qun clear">
							<s:if test="craApply.isPass != -1">
								<a href="/GDUTHackerSpace/manager/classapply/update?id=<s:property value="craApply.id"/>&pass=1">通过</a>
								<a href="/GDUTHackerSpace/manager/classapply/update?id=<s:property value="craApply.id"/>&pass=0">不通过</a>
							</s:if>
							<s:else>
								<p>状态：<s:property value="#request.passed"/></p>
							</s:else>
						</div>
					</div>
				</div>
				 <p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
	</body>
</html>