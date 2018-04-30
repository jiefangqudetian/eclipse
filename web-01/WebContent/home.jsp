<%@page import="java.util.List"%>
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
	//String name = request.getParameter("username");//改为password为什么返回null
	String name = (String)request.getAttribute("userName");
	int money = (int)request.getAttribute("money");
	List<String> lists = (List<String>)request.getAttribute("lists");
	%>
	<h1>登录成功</h1>
    <h1>欢迎光临,<%=name %>,您的余额为：<%=money %></h1>
    <span>您的兴趣竟然是：</span>
    <ul>
    <%for(String str:lists){ %>
    <li><%=str %></li>
    <%} %>
    
    </ul>
    
    <% %>
</body>
</html>