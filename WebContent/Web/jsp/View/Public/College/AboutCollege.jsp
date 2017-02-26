<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.hackerspace.model.Program"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>学院简介</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
		<%
		//处理 图片
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
		String curl=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));//最后路径的名字
		int i=0,flag=0;
		for(Program pp:ll) {
			if(pp.getUrl().contains(curl)) {
				flag=i;
			} 
			i++;
			System.out.println("curl:"+curl);
			System.out.println("ggurl:"+pp.getUrl());
		}
		%>
			<img src="<%=url %>" alt="栏目图片"/>
			<div id="header_column">
				<div class="wrapper">
					<h2><a href="showHomePage">主页</a> > 
					<a href="<%=p.getUrl() %>"><%=p.getName() %></a></h2>
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
						if(pp.getUrl().contains(curl)) {
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
					}
				%>
				<!-- 	<li class="current"><a href="#sec_article">学院介绍</a></li>
					<li><a href="AboutOrganization.jsp#sec_article">机构设置</a></li> -->
				
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			<div id="sec_article">
				<div class="sec_article_area" >
					<div class="article_area_title">
						<h2>学院简介</h2>
					</div>
					<div class="article_area_content" id="article_school">
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广东工业大学坚持“与广东崛起共成长、为广东发展作贡献”的办学理念，不断创新人才培养模式，努力为学生“植入创新因子，播种创业种子”，实现以“创新引领创业，创业带动就业”的创新创业工作目标。尤其是把创新创业教育和实践能力培养有效贯穿人才培养全过程，探索出一条面向产业锻造学生创新创业能力，面向社会构筑学生创新创业实践平台，面向粤港共建创新创业学院（创客学院）的协同创新创业教育特色之路。</p>
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2014年6月，揭牌成立了<em class="emphasize">广东工业大学创新创业学院</em>，2015年成立 “ <em class="emphasize">创客学院</em> ”，与创新创业学院合署办公，统筹规划创新创业教育，积极借助与香港科技大学联合承办第十四届“挑战杯”全国大学生课外学术科技作品竞赛的契机，推进两岸四地区域政府、风投基金、行业协会的共建模式。</p>
					</div>
				</div>
				<div class="sec_article_area">
					<div class="article_area_title">
						<h2>学院职能</h2>
					</div>
					<div class="article_area_content" id="article_school_part_two">
					<img src="/GDUTHackerSpace/Web/images/temp/images/schoolv2_03.png"/>
					<!-- 
						<ol>
							<li>统筹协调学生创新精神和实践能力的理论教育、实践训练；</li>
							<li>统筹协调学生创业意识和能力的教育、实训，和创新创业项目的孵化和转化；</li>
							<li>对接校内外创新创业教育、实践和孵化平台；</li>
							<li>对接引进港澳台等地优秀大学生创新创业项目与我校学生联合孵化和转化。</li>
						</ol>
					 -->
					</div>
				</div>
				<div class="sec_article_area">
					<div class="article_area_title">
						<h2>使命定位</h2>
					</div>
					<div class="article_area_content">
						<p><b>人才培育苗圃：</b>国内领先的创新创业人才培育苗圃；</p>
<p><b>粤港交流平台：</b>有较大影响的社会及企业精英和粤港学生的创新创业交流平台；</p>
<p><b>创业示范基地：</b>科技创新成果产业化及创业实践的示范孵化基地；建设国家级创业带动就业孵化基地;</p>
<p><b>创新研究中心：</b>创新创业科学研究及教育研究中心。</p>

					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	</body>
</html>