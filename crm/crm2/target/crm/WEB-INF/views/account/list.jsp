<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>凯盛软件CRM-员工页</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 	<%@ include file="../include/css.jsp"%> 
 	<link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

 <%@ include file="../include/header.jsp"%> 
<%--  <%@ include file="../include/sider.jsp"%>  --%>
 <jsp:include page="../include/sider.jsp">
 	<jsp:param value="account" name="param"/>
 </jsp:include> 
  <!-- =============================================== -->

  <!-- 右侧内容部分 -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">
      <div class="row">
      
      <!--部门列表  -->
        <div class="col-md-2">
            <div class="box">
              <div class="box-body">
                <button class="btn btn-default" id="addDept">添加部门</button>
                <ul id="ztree" class="ztree"></ul>
              </div>
            </div>
        </div>
        
        <!--员工列表  -->
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box">
              <div class="box-header with-border">
                <h3 class="box-title">员工管理</h3>
                <div class="box-tools pull-right">
                  <button type="button" class="btn btn-box-tool" id="addAcc"  title="Collapse">
                    <i class="fa fa-plus"></i> 添加员工</button>
                </div>
              </div>
              <div class="box-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th>姓名</th>
                      <th>部门</th>
                      <th>手机</th>
                      <th>功能</th>
                    </tr>
                  </thead>
				  <tbody id="body">
				  </tbody>
                </table>
                <ul class="pagination pull-right" id="pagination"></ul>
              </div>
            </div>
            <!-- /.box -->
        </div>
      </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
    <!-- Modal 模态框    默认隐藏  添加员工 -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">新增员工</h4>
        </div>
        <div class="modal-body">
        	<form id="addAccountForm">
                     <div class="form-group">
                         <label>姓名</label>
                         <input type="text" class="form-control" name="username">
                     </div>
                     <div class="form-group">
                         <label>手机号码</label>
                         <input type="text" class="form-control" name="mobile">
                     </div>
                     <div class="form-group">
                         <label>密码(默认000000)</label>
                         <input type="password" class="form-control" name="password" value="000000" >
                     </div>
                     <div class="form-group">
                         <label>所属部门</label>
                         <div id="checkboxList">
                         </div>
                     </div>
             </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
        </div>
      </div>
    </div>
  </div>
<!-- Modal 模态框    默认隐藏  编辑员工 -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">编辑员工</h4>
        </div>
        <div class="modal-body">
        	<form id="editAccountForm">
                     <div class="form-group">
                         <label>姓名</label>
                         <input type="text" class="form-control" name="userName">
                     </div>
                     <div class="form-group">
                         <label>手机号码</label>
                         <input type="text" class="form-control" name="mobile">
                     </div>
                     <div class="form-group">
                         <label>密码(默认000000)</label>
                         <input type="password" class="form-control" name="password" value="000000">
                     </div>
                     <div class="form-group">
                         <label>所属部门</label>
                         <div id="editcheckboxList">
                         </div>
                     </div>
                 </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" id="editBtn">保存</button>
        </div>
      </div>
    </div>
  </div>
  <!-- 底部 -->
  <%@ include file="../include/footer.jsp"%> 

</div>
<!-- ./wrapper -->

<!--java script-->
<%@ include file="../include/js.jsp"%> 
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script src="/static/plugins/jQuery/jquery.twbsPagination.js"></script>

<script>
  $(function(){
	  	
	  var editId = null;
	  var deptId = null;
	  var $pagination = $('#pagination');
	  var defaultOpts = {
	       totalPages: 20,
	  };
	  $pagination.twbsPagination(defaultOpts);
	  pageload(deptId);
	  
	  function pageload(deptId){
		   $.ajax({
		        url:"/account/list.json",
		        type:"get",
		        data:{
		        	"deptId":deptId
		        },
		        success: function (data) {
		        	var page = data.data;
		            var totalPages = page.totalpage;
		            var currentPage = $pagination.twbsPagination('getCurrentPage');
		            $pagination.twbsPagination('destroy');
		            $pagination.twbsPagination($.extend({}, defaultOpts, {
		                startPage: currentPage,
		                totalPages: totalPages,
		                visiblePages:3,
		    		    // href:'/list?p={{number}}',
		    		    first:'首页',
		    		    last:'末页',
		    		    prev:'上一页',
		    		    next:'下一页',
		    		    onPageClick: function (event, page) {
		    		    	// 自动执行第一页数据
		    		    	load(deptId,page);
		    		    	/* var page = data.data;
				        	$("#body").html("");
				            for(var i = 0; i < page.items.length; i++) {
				            	var account = page.items[i];
				            	var html = "<tr> <td>" + account.username+"</td> <td>" + account.deptName + "</td> <td>"+account.mobile+"</td> <td> <a href=''>禁用</a><a href=''>删除</a><a href=''>编辑</a></td></tr>";
				            	$("#body").append(html);
				            } */
		    	        }
		            }));
		        }
		    });
	   }
	  
	  // 加载table数据
	  function load(deptId,p) {
		  $.ajax({
		        url:"/account/list.json",
		        type:"get",
		        data:{
		        	"deptId":deptId,
		        	"p":p
		        },
		        success: function (data) {
		        	var page = data.data;
		        	$("#body").html("");
		            for(var i = 0; i < page.items.length; i++) {
		            	var account = page.items[i];
		            	var html = "<tr> <td>" + account.username+"</td> <td>" + account.deptName + "</td> <td>"+account.mobile+"</td> <td><a href='javascript:;' class='del' rel='" + account.id + "'>删除</a><span>&nbsp</span><a href='javascript:;' class='edit' rel='" + account.id + "'>编辑</a></td></tr>";
		            	$("#body").append(html);
		            }
		        }
		    });
	  }
	  
	// 事件委托删除
	  	$(document).delegate(".del","click",function(){
	  		alert("确认删除？");
	  		var id = $(this).attr("rel");
	  		$.ajax({
	  		   type: "POST",
	  		   url: "/account/delete",
	  		   data: {
		        	"accId":id
		        },
	  		   success: function(msg){
	  		     alert( "删除成功" );
	  		     pageload(deptId)
	  		   }
	  		});
	  	})
	  //事件委托编辑
	  	$(document).delegate(".edit","click",function(){
	  		var id = $(this).attr("rel");
	  		$("#edithtml").remove();
	  		var edithtml ='<div id="edithtml" class="form-group"><input type="hidden" class="form-control" name="editId" value="'+id+'"></div>';
			$("#editAccountForm").append(edithtml);
	  		$.get("/dept/list").done(function(data){
				//每次添加前清空上一次添加时的列表
				$("#editcheckboxList").html("");
				//动态的显示deptList列表
				for(var i=0;i<data.length;i++){
					var dept=data[i];
					if(dept.pId==1){
						var html='<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+ dept.id +'">'+dept.deptName+'</label>'
						$("#editcheckboxList").append(html);
					}
				}
				$('#editModal').modal({
					backdrop:'static',
					keybord:false
					
				});
			}).error(function(){
				layer.msg("系统异常，暂时无法编辑");
			})
	  	})
	  
	  /*事件：添加员工，模态框显示*/
	$("#addAcc").click(function(){
		$.get("/dept/list").done(function(data){
			//每次添加前清空上一次添加时的列表
			$("#checkboxList").html("");
			//动态的显示deptList列表
			for(var i=0;i<data.length;i++){
				var dept=data[i];
				if(dept.pId==1){
					var html='<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+ dept.id +'">'+dept.deptName+'</label>'
					$("#checkboxList").append(html);
				}
			}
			
			$('#myModal').modal({
				backdrop:'static',
				keybord:false
				
			});
		}).error(function(){
			layer.msg("系统异常，暂时无法添加");
		})
	});
	  
	  /*事件：保存模态框显示,提交表单*/
	$("#saveBtn").click(function(){
		$("#addAccountForm").submit();
	});
	  //模态框表单提交简单验证
	$("#addAccountForm").validate({
		errorClass:'text-danger',
		errorElement:'span',
		rules:{
			userName:{
				required:true
			},
			mobile:{
				required:true
			},
			password:{
				required:true
			},
			deptId:{
				required:true
			}
		},
		messages:{
			userName:{
				required:"请填写员工名称"
			},
			mobile:{
				required:"请填写员工电话"
			},
			password:{
				required:"请填写员工密码"
			},
			deptId:{
				required:"请填写员工部门"
			}
		},
		submitHandler:function(){
			$.ajax({
				url:'/account/add',
				type:'post',
				data:$("#addAccountForm").serialize(),
				//如果服务器有响应则成功
				success:function(data){
					//如果响应状态为success
					if(data.state=='success'){
						layer.alert("新增成功");
						$('#myModal').modal('hide');
						pageload(deptId);
					}else{
						layer.alert(data.message);
					}
				},
				error:function(){
					layer.alert("添加失败，系统异常");
				}
			})
		}
	})
	
	  /*事件：编辑模态框显示,提交表单*/
	$("#editBtn").click(function(){
		$("#editAccountForm").submit();
	});
	  //编辑表单提交简单验证
	$("#editAccountForm").validate({
		errorClass:'text-danger',
		errorElement:'span',
		rules:{
			userName:{
				required:true
			},
			mobile:{
				required:true
			},
			password:{
				required:true
			},
			deptId:{
				required:true
			}
		},
		messages:{
			userName:{
				required:"请填写员工名称"
			},
			mobile:{
				required:"请填写员工电话"
			},
			password:{
				required:"请填写员工密码"
			},
			deptId:{
				required:"请填写员工部门"
			}
		},
		submitHandler:function(){
			
			$.ajax({
				url:'/account/edit',
				type:'post',
				data:$("#editAccountForm").serialize(),
				//如果服务器有响应则成功
				success:function(data){
					//如果响应状态为success
					if(data.state=='success'){
						layer.alert("修改成功");
						$('#editModal').modal('hide');
						pageload(deptId);
					}else{
						layer.alert(data.message);
					}
				},
				error:function(){
					layer.alert("添加失败，系统异常");
				}
			})
		}
	})
	
	  
	  //添加部门
	$("#addDept").click(function(){
		layer.prompt({title:"请输入部门名称"},function(text,index){
			//关闭输入框
			layer.close(index);
			//发送ajax请求到db
			$.post("/dept/add",{"deptName":text}).done(function(data){
				alert(data.state);
				if(data.state == 'success'){
					layer.msg("部门新增成功");
					//刷新ztree
					var treeObj = $.fn.zTree.getZTreeObj("ztree");
					treeObj.reAsyncChildNodes(null, "refresh");
				}
				
				
			}).error(function(){
				alert("系统异常，请稍后再试");
			})
		});
		
		
	});
	  
    var setting = {
			data: {
				simpleData: {
					enable: true
				},
				key :{
					name:"deptName", // 配置json name节点的属性名称
				}
				
			},
			async :{
				enable:true,
				url:'/dept/list',
				type:'get',
				dataFilter : ajaxDataFilter,
			},
			callback:{
				onClick:function(event,treeId,treeNode,clickFlag){
					deptId = treeNode.id;
					if(deptId == 1) {
						deptId = null;
					}
					// 当用户点击了部门节点后，重新加载分页插件，一旦分页插件重新加在后，那么就会执行	
					pageload(deptId);
				}
			}
		};
    
    function ajaxDataFilter(treeId, parentNode, responseData) {
        if (responseData) {
          for(var i =0; i < responseData.length; i++) {
				if(responseData[i].id == 1) {
					responseData[i].open = true;
				}
          }
        }
        return responseData;
    };
	
	$.fn.zTree.init($("#ztree"), setting);
  });
</script>
</body>
</html>
    