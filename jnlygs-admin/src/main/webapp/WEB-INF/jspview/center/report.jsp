<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
      
      
        
        
         
        
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox">
                    <div class="ibox-content">
                        <h5>本日活跃用户</h5>
                        <h2>198 009</h2>
                        <div id="sparkline1"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox">
                    <div class="ibox-content">
                        <h5>本周活跃用户</h5>
                        <h2>65 000</h2>
                        <div id="sparkline2"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox">
                    <div class="ibox-content">
                        <h5>本月活跃用户</h5>
                        <h2>680 900</h2>
                        <div id="sparkline3"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox">
                    <div class="ibox-content">
                        <h5>平均停留时间</h5>
                        <h2>00:06:40</h2>
                        <div id="sparkline4"></div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox">
                    <div class="ibox-content">
                        <h5>在线人数/总人数</h5>
                        <h2>42/20</h2>
                        <div class="text-center">
                            <div id="sparkline5"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
   
    <script src="${pageContext.request.contextPath}/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/peity/jquery.peity.min.js"></script>
    <script>

					$(document).ready(
							function() {
								$("body").addClass("gray-bg");
								$("#sparkline1").sparkline(
										[ 34, 43, 43, 35, 44, 32, 44, 52 ], {
											type : "line",
											width : "100%",
											height : "60",
											lineColor : "#1ab394",
											fillColor : "#ffffff"
										});
								$("#sparkline2").sparkline(
										[ 24, 43, 43, 55, 44, 62, 44, 72 ], {
											type : "line",
											width : "100%",
											height : "60",
											lineColor : "#1ab394",
											fillColor : "#ffffff"
										});
								$("#sparkline3").sparkline(
										[ 74, 43, 23, 55, 54, 32, 24, 12 ], {
											type : "line",
											width : "100%",
											height : "60",
											lineColor : "#ed5565",
											fillColor : "#ffffff"
										});
								$("#sparkline13").sparkline([ 1, 4 ], {
									type : "pie",
									height : "60",
									sliceColors : [ "#1ab394", "#F5F5F5" ]
								});
								$("#sparkline4").sparkline(
										[ 24, 43, 33, 55, 64, 72, 44, 22 ], {
											type : "line",
											width : "100%",
											height : "60",
											lineColor : "#ed5565",
											fillColor : "#ffffff"
										});
								$("#sparkline5").sparkline([ 1, 4 ], {
									type : "pie",
									height : "140",
									sliceColors : [ "#1ab394", "#F5F5F5" ]
								});
								$("#sparkline6").sparkline([ 5, 3 ], {
									type : "pie",
									height : "140",
									sliceColors : [ "#1ab394", "#F5F5F5" ]
								});
								$("#sparkline7").sparkline([ 2, 2 ], {
									type : "pie",
									height : "140",
									sliceColors : [ "#ed5565", "#F5F5F5" ]
								});
								$("#sparkline8").sparkline([ 2, 3 ], {
									type : "pie",
									height : "140",
									sliceColors : [ "#ed5565", "#F5F5F5" ]
								})
							});
				</script>
</body>

</html>
