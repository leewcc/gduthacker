<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.model.Program"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程体系</title>
<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
</head>
<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
		<%
		PictureService ps=new PictureService();
		String url=	ps.getOnePicture("15");
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
		Program p=l.get(5);
		ArrayList<Program> ll=(ArrayList<Program>) p.getPrograms();	
		String curl=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);//最后路径的名字
		int i=0,flag=0;
		for(Program pp:ll) {
			if(pp.getUrl().contains(curl)) {
				flag=i;
			} 
			i++;
			
		}
		%>
		<img src="<%=url %>" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2><a href="showHomePage">主页 </a>
					> <a href="<%=p.getUrl() %>"><%=p.getName() %></a></h2>
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
							<li class="current" ><a href="<%=ll.get(flag).getUrl() %>">
								<%=ll.get(flag).getName() %>	
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
				<!-- 	<li class="current"><a href="/GDUTHackerSpace/Web/jsp/View/Public/Education/AboutCourse.jsp">课程体系</a></li>
					<li><a href="showPublishedEducation?tag=0">讲座论坛</a></li>
					<li><a href="showPublishedEducation?tag=1">实践实训</a></li> -->
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="sec_article">
				<div class="sec_article_area" >
					<div class="article_area_title">
						<h2>设置理念</h2>
					</div>
					<div class="article_area_content">
						<p>点面结合、创新创业课程融合、学科专业融合、理论实训融合；</p>
						<p>培养学生创新创业精神、意识、能力、素质。</p>
					</div>
				</div>
				<div class="sec_article_area" >
					<div class="article_area_title">
						<h2>课程结合</h2>
					</div>
					<div class="article_area_content">
						<p>低年级：采取通识教育与精英培育相结合，面上覆盖与点上突破相结合，低年级撒网式培养，以大班讲座、课程教学为主;</p>
						<p>高年级：以创新、创业实践班为载体，以小型讲座或创新创业研讨、参加实习实训及创新创业实践为主。</p>
						<p><img src="/GDUTHackerSpace/Web/images/temp/crouse.png" alt="" /></p>
					</div>
				</div>
			</div>
			</div>
			<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>