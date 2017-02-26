<%@page import="com.hackerspace.util.TimeParseUtil"%>
<%@page import="com.hackerspace.util.StringUtil"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hackerspace.model.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<%
	String tag = (String) request.getAttribute("tag");
	if (tag == null) 
		tag = "0";
%>
	<head>
		<meta charset="UTF-8">
		<title>项目路演</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
		<%
		PictureService p=new PictureService();
		String url=	p.getOnePicture("16");
		if(url==null||"".equals(url)) {
			url="/GDUTHackerSpace/Web/images/column/pictures1.png";//默认的图片
		}
		%>
		<img src="<%=url %>" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2>主页 > 创客项目</h2>
					<h1>项目路演</h1>
				</div>
			</div>
		</div>
		<div id="section" class="wrapper">
			<div id="sec_sidebar">
				<div id="sec_sidebar_head">
					<h2>创客项目</h2>
					<div id="sec_temp"></div>
				</div>
				<ul>
					<li><a href="/GDUTHackerSpace/user/project/query?tag=0">创新项目</a></li>
					<li><a href="/GDUTHackerSpace/user/project/query?tag=1">创业项目</a></li>
					<li class="current"><a href="/GDUTHackerSpace/user/projectshow/querys">项目路演</a></li>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			
			<div id="sec_article">
				<div id="news_list">
<%
	PageElem<ProjectShow> pe = (PageElem<ProjectShow>) request.getAttribute("pageElem");
	
	if ( pe != null && pe.getPageElem().size() >= 1 ) {
%>				
<%
	for (ProjectShow ps : pe.getPageElem()) {
%>
							<div class="one_news ov">
								<h1>
									<a href="/GDUTHackerSpace/user/projectshow/query?id=<%=ps.getId() %>"><%=ps.getTitle() %></a>
								
								

							 </h1>	
							 <p class="fr cr">编辑时间：<span class="time"><%=TimeParseUtil.TimestamptoStr(ps.getTime()).substring(0, 16) %></span></p>
							 </div>
		
						<%} %>
				<%}else { %><p id="no_content">当前没有项目</p><%} %>
					</div>
				</div>
				
					<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		if(cp>1) {
				%>
		<a href="/GDUTHackerSpace/user/project?tag=<%=tag %>&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
				<%
			}
		 int i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
				%>
		<a href="/GDUTHackerSpace/user/project?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
				<%	
				}
			} else {//分页大于十页的情况
		String selected1 =(1==cp)? "selected":"";
		String selected2 =(2==cp)? "selected":"";
	
			%>
		<a href="/GDUTHackerSpace/user/projectshow?cp=1" class="<%=selected1 %>">1</a>		
		<a href="/GDUTHackerSpace/user/projectshow?cp=2" class="<%=selected2 %>">2</a>		
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
		<a href="/GDUTHackerSpace/user/projectshow?cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
	<a href="/GDUTHackerSpace/user/projectshow?cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
	<a href="/GDUTHackerSpace/user/projectshow?cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
		}
		if(cp<tp) {
			%>
	<a href="/GDUTHackerSpace/user/projectshow?cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
			<%
		}
		%>
	
			</div>
		</div>
			
	</body>
</html>