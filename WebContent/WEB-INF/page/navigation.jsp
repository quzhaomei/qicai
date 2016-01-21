<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <div id="head" class="nohome">
            <div class="nav nav_nopadding">
                <div class="container">
                <div class="row navigation"  >
               <div class="col-md-2">
                   <div class="logo">
                      <a href="index.html"> <img src="images/logowhite.png" alt=""></a>
                      
                   </div>
               </div>
               <div class="col-md-7">
                   <div class="mainnav">
                  	 <c:forEach items="${menus }" varStatus="status" var="menu">
                  	 <c:if test="${status.index!=0 }">
                  	 	|
                  	 </c:if>
                           <a  href="${menu.uri }">${menu.name }</a>
                           </c:forEach>
                    </div>
                    
               </div>
                   <div class="col-md-3">
                   <c:if test="${sessionScope.userSession!=null }">
                    <div class="logged text-right"><i class="icon-user"></i> 欢迎您, <a href="userIndex.html">
                    ${sessionScope.userSession.nickname}</a> 
                    ｜ <a href="#" class="logout" id="logout">退出</a></div>
					</c:if>
					 <c:if test="${sessionScope.userSession==null }">
                               <div class="login text-right">
                                  <a href="toLogin.html" >登录</a>
                                  <a href="toRegister.html">注册</a>
                               </div> 
					 </c:if>

                       
                  </div>
             </div>
                
            </div>
            </div>           
        </div>