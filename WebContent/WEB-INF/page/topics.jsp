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
        <title>精彩专题-汽车财经</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/fontello.css">

        <link rel="stylesheet" href="css/main.css">
        <style>
.artical_img{position:relative;overflow:hidden;height:100%;}
.artical_img img{min-height:100%;min-width:100%;position:relative;}
.img img{width:auto;height:auto;}
</style>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

              <!-- 广告位开始 -->
             <div class="abad" style="width: 1060px; height: 100px; margin: auto;">
                      <!-- 广告位 -->
					<a:output code="topicspage_1" descript="专题页面头部广告位" height="100"
						width="1060" href="#" />
             </div>
            <!-- 广告位结束 -->
      <jsp:include page="/menus.html" ></jsp:include>


       
        <div id="main" class="container nohome">

            
           
            <!-- 专题开始 -->
            <div class="row bgwhite">
                <div class="col-lg-8">
                <!-- 专栏列表开始 -->
                    <div id="columnpage">
                    <c:forEach items="${tags.param }" var="tag" varStatus="status">
                        
                             <div class="item">
                             <div class="contents">
                            <div class="imgfull artical_img">
                             <a href="topicDetail.html?tagId=${tag.tagId }">
                                <img o-width="${tag.imgWidth }" o-height="${tag.imgHeight }"
								src="${tag.imgPath }" />
								</a>
                            </div>
                            <h2>
                       		 <a href="topicDetail.html?tagId=${tag.tagId }">
                                 ${fn:escapeXml(tag.tagName) }
                                 </a>
                            </h2>
                            <div class="text">
                            	${fn:escapeXml(tag.introduce) }
                            </div>
                                <div class="mis">
                                <div class="datetime">
                                    <span class="time">
                                    <fmt:formatDate value="${tag.createDate }" pattern="yyyy-MM-dd"/>
                                    </span>
                                    <span class="source">来自：<a href="#">
                                    	汽车财经</a></span>
                                  
                                </div>
                            </div>
                                
                            </div>
                        </div>
                        
                    </c:forEach>
                    
                    <!-- 隐藏表单 -->
					<form action="topics.html" method="post" id="newsForm">
					<input type="hidden" name="pageIndex" id="pageIndex" value="${tags.pageIndex }"/>
					<input type="hidden" name="pageSize" id="pageSize" value="${tags.pageSize }"/>
					</form>
                          <ul class="pagination">
                        <li  
                        <c:if test="${tags.pageIndex>1 }">
                        	index="${tags.pageIndex-1}"
                        </c:if>
                        <c:if test="${tags.pageIndex==1 }">
                        	class="disabled"
                        </c:if>
                        
                        >
                          <a href="javascript:void(0);">
                            <i class="icon-left-open-big"></i>
                          </a>
                        </li>
                          <c:if test="${tags.pageIndex-1==4 }">
	                         	 <li index="1">
	                           <a href="javascript:void(0);" >1</a>
	                           </li>
	                         </c:if>
	                         <c:if test="${tags.pageIndex-1>4 }">
	                         <li index="1">
	                           <a href="javascript:void(0);" >1</a>
	                           </li>
	                         <li>
	                           <a href="javascript:void(0);">...</a>
	                         </c:if>
	                         
                        <c:forEach begin="1" end="${tags.totalPage }" var="temp">
	                        <c:if test="${(temp-tags.pageIndex>-4)&&(temp-tags.pageIndex<4) }">
	                           <li ${temp==tags.pageIndex?"class='active'":"" } index="${temp }">
	                           <a href="javascript:void(0);">${temp }</a>
	                           </li>
	                         </c:if>
                        </c:forEach>
                        
                         <c:if test="${tags.totalPage-tags.pageIndex==4 }">
	                         	 <li index="${tags.totalPage  }">
	                           <a href="javascript:void(0);" >${tags.totalPage }</a>
	                           </li>
	                         </c:if>
	                         <c:if test="${tags.totalPage-tags.pageIndex>4 }">
	                         <li >
	                           <a href="javascript:void(0);">...</a>
	                         	 <li index="${tags.totalPage }">
	                           <a href="javascript:void(0);" >${tags.totalPage }</a>
	                           </li>
	                         </c:if>
                        <li 
	                        <c:if test="${tags.pageIndex==tags.totalPage }">
	                        	class="disabled"
	                        </c:if>
	                        <c:if test="${tags.pageIndex!=tags.totalPage }">
	                        	index="${tags.pageIndex+1}"
	                        </c:if>
                        >
                          <a href="javascript:void(0);">
                            <i class="icon-right-open-big"></i>
                          </a>
                        </li>
                     </ul>

                    </div>
                     <!-- 专栏列表结束 -->
                 
                </div>



                <div class="col-lg-4">

                        <div id="sidebar">
                            <div class="search sec">
                              <div class="title">
                                  <span></span>
                                  <h2>新闻搜索</h2>

                              </div>
                             
                                 <div class="contents">
                                    <form action="searchArtical.html" method="post" 
                                 onsubmit="if(!(document.getElementById('searchKey').value)){return false;}">
                                     <input type="text" name="key" id="searchKey" value="${key }" maxlength="20">
                                    <button type="submit"><i class="icon-search-1"></i></button>
                                    </form>
                                </div>
                            </div>
                            
                            
                               <!-- 猜你喜欢开始 -->
                            <div class="reco sec hidedetail">
                                 <div class="title">
                                  <span></span>
                                  <h2>猜你喜欢</h2>
                                 </div>
                                 <ul class="list">
									 <c:forEach items="${nowList }" var="hot" varStatus="status">
                                 	<li class="item">
                                        <div>
                                        <a href="newDetail.html?articalId=${hot.articalId }">
                                          <a:trim length="16" content="${hot.title }"></a:trim>
                                           </a>
                                        </div>
                                         
                                         <div class="contents">
                                            <div class="img">
                                            	<div class="artical_img">
												<img style="width:100%;height:100%"
														src="${hot.scanImgPath }-mini" />
												</div>
                                            </div>
                                            <div class="text">
                                             <a:trim length="32" content="${hot.quote }"></a:trim>
                                            </div>
                                         </div>   
                                     </li>
                                 </c:forEach>
                                 </ul>
                            </div>
                           <!-- 猜你喜欢结束 -->
                           
                            <!-- 一周热文开始 -->
                            <div class="weekly sec hidedetail"
                            <c:if test="${fn:length(hotList)==0}">
                            style="display:none;"
                            </c:if>
                            >
                                 <div class="title">
                                  <span></span>
                                  <h2>一周热文</h2>
                                  </div>

                                 <ul class="list">
                                 <c:forEach items="${hotList }" var="hot" varStatus="status">
                                 	<li class="item">
                                        <div>
                                            <span class="digi ${status.count<=3?'top':'' }">
                                               ${status.count }
                                            </span>
                                            <a href="newDetail.html?articalId=${hot.articalId }"">
                                             <a:trim length="14" content="${hot.title }"></a:trim>
                                            </a>
                                        </div>
                                         
                                         <div class="contents">
                                            <div class="img">
                                            	<div class="artical_img">
											<img style="width:100%;height:100%"
														src="${hot.scanImgPath }-mini" />
												</div>
                                            </div>
                                            <div class="text">
                                           	  <a:trim length="32" content="${hot.quote }"></a:trim>
                                            </div>
                                         </div>   
                                     </li>
                                 </c:forEach>
                                
                                </ul> 
                            
                            </div>

                           <!-- 一周热文结束 -->
                            

                             <!-- 广告位开始 -->
                              <div class="abad" style="width: 100%; height: 250px; ">
                                		<a:output code="topicspage_2" descript="专题页面中部右边广告位" height="250"
						width="296" href="#" />
                                </div>
                            <!-- 广告位结束 -->
                        </div>

                </div>


            </div>

            <!-- 分栏结束 -->

            <div class="row bgwhite">
                      <!-- 广告位开始 -->
                     <div class="abad" style="width: 100%; height: 100px;  margin-bottom: 30px;">
                           <!-- 广告位 -->
					<a:output code="topicspage_3" descript="专题页面底部广告位" height="100"
						width="1060" href="#" />
                    </div>
                    <!-- 广告位结束 -->   
            </div>
          


           

        </div>

     

<jsp:include page="footer.jsp" ></jsp:include>

        <script type="text/javascript">
            $(document).ready(function() 
            {
            	$(".pagination li").on("click",function(){
            		var index=$(this).attr("index");
            		if(index){
            			$("#pageIndex").val(index);
            			$("#newsForm").submit();
            		}
            	});
            	
            	//缩略图居中
        		$(".artical_img").each(function(){
        			var $img=$(this).find("img");
        			var width1=$(this).width();
        			var height1=$(this).height();
        			var width2=$img.attr("o-width");
        			var height2=$img.attr("o-height");
        			if(!width2){
        				return;
        			}
        			//如果图片比div大，则比较比例
        			if(width2>width1&&height2>height1){
        				if((width2/width1)>(height2/height1)){
        					$img.css("height","100%");
        					width2=width2*(height1/height2);
        					var left=(width2-width1)/2;
        					$img.css("left","-"+left+"px");
        				}else{
        					$img.css("width","100%");
        					height2=height2*(width1/width2);
        					var top=(height2-height1)/2;
        					$img.css("top","-"+top+"px");
        				}
        			}else{
        				if(width2<width1){
        				 width2=$img.width();
        				 height2=height2*(width1/width2);
        				}else{
        				 width2=width2*(height1/height2);
        				 height2=$img.height();
        				}
        				if(width1-width2>-10&&width1-width2<10){
        					var top=(height2-height1)/2;
        					$img.css("top","-"+top+"px");
        				}else if(height1-height2>-10&&height1-height2<10){
        					var left=(width2-width1)/2;
        					$img.css("left","-"+left+"px");
        				}
        			}
        		});
            	
        		 $(".hidedetail").on('mouseover', 'li', function(event) {
                     $(".contents",this).show();
                    
                 }).on('mouseout', 'li', function(event) {
                      $(".contents",this).hide();
                 });;
                
            });
        </script>
    </body>
</html>

