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
    	<div id="newalert" class="alert alert-danger" style="display:none"><a id="text" href="javascript:;"></a></div>
    	<ul class="list-group" id="weibo"></ul>
    </div>
    <script src="/static/js/jquery-1.12.4.js"></script>
    <script>
    	$(function(){
    		var maxId = null;
    		var newmessage = null;
    		$.get("/message").done(function(data){
    			for(var i = 0; i<data.length; i++){
    			//找到当前数据的最大值
    			maxId = data[0].id;
    			alert(data[0].id);
    			alert(data[0].message);
    			var li = "<li class='list-group-item'>"+data[i].message+"</li>";
    			$("#weibo").append(li);}
    		}).error(function(){
    			alert("系统异常")
    		});
    		//关闭服务器后，会alert系统异常，原因在于关闭了服务器，jsp那边还执行最后一次间歇调用,request请求不成功
    		setInterval(function(){
    			$.get("/message",{"maxId":maxId}).done(function(data){
    				if(data.length>0){
    					$("#text").text("有" + data.length + "条新微博");
    					$("#newalert").show();
    					newMessage = data;
    				}
    			}).error(function(){
    				alert("系统异常")
    			})
    		},5000);
    		//新消息newMessage是倒序排列，比如[{"id":5,"message":"c"},{"id":4,"message":"b"}]
    		//消息要显示在上面所以用prepend方法
    		//如果正向循环的话将会是先显示5，再显示4，所以要逆向循环，下标大的反而id小，然后逐条往上添加
    		$("#text").click(function(){
    			//alert框消失
    			$("#newalert").hide();
    			//添加数据
    			for(var i = newMessage.length-1; i >= 0;i--){
    				//修改maxid
    				maxId = newMessage[0].id;
    				var li = "<li class='list-group-item'>"+newMessage[i].message+"</li>";
    				$("#weibo").prepend(li);
    			}
    		});
    		
    		
    	})
    </script>
</body>
</html>