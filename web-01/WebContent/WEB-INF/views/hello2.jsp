<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<%
			pageContext.setAttribute("age", 12);
			int age = (Integer)pageContext.getAttributesScope("age");
			String name = (String)request.getAttribute("name");
			String address = (String)session.getAttribute("address");
			String status = (String)application.getAttribute("status");
		%>
		<h4><%=age %></h4>
		<h4><%=name %></h4>
		<h4><%=address %></h4>
		<h4><%=status %></h4>
	
	</div>
    
</body>
</html>