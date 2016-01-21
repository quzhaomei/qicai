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
<title>封面秀-汽车财经</title>
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
		<a:output code="showdetail_1" descript="封面秀详细页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">
		<div class="row bgwhite">
			<div class="col-lg-8">
				<!-- 杂志详情开始 -->
				<div id="coverpage">
					<div class="item detail clearfix">

						<div class="contents">
							<h2>  <a:trim length="16" content="${artical.title }"></a:trim></h2>
							<div class="text">
								${artical.content }
							</div>
							<div class="more">
								<a href="${artical.sourcePath }" class="btn" target="_blank">立刻下载</a>
							</div>


						</div>
						<div class="img">
							 <div class="artical_img">
										<img o-width="${artical.scanImgWidth }" o-height="${artical.scanImgHeight }"
											src="${artical.scanImgPath }" />
								</div>
						</div>

					</div>


				</div>
				<!-- 杂志详情结束 -->



				<!-- 热门新闻开始 -->
				<div id="relative">
					<div class="mainhead title">
						<h1>热门文章</h1>
					</div>
					<div class="lists row">
					<c:forEach items="${hotList }" var="news">
						<div class="item col-sm-4">
							<div class="img">
								<div class="artical_img">
								<a href="newDetail.html?articalId=${news.articalId }" style="padding:0 0;">
										<img o-width="${news.scanImgWidth }" o-height="${news.scanImgHeight }"
											src="${news.scanImgPath }-mini" />
											</a>
								</div>
							</div>
							<div class="contents">
								<h2>
								<a href="newDetail.html?articalId=${news.articalId }" style="padding:0 0;">
								<a:trim length="12" content="${news.title }"></a:trim>
								</a></h2>
								<div class="text"><a:trim length="30" content="${news.quote }"></a:trim></div>
								<div class="more">
									<a href="newDetail.html?articalId=${news.articalId }">【详细】</a>
								</div>
							</div>
						</div>
					
					</c:forEach>

					</div>

				</div>

				<!-- 热门新闻结束 -->
			</div>



			<div class="col-lg-4">

				<div id="sidebar">




					<!-- 猜你喜欢开始 -->
					<div class="reco sec hidedetail coverside">
						<div class="title">
							<span></span>
							<h2>猜你喜欢</h2>
						</div>
						<ul class="list">
							<c:forEach items="${newPage.param }" var="news">
								<li class="item">
									<div class="img">
										<div class="artical_img">
										<a href="showDetail.html?articalId=${news.articalId }" style="padding:0 0;">
										<img o-width="${news.scanImgWidth }" o-height="${news.scanImgHeight }"
											src="${news.scanImgPath }-mini" />
											</a>
								</div>
									</div>
									<h3><a:trim length="8" content="${news.title }"></a:trim></h3>
									<div class="more">
										<a href="showDetail.html?articalId=${news.articalId }">立刻下载</a>
									</div>
								</li>
							</c:forEach>

						</ul>
					</div>

					<!-- 猜你喜欢结束 -->



					<div class="row bgwhite">

						<!-- 广告位开始 -->
						<div class="abad" style="width: 100%; height: 250px;">
							<a:output code="showdetail_2" descript="封面秀详细页面中部右边广告位"
								height="250" width="296" href="#" />
						</div>
						<!-- 广告位结束 -->

					</div>

				</div>

			</div>

		</div>
		<!-- 分栏结束 -->
		<div class="row bgwhite">
			<!-- 广告位开始 -->
			<div class="abad"
				style="width: 100%; height: 100px;margin-bottom: 30px;">
				<!-- 广告位 -->
				<a:output code="showdetail_3" descript="封面秀详细页面底部广告位" height="100"
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

