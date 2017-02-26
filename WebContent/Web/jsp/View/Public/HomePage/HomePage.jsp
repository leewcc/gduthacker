<%@page import="com.hackerspace.model.Picture"%>
<%@page import="com.hackerspace.model.Team"%>
<%@page import="com.hackerspace.model.News"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>创客空间</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/HomePage.css">
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<ul id="fixed_ul">
			<li id="er_wei_ma"><img src="/GDUTHackerSpace/Web/images/temp/er_wei_ma.jpg">
				<div id="prompt_message">
					<div id="prompt_area">
						<div id='temp_left'></div><div id='temp_right'></div>
					</div>
				</div>
			</li>
			<li id="to_top"><i class="fa fa-long-arrow-up" aria-hidden="true"></i></li>
		</ul>
		<div class="subwrapper" id="focus_Box">
			<ul>
			<%
			//轮播图片
			List<Picture> pl = (ArrayList<Picture>) request.getAttribute("pictureList");
			Picture[] pp=pl.toArray(new Picture[pl.size()]);
			%>
			<li id="1">
				<a href="#"><img width="0" height="0" alt="这个时代 你所追求的是什么？" src="<%=pp[0].getPictureUrl() %>" /></a>
				<p>
					<span>这个时代 你所追求的是什么？</span>
				</p>
			</li>
			<li id="2">
				<a href="#"><img width="600" height="150" alt="我们所追求的不是拥有一切，而是拥有值得的一切" src="<%=pp[1].getPictureUrl() %>" /></a>
				<p>
					<span>我们所追求的不是拥有一切，而是拥有值得的一切</span>
				</p>
			</li>
			<li id="3">
				<a href="#"><img width="1200" height="300" alt="一段旅程，两个城市，潮流正在被重塑" src="<%=pp[2].getPictureUrl() %>" /></a>
				<p>
					<span>一段旅程，两个城市，潮流正在被重塑</span>
				</p>
			</li>
			<li id="4">
				<a href="#"><img width="600" height="150" alt="你的眼光、激情、创意和内涵，也在重塑着城市的潮流" src="<%=pp[3].getPictureUrl() %>" /></a>
				<p>
					<span>你的眼光、激情、创意和内涵，也在重塑着城市的潮流</span>
				</p>
			</li>
			<li id="5">
				<a href="#"><img width="0" height="0" alt="在这里，抛开重重限制，释放真实自我" src="<%=pp[4].getPictureUrl() %>" /></a>
				<p>
					<span>在这里，抛开重重限制，释放真实自我</span>
				</p>
			</li>
		</ul>
	</div>

	<%-- 新闻 --%>
	<div class="supwrapper"  id="news_column_sub">
		<div class="wrapper" id="news_column">
		<h1>头条新闻 <span>news</span></h1>
			<%
				List<News>l=(ArrayList<News>)request.getAttribute("newsList");
			int i = 0;
				if(l.size()!=0) { 
				for(News n: l) {
					i++;
					%>
					<div class="one_new <%=i%3==0?"no_right":"" %>">
						<div class="time"><div><span class="day"><%=n.getTime().toString().substring(8,10) %></span>
						<span><%=n.getTime().toString().substring(0,7) %></span></div></div>
						<div class="new_content">
							<h2>
							<a href="showViewNews?id=<%=n.getId() %>"><%=n.getTitle() %></a>
							</h2>
							<p>
								<img src="<%=n.getImageUrl() %>" width="32px" height="60px"/>
							</p>
						</div>
					</div>
<%
				}
			}else{
%>	
					<p id="no_content" style="margin: 10px auto;text-align: center;">当前没有新闻</p>
<%
			}
%>
		</div>
	</div>
	<%--团队模块 --%>
	<div class="supwrapper" id="team_column">
	<div style="width: 1024px; position: relative;overflow: hidden;height: 400px;margin: 0 auto;">
<%
		List<Team> teams = (List<Team>)request.getAttribute("teams");
		i = 0;
		for(Team t : teams){
			i++;
%>
			<div id=<%="team"+i %>>
				<div class="team_pictures">
					<%--展示的图片 --%>
					<a href="SeeMaker?id=<%=t.getId() %>">
					<img class="team_own_picture" src="<%=t.getPicture() %>" alt=<%=t.getName() %>/>
					<%--泡泡 --%>
					<img class="bubble" src="/GDUTHackerSpace/Web/images/temp/images/bubble_03.png" alt="泡泡"/>
					</a>
				</div>
				<div class="team_content">
					<h3><%=t.getName() %></h3>
					<p>简介：<%=t.getBrief() %></p>
				</div>
			</div>
<%
		}
%>
	</div>
	</div>
	<div id="teacher_column" class="supwrapper">
		
	<div  class="article_area_teacher wrapper">
		<h1>师资力量  <span>Teachers</span></h1>
		<span id="teacher_column_last">上一个</span>
		<span id="teacher_column_next">下一个</span>
		<ul>
			<li style="display: none;">
				<div class="artcle_area_tea_pic">
					<div class="tea_pic_con">
						<p>
						<span>汉诺·凯霍宁</span>
						我校“百人计划”特聘教授，欧洲顶级工业设计大师、广东省工业设计集成创新科研团队带头人</p>
					</div>
					<img src="/GDUTHackerSpace/Web/images/teacher/Hannu_Kahonen.png" alt="汉诺·凯霍宁老师"/>
				</div>
				<div class="artcle_area_tea_pic">
					<div class="tea_pic_con">
						<p>
							<span>章国豪</span>
							信息工程学院院长，曾任美国著名芯片公司首席设计师</p>
						</div>
					<img src="/GDUTHackerSpace/Web/images/teacher/Guohao_Zhang.png" alt="章国豪老师"/>
				</div>
				<div class="artcle_area_tea_pic">
					<div class="tea_pic_con">
						<p>
							<span>陈晓辉</span>
							运豪集团副总裁
						</p>
					</div>
					<img src="/GDUTHackerSpace/Web/images/teacher/Xiaohui_Chen.png" alt="陈晓辉老师"/>
				</div>
			</li>
			<li>
				<div class="artcle_area_tea_pic">
					<div class="tea_pic_con">
						<p><span>吴杰庄</span>香港新一代文化协会总干事、高锋集团董事局主席</p>
					</div>
					<img src="/GDUTHackerSpace/Web/images/teacher/Jiezhuan_Wu.png" alt=""/>
				</div>
				<div class="artcle_area_tea_pic">
					<div class="tea_pic_con">
						<p><span>周裕丰</span>1999届校友，广东国地规划科技有限公司董事长、总经理</p>
					</div>
					<img src="/GDUTHackerSpace/Web/images/teacher/Yufeng_Zhou.png" alt="周裕丰老师"/>
				</div>
				<div class="artcle_area_tea_pic">
					<div class="tea_pic_con">
					<p><span>蔡延青</span>2009届校友，福布斯2015年中国30位30岁以下创业者</p>
				</div>
				<img src="/GDUTHackerSpace/Web/images/teacher/Yanqing_Cai.png" alt="蔡延青老师"/>
				</div>
			</li>
		</ul>
	</div>
	</div>
	<div id="welcome_join">
		<img src="/GDUTHackerSpace/Web/images/temp/temp_02.png" alt=""/>
		<p>JOIN IN US! LET`S BE TOGETHER!   JOIN IN US! COME ON!</p>
		<a href="/GDUTHackerSpace/Web/jsp/View/Public/Services/LiveIn.jsp">申请入驻</a>
	</div>
		
	<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/jsp/View/Public/HomePage/HomePage.js"></script>
		<!--[if lte IE 8]>
		<script type="text/javascript">
			$(function(){
				//IE7下把事件绑定的清除，把所有东西都显示出来
				$('img.bubble').hide();
				$('#team1,#team2,#team3').unbind("mouseenter");
				$('#team1,#team2,#team3').unbind("mouseleave");
				$("#team1,#team2,#team3").find("div.team_content").show();
			});
		</script>	
		<![endif]-->
	</body>
</html>