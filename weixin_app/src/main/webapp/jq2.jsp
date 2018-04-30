<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <input type="text" name="username" id="name"/><span id="check"></span>
    <script src="/static/js/jquery-1.12.4.js"></script>
    <script>
    	/* (function(){
    		//创建input对象  span对象
    		var input = document.getElementById("name");
    		var span = document.getElementById("check");
    		var xmlHttp = kaisheng.createXmlHttp();
    		//input输入框改变事件
    		input.onchange = function(){
    			//解决将参数进行URLEncode编码，指定请求方式，发送地址
    			xmlHttp.open("get","/ajax2?name=" + encodeURIComponent(input.value));
    			//在send之前设置回调函数
    			xmlHttp.onreadystatechange = function(){
    				if(xmlHttp.readyState == 4){
    					if(xmlHttp.status == 200){
    						//获取服务器端返回的数据
    						var data = xmlHttp.responseText;
    						alert(data);
    						if(data == "yes"){
    							//span对象内容设置为“√”
    							span.innerText = "√";
    						} else {
    							//span对象内容设置为“×”
    							span.innerText = "×";
    						}
    						
    					}
    					
    					
    				}
    			}
    			
    			xmlHttp.send();
    			
    		}
    		
    		
    	})(); */
    	$(function(){
    		var span = document.getElementById("check");
    		$("#name").change(function(){
    			//发起ajax异步请求
    			$.get("/ajax2",{"name":$(this).val()}).done(function(data){
    				alert(data);
    				if(data=="yes"){
    				span.innerText="√";
    				} else {span.innerText="×";}
    			}).error(function(){
    				alert("系统繁忙");
    			});
    		});
    	})
    	
    </script>
</body>
</html>