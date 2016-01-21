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
        <title>汽车财经</title>
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
    <body>
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
                  	<li class="active">
					<a href="index.html">首页</a>
					</li>
					
                  		<li>
						<a href="mobileList.html?type=1">资讯</a>
						</li>
					
                  		<li>
						<a href="mobileList.html?type=2">对话</a>
						</li>
					
                  		<li>
						<a href="mobileList.html?type=7">数据</a>
						</li>
					
                  		<li>
						<a href="mobileList.html">专栏</a>
						</li>
					
                  		<li>
						<a href="mobileList.html?type=5">活动</a>
						</li>
                </ul>
								
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

          <!-- 今日头条开始 -->
            <div id="highlight">
              <div class="title">
                <h1>今日头条</h1>
              </div>
              <ul>
              
                <li>
                  <div class="img">
                  	<a href="mobileDetail.html?articalId=${top.artical.articalId }">
                  	<div class="artical_img">
					<img o-width="${top.artical.scanImgWidth }"
						o-height="${top.artical.scanImgHeight }"
						src="${top.artical.scanImgPath }" />
					</div>
					</a></div>
                  <h2><a href="mobileDetail.html?articalId=${top.artical.articalId }">
                 	 <a:trim length="50" content="${top.artical.quote}"></a:trim></a>
                  </h2>
                </li>
                
                <li>
                  <div class="img">
                  	<a href="mobileDetail.html?articalId=${general.artical.articalId }">
                  	<div class="artical_img">
					<img o-width="${general.artical.scanImgWidth }"
						o-height="${general.artical.scanImgHeight }"
						src="${general.artical.scanImgPath }" />
					</div>
					</a></div>
                  <h2><a href="mobileDetail.html?articalId=${general.artical.articalId }">
                 	 <a:trim length="50" content="${general.artical.quote}"></a:trim></a>
                  </h2>
                </li>
                
                <li>
                   <div class="img">
                  	<a href="mobileDetail.html?articalId=${quick.artical.articalId }">
                  	<div class="artical_img">
					<img o-width="${quick.artical.scanImgWidth }"
						o-height="${quick.artical.scanImgHeight }"
						src="${quick.artical.scanImgPath }" />
					</div>
					</a></div>
                  <h2><a href="mobileDetail.html?articalId=${quick.artical.articalId }">
                 	 <a:trim length="50" content="${quick.artical.quote}"></a:trim></a>
                  </h2>
                </li>
                
                <li>
                    <div class="img">
                  	<a href="mobileDetail.html?articalId=${orignal.artical.articalId }">
                  	<div class="artical_img">
					<img o-width="${orignal.artical.scanImgWidth }"
						o-height="${orignal.artical.scanImgHeight }"
						src="${orignal.artical.scanImgPath }" />
					</div>
					</a></div>
                  <h2><a href="mobileDetail.html?articalId=${orignal.artical.articalId }">
                 	 <a:trim length="50" content="${orignal.artical.quote}"></a:trim></a>
                  </h2>
                </li>
              </ul>
            </div>
        <!-- 今日头条结束 -->


        <!-- 最新资讯开始 -->
        <div id="newest">
             <div class="title">
                <h1>最新资讯</h1>
              </div>
              <ul id="news_ul">
              <c:forEach begin="0" end="4" var="index">
              	<c:if test="${fn:length(newPage.param)>index }">
              	<c:set var="newitem" value="${newPage.param[index] }" />
              		<li> 
	                  <div class="img leftimg">
									<div class="artical_img">
									<a href="mobileDetail.html?articalId=${newitem.articalId }">
										<img o-width="${newitem.scanImgWidth }"
											o-height="${newitem.scanImgHeight }"
											src="${newitem.scanImgPath }-mini" />
											</a>
									</div>
	                  </div>
	                  <div class="contents rightcon">
	                      <h2>
	                       <a href="mobileDetail.html?articalId=${newitem.articalId }">
											<a:trim length="30" content="${newitem.title}"></a:trim>

										</a>
	                      </h2>
	                      <div class="mis bottomfix">
	                     		 发表于
										<fmt:formatDate value="${newitem.createDate }"
											pattern="yyyy/MM/dd HH:mm:ss" />
	                      </div>
	                  </div>
	                </li> 
              	</c:if>
              </c:forEach>
              </ul>

              <div class="more">
                  <button class="btn" id="loadMoreNews">
                    	  查看更多
                  </button>
              </div>
        </div>
        <!-- 最新资讯结束 -->

        

      <!-- 专题开始 -->
        <div id="column">
              <div class="title">
                <h1>精彩专题</h1>
              </div>
              <ul class="bxslider2">
              <c:forEach items="${tagPage }" var="temp">
	              <li>
	            			<a href="mobileList.html?tagId=${temp.tagId }">
											<img o-width="${temp.imgWidth }"
												o-height="${temp.imgHeight }"
												src="${temp.imgPath }-mini" style="width:100%;height:200px;"/>
								</a>
	              <div class="bx-caption"> 
	              	<a:trim length="20" content="${temp.tagName }"></a:trim>
	              </div>
	              </li>
              </c:forEach>
            </ul>
          
        </div>

      <!-- 专题结束 -->





      <!-- 名人开始 -->
      <div id="famous">
           <div class="title">
                <h1>对话名人</h1>
           </div>

           <ul id="famous_ul">
           
            <c:forEach begin="0" end="5" var="index">
            <c:set var="temp" value="${famousPage[index] }" />
             <li>
             <a href="mobilefamousDetail.html?peopleId=${temp.peopleId }" style="diplay:block;width:100%;height:100%;">
              <div class="img">
							<div class="artical_img">
								<img o-width="${temp.imgWidth }" o-height="${temp.imgHeight }"
									src="${temp.imgPath }-mini" />
							</div>
						</div>
               <div class="caption">
                	<a:trim length="6" content="${temp.name }"></a:trim>
               </div>
                </a>
             </li>
            
             </c:forEach>
             
             
           </ul>

            <div class="more">
              <a href="javascript:void(0);">
                  <button class="btn" type="button" id="moreFamous">
                     		 查看更多
                  </button>
                </a>
              </div>

           
      </div>
	<div style="clear:both;"></div>
       <!-- 名人结束 -->

    



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
          <a href="index.html?tag=PC"><i class="icon-desktop"></i></a>
        </div>
        
<span class="statistic">
        <script language="javascript" type="text/javascript" src="http://js.users.51.la/18666544.js"></script>
<noscript>
<a href="http://www.51.la/?18666544" target="_blank">
<img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/18666544.asp" style="border:none" />
</a></noscript> 
</span>
            
      
        

        <script src="js/vendor/jquery-1.11.3.min.js"></script>
        <script src="js/vendor/jquery.bxslider.min.js"></script>
        <script src="js/layer/layer.js"></script>
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

                 
                $('.bxslider2').bxSlider({
                  speed: 750,
                  auto: true,
                  pager: false,
                  pause: 5000
                });

              
                $("#famous").on('mouseover', 'li', function(event) {
                    $('.overlay',this).show();
                }).on('mouseout', 'li', function(event) {
                    $('.overlay',this).hide();
                });
               
                //加载更多资讯
                var pageIndex=${newPage.pageIndex+1 };
            	var type=1; 
                var pageSize=${newPage.pageSize };
                var totalPage=${newPage.totalPage };
				$("#loadMoreNews").on("click",function(html){
					 if(pageIndex<=totalPage){
	            		   var param={};
	            		   param.pageIndex=pageIndex;
	            		   param.type=type;
	            		   param.pageSize=pageSize;
	            		   param.totalPage=totalPage;
	            		   $.post("mobileLoadMore.html",param,function(html){
	            			   $("#news_ul").append(html);
	            			   pageIndex++;
	            			   
	            			   $(".artical_img").each(function() {
	            					var img=$(this).find("img")[0];
	            					// 从缓存获取时候的处理过程
	            					loadInitImg(img);
	            					// 第一次加载完毕事件
	            					img.onload = function() {
	            						loadInitImg(this);
	            					}
	            					// 事件结束
	            				});
	            			   if(pageIndex==totalPage){
	            				   $("#loadMore").parent().remove();
	            			   }
	            		   });
	            	   }
				});
				
				//加载更多名人
                var pageIndex_famous=1;
                var pageSize_famous=6;
                var totalPage_famous=0;//初始化
				$("#moreFamous").on("click",function(html){
					 if(pageIndex_famous<=totalPage_famous||totalPage_famous==0){
							pageIndex_famous++;
	            		   var param={};
	            		   param.pageIndex=pageIndex_famous;
	            		   param.pageSize=pageSize_famous;
	            		   $.post("mobileMoreFamous.html",param,function(json){
	            			   totalPage_famous=json.totalPage;//初始化总页数
	            			   var html="";
	            			   $(json.param).each(function(){
	            				   var temp=this;
	            				   html+='<li> <a href="mobilefamousDetail.html?peopleId='+temp.peopleId+'" style="diplay:block;width:100%;height:100%;"><div class="img">'+
	   									'<div class="artical_img">'+
	   								'<img o-width="'+temp.imgWidth+'" o-height="'+temp.imgHeight+'" src="'+temp.imgPath+'-mini" />'+
	   							'</div></div>'+
			                 ' <div class="caption">'+
			                   	(temp.name.length>6?(temp.name.substring(0,6)):temp.name)+
			                  '</div>'+
			                '</a></li>';
	            			   });
	            			   $("#famous_ul").append(html);
	            			   $(".artical_img").each(function() {
	            					var img=$(this).find("img")[0];
	            					if(img){
	            					// 从缓存获取时候的处理过程
	            					loadInitImg(img);
	            					// 第一次加载完毕事件
	            					img.onload = function() {
	            						loadInitImg(this);
	            					}
	            					}
	            				});
	            			   if(totalPage_famous==pageIndex_famous){
	            				   $("#moreFamous").parent().remove();
	            			   }
	            		   },"json");
	            	   }
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
    </body>
</html>

