<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑项目页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/config.js"></script>

	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_projects"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 创客项目管理 > 添加项目</h1>
				<div id="col_area">
					<div id="col_area_header">
						<a href="/GDUTHackerSpace/manager/project/query?currentPage=1&status=1&tag=1">已发布项目</a>
						<a href="/GDUTHackerSpace/manager/project/query?currentPage=1&status=0&tag=1" >未发布项目</a>
						<a href="/GDUTHackerSpace/manager/project/befcre" class="current">添加项目</a>
					</div>
					
					<%
						// 获取错误提示信息
						String eTitle = (String) request.getAttribute("title");
						String eContent = (String) request.getAttribute("content");
						String eTeam = (String) request.getAttribute("team");
					%>
					<s:form  id="news" action="create" method="post" enctype="multipart/form-data" namespace="/manager/project"> 
						<div>
							
							<s:if test="#request.eTitle != null">
								<p><s:property value="#request.eTitle"/></p>
							</s:if>							
							<p><s:property value="#request.title"/></p>
							<label for="project.title">项目名称：</label>
							<s:textfield id ="project.title" name="project.title" />
							
						</div>
						<div>  
							<!-- 错误提示 -->
							<s:if test="#request.eTeam != null">
								<p><s:property value="#request.eTeam"/></p>
							</s:if>							
							<p><s:property value="#request.title"/></p>
							<label for="teams">负责团队：</label>
					     	<s:select list="teams" name="project.teamId" listid="teams" listKey="id" listValue="name"  headerKey="0" headerValue="请选择团队"></s:select>  
				     	</div>
				     	<div id="form_project_tag" >  
					     	<label for="project.tag">选择类型：</label>
					     	<s:radio list="#{'0':'创新项目','1':'创业项目'}"  name="project.tag" id="project.tag" value="0"/>
				     	</div>
				     	<div  id="new_content_edi">
							<!-- 错误提示 -->
							<s:if test="#request.eContent != null">
								<p><s:property value="#request.eContent"/></p>
							</s:if>			     		
				     		<p><s:property value="#request.content"/></p>
					     	<label for="project.content">项目介绍：</label>
					     	<s:textarea id="project.content" name="project.content"></s:textarea> 
					     	
							<script type="text/javascript">CKEDITOR.replace('project.content');</script>
						</div>
				     	<div id="button_qun">
				     		<s:submit value="提交" id="submit"/>
							<button  type="submit" value="2" name="option">存为草稿</button>
							<button type="button" value="3" name="option" id="resee">预览</button>
							<button  type="reset" value="4" onclick="window.history.go(-1)" name="option">取消</button>	
						</div>		
				    </s:form>
				   
				  </div>
				  <p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			  </div>
		  </div>
		  <script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		  <script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
		<script type="text/javascript">
		function surePublish(){
			var type = document.getElementById("project.content");
			var title = document.getElementById("project.title");
			var content = CKEDITOR.instances.artContent.document.getBody().getText();
			if(type.value == "" ){
				alert("请选择文章种类");
			}
			if(content.trim()==""||title.value=="")
			{alert("文章尚未编辑完成");return false;}
			else return true;
		}
		
		$(function(){
			$('#resee').click(function(){
				//获取编辑框的内容
				var temp = CKEDITOR.instances.content;
				var text;
				if(temp){
					text = temp.getData();
				    testwin= open("", "testwin","status=no,menubar=yes,toolbar=no");
				    testwin.location = "no";
				    testwin.width = "800";
				    testwin.document.open();
				    testwin.document.write(text);
				    testwin.document.close();
					
				}
			});
		});
		
	</script>
	</body>
</html>