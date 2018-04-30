<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>页面3</h4>
    <%
    String name3 = (String)request.getAttribute("name");
    %>
    <h4><%=name3 %></h4>
</body>
</html>