<%@ page import="com.hackerspace.model.Power"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.User" %>
<%
	User user = (User)session.getAttribute(request.getParameter("role"));
%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>使用旧密码修改密码</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
<%
	if(user.getRole() >= 4){
%>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
<%
	}else{
%>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
<%
	}
%>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
<% 		
		Power p = user.getPower();
		System.out.print(p);
			if(user.getRole() >= 4){
%>
		<div id="wrapper">
			<jsp:include page="../../../../Manager/Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_account"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../../../Manager/Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 账户管理 > 修改密码 </h1>
				<div id="col_area">
<%
			}else{
				
%>
<%-- 如果是前台用户包含在这个里面--%>
		<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
		<jsp:include page="../../Common/CommonSidebar.jsp"></jsp:include>
		<div id="user_area">
		<h1 id="account_message">账户管理</h1>
			<div id="personal_message">
			<%-- 账户管理的侧边栏v2.0-修改了修改密码的路径bug--%>
<!-- 					<ul id="account_ul"> -->
<!-- 						<li><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/UserMess.jsp">查看个人资料</a></li> -->
<!-- 						<li class="current"><a href="/GDUTHackerSpace/Web/jsp/View/User/Public/User/ModifyPassByPass.jsp">修改密码</a></li> -->
<!-- 						<li><a href="FindSecurities">修改密保</a></li> -->
<!-- 						<li><a href=""></a></li> -->
<!-- 					</ul> -->
<%
			}
%>
	<%--还没加验证功能 --%>
					<div id="col_area_form">
						<form action="UpdatePass" method="post" id="col_area_form_form">
							<div>
								<p class="illegal">${message}</p>
								<label for="oldPass">旧密码 : </label>
								<input type="password" name="oldPass" id="oldPass" autofocus/>
								<p class="illegal">${fieldErrors['oldPass'][0]}</p>
							</div>
							<div>
								<label for="newPass">新密码 : </label>
								<input type="password" name="newPass" id="newPass"/>
								<p class="illegal">${fieldErrors['passwordN'][0]}</p>
							</div>
							<div>
								<label for="newPassA">新密码 : </label>
								<input type="password" name="newPassA" id="newPassA" />
								<p class="illegal">${fieldErrors['passwordNA'][0]}</p>
							</div>
							<input type="hidden" name="role" value="<%=request.getParameter("role") %>" />
							<button type="submit">修改</button>
							<button type="button" onclick="window.history.go(-1)">取消</button> 	
						</form>
						
			<%--	这功能要改		<a href="/GDUTHackerSpace/Web/jsp/View/Public/User/ModifyPassByQues.jsp" id="col_area_upd_pass">使用密保修改</a> --%>
					</div>
<%
	if(user.getRole() >= 4){
%>
				</div>
			</div>
		</div>
<%
	}else{

		
%>
				</div>
			</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
<%

	}
%>
		<!-- 
		<script type="text/javascript">
			addLoadEvent(function(){
				var temp = document.getElementById("col_area_form_form");
				var pattern =  /^[0-9|a-z|A-Z]*$/g;
				var p ;
				if(temp){
					var input = document.getElementById("oldPass"); 
					if(input.autofocus !== true){
						input.focus();
					}
					//一下的在target.parentNode应用多次
				//旧密码
					EventUtil.addHandler(input, "blur", function(event){
						event = EventUtil.getEvent(event);
						target = EventUtil.getTarget(event);
						var parent = target.parentNode; 
						if(parent.getElementsByTagName("p").length != 0){
							parent.removeChild(parent.getElementsByTagName("p")[0]);
							target.style.border = "";
						}
						//第一步，获取target元素
						if(target.value.trim()==""){
							if(target.parentNode.getElementsByTagName("p").length == 0){
								p = document.createElement("p");
								addClass(p,"illegal");
								p.appendChild(document.createTextNode("* 请填写旧密码"));
								//插入到input的下面
								target.parentNode.insertBefore(p, null);
								target.style.border = "1px solid red";

							}
						}else if((!(pattern.test(target.value)))){ //输入格式不正确的情况下
							p = document.createElement("p");
							addClass(p,"illegal");
							p.appendChild(document.createTextNode("* 你的输入格式不正确"));
							target.parentNode.insertBefore(p, null);
							target.style.border = "1px solid red";
						}
					});
					//新密码
					input = document.getElementById("newPass"); 
					EventUtil.addHandler(input, "blur", function(event){
						event = EventUtil.getEvent(event);
						target = EventUtil.getTarget(event);
						var parent = target.parentNode; 
						if(parent.getElementsByTagName("p").length != 0){
							parent.removeChild(parent.getElementsByTagName("p")[0]);
							target.style.border = "";
						}
						//第一步，获取target元素
						if(target.value.trim()==""){ //输入为空的情况下
							if(target.parentNode.getElementsByTagName("p").length == 0){
								p = document.createElement("p");
								addClass(p,"illegal");
								p.appendChild(document.createTextNode("* 请填写新密码"));
								target.parentNode.insertBefore(p, null);
								target.style.border = "1px solid red";
							}
						}else if(((!(pattern.test(target.value))))){ //输入格式不正确的情况下
								p = document.createElement("p");
								addClass(p,"illegal");
								p.appendChild(document.createTextNode("* 你的输入格式不正确"));
								target.parentNode.insertBefore(p, null);
								target.style.border = "1px solid red";
						}
					});
					//新密码A
					input = document.getElementById("newPassA"); 
					EventUtil.addHandler(input, "blur", function(event){
						event = EventUtil.getEvent(event);
						target = EventUtil.getTarget(event);
						var parent = target.parentNode; 
						if(parent.getElementsByTagName("p").length != 0){
							parent.removeChild(parent.getElementsByTagName("p")[0]);
							target.style.border = "";
						}
						//第一步，获取target元素
						if(target.value !== document.getElementById("newPass").value){
							if(target.parentNode.getElementsByTagName("p").length == 0){
								p = document.createElement("p");
								addClass(p,"illegal");
								p.appendChild(document.createTextNode("* 你的两次输入不一致"));
								target.parentNode.insertBefore(p, null);
								target.style.border = "1px solid red";

							}
						}
					});
				}
			});
		</script>
		 -->
	</body>
</html>