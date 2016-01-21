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
         <title>用户登录-汽车财经</title>
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
        #register-login span.ui-helper-hidden-accessible{display: none;}
        #register-login .error{display:inline; color:#C52727;}
      
        
        </style>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

              <!-- 广告位开始 -->
             <div class="abad" style="width: 1060px; height: 100px;  margin: auto;">
               <!-- 广告位 -->
					<a:output code="newspage_1" descript="登录页面头部广告位" height="100"
						width="1060" href="#" />
             </div>
            <!-- 广告位结束 -->
            
	<jsp:include page="/menus.html" ></jsp:include>

       
            
        <div id="main" class="container nohome">
              
              
              <div class="panel" id="register-login">
                  <div class="tabarea">
                    <div class="tabs">
                      <span class="tab ${(orinal==0||orinal==null)?'current':'' }" id="personal">个人登录</span>
                      |<span class="tab ${(orinal==1)?'current':'' }" id="cooperation">企业登录</span>
                    </div>
                    <div class="tabcontents">
                       <div class="personal row tabcontent ${(orinal==0||orinal==null)?'active':'' }">
                         <div class="col-md-7">
                              <div class="formset">
                                <form ction="toLogin.html" method="post" id="userForm"> 
                               <div class="formitem">
                                 <label for="">昵称/邮箱</label>
                                 <input type="text" name="loginname" maxlength="30" value="${orinal==0?loginname:'' }" autocomplete="off" placeholder="单击输入昵称/邮箱">
                                 <input type="hidden" name="orinal" value="0">
                                  <span class="info"><strong>*</strong> <span class="error">
                                  	 <c:if test="${orinal==0 }">
                                  	${loginname_info }
                                  </c:if>
                                  </span> </span>
                               </div>
                               
                               <div class="formitem">
                                 <label for="">登录密码</label>
                                 <input type="password" name="password" placeholder="单击输入登入密码" maxlength="50">
                                 <span class="info"><strong>*</strong> <span class="error">
                                 	 <c:if test="${orinal==0 }">
                                  	${password_info }
                                  </c:if>
                                 </span></span>
                               </div>
                              
                               <div class="check">
                                 <input type="checkbox" name="loginTag" value="true"> 记住登录状态
                                 <span><a href="findps.html">忘记密码？</a></span>
                               </div>
                               <div class="register">
                                 <button class="btn" type="submit">登录</button>
                               </div>
                               </form>
                             </div>
                         </div>
                         <div class="col-md-5 othermethod">
                            <div class="hasaccount">
                                <span>没有账号？我要<a href="toRegister.html">注册</a></span><br>
                             	    您也可以通过站外账号进行登录
                            </div>
                            
                            <div class="sns">
                              <i class="icon-weibo otherLogin"  data-uri="${sinaUri }"  data-height="550" data-width="600"></i>
                              <i class="icon-qq otherLogin" data-uri="${qqUri }"  data-height="550" data-width="600"></i>
                            </div>
                         </div>
                       </div>

                       <div class="cooperation tabcontent row ${orinal==1?'active':'' }">
                          <div class="col-md-7">
                              <div class="formset">
                               <form action="toLogin.html" method="post" id="cooForm"> 
                                <div class="formitem">
                                 <label for="">登录名</label>
                                 <input type="text" name="loginname" maxlength="30" value="${orinal==1?loginname:'' }" autocomplete="off" placeholder="单击输入登录名">
                                  <span class="info"><strong>*</strong> <span class="error">
                                  <c:if test="${orinal==1}">
                                  	${loginname_info }
                                  </c:if>
                                  </span></span>
                                   <input type="hidden" name="orinal" value="1">
                               </div>
                               
                               <div class="formitem">
                                 <label for="">登录密码</label>
                                 <input type="password" name="password" maxlength="50" placeholder="单击输入登入密码">
                                 <span class="info"><strong>*</strong> <span class="error">
                                 	 <c:if test="${orinal==1}">
                                  	${password_info }
                                  </c:if>
                                 </span></span>
                               </div>
                              
                               <div class="check">
                                <input type="checkbox" name="loginTag" value="true"> 记住登录状态
                                 <span><a href="findps.html">忘记密码？</a></span>
                               </div>
                               <div class="register">
                                 <button class="btn" type="submit">登录</button>
                               </div>
                                </form>
                             </div>
                         </div>
                         <div class="col-md-5 othermethod">
                            <div class="hasaccount">
                                <span>已有账号？我要<a href="toLogin.html">登录</a></span><br>
                            </div>
                            
                         </div>
                       </div>
                    </div>
                      
                  </div>
              </div>
           
        </div>
        
        <jsp:include page="footer.jsp" ></jsp:include>
        <script type="text/javascript">
        $(document).ready(function() {
              $(".tab").on('click', function(event) {
                    $(this).addClass('current').siblings('.tab').removeClass('current');
                    var target = $(this)[0].id;
                    $(".tabcontents ."+target).addClass('active').siblings('.tabcontent').removeClass('active');
                 });
              //个人登录验证
              $("#userForm").on("submit",function(){
            		return validDate(this);
              });
              //个人登录验证
              $("#cooForm").on("submit",function(){
            		return validDate(this);
              });
            //个人登录验证
              $("#userForm").on("blur","input[type='text']",function(){
            		 validDate($("#userForm"));
              });
              //个人登录验证
              $("#cooForm").on("blur","input[type='text']",function(){
            		validDate($("#cooForm"));
              });
            
        });    

        function validDate(obj){
      	  var $loginname= $(obj).find("input[name='loginname']");
       	 var $login_tips= $loginname.parent().find("span.info .error");
       	 if(!$loginname.val()){
       		 $login_tips.text("登录名不能为空！");
       		 return false;
       	 }else if($loginname.val().length<6){
       		 $login_tips.text("登录名太短");
       		 return false;
       	 }else{
       		 $login_tips.text("");
       	 }
       	
       	 var $password= $(obj).find("input[name='password']");
       	 var $password_tips= $password.parent().find("span.info .error");
       	 if(!$password.val()){
       		 $password_tips.text("登录密码不能为空！");
       		 return false;
       	 }else if($password.val().length<6){
       		 $password_tips.text("密码太短，至少为6位！");
       		 return false;
       	 }else{
       		 password_tips.text("");
       	 }
       	 
       	 return true;
        }
              
        </script>
    </body>
</html>

