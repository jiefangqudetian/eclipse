<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css" />
    <link rel="stylesheet" href="/static/js/editor/styles/simditor.css" />
    <link rel="stylesheet" href="/static/js/emoji/styles/simditor-emoji.css" />
</head>
<body>
    <div class="container">
    	<div class="pannel pannel-default">
    		<form action="/editor" method="post">
    			<textarea id="editor" name="content" placeholder="Balabala" autofocus></textarea>
    			<button class="btn btn-success">发布</button>
    		</form>
    	</div>
    </div>
    
    <script src="/static/js/jquery-1.12.4.js"></script>
    <script src="/static/js/editor/scripts/module.js"></script>
	<script src="/static/js/editor/scripts/hotkeys.js"></script>
	<script src="/static/js/editor/scripts/uploader.js"></script>
    <script src="/static/js/editor/scripts/simditor.js"></script>
    <script src="/static/js/emoji/lib/simditor-emoji.js"></script>
    <script>
	    $(function(){
	    	
    	var editor = new Simditor({
    		textarea:$('#editor'),
    		toolbar:[
    			'blod',
    			'italic',
    			'underline',
    			'image',
    			'emoji'
    		],
    		emoji:{
    			imagePath:'/static/js/emoji/images/emoji/',
    			images:['+1.png', '100.png', '109.png',
                    'smile.png', 'smiley.png', 'laughing.png' ,'blush.png' ]
    		},
    		upload:{
    			//图片上传地址
    			url:'/file/upload',
    			//上传空间的name属性值
    			fileKey:'file'
    		}
    	});
	    })
    
    </script>
</body>
</html>