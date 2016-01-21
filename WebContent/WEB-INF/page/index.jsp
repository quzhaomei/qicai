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
<meta property="qc:admins" content="11605745776154752126367" />
<meta property="wb:webmaster" content="4bc06e95d2d216f6" />
<title>汽车财经</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
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


	<div id="head" class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="logo">
					<img src="images/logo.png" alt="">
				</div>
			</div>
			<div class="col-lg-9">
				<div class="login row">
					<div class="col-md-3"></div>
					<c:if test="${sessionScope.userSession==null }">
						<div class="col-md-9 text-right">
							<a href="toLogin.html">登录</a> | <a href="toRegister.html">注册</a>

						</div>
					</c:if>
					<c:if test="${sessionScope.userSession!=null }">
						<div class="col-md-9 logged text-right">
							<i class="icon-user"> </i> 欢迎您, <a href="userIndex.html">${sessionScope.userSession.nickname}</a>
							｜ <a href="#" id="logout" class="logout">退出</a>
						</div>
					</c:if>
				</div>

				<div class="nav">
					<div class="row navigation">
						<div class="col-md-8">
							<div class="mainnav">
								<c:forEach items="${menus }" varStatus="status" var="menu">
									<c:if test="${status.index!=0 }">
		                  	 	|
		                  	 </c:if>
									<a href="${menu.uri }">${menu.name }</a>
								</c:forEach>
							</div>
						</div>
						<div class="col-md-4">
							<div class="search text-right">
								 <form action="searchArtical.html" method="post" 
                                 onsubmit="if(!(document.getElementById('searchKey').value)){return false;}">
                                     <input type="text" name="key" id="searchKey" value="${key }" maxlength="20">
                                    <button type="submit"><i class="icon-search-1"></i></button>
                                    </form>
								
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>
	<div id="slidershow">
		<ul class="bxslider">
			<c:forEach items="${scrolls }" var="scroll">
				<li>
				<a href="${scroll.href }">
					<img src="${scroll.imgPath }" />
					<div class="bx-caption">
						<span>${fn:escapeXml(scroll.title) }</span>
					</div>
					</a></li>
			</c:forEach>
		</ul>
	</div>

	<div id="main" class="container">

		<!-- 置顶新闻开始 -->
		<div id="highlight">
			<div class="headline">
				<!-- 头条 -->
				<div class="artical_img">
					<img o-width="${top.artical.scanImgWidth }"
						o-height="${top.artical.scanImgHeight }"
						src="${top.artical.scanImgPath }" />
				</div>
				<div class="overlay">
					<h2>
						<a href="newDetail.html?articalId=${top.artical.articalId }">
							<a:trim length="35" content="${top.artical.title}"></a:trim>

						</a>
					</h2>
					<div class="excerpt">
						<a:trim length="95" content="${top.artical.quote}"></a:trim>
					</div>

				</div>
				<div class="label red" topId="${top.topId }">${fn:escapeXml(top.name)}</div>
			</div>

			<div class="commonnews">
				<!-- 概要 -->
				<div class="artical_img">
					<img o-width="${general.artical.scanImgWidth }"
						o-height="${general.artical.scanImgHeight }"
						src="${general.artical.scanImgPath }" />
				</div>

				<div class="overlay">
					<h2>
						<a href="newDetail.html?articalId=${general.artical.articalId }">
							<a:trim length="35" content="${general.artical.title }"></a:trim>
						</a>
					</h2>
				</div>
				<div class="label orange">${general.name }</div>
			</div>

			<div class="instantnews">
				<!-- 快讯 -->
				<div class="artical_img">
					<img o-width="${quick.artical.scanImgWidth }"
						o-height="${quick.artical.scanImgHeight }"
						src="${quick.artical.scanImgPath }" />
				</div>

				<div class="overlay">
					<h2>
						<a href="newDetail.html?articalId=${quick.artical.articalId }">
							<a:trim length="35" content="${quick.artical.title }"></a:trim>
						</a>
					</h2>
				</div>
				<div class="label green">${fn:escapeXml(quick.name) }</div>
			</div>

			<div class="original">
				<!-- 原创 -->
				<div class="artical_img">
					<img o-width="${orignal.artical.scanImgWidth }"
						o-height="${orignal.artical.scanImgHeight }"
						src="${orignal.artical.scanImgPath }" />
				</div>
				<div class="overlay">
					<h2>
						<a href="newDetail.html?articalId=${orignal.artical.articalId }">
							<a:trim length="35" content="${orignal.artical.title }"></a:trim>
							</a>
					</h2>
				</div>
				<div class="label blue">${fn:escapeXml(orignal.name) }</div>
			</div>


		</div>
		<!-- 置顶新闻结束 -->

		<!-- 滚动资讯开始 -->
		<div id="rollnews">

			<div class="title">
				滚动资讯
				<div class="tri"></div>
			</div>


			<div class="rolls">
				<c:forEach items="${topScroll }" var="temp" varStatus="status">
				<c:if test="${status.index>0 }">
					<c:if test="${(status.index-1)%3==0 }">
						<div class="roll topRoll">
							<ul>
					</c:if>
					<li><a
						href="newDetail.html?articalId=${temp.articalId }"> <a:trim
								length="16" content=" ${temp.title }"></a:trim></a></li>
					<c:if
						test="${(status.index)%3==0 || fn:length(topScroll)==status.count }">
						</ul>
					</div>
					</c:if>
					</c:if>
			</c:forEach>

			<div class="roll topRoll">
				<ul>
					<c:forEach items="${infoScrolls }" var="temp">
						<li><a href="${temp.href }"> <a:trim length="16"
									content=" ${temp.title }"></a:trim>
						</a></li>
					</c:forEach>

				</ul>
			</div>

		</div>


	</div>

	<!-- 滚动资讯结束 -->

	<!-- 分栏资讯开始 -->
	<div class="cols row">
		<div class="col-lg-8">


			<!-- 最新资讯开始 -->
			<div class="panel panel_line" id="newest">
				<h1>最新资讯</h1>
				<c:set var="newest" value="${newPage.param[0] }" />
				<div class="focus clearfix">
					<div class="img">
						<div class="artical_img">
						<a href="newDetail.html?articalId=${newest.articalId }">
							<img o-width="${newest.scanImgWidth }"
								o-height="${newest.scanImgHeight }"
								src="${newest.scanImgPath }" /><!-- -mini -->
								</a>
						</div>

					</div>
					<div class="contents">
						<h2>
							<a href="newDetail.html?articalId=${newest.articalId }"> <a:trim
									length="40" content="${newest.title}"></a:trim>
							</a>
						</h2>
						<div class="datetime">
							发表于
							<fmt:formatDate value="${newest.createDate }"
								pattern="yyyy/MM/dd HH:mm:ss" />

							来源:<a href="${newest.resource.url}">
								${fn:escapeXml(newest.resource.name) }</a>
						</div>
						<div class="text">
							<a:trim length="100" content="${newest.quote}"></a:trim>
						</div>
					</div>
				</div>
				<div class="list">
					<c:forEach begin="1" end="5" var="index">
						<c:if test="${fn:length(newPage.param)>index }">
							<c:set var="newitem" value="${newPage.param[index] }" />
							<div class="item">
								<div class="img">
									<div class="artical_img">
									<a href="newDetail.html?articalId=${newitem.articalId }">
										<img o-width="${newitem.scanImgWidth }"
											o-height="${newitem.scanImgHeight }"
											src="${newitem.scanImgPath }-mini" />
											</a>
									</div>
								</div>
								<div class="contents">
									<h3>
										<a href="newDetail.html?articalId=${newitem.articalId }">
											<a:trim length="20" content="${newitem.title}"></a:trim>

										</a>
									</h3>
									<div class="datetime">
										发表于
										<fmt:formatDate value="${newitem.createDate }"
											pattern="yyyy/MM/dd HH:mm:ss" />
									</div>
									<div class="text">
										<a:trim length="15" content="${newitem.quote}"></a:trim>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>

				</div>

				<div class="more_righttop">
					<a href="toNews.html">更多 >></a>
				</div>
			</div>
			<!-- 最新资讯结束 -->



			<!-- 精彩专题开始 -->
			<div class="panel panel_trans" id="topic">
				<h1>精彩专题</h1>
				<div id="${fn:length(tagPage)>4?"list1":"" }" class="als-container">
					<span class="als-prev"><i class="icon-left-open-big"></i></span>
					<div class="als-viewport">
						<ul class="list als-wrapper">
							<c:forEach items="${tagPage }" var="temp">
								<li class="item als-item">
									<div class="img">
										<div class="artical_img">
										<a href="topicDetail.html?tagId=${temp.tagId }">
											<img o-width="${temp.imgWidth }"
												o-height="${temp.imgHeight }"
												src="${temp.imgPath }-mini" />
												</a>
										</div>
									</div>
									<div class="contents">
										<h3>
											<a href="topicDetail.html?tagId=${temp.tagId }"> <a:trim
													length="9" content="${temp.tagName }"></a:trim>
											</a>
										</h3>
										<div class="text">
											<a:trim length="30" content="${temp.introduce }"></a:trim>
										</div>
										<div class="datetime">
											发表于
											<fmt:formatDate value="${temp.createDate }"
												pattern="yyyy/MM/dd" />
										</div>
									</div>
								</li>
							</c:forEach>


						</ul>
					</div>
					<span class="als-next"><i class="icon-right-open-big"></i></span>

				</div>


				<div class="more_righttop">
					<a href="topics.html">更多 >></a>
				</div>
			</div>
			<!-- 精彩专题结束 -->

			<!-- 活动开始 -->
			<div class="panel panel_line" id="activity">
				<h1>活动中心</h1>
				<div class="list">

					<c:forEach begin="0" end="5" var="index">
						<c:if test="${fn:length(livePage.param)>index }">
							<c:set var="newitem" value="${livePage.param[index] }" />
							<div class="item">
								<div class="img">
									<div class="artical_img">
									<a href="newDetail.html?articalId=${newitem.articalId }">
										<img o-width="${newitem.scanImgWidth }"
											o-height="${newitem.scanImgHeight }"
											src="${newitem.scanImgPath }-mini" />
											</a>
									</div>
								</div>
								<div class="contents">
									<h1>
										<a href="newDetail.html?articalId=${newitem.articalId }"> <a:trim length="35" content="${newitem.title}"></a:trim>
										</a>
									</h1>
									<div class="text">
										<a:trim length="105" content="${newitem.quote}"></a:trim>
									</div>
								</div>
								<a class="btn btn_rb"
									href="newDetail.html?articalId=${newitem.articalId }"> 查看更多
								</a>
							</div>
						</c:if>
					</c:forEach>

				</div>

				<div class="more_righttop">
					<a href="activity.html">更多 >></a>
				</div>
			</div>
			<!-- 活动结束 -->

		</div>
		<div class="col-lg-4">


			<div class="panel panel_trans panel_nopad" id="column">
				<h1>作者专栏</h1>
				<div class="list">
					<c:forEach begin="0" end="5" var="index">
						<c:if test="${fn:length(authorPage.param)>index }">
							<c:set var="newitem" value="${authorPage.param[index] }" />
							<div class="item">
								<div class="img">
									<div class="artical_img">
									<a href="newDetail.html?articalId=${newitem.articalId }">
										<img o-width="${newitem.scanImgWidth }"
											o-height="${newitem.scanImgHeight }"
											src="${newitem.scanImgPath }-mini" />
											</a>
									</div>
								</div>
								<div class="contents">
									<h3>
										<a href="newDetail.html?articalId=${newitem.articalId }">
											<a:trim length="25" content="${newitem.title }"></a:trim>
										</a>
									</h3>
									<div
										class="label
										<c:choose>
											<c:when test="${index==0}">
												red
											</c:when>
											<c:when test="${index==1}">
												blue
											</c:when>
											<c:when test="${index==2}">
												red
											</c:when>
											<c:when test="${index==3}">
												orange
											</c:when>
											<c:when test="${index==4}">
												blue
											</c:when>
										</c:choose>
									   ">
									 <a href="authorIndex.html?authorId=${newitem.author.authorId }"> ${fn:escapeXml(newitem.author.penName)}</a>  
									   </div>
									<div class="datetime">
										<fmt:formatDate value="${newitem.createDate }"
											pattern="yyyy/MM/dd" />
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>

				</div>

				<div class="more_righttop">
					<a href="authors.html">更多 >></a>
				</div>
			</div>


			<div class="abad"
				style="width: 335px; height: 300px;padding-top:0px;">
				<!-- 广告位 -->
				<a:output code="mainpage_1" descript="首页品牌上方广告位" height="300"
					width="335" href="#" />
			</div>

			<div class="panel panel_trans panel_nopad" id="brand">
				<h1>品牌专区</h1>
				<div class="list">
					<c:forEach items="${brands }" var="brand">
						<div class="item">
							<div class="logo img" style="overflow:hidden;">
								<div class="artical_img">
								<a href="carBrandDetail.html?brandId=${brand.tagId }">
									<img o-width="${brand.imgWidth }"
										o-height="${brand.imgHeight }" src="${brand.imgPath }-mini" />
										</a>
								</div>
							</div>
							<div class="name">
								<a href="carBrandDetail.html?brandId=${brand.tagId }">${fn:escapeXml(brand.tagName) }</a>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="more_righttop">
					<a href="carBrand.html">更多 >></a>
				</div>



			</div>
		</div>

		<!-- 分栏资讯结束 -->
	</div>

	<!-- 名人开始 -->
	<div class="panel panel_trans clearfix" id="famous">
		<h1>对话</h1>
		<div class="list">

			<c:forEach items="${famousPage}" var="temp">
				<div class="item">
					<a href="famousDetail.html?peopleId=${temp.peopleId }">
						<div class="img">
							<div class="artical_img">
								<img o-width="${temp.imgWidth }" o-height="${temp.imgHeight }"
									src="${temp.imgPath }-mini" />
							</div>
						</div>
						<div class="overlay">
						<a:trim length="6" content="${temp.job }"></a:trim>
							 <br>
							 ${fn:escapeXml(temp.name) }
						</div>
					</a>
				</div>
			</c:forEach>

		</div>

		<div class="more_righttop">
			<a href="toTalks.html">更多 >></a>
		</div>
	</div>
	<!-- 名人结束 -->

	<!-- 封面秀开始 -->
	<div class="panel panel_nopad panel_trans" id="covershow">
		<img src="images/title_mag.png" alt="">
		<div class="panel">

			<div id="${fn:length(showPage.param)>6?"list2":"" }" class="als-container">
				<span class="als-prev"><i class="icon-left-open-big"></i></span>
				<div class="als-viewport">
					<ul class="list als-wrapper">
						<c:forEach items="${showPage.param }" var="temp">
							<li class="item als-item">
								<div class="img">
									<div class="artical_img">
									<a href="showDetail.html?articalId=${temp.articalId }">
										<img o-width="${temp.scanImgWidth }"
											o-height="${temp.scanImgHeight }"
											src="${temp.scanImgPath }-mini" />
											</a>
									</div>
								</div>
								<div class="name">
									<a href="showDetail.html?articalId=${temp.articalId }">${temp.title
										}</a>
								</div>
							</li>
						</c:forEach>

					</ul>
				</div>
				<span class="als-next"><i class="icon-right-open-big"></i></span>

			</div>
			<div class="more_righttop">
				<a href="show.html">更多 &gt;&gt;</a>
			</div>
		</div>
	</div>

	<!-- 封面秀结束 -->

	<!-- 广告位开始 -->
	<div class="abad" style="width: 1060px; height: 100px;">
		<!-- 广告位 -->
		<a:output code="mainpage_2" descript="首页中部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->

	<div class="cols row">
		<div class="col-lg-8">

			<!-- 经销商开始 -->
			<div class="panel panel_line" id="merchant">
				<h1>经销商</h1>
				<div class="clearfix">
					<c:forEach items="${merchantPage }" var="temp" varStatus="status">
						<c:if test="${status.count==1 }">
							<div class="focus">
								<div class="img">
									<div class="artical_img">
									<a href="newDetail.html?articalId=${temp.articalId }">
										<img o-width="${temp.scanImgWidth }"
											o-height="${temp.scanImgHeight }"
											src="${temp.scanImgPath }-mini" />
											</a>
									</div>
								</div>
								<div class="contents">
									<h2>
										<a href="newDetail.html?articalId=${temp.articalId }"> <a:trim
												length="30" content="${temp.title }"></a:trim>

										</a>
									</h2>
									<div class="text">
										<a:trim length="140" content="${temp.quote }"></a:trim>
									</div>
								</div>
							</div>
							<div class="list">
						</c:if>
						<c:if test="${status.count!=1 }">
							<div class="item">
								<div class="img">
									<div class="artical_img">
									<a href="newDetail.html?articalId=${temp.articalId }">
										<img o-width="${temp.scanImgWidth }"
											o-height="${temp.scanImgHeight }"
											src="${temp.scanImgPath }-mini" />
											</a>
									</div>
								</div>
								<div class="contents">
									<h3>
										<a href="newDetail.html?articalId=${temp.articalId }"> <a:trim
												length="28" content="${temp.title }"></a:trim>
										</a>
									</h3>
									<div class="text">
										<a:trim length="30" content="${temp.quote }"></a:trim>
									</div>

								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>

			</div>
			<div class="more_righttop">
				<a href="merchant.html">更多 >></a>
			</div>
		</div>
		<!-- 经销商结束 -->

		<!-- 视频开始 -->
		<div class="panel panel_line" id="video">
			<h1>精彩视频</h1>
			<div class="contents">
				<c:set var="firseVideo" value="${videoPage.param[0] }"></c:set>
				<div class="video">
					<video controls>
						<source src="${firseVideo.video.path }" type="video/mp4">                               
						
					</video>
				</div>
				<div class="thumbs list">
					<c:forEach items="${videoPage.param }" var="temp">
						<div class="item" data-url="${temp.video.path }">
							<div class="img">
								<div class="artical_img">
									<img o-width="${temp.scanImgWidth }"
										o-height="${temp.scanImgHeight }"
										src="${temp.scanImgPath }-mini" />
								</div>
								<i class="icon-play-circled2"></i>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>
			<div class="more_righttop">
				<a href="video.html">更多 >></a>
			</div>

		</div>

		<!-- 视频结束 -->


	</div>
	<div class="col-lg-4">
		<!-- 热门文章开始 -->
		<div class="panel panel_trans" id="hot">
			<h1 class="underline">热门文章</h1>
			<div class="contents">
				<c:forEach items="${hotPage }" var="temp" varStatus="status">
					<div class="item">
						<span class="digi ${status.count<=3?"red":"graygreen" }">
							${status.count } </span> <a
							href="newDetail.html?articalId=${temp.articalId }"> <a:trim
								length="15" content="${temp.title }"></a:trim>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- 热门文章结束 -->


		<!-- sns开始 -->
		<div class="panel panel_trans" id="sns">
			<h1 class="underline">关注汽车财经</h1>
			<div class="list">
				<div class="item">

					<span> <img src="images/wechat.png" alt="">
					</span> 微信号: <a href="">Autofortune2014</a>
				</div>

				<div class="item">

					<span> <img src="images/weibo.png" alt="">
					</span> 新浪微博<a href="">@汽车财经</a>
				</div>

				<div class="item">

					<span> <img src="images/tengxun.png" alt="">
					</span> 腾讯微博<a href="">@汽车财经</a>
				</div>
			</div>
		</div>

		<!-- sns结束 -->

		<!-- 广告位开始 -->
		<div class="abad" style="width: 335px; height: 300px;">
			<a:output code="mainpage_3" descript="首页右下角广告位" height="300"
				width="335" href="#" />
		</div>
		<!-- 广告位结束 -->

	</div>

	</div>

	<!-- 分栏资讯2结束 -->



	<!-- 广告位开始 -->
	<div class="abad" style="width: 1060px; height: 100px;">
		<a:output code="mainpage_4" descript="首页底部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->

	<!-- 友情链接开始 -->
	<div class="panel panel_line" id="links">
		<h2>
			<span>友情链接</span>
		</h2>
		<div class="contents">
			<c:forEach items="${hrefs }" var="href" varStatus="status">
			${status.index!=0?" | ":"" }
			<a href="${href.href }">${fn:escapeXml(href.name) }</a>
			</c:forEach>
		</div>


	</div>
	<!-- 友情链接结束 -->

	<!-- 底部信息开始 -->
	<div class="panel" id="info">
		<div class="sec sec1">
			第一家汽车财经高端媒体<br> 首家汽车财经上市公司
		</div>
		<div class="sec sec2">
			<img src="images/qrcode_web.png" alt="">
			<h2>汽车财经网</h2>
			<br> <span> 关注我们<br> <strong>官方微博</strong>
			</span>
		</div>
		<div class="sec sec3">
			<img src="images/qrcode_wechat.png" alt="">
			<h2>
				微信号:<br> <span>Autofortune2014</span>
			</h2>
			<br> <span> 关注我们<br> <strong>官方微博</strong>
			</span>
		</div>
		<div class="sec sec4">
			021-39007315<br> <span> 24小时服务热线 </span>
		</div>
	</div>
	<!-- 底部信息结束 -->


	</div>

	<div id="footer">
		<div class="container">
			<div class="logo">
				<img src="images/logofooter.png" alt="">
			</div>

			<div class="contents">
				<div class="links">
					<a href="introduce.html#aboutus">关于我们</a> | <a href="introduce.html#contact">联系我们</a> | <a href="introduce.html#reveal">新闻爆料</a> | <a
						href="introduce.html#joinus">加入我们</a>
				</div>
				<div class="copyright">
				<% Date date=new Date();request.setAttribute("date", date);%>
					Copyright © <fmt:formatDate value="${date }" pattern="yyyy"/> <a href="">auto-biz.cn</a>
				</div>
			</div>

		</div>

	</div>
	<span class="statistic">
	<script language="javascript" type="text/javascript" src="http://js.users.51.la/18666544.js"></script>
<noscript><a href="http://www.51.la/?18666544" target="_blank">
	<img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" 
	src="http://img.users.51.la/18666544.asp" style="border:none" /></a>
	</noscript>
	</span>
	<script src="js/vendor/jquery-1.11.3.min.js"></script>
	<script src="js/vendor/jquery.als-1.2.min.js"></script>
	<script src="js/vendor/jquery.bxslider.min.js"></script>
	<script src="js/vendor/jquery.vticker.min.js"></script>
	<script src="js/layer/layer.js"></script>
	<!-- layer.msg -->
	<script src="js/main.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.bxslider').bxSlider({
				mode : 'fade',
				speed : 750,
				auto : true,
				pause : 6000
			});

			$('.topRoll').each(function() {
				$(this).vTicker();
			});

			$("#list1").als({
				visible_items : 4,
				scrolling_items : 1,
				orientation : "horizontal",
				circular : "yes",
				autoscroll : "yes",
				interval : 4000,
				direction : "left",
				start_from : 1
			});

			$("#list2").als({
				visible_items : 6,
				scrolling_items : 1,
				orientation : "horizontal",
				circular : "yes",
				autoscroll : "yes",
				interval : 4000,
				direction : "left",
				start_from : 1
			});

			$("#famous").on('mouseover', '.item', function(event) {
				$('.overlay', this).show();
			}).on('mouseout', '.item', function(event) {
				$('.overlay', this).hide();
			});
			$("#video").on('click', '.item', function(event) {
				var videoFile = $(this).data('url');
				$('#video video source').attr('src', videoFile);
				$("#video video")[0].load();
			});

		});
	</script>
</body>
</html>

