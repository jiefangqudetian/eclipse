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
    		</div>
    		<div class="panel-body">
				<div id="result"></div>    			
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
    			fileVal:'file',
    			//文件自动上传
    			auto:true,
    			//上传文件类型仅限图片，其他无法选择
    			accept:{
    				title:'Image',
    				extensions:'gif,jpg,jpeg,bmp,png',
    				mimeTypes:'image/*'
    			}
    		});
    		
    		//文件上传成功事件
    		uploader.on('uploadSuccess',function(file,resp){
    			alert(resp.state);
    			if(resp.state == 'success'){
    				$("<image style='height:100px'></image>").attr("src",resp.data).appendTo($("#result"))
    			}
    		})
    		//文件上传事件,每次上传清空缩略图
    		uploader.on('startUpload',function(){
    			$("#result").html("");
    		})
    		
    		
    	})
    </script>
</body>
</html>