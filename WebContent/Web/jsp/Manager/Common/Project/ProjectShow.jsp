<%@page import="com.hackerspace.util.TimeParseUtil"%>
<%@page import="com.hackerspace.model.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>项目路演管理列表</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
<body>
	<div id="wrapper">
		<jsp:include page="../../Common/Common/ManagerSidebar.jsp">
			<jsp:param name="param_sidebar" value="sidebar_projects"></jsp:param>
			<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
		</jsp:include>
		<div id="column">
			<jsp:include page="../../Common/Common/ManagerHeader.jsp"></jsp:include>
			<h1 id="col_nav">首页 > 创客项目管理 > 项目路演管理</h1>
			<div id="col_area">
				<div id="col_area_header">
<%
		String status = (String) request.getAttribute("status");
		String result = (String) request.getAttribute("result");
		if (ActionResult.RESULT_SUCCESS.equals(result)) {
     		out.print("<script>alert('"+(String)request.getAttribute("msg")+"');</script>");
      		request.removeAttribute("result");
     		request.removeAttribute("msg");
		}
%>
					<a <%=status.equals("1")?"class='current'":"" %>href="/GDUTHackerSpace/manager/projectshow/querys?status=1">已发布</a>
					<a <%=status.equals("0")?"class='current'":"" %> href="/GDUTHackerSpace/manager/projectshow/querys?status=0">未发布</a>
					<a  href="/GDUTHackerSpace/Web/jsp/Manager/Common/Project/CreProShow.jsp">添加路演</a>
					
				</div>
				<div id="col_area_table">
						<table>
							<thead>
								<tr>
									<th><span>路演名称</span></th>
									<th><span>编辑者</span></th>
									<th><span>发布时间</span></th>
									<th></th>
								</tr>
							</thead>					
							<tbody>
								<%
									PageElem<ProjectShow> pageElem = (PageElem<ProjectShow>) request.getAttribute("pageElem");
									if (pageElem != null) {
									for (ProjectShow ps : pageElem.getPageElem()) {
								%>
									<tr>
										
										<td class="title"><%=ps.getTitle() %></td>
										<td><%=ps.getAuthor()%></td>
										<td><%=TimeParseUtil.TimestamptoStr(ps.getTime()).substring(0, 16) %></td>
										<%if ("1".equals(status)) {%>
										<td>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/projectshow/updateSta?id=<%=ps.getId()%>&status=0'">取消发布</button>
										</td>
										<%}else if ("0".equals(status)) {%>
										<td>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/projectshow/updateFir?id=<%=ps.getId()%>'">修改</button>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/projectshow/updateSta?id=<%=ps.getId()%>&status=1'">发布</button>
											<button type="button" onclick="window.location='/GDUTHackerSpace/manager/projectshow/delete?id=<%=ps.getId()%>'">删除</button>
										</td>
										<%} %>
									</tr>
								<%} }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>