<%@page import="com.hackerspace.model.Program"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>机构设置</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
	<div id="column_pic">
		<%
		//处理图片
		PictureService ps=new PictureService();
		String url=	ps.getOnePicture("11");
		if(url==null||"".equals(url)) {
			url="/GDUTHackerSpace/Web/images/column/pictures1.png";//默认的图片
		}
		
		//处理 侧边栏
		ArrayList<Program> l=(ArrayList<Program>) request.getServletContext().getAttribute("programList");
		if(l==null) return ;
		Program p=l.get(0);
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
			<h2><a href="showHomePage">主页</a> > <a href="<%=p.getUrl() %>"><%=p.getName() %></a></h2>
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
							System.out.println("横条");
							%>
							<li class="current"><a href="<%=pp.getUrl() %>">
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
					%>
					
					<%
					}
				%>
				<!-- 	<li><a href="AboutCollege.jsp#sec_article">学院介绍</a></li>
					<li class="current"><a href="#sec_article">机构设置</a></li> -->
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="sec_article">
				<div class="sec_article_area" >
					<div class="article_area_title">
						<h2>机构设置</h2>
					</div>
					<div class="article_area_content">
						<p>院  长：米银俊（校党委副书记）</p>
						<p>常务副院长：张育广（校团委书记）</p>
						<p>   
						<ul id="area_content_list">副院长： 
							<li>杨文斌（教务处副处长） </li>
						    <li>刘琼辉（学生处副处长、就业指导中心主任）</li>
							<li>唐建伟（研究生院副院长）</li>
							<li>于兆勤（教授，硕士生导师）</li>
						</ul>
						<p>院长助理：许金镇</p>
						<h2>创新创业教育及实践指导委员会成员单位</h2>

						<ul>
							<li>团委</li>
							<li>教务处</li>
							<li>学生处</li>
							<li>管理学院</li>
							<li>实验教学部</li>
							<li>校友工作及社会联络办公室</li>
							<li>科技处</li>
							<li>研究生院</li>
							<li>国际合作与交流处（港澳台事务办公室）</li>
							<li>实验室与设备管理处</li>
							<li>广东工大资产经营有限公司</li>
							<li>通识教育中心</li>
							<li>广州国家现代服务业集成电路设计产业化基地</li>
							<li>东莞华南设计创新院</li>
							<li>佛山广工大数控装备协同创新研究院</li>
							<li>其他校外有关单位</li>
						</ul>
	
					</div>
				</div>
				
			</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	</body>
</html>