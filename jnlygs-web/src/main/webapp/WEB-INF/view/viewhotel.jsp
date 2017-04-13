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
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="css/animate.css" rel="stylesheet" type="text/css">
    <link href="css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
    <link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/colorbox-skins/4/colorbox.css" type="text/css">
        <link href="css/main.css" rel="stylesheet" type="text/css">
   
     <link href=" http://demo.htmleaf.com/1503/201503041714/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css">
    <link href=" http://demo.htmleaf.com/1503/201503041714/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css">
  
    <link href="css/header/h1.css" rel="stylesheet" type="text/css">
    <link href="css/responsive.css" rel="stylesheet" type="text/css">
    <link href="css/color1.css" rel="stylesheet" type="text/css" id="envor-site-color">
    <link href="css/rivathemes.css" rel="stylesheet" type="text/css">

    <!-- LayerSlider styles -->
    <link rel="stylesheet" href="css/layerslider/css/layerslider.css" type="text/css">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="js/vendor/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     <script src="http://demo.htmleaf.com/1503/201503041714/js/bootstrap-datetimepicker.js"></script>

    <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
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
                <a href="index.html">首页</a><i class="fa fa-angle-double-right"></i>酒店预订
              </div>
            </div>
          </div>
        </div>
      </section>
      
     <section class="envor-section envor-single-product">
        <div class="container">
         <c:if test="${tip!=null }">
      <div class="envor-msg envor-msg-info">
                <header>
                	  提示!
                  <i class="fa fa-times"></i>
                </header>
                <p>${tip }</p>
          </div>
      </c:if>
          <div class="row" style="margin-top: 0px;">
            <!--

            Product start

            //-->
            <div class="col-lg-12">
              <div class="row envor-margin-top-0" style="margin-top: 60px;">
                <!--

                Product images

                //-->
                <div class="col-lg-3">
                  <div id="envor-project-images" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                      <li data-target="#envor-project-images" data-slide-to="0" class=""></li>
                      <li data-target="#envor-project-images" data-slide-to="1" class="active"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                      <div class="item">
                        <a href="http://www.jiawubang.me/Uploads/Picture/2015/06/10/JWB.ESiVw38b02a95b33szDN.jpg_280_200.jpg" class="colorbox cboxElement"><img src="http://www.jiawubang.me/Uploads/Picture/2015/06/10/JWB.ESiVw38b02a95b33szDN.jpg_280_200.jpg" alt=""></a>
                        <div class="carousel-caption">
                          Laptop
                        </div>
                      </div>
                      <div class="item active">
                        <a href="http://www.jiawubang.me/Uploads/Picture/2015/06/10/JWB.VsOpd84711f2b12NmUpH.jpg_280_200.jpg" class="colorbox cboxElement"><img src="http://www.jiawubang.me/Uploads/Picture/2015/06/10/JWB.VsOpd84711f2b12NmUpH.jpg_280_200.jpg" alt=""></a>
                        <div class="carousel-caption">
                          Laptop
                        </div>
                      </div>
                    </div>
                    <!-- Controls -->
                    <a class="left carousel-control" href="#envor-project-images" data-slide="prev">
                      <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#envor-project-images" data-slide="next">
                      <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                  </div>
                </div>
                <!--

                Product description

                //-->
                <div class="col-lg-9">
                  <h3 style="margin-top: 0px;">${bean.name }</h3>
                  <p class="price">￥${bean.price }</p>
                    <form class="envor-f1" action="submithotelorder" method="post">
                    <input type="hidden"  name='pid' value='${bean.id }'>
                 <p>
                 	<label for="rt1-fax">房间类型</label>
                 	<select name='type' >
                 		<option value="标准间">标准间</option>
                 		<option  value="大床房">大床房</option>
                 		<option  value="儿童房">儿童房</option>
                 	</select>
                 </p>
              
                 <p><label for="rt1-fax">预定日期</label><input type="text"  id='orderDate' name='orderDate'></p>
               
                  <p><label for="rt1-fax">备注</label><input type="text"  name='remark'></p>
                  <p><label for="rt1-fax">电话</label><input type="text"  name='tel'></p>
                  <p><input type="submit" value="提交订单" class="envor-btn envor-btn-normal envor-btn-primary"></p>
                </form>
                   </div>
              </div>
              <div class="row envor-margin-top-0" style="margin-top: 60px;">
                <!--

                Product Details

                //-->
                <div class="col-lg-12">
                  <h3 class="h3-2" style="margin-top: 0px;">product details</h3>
                  <div>
                  ${bean.remark }
                  </div>
                </div>
              <!--

              Product end

              //-->
              </div>
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
        <script src="js/vendor/jquery-1.11.0.min.js"></script>

    <script src="js/vendor/core-1.0.5.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.mCustomScrollbar.min.js"></script>
    <script src="js/jquery.mousewheel.min.js"></script>
    <script src="js/jquery.colorbox-min.js"></script>
    <script src="js/preloadCssImages.jQuery_v5.js"></script>
    <script src="js/jquery.stellar.min.js"></script>
    <!--
    * jQuery with jQuery Easing, and jQuery Transit JS
    //-->
    <script src="js/layerslider/jquery-easing-1.3.js" type="text/javascript"></script>
    <script src="js/layerslider/jquery-transit-modified.js" type="text/javascript"></script>
    <!--
    * LayerSlider from Kreatura Media with Transitions
    -->
    <script src="js/layerslider/layerslider.transitions.js" type="text/javascript"></script>
    <script src="js/layerslider/layerslider.kreaturamedia.jquery.js" type="text/javascript"></script>
    <script src="js/jquery.rivathemes.js"></script>
    <script type="text/javascript">
    $('document').ready(function() {
        /*

        Sorting

        */
        $('#faq-sorting').rivaSorting({
          showAll : 1
        });
        /*

        Footer News Slider

        */
        $('#footer-news').rivaSlider({
          visible : 1,
          selector : 'envor-post-preview'
        });
    });

      $('document').ready(function() {
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
    <script src="js/envor.js"></script>
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