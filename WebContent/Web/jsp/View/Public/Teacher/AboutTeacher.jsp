<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.model.Program"%>
<%@page import="com.hackerspace.service.PictureService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>师资力量</title>
	<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
	<jsp:include page="../../../Result/iehack.jsp"></jsp:include>
	<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
</head>
<body>
<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
<div id="column_pic">
	<%
PictureService ps=new PictureService();
String url=	ps.getOnePicture("12");
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
Program p=l.get(1);
ArrayList<Program> ll=(ArrayList<Program>) p.getPrograms();	
String curl=request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1);//最后路径的名字
curl=curl.replace(".jsp", "");
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
				if(pp.getUrl().contains(curl)) {
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
		<div class="sec_article_area" >
			<div class="article_area_title">
				<h2>师资队伍</h2>
			</div>
			<div class="article_area_teacher">
				<ul id="article_area_tea_temp">
					<li>组织校内课程教学与实践指导教师，进行理论知识传授和指导学术科技创新实践；</li>
					<li>引入国际先进创新创业课程和培训公司，对学生进行相关的教育； </li>
					<li>聘请校外平台、企业讲师，指导学生创新实践 ；</li>
					<li>聘请校外平台、企业创业导师，指导学生创业实践；</li>
				</ul>
			</div>
		</div>
		<div class="sec_article_area" >
			<div class="article_area_title">
				<h2>导师资源</h2>
			</div>
			<div id="article_tea" class="article_area_teacher">
				<ul>
					<li class="artcle_area_tea_pic">
						<div>
							<p>
							<span>汉诺·凯霍宁</span>
							我校“百人计划”特聘教授，欧洲顶级工业设计大师、广东省工业设计集成创新科研团队带头人</p>
						</div>
						<img src="/GDUTHackerSpace/Web/images/teacher/Hannu_Kahonen.png" alt="汉诺·凯霍宁老师"/>
						
					</li>
					<li class="artcle_area_tea_pic">
						<div>
							<p>
							<span>章国豪</span>
							信息工程学院院长，曾任美国著名芯片公司首席设计师</p>
						</div>
						<img src="/GDUTHackerSpace/Web/images/teacher/Guohao_Zhang.png" alt="章国豪老师"/>
						
					</li>
					<li class="artcle_area_tea_pic pic_temp">
						<div>
							<p>
							<span>陈晓辉</span>
							运豪集团副总裁
							</p>
						</div>
						<img src="/GDUTHackerSpace/Web/images/teacher/Xiaohui_Chen.png" alt="陈晓辉老师"/>
						
					</li>
					<li class="artcle_area_tea_pic">
						<div>
							<p><span>吴杰庄</span>香港新一代文化协会总干事、高锋集团董事局主席</p>
						</div>
						<img src="/GDUTHackerSpace/Web/images/teacher/Jiezhuan_Wu.png" alt=""/>
						
					</li>
					<li class="artcle_area_tea_pic">
						<div>
							<p><span>周裕丰</span>1999届校友，广东国地规划科技有限公司董事长、总经理</p>
						</div>
						<img src="/GDUTHackerSpace/Web/images/teacher/Yufeng_Zhou.png" alt="周裕丰老师"/>
						
					</li>
					<li class="artcle_area_tea_pic pic_temp">
						<div>
							<p><span>蔡延青</span>2009届校友，福布斯2015年中国30位30岁以下创业者</p>
						</div>
						<img src="/GDUTHackerSpace/Web/images/teacher/Yanqing_Cai.png" alt="蔡延青老师"/>
					</li>
				</ul>
			</div>
		</div>
	</div>	
</div>
<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(function(){
	$(".artcle_area_tea_pic").mouseenter(function(event){
		var $target = $(event.target);
		if($target.prop("tagName") == "LI"){
			$target = $($target.children("img")[0]); 
		}
		if($target.prop("tagName") == "IMG"){
			$target.prev().show(200);
		}
	});
	$(".artcle_area_tea_pic").mouseleave(function(event){
		var $target = $(event.target);
		if($target.prop("tagName") == "LI"){
			$($target.children("img")[0]).prev().hide(100);
		}else if($target.prop("tagName") == "IMG"){
			$target.prev().hide(100);
		}else if($target.prop("tagName") == "DIV"){
			$target.hide(100);
		}else if($target.prop("tagName") == "P"){
			$target.parent().hide(100);
		}else if($target.prop("tagName") == "SPAN"){
			$target.parent().parent().hide(100);
		}
	});
});
</script>
</body>
</html>