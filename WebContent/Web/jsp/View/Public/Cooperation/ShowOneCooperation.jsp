<%@page import="com.hackerspace.service.PictureService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.model.Program"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="freemarker.template.SimpleDate"%>
<%@page import="com.hackerspace.model.Cooperation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<% 
	Cooperation c=(Cooperation)request.getAttribute("cooperation");
	Timestamp timestamp=c.getDate();
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time=sf.format(timestamp);
%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><%=c.getName() %></title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
	</head>
	<body>
	<%
		//处理 图片
		PictureService ps=new PictureService();
		String url=	ps.getOnePicture("18");
		if(url==null||"".equals(url)) {
			url="/GDUTHackerSpace/Web/images/column/pictures1.png";//默认的图片
		}
		//处理 侧边栏
		ArrayList<Program> l=(ArrayList<Program>) request.getServletContext().getAttribute("programList");
		if(l==null) return ;
		/*
		0  学院简介
		1 师资力量
		2 新闻动态
		3 办事服务
		4 创客教育
		5 创客项目
		6 学院之窗
		7 校企合作
		*/
		Program p=l.get(7);//一级
		ArrayList<Program> ll=(ArrayList<Program>) p.getPrograms();//二级	
		int i=0,flag=0;
		for(Program pp:ll) {
			if(pp.getUrl().contains(String.valueOf(c.getTag()))) {
				flag=i;
				break;
			} 
			i++;
		}
		%>
		<%=c.getStatus() %>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
				<div id="column_pic">		

			<img src="<%=url %>" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2><a href="showHomePage">主页</a>
					 >  <a href="<%=p.getUrl() %>"><%=p.getName() %></a></h2>
					 <h1><%=ll.get(flag).getName() %></h1>
				</div>
			</div>
		</div>
		<div id="section" class="wrapper">
			<div id="sec_sidebar">
				<div id="sec_sidebar_head">
					<h2><%=p.getName() %></h2>
					<div id="sec_temp"></div>
				</div>
				<ul>
				<%
					for(Program pp:ll) {
						if(pp.getName().equals(ll.get(flag).getName())) {
							%>
							<li class="current" ><a href="<%=pp.getUrl() %>">
								<%=pp.getName() %>	
							</a></li>							
							<%
						} else {
							%>
							<li><a href="<%=pp.getUrl() %>">
								<%=pp.getName() %>
							</a></li>
							<%
						}
					}
				%>
				<%-- 	<li <%=c.getTag()==0?"class='current'":""%>><a href="showPublishedCooperation?tag=0">合作项目</a></li>
					<li <%=c.getTag()==1?"class='current'":""%>><a href="showPublishedCooperation?tag=1">合作企业</a></li> --%>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="new_article">
				<h1><%=c.getName() %></h1>
				<h3>
					<span class="temp_mess">编辑者：<%=c.getAuthor().getName() %></span>
					<span class="temp_mess">编辑时间：<%=time.toString().substring(0,16) %></span>
				</h3>
				<div id="new_art_con">
				<%=c.getDes() %>
				</div>
			</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	</body>
</html>