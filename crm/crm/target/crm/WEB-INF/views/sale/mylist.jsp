<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-工作记录页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp"%>
   	<jsp:include page="../include/sider.jsp">
   		<jsp:param value="work_record_my" name="param"/>
   	</jsp:include>
   
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <button type="button" id="addChance" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 添加机会
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <td>机会名称</td>
                                <td>关联客户</td>
                                <td>机会价值</td>
                                <td>当前进度</td>
                                <td>最后跟进时间</td>
                                
                            </tr>
                            <c:forEach items="${page.items}" var="chance">
		                       	 <tr class="datarow" rel="${chance.id}">
			                       	<td>${chance.name}</td>
	                                <td>${chance.customer.custName}</td>
	                                <td><fmt:formatNumber value="${chance.worth}"></fmt:formatNumber></td>
	                                <td>${chance.process}</td>
	                                <td><fmt:formatDate value="${chance.lastTime}"  pattern='yyyy年MM月dd日'></fmt:formatDate></td>
                                 </tr>
                                 
                            </c:forEach>
                        </thead>
                    </table>
                    <ul class="pagination pagination-lg pull-right" id="pagination"></ul>
                </div>
                <!-- /.box-body -->
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

 <%@ include file="../include/js.jsp"%>
 <script>
	$(function(){
		
		 /*    var $pagination = $('#pagination');
		    var defaultOpts = {
		        totalPages: 20,
		    };
		    $pagination.twbsPagination(defaultOpts);
		    pageload();
		    function pageload(){
			   $.ajax({
			        url:"/sale/my/list.json",
			        type:"get",
			        data:{
			        },
			        success: function (data) {
			        	alert(data.data.items.length);
			        	var page = data.data;
			            var totalPages = page.totalpage;   //
			            var currentPage = $pagination.twbsPagination('getCurrentPage');
			            // currentPage不能比totalPages大，因此使用三元表达式选择其中较小值
			            currentPage = (currentPage > totalPages ? totalPages : currentPage);
			            $pagination.twbsPagination('destroy');
			            $pagination.twbsPagination($.extend({}, defaultOpts, {
			                startPage: currentPage,
			                totalPages: totalPages,
			                visiblePages:3,
			    		    first:'首页',
			    		    last:'末页',
			    		    prev:'上一页',
			    		    next:'下一页',
			    		    onPageClick: function(event, pageNo) {
			    		    	// 一旦分页插件被重新加载，分页插件就会自动执行第一页数据
			    		    	alert("执行load");
			    		    	load(pageNo);
			    	        }
			            }));
			        }
			    });
		   }
		   
		  // 加载table数据
		  function load(p) {
			  $.ajax({
			        url:"/sale/my/list.json",
			        type:"get",
			        data:{
			        	"p":p
			        },
			        success: function (data) {
			        	var page = data.data;
			        	$("#body").html("");
			            for(var i = 0; i < page.items.length; i++) {
			            	var chance = page.items[i];
			            	var html = "<tr> <td>" + chance.name+"</td> <td>"
			            	+chance.customer.custName+"</td> <td>"
			            	+chance.worth+"</td> <td>"+chance.process+"</td> <td>"
			            	+chance.lastTime+"</td> <td></tr>";
			            	
			            	console.log($(html)[0]);
			            	//$("#body").append($(html));
			            	$(html).appendTo($("#body"));
			            	$("#body").find("tr").addClass("aa");
			            }
			        }
			    });
		  } */
		//点击分页页码
 		 $("#pagination").twbsPagination({
 	         totalPages:"${page.totalpage}",
 	         visiblePages:3,
 	         href:"/sale/my/list?p={{number}}",
 	         first: "首页",
 	         prev: "上一页",
 	         next:"下一页",
 	         last:"末页"
 	     });
		
		$("#addChance").click(function(){
			window.location.href="/sale/add";
		});
		
		$(".datarow").click(function(){
			var id = $(this).attr("rel");
			window.location.href="/sale/detail?saleId="+id;
		});
		
	}) 
 </script>
 	
 
</body>
</html>
    