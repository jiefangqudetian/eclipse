<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <%
    	session.setMaxInactiveInterval(2);
    	String sessionId = session.getId();
    	long time = session.getCreationTime();
    %>
    <h4><%=sessionId %></h4>
    <h4><%=time %></h4>
</body>
</html>