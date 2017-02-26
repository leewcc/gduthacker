<%@page import="java.util.List"%>
<%@page import="com.hackerspace.model.Question"%>
<%@page import="com.hackerspace.model.PageElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>常见问题</title>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/view/AboutCollege.css">
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
	</head>
	<body>
	<jsp:include page="../HomePage/Nav.jsp"></jsp:include>
		<div id="column_pic_re">
			<img id="user_background" src="/GDUTHackerSpace/Web/images/coin/images/user_background.png" alt="用户头像背景" />
		</div>
<%--第一步：	获取留言数据 --%>
		<div id="question_message" class="wrapper">
			<p id="question_tips">尊敬的用户您好，以下是一些常见的问题集锦，如果下述问题不能解答你的疑惑，欢迎在  用户中心->我的留言  中给管理员留言。我们会尽快给你解答。</p>
<%
	PageElem<Question> pe =(PageElem<Question>)request.getAttribute("questions");
	List<Question> ques = pe.getPageElem();
	if(ques.isEmpty()){
%>
		<p id="no_content">当前没有常见的问题</p>
<%
	}
%>
							
<%
		for(Question q : ques){
%>
					<div class="one_question">
						<div class="one_question_user">
							<img src="/GDUTHackerSpace/Web/images/temp/peopleBig.jpg" alt="用户"/>
							<p>匿名用户</p>
						</div>
						<div class="one_question_content">
						<h1><%=q.getContent() %></h1>
						<div class="one_question_answer">
							<div class="one_question_answer_user">
								<img src="/GDUTHackerSpace/Web/images/temp/peopleBig.jpg" alt="用户"/>
								<p>管理员</p>
							</div>
							<div class="one_question_answer_con">
								<p>答复：<%=q.getAnswer() %></p>
								<p><span class="time">编辑时间：<%=q.getDate().toString().substring(0, 16) %></span>
								</p>
							</div>	
						</div>		
						</div>					
					</div>
<%
		}
%>
		</div>
		<div id="page">
		<%
		int cp=pe.getCurrentPage();
		int tp=pe.getTotalPage();
		String selected;
		int i;
		if(cp>1) {//有页码
				%>
		<a href="SelectQuestionView?page=<%=cp>tp? 1:cp-1 %>" id="previousPage">.</a><!--上一页-->
				<%
			}
				i=1;
				if(tp<=10) {//分页小于十页
					for(i=1;i<=tp;i++) {
						selected = (i==cp)? "selected":"";
					%>
			<a href="SelectQuestionView?page=<%=i %>" class="<%=selected %>"><%=i %></a>		
					<%	
					}
				} else {//分页大于十页的情况
			String selected1 =(1==cp)? "selected":"";
			String selected2 =(2==cp)? "selected":"";
		
				%>
			<a href="SelectQuestionView?page=1" class="<%=selected1 %>">1</a>		
			<a href="SelectQuestionView?page=2" class="<%=selected2 %>">2</a>		
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
			<a href="SelectQuestionView?page=<%=i %>" class="<%=selected %>"><%=i %></a>				
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
		<a href="SelectQuestionView?page=<%=tp-1 %>" class="<%=selectedt1 %>"><%=tp-1 %></a>	
		<a href="SelectQuestionView?page=<%=tp %>" class="<%=selectedt2 %>"><%=tp %></a>	
			<%
			}
		if(cp<tp) {
			%>
	<a href="SelectQuestionView?page=<%=cp>=tp? tp:cp+1%>" id="nextPage">.</a>		<!--下一页-->
			<%
		}
		%>
	</div>
		<jsp:include page="../HomePage/Footer.jsp"></jsp:include>
</body>
</html>