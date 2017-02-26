<%@page import="com.hackerspace.model.PageElem"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.hackerspace.model.Cooperation"%>
<%@page import="com.hackerspace.action.manager.CooperationAction"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理合作</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
<% 
	String tag = (String)request.getAttribute("tag");
	if(tag==null) {tag="3";} 
%>	
		<div id="wrapper">
<% 
	if("0".equals(tag)){ 

%>
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_coop"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
<%
	}else{
%>
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_coop"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>

<%
		
	}
%>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 校企合作管理 > <%="0".equals(tag)?"合作项目":"" %><%="1".equals(tag)?"合作企业":"" %><%="3".equals(tag)?"草稿箱":"" %></h1>
				<div id="col_area">
				
					<div id="col_area_header">
						<a href="showManagerCooperation?tag=0&cp=1" <%="0".equals(tag)?"class='current'":"" %>>合作项目</a>
						<a href="showManagerCooperation?tag=1&cp=1" <%="1".equals(tag)?"class='current'":"" %>>合作企业</a>
						<a href="showCooperationDrafts?cp=1"<%="3".equals(tag)?"class='current'":"" %>>草稿箱</a>
						<a href="prepareEditorCooperation">新增合作</a>
					</div>
		<%-- 展示文章 --%>
					
<%
	PageElem<Cooperation> pe = (PageElem<Cooperation>) request.getAttribute("pageElem");
	String status =(String) request.getAttribute("status");
	List<Cooperation> cl = (ArrayList<Cooperation>)pe.getPageElem();
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
						<th></th>
					</tr>
				</thead>
			<tbody>
<%

	for(Cooperation c:cl) {
		Timestamp timestamp=c.getDate();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time= sf.format(timestamp);
%>
				<tr>
					<td class="tit"><a href="showOneCooperation?id=<%=c.getId() %>&status=<%=status %>"><%=c.getName() %></a></td>
					<td><%=c.getAuthor().getName() %></td>
					<td><%=time %></td>
					<td>
						<a class="button" href="deleteOneCooperation?id=<%=c.getId() %>&tag=<%=tag%>&status=<%=status %>">删除</a>
					</td>
				</tr>	
				
<%
		}
%>	
			</tbody>
		</table>
	</div>
	<%-- --%>
	<div id="page">
<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		//已发布的文章分页
		if(cl.get(0).getStatus()==1 ){
			if(cp>1) {
				%>
		<a href="showManagerCooperation?tag=<%=tag %>&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
				<%
			}
			int i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
				%>
		<a href="showManagerCooperation?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
				<%	
				}
			} else {//分页大于十页的情况
		String selected1 =(1==cp)? "selected":"";
		String selected2 =(2==cp)? "selected":"";
	
			%>
		<a href="showManagerCooperation?tag=<%=tag %>&cp=1" class="<%=selected1 %>">1</a>		
		<a href="showManagerCooperation?tag=<%=tag %>&cp=2" class="<%=selected2 %>">2</a>		
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
		<a href="showManagerCooperation?tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
	<a href="showManagerCooperation?tag=<%=tag %>&cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
	<a href="showManagerCooperation?tag=<%=tag %>&cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
		}
		if(cp<tp) {
			%>
	<a href="showManagerCooperation?tag=<%=tag %>&cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
			<%
		}
		} else {//草稿分页
			if(cp>1) {
			%>
	<a href="showCooperationDrafts?cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
			<%
				}
				int i=1;
				if(tp<=10) {
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="showCooperationDrafts?cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="showCooperationDrafts?cp=1" class="<%=selected1 %>">1</a>		
			<a href="showCooperationDrafts?cp=2" class="<%=selected2 %>">2</a>		
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
			<a href="showCooperationDrafts?cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
		<a href="showCooperationDrafts?cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="showCooperationDrafts?cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
			if(cp<tp) {
				%>
		<a href="showCooperationDrafts?cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
				<%
			}
		}
	}
		%>
				</div>
			</div>
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
</body>
</html>