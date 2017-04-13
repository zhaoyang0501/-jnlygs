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
                    <div class="ibox-content">
                       <div class="alert alert-warning">
                          	 提示:如果某分类下有文章，请不要删除该分类！
                        </div>
                        <form role="form" class="form-inline">
                              <button class="btn btn-primary" type="button" id='_new'>新增</button>
                        </form>
                    </div>
                    
                    <div class="ibox-content ">
                         <table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									<th>名称</th>
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
       <div class="ibox-content">
 		 <div class="row">
                            <div class="col-sm-12 b-r">
                         
		                           <form class="form-horizontal" action="" method="get">
		                           <input name='id' type="hidden"/>
		                           	<table class='table table-bordered'>
		                           		<thead>
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>新闻分类管理</h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			
		                           			<tr>
			                           			<td>名称</td>
			                           			<td> 
			                           				<input value="" type="text" name="name" class="form-control"/>
			                           			</td>
			                           		</tr>
			                           		
		                           			<tr>
		                           				<td>备注</td>
		                           				<td> 
		                           					<textarea name='remark' rows="3" class="form-control"></textarea>
		                           				</td>
		                           			</tr>
		                           			
		                           			
		                           			
		                           			<tr>
		                           				<td colspan="6"> 
		                           					 <div class="col-sm-4 col-sm-offset-2">
		                                  			  		<button class="btn btn-primary" type="button" onclick="submit_form()">提交</button>
		                               				 </div>
		                           				</td>
		                           			</tr>
		                           		</tbody>
		                           	</table>
		                           	</form>
                            </div>
                        </div>
                        </div>
   </div>
   <script>
    var table=null;
    
    function submit_form(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/news/category/save",
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
    
    function fun_delete(id){
    	
    	layer.confirm('确定删除当前记录？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/news/category/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	
    		 		     }else{
    		 		    	 toastr.error(msg.msg);
    		 		     }
    		 		    table.draw();
    		 		     layer.closeAll() ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.closeAll() ;
    		});
    	
    	
     }
    
    function fun_update(id){
     }
    
    $(document).ready(function(){
          $("select[name='type']").change(function(){
       		  $("#exam_items_table tbody").hide();
       		  $("#exam_items_table #"+$(this).val()).show();
          	}
          );
          
          $("#SINGLECHOICE input[type='radio']").click(function(){
        	  $("#SINGLECHOICE input[type='radio']").prop("checked",false);
        	  $(this).prop("checked",true);
          });
          
          $("#TRUEORFALSE input[type='radio']").click(function(){
        	  $("#SINGLECHOICE input[type='radio']").prop("checked",false);
        	  $(this).prop("checked",true);
          });
          
          
          
    	  $("#_new").click(function(){
        		$("input[name='name']").val("");
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
	                "url":  $.common.getContextPath() + "/news/category/list",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "name",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                {
				                    "render": function ( data, type, row ) {
				                        return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>";
				                    },
				                    "targets":1
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
		        	data.columnname = 'chinesename';
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>

</body>

</html>
