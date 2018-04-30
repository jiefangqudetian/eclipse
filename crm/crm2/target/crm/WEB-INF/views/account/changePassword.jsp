<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>凯盛软件CRM|首页</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 	<%@ include file="../include/css.jsp"%> 
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
	<%@ include file="../include/header.jsp"%> 

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
	
	
  	<jsp:include page="../include/sider.jsp">
 		<jsp:param value="home" name="param"/>
 	</jsp:include> 

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改密码</h3>
                   	<div class="box-tools pull-right">
                        <a href="/account/home" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回首页</a>
                    </div>
                </div>
                <div class="box-body">
                    <form  id="form" method="post">
                        <div class="form-group">
                            <label>原始密码</label>
                            <input type="password" name="oldpassword" class="form-control" >
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" name="newpassword" class="form-control" >
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" name="confirm" class="form-control" >
                        </div>
                        <div class="alert alert-danger" hidden id="message"></div>
                    </form>
                </div>
                <div class="box-footer">
                    <button id="savebtn" class="btn btn-primary">保存</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
	  <%@ include file="../include/footer.jsp"%> 

</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp"%> 
<script>
	$("#savebtn").click(function(){
		$("#form").submit()	
		
	});
	$("#form").validate({
		errorClass : 'text-danger',
		errorElement : 'span',
		rules : {
			oldpassword : {
				"required" : true
			},
			newpassword : {
				"required" : true
			},
			confirm : {
				"required" : true
			}
			
		},
		messages : {
			oldpassword : {
				"required" : "请输入原始密码"
			},
			newpassword : {
				"required" : "请输入新密码"
			},
			confirm : {
				"required" : "请对新密码进行确认"
			}
		},
		submitHandler : function(form) {
			$.ajax({
				url : '/account/changePassword',
				type : 'post',
				data : $("#form").serialize(),
				beforeSend : function() {
					$("#savebtn").text("保存中...").attr(
							"disabled", "disabled");
				},
				success : function(data) {
					if (data.state == 'success') {
						alert('保存成功，跳转首页');
						window.location.href = "/account/home";
						
					} else {
						$("#message").text(data.message).show();
					}
				},
				error : function() {
					layer.alert("系统异常");
				},
				complete : function() {
					$("#savebtn").text("保存")
							.removeAttr("disabled");
				}
			});
		}
	});

</script>
</body>
</html>