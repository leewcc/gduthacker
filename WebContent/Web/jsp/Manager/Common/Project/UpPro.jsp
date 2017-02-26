<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑项目</title>
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
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 创客项目管理 > 项目管理</h1>
				<div id="col_area">
					<s:form id="news" action="updateSec.action" method="post" enctype="multipart/form-data" namespace="/manager/project">   
						<div>  
							<label for="project.title">项目名称：</label>
							<s:textfield id ="project.title" name="project.title" />
						</div>
						<div id="form_project_tag">
							<label for="tag">选择类型：</label>
							
							<s:if test='project.tag == "0"'>
							<input type="radio" value="0" name="tag" checked="true"/><label>创新项目</label>  
					     	<input type="radio" value="1" name="tag"/><label>创业项目</label>
				     		</s:if>
				     		<s:else>
				     		<input type="radio" value="0" name="tag" /><label>创新项目</label>  
					     	<input type="radio" value="1" name="tag" checked="true"/><label>创业项目</label>
				     		</s:else>
				     	</div>
				     	<div>  
							<label for="teams">负责团队：</label>
					     	<s:select list="teams" name="project.teamId" listid="teams" listKey="id" listValue="name"  headerKey="0" headerValue="请选择团队"></s:select>  
				     	</div>
				     	
				     	<div>
					     	<label for="project.content">项目介绍：</label>
					     	<s:textarea name="project.content" id="project.content"/>
					     	<script type="text/javascript">CKEDITOR.replace('project.content');</script>
				     	
						</div>
				     	<s:hidden name="project.id"></s:hidden>
				     	<s:hidden name="project.authorId"></s:hidden>
				     	<s:hidden name="project.status"></s:hidden>
				     	<s:hidden name="project.tag"></s:hidden>
				     	<div id="button_qun">
				     	<s:submit value="提交"/>
				     	<a id="cancel_button" href="javascript:window.history.go(-1)">取消</a>
				  		</div>
				    </s:form>
				    </div>
			   </div>
		   </div>
	</body>
</html>