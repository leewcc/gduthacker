<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="freemarker.template.SimpleDate"%>
<%@page import="com.hackerspace.model.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>一篇新闻</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>

		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-form.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/API/ckeditor/config.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_news"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 新闻管理  > 修改新闻 </h1>
				<div id="col_area">
	<% 
		News n=(News)request.getAttribute("news");
		Timestamp timestamp=n.getTime();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sf.format(timestamp);
	%>
					<form id="news" action="saveNews" method="post"  onSubmit="return surePublish();">
						<%--制作文章 --%>	
						<input type="hidden" value="<%=n.getId() %>" id="id" name="id"/>
						<p>该文章上次编辑时间是<%=time %></p>
						<div >
							<label for="title">文章标题：</label>
							<input type="text" name="title" id="title" value="<%=n.getTitle()%> "/>
						</div>
						<div>
							文章分类：
								<label><input type="radio" class="radio" name="tag" value="0" <%=n.getTag()==0?"checked":"" %>/>学校新闻</label>
								<label><input type="radio" name="tag" value="1" <%=n.getTag()==1?"checked":"" %>/>活动新闻</label>
						</div>
						<div>
						<textarea id="content" name="content"><%=n.getContent() %></textarea> 
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
		</div>
	<%-- 进行预览 --%>
			<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
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