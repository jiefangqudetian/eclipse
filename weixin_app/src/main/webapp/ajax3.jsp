<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <input type="text" name="username" id="name"/>
    <button id="btn">save</button>
    <script src="/static/js/ajax.js"></script>
    <script>
    	//var $ = ...;
    	(function(){
    		//创建input对象  span对象
    		var input = document.getElementById("name");
    		var span = document.getElementById("check");
    		var xmlHttp = kaisheng.createXmlHttp();
    		//input输入框改变事件
    		input.onchange = function(){
    			//解决将参数进行URLEncode编码，指定请求方式，发送地址
    			xmlHttp.open("post","/save");
    			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    			//在send之前设置回调函数
    			xmlHttp.onreadystatechange = function(){
    				if(xmlHttp.readyState == 4){
    					if(xmlHttp.status == 200){
    						//获取服务器端返回的数据
    						var data = xmlHttp.responseText;
    						alert(data);
    					} else {
    						alert("系统繁忙，请稍后再试");
    					}
    				}
    			}
    			xmlHttp.send("name=" + input.value);
    		}
    	})();
    </script>
</body>
</html>