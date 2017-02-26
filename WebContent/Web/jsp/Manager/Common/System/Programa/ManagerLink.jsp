<%@page import="com.hackerspace.model.Link"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理链接</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_system"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="2"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 系统管理 > 设置底部页尾</h1>
				
				<div id="col_area">	
					<form action="updateLink" method="post">
<%
	List<Link> la=(List<Link>)request.getAttribute("la");
	List<Link> lb=(List<Link>)request.getAttribute("lb");
	List<Link> lc=(List<Link>)request.getAttribute("lc");
%>
						<div class="add_link">
						<div id="col_area_header">
							<a href="#" class="current">国外创客</a>
						</div>
							<button type="button" onclick="addLink()" value="1">添加链接</button>
							<ul>
								<li>格式：（&nbsp;链接名称&nbsp;:&nbsp;链接URL地址&nbsp;）</li>
							
		<%
		for(Link ll:la) {
			%>
								<li>
									<input type="hidden" name="id" value="<%=ll.getId() %>"/>
									<input type="hidden" name="belong" value="<%=ll.getBelong() %>"/>
									<input type="text" name="name" value="<%=ll.getName() %>"/>
									:
									<input type="text" name="link" value="<%=ll.getLink() %>">
									<a href="deleteLink?id=<%=ll.getId() %>">删除</a>
								</li>
			
			<%
		}
		%>
							</ul>
						</div>
						<div class="add_link">
							<div id="col_area_header">
								<a href="#"  class="current">国内创客</a>
							</div>
							<button type="button" onclick="addLink()" value="2">添加链接</button>
								<ul>
									<li>格式：（&nbsp;链接名称&nbsp;:&nbsp;链接URL地址&nbsp;）</li>
							
		<%
		for(Link ll:lb) {
			%>
									<li>
										<input type="hidden" name="id" value="<%=ll.getId() %>"/>
										<input type="hidden" name="belong" value="<%=ll.getBelong() %>"/>
										<input type="text" name="name" value="<%=ll.getName() %>"/>
										:
										<input type="text" name="link" value="<%=ll.getLink() %>">
										<a href="deleteLink?id=<%=ll.getId() %>">删除</a>
									</li>
			
			<%
		}
		%>
								</ul>
						</div>
						<div class="add_link">
							<div id="col_area_header">	
								<a href="#" class="current">友情链接</a>
							</div>
							<button type="button" onclick="addLink()" value="3">添加链接</button>
							<ul>
								<li>格式：（&nbsp;链接名称&nbsp;:&nbsp;链接URL地址&nbsp;）</li>
							
		<%
		for(Link ll:lc) {
			%>
								<li>
									<input type="hidden" name="id" value="<%=ll.getId() %>"/>
									<input type="hidden" name="belong" value="<%=ll.getBelong() %>"/>
									<input type="text" name="name" value="<%=ll.getName() %>"/>
									:
									<input type="text" name="link" value="<%=ll.getLink() %>">
									<a href="deleteLink?id=<%=ll.getId() %>">删除</a>
								</li>
			
<%
		}
%>
							</ul>
						</div>
					<button type="submit" id="update_link">提交</button>
				</form>
			</div>
			<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</div>

	<script type="text/javascript">
		function addLink() {
			var x=event.target;
			var y='<li>' 
				+'<input type="hidden" name="id" value="0"/>'
				+'<input type="hidden" name="belong" value="'+x.value+'"/>'
				+'<input type="text" name="name" value=""/>：'
				+'	<input type="text" name="link" value="http://"></li>';
			x.nextElementSibling.innerHTML+=y;
		}
	</script> 
</body>
</html>