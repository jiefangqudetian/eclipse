<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css" />
</head>
<body>
    <div class="container">
    <h5>文件上传的表单的method属性必须是post</h5>
    <h5>文件上传的表单控件使用的是input元素的type属性为file</h5>
    <h5>设置文件上传的表单的form元素的enctype属性的值为multipart/form-data</h5>
    	<div class="row">
    		<div class="col-md-5">
    			<form action="/upload2" method="post" enctype="multipart/form-data">
    				<div class="form-group">
    					<input type="text" name="desc"  class="form-control"/><br />
    					<input type="file" name="file1" />
    				</div>
    				<button class="btn btn-primary">文件上传</button>
    			</form>
    			<br />
    			<a href="/download?fileName=567e872f-4a22-4df2-abf0-bd957569cdd6.jpg&name=我的照片.jpg">我的照片</a>
    			<a href="/download?fileName=567e872f-4a22-4df2-abf0-bd957569cdd6.jpg">预览我的照片</a>
    		</div>
    	</div>
    </div>
</body>
</html>