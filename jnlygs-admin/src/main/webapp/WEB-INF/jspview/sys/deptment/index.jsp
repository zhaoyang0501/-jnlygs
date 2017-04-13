<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/plugins/jsTree/style.min.css" rel="stylesheet">
</head>
<body >
   <input id='deptid' type="hidden"/>
		                      
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>公司管理 </h5>
                    </div>
                   
                    
                    <div class="ibox-content ">
                     <div class="row">
	                     <div class="col-sm-4">
	           				<div id="jstree_demo_div">
	           				</div>
	          			 </div>
          			  	
          			  	<div class="col-sm-8">
	                        <form role="form" class="form-inline">
	                            <div class="form-group">
	                                <label for="exampleInputEmail2" class="sr-only">名称</label>
	                                <input type="text" placeholder="名称" id="_name" class="form-control">
	                            </div>
                            
	                            <button class="btn btn-primary" type="button" id='_search'>查询</button>
	                            <button class="btn btn-primary" type="button" id='_new'>新增</button>
	                        </form>
           				
           					<table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
	                            <thead>
	                                <tr>
										<th>公司名称</th>
										<th>编码</th>
										<th>电话</th>
										<th>负责人</th>
										<th>上级</th>
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
        </div>
   </div>
   
   <div id='_form' style="display: none;">
       <div class="ibox-content">
 		 <div class="row">
                            <div class="col-sm-12 b-r">
		                           <form id='form' class="form-horizontal" action="" method="get">
		                           <input name='id' type="hidden"/>
		                           	<table class='table table-bordered'>
		                           		<thead>
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>公司信息<h3></h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			<tr>
		                           				<td>公司名称</td>
		                           				<td> <input name='name' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           				
		                           			<tr>
		                           				<td>公司编码</td>
		                           				<td> <input name='code' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           				<td>电话</td>
		                           				<td> <input name='tel' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			
		                           			<tr>
		                           				<td>负责人</td>
		                           				<td> <input name='manger'  type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           				<td>上级公司</td>
		                           				<td>
												<select name='parent.id' class=" form-control">
												  	<c:forEach var="bean" items="${deptmentselects}">
												  		<option value="${bean.id }">${bean.text }</option>
												  	</c:forEach>
												  </select>
		                           				</td>
		                           			</tr>
		                           			
										
		                           			
		                           		
		                           			<tr>
		                           				<td>提示</td>
		                           				<td > 
		                           					 <h4>提示</h4>
		                               					 <ol>
									    					<li>上级公司不填默认为根公司，上级公司不能选自己</li>
									    				</ol>
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
    var tree;
    var form_ = $("#form").validate({
		rules : {
			name : "required"
		},
		ignore : "",
		messages : {
			name : "公司名称必须填写"
		}
	});
    function submit_form(){
    	if (!form_.form())
			return;
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/sys/deptment/save",
    		   data: $("form").serialize(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 toastr.success('操作成功');
    		    	 window.location.reload();
    		     }
    		     layer.closeAll() ;
    		   }
    		});
     }
    
    function fun_delete(id){
    	layer.confirm('确定删除当前公司？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/deptment/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	window.location.reload();
    		 		     }
    		 		     layer.closeAll() ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.closeAll() ;
    		});
     }
   
    function fun_update(id){
    	$.ajax({
 		   url:  $.common.getContextPath() + "/sys/deptment/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='name']").val(msg.datas.name);
 		    	$("radio[name='tel']").val(msg.datas.tel);
 		   		$("input[name='manger']").val(msg.datas.manger);
 				$("input[name='tel']").val(msg.datas.tel);
 				$("input[name='code']").val(msg.datas.code);
 				$("select[name='parent.id']").val(msg.datas.parent.id);
 		    	layer.open({
      			  type: 1,
      			  skin: 'layui-layer-rim', 
      			  content: $("#_form"),
      			  area: "800px"
      			});
 		     }
 		   }
 		});
     }
    
    function init_dept_tree(){
    	$.ajax({
	 		   url:  $.common.getContextPath() + "/sys/user/alldeptments",
	 		   success: function(msg){
	 			  tree = $('#jstree_demo_div').jstree({
	 			 		'core' : {
	 			 			"multiple" : false,
	 			 		  'data' : msg
	 			 		}}).on('changed.jstree', function (e, data) {
	 			 			console.info(data.node.id);
	 			 			$("#deptid").val(data.node.id);
	 			 			 table.draw();
	 			 		  });
	 			   
	 		   }
	 	});
    }
   
    $(document).ready(function(){
    	init_dept_tree();
        	$("#_new").click(function(){
        		$("input[name='id']").val("");
 		    	$("input[name='name']").val("");
 		    	$("radio[name='code']").val("");
 		   		$("input[name='tel']").val("");
 				$("input[name='manger']").val("");
 				$("select[name='parent']").val("");
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        	});
        	table=$('#dt_table_view').DataTable( {
        		buttons : [ {
					extend : 'excel',
					className : 'btn btn-success',
					title : '公司列表',
					text : '导出',
					exportOptions : {
						columns : [ 0,1,2,3,4 ],
						modifier : {
							page : 'all'
						}
					}
				} ],
				"displayLength" : 20,
				"lengthMenu" : [ 20, 100, 200],
				"dom" : "lBrt<'row'<'col-sm-5'i><'col-sm-7'p>>",
	            "ajax": {
	                "url":  $.common.getContextPath() + "/sys/deptment/listall",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "name"
				}, {
					"data" : "code"
				},{
					"data" : "tel",
				},{
					"data" : "manger",
				},{
					"data" : "parent",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
								{
								    "render": function ( data, type, row ) {
								        if(row.parent!=null)
								        	return row.parent.name;
								        else return "";
								        	
								    },
								    "targets":4
								}, 
				                {
				                    "render": function ( data, type, row ) {
				                    	 return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
					                        	"<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
				                    },
				                    "targets":5
				                }
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
		        	data.name = $("#_name").val();
		        	data.deptid = $("#deptid").val();
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
    <script src="${pageContext.request.contextPath}/js/plugins/jsTree/jstree.min.js"></script><script src="${pageContext.request.contextPath}/js/plugins/dataTables/Buttons-1.2.2/js/dataTables.buttons.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/jszip.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/Buttons-1.2.2/js/buttons.html5.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/Buttons-1.2.2/js/buttons.print.js"></script>
</body>
 
</html>
