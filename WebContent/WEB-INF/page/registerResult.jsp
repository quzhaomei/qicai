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
        <title>用户注册-汽车财经</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/fontello.css">

        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <style type="text/css">
    
        </style>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

              <!-- 广告位开始 -->
             <div class="abad" style="width: 1060px; height: 100px;  margin: auto;">
               <!-- 广告位 -->
					<a:output code="registerpage_1" descript="注册页面头部广告位" height="100"
						width="1060" href="#" />
             </div>
            <!-- 广告位结束 -->
            
		<jsp:include page="/menus.html" ></jsp:include>

       
        <div id="main" class="container nohome">
              
              <c:if test="${json.status==1 }">
	              <div class="panel" id="register-login">
	                  <div class="tabarea" style="text-align:center;padding-top: 50px;">
	                      	<h1>注册用户成功</h1>
	                      <p>
	                     <a id="clock" href="#">3</a>秒钟后跳往 <a href="index.html">主页</a>
	                      </p>	
	                  </div>
	              </div>
              </c:if>
              <c:if test="${json.status==0 }">
               <div class="panel" id="register-login">
                  <div class="tabarea" style="text-align:center;padding-top: 50px;">
                      	<h1>注册用户失败</h1>
                      <p>
                      ${json.message }
                      </p>	
                  </div>
              </div>
              </c:if>
        </div>
    <jsp:include page="footer.jsp" ></jsp:include>
        
    </body>
    <script type="text/javascript">
    <c:if test="${json.status==1 }">
    var num=3;
    var task=
    setInterval(function(){
    	if(num==0){
    		clearInterval(task);
    		window.location.href="index.html";
    	}
    	$("#clock").text(num);
    	num--;
    },1000);
    </c:if>
    </script>
</html>

