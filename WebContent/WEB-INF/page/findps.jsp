<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/adtag" prefix="a"%>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
         <title>找回密码－汽车财经</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/fontello.css">

        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

              <!-- 广告位开始 -->
             <div class="abad" style="width: 1060px; height: 100px; px; margin: auto;">
                      <!-- 广告位 -->
					<a:output code="findpspage_1" descript="找回密码页面头部广告位" height="100"
						width="1060" href="#" />
             </div>
     
     <jsp:include page="/menus.html" ></jsp:include>


       
        <div id="main" class="container nohome">
              
              
              <div class="panel" id="register-login">
                  <div class="tabarea">
                    <div class="tabs">
                      <span class="tab current" id="personal">找回密码</span>
                    </div>
                    <div class="tabcontents">
                       <div class="personal row tabcontent active">
                         <div class="col-md-7">
                              <div class="formset">
                               <div class="formitem">
                                 <label for="">邮箱</label>
                                 <input type="text">
                                  <span class="info"><strong>*</strong> 单击输入邮箱</span>
                               </div>
                               <div class="tologin">
                                 <span>还未注册？<a href="toRegister.html">立刻注册</a></span>
                               </div>
                               <div class="register">
                                 <button class="btn">立刻找回</button>
                               </div>
                             </div>
                         </div>
                         <div class="col-md-5">
                            
                         </div>
                       </div>

                       
                    </div>
                      
                  </div>
              </div>
           

        </div>

        
      <jsp:include page="footer.jsp" ></jsp:include>
      
        <script type="text/javascript">
            $(document).ready(function() 
            {
               $(".tab").on('click', function(event) {
                  $(this).addClass('current').siblings('.tab').removeClass('current');
                  var target = $(this)[0].id;
                  $(".tabcontents ."+target).addClass('active').siblings('.tabcontent').removeClass('active');
               });
            });
        </script>
    </body>
</html>

