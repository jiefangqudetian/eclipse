<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!-- //怎么导的 -->
<%@ page import="com.kaishengit.entity.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<a href="/Add" class="btn btn-primary">新增书籍</a> <a href="/logout"
			class="btn btn-default">退出登录</a>
		<table class="table">
			<tr>
				<th>书籍名称</th>
				<th>书籍作者</th>
				<th>出版社</th>
				<th>ISBN</th>
				<th>总数量</th>
				<th>当前数量</th>
				<th>操作</th>
			</tr>
			<tbody>
				<c:forEach items="${page.items }" var="book">
					<tr>
						<td>${book.name }</td>
						<td>${book.author }</td>
						<td>${book.publish }</td>
						<td>${book.isbn }</td>
						<td>${book.count }</td>
						<td>${book.currcount }</td>
						<td><a href="javascript:;" rel="${book.id }" class="del">删除</a>
							<!-- //触发事件 --> <a href="/update?id=${book.id }">修改</a> <!-- //直接跳转 -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul id="pagination" class="pagination-sm pull-right"></ul>
		<!-- 插件jQuery Pagination -->
	<%-- 	<ul class="pagination pull-right">
		<c:choose>
			<c:when test="${page.pageNo==1}">
				<li class="disabled"><a href="javascript:;">首页</a></li>
				<li class="disabled"><a href="javascript:;">上一页</a></li> 
			</c:when>
			<c:otherwise>
				<li><a href="/list">首页</a></li>
				<li><a href="/list?p=${page.pageNo-1 }">上一页</a></li> 
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.pageNo==page.totalpage }">
				<li class="disabled"><a href="javascript:;">下一页</a></li>
				<li class="disabled"><a href="javascript:;">末页</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/list?p=${page.pageNo+1 }">下一页</a></li>
				<li><a href="/list?p=${page.totalpage} ">末页</a></li>
			</c:otherwise>
		</c:choose>
		</ul> --%>
	</div>
	<script src="/static/js/query3.2.1.js"></script>
	<script src="/static/js/layer/layer.js"></script>
	<script src="/static/js/jquery.twbsPagination.js"></script>
	<script>
		$(function() {
			$("#pagination").twbsPagination({
				totalPages:${page.totalpage},
				visiblePages:3,
				href:'/list?p={{number}}',
				first:'首页',
				last:'末页',
				prev:'上一页',
				next:'下一页'
				
			});
			$(".del").click(function() {
				var id = $(this).attr("rel");//
				layer.confirm("确定要删除么", function() {
					location.herf = "/del?id=" + id;
				});
				/* if(confirm("确认要删除么？")){
					// /del?id=1 修改浏览器地址栏的url
					location.href="/del?id="+id; //?后不能有空格
					//js中location对象 confirm对象 
				}*/
			})
		})
	</script>
</body>
</html>