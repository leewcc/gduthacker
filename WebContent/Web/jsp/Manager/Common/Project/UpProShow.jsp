<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑路演页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/config.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_makers"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 创客项目管理 > 路演管理</h1>
				<div id="col_area">
					<div id="col_area_header">

					<a href="/GDUTHackerSpace/manager/projectshow/querys?status=1">已发布</a>
					<a href="/GDUTHackerSpace/manager/projectshow/querys?status=0">未发布</a>
					<a class="current" href="/GDUTHackerSpace/Web/jsp/Manager/Common/Project/CreProShow.jsp">添加路演</a>
					
				</div>
					<s:form action="updateSec" id="news" method="post" enctype="multipart/form-data" namespace="/manager/projectshow">   
						<div>
						<label for="projectShow.title">标题：</label><s:textfield id = "projectShow.title" name="projectShow.title"/>
						<s:hidden name="projectShow.id"></s:hidden>
						</div>
						<div id="new_content_edi">
				     	<label for="projectShow.content">路演内容：</label>
				     	<s:textarea name="projectShow.content" id="projectShow.content"/>
				     	<script type="text/javascript">CKEDITOR.replace('projectShow.content');</script>
				     	<s:hidden name="projectShow.status"></s:hidden>
				     	</div>
				     	<div id="button_qun">
				     	<s:submit  id="resee" value="提交"/>
				     	<button type="reset" value="4" onclick="window.history.go(-1)" name="option">取消</button>	
				     	</div>
				    </s:form>
			   </div>
		   </div>
	   </div>
	</body>
</html>