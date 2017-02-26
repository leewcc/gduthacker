<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>图片上传</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/common/jquery.Jcrop.css"	type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.min.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.Jcrop.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/picture-deal.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery-form.js" type="text/javascript"></script>		
	</head>
	<body>
<%
	String fileShowUrl="";
	Integer imgBoxSize=0;//原图展示边长
	Integer viewImgWidth=0;//缩略图展示宽
	Integer viewImgHeight=0;//缩略图展示长
	Integer imageHeight=0;
	Integer imageWidth=0;
	String belong="首页图片";
	Integer id=(Integer)request.getAttribute("id");
	fileShowUrl=(String)request.getAttribute("fileShowUrl");

	if(fileShowUrl==null) {
		fileShowUrl="Web/images/column/web.jpg";
	} else {
		belong=(String)request.getAttribute("belong");
		imgBoxSize= (Integer)request.getAttribute("imgBoxSize");
		viewImgHeight=(Integer)request.getAttribute("viewImgHeight");
		viewImgWidth=(Integer)request.getAttribute("viewImgWidth");
		imageWidth=(Integer)request.getAttribute("imageWidth");
		imageHeight=(Integer)request.getAttribute("imageHeight");
	}
%>
<p></p>
	<div id="wrapper">
		<jsp:include page="../../../Common/Common/ManagerSidebar.jsp">
			<jsp:param name="param_sidebar" value="sidebar_pictures"></jsp:param>
			<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
		</jsp:include>
		<div id="column">
			<jsp:include page="../../../Common/Common/ManagerHeader.jsp"></jsp:include>
			<h1 id="col_nav">首页 > 图片管理</h1>
			<div id="col_area">
					<%-- 上传预览文件 --%>
					<button type="button" style="float: right;border: 1px solid #c8c8c8;background: white;padding: 5px 30px;font-size: 16px;" onclick="window.history.go(-1);">返回上一层</button>

					<form action="uploadPhoto" id="photoForm" method="post" enctype="multipart/form-data">
						<p class="tips">第一步：上传图片<%=fileShowUrl!=null?"":"" %></p>
						<input type="hidden" name="belong" value="<%=belong %>"/>
						<input type="hidden" name="id" id="id" value="<%=id %>">
						<div id="send_file">
							<div>上传图片</div>
							 <input type="file"  id="file" name="file" onchange="uploadPhoto(this)"/>
							<!--<input type="file"  id="file" name="file" /> -->
						</div>
					</form>
					<div id="show_picture_area">
					<p class="tips">第二步： 裁剪图片</p>
						<div id="selected_picture">
							<span>选中的图片：</span>
							<div id="imgdiv" style="height:<%=imgBoxSize %>px;width:<%=imgBoxSize %>px">
									<img src="<%=fileShowUrl %>" alt="原图片" id="cropbox" 
									 width="<%=imageWidth %>px" height="<%=imageHeight %>px!important" />
							</div>
						</div>
						<div id="reshow_picture">
							<span>预览：</span>
							<div style="width:<%=viewImgWidth %>px; height: <%=viewImgHeight %>px; overflow: hidden;">
								<img id="preview" src="<%=fileShowUrl%>" width="<%=viewImgWidth %>px" height="<%=viewImgHeight %>px" />
							</div>
						</div>
					</div>
					<form action="uploadColumnPhoto" method="post" onsubmit="return checkSubmit()" id="submit_picture">
						<input type="hidden" id="x" name="x" /> 
						<input type="hidden" id="y" name="y" /> 	
						<input type="hidden" id="w" name="w" /> 
						<input type="hidden" id="h" name="h" />
						<input type="hidden" id="belong" name="belong" value="<%=belong %>"/>
						<input type="hidden" id="id" name="id" value="<%=id %>"/>
						<input type="hidden" id="imgBoxSize" name="imgBoxSize" value="<%=imgBoxSize %>"/>
						<input type="hidden" id="imgPath" name ="imgPath" value="<%=fileShowUrl %>"/>
						<button	type="submit">确认</button>
					</form>

		</div>
		<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</div>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>