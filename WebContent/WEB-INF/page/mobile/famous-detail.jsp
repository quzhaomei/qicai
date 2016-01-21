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
        <title>名人::汽车财经</title>
        <meta name="description" content="">
        <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, minimum-scale=1, user-scalable=no">  
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/fontello.css">
    
        <link rel="stylesheet" href="css/mobilemain.css">
         <style>
		.artical_img {
			position: relative;
			overflow: hidden;
			height: 100%;
		}
		
		.artical_img img {
			min-height: 100%;
			min-width: 100%;
			position: relative;
		}
		
		.img img {
			width: auto;
			height: auto;
		}
</style>
    </head>
    <body >
        <div id="head">
           <div id="top" class="row">
               <div class="col-xs-8">
                   <div class="logo">
                       <img src="images/logo.png" alt="">
                   </div>
               </div>
               <div class="col-xs-4">
                  <div class="search text-right">
                     <button><i class="icon-search-1"></i></button>
                  </div>
            </div>

            </div>

            <nav id="mainnav" class="hnav">
                <ul>
                  <li>
					<a href="index.html">首页</a>
					</li>
                  		<li ${type==1?"class='active'":"" }>
						<a href="mobileList.html?type=1">资讯</a>
						</li>
					
                  		<li ${type==2?"class='active'":"" }>
						<a href="mobileList.html?type=2">对话</a>
						</li>
					
                  		<li ${type==7?"class='active'":"" }>
						<a href="mobileList.html?type=7">数据</a>
						</li>
					
                  		<li>
						<a href="mobileList.html">专栏</a>
						</li>
					
                  		<li ${type==5?"class='active'":"" }>
						<a href="mobileList.html?type=5">活动</a>
						</li>
                </ul>
            </nav>
             <div id="searcharea">
            <form action="mobileSearch.html" method="post" id="search-form">
                <input type="text" placeholder="请输入搜索内容" name="key" id="search-key"><span id="search-btn"><i class="icon-search-1"></i></span>
                <span class="scrollup"><i class="icon-up-open"></i></span>
                </form>
            </div>

        </div>

       
        <div id="main" class="famous">
      
        <!-- 名人介绍开始 -->
          <div class="panel intro">
               <div class="person">
                <div class="img">
                  <img src="${people.imgPath }-mini" style="width:100%;height:100%;"/>
                </div>
                <div class="name">
                    <h2>${people.name }<br>
                   ${people.ename }
                    </h2>
                 
                </div>
              </div>
              <div class="per_detail">
                  <div class="info">
                   <c:if test="${!empty people.name }"> 中文名：${people.name }<br></c:if>   
                 <c:if test="${!empty people.ename }"> 外文名： ${people.ename }<br></c:if>
                 <c:if test="${!empty people.nationality }">   国 籍：${people.nationality }<br></c:if>
                 <c:if test="${!empty people.birthPlace }">   出生地：${people.birthPlace }<br></c:if>
            <c:if test="${!empty people.birthDay }">  出生日期：<fmt:formatDate value="${people.birthDay }"
             pattern="yyyy年MM月dd日"/> <br></c:if>
                <c:if test="${!empty people.job }">  职 业：${people.job }</c:if>
                  </div>

                  <div class="resume">
                      <div class="title">
                        <h2>个人简介</h2>
                      </div>
                        <p>${people.introduce }</p>
                    </div>
             </div>
          </div>
          <!-- 名人介绍结束 -->
          
          
          <!-- 相关文章开始 -->
		<c:if test="${! empty onwerList}">
            <div class="panel talklist" id="sectionlist">
              <div class="title">
                <h1>
                	  相关文章
                </h1>
              </div>
              <ul>
              <c:forEach items="${onwerList }" var="news">
               <li> 
                  <div class="img leftimg">
                    <a href="mobileDetail.html?articalId=${news.articalId }">
	                           <div class="artical_img">
											<img o-width="${news.scanImgWidth }" o-height="${news.scanImgHeight }"
												src="${news.scanImgPath }" />
									</div>
									</a>
                  </div>
                  <div class="contents rightcon">
                      <h2>
                        <a href="mobileDetail.html?articalId=${news.articalId }">
                            <a:trim length="20" content="${news.title }"></a:trim>
                            </a>
                      </h2>
                      <div class="mis bottomfix">
							<fmt:formatDate value="${news.createDate }"
												pattern="yyyy/MM/dd" />
                      </div>
                  </div>
                </li> 
                </c:forEach>
              </ul>
            </div>
		</c:if>
          <!-- 相关文章结束 -->

       

        </div>


        <footer class="hnav">
            <ul>
              <li>
                  <a href="">联系我们</a>
              </li>
              <li>
                  微信号：Autofortune2014
              </li>

               <li>
                  <a href="">官方微博</a>
              </li>

              <li>
                  <a href="">投稿须知</a>
              </li>
              
              
            </ul>
            <div class="copyright">
               Copyright © 2015 auto-biz.cn
            </div>
        </footer>

                   
            
      
        

        <script src="js/vendor/jquery-1.11.3.min.js"></script>
        <script src="js/vendor/jquery.bxslider.min.js"></script>
        <script src="js/main.js"></script>
        <script type="text/javascript">
            $(document).ready(function() 
            {
            	 $('.search').on('click', '', function(event) {
                     $("#searcharea").addClass('shown');
                  });

                  $('#searcharea').on('click', '.scrollup', function(event) {
                    $("#searcharea").removeClass('shown');
                  });
                  
                //搜索按钮
  				$("#search-btn").on("click",function(){
  					var key=$("#search-key").val();
  					if(!$.trim(key)){
  						alert("请输入搜索内容");
  						return;
  					}
  					$("#search-key").val($.trim(key));
  					$("#search-form").submit();
  				});

                
            });
        </script>
        <span class="statistic">
        <script language="javascript" type="text/javascript" src="http://js.users.51.la/18666544.js"></script>
<noscript>
<a href="http://www.51.la/?18666544" target="_blank">
<img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/18666544.asp" style="border:none" />
</a></noscript>
</span>
    </body>
</html>

