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
    			
    			<c:if test="${not empty message }">
    				<div class="alert alert-danger">${message }</div>
    			</c:if>
    			<form action="/Add" id="addForm" method="post">
    				<div class="form-group">
    					<label for="">书籍名称</label>
    					<input type="text" class="form-control" name="name" value="${name }"/>
    				</div>
    				<div class="form-group">
    					<label for="">书籍作者</label>
    					<input type="text" class="form-control" name="author" value="${author }"/>
    				</div>
    				<div class="form-group">
    					<label for="">书籍出版社</label>
    					<input type="text" class="form-control" name="publish" value="${publish }"/>
    				</div>
    				<div class="form-group">
    					<label for="">ISBN</label>
    					<input type="text" class="form-control" name="isbn" value="${isbn }"/>
    				</div>
    				<div class="form-group">
    					<label for="">书籍数量</label>
    					<input type="text" class="form-control" name="num" value="${num }"/>
    				</div>
    			</form>
    			<button class="btn btn-primary" id="addBtn">新增</button>
    		</div>
    	</div>
    </div>
    <script src="/static/js/query3.2.1.js"></script>
    <script>
    	$(function(){
    		$("#addBtn").click(function(){
    			$("#addForm").submit();
    		});
    	});
    </script>
</body>
</html>