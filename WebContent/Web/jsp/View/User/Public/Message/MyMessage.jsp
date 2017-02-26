<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Message"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的留言页面</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/UserPage.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		
	</head>
	<body>
		<jsp:include page="../../../Public/HomePage/Nav.jsp"></jsp:include>
			<jsp:include page="../../Common/CommonSidebar.jsp">
				<jsp:param name="sidebar" value="2"></jsp:param>
			</jsp:include>
			<div id="user_area">
				<div id="user_send_message">
					<h1>请在此给管理员留言</h1>
					<form action="SendMessage" method="post">
						<p class="illegal">${message}</p>
						<textarea  name="content"  value="${m.content}"></textarea>
						<button type="submit">发送</button>
		 			</form>
	 			</div>
	 			<button type="button" onclick="window.location.href='MessageBox'" class="button_normal">我的消息箱</button>
	 			<div id="user_have_message">
	 			<h1>留言记录</h1>

 <%
	 			PageElem<Message> pe = (PageElem<Message>)request.getAttribute("messages");
	 			List<Message> mess = pe.getPageElem();
	 			if(mess.isEmpty()){
 %>
 
				<p id="no_content">当前没有留言</p>
 <%
	 			}else{
 %>
 					<table id="user_mess_list">
							<thead>
								<tr>
									<th><span>留言信息</span></th>
									<th><span>留言时间</span></th>
									<th><span>状态</span></th>
								</tr>
							</thead>
							<tbody>
							
<%
	 			for(Message m : mess){
%>
								<tr>								
									 <td><a href="SeeMessage?id=<%=m.getId() %>"><%=m.getContent() %></a></td>
									<!-- <td><%=m.getContent() %></td> -->
									<td><%=m.getDate() %></td>
									<td><%=m.getReply().size() > 0 ? "已回复" : "未回复" %></td>
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
		int i;
		if(cp>1) {//有页码
				%>
			<a href="SelectMyMessage?page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
				<a href="SelectMyMessage?page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
				<a href="SelectMyMessage?page=1" class="<%=selected1 %>">1</a>		
				<a href="SelectMyMessage?page=2" class="<%=selected2 %>">2</a>		
				<%
				i=cp-2;
				if(i>3) {//出现省略号
					%>
				<span>...</span>	
					<%
				}else {
				i=3;		
					}
				for(;i<cp+2&&i<tp-1;i++) {
				selected =(i==cp)?"selected":"";
				%>
				<a href="SelectMyMessage?page=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
				<a href="SelectMyMessage?page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
				<a href="SelectMyMessage?page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
				<a href="SelectMyMessage?page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
			</div>
		</div>
		<jsp:include page="../../../Public/HomePage/Footer.jsp"></jsp:include>
		<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
	</body>
</html>