<%@page import="com.hackerspace.model.Program"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>栏目管理</title>
		<link rel="shortcut icon" type="image/x-icon" href="/GDUTHackerSpace/Web/images/coin/favicon.ico" media="screen"/>
		<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/manager/ManagerPage.css"/>	
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="../../../Common/Common/ManagerSidebar.jsp">
				<jsp:param name="param_sidebar" value="sidebar_system"></jsp:param>
				<jsp:param name="param_sidebar_sec" value="4"></jsp:param>
			</jsp:include>
			<div id="column">
				<jsp:include page="../../../Common/Common/ManagerHeader.jsp"></jsp:include>
				<h1 id="col_nav">首页 > 栏目管理</h1>
				<div id="col_area">
				<div id="all_col">
	<%
	ArrayList<Program> l=(ArrayList<Program>)request.getAttribute("programList");
	for(Program p:l) {
		%>
			<%-- 一级栏目--%>
					<div class="col_area_col">
						<div  class="col_area_fir_col">
							<form action="saveProgram" method="post">
								<input type="hidden" name="id" value="<%=p.getId() %>">
								<input type="hidden" name="status" value="0">
								<input type="hidden" name="num" value="<%=p.getNum() %>">
								<input type="text" name="name" value="<%=p.getName() %>">
								<button type="submit">修改</button>
							</form>
						</div>
			<%
			Program[] ppp=(Program[])p.getPrograms().toArray(new Program[p.getPrograms().size()]);
			for(int i=0;i<ppp.length;i++) {
				int hid,fid;
				if(i==0) {hid=ppp[i].getId();}//上一个id
				else {hid=ppp[i-1].getId();}
				if(i==ppp.length-1) {fid=ppp[i].getId();}//下一个id
				else {fid=ppp[i+1].getId();}
				%>
				<%-- 二级栏目--%>
					<div  class="col_area_sec_col">
						<form action="saveProgram" method="post">
							<div class="program_label">
<%
				if(ppp[i].getRank()==-1) {
%>
							<a href="operationProgram?id=<%=ppp[i].getId() %>&status=<%=2 %>">可见</a>
<%
				} else {
%>
							<a href="operationProgram?id=<%=ppp[i].getId() %>&status=<%=-1 %>">不可见</a>
<%
				}
%>
							<a href="moveProgram?isUp=1&hid=<%=hid %>&id=<%=ppp[i].getId() %>&fid=<%=fid %>" class="up">上移</a>
							<a href="moveProgram?isUp=0&hid=<%=hid %>&id=<%=ppp[i].getId() %>&fid=<%=fid %>" class="down">下移</a>
							</div>
							<input type="hidden" name="num" value="<%=ppp[i].getNum() %>">
							<input type="hidden" name="id" value="<%=ppp[i].getId() %>">
							<input type="hidden" name="status" value="<%=ppp[i].getStatus()%>">
							<input type="text" name="name" style="width: 5em; float: left;" value="<%=ppp[i].getName() %>">
							<button type="submit" style="float: left;margin-top: 3px;">修改</button>
						</form>
				<%-- 设置栏目不可用 --%>
				
					</div>
				<%
			}
			%>
		</div>		
		<%
	}
	%>
			</div>
		</div>
		<p id="footer">Copyright © 2016 广东工业大学创客学院</p>
	</div>
	</div>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/common/Common.js"></script>
<script type="text/javascript" src="/GDUTHackerSpace/Web/js/manager/ManagerPage.js"></script>
<script type="text/javascript">
 	function upOrDown() {
		var p = document.getElementsByTagName("a");
		for (var j = 0 ;j< p.length;j++){
				EventUtil.addHandler(p[j],"click",function(){
					var addNum = 10;//用来增加序号假设二级栏目最多有十个
					var event = EventUtil.getEvent(event);
					var target = EventUtil.getTarget(event);
					//进行操作
					if(target.className=='up') {
						var num = new Number(target.nextElementSibling.nextElementSibling.value);
							target.nextElementSibling.nextElementSibling.value=num>10 ? num-10: 0;
						} else {
						var num = new Number(target.nextElementSibling.value);
							target.nextElementSibling.value=10+num;
						}						
			});
		}
	}


	addLoadEvent(upOrDown);
</script>

</body>
</html>