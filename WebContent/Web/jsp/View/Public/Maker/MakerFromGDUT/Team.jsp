<%@page import="com.hackerspace.service.PictureService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.model.Program"%>
<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Team"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创客之窗页面</title>
<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
</head>
<body>
	<jsp:include page="../../HomePage/Nav.jsp"></jsp:include>
	<%
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
	6 创客之窗
	7 校企合作
	*/
	Program p=l.get(6);
	ArrayList<Program> ll=(ArrayList<Program>) p.getPrograms();	
	String curl=request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1);//最后路径的名字
	%>
	<div id="column_pic">
		<img src="<%=url %>" alt="栏目图片"/>
		<div id="header_column">
			<div class="wrapper">
				<h2><a href="showHomePage">主页</a> >
					<a href="<%=p.getUrl() %>"> <%=p.getName() %></a></h2>
				<h1><%=p.getName() %></h1>
			</div>
		</div>
	</div>
	<div  id="teams_list" class="subwrapper">
	<div class="wrapper">
<%
	PageElem<Team> pe = (PageElem<Team>)request.getAttribute("teams");
	List<Team> teams = pe.getPageElem();
	for(Team t : teams) {
%>
		<div class="one_team">
			<a href="SeeMaker?id=<%=t.getId() %>"><img src="<%=t.getPicture() %>" /></a>
			<h1><%=t.getName() %></h1>
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
		if(cp>1) {//有页码
				%>
		<a href="Maker?page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				int  i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="Maker?page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="Maker?page=1" class="<%=selected1 %>">1</a>		
			<a href="Maker?page=2" class="<%=selected2 %>">2</a>		
				<%
				i=cp-2;
				if(i>3) {//出现省略号
					%>
				<span>...</span>	
					<%
				}else {
				i=3;		
					}
				for(;i<cp+2&&i<tp-1;i++) {
				selected =(i==cp)?"selected":"";
				%>
			<a href="Maker?page=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
		<a href="Maker?page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="Maker?page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="Maker?page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
	</div>
	</div>
	<jsp:include page="../../HomePage/Footer.jsp"></jsp:include>
</body>
</html>