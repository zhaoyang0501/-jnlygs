<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Envor HTML5/CSS3 Template">
    <meta name="author" content="Suono Libero ( @rivathemes.com )">
    <link rel="shortcut icon" href="favicon.ico">

    <title>Envor HTML5/CSS3 Template</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/colorbox-skins/4/colorbox.css" type="text/css">
        <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
    

    <link href="${pageContext.request.contextPath}/css/header/h1.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/color1.css" rel="stylesheet" type="text/css" id="envor-site-color">
    <link href="${pageContext.request.contextPath}/css/rivathemes.css" rel="stylesheet" type="text/css">

    <!-- LayerSlider styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layerslider/css/layerslider.css" type="text/css">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="${pageContext.request.contextPath}/js/vendor/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    
    <style type="text/css">
    .active a{
    color: white;
    }
    </style>
  </head>

    <body>
  

    <!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
   
   	<%@include file="./header.jsp" %>
   
   <div class="envor-content" style="padding-top: 0px;">
      <section class="envor-desktop-breadscrubs">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="envor-desktop-breadscrubs-inner">
                <a href="index.html">首页</a><i class="fa fa-angle-double-right"></i>最新菜单
              </div>
            </div>
          </div>
        </div>
      </section>
      
 
 <section class="envor-section">
        <div class="container">
          <div class="row" style="margin-top: 0px;">
            
            <div class="col-lg-12">
              <p class="block-description">丰富的资讯，免费查询！</p>
              <p>加入小额贷款平台，您还享受专属VIP服务，更有优先资金安排、福利赠送等各种特权。</p>
              <div class="envor-sorting" id="portfolio-sorting">
               
                <div class="row" >
                   
                 
                  <div class="col-lg-12">
                  <form action="${pageContext.request.contextPath}/search" method="post">
                 	 <div class="input-group  pull-right " style="width: 300px">
					      <input name='title' type="text" class="form-control" placeholder="查找...">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="submit">查询!</button>
					      </span>
					    </div><!-- /input-group -->
					  </form>   
                  <table class="table table-hover">
					 
					 <thead>
					 	<tr>
					 		<th>发布人</th>
					 		<th>标题</th>
					 		<th>点击</th>
					 		<th>发布日期</th>
					 	</tr>
					 </thead>
					 <tbody>
					 
					 <c:forEach items="${publishs }" var="bean">
					 	<tr>
					 		<td>${bean.user.username }</td>
					 		<td><a href="${pageContext.request.contextPath}/viewpublish?id=${bean.id}" > ${bean.title }</a></td>
					 		<td>23</td>
					 		<td>
					 			<fmt:formatDate value="${bean.createDate}" pattern="yyyy/MM/dd"/>
					 		</td>
					 	</tr>
					 </c:forEach>
					 </tbody>
					</table>
              </div>
              </div>
            <!--

            Items To Sort end

            //-->
            </div>
            <!--

            pagination start

            //-->
            <div class="envor-pagination">
              <span class="page-numbers current">1</span>
              <a class="next page-numbers" href="">Next »</a>
            <!--

            pagination end

            //-->
            </div>
          </div>
        </div>
        </div>
      <!--

      Main Content start

      //-->
      </section>
 
 
    </div>
    
	<%@include file="./footer.jsp" %>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/vendor/jquery-1.11.0.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/vendor/core-1.0.5.js"></script>

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.colorbox-min.js"></script>
    <script src="${pageContext.request.contextPath}/js/preloadCssImages.jQuery_v5.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.stellar.min.js"></script>
    <!--
    * jQuery with jQuery Easing, and jQuery Transit JS
    //-->
    <script src="${pageContext.request.contextPath}/js/layerslider/jquery-easing-1.3.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layerslider/jquery-transit-modified.js" type="text/javascript"></script>
    <!--
    * LayerSlider from Kreatura Media with Transitions
    -->
    <script src="${pageContext.request.contextPath}/js/layerslider/layerslider.transitions.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layerslider/layerslider.kreaturamedia.jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.rivathemes.js"></script>
    <script type="text/javascript">
      $('document').ready(function() {
    	  $("#span_${category.id}").addClass("active");
          /*

          Home Page Layer Slider

          */
          $('#layerslider').layerSlider({
            skinsPath               : 'css/layerslider/skins/',
            skin : 'fullwidth',
            thumbnailNavigation : 'hover',
            hoverPrevNext : false,
            responsive : false,
            responsiveUnder : 1170,
            sublayerContainer : 1170
          });
          /*

          Latest Projects Slider
          
          */
          $('#latest-projects').rivaSlider({
            visible : 4,
            selector : 'envor-project'
          });
          /*

          Our Partners Slider

          */
          $('#our-partners').rivaCarousel({
            visible : 5,
            selector : 'envor-partner-logo',
            mobile_visible : 1
          });
          /*

          Footer News Slider

          */
          $('#footer-news').rivaSlider({
            visible : 1,
            selector : 'envor-post-preview'
          });
          /*

          Testimonials #1 Carousel

          */
          $('#clients-testimonials').rivaCarousel({
            visible : 1,
            selector : 'envor-testimonials-1',
            mobile_visible : 1
          });
      });
    </script>
    <script src="${pageContext.request.contextPath}/js/envor.js"></script>
    <script type="text/javascript">
      $('document').ready(function() {
          /*

          Preload Images

          */
          var imgs = new Array(), $imgs = $('img');
          for (var i = 0; i < $imgs.length; i++) {
            imgs[i] = new Image();
            imgs[i].src = $imgs.eq(i).attr('src');
          }
          Core.preloader.queue(imgs).preload(function() {
            var images = $('a').map(function() {
                    return $(this).attr('href');
            }).get();
            Core.preloader.queue(images).preload(function() {
                  $.preloadCssImages();
            })
          })
                  $('#envor-preload').hide();
      });
      /*

      Windows Phone 8 и Internet Explorer 10

      */
      if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
        var msViewportStyle = document.createElement("style")
        msViewportStyle.appendChild(
          document.createTextNode(
            "@-ms-viewport{width:auto!important}"
          )
        )
        document.getElementsByTagName("head")[0].appendChild(msViewportStyle)
      }
    </script>
  </body>
</html>