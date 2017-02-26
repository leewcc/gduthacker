<%@page import="java.util.ArrayList"%>
<%@page import="com.hackerspace.model.Link"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="footer">
	<%--logo的那个 --%>
	
	<div id="footer_logo">
		<div>
			<a href="#"></a>	
			<p>Copyright © 2016 广东工业大学创客学院</p>
		</div>
	</div>
	<div id="footer_link">
		<div>
			<h2>联系方式</h2>
			<span>邮箱：QGStudio@126.com</span>
			<span>电话：020-88899666</span>
			<span>地址：广州大学城外环西路100号</span>
		</div>
		<div>
			<h2>友情连接</h2>
			<%
			List<Link> la=(ArrayList<Link>) request.getServletContext().getAttribute("la");
			List<Link> lb=(ArrayList<Link>) request.getServletContext().getAttribute("lb");
			List<Link> lc=(ArrayList<Link>) request.getServletContext().getAttribute("lc");
			if(lc!=null) {
			for(Link ll:lc) {
				%>
				<a href="<%=ll.getLink() %>">
					<span><%=ll.getName() %></span>
				</a>
				<%
				}
			}
			%>
		</div>
		<div>
		<h2>国外创客</h2>
			<%
			if(la!=null) {
			for(Link ll:la) {
				%>
				<a href="<%=ll.getLink() %>">
					<span><%=ll.getName() %></span>
				</a>
				<%
				}
			}
			%>	
		</div>
		<div>
			<h2>国内创客</h2>
		<%
			if(lb!=null) {
			for(Link ll:lb) {
				%>
				<a href="<%=ll.getLink() %>">
					<span><%=ll.getName() %></span>
				</a>
				<%
				}
			}
		%>	

		</div>
	</div>
</div>