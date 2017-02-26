<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String message = (String)request.getAttribute("message");
	String url = (String)request.getAttribute("url");
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转页面</title>
<link rel="stylesheet" href="/GDUTHackerSpace/Web/css/common/Error.css"/>
</head>
<body>

<div  id="error_page_message" style="width: 600px; margin: 30px auto;">
	<p><%=message %></p>
	<p>请稍等，页面待隔 <span id="time">5</span> 自动跳转。或者你可以点击<a href="<%=url%>">这里</a>跳转页面</p>

</div>
<script type="text/javascript">  
    delayURL();    
    function delayURL() { 
        var delay = document.getElementById("time").innerHTML;
 		var t = setTimeout("delayURL()", 1000);
        if (delay > 0) {
            delay--;
            document.getElementById("time").innerHTML = delay;
        } else {
     clearTimeout(t); 
            window.location.href = "<%=url%>";
        }        
    } 
</script>
</body>
</html>