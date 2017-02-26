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
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.min.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.Jcrop.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/picture-deal.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery-form.js" type="text/javascript"></script>
</head>
<body>
<div id="wrapper">
		<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
			<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
			<jsp:param name="param_sidebar_sec" value="3"></jsp:param>
		</jsp:include>
		<div id="column">
			<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
<%
	String fileShowUrl="";
	Integer imgBoxSize=0;//原图展示边长
	Integer viewImgWidth=0;//缩略图展示宽
	Integer viewImgHeight=0;//缩略图展示长
	Integer imageWidth=0;
	Integer imageHeight=0;
	Integer id=(Integer)request.getAttribute("id");
	fileShowUrl=(String)request.getAttribute("fileShowUrl");

	if(fileShowUrl==null || "".equals(fileShowUrl)) {
		fileShowUrl="/GDUTHackerSpace/Web/images/team/1.png";
	} else {
		imgBoxSize= (Integer)request.getAttribute("imgBoxSize");
		viewImgHeight=(Integer)request.getAttribute("viewImgHeight");
		viewImgWidth=(Integer)request.getAttribute("viewImgWidth");
		imageWidth=(Integer) request.getAttribute("imageWidth");
		imageHeight=(Integer) request.getAttribute("imageHeight");
	}
%>
			<h1 id="col_nav">首页 > 用户管理 > 团队LOG</h1>
			<div id="col_area">
					<%-- 上传预览文件 --%>
										<button type="button" style="float: right;border: 1px solid #c8c8c8;background: white;padding: 5px 30px;font-size: 16px;" onclick="window.history.go(-1);">返回上一层</button>
					<form action="uploadTeamPicture" id="photoForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" id="id" value="<%=id %>">
						<div id="send_file">
							<div>上传图片</div>
							<input type="file"  id="file" name="file" onchange="uploadPhoto(this)"/>
						</div>
					</form>
					<div id="show_picture_area">
						<div id="selected_picture">
							<span>选中的图片：</span>
							<div id="imgdiv" style="height:<%=imgBoxSize %>px">
									<img src="<%=fileShowUrl %>" alt="原图片" id="cropbox" height="<%=imageHeight %>px" width="<%=imageWidth %>px"/>
							</div>
						</div>
						<div id="reshow_picture">
							<span>预览：</span>
							<div style="width:<%=viewImgWidth %>px; height: <%=viewImgHeight %>px; overflow: hidden;">
								<img id="preview" src="<%=fileShowUrl%>" width="<%=viewImgWidth %>px" height="<%=viewImgHeight %>px" />
							</div>
						</div>
					</div>
					<form action="updateTeamPicture" method="post" onsubmit="return checkSubmit()" id="submit_picture">
						<input type="hidden" id="x" name="x" /> 
						<input type="hidden"  id="y" name="y" /> 	
						<input type="hidden" id="w" name="w" /> 
						<input type="hidden" id="h" name="h" />
						<input type="hidden" id="id" name="id" value="<%=id %>"/>
						<input type="hidden" id="imgBoxSize" name="imgBoxSize" value="<%=imgBoxSize %>"/>
						<input type="hidden" id="imgPath" name ="imgPath" value="<%=fileShowUrl %>"/>
						<button	type="submit">确认</button>
					</form>

		</div>
	</div>
</div>
</body>
</html>