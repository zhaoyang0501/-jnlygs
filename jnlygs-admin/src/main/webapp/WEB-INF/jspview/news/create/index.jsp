<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/simditor-2.3.6/styles/simditor.css" />
</head>
<body >
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                 
      		 	<div class="ibox-content">
 		 			<div class="row">
                            <div class="col-sm-12 b-r">
		                           <form class="form-horizontal" action="${pageContext.request.contextPath}/news/create/create" method="post">
		                           <input name='id' type="hidden" value="${news.id }"/>
		                           	<table class='table table-bordered'>
		                           		<thead>
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>发布<h3></h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			<tr>
		                           				<td>标题</td>
		                           				<td> <input name='title' value="${news.title }" type="text" class="form-control"></td>
		                           			</tr>
		                           			
					                           			
											<tr>
												<td>内容</td>
		                           				<td> <textarea  id='body' name='body' rows="4" cols="" style="width: 80%">${news.body }</textarea></td>
		                           			</tr>
		                           			
		                           			
		                           			
		                           			<tr>
		                           				<td>提示</td>
		                           				<td > 
		                           					 <h4>提示</h4>
		                               					 <ol>
									    					<li>文章长度不能超过10000个字长度</li>
									    				</ol>
		                           				</td>
		                           			</tr>
		                           			<tr>
		                           				<td colspan="6"> 
		                           					 <div class="col-sm-4 col-sm-offset-2">
		                                  			  		<button  class="btn btn-primary" type="submit" >提交</button>
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
            </div>
        </div>
   </div>
   
   
   <script>
    $(document).ready(function(){
    	var editor=new Simditor({
    		textarea:$("#body"),
    		 upload : {
    	            url : '${pageContext.request.contextPath}/simditorupload', //文件上传的接口地址
    	            fileKey: 'file', //服务器端获取文件数据的参数名
    	            leaveConfirm: '正在上传文件'
    	        } 
    		});
    	
    	var uploader1 = new WebUploader.Uploader(
				{
					auto : true,
					fileNumLimit : 1,
					server : '${pageContext.request.contextPath}/book/bookimgupload/upload',
					pick : {
						id : "#picker1",
						multiple : false
					},
					resize : false
				});

		uploader1.on('fileQueued',function(file) {
							var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
									+ '<img>'
									+ '<div class="info">'
									+ file.name
									+ '</div>'
									+ '<p class="state">等待上传...</p>'
									+ ' <input type="hidden" name="img" value=""/>'
									+ '</div>'), $img = $li.find('img');
							$("#thelist1").append($li);

							uploader1.makeThumb(file, function(error, src) {
								if (error) {
									$img.replaceWith('<span>不能预览</span>');
									return;
								}

								$img.attr('src', src);
							}, 190, 100);
						});

		uploader1.on('uploadSuccess', function(file, data) {
			if (data.code != 1) {
				$('#' + file.id).find('p.state').text(data.msg);
			} else {
				$('#' + file.id).find('p.state').text('已上传');
				$('#' + file.id).find("input").val(data.datas.filepath);
			}

			return false;
		});

		uploader1.on('uploadError', function(file) {
			$('#' + file.id).find('p.state').text('上传出错');
		});
		uploader1.on('beforeFileQueued', function(file) {
			$("#thelist1").empty();
			uploader1.reset();
		});

		uploader1.on('uploadComplete', function(file) {
			$('#' + file.id).find('.progress').fadeOut();
		});

	 	$(".webuploader-pick").addClass("btn").addClass("btn-success").removeClass("webuploader-pick");

   });
    </script>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/simditor-2.3.6/scripts/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/simditor-2.3.6/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/simditor-2.3.6/scripts/uploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/simditor-2.3.6/scripts/simditor.js"></script>
</body>
</html>
