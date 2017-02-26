<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑新闻</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
	<div id="wrapper">
		<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
			<jsp:param name="param_sidebar" value="sidebar_news"></jsp:param>
			<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
		</jsp:include>
		<div id="column">
			<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
			<h1 id="col_nav">首页 > 新闻管理 > 编辑新闻</h1>
			<div id="col_area">
<% 
		String tag = (String)request.getAttribute("tag");
		if(tag==null) {tag="3";} 
%>					
				<div id="col_area_header">
					<a href="showManagerNews?tag=0&cp=1">学校新闻</a>
					<a href="showManagerNews?tag=1&cp=1">活动新闻</a>
					<a href="showDrafts?cp=1" >草稿箱</a>
					<a href="prepareEditorNews" class='current'>新增文章</a>
				</div>
					<form id="news" action="saveNews" method="post"  onSubmit="return surePublish();">
						<%--制作文章 --%>	
						<div >
							<label for="title">文章标题：</label>
							<input type="text" name="title" id="title"/>
						</div>
						<div>
							文章分类：
								<label><input type="radio" class="radio" name="tag" value="0" checked/>学校新闻</label>
								<label><input type="radio" name="tag" value="1"/>活动新闻</label>
						</div>
						<div id="new_content_edi">
							<label for="artContent">文章内容：</label>
							<textarea id="content" name="content"></textarea> 
							<script type="text/javascript">CKEDITOR.replace('content');</script>
						</div>
						<div id="button_qun">
							<button type="submit" value="1" name="option" id="publish">发布</button>
							<button  type="submit" value="2" name="option">存为草稿</button>
							<button type="button" value="3" name="option" id="resee">预览</button>
							<button  type="reset" value="4" onclick="window.history.go(-1)" name="option">取消</button>	
						</div>		
					</form>
					
					<%-- 进行预览 --%>
						
				</div>
				<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
			</div>
		</div>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/config.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-form.js"></script>
			
		<script type="text/javascript">
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
			addLoadEvent(function(){
				var button = document.getElementById("publish");
				EventUtil.addHandler(button,"click",function(){
					var type = document.getElementById("tag");
					var title = document.getElementById("title");
					var content = CKEDITOR.instances.artContent.document.getBody().getText();
					if(type.value == "" ){
						alert("请选择文章种类");
						
						event.preventDefault();
						return false;
					}
					if(content.trim()==""||title.value=="")
					{
						alert("文章尚未编辑完成");
						event.preventDefault();
						return false;
					}
					else return true;
				});
				
			});
			
		</script>
		
</body>
</html>