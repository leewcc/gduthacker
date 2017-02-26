<%@page import="com.hackerspace.util.TimeParseUtil"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.hackerspace.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>项目管理页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_projects"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="1"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 创客项目管理 > 项目管理</h1>
				<div id="col_area">
<%
	String status = (String) request.getAttribute("status");
	String tag = (String)request.getAttribute("tag");
	if (status == null) {
		status = "1";
	}
	if (tag == null) 
		tag = "1";
	
	String result = (String) request.getAttribute("result");
	if (ActionResult.RESULT_SUCCESS.equals(result)) {
	      out.print("<script>alert('"+(String)request.getAttribute("msg")+"');</script>");
	      request.removeAttribute("result");
	      request.removeAttribute("msg");
	}
%>
					<div id="col_area_header">
						<a href="/GDUTHackerSpace/manager/project/query?cp=1&status=1&tag=<%=tag%>" <%=status.equals("1")?"class='current'":"" %>>已发布项目</a>
						<a href="/GDUTHackerSpace/manager/project/query?cp=1&status=0&tag=<%=tag%>" <%=status.equals("0")?"class='current'":"" %>>未发布项目</a>
						<a href="/GDUTHackerSpace/manager/project/befcre">添加项目</a>
					</div>
					<div id="col_area_sub_header">
						<a href="/GDUTHackerSpace/manager/project/query?cp=1&tag=0&status=<%=status%>"  <%=tag.equals("0")?"class='current'":"" %>>创新项目</a>
						<a href="/GDUTHackerSpace/manager/project/query?cp=1&tag=1&status=<%=status%>"  <%=tag.equals("1")?"class='current'":"" %>>创业项目</a>
					</div>
					<div id="col_area_table" class="no_padding">
					<%
						PageElem<Project> pageElem = (PageElem<Project>) request.getAttribute("pageElem");
					%>
					<s:if test="#request.pageElem.pageElem.size() > 0">
							<table>
								<thead>
									<tr>
										<th><span>名称</span></th>
										<th><span>负责团队</span></th>
										<th class="project_type"><span>项目类型</span></th>
										<th><span>发布时间</span></th>
										<th></th>
									</tr>
								</thead>					
								<tbody>
									
									<%
										for (Project p : pageElem.getPageElem()) {
									%>
									<tr>
										<td class="title"><%=p.getTitle() %></td>
										<td><%=p.getTeamName() %></td>
										<%if (p.getTag() == ((byte)0)) {%>
										<td class="project_type">创新项目</td>
										<%}else { %>
										<td class="project_type">创业项目</td>
										<%} %>
										
										<td><%=TimeParseUtil.TimestamptoStr(p.getTime()).substring(0, 16) %></td>
										<td>
											<%
												if(status.equals("1")) {// 已发布项目 
											%>
											
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/project/updateSta?id=<%=p.getId()%>&status=0'">停用</button>
											<%} else { %>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/project/updateSta?id=<%=p.getId()%>&status=1'">发布</button>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/project/delete?id=<%=p.getId()%>">删除</button>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/project/updateFir?id=<%=p.getId()%>'">修改</button>
											<%} %>
										</td>
									</tr>
								<%} %>
								</tbody>
								</table>
							</s:if>
							<s:else>
								<p id="no_content">当前无项目</p>
							</s:else>
					</div>
				</div>

		<div id="page">
		<%
			PageElem<Project>  pe = (PageElem<Project>) request.getAttribute("pageElem");
			int cp=pe.getCurrentPage();
			int tp=pe.getTotalPage();
			String selected;
			if(cp>1) {
		%>
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
		<%
			}
			int i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
		<%	
				}
			} else {//分页大于十页的情况
				String selected1 =(1==cp)? "selected":"";
				String selected2 =(2==cp)? "selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=1" class="<%=selected1 %>">1</a>		
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=2" class="<%=selected2 %>">2</a>		
		<%
			i=cp-2;
			if(i>3) {
		%>
			<span>...</span>	
		<%
			}else {i=3;}
			
			for(;i<cp+2&&i<tp-1;i++) {
			selected =(i==cp)?"selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
		<% } if(i<tp-1) { %>
		<span>...</span>
		<%  }
			String selectedt1=(cp==tp-1) ?"selected":"";
			String selectedt2=(tp==cp)?"selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
			<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
			}
			if(cp<tp) {
		%>
				<a href="/GDUTHackerSpace/manager/project/query?status=<%=status %>&tag=<%=tag %>&cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
		<%
			}
		%>
		</div>
			 <p id="footer">Copyright © 2016 广东工业大学创客学院</p>

			</div>
		</div>

	</body>

</html>