<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body >
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>美食管理 </h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    
                    <div class="ibox-content">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <label for="exampleInputEmail2" class="sr-only">标题</label>
                                <input type="text" placeholder="标题" id="_name" class="form-control">
                            </div>
                            <button class="btn btn-primary" type="button" id='_search'><i class="fa fa-search"></i>查询</button>
                        </form>
                    </div>
                    
                    <div class="ibox-content ">
                         <table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									
									<th>标题</th>
									<th>发表</th>
									<th>操作</th>
								</tr>
                            </thead>
                       		 <tbody>
                            </tbody>
                          </table>
                    </div>
                    
                </div>
            </div>
        </div>
   </div>
   
     <div id='_form' style="display: none;">
     	 <div class="ibox-content ">
                         <table  class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									<th>内容</th>
									<th>发表人</th>
									<th>发表日期</th>
									<th>操作</th>
								</tr>
                            </thead>
                       		 <tbody id='table_comments'>
                       		 		<tr>
                       		 			<td></td>
                       		 			<td></td>
                       		 			<td></td>
                       		 			<td></td>
                       		 		</tr>
                            </tbody>
                          </table>
            </div>
   </div>
   <script>
    var table=null;
    
    function submit_form(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/sys/user/create",
    		   data: $("form").serialize(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 toastr.success('操作成功');
    		    	 table.draw();
    		     }
    		     layer.closeAll() ;
    		   }
    		});
     }
    
    function fun_view(url){
    	layer.open({
    		  type: 2,
    		  title: '新闻详情',
    		  shadeClose: true,
    		  area: ['800px', '90%'],
    		  content: url
    		}); 
    }
    function fun_delete(id){
    	layer.confirm('确定删除当前新闻？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/news/news/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	 table.draw();
    		 		     }
    		 		     layer.closeAll() ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.closeAll() ;
    		});
     }
    
    var comment_layer_index;
    var curid;
    function fun_comment(id){
    	curid=id;
    	fun_initcomments();
    	comment_layer_index= layer.open({
			  type: 1,
			  skin: 'layui-layer-rim', 
			  content: $("#_form"),
			  area: "800px"
			});
     }
    function fun_initcomments(id){
    	$.ajax({
   		   url:  $.common.getContextPath() + "/news/news/comments/"+curid,
   		   success: function(msg){
   		     if(msg.code==1){
   		    	$("#table_comments").empty();
   		    	for(var i=0;i<msg.datas.length;i++){
   		    		$("#table_comments").append("<tr>"+
 						   		 			"<td>"+msg.datas[i].body+"</td>"+
 						   		 			"<td>"+msg.datas[i].user.username+"("+msg.datas[i].user.chinesename+")</td>"+
 						   		 		    "<td>"+msg.datas[i].createDate+"</td>"+
 						   		 	    "<td><a tager='_blank' href='javascript:void(0)' onclick='fun_deletecomment("+msg.datas[i].id+")'>删除 </a></td>"+
 						   		 		"</tr>");
   		    	}
   		     }
   		   }
    	});
    	 table.draw();
    }
    
    
    function fun_deletecomment(id){
    	var index = layer.confirm('确定删除当前评论？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/news/news/deleteComments/"+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	fun_initcomments(id);
    		 		     }else{
    		 		    	 toastr.warning(msg.msg);
    		 		     }
    		 			 layer.close(index) ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.close(index) ;
    		});
     }
    
    
    $(document).ready(function(){
        	$("#_new").click(function(){
        		$("input[name='id']").val("");
 		    	$("input[name='chinesename']").val("");
 		    	$("radio[name='sex']").val("");
 		   		$("input[name='username']").val("");
 				$("input[name='tel']").val("");
 				$("input[name='email']").val("");
 				$("textarea[name='remark']").val("");
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        	});
        	table=$('#dt_table_view').DataTable( {
        		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
	            "ajax": {
	                "url":  $.common.getContextPath() + "/news/news/list",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "title"
				},{
					"data" : "createDate",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                {
				                    "render": function ( data, type, row ) {
				                   	 return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
				                     "<a tager='_blank' href='${pageContext.request.contextPath}/news/news/update/"+data+"'>编辑 </a>";
				                       
				                    },
				                    "targets":2
				                }
								
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
		        	data.value = $("#_name").val();
		        	data.columnname = 'title';
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
    
</body>

</html>
