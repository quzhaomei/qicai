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
<title>精彩视频-汽车财经</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/fontello.css">

<link rel="stylesheet" href="css/main.css">
<style>
#videopage .artical_img {
	position: relative;
	overflow: hidden;
	height: 100%;
}

#videopage .artical_img img {
	min-height: 100%;
	min-width: 100%;
	position: relative;
}

#videopage .img img {
	width: auto;
	height: auto;
}
</style>
</head>
<body>
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

	<!-- 广告位开始 -->
	<div class="abad" style="width: 1060px; height: 100px; margin: auto;">
		<!-- 广告位 -->
		<a:output code="videopage_1" descript="精彩视频页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">



		<!-- 分栏开始 -->
		<div class="row bgwhite">
			<div id="videopage" class="col-lg-8">
				<div class="cat">
					<div class="title">
						<h1>原创视频</h1>
					</div>
					<ul class="list bxslider1">
						<!-- 6条一页 -->
						<c:forEach items="${ownVideo }" var="temp" varStatus="status">
							<c:if test="${status.index%6==0 }">
								<li class="page">
							</c:if>
							<div class="item">
								<div class="img">

									<div class="artical_img">
										<img o-width="${temp.scanImgWidth }"
											o-height="${temp.scanImgHeight }"
											src="${temp.scanImgPath }-mini" />
									</div>
									<a href="videoDetail.html?articalId=${temp.articalId }"> <i class="icon-play-circled2"></i>
									</a>
								</div>
								<div class="info">
									<a:trim length="17" content="${temp.title }"></a:trim>
								</div>
							</div>
							<c:if test="${status.index+1%6==0 }">
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>



				<div class="cat">
					<div class="title">
						<h1>试驾视频</h1>
					</div>
					<ul class="list bxslider2">
						<!-- 6条一页 -->
						<c:forEach items="${testVideo }" var="temp" varStatus="status">
							<c:if test="${status.index%6==0 }">
								<li class="page">
							</c:if>
							<div class="item">
								<div class="img">

									<div class="artical_img">
										<img o-width="${temp.scanImgWidth }"
											o-height="${temp.scanImgHeight }"
											src="${temp.scanImgPath }-mini" />
									</div>
									<a href="videodetail.html"> <i class="icon-play-circled2"></i>
									</a>
								</div>
								<div class="info">
									<a:trim length="17" content="${temp.title }"></a:trim>
								</div>
							</div>
							<c:if test="${status.index+1%6==0 }">
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>



			</div>



			<div class="col-lg-4">

				<div id="sidebar">

					<!-- 侧边栏搜索开始 -->
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
					<!-- 侧边栏搜索结束 -->



					<!-- 一周热文开始 -->
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
					<div class="abad" style="width: 100%; height: 250px;">
						<!-- 广告位 -->
						<a:output code="videopage_2" descript="精彩视频页面中部右边广告位" height="250"
							width="293" href="#" />
					</div>
					<!-- 广告位结束 -->




				</div>

			</div>


		</div>

		<!-- 分栏结束 -->

		<div class="row bgwhite">
			<!-- 广告位开始 -->
			<div class="abad"
				style="width: 100%; height: 100px;margin-bottom: 30px;">
				<a:output code="videopage_3" descript="精彩视频页面底部广告位" height="100"
					width="1060" href="#" />
			</div>
			<!-- 广告位结束 -->
		</div>

	</div>


	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {

			$('.bxslider1').bxSlider({
				speed : 750,
				auto : false,
				pause : 7000
			});

			$('.bxslider2').bxSlider({
				speed : 750,
				auto : false,
				pause : 7000
			});

			$(".hidedetail").on('mouseover', 'li', function(event) {
				$(".contents", this).show();

			}).on('mouseout', 'li', function(event) {
				$(".contents", this).hide();
			});
			;

		});
	</script>
</body>
</html>

