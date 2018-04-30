<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h4>数据库访问异常</h4>
    <%
    	String message = exception.getMessage();
    	Integer code = (Integer)request.getAttribute("javax.servlet.error.status_code");
   		Class class1 = (Class)request.getAttribute("javax.servlet.error.exception_type");
    	String string = (String)request.getAttribute("javax.servlet.error.message");
    	Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
    	String string2 = (String)request.getAttribute("javax.servlet.error.request_uri");
    	String string3 = (String)request.getAttribute("javax.servlet.error.servlet_name");
    	%>
    
    <%=message %>
    <br />
    <%=code %>   <br />
    <%=class1 %>  <br />
    <%=string %>  <br />
    <%=throwable %>  <br />
    <%=string2 %>  <br />
    <%=string3 %> <br />
    
</body>
</html>