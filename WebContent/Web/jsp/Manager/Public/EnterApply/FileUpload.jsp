<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.ApplyFile" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>入驻文件上传页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		 <div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_things"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 办事服务管理 > 入驻申请管理</h1>
				<div id="col_area">
					<div id="col_area_table">
					
					<s:if test="#request.msg != null">
						<script>alert('<%=request.getAttribute("msg")%>');</script>
					</s:if>
 					<s:if test="applyFile != null">
					 <div id="file_show">
					 	<p>温馨提示：上传文件后成功后，可直接替换该文件</p>
					 	<p>文件名：<span><s:property value="applyFile.fileName"/></span></p>
					 	<p>上传时间：<span><s:date name="applyFile.fileTime" format="YYYY-MM"/></span></p>
					 	<p><a class="button_normal fl" href="/GDUTHackerSpace/apply/download?fileName=<s:property value='applyFile.fileName'/>">下载</a>
					 	<a class="button_normal fl" href="/GDUTHackerSpace/manager/enterapply/delete?id=1">删除</a></p>
					 </div>
					</s:if>
					<s:else>
				 	<form  action="/GDUTHackerSpace/manager/enterapply/upload" enctype="multipart/form-data" method="post">  
			     		<input name="file" type="file"   id="file" onchange="sendFile()" />  
	     				<button type="submit" class="button">上传文件</button>
	 		 		</form>  
		 		 	</s:else>
 		 	</div>
		 		 </div>
		 		 <p id="footer">Copyright © 2016 广东工业大学创客学院</p>
	 		 </div>
 		 </div>
	</body>
</html>