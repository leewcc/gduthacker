<%@page import="com.hackerspace.model.ActionResult"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册请求管理页面</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>      
		<div id="wrapper">
			<jsp:include page="../../Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_things"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="3"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 办事服务管理 > 课室管理</h1>
				<div id="col_area">
					<div id="col_area_table">     
					
						<%
							if (ActionResult.RESULT_SUCCESS.equals(request.getAttribute("result"))) {
								  out.print("<script>alert('"+(String)request.getAttribute("msg")+"');</script>");
							      request.removeAttribute("result");
							      request.removeAttribute("msg");
							}
						%>                                                                  
						<s:form action="create" method="post" enctype="multipart/form-data" namespace="/manager/classroom">  
							<fieldset> 
								<legend>创建课室</legend>
								<s:if test="#request.eNum != null">
									<p><s:property value="#request.eNum"/></p>
								</s:if>
								<label for="classroom.num">课室号：</label><s:textfield  name="classroom.num" />
						     	<s:submit value="创建"/>
					     	</fieldset>
					    </s:form>
					    <s:if test="classrooms.size() > 0">
					        <table>
					        	<thead>
					        		<tr>
					    				<th><span>课室号</span></th>
					    				<th></th>
					    			</tr>
					    		</thead>
					    		<tbody>
								    <s:iterator value="classrooms">
								    <tr>
								    	<td><s:property value="num"/></td>
								    	<td><button type="button" class="fn" onclick="window.location='/GDUTHackerSpace/manager/classroom/delete?id=<s:property value="id"/>'">删除</button></td>
								    </tr>
								    </s:iterator>
						    	</tbody>
						    </table>
					    </s:if>
					    <s:else>
					    	<p id="no_content">当前未创建任何课室</p>
					    </s:else>
				    </div>
			    </div>
			    <p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		    </div>
	    </div>
	</body>
</html>