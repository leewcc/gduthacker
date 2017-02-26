<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑路演页面</title>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
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
				<jsp:param name="param_sidebar" value="sidebar_projects"></jsp:param>
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
					<s:form action="create" method="post" enctype="multipart/form-data" namespace="/manager/projectshow"  id="news" >   
						<div>
							<s:if test="#request.eTitle != null">
								<p>请填写标题</p>
							</s:if>  
							<label for="projectShow.title">标题：</label>
							<s:textfield id = "projectShow.title" name="projectShow.title" />
						</div>
						<div id="new_content_edi">
							<s:if test="#request.eContent != null">
								<p>请填写内容</p>
							</s:if>
					     	<label for="projectShow.content">路演内容：</label>
					     	<s:textarea name="projectShow.content" id="projectShow.content"/>
							<script type="text/javascript">CKEDITOR.replace('projectShow.content');</script>
				     	</div>
			     		<div id="button_qun">
				     	<s:submit value="提交" id="submit"/>
				     	<button type="button" value="3" name="option" id="resee">预览</button>
				     	<button type="button" onclick="window.history.go(-1)">取消</button>
				     	</div>
				    </s:form>
			   </div>
		   </div>
	   </div>
	    <script type="text/javascript">
		function surePublish(){
			var type = document.getElementById("projectShow.title");
			var title = document.getElementById("projectShow.content");
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
				var temp = CKEDITOR.instances["projectShow.content"];
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