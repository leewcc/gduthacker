<%@page import="com.hackerspace.model.Program"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="freemarker.template.SimpleDate"%>
<%@page import="com.hackerspace.model.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<% 
	News n=(News)request.getAttribute("news");
	Timestamp timestamp=n.getTime();
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time=sf.format(timestamp);
	PictureService	ps=new PictureService();
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
	Program p=l.get(2);
	ArrayList<Program> ll=(ArrayList<Program>) p.getPrograms();	
	int i=0,flag=0;
	for(Program pp:ll) {
		if(pp.getUrl().contains(String.valueOf(n.getTag()))) {
			flag=i;
		} 
		i++;
	}
%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><%=n.getTitle() %></title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>

	</head>
	<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
			<img src="<%=url %>" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2><a href="showHomePage">主页</a> >
					<a href="<%=p.getUrl() %>"> <%=p.getName() %></a></h2>
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
						System.out.println(pp.getUrl());
						if(pp.getId().equals(ll.get(flag).getId())) {
							%>
							<li class="current" ><a href="<%=pp.getUrl() %>">
								<%=pp.getName() %>	
							</a></li>							
							<%
						} else {
							%>
							<li class=""><a href="<%=pp.getUrl() %>">
								<%=pp.getName() %>
							</a></li>
							<%
						}
					}
				%>
					<%-- <li <%=n.getTag()==0?"class='current'":""%>><a href="showPublishedNews?tag=0">学校新闻</a></li>
					<li <%=n.getTag()==1?"class='current'":""%>><a href="showPublishedNews?tag=1">团队新闻</a></li> --%>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="new_article">
				<h1><%=n.getTitle() %></h1>
				<p class="show_title">
					<span class="temp_mess">编辑者：<%=n.getAuthorName() %></span>
					<span class="temp_mess">编辑时间：<%=time.toString().substring(0,10) %></span>
					<span class="temp_mess">点击量：<%=n.getClickNum() %></span>
				</p>
				<div id="new_art_con"><%=n.getContent() %>
				</div>
			</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	</body>
</html>