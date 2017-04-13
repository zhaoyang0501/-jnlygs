<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML>
<html>
<head>
</head>
<body >
    <div class="wrapper wrapper-content animated fadeInRight">
          <div class="row">
           		 <div class="col-sm-12">
        			 
        			 <div class="ibox float-e-margins">
	                    <div class="ibox-title">
		                        <h5>修改密码 </h5>
	                     </div>
                   	 <div class="ibox-content">
                        <form id='_form' role="form" class="form-horizontal" action="${pageContext.request.contextPath}/center/password" method="post">
                            <div class="form-group">
                                
                                <label class="col-sm-2 control-label">旧密码</label>
                                <div class="col-sm-10">
                                    <input name='pw' type="password" class="form-control">
                                </div>
                            </div>
                               
                            <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                
                                <label class="col-sm-2 control-label">新密码</label>
                                <div class="col-sm-10">
                                    <input name='pw1' type="password" class="form-control">
                                </div>
                            </div>
                                 
                            <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">新密码确认</label>
                                <div class="col-sm-10">
                                    <input name='pw2' type="password" class="form-control">
                                </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2 ">
                                      <button id='form_submit_btn'  class="btn btn-primary" type="submit">确定修改</button>
                            </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
           	</div>
   </div>
</div>              
  
   <!-- end choose -->   
   <script>
   var form_ = $("#_form").validate({
		rules : {
			
			pw: "required",
			pw1: "required",
		      pw2: {
		        required: true,
		        equalTo:"input[name='pw1']"
		      }
		},
		messages : {
			pw : "旧密码必须填写",
			pw1 : "新密码必须填写",
			pw2: {
		        required: "密码确认必须填写",
		        equalTo:"两次输入不一致"
		      }
		}
	});
   
    </script>
</body>

</html>
