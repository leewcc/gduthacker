<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑合作</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-form.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/config.js"></script>
		<style type="text/css">
			.haha{
				width: 100%;
				height: 100%;
				position: absolute;
			}
		</style>
	</head>
	<body>
	<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_coop"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="0"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 校企合作 > 编辑合作 </h1>
				<div id="col_area">
<% 
		String tag = (String)request.getAttribute("tag");
		if(tag==null) {tag="3";} 
%>					
				<div id="col_area_header">
					<a href="showManagerCooperation?tag=0&cp=1">合作项目</a>
					<a href="showManagerCooperation?tag=1&cp=1">合作企业</a>
					<a href="showCooperationDrafts?cp=1" >草稿箱</a>
					<a href="prepareEditorCooperation" class='current'>新增合作</a>
				</div>
					<form id="news" action="saveCooperation" method="post"  onSubmit="return surePublish();">
						<%--制作文章 --%>	
						<div class="article_full">
							<label for="title">文章标题：</label>
							<input type="text" name="title" id="title"/>
						</div>
						<div>
							文章分类：
								<label><input type="radio" class="radio" name="tag" value="0" checked/>合作项目</label>
								<label><input type="radio" name="tag" value="1"/>合作企业</label>
						</div>
						<div>
							<label for="url">合作的链接网页</label>
							<input type="text" name="url" id="url" value="http://"/>
							<label for="artContent">文章内容：</label>
							<textarea id="content" name="content"></textarea> 
							<script type="text/javascript">CKEDITOR.replace('content');</script>
						</div>
						<div id="button_qun">
							<button type="submit" value="1" name="option">发布</button>
							<button  type="submit" value="2" name="option">存为草稿</button>
							<button type="button" value="3" name="option" id="resee">预览</button>
							<button  type="reset" value="4" onclick="window.history.go(-1)" name="option">取消</button>	
						</div>		
					</form>
				</div>
			</div>

	
	<script type="text/javascript">
		function surePublish(){
			var type = document.getElementById("tag");
			var title = document.getElementById("title");
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
				    testwin.document.open();
				    testwin.document.write(text);
				    testwin.document.close();
					
				}
			});
		});
		
	</script>
</body>
</html>