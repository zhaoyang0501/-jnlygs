<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的生活</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
 	<style type="text/css">
 	body{
 	font-family: "Microsoft YaHei","WenQuanYi Micro Hei","Helvetica Neue",Arial,sans-serif;
 	background-color:white !important; 
 	}
		.news_item{
			
			background-image: url("") !important;
		}
		.item-text{
		padding-top: 20px;
		}
		
		.item-text span{
		margin-right: 20px;
		}
	</style>
  </head>
  <body>
  
   <div class="page-group">
    
    <div id="page-nav-bar" class="page page-fixed-tab">
  
	     <%@include file="./web/head.jsp" %>
	
	  
	  
	  <div class="content">
		
	  
	   <div class="content-padded">
		     <h3> ${news.title }</h3>
		     <div style="    font-size: 11px;">${news.createDate }发表</div>
		  	 <div> ${news.body }</div>
		     <h2 style="    border-bottom: 1px solid #0894ec;" >评论：</h2>
	            
	            <c:forEach items="${news.comments }" var="bean">
	           			 <div style="padding-bottom: 20px; padding-top:20px;   border-bottom-color: #e7eaec; border-bottom-style: solid;    border-bottom-width: 1px;">
								<div style="height: 40px;">
								<a  style="float: left;" class="btn" data-position="comment"><span><img style="height: 32px;width: 32px;" src="http://p3.pstatp.com/thumb/12391/1243072150"></span></a>
								<div style="margin-left: 50px;color:     color: #999;font-size: 14px;">
								<span>${bean.user.username } </span><a style="float: right;" href="javascript:void(0);"  onclick="fun_follower('${bean.user.id }')" >关注</a></div>
							</div>
							<div>
								 <span style="margin-left: 50px">${bean.body }</span>
							</div>
						</div>
	            </c:forEach>
	           			
						
				<form action="/web/comment">
				<input name='aid' type="hidden" value="${news.id }"/>
				 <textarea name='body' placeholder="说点啥呢" style="width: 100%;"></textarea>
				 
				     <p><button type="submit" class="button">提交评论</button></p>		
						
				</form>		
			
			
			<h2 style="    border-bottom: 1px solid #0894ec;" >精彩推荐：</h2>
			
			
			<div class="list-block media-list">
		      <ul>
		      <c:forEach items="${newss }" var="bean" end="4">
			       <li>
			          <a href="${pageContext.request.contextPath}/newsdetail/${bean.id}" class="item-link item-content">
			            <div class="item-media"><img src="http://p1.pstatp.com/list/190x124/150c001460407bedf8b6" width="90"></div>
			            <div class="item-inner news_item">
			              <div class="">
			                <div class="item-title">${bean.title }</div>
			              </div>
			              <div class="item-text"> <span>${fn:length(bean.comments)}评论</span> <span class=''>
			              
			              <f:formatDate  pattern="MM/dd" value="${bean.createDate }"/>发布
			              </span></div>
			            </div>
			          </a>
			        </li>
		      </c:forEach>
			</ul>
			</div>
			
		</div> 
	  </div><!-- end content -->

	</div>
    </div>
    <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
   <script>
   
   
   $(function() {
  	 <c:if test="${tip!=null }">
	 	 $.toast("${tip}");
	  </c:if>
  	});
   
   
       function fun_follower(id){
    	   $.ajax({
    		   url: '/web/addfollower?id='+id,
    		   dataType: 'json',
    		   success: function(data){
    			   $.toast("关注成功！");
    		   }
    		 })
       }
    </script>
  </body>
</html>