<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.hackerspace.model.Education"%>
<%@page import="com.hackerspace.action.manager.EducationAction"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>教育管理</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
	</head>
	<body>
	<% 
		String tag = (String)request.getAttribute("tag");
		if(tag==null) {tag="3";} 
%>			
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_education"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页  > 教育管理  > <%="0".equals(tag)?"讲座论坛":"" %><%="1".equals(tag)?"实践实训":"" %><%="3".equals(tag)?"草稿箱":"" %></h1>
				<div id="col_area">
					<div id="col_area_header">
						<a href="showManagerEducation?tag=0&cp=1" <%="0".equals(tag)?"class='current'":"" %>>讲座论坛</a>
						<a href="showManagerEducation?tag=1&cp=1" <%="1".equals(tag)?"class='current'":"" %>>实践实训</a>
						<a href="showEducationDrafts?cp=1"<%="3".equals(tag)?"class='current'":"" %>>草稿箱</a>
						<a href="prepareEditorEducation">新增文章</a>
					</div>
		<%-- 展示文章 --%>
					
<%
	PageElem<Education> pe = (PageElem<Education>) request.getAttribute("pageElem");
	String status =(String) request.getAttribute("status");
	List<Education> cl = (ArrayList<Education>)pe.getPageElem();
	if(cl.size() <= 0){ 
%>
		<p id="error_message">当前没有文章。</p>
<%
		return ;
	}else{
		if(status==null) {
			status=cl.get(0).getStatus()==1? "1":"0";
		}
%>
		<div id="col_area_table">
			<table>
				<thead>
					<tr>
						<th><span>标题</span></th>
						<th><span>编辑者</span></th>
						<th><span>时间</span></th>
						<th><span>操作</span></th>

					</tr>
				</thead>
			<tbody>
<%

	for(Education c:cl) {
		Timestamp timestamp=c.getTime();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time= sf.format(timestamp);
%>
				<tr>
					<td class="class_title"><a href="showOneEducation?id=<%=c.getId() %>&status=<%=status %>"><%=c.getTitle() %></a></td>
					<td class="author"><%=c.getAuthorName() %></td>
					<td><%=time.substring(0, 16) %></td>
					<td>
						<a class="button" href="deleteOneEducation?id=<%=c.getId() %>&tag=<%=tag%>&status=<%=status %>">删除</a>
					</td>
				</tr>	
				
<%
		}
%>	
			</tbody>
		</table>
	</div>
	<div id="page" >
<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		//已发布的文章分页
		if(cl.get(0).getStatus()==1 ){
			if(cp>1) {
				%>
		<a href="showManagerEducation?tag=<%=tag %>&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
				<%
			}
			int i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
				%>
		<a href="showManagerEducation?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
				<%	
				}
			} else {//分页大于十页的情况
		String selected1 =(1==cp)? "selected":"";
		String selected2 =(2==cp)? "selected":"";
	
			%>
		<a href="showManagerEducation?tag=<%=tag %>&cp=1" class="<%=selected1 %>">1</a>		
		<a href="showManagerEducation?tag=<%=tag %>&cp=2" class="<%=selected2 %>">2</a>		
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
		<a href="showManagerEducation?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
	<a href="showManagerEducation?tag=<%=tag %>&cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
	<a href="showManagerEducation?tag=<%=tag %>&cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
		}
		if(cp<tp) {
			%>
	<a href="showManagerEducation?tag=<%=tag %>&cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
			<%
		}
		} else {//草稿分页
			if(cp>1) {
			%>
	<a href="showEducationDrafts?cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
			<%
				}
				int i=1;
				if(tp<=10) {
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="showEducationDrafts?cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="showEducationDrafts?cp=1" class="<%=selected1 %>">1</a>		
			<a href="showEducationDrafts?cp=2" class="<%=selected2 %>">2</a>		
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
			<a href="showEducationDrafts?cp=2" class="<%=selected %>"><%=i %></a>				
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
		<a href="showEducationDrafts?cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="showEducationDrafts?cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
			if(cp<tp) {
				%>
		<a href="showEducationDrafts?cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
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
	<%-- 提示信息 --%>
	<%
		    String message = (String)request.getAttribute("message");
		    if(message!=null){
		      // out.print("<script>alert('"+message+"');</script>");
		      request.removeAttribute("message");
		    }
	%>
	<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
</body>
</html>