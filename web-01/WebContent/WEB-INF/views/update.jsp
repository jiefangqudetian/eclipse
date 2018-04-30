<%@page import="com.kaishengit.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" />
</head>
<body>
    <div class="container">
    	<div class="row">
    		<div class="col-md-7">
    			
    			<form action="/update" id="updateForm" method="post">
    				<input type="hidden"  name="id" value="${book.id }" class="form-control"/>
    				<div class="form-group">
    					<label for="">书籍名称</label>
    					<input type="text" class="form-control" name="name" value="${book.name} "/>
    				</div>
    				<div class="form-group">
    					<label for="">书籍作者</label>
    					<input type="text" class="form-control" name="author" value="${book.author }"/>
    				</div>
    				<div class="form-group">
    					<label for="">书籍出版社</label>
    					<input type="text" class="form-control" name="publish" value="${book.publish }"/>
    				</div>
    				<div class="form-group">
    					<label for="">ISBN</label>
    					<input type="text" class="form-control" name="isbn" value="${book.isbn }"/>
    				</div>
    				<div class="form-group">
    					<label for="">总数量</label>
    					<input type="text" class="form-control" name="count" value="${book.count }"/>
    				</div>
    				<div class="form-group">
    					<label for="">当前数量</label>
    					<input type="text" class="form-control" name="currCount" value="${book.currcount }"/>
    				</div>
    			</form>
    			<button class="btn btn-primary" id="updateBtn">修改</button>
    		</div>
    	</div>
    </div>
    <script src="/static/js/query3.2.1.js"></script>
    <script>
    	$(function(){
    		$("#updateBtn").click(function(){
    			$("#updateForm").submit();
    		});
    	});
    </script>
</body>
</html>