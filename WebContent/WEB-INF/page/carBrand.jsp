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
<title>品牌专区－汽车财经</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/fontello.css">

<link rel="stylesheet" href="css/main.css">
<style>
#famouspage .famouslist .item .img .artical_img {
	position: relative;
	overflow: hidden;
	height: 100%;
}

#famouspage .famouslist .item .img .artical_img img {
	min-height: 100%;
	min-width: 100%;
	position: relative;
}

#famouspage .famouslist .item .img  img {
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
		<a:output code="brandPage_1" descript="品牌列表页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">
		<div id="famouspage" class="row bgwhite">
			<div class="search">
				<div class="title">
					<h2>品牌检索</h2>
				</div>
				<c:if test="${key!=null }">
				<div class="alpha">
					关键词：<a href="#" class="current">${key}</a>
				</div>
				</c:if>
				<c:if test="${charIndexs!=null }">
				<div class="alpha">
					按字母检索：<a href="carBrand.html" class="${empty charIndex?"current":"" }">全部</a>
					<c:forEach  items="${charIndexs }" var="char_" >
					<a href="carBrand.html?charIndex=${char_ }" class="${charIndex==char_?"current":"" }">${char_}</a>
					</c:forEach>
				</div>
				</c:if>
			</div>

			<div class="famouslist row">
			<c:if test="${empty brandPage.param }">
				<div>无搜索结果！</div>
			</c:if>
				<c:forEach items="${brandPage.param }" var="brand">
					<div class="item">
						<div class="img ">
						<a href="carBrandDetail.html?brandId=${brand.tagId }">
							<div class="artical_img">
							<img o-width="${brand.imgWidth }"
								o-height="${brand.imgHeight }" src="${brand.imgPath }" />
								</div>
							</a>
						</div>
						<div class="name">
							${brand.tagName }<br/>
						</div>
						<div class="info">
						<a:trim length="20" content="${brand.introduce }"></a:trim>
						</div>
					</div>
				</c:forEach>

				<!-- 隐藏表单 -->
				<form action="carBrand.html" method="post" id="newsForm">
					<input type="hidden" name="charIndex" 
						value="${charIndex}" /> <input type="hidden"
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${brandPage.pageIndex }" /> <input type="hidden"
						name="pageSize" id="pageSize" value="${brandPage.pageSize }" />
				</form>
				<c:if test="${brandPage.totalPage>1 }">
				<ul class="pagination">
					<li
						<c:if test="${brandPage.pageIndex>1 }">
                        	index="${brandPage.pageIndex-1}"
                        </c:if>
						<c:if test="${brandPage.pageIndex==1 }">
                        	class="disabled"
                        </c:if>>
						<a href="javascript:void(0);"> <i class="icon-left-open-big"></i>
					</a>
					</li>
					<c:if test="${brandPage.pageIndex-1==4 }">
						<li index="1"><a href="javascript:void(0);">1</a></li>
					</c:if>
					<c:if test="${brandPage.pageIndex-1>4 }">
						<li index="1"><a href="javascript:void(0);">1</a></li>
						<li><a href="javascript:void(0);">...</a>
					</c:if>

					<c:forEach begin="1" end="${brandPage.totalPage }" var="temp">
						<c:if
							test="${(temp-brandPage.pageIndex>-4)&&(temp-brandPage.pageIndex<4) }">
							<li ${temp==brandPage.pageIndex?"class='active'":"" }
								index="${temp }"><a href="javascript:void(0);">${temp }</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${brandPage.totalPage-brandPage.pageIndex==4 }">
						<li index="${brandPage.totalPage  }"><a
							href="javascript:void(0);">${brandPage.totalPage }</a></li>
					</c:if>
					<c:if test="${brandPage.totalPage-brandPage.pageIndex>4 }">
						<li><a href="javascript:void(0);">...</a>
						<li index="${brandPage.totalPage }"><a
							href="javascript:void(0);">${brandPage.totalPage }</a></li>
					</c:if>
					<li
						<c:if test="${brandPage.pageIndex==brandPage.totalPage }">
	                        	class="disabled"
	                        </c:if>
						<c:if test="${brandPage.pageIndex!=brandPage.totalPage }">
	                        	index="${brandPage.pageIndex+1}"
	                        </c:if>>
						<a href="javascript:void(0);"> <i class="icon-right-open-big"></i>
					</a>
					</li>
				</ul>
			</c:if>


			</div>

		</div>

		<div class="row bgwhite">
			<!-- 广告位开始 -->
			<div class="abad"
				style="width: 100%; height: 100px;  margin-bottom: 30px;">
				<!-- 广告位 -->
				<a:output code="brandPage_2" descript="品牌列表页面底部广告位" height="100"
					width="1060" href="#" />
			</div>
			<!-- 广告位结束 -->
		</div>





	</div>



	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".pagination li").on("click", function() {
				var index = $(this).attr("index");
				if (index) {
					$("#pageIndex").val(index);
					$("#newsForm").submit();
				}
			});

			$(".hidedetail").on('mouseover', 'li', function(event) {
				$(".contents", this).show();

			}).on('mouseout', 'li', function(event) {
				$(".contents", this).hide();
			});

			$('.bxslider').bxSlider({
				minSlides : 3,
				maxSlides : 4,
				slideWidth : 170,
				slideMargin : 20,
				speed : 750,
				auto : true,
				pause : 6000
			});

		});
	</script>
</body>
</html>

