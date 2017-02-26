<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.hackerspace.model.*, java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询申请信息</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_things"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="4"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 办事服务管理 > 课室管理</h1>
				<div id="col_area">
					<div id="col_area_table">    
		<s:if test="#request.pageElem != null && #request.pageElem.pageElem.size() > 0">
						<table>
				        	<thead>
				        		<tr>
				    				<th><span>申请人</span></th>
									<th><span>申请单位</span></th>
									<th><span>申请课室</span></th>
									<th><span>申请占用时间</span></th>
									<th><span>提交时间</span></th>
									<th><span>当前状态</span></th>	
									<th></th>
								</tr>
							</thead>
			    		<tbody>         
							<s:iterator value="#request.pageElem.pageElem">
								 <tr>
								<td><s:property value="user"></s:property></td>
								<td><s:property value="team"></s:property></td>
								<td><s:property value="classroom.num"></s:property></td>
								<td>
									<s:property value="date"></s:property>
								</td>
								<%--这个时间未处理好，但是因为使用的是这个property，所以我不知道怎么改 --%>
								<td>
									<s:date name="postTime" format="yyyy-MM-dd HH:MM"/>
								</td>
								<s:if test="isPass == -1">
									<td>未处理</td>
								</s:if>
								<s:elseif test="isPass = 0">
									<td>未通过</td>
								</s:elseif>
								<s:else>
									<td>已通过</td>
								</s:else>
								<td><button type="button" onclick="window.location='/GDUTHackerSpace/manager/classapply/query?id=<s:property value="id"/>'">查看</button></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
				<s:else>
					<p id="no_content">当前无申请信息</p>
				</s:else>
		
					</div>
				</div>
				
					<div id="page">
		<%
			PageElem<ClassroomApply>  pe = (PageElem<ClassroomApply>) request.getAttribute("pageElem");
			int cp=pe.getCurrentPage();
			int tp=pe.getTotalPage();
			String selected;
			if(cp>1) {
		%>
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a>
		<%
			}
			int i=1;
			if(tp<=10) {//分页小于十页
				for(i=1;i<=tp;i++) {
					selected = (i==cp)? "selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=<%=i %>" class="<%=selected %>"><%=i %></a>		
		<%	
				}
			} else {//分页大于十页的情况
				String selected1 =(1==cp)? "selected":"";
				String selected2 =(2==cp)? "selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=1" class="<%=selected1 %>">1</a>		
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=2" class="<%=selected2 %>">2</a>		
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
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=<%=i %>" class="<%=selected %>"><%=i %></a>				
		<% } if(i<tp-1) { %>
		<span>...</span>
		<%  }
			String selectedt1=(cp==tp-1) ?"selected":"";
			String selectedt2=(tp==cp)?"selected":"";
		%>
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
			<a href="/GDUTHackerSpace/manager/classapply/querys?cp=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
		<%
			}
			if(cp<tp) {
		%>
				<a href="/GDUTHackerSpace/manager/classapply/querys?cp=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		
		<%
			}
		%>
		</div>
			 <p id="footer">Copyright © 2016 广东工业大学创客学院</p>

			</div>
		</div>
				
		                       
	</body>
</html>