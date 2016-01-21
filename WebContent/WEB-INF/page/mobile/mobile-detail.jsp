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
        <title>${fn:escapeXml(artical.title) }::汽车财经</title>
        <meta name="description" content="">
        <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, minimum-scale=1, user-scalable=no">  
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/fontello.css">
    
        <link rel="stylesheet" href="css/mobilemain.css">
        <style type="text/css">
        	.contents img{max-width: 100%;height:auto !important;}
        </style>
    </head>
    <body class="detail">
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

       
        <div id="main">
      
        <!-- 资讯开始 -->
        <article id="section">
               <div class="title">
                <h1>${fn:escapeXml(artical.title) }</h1>
               </div>
                <div class="mis">
                  <span class="author"> <a href="">${artical.author.penName}</a>
                  </span> <span class="datetime">发表于  <fmt:formatDate
								value="${artical.createDate }" pattern="yyyy/MM/dd HH:mm:ss" />
                  </span> <span class="topic"> <a href="#">${fn:escapeXml(artical.resource.name)}</a>
                  </span>
                </div>
                <div class="contents">
                  ${artical.content }
                </div>
      
        </article>
        <!-- 资讯结束 -->
       

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
		
		  <div class="pcver">
          <a href="newDetail.html?articalId=${artical.articalId }"><i class="icon-desktop"></i></a>
        </div>
                   
            
      
        

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

  				$("#section .contents img").each(function(){
  					var $parent=$(this).parent("p");
  					if($parent[0]){
  						$parent.css("text-indent","0px")
  					}
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

