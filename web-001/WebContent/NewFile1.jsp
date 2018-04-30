<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>页面2</h4>
    <%
    	String name2 = (String)request.getAttribute("name");
    	String password2 = (String)request.getAttribute("password");
    	String name = request.getParameter("name");
    %>
    <h4><%=name2 %></h4>
    <h4><%=name %></h4>
    <h4><%=password2 %></h4>
</body>
</html>