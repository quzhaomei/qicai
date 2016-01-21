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
<title>${artical.title }-汽车财经</title>
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
		<a:output code="detailpage_1" descript="资讯详细页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">



		<!-- 分栏开始 -->
		<div class="row bgwhite">
			<div class="${artical.type==5?'col-lg-12':'col-lg-8' }">
				<!-- 文章详情开始 -->
				<div class="detail" id="article">
					<h1>${fn:escapeXml(artical.title) }</h1>
					<div class="source">
						<span class="author"> <a href="authorIndex.html?authorId=${artical.author.authorId }">${artical.author.penName}</a>
						</span> <span class="datetime">发表于 <fmt:formatDate
								value="${artical.createDate }" pattern="yyyy/MM/dd HH:mm:ss" />
						</span> <span class="topic"> <a href="${artical.resource.url }">${fn:escapeXml(artical.resource.name)}</a>
						</span>
					</div>
					<div class="contents">${artical.content }</div>


					<div class="footer">
						<div class="trackback">
							原创文章，转载请注明来源及本文链接：<br>
							<script>
								document.write(window.location.href);
							</script>
							<!-- http://wwwauto-biz.cn/news/show/id/1380.html -->
						</div>

						<div class="share row">
							<div class="sns col-sm-7">
								<!-- JiaThis Button BEGIN -->
								<div class="jiathis_style_32x32">
									<a class="jiathis_button_qzone"></a> <a
										class="jiathis_button_tsina"></a> <a
										class="jiathis_button_tqq"></a> <a
										class="jiathis_button_weixin"></a> <a
										class="jiathis_button_kaixin001"></a> <a
										href="http://www.jiathis.com/share"
										class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis"
										target="_blank"></a> <a class="jiathis_counter_style"></a>
								</div>
								<script type="text/javascript">
									var jiathis_config = {
										summary : "",
										shortUrl : false,
										hideMore : true
									}
								</script>
								<script type="text/javascript"
									src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
								<!-- JiaThis Button END -->
							</div>
							<div class="fav col-sm-5">
								<c:set var="isRight" value="right_${artical.articalId }"></c:set>
								<span class="up artical-up" articalId="${artical.articalId }"><i
									class="icon-thumbs-up ${cookie[isRight]!=null?'clicked':'' }"></i>
									顶 <strong> ${artical.rightNum }</strong></span>
								<c:set var="isWrong" value="wrong_${artical.articalId }"></c:set>
								<span class="down artical-down"
									articalId="${artical.articalId }"><i
									class="icon-thumbs-down-1 ${cookie[isWrong]!=null?'clicked':'' }"></i>
									踩 <strong>${artical.wrongNum }</strong></span> <span
									class="addtofav storeArtical" articalId="${artical.articalId }"><i
									class="icon-star-empty ${store!=null?"clicked":"" }"></i> 收藏</span>
							</div>
						</div>
					</div>
				</div>
				<!-- 文章详情结束 -->

				<!-- 相关文章开始 -->
				<div id="relative" style=" ${fn:length(nearArtical)==0?"display:none":""}">
					<div class="mainhead title">
						<h1>相关阅读</h1>
					</div>
					<div class="lists row">
						<c:forEach items="${nearArtical }" var="temp">

							<div class="item col-sm-4">
								<div class="img">
									<div class="artical_img">
										<img o-width="${temp.scanImgWidth }"
											o-height="${temp.scanImgHeight }"
											src="${temp.scanImgPath }-mini" />
									</div>
								</div>
								<div class="contents">
									<h2>
										<a:trim length="12" content="${temp.title }"></a:trim>
									</h2>
									<div class="text">
										<a:trim length="25" content="${temp.quote }"></a:trim>
									</div>
									<div class="more">
										<a href="newDetail.html?articalId=${temp.articalId }">【详细】</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				</div>

					<!-- 相关文章结束 -->




				<div class="col-lg-4" style="${artical.type==5?"display:none;":""}">
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
											<a href="newDetail.html?articalId=${hot.articalId }"> <a:trim
													length="16" content="${hot.title }"></a:trim>
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
                            </c:if>>
							<div class="title">
								<span></span>
								<h2>一周热文</h2>
							</div>

							<ul class="list">
								<c:forEach items="${hotList }" var="hot" varStatus="status">
									<li class="item">
										<div>
											<span class="digi ${status.count<=3?'top':'' }">
												${status.count } </span> 
										<a href="newDetail.html?articalId=${hot.articalId }"> 
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
											<a:trim length="30" content="${hot.quote }"></a:trim>
										</div>
										</div>
									</li>
						</c:forEach>

						</ul>

					</div>

					<!-- 一周热文结束 -->


					<!-- 广告位开始 -->
					<div class="abad" style="width: 100%; height: 250px; ">
						<a:output code="detailpage_2" descript="资讯列表页面中部右边广告位" height="250"
							width="296" href="#" />
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
				<!-- 广告位 -->
				<a:output code="detailpage_3" descript="资讯详细页面底部广告位" height="100"
					width="1060" href="#" />
			</div>
			<!-- 广告位结束 -->
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".hidedetail").on('mouseover', 'li', function(event) {
				$(".contents", this).show();

			}).on('mouseout', 'li', function(event) {
				$(".contents", this).hide();
			});
		});
	</script>
</body>
</html>

