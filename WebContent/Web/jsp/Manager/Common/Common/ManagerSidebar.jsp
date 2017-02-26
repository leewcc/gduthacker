<%@ page import="com.hackerspace.model.Power"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hackerspace.model.User" %>
<% 
	String param_sidebar = request.getParameter("param_sidebar");
	String param_sidebar_sec = request.getParameter("param_sidebar_sec");
	User user = (User)session.getAttribute("manager");
	Power p = user.getPower();
	System.out.print(p);
%>
 <div id="sidebar">
 	<a href="showHomePage" id="side_logo"></a>
 	<ul id="side_first">
<% 
	if(p.isCanSystem()){
%>
	 	<li  <%=param_sidebar.equals("sidebar_system")?"class='current'":""%>><a href="#" class="side_fir_li">系统管理</a>
	 		<ul class="side_second">
	 		<!-- 	<li <%--param_sidebar_sec.equals("1")?"class='current'":"" --%>><a href="updateSystemName">设置系统名字</a></li> -->
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="managerLink">设置底部导航栏</a></li>
	 		<!--	<li <%--=param_sidebar_sec.equals("3")?"class='current'":"" --%>><a href="javascript:alert('该功能还在完善中');">设置首页</a></li>-->
	 			<li <%=param_sidebar_sec.equals("4")?"class='current'":"" %>><a href="managerProgram">栏目管理</a></li>
	 		</ul>
	 	</li>
<% 
	}
	if(p.isCanUser()){
%>
	 	<li <%=param_sidebar.equals("sidebar_users")? "class='current'":""%>><a href="#" class="side_fir_li">用户管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="SelectRegister?page=1">用户注册审核</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="SelectByRole?page=1&role=1">用户账户管理</a></li>
	 			<li <%=param_sidebar_sec.equals("3")?"class='current'":"" %>><a href="SelectTeam?pageD=1&pageE=1">创客团队管理</a></li>
	 		</ul>
	 	</li>
<% 
	}
	if(p.isCanPicture()){
%>
	 	<li   <%=param_sidebar.equals("sidebar_pictures")? "class='current'":""%>><a href="showManagerPhoto" class="side_fir_li_no">图片管理</a>
	 	</li>
<% 
	}
%>
	 	<li   <%=param_sidebar.equals("sidebar_news")? "class='current'":""%>><a href="showManagerNews" class="side_fir_li_no">新闻管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="showManagerNews?tag=0&cp=1">学校新闻</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="showManagerNews?tag=1&cp=1">活动新闻</a></li>
	 		</ul>
	 	</li>
	 	<li   <%=param_sidebar.equals("sidebar_projects")? "class='current'":""%>><a href="#" class="side_fir_li">创客项目管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="/GDUTHackerSpace/manager/project/query">项目管理</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="/GDUTHackerSpace/manager/projectshow/querys">路演管理</a></li>
	 		</ul>
	 	</li>
	 	<li   <%=param_sidebar.equals("sidebar_education")? "class='current'":""%>><a href="#" class="side_fir_li">创客教育管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="javascript:alert('该功能还在完善中');">课程管理</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="showManagerEducation?tag=0&cp=1" >讲座和实践管理</a></li>
	 		</ul>
	 	</li>
<% 
	if(p.isCanMessage()){
%>
	 	<li   <%=param_sidebar.equals("sidebar_messages")? "class='current'":""%>><a href="#" class="side_fir_li">留言管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="SelectMessage?page=1">用户留言管理</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="SelectQuestion?page=1">常见留言管理</a></li>
	 			<li <%=param_sidebar_sec.equals("3")?"class='current'":"" %>><a href="PrepareUser?role=1&page=1">发布留言管理</a></li>
	 		</ul>
	 	</li>
<% 
	}
%>
	 	<li <%=param_sidebar.equals("sidebar_coop")? "class='current'":""%>><a href="showManagerCooperation" class="side_fir_li ">校企合作管理</a>
	 	 	<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="showManagerCooperation?tag=0&cp=1">合作项目管理</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="showManagerCooperation?tag=1&cp=1">合作企业管理</a></li>
	 		</ul> 
	 	</li>

<% 
	if(p.isCanSerivce()){
%>
	 	<li   <%=param_sidebar.equals("sidebar_things")? "class='current'":""%>><a href="#" class="side_fir_li">办事服务管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="/GDUTHackerSpace/manager/enterapply/query">入驻申请管理</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href=" /GDUTHackerSpace/manager/classapply/querys">课程申请管理</a></li>
 				<li <%=param_sidebar_sec.equals("3")?"class='current'":"" %>><a href="/GDUTHackerSpace/manager/classroom/query">课室管理</a></li>
 				<li <%=param_sidebar_sec.equals("4")?"class='current'":"" %>><a href="/GDUTHackerSpace/manager/classapply/querys">课室申请管理</a></li>
	 			<li <%=param_sidebar_sec.equals("5")?"class='current'":"" %>><a href="javascript:alert('该功能还在完善中');">工作室申请管理</a></li>
	 		</ul>
	 	</li>
<% 
	}
%>
	 	<li   <%=param_sidebar.equals("sidebar_account")? "class='current'":""%>><a href="#" class="side_fir_li">账户管理</a>
	 		<ul class="side_second">
	 			<li <%=param_sidebar_sec.equals("1")?"class='current'":"" %>><a href="GoPassword">修改密码</a></li>
	 			<li <%=param_sidebar_sec.equals("2")?"class='current'":"" %>><a href="FindSecurities?role=manager">修改密保</a></li>
	 		</ul>
	 	</li>
 	</ul>
 </div>