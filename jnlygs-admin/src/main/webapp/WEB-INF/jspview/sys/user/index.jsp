<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/plugins/jsTree/style.min.css" rel="stylesheet">
     <link href="${pageContext.request.contextPath}/plugins/dropdown/dist/css/dropdowns-enhancement.min.css" rel="stylesheet">
<style type="text/css">
.webuploader-container{
display: inline;
}
.error{
color: red;
}
.dropdown-menu>li>label {
    margin: 4px;
    line-height: 25px;
}
</style>
</head>
<body >
   <input id='deptid' type="hidden"/>
		                      
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>注册用户管理 </h5>
                    </div>
                   
                    
                    <div class="ibox-content ">
                     <div class="row">
	                     <div class="col-sm-3">
	           				<div id="jstree_demo_div">
	           				</div>
	          			 </div>
          			  	
          			  	<div class="col-sm-9">
	                        <form role="form" class="form-inline">
	                        
	                        <div class="form-group">
	                           <div class="input-group ">
                                        <div class="input-group-btn btn-group" id="name_id_dropdown">
                                            <button data-toggle="dropdown" class="btn  btn-white dropdown-toggle">用户名 <span class="caret"></span></button>
                                            <ul class="dropdown-menu">
                                           <li> 
                                                 <li > <input type="radio" checked="checked" id="username" name="name" value="username"><label for="username">用户名</label></li>
                                                  <li > <input type="radio" id="chinesename" name="name" value="chinesename"><label for="chinesename">姓名</label></li>
                                                 
                                            </ul>
                                        </div>
                                        <input type="text" placeholder="工号或姓名" id="_name" class="form-control">
                                    </div>
							      </div>
							      
	                           
	                           
                           		 
	                            <button class="btn btn-primary" type="button" id='_search'><i class="fa fa-search"></i> 查询</button>
	                            <button class="btn btn-primary" type="button" id='_new'><i class=" glyphicon glyphicon-pencil "></i>新增</button>
	                        </form>
           				
           					<table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
	                            <thead>
	                                <tr>
										<th><input type="checkbox" onclick="fun_selectall()" id='select_all' /> 全选</th>
										<th>用户名</th>
										<th>工号</th>
										<th>真实姓名</th>
										<th>性别</th>
										<th>电话</th>
										<th>部门组织</th>
										<th>角色</th>
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
   
      
     <div id='_formbathdelete' style="display: none;">
       <div class="ibox-content">
 		 <div class="row">
                            <div class="col-sm-12 b-r">
                            	<div class="alert alert-success">
                           		 	提示：<br/>
                           		 	1.下载模板，根据模板格式准备数据，支持xls、xlsx格式<a href="${pageContext.request.contextPath}/template/deleteusers.xlsx">点此下载</a> 
                           		 	<br>2.点击上传按钮上传xls。
                           		 	<br>3.excel中的用户将会被全部删除。
                      		 	 </div>
                      		 	 <div class="pull-right">
                      		 		<input type="hidden" id='filepath_delete'/>
                           		 	     	<div id="picker4_delete" ><i class="fa fa-upload"></i>选择文件</div>
                        					<button id='ok_btn_delete' onclick="fun_save_users_delete()" class="btn btn-primary " type="button"><i class="fa fa-check"></i>&nbsp;确认</button>
                      		 	 </div>
                      		 	 
                      		 	 <div>
                      			 预览：<span class='state'>暂时没有文件上传</span>
                      		 	
                          </div>
                          
                            </div>
         </div>
     </div>
     </div>
     
     <div id='_formexport' style="display: none;">
       <div class="ibox-content">
 		 <div class="row">
                            <div class="col-sm-12 b-r">
                            	<div class="alert alert-success">
                           		 	提示：<br/>
                           		 	1.下载模板，根据模板格式准备数据，支持xls、xlsx格式<a href="${pageContext.request.contextPath}/template/用户模板.xlsx">点此下载</a> 
                           		 	<br>2.点击上传按钮上传xls。<br>3.查看文件校验结果，确认无误点击确认。
                           		 	<br>4.如果系统中存在该用户、将会被忽略。
                      		 	 </div>
                      		 	 <div class="pull-right">
                      		 		<input type="hidden" id='filepath'/>
                           		 	     	<div id="picker4" ><i class="fa fa-upload"></i>选择文件</div>
                        					<button id='ok_btn' onclick="fun_save_users()" class="btn btn-primary " type="button"><i class="fa fa-check"></i>&nbsp;确认</button>
                      		 	 </div>
                      		<div>
                      		 预览：<span class='state'>暂时没有文件上传</span>
                      		 	  <table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									<th>部门组织</th>
									<th>用户名</th>
									<th>工号</th>
									<th>真实姓名</th>
									<th>性别</th>
									<th>校验结果</th>
								</tr>
                            </thead>
                       		 <tbody id='prev_tbody'>
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
		                           <form id='form' class="form-horizontal" action="">
		                           <input name='id' type="hidden"/>
		                           	<table class='table table-bordered'>
		                           		<thead>
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>注册用户信息<h3></h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			<tr>
		                           				<td>姓名</td>
		                           				<td> <input name='chinesename' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           			<td>性别</td>
		                           				<td>
												  <label class='checkbox-inline'>
												    <input type="radio" name="sex"  value="男" checked>
												    	男
												   </label>
												   <label class='checkbox-inline'>
												    <input type="radio" name="sex"  value="女">
												 	   女
												  </label>
		                           				</td>
		                           			</tr>
		                           			
		                           				
		                           			<tr>
		                           				<td>用户名</td>
		                           				<td> <input name='username' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           				<td>电话</td>
		                           				<td> <input name='tel' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			
		                           			<tr>
		                           				<td>公司</td>
		                           				<td>
												<select name='deptment.id' class=" form-control">
												  	<c:forEach var="bean" items="${deptmentselects}">
												  		<option value="${bean.id }">${bean.text }</option>
												  	</c:forEach>
												  </select>
		                           				</td>
		                           			</tr>
		                           			
											<tr>
												<td>备注</td>
		                           				<td> <textarea name='remark' rows="4" cols="" style="width: 80%"></textarea></td>
		                           			</tr>
		                           			
		                           			
		                           			<tr>
		                           				<td>角色</td>
		                           				<td> 
		                           				<c:forEach items="${roles }" var="bean">
		                           				  <label class='checkbox-inline'>
														<input type="checkbox" name="role" value="${bean.id }"> ${bean.name }
												</label>
		                           				</c:forEach>
												</td>
		                           			</tr>
		                           			
		                           		 
		                           			<tr>
		                           				<td>提示</td>
		                           				<td > 
		                           					 <h4>提示</h4>
		                               					 <ol>
									    					<li>初始密码为123456</li>
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
    
    /**上传组件*/
    var uploader=null;
    var uploader2=null;
    
    function fun_selectall(){
    	var flag=$("#select_all").is(":checked");
    	$("input[name=ck_userids]").each(function(){ 
    		 $(this).prop("checked",flag);
    	})
    }
    
    function fun_exportall(){
    	 toastr.warning('服务器正在处理导出请求，如果数据量比较大，请耐心等待.....');
    	 var value = $("#_name").val();
    	 var deptid = $("#deptid").val();
    	 var freeze = $("input[name='isFreeze']:checked").val();
    	 var party = $("input[name='_party']:checked").val();
    	 var url= $.common.getContextPath() + "/sys/user/exportall?value="+value+"&deptid="+deptid+"&isFreeze="+freeze;
    	 if(party!= null )
    		 url+="&party="+party;
    	// window.open(url);
    	 window.location.href=url;
    	 toastr.clear();
    }
     
    function get_select_userids(){
    	var userid = $("input[name=ck_userids]:checked");
    	var id ="";
    	for(i=0;i<userid.size();i++){
    		if(i==(userid.size()-1))
    			id=id+userid.eq(i).val();
    		else
    			id=id+userid.eq(i).val()+",";
    	}
    	return id;
    }
    function fun_init_upload(){
    	
    	
    	 uploader2 =new  WebUploader.Uploader({
     		auto:true,
     		fileNumLimit :1,
     	    server: '${pageContext.request.contextPath}/sys/usersDelete/upload',
     	    pick:{id : "#picker4_delete",multiple: false} ,
     	    resize: false
     	});
     	
    	 uploader2.on( 'fileQueued', function( file ) {
     		    $(".state").html( file.name+ "上传中...." );
     	});
     	
    	 uploader2.on( 'uploadSuccess',  function(file, data){
     		if(data.code==1){
     			$(".state").html( "上传成功!" );
     				isPass=true;
        		 	$('#filepath_delete').val(data.datas.filepath);
     		}else{
     			 $(".state").html( "上传失败!"+data.msg );
     		}
     		 return false;
 		});

    	 uploader2.on( 'uploadError', function( file ) {
     		 $(".state").html( "上传出错!" );
     	});
    	 uploader2.on( 'beforeFileQueued', function( file ) {
     		$(".state").empty();
     		uploader2.reset();
     	});

    	 uploader2.on( 'uploadComplete', function( file ) {
     	  
     	});
     	
    	
    	
    	 uploader =new  WebUploader.Uploader({
    		auto:true,
    		fileNumLimit :1,
    	    server: '${pageContext.request.contextPath}/sys/usersUpload/upload',
    	    pick:{id : "#picker4",multiple: false} ,
    	    resize: false
    	});
    	
    	uploader.on( 'fileQueued', function( file ) {
    		    $(".state").html( file.name+ "上传中...." );
    	});
    	
    	uploader.on( 'uploadSuccess',  function(file, data){
    		if(data.code==1){
    			$(".state").html( "上传成功，正在校验文件请稍后!" );
       		 	$('#filepath').val(data.datas.filepath);
       			fun_preview_excel(data.datas.filepath);
    		}else{
    			 $(".state").html( "上传失败!"+data.msg );
    		}
    		 return false;
		});

    	uploader.on( 'uploadError', function( file ) {
    		 $(".state").html( "上传出错!" );
    	});
    	uploader.on( 'beforeFileQueued', function( file ) {
    		$(".state").empty();
    		uploader.reset();
    	});

    	uploader.on( 'uploadComplete', function( file ) {
    	  
    	});
    	
    	$(".webuploader-pick").addClass("btn").addClass("btn-success").removeClass("webuploader-pick");
    }
   	
    var isPass=false;
    /*生成预览*/
    function fun_preview_excel(file){
    	$.ajax({
 		   type: "POST",
 		   url:  $.common.getContextPath() + "/sys/user/converUsersFromExcel?filepath="+file,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	isPass=true;
 		    	$(".state").html("文件解析成功！");
 		    	 $("#prev_tbody").empty();
 		    	 for( i=0;i<msg.datas.length;i++){
 		    		 if(msg.datas[i].remark!=null) 
 		    			isPass=false;
 		    		$("#prev_tbody").append("<tr>"+
 		    							"<td>"+(msg.datas[i].deptment==null?"":msg.datas[i].deptment.name)+"</td>"+
										"<td>"+msg.datas[i].username+"</td>"+
										"<td>"+msg.datas[i].empid+"</td>"+
										"<td>"+msg.datas[i].chinesename+"</td>"+
										"<td>"+msg.datas[i].sex+"</td>"+
										"<td class='error'>"+(msg.datas[i].remark==null?"":msg.datas[i].remark)+"</td>"+
										"</tr>"); 
 		    	 }
 		     }else{
 		    	$(".state").html(msg.msg);
 		     }
 		   }
 		});
    }
    
 /***  点击确认按钮保存excel***/
    
 function fun_save_users_delete(){
    	if($('#filepath_delete').val()==""){
    		 toastr.warning('没有选择excel文件！');
    		 return;
    	}
    	if(isPass){
    		$("#ok_btn_delete").attr("disabled",true);
    		$(".state").html("正在处理中...");
    		$.ajax({
    	  		   type: "POST",
    	  		   url:  $.common.getContextPath() + "/sys/user/deleteUsersFromExcel?filepath="+$('#filepath_delete').val(),
    	  		   success: function(msg){
    	  		     if(msg.code==1){
    	  		    	$("#ok_btn_delete").removeAttr("disabled");
    	  		    	$(".state").html("");
    	  		    	$('#filepath_delete').val("");
    	  		    	toastr.success(msg.msg);
    	  		    	table.draw();
		 		    	layer.closeAll() ;
    	  		     }
    	  		   }
    	  		});
    	}else{
    		 toastr.warning('文件校验不通过！');
    	}
    	
    }
 
    function fun_save_users(){
    	if($('#filepath').val()==""){
    		 toastr.warning('没有选择excel文件！');
    		 return;
    	}
    	if(isPass){
    		$("#ok_btn").attr("disabled",true);
    		$(".state").html("正在处理中...");
    		$.ajax({
    	  		   type: "POST",
    	  		   url:  $.common.getContextPath() + "/sys/user/saveUsersFromExcel?filepath="+$('#filepath').val(),
    	  		   success: function(msg){
    	  		     if(msg.code==1){
    	  		    	$("#ok_btn").removeAttr("disabled");
    	  		    	$(".state").html("");
    	  		    	$("#prev_tbody").empty();
    	  		    	$('#filepath').val("");
    	  		    	toastr.success(msg.msg);
    	  		    	table.draw();
		 		    	layer.closeAll() ;
    	  		     }
    	  		   }
    	  		});
    	}else{
    		 toastr.warning('文件校验不通过！');
    	}
    	
    }
    var form_ = $("#form").validate({
		rules : {
			chinesename : "required",
			username : "required",
			empid : "required",
			
		},
		ignore : "",
		messages : {
			chinesename : "姓名必须填写",
			username : "用户名必须填写",
			empid : "工号必须填写",
		}
	});
    
    function submit_form(){
    	if (!form_.form())
			return;
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/sys/user/saveuser",
    		   data: $("form").serialize(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 layer.closeAll() ;
    		    	 toastr.success('操作成功');
    		    	 table.draw();
    		     }else{
    		    	 toastr.warning(msg.msg);
    		     }
    		     
    		   }
    		});
     }
    
    function fun_delete(id){
    	layer.confirm('确定删除当前用户？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/delete?id="+id,
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
    
    function fun_resetpw(id){
    	layer.confirm('确定重置密码？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/resetpw?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('密码重置为123456，请及时修改密码！');
    		 		    	 table.draw();
    		 		     }
    		 		     layer.closeAll() ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.closeAll() ;
    		});
     }
    
    function fun_freeze(id){
    	layer.confirm('确定冻结当前用户，冻结后的用户将无法登陆？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/freeze?id="+id,
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
    function fun_unfreeze(id){
    	layer.confirm('确定解冻当前用户？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/unfreeze?id="+id,
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
    function fun_update(id){
    	$.ajax({
 		   url:  $.common.getContextPath() + "/sys/user/getuser?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='chinesename']").val(msg.datas.chinesename);
 		    	$("radio[name='sex']").val(msg.datas.sex);
 		    	$("radio[name='gag']").val(msg.datas.gag);
 		   		$("input[name='username']").val(msg.datas.username);
 				$("input[name='tel']").val(msg.datas.tel);
 				$("input[name='empid']").val(msg.datas.empid);
 				$("input[name='email']").val(msg.datas.email);
 				$("textarea[name='remark']").val(msg.datas.remark);
 				$("input:checkbox[name='role']").prop('checked',false); 
 				for(var i=0;i<msg.datas.roles.length;i++){
 					$("input:checkbox[value='"+msg.datas.roles[i].id+"']").prop('checked',true); 
 				}
 				
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
    
    $(document).ready(function(){
    	
    	//$("#name_id_dropdown").dropdown();
    	$('#name_id_dropdown').on('show.bs.dropdown', function (event) {
    		console.info(event.target);
    		alert($(event.target ).html() );
    		})
    	$.ajax({
	 		   url:  $.common.getContextPath() + "/sys/user/alldeptments",
	 		   success: function(msg){
	 			  $('#jstree_demo_div').jstree({
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
    	
    	
    		$("#_delete").click(function(){
    			
    			layer.confirm('确定批量冻结选中用户？', {
    	    		  btn: ['确定','取消'] //按钮
    	    		}, function(){
    	    			$.ajax({
    	    		 		   url:  $.common.getContextPath() + "/sys/user/freezeAll?ids="+get_select_userids(),
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
        	});
    	
	    	$("#_export").click(function(){
	      	  layer.open({
	      			  type: 1,
	      			  skin: 'layui-layer-rim', //加上边框
	      			  content: $("#_formexport"),
	      			  area: ["800px","600px"]
	      			});
	      	  if(uploader==null)
						fun_init_upload();
	      	});
    	
	    	$("#_bathdelete").click(function(){
		      	  layer.open({
		      			  type: 1,
		      			  skin: 'layui-layer-rim', //加上边框
		      			  content: $("#_formbathdelete"),
		      			  area: ["800px","600px"]
		      			});
		      	  if(uploader2==null)
							fun_init_upload();
		      	});
	    	
        	$("#_new").click(function(){
        		$("input[name='id']").val("");
 		    	$("input[name='chinesename']").val("");
 		    	$("radio[name='sex']").val("");
 		    	$("radio[name='gag']").val("");
 		    	$("radio[name='party']").val("");
 		    	$("input[name='empid']").val("");
 		   		$("input[name='username']").val("");
 				$("input[name='tel']").val("");
 				$("input[name='email']").val("");
 				$("textarea[name='remark']").val("");
 				$("input:checkbox[name='role']").prop('checked',false); 
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        	});
        	table=$('#dt_table_view').DataTable( {
        		
				"displayLength" : 20,
				"lengthMenu" : [ 20, 100, 200],
				"dom" : "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
	            "ajax": {
	                "url":  $.common.getContextPath() + "/sys/user/listall",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "id"
				}, {
					"data" : "username"
				}, {
					"data" : "empid"
				},{
					"data" : "chinesename",
				},{
					"data" : "sex",
				},{
					"data" : "tel",
				},{
					"data" : "deptment.name",
				},{
					"data" : "roles",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
							   {
					                render: function ( data, type, row ) {
					                    return "<input  name='ck_userids' class='ck_userids' type='checkbox' value="+data+" class='editor-active'>";
					                },
					                "targets":0
					            },
					           
								{
								    "render": function ( data, type, row ) {
								      var str="";
								    	for(i=0;i<data.length;i++){
								    	   str+= "<span class='label label-primary '>"+data[i].name+" </span> &nbsp;&nbsp;  ";
								       }
								       return str;
								    },
								    "targets":7
								}, 
								
				                {
				                    "render": function ( data, type, row ) {
				                    	if(row.isFreeze){
				                    		 return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 &nbsp;</a>"+
						                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_resetpw("+data+")'>密码重置&nbsp; </a>"+
						                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑  </a>";
				                    	}else{
				                   		 return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 &nbsp;</a>"+
					                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_resetpw("+data+")'>密码重置&nbsp;</a>"+
					                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
			                    
				                    	}
				                    
				                       
				                    },
				                    "targets":8
				                }
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
        			data.attr = $("input[name='name']:checked").val();
		        	data.value = $("#_name").val();
		        	data.deptid = $("#deptid").val();
		        	data.isFreeze = $("input[name='isFreeze']:checked").val();
		        	data.party = $("input[name='_party']:checked").val();
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
    <script src="${pageContext.request.contextPath}/js/plugins/jsTree/jstree.min.js"></script>
    	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/Buttons-1.2.2/js/dataTables.buttons.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/jszip.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/Buttons-1.2.2/js/buttons.html5.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/Buttons-1.2.2/js/buttons.print.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/dropdown/dist/js/dropdowns-enhancement.js"></script>
</body>
 
</html>
