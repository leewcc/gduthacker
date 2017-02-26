<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更换个人头像</title>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.min.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery.Jcrop.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/picture-deal.js" type="text/javascript"></script>
		<script src="/GDUTHackerSpace/Web/js/common/jquery-form.js" type="text/javascript"></script>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/common/jquery.Jcrop.css"	type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</head>
<body>
	<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp"></jsp:include>
		<div id="user_area">
			<h1 id="account_message">账户管理</h1>
			<div id="personal_message">
			<%-- 账户管理的侧边栏--%>
				<ul id="account_ul">
					<li class="current"><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/UserMess.jsp">查看个人资料</a></li>
					<li><a href="/GDUTHackerSpace/Web/jsp/User/Public/User/ModifyPassByPass.jsp">修改密码</a></li>
					<li><a href="FindSecurities">修改密保</a></li>
					<li><a href=""></a></li>
			</ul>

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
<%-- 
	<div id="wrapper">
		<jsp:include page="../../../Common/Common/ManagerSidebar.jsp">
			<jsp:param name="param_sidebar" value="sidebar_users"></jsp:param>
			<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
		</jsp:include> --%>
		<div id="column">
<%-- 			<jsp:include page="/GDUTHackerSpace/Web/jsp/Common/Common/ManagerHeader.jsp"></jsp:include> 
			<h1 id="col_nav">首页 > 用户管理 > 个人头像</h1>
			<div id="col_area">
					<%-- 上传预览文件 --%>
					<div id="col_area">
					<form action="uploadUserPicture" id="photoForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="way" value="1" />
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
									<img src="<%=fileShowUrl %>" alt="原图片" id="cropbox" height="<%=imgBoxSize %>px"/>
							</div>
						</div>
				<div id="reshow_picture">
					<span>预览：</span>
					<div style="width:<%=viewImgWidth %>px; height: <%=viewImgHeight %>px; overflow: hidden;">
						<img id="preview" src="<%=fileShowUrl%>" width="<%=viewImgWidth %>px" height="<%=viewImgHeight %>px" />
					</div>
				</div>
			</div>
			<form action="updateUserPicture" method="post" onsubmit="return checkSubmit()" id="submit_picture">
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
<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
</body>
</html>