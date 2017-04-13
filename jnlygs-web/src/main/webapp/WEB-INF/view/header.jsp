<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <!--

    Scroll to the top

    //-->
    <div id="to-the-top"><i class="fa fa-chevron-up"></i></div>
    <!--

    Image Preload

    //-->
    <div id="envor-preload">
      <span>正在为您加载.<br>请稍后.</span>
      <i class="fa fa-cog fa-spin"></i>
      <p></p>
    </div>
    <header class="envor-header envor-header-1">
      <div class="envor-top-bar">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <!--

              Contact information start

              //-->
              <p class="contacts"><i class="fa fa-phone"></i> 001-888888 </p>
              <p class="contacts"><i class="fa fa-envelope"></i> <a href="">263608237@qq.com</a></p>
              <ul class="social-btns">
                  <c:if test="${sessionScope.user!=null}">
                    <li style=" width: 250px;"><a href="center">欢迎您：${sessionScope.user.name }</a></li>
               	 	<li><a href="loginout"><span class=" glyphicon glyphicon-off" aria-hidden="true"></span></a></li>
                  </c:if>
                <c:if test="${sessionScope.user==null}">
                    <li style=" width: 250px;"><a href="login">请登录</a></li>
                  </c:if>
              </ul>
            </div>
          </div>
        </div>
      <!--

      Top bar end

      //-->
      </div>
      <!--

      Logo & Menu start

      //-->
      <div class="envor-header-bg" id="envor-header-menu">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="envor-relative">
                <!--

                Site Logo start

                //-->
                <a href="${pageContext.request.contextPath}/index.html">
                <div class="envor-logo">
                  <img src="${pageContext.request.contextPath}/img/site-logo.png" alt="Envor Logo">
                  <p class="logo" style="width: 330px;">济南市旅游网！</p>
                <!--

                Site Logo end

                //-->
                </div>
                </a>
                <!--

                Desktop Menu start

                //-->
                <nav>
                  <ul>
                    <li>
                      <a href="${pageContext.request.contextPath}/index">首页</a>
                    </li>
                 
                    <li>
                      <a href="${pageContext.request.contextPath}/project">景点</a>
                    </li>
                     <li>
                      <a href="${pageContext.request.contextPath}/hotel">酒店</a>
                    </li>
                      <li>
                      <a href="${pageContext.request.contextPath}/news">攻略</a>
                    </li>
                    
                      <li>
                      <a href="${pageContext.request.contextPath}/msgboard">论坛/留言</a>
                    </li>
                    
                      <li>
                      <a href="${pageContext.request.contextPath}/center">个人中心</a>
                    </li>
                    <li>
                      <a href="${pageContext.request.contextPath}/about">关于我们</a>
                    </li>
                     <li>
                      <a href="${pageContext.request.contextPath}/register">加入会员</a>
                    </li>
                  </ul>
                <!--

                Desktop Menu end

                //-->
                </nav>
              </div>
            </div>
          </div>
        </div>
      <!--

      Logo & Menu end

      //-->
      </div>
    <!--

    Envor header end

    //-->
    </header>
   
   
