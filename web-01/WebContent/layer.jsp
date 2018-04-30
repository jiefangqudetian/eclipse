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
    <h5>你好</h5>
    
    <button id="msg">提示框</button>
    <button id="alertBtn">弹出框</button>
    <button id="confirmBtn">询问框</button>
    
    <script src="/static/js/query3.2.1.js"></script>
    <script src="/static/js/layer/layer.js"></script>
    <script>
    	$(function(){
    		$("#msg").click(function(){
    			layer.msg("删除成功！");
    		});
    		
    		$("#alertBtn").click(function(){
    			layer.alert("欢迎光临");
    		});
    		
    		$("#confirmBtn").click(function(){
    			layer.confirm("确定要删除么？",function(){
    				layer.msg("删除成功");
    			},function(){
    				layer.msg("删除取消");
    			});
    		});
    		
    	});
    </script>
</body>
</html>