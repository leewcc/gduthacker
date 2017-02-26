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
		<title>创新项目</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/jquery-1.9.1.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</head>
	<body>
		<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic">
				<div id="header_column">
					<div class="wrapper">
						<h2>主页 > 创客项目</h2>
						<h1><%=tag.equals("1")?"创新项目":"创业项目" %></h1>
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
					<li <%=tag.equals("1")?"class='current'":"" %>><a href="/GDUTHackerSpace/user/project/query?tag=0">创新项目</a></li>
					<li <%=tag.equals("0")?"class='current'":"" %>><a href="/GDUTHackerSpace/user/project/query?tag=1">创业项目</a></li>
					<li><a href="/GDUTHackerSpace/user/projectshow/querys">项目路演</a></li>
				</ul>
				<div id="sec_tempv1"></div>
			</div>
			
			<div id="sec_article">
<%
	PageElem<Project> pageElem = (PageElem<Project>) request.getAttribute("pageElem");
	System.out.println(pageElem);
	if ( pageElem != null && pageElem.getPageElem().size() >= 1 ) {System.out.println(pageElem + "1");
%>				
				<table>
					<thead>
						<tr>
							<th><span>名称</span></th>
							<th><span>负责团队</span></th>
							<th><span>发布时间</span></th>
						</tr>
					</thead>
	
					
					<tbody>
						<s:iterator value="#request.pageElem.pageElem">
							<tr>
								<td><s:property value="title"/></td>
								<td><s:property value="teamName"/></td>
								<td><s:date name="time" format="yyyy-mm-dd"/></td>
								<s:hidden value="id"></s:hidden>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<%}else {%><p  id="no_content">当前无项目</p><%} %>
			
		</div>
		</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
	</body>
</html>