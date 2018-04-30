<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <title>凯盛软件CRM-首页</title>
   <!-- Tell the browser to be responsive to screen width -->
   <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

   <%@ include file="../include/header.jsp"%>
   <jsp:include page="../include/sider.jsp">
   		<jsp:param value="task_list" name="param"/>
   	</jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
		<div><%=request.getAttribute("sex") %></div>
		<div><%=request.getParameter("name") %></div>
		<div><%=request.getParameter("finishTime") %></div>
      
    </div>
    <!-- /.content-wrapper -->
<!-- 底部 -->
<%@ include file="../include/footer.jsp"%>
</div>
<%@ include file="../include/js.jsp"%> 
<script>
	
</script>
</body>
</html>