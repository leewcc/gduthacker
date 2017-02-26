<%@page import="com.hackerspace.model.Program"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hackerspace.model.News"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学校新闻</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
	</head>
	<body>
<% 
	String tag = (String)request.getAttribute("tag");
%>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
		<%
		PictureService ps=new PictureService();
		String url=	ps.getOnePicture("13");
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
			if(pp.getUrl().contains(tag)) {
				flag=i;
			} 
			i++;
		}
		%>
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
				
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="sec_article">
				<div id="news_list">
		<%
			PageElem<News> pe=(PageElem<News>)request.getAttribute("pageElem");
			List<News> nl=pe.getPageElem();
			if(nl == null){ 
%>
			<p id="no_content">当前没有新闻</p>
<%
				return ;
			}
			for(News n:nl) {
				Timestamp timestamp=n.getTime();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time= sf.format(timestamp);
%>

				<div class="one_news">
					<h1>
						<a href="showViewNews?id=<%=n.getId() %>"><%=n.getTitle() %>
							<span class="top_tag"><%=n.getIsTop()==1? "[置]":"" %></span>
						</a> 
						<p class="cr fr wd"><span class="time"><%=time.toString().substring(0,10) %></span></p>
					</h1>
				</div>
				<%
			}
%>
		</div>
	<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		if(cp>1) {
				%>
		<a href="showPublishedNews?tag=<%=tag %>&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
				<%
			}
			i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
				%>
		<a href="showPublishedNews?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
				<%	
				}
			} else {//分页大于十页的情况
		String selected1 =(1==cp)? "selected":"";
		String selected2 =(2==cp)? "selected":"";
	
			%>
		<a href="showPublishedNews?tag=<%=tag %>&cp=1" class="<%=selected1 %>">1</a>		
		<a href="showPublishedNews?tag=<%=tag %>&cp=2" class="<%=selected2 %>">2</a>		
			<%
			i=cp-2;
			if(i>3) {
				%>
			<span>...</span>	
				<%
			}else {
			i=3;		
				}
			for(;i<cp+2&&i<tp-1;i++) {
			selected =(i==cp)?"selected":"";
			%>
		<a href="showPublishedNews?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
			<%
			}
			if(i<tp-1) {
				%>
		<span>...</span>
				<%
			}
		String selectedt1=(cp==tp-1) ?"selected":"";
		String selectedt2=(tp==cp)?"selected":"";
		%>
	<a href="showPublishedNews?tag=<%=tag %>&cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
	<a href="showPublishedNews?tag=<%=tag %>&cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
		}
		if(cp<tp) {
			%>
	<a href="showPublishedNews?tag=<%=tag %>&cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
			<%
		}
		%>
	
			</div>
		</div>
	</div>
	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>