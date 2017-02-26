<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.hackerspace.model.News"%>
<%@page import="com.hackerspace.action.manager.NewsAction"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<% 
		String tag = (String)request.getAttribute("tag");
		if(tag==null) {tag="3";} 
%>				
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理新闻</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
		<div id="wrapper">
<% 
	if(tag.equals("0")){
%>
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_news"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
<%}else{ %>
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_news"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
<%} %>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 新闻管理 </h1>
				<div id="col_area">
	
					<div id="col_area_header">
						<a href="showManagerNews?tag=0&cp=1" <%="0".equals(tag)?"class='current'":"" %>>学校新闻</a>
						<a href="showManagerNews?tag=1&cp=1" <%="1".equals(tag)?"class='current'":"" %>>活动新闻</a>
						<a href="showDrafts?cp=1"<%="3".equals(tag)?"class='current'":"" %>>草稿箱</a>
						
						<a href="prepareEditorNews">新增文章</a>
					</div>
		<%-- 展示文章 --%>
					
<%
	PageElem<News> pe = (PageElem<News>) request.getAttribute("pageElem");
	List<News> nl = (ArrayList<News>)pe.getPageElem();

	if(nl.size() <= 0){ 
		
%>
		<p id="error_message">当前没有文章。</p>
<%
		return ;
	}else{
%>
					<div id="col_area_table" class="no_padding">
						<table>
							<thead>
								<tr>
									<th><span class="title">标题</span></th>
									<th><span>状态</span></th>
									<th><span>编辑者</span></th>
									<th><span>时间</span></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
<%

	for(News n:nl) {
		Timestamp timestamp=n.getTime();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time= sf.format(timestamp);
		if(n.getStatus()==1) {
			if(n.getIsTop()==1) {
%>
				<tr>
					<td class="title"><a href="showOneNews?id=<%=n.getId() %>&status=1"><%=n.getTitle() %></a></td>
					<td>置顶</td>
					<td><%=n.getAuthorName() %></td>
					<td><%=time %></td>
					<td>
						<a class="button" href="takeToTop?id=<%=n.getId()%>&top=0&tag=<%=tag %>&status=1">取消置顶</a>
						<a class="button" href="deleteOneNews?id=<%=n.getId() %>&tag=<%=tag%>&status=1">删除</a>
					</td>
				</tr>		
<% 
		}
		if(n.getIsTop()==0){
%>
				<tr>
					<td class="title" ><a href="showOneNews?id=<%=n.getId() %>&status=1"><%=n.getTitle() %></a></td>
					<td>未置顶</td>
					<td><%=n.getAuthorName() %></td>
					<td><%=time %></td>
					<td>
						<a class="button" href="takeToTop?id=<%=n.getId()%>&top=1&tag=<%=tag %>&status=1">选择置顶</a>
						<a class="button" href="deleteOneNews?id=<%=n.getId() %>&tag=<%=tag%>&status=1">删除</a>
					</td>
				</tr>		
		<%
				} 
			} else {
			%>
			<%--这个又是哪个分类 --%>
				<tr>
					<td class="title"><a href="showOneNews?id=<%=n.getId() %>&status=0"><%=n.getTitle() %></a></td>
					<td><%=n.getAuthorName() %></td>
					<td><%=time %></td>
					<td>
						<a class="button" href="deleteOneNews?id=<%=n.getId() %>&page=<%=pe.getCurrentPage() %>&status=0">删除</a>
					</td>
				</tr>	
			<%
				}
			}
%>
			</tbody>
		</table>
	</div>
	<div id="page">
<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		//已发布的文章分页
		if(nl.get(0).getStatus()==1 ){
			if(cp>1) {
				%>
		<a href="showManagerNews?tag=<%=tag %>&status=1&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
				<%
			}
			int i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
				%>
		<a href="showManagerNews?tag=<%=tag %>&status=1&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
				<%	
				}
			} else {//分页大于十页的情况
		String selected1 =(1==cp)? "selected":"";
		String selected2 =(2==cp)? "selected":"";
	
			%>
		<a href="showManagerNews?tag=<%=tag %>&status=1&cp=1" class="<%=selected1 %>">1</a>		
		<a href="showManagerNews?tag=<%=tag %>&status=1&cp=2" class="<%=selected2 %>">2</a>		
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
		<a href="showManagerNews?tag=<%=tag %>&status=1&cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
	<a href="showManagerNews?tag=<%=tag %>&status=1&cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
	<a href="showManagerNews?tag=<%=tag %>&status=1&cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
		}
		if(cp<tp) {
			%>
	<a href="showManagerNews?tag=<%=tag %>&status=1&cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
			<%
		}
		} else {//草稿分页
			if(cp>1) {
			%>
	<a href="showDrafts?status=0&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
			<%
				}
				int i=1;
				if(tp<=10) {
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="showDrafts?status=0&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
						String selected1 =(1==cp)? "selected":"";
						String selected2 =(2==cp)? "selected":"";
					
							%>
						<a href="showDrafts?status=0&cp=1" class="<%=selected1 %>">1</a>		
						<a href="showDrafts?status=0&cp=2" class="<%=selected2 %>">2</a>		
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
						<a href="showDrafts?status=0&cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
					<a href="showDrafts?status=0&cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
					<a href="showDrafts?status=0&cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
						<%
						}
						if(cp<tp) {
							%>
					<a href="showDrafts?status=0&cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
							<%
						}
					}
				}
					%>
				</div>
			</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</div>
	<!-- 提示信息 -->
	<%
		    String message = (String)request.getAttribute("message");
		    if(message!=null){
		      out.print("<script>alert('"+message+"');</script>");
		      request.removeAttribute("message");
		    }
	%>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>