<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>上传头像</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/common/jquery.Jcrop.css"	type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css"/>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
			<div id="column_pic_re">
				<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
			</div>
			<div id="user_register" class="wrapper">
			<h2>用户注册</h2>
<%
	String fileShowUrl="";
	Integer imgBoxSize=0;//原图展示边长
	Integer viewImgWidth=0;//缩略图展示宽
	Integer viewImgHeight=0;//缩略图展示长
	Integer id=(Integer)request.getAttribute("id");
	fileShowUrl=(String)request.getAttribute("fileShowUrl");

	if(fileShowUrl==null) {
		fileShowUrl="/GDUTHackerSpace/Web/images/portrait/1.png";
	} else {
		imgBoxSize= (Integer)request.getAttribute("imgBoxSize");
		viewImgHeight=(Integer)request.getAttribute("viewImgHeight");
		viewImgWidth=(Integer)request.getAttribute("viewImgWidth");
	}
%>
			<div id="col_area">
				<%-- 上传预览文件 --%>
				<p>第三步：上传个人头像</p>
				<p class="illegal">${fieldErrors['picture'][0]}</p>
				<form action="uploadUserPicture" id="photoForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="way" id="way" value="<%=3 %>">
					<div id="send_file">
						<div>上传图片</div>
						<input type="file"  id="file" name="file" onchange="uploadPhoto(this)"/>
					</div>
				</form>
				<%if(!fileShowUrl.equals("") && !fileShowUrl.equals("/GDUTHackerSpace/Web/images/portrait/1.png")){ %>
				<div id="show_picture_area">
					<div id="selected_picture">
						<span>选中的图片：</span>
						<div id="imgdiv" style="height:<%=imgBoxSize %>px">
								<img src="<%=fileShowUrl %>" alt="原图片" id="cropbox" height="<%=imgBoxSize %>px"/>
						</div>
					</div>
					<div id="reshow_picture">
						<span>预览：</span>
						<div style="width:<%=viewImgWidth %>px; height: <%=viewImgHeight %>px; overflow: hidden;">
							<img id="preview" src="<%=fileShowUrl%>" width="<%=viewImgWidth %>px" height="<%=viewImgHeight %>px" />
						</div>
					</div>
					<form action="InputPicture" method="post" onsubmit="return checkSubmit()" id="submit_picture">
						<input type="hidden" id="x" name="x" /> 
						<input type="hidden"  id="y" name="y" /> 	
						<input type="hidden" id="w" name="w" /> 
						<input type="hidden" id="h" name="h" />
						<input type="hidden" id="imgBoxSize" name="imgBoxSize" value="<%=imgBoxSize %>"/>
						<input type="hidden" id="imgPath" name ="imgPath" value="<%=fileShowUrl %>"/>
						<button	type="submit">确认</button>
					</form>
				</div>
				<%} %>
			</div>
		</div>

	 	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	 	<script src="/GDUTHackerSpace/Web/js/common/jquery.min.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.Jcrop.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/picture-deal.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery-form.js" type="text/javascript"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</body>
</html>