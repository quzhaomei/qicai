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
<title>x封面秀-汽车财经</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/fontello.css">

<link rel="stylesheet" href="css/main.css">
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
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

	<!-- 广告位开始 -->
	<div class="abad" style="width: 1060px; height: 100px; margin: auto;">
		<!-- 广告位 -->
		<a:output code="showpage_1" descript="封面秀页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">

		<div class="row bgwhite">
			<div class="col-lg-8">
				<!-- 资讯列表开始 -->
				<div id="coverpage">
				<c:forEach items="${newPage.param }" var="news">
				<div class="item">
						<div class="contents">
							<h2> 
							<a href="showDetail.html?articalId=${news.articalId }" >
							<a:trim length="20" content="${news.title }"></a:trim>
							</a>
							</h2>
							<div class="text">
							  <a:trim length="150" content="${news.quote }"></a:trim>
							</div>
							<div class="mis">
								<div class="more">
									<a href="showDetail.html?articalId=${news.articalId }" class="btn">立刻下载</a>
								</div>
							</div>
						</div>
						<div class="img">
                           <div class="artical_img">
                           <a href="showDetail.html?articalId=${news.articalId }">
										<img o-width="${news.scanImgWidth }" o-height="${news.scanImgHeight }"
											src="${news.scanImgPath }-mini" />
											</a>
								</div>
                            </div>
					</div>
				
				</c:forEach>
					

				 <!-- 隐藏表单 -->
					<form action="show.html" method="post" id="newsForm">
					<input type="hidden" name="pageIndex" id="pageIndex" value="${newPage.pageIndex }"/>
					<input type="hidden" name="pageSize" id="pageSize" value="${newPage.pageSize }"/>
					</form>
					 <ul class="pagination">
                        <li  
                        <c:if test="${newPage.pageIndex>1 }">
                        	index="${pageIndex-1}"
                        </c:if>
                        <c:if test="${newPage.pageIndex==1 }">
                        	class="disabled"
                        </c:if>
                        
                        >
                          <a href="javascript:void(0);">
                            <i class="icon-left-open-big"></i>
                          </a>
                        </li>
                          <c:if test="${newPage.pageIndex-1==4 }">
	                         	 <li index="1">
	                           <a href="javascript:void(0);" >1</a>
	                           </li>
	                         </c:if>
	                         <c:if test="${newPage.pageIndex-1>4 }">
	                         <li index="1">
	                           <a href="javascript:void(0);" >1</a>
	                           </li>
	                         <li>
	                           <a href="javascript:void(0);">...</a>
	                         </c:if>
	                         
                        <c:forEach begin="1" end="${newPage.totalPage }" var="temp">
	                        <c:if test="${(temp-newPage.pageIndex>-4)&&(temp-newPage.pageIndex<4) }">
	                           <li ${temp==newPage.pageIndex?"class='active'":"" } index="${temp }">
	                           <a href="javascript:void(0);">${temp }</a>
	                           </li>
	                         </c:if>
                        </c:forEach>
                        
                         <c:if test="${newPage.totalPage-newPage.pageIndex==4 }">
	                         	 <li index="${newPage.totalPage  }">
	                           <a href="javascript:void(0);">${newPage.totalPage }</a>
	                           </li>
	                         </c:if>
	                         <c:if test="${newPage.totalPage-newPage.pageIndex>4 }">
	                         <li >
	                           <a href="javascript:void(0);">...</a>
	                         	 <li index="${newPage.totalPage }">
	                           <a href="javascript:void(0);" >${newPage.totalPage }</a>
	                           </li>
	                         </c:if>
                        <li 
	                        <c:if test="${newPage.pageIndex==newPage.totalPage }">
	                        	class="disabled"
	                        </c:if>
	                        <c:if test="${newPage.pageIndex!=newPage.totalPage }">
	                        	index="${pageIndex+1}"
	                        </c:if>
                        >
                          <a href="javascript:void(0);">
                            <i class="icon-right-open-big"></i>
                          </a>
                        </li>
                     </ul>
				</div>
				<!-- 资讯列表结束 -->

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


					<!-- 猜你喜欢开始 -->
					<div class="reco sec hidedetail coverside">
						<div class="title">
							<span></span>
							<h2>猜你喜欢</h2>
						</div>
						<ul class="list">
						<c:forEach items="${hotList }" var="news">
							<li class="item">
								<div class="img">
                           <div class="artical_img">
                           <a href="showDetail.html?articalId=${news.articalId }" style="padding:0 0;">
										<img o-width="${news.scanImgWidth }" o-height="${news.scanImgHeight }"
											src="${news.scanImgPath }-mini" />
											</a>
								</div>
                            </div>
								<h3><a:trim length="7" content="${news.title }"></a:trim></h3>
								<div class="more">
									<a href="showDetail.html?articalId=${news.articalId }">立刻下载</a>
								</div>

							</li>
						</c:forEach>
						</ul>

					</div>

					<!-- 猜你喜欢结束 -->



					<!-- 广告位开始 -->
					<div class="abad"
						style="width: 100%; height: 250px;">
						<!-- 广告位 -->
		<a:output code="showpage_2" descript="封面秀页面中部广告位" height="250"
			width="293" href="#" />
					</div>
					<!-- 广告位结束 -->

				</div>

			</div>

		</div>

		<div class="row bgwhite">
			<!-- 广告位开始 -->
			<div class="abad"
				style="width: 100%; height: 100px;  margin-bottom: 30px;">
				<!-- 广告位 -->
				<a:output code="showpage_3" descript="封面秀页面底部广告位" height="100"
					width="1060" href="#" />
			</div>
			<!-- 广告位结束 -->
		</div>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
	  $(document).ready(function() 
	            {
	                $(".hidedetail").on('mouseover', 'li', function(event) {
	                    $(".contents",this).show();
	                   
	                }).on('mouseout', 'li', function(event) {
	                     $(".contents",this).hide();
	                });;
	                
	            });
	</script>
</body>
</html>

