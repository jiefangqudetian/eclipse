<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>页面1</h4>
    <%
    	request.setAttribute("name", "小明");
    	String name = (String)request.getAttribute("name");
    	
    %>
    <h4><%=name %></h4>
    <h4><a href="/NewFile1?name=tom">页面2（间接）</a></h4>
    <h4><a href="/NewFile1.jsp?name=tom">页面2（直接）</a></h4>
    <h4><a href="/NewFile2.jsp">页面3</a></h4>
    <%
    response.sendRedirect("NewFile1.jsp");
    %>
</body>
</html>