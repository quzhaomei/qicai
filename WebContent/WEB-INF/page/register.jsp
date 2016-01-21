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
        #register-login span.ui-helper-hidden-accessible{display: none;}
        #register-login label.error{display:inline; color:#C52727;}
    
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
              
              
              <div class="panel" id="register-login">
                  <div class="tabarea">
                    <div class="tabs">
                      <span class="tab current" id="personal">个人注册</span>|<span class="tab" id="cooperation">企业注册</span>
                    </div>
                    <div class="tabcontents">
                    <!-- 个人注册开始 -->
                       <div class="personal row tabcontent active">
                         <div class="col-md-7">
                              <div class="formset">
                              <form action="register.html" method="post" id="userForm" class="dataForm">
                               <div class="formitem">
                                 <label for="">昵称</label>
                                 <input type="text" name="loginname" maxlength="30" placeholder="单击输入昵称">
                                 <input type="hidden" name="orinal" value="0">
                                  <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">邮箱</label>
                                 <input type="text" class="email" name="email" maxlength="50" placeholder="单击输入邮箱">
                                  <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">手机号</label>
                                 <input type="text"  name="phone" maxlength="11" placeholder="请输入手机号"> 
                                 <span class="info"> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">设置密码</label>
                                 <input type="password" name="password" maxlength="50" placeholder="单击输入登入密码">
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">确认密码</label>
                                 <input type="password" name="repassword" placeholder="单击输入确认密码">
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">验证码</label>
                                 <input type="text" class="verify" id="registerCode" name="registerCode" maxlength="6" placeholder="请输入验证码">
                                 <span class="verify"><img src="getCode.html?code=registerCode" 
                                 title="点击更换验证码"
                                 id="registerCode_img"></span>
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="check">
                                 <input type="checkbox" name="reguler" > 我已阅读<a href="">《汽车财经网用户注册协议》</a>
                                  <span class="info"> </span>
                               </div>
                               <div class="register">
                                 <button class="btn" type="submit" id="userRegister">注册</button>
                               </div>
                               </form>
                             </div>
                         </div>
                         <div class="col-md-5 othermethod">
                            <div class="hasaccount">
                                <span>已有账号？我要<a href="toLogin.html">登录</a></span><br>
                           	      您也可以通过站外账号进行登录
                            </div>
                            
                            <div class="sns">
                              <i class="icon-weibo otherLogin" data-uri="${sinaUri }"  data-height="550" data-width="600"></i>
                              <i class="icon-qq otherLogin" data-uri="${qqUri }"  data-height="550" data-width="600"></i>
                            </div>
                         </div>
                       </div>
                       <!-- 个人注册结束-->
                       
                       <!-- 企业注册开始 -->
                         <div class="cooperation tabcontent row">
                          <div class="col-md-7">
                              <div class="formset">
                                <form action="register.html" method="post" id="cooForm" class="dataFrom">
                               <div class="formitem">
                                 <label for="">登录名</label>
                                 <input type="text" name="loginname" placeholder="请输入登录名" maxlength="30">
                                  <input type="hidden" name="orinal" value="1">
                                  <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">企业名称</label>
                                 <input type="text" name="nickname" placeholder="请输入企业名称" maxlength="30">
                                  <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">电子邮箱</label>
                                 <input type="text" class="email" name="email" placeholder="请输入电子邮箱" maxlength="30">
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                                <div class="formitem">
                                 <label for="">公司地址</label>
                                 <input type="text" class="add" name="position"  placeholder="请输入公司地址" maxlength="50">
                                  <span class="info"> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">设置密码</label>
                                 <input type="password" name="password"  placeholder="请输入密码" maxlength="50">
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">确认密码</label>
                                 <input type="password" name="repassword"  placeholder="请输入密码">
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="formitem">
                                 <label for="">验证码</label>
                                 <input type="text" class="verify" id="registerCode_1" name="registerCode_1" maxlength="6"
                                 placeholder="请输入验证码">
                                 <span class="verify"><img src="getCode.html?code=registerCode_1" 
                                 title="点击更换验证码" id="registerCode_1_img"></span>
                                 <span class="info"><strong>*</strong> </span>
                               </div>
                               <div class="check">
                                 <input type="checkbox" name="reguler"> 我已阅读<a href="#">《汽车财经网用户注册协议》</a>
                                 <span class="info"></span>
                               </div>
                               <div class="register">
                                 <button class="btn" type="submit" id="cooRegister">注册</button>
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
                       <!-- 企业注册结束 -->
                       
                    </div>
                    </div>
                      
                  </div>
              </div>
           

        </div>

    <jsp:include page="footer.jsp" ></jsp:include>
        
        <script src="js/jquery-ui-1.10.0.custom.min.js"></script>
        <script src="js/jquery.validate.min.js"></script><!-- 验证框架 -->
        
        
        <script type="text/javascript">
            $(document).ready(function() {
            	initEmail($(".email"));
               $(".tab").on('click', function(event) {
                  $(this).addClass('current').siblings('.tab').removeClass('current');
                  var target = $(this)[0].id;
                  $(".tabcontents ."+target).addClass('active').siblings('.tabcontent').removeClass('active');
               });
           var valiparam={
             		rules:{
             			loginname:{
             				required:true,
             				rangelength:[6,30],
             				remote:{//
             				    url: "valid.html",     //后台处理程序
             				    type: "post",               //数据发送方式
             				    dataType: "json",           //接受数据格式   
             				    data: {                     //要传递的数据
             				    	loginname: function() {
             				            return $(".active input[name='loginname']").val();
             				        }
             				    }
             				}
             			},
             			nickname:{
             				required:true,
             				rangelength:[3,30]
             			},
             			email:{
             				required:true,
             				email:true,
             				remote:{
             					 url: "valid.html",     //后台处理程序
              				    type: "post",               //数据发送方式
              				    dataType: "json",           //接受数据格式   
              				    data: {                     //要传递的数据
              				        email: function() {
              				        	 return $(".active input[name='email']").val();
              				        }
              				    }
             				}
             			},
             			phone:{
             				isPhone:true,
             				remote:{//验证电话号码重复
             				    url: "valid.html",     //后台处理程序
             				    type: "post",               //数据发送方式
             				    dataType: "json",           //接受数据格式   
             				    data: {                     //要传递的数据
             				        phone: function() {
             				        	 return $(".active input[name='phone']").val();
             				        }
             				    }
             				}
             			},
             			password:{
             				required:true,
             				rangelength:[6,50]
             			},
             			repassword:{
             				required:true,
             				equalTo:".active input[name='password']"
             			},
             			registerCode:{
             				required:true,
             				rangelength:[6,6],
             				remote:{//验证验证码
             				    url: "checkCode.html",     //后台处理程序
             				    type: "post",               //数据发送方式
             				    dataType: "json",           //接受数据格式   
             				    data: {                     //要传递的数据
             				    	code: "registerCode"
             				        ,
             				       value:function() {
             				            return $("#registerCode").val();
             				        }
             				   	 }
             				    }
             			},
             			registerCode_1:{
             				required:true,
             				rangelength:[6,6],
             				remote:{//验证验证码
             				    url: "checkCode.html",     //后台处理程序
             				    type: "post",               //数据发送方式
             				    dataType: "json",           //接受数据格式   
             				    data: {                     //要传递的数据
             				    	code: "registerCode_1"
             				        ,
             				       value:function() {
             				            return $("#registerCode_1").val();
             				        }
             				   	 }
             				    }
             			},
             			reguler:{
             				required:true
             			}
             		},
             		messages:{
             			loginname:{
             				required:"请输入用户名",
             				rangelength:"用户名为6-30位",
             				maxlength:"字数过多，最多为30位",
             				remote:"该用户名已存在"
             			},
             			nickname:{
             				required:"请输入企业名称",
             				rangelength:"企业名称至少为3位"
             			},
             			email:{
             				required:"请输入邮箱",
             				maxlength:"字数过多",
             				email:"请输入合法的邮箱",
             				remote:"该邮箱已经被注册过"
             			},
             			phone:{
             				isPhone:"请输入合法的手机号",
             				remote:"该手机号已被注册"
             			},
             			password:{
             				required:"请输入登陆密码",
             				maxlength:"字数过多",
             				rangelength:"密码至少为6位"
             				
             			},
             			repassword:{
             				required:"请输入登陆密码",
             				maxlength:"字数过多",
             				equalTo:"两次输入密码不一致！"
             			},
             			registerCode:{
             				required:"请输入验证码",
             				rangelength:"请输入六位验证码",
             				remote:"验证码错误"
             			},
             			registerCode_1:{
             				required:"请输入验证码",
             				rangelength:"请输入六位验证码",
             				remote:"验证码错误"
             			},
             			reguler:{
             				required:" 请点击我已阅读"
             			}
             		}
             		,errorPlacement: function(error, element) {  
                		 error.appendTo(element.parent().find("span.info"));  
             		} 
             	};  
               //新增用户的数据验证
             	var userValid=$("#userForm").validate(valiparam);
               //新增用户的数据验证
             	var cooValid=$("#cooForm").validate(valiparam);
             //点击更换验证码
             $("#registerCode_1_img").on("click",function(){
            	 this.src="getCode.html?code=registerCode_1&"+new Date();
            	 $("#registerCode_1").val("");
             });
             $("#registerCode_img").on("click",function(){
            	 this.src="getCode.html?code=registerCode&"+new Date();
            	 $("#registerCode").val("");
             });
              /*  $("#userRegister").on("click",function(){
            	   var param=getFormDate($("#userForm"));
           	   $("#userForm").submit();
               });
               $("#cooRegister").on("click",function(){
            	   cooValid.resetForm();
               });  */
               
            });
          
            function initEmail($email){
                var MailList = ["qq.com", "hotmail.com", "163.com", "yahoo.com", "126.com"
                                ,"sina.cn"];
                $email.autocomplete({
                    autoFocus: true,
                    source: function (request, response) {
                        var KeyValue = request.term,
                            index = KeyValue.indexOf("@"),
                            Address = KeyValue,
                            host = "",
                            result = [];

                        result.push(KeyValue);
                        if (index > -1) {
                            Address = KeyValue.slice(0, index);
                            host = KeyValue.slice(index + 1);
                        }
                        if (Address) {
                            var findedHosts = (host ? $.grep(MailList, function (value) {
                                return value.indexOf(host) > -1;
                            }) : MailList),
                            findedResults = $.map(findedHosts, function (value) {
                                return Address + "@" + value;
                            });
                            result = result.concat($.makeArray(findedResults));
                        }
                        response(result);
                    }
                });
            	}
            $.validator.addMethod("isPhone", function(value, element) {   
              	 var tel = /^1[3|4|5|8][0-9]\d{4,8}$/;
              	 return this.optional(element) || (tel.test(value)&&value.length==11);
           	});
           	$.validator.addMethod("isEmail", function(value, element) {   
              	 var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
              	 return this.optional(element) || (email.test(value));
           	});
           	//获取表单元素的值
           	function getFormDate($form){
           		var $data=$form.find("input[name]");
           		var formDate={};
           		$data.each(function(){
           			formDate[$(this).attr("name")]=$(this).val();
           		});
           		return formDate;
           	}
        </script>
    </body>
</html>

