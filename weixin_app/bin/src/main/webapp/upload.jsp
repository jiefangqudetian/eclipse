<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css" />
    <link rel="stylesheet" href="/static/js/upload/webuploader.css" />
</head>
<body>
    <div class="container">
    	<div class="panel panel-default">
    		<div class="panel-heading">
    			<div id="picker">请选择文件</div>
    			<button id="btn" class="btn btn-default">开始上传</button>
    		</div>
    		<div class="panel-body">
    			<ul class="list-group" id="fileList"></ul>
    		</div>
    	</div>
    
    </div>
    
    <script src="/static/js/jquery-1.12.4.js"></script>
    <script src="/static/js/upload/webuploader.js"></script>
    <script>
    	$(function(){
    		var uploader = WebUploader.create({
    			//指定flash文件的上传路径
    			swf:'/static/js/upload/Uploader.swf',
    			//指定文件上传的服务器地址
    			server:'/file/upload',
    			//指定文件上传的html控件
    			pick:'#picker',
    			fileVal:'file'
    		});
    		
    		//当有文件被添加进该队列时绑定该事件
    		uploader.on('fileQueued',function(file){
    			var li = "<li class='list-group-item' id='"+file.id+"'>"+file.name+"<span style='padding-left:20px'></span></li>";
    			//file.name为什么不放span中
    			$("#fileList").append(li);
    		})
    		//文件上传进度
    		uploader.on('uploadProgress',function(file,percentage){
    			$("#"+file.id).find("span").text(parseInt(percentage*100)+"%");
    		})
    		//文件上传成功事件
    		uploader.on('uploadSuccess',function(file){
    			$("#"+file.id).find("span").text('√');
    		})
    		//文件上传失败事件
    		uploader.on('uploadError',function(file){
    			$("#"+file.id).find("span").text('×');
    		})
    		
    		//文件不论上传失败还是成功都执行的事件
    		uploader.on('uploaderComlpete',function(file){
    			
    		})
    		//文件上传事件
    		$("#btn").click(function(){
    			uploader.upload();
    		})
    		
    		
    	})
    </script>
</body>
</html>