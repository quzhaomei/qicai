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
<title>${author.penName }个人中心－汽车财经</title>
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
		<a:output code="userIndexpage_1" descript="个人中心页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">

		<div class="row bgwhite" id="userpage">
			<div class="col-md-3">
				<div id="userinfo" class="author">
					<div class="avatar">
						<div class="img">
							<img style="width:100%;height:100%;" src="${author.photoPath }" />
						</div>
						<div class="name">
							${user.nickname }
							<c:if test="${author!=null }">
								<br />(${author.penName })
                          </c:if>
						</div>
					</div>
					<div class="info">
						<c:choose>
							<c:when test="${author.introduce!=null||author.introduce=='' }">
                        		${fn:escapeXml(author.introduce) }
                        	</c:when>
							<c:otherwise>
                     	     TA还没有个人介绍
                        	</c:otherwise>
						</c:choose>
					</div>
					<div class="tabs">
						<div class="tab current" id="articles">TA的文章</div>
						<div class="tab" id="favorites">TA的收藏</div>
						<div class="tab" id="comments">TA的评论</div>
					</div>

				</div>
			</div>
			<div class="col-md-9">
				<div class="tabcontents">
					<div class="articles tabcontent lists active">
						<c:forEach items="${newPage.param }" var="news" varStatus="status">
							<div class="item ${status.count%2==0?"alt":"" }">
								<div class="contents">
									<h2>
									<a href="newDetail.html?articalId=${news.articalId }">
										<a:trim length="30" content="${news.title }"></a:trim>
										</a>
									</h2>
									<div class="img">
										<div class="artical_img">
											<img o-width="${news.scanImgWidth }"
												o-height="${news.scanImgHeight }"
												src="${news.scanImgPath }-mini" />
										</div>
									</div>
									<div class="datetime">
										<fmt:formatDate value="${news.createDate }"
											pattern="yyyy年MM月dd日  hh:mm" />
									</div>
									<div class="text">
										<a:trim length="150" content="${news.quote }"></a:trim>
									</div>
									<div class="more">
										<a href="newDetail.html?articalId=${news.articalId }">&gt;&gt;查看全文</a>
									</div>
								</div>

							</div>
						</c:forEach>
						<!-- 隐藏表单 -->
						<form action="authorIndex.html" method="post" id="newsForm">
							<input type="hidden" name="pageIndex" id="pageIndex"
								value="${newPage.pageIndex }" /> <input type="hidden"
								name="pageSize" id="pageSize" value="${newPage.pageSize }" />
								<input type="hidden"
								name="authorId" id="authorId" value="${author.authorId }" />
						</form>
						<ul class="pagination">
							<li
								<c:if test="${newPage.pageIndex>1 }">
                        	index="${pageIndex-1}"
                        </c:if>
								<c:if test="${newPage.pageIndex==1 }">
                        	class="disabled"
                        </c:if>>
								<a href="javascript:void(0);"> <i class="icon-left-open-big"></i>
							</a>
							</li>
							<c:if test="${newPage.pageIndex-1==4 }">
								<li index="1"><a href="javascript:void(0);">1</a></li>
							</c:if>
							<c:if test="${newPage.pageIndex-1>4 }">
								<li index="1"><a href="javascript:void(0);">1</a></li>
								<li><a href="javascript:void(0);">...</a>
							</c:if>

							<c:forEach begin="1" end="${newPage.totalPage }" var="temp">
								<c:if
									test="${(temp-newPage.pageIndex>-4)&&(temp-newPage.pageIndex<4) }">
									<li ${temp==newPage.pageIndex?"class='active'":"" }
										index="${temp }"><a href="javascript:void(0);">${temp
											}</a></li>
								</c:if>
							</c:forEach>

							<c:if test="${newPage.totalPage-newPage.pageIndex==4 }">
								<li index="${newPage.totalPage  }"><a href="javascript:void(0);"
									>${newPage.totalPage }</a></li>
							</c:if>
							<c:if test="${newPage.totalPage-newPage.pageIndex>4 }">
								<li><a href="javascript:void(0);">...</a>
								<li index="${newPage.totalPage }"><a
									href="javascript:void(0);">${newPage.totalPage }</a></li>
							</c:if>
							<li
								<c:if test="${newPage.pageIndex==newPage.totalPage }">
	                        	class="disabled"
	                        </c:if>
								<c:if test="${newPage.pageIndex!=newPage.totalPage }">
	                        	index="${newPage.pageIndex+1}"
	                        </c:if>>
								<a href="javascript:void(0);"> <i
									class="icon-right-open-big"></i>
							</a>
							</li>
						</ul>
					</div>


					<div class="favorites tabcontent lists">TA还没有收藏</div>

					<div class="comments tabcontent lists">TA还没有评论</div>

				</div>









			</div>
		</div>


		<div class="row bgwhite">
			<!-- 广告位开始 -->
			<div class="abad"
				style="width: 100%; height: 100px;  margin-bottom: 30px;">
				<!-- 广告位 -->
				<a:output code="userIndexpage_2" descript="个人中心页面底部广告位" height="100"
					width="1060" href="#" />
			</div>
			<!-- 广告位结束 -->
		</div>





	</div>



	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(
				function() {
					$(".tab").on(
							'click',
							function(event) {
								$(this).addClass('current').siblings('.tab')
										.removeClass('current');
								var target = $(this)[0].id;
								$(".tabcontents ." + target).addClass('active')
										.siblings('.tabcontent').removeClass(
												'active');
							});
				});
	</script>
</body>
</html>

