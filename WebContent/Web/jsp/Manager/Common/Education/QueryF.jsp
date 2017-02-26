<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册请求管理页面</title>
	</head>

<body>
<%
	String status = (String) request.getAttribute("status");
	System.out.println(status);
	if (status == null) 
		status = "1";
%>
	<div>
		<a href="/GDUTHackerSpace/manager/education/query?tag=0&status=1">已发布</a>
		<a href="/GDUTHackerSpace/manager/education/query?tag=0&status=0">未发布</a>
	</div>
	
	<div>
		<input type="button" value="增加讲座通知">
	</div>
	
	<div>
		<div>
			<span>名称</span><span>编辑者</span><span>日期</span>
		</div>
		
		<s:if test="#request.pageElem.pageElem.size() >= 1">
		<s:iterator value="#request.pageElem.pageElem">
			<span><s:property value="title" /></span>
			<span><s:property value="authorName" /></span>
			<span><s:property value="time" /></span>
			
			<%if(status.equals("1")) {%>
			<input type="button" value="修改" onclick="window.location='/GDUTHackerSpace/manager/education/updateF?id=<s:property value="id"/>&tag=0'"/>
			<input type="button" value="停用" onclick="window.location='/GDUTHackerSpace/manager/education/updateSta?id=<s:property value="id"/>&status=0'"/>
			<%}else{ %>
			<input type="button" value="发布" onclick="window.location='/GDUTHackerSpace/manager/education/updateSta?id=<s:property value="id"/>&status=1'"/>
			<input type="button" value="删除" onclick="window.location='/GDUTHackerSpace/manager/education/delete?id=<s:property value="id"/>'"/>
			<%} %>
		</s:iterator>
		</s:if>
		<s:else>
			<span>当前未创建讲座通知</span>
		</s:else>
	</div>
</body>


</html>