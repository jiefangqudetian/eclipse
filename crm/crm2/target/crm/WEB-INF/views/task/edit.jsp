<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <!--头部-->
 	<%@ include file="../include/header.jsp"%>
 	 <!--侧边-->
   <jsp:include page="../include/sider.jsp">
   		<jsp:param value="task_add" name="param"/>
   	</jsp:include>


    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增待办任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/list" class="btn btn-default btn-sm">
                            <i class="fa fa-arrow-left"></i> 返回列表
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" id="saveForm">
                        <div class="form-group">
                            <label>任务名称</label>
                            <input type="text" name="taskName" class="form-control" value="${taskName}">
                        </div>
                        <div class="form-group">
                            <label>完成日期</label>
                            <input type="text" name="finishTime" class="form-control" value="${finishTime}" id="datepicker">
                        	<input type="hidden" name="taskId" value="${taskId}" />
                        </div>
                    </form>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button class="btn btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    
    <!-- 底部 -->
   <%@ include file="../include/footer.jsp"%>
</div>
<!-- ./wrapper -->
<!-- javascript -->
<%@ include file="../include/js.jsp"%>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
$(function () {
	/* 
	https://bootstrap-datepicker.readthedocs.io/en/latest/ 
	http://www.bootcss.com/p/bootstrap-datetimepicker/	
	*/
    var picker = $('#datepicker').datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        startDate:moment().format("yyyy-MM-dd")
    });
    
	$("#saveBtn").click(function(){
		$("#saveForm").submit();
	});
	
	$("#saveForm").validate({
		errorClass : 'text-danger',
		errorElement : 'span',
		rules : {
			taskName :{
				"required" : true
			},
			finishTime : {
				"required" : true,
			}
		},
		messages :{
			taskName :{
				"required" : "请输入任务名称"
			},
			finishTime : {
				"required" : "请输入结束时间",
			}
		},
		submitHandler : function(form){
			$.ajax({
				url:'/task/edit',
				type:'post',
				data:$("#saveForm").serialize(),
				beforeSend : function(){
					$("#saveBtn").text("保存中...").attr("disabled","disabled");
				},
				success : function(data){
					if(data.state=='success'){
						window.location.href="/task/list";
					}else{
						layer.alert(data.message);
					}
				},
				error : function(){
					alert("系统异常");
				},
				complete : function(){
					$("#saveBtn").text("保存").removeAttr("disabled");
				}
			});
			
		}
	})
	
});


</script>


</body>
</html>