<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
	<button id="btn">跳转</button>
    <%-- <%request.getRequestDispatcher("/page2.jsp").forward(request, response); %> --%>
    <%-- <%response.sendRedirect("/page2.jsp"); %> --%>
    <script src="/static/js/jquery-1.12.4.js"></script>
    <script>
    	$("#btn").click(function(){
    		alert(123);
    		<%String name = null;
    		
    		System.out.println("tom");%>
    		<%-- <%response.sendRedirect("/page2.jsp"); %> --%>
    	});
    </script>
</body>
</html>