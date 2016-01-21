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
<title>活动－汽车财经</title>
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
		<a:output code="activityspage_1" descript="活动页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">



		<!-- 分栏开始 -->
		<div class="row bgwhite">
			<div class="col-lg-12">
				<c:choose>
					<c:when
						test="${newPage.param==null || fn:length(newPage.param)==0 }">
						<div>暂无活动</div>
					</c:when>
					<c:otherwise>
						<c:set var="news" value="${newPage.param[0]}"></c:set>
						<div id="eventspage">
							<!-- 最近的一条 -->
							<div class="latest">
								<div class="img artical_img">
								<a href="newDetail.html?articalId=${news.articalId }">
									<img o-width="${news.scanImgWidth }"
										o-height="${news.scanImgHeight }" src="${news.scanImgPath }" />
										</a>
								</div>
								<div class="contents">
									<h2>
										<a:trim length="30" content="${news.title }" />
									</h2>
									<div class="text">
										<a:trim length="30" content="${news.quote }" />
									</div>
									<div class="more">
										<a href="newDetail.html?articalId=${news.articalId }">【查看详情】</a>
									</div>
								</div>
							</div>
							<!-- 最近的一条 -->

							<!-- 最近的四条 -->
							<div class="lists">
								<!-- 排除第一个 -->
							<c:forEach	items="${newPage.param}" var="news" varStatus="status">
								<c:if test="${status.index!=0 }">
									<div class="item">
										<div class="img artical_img">
										<a href="newDetail.html?articalId=${news.articalId }">
										<img o-width="${news.scanImgWidth }"
											o-height="${news.scanImgHeight }" src="${news.scanImgPath }" />
											</a>
											</div>
										<div class="contents">
											<h2><a:trim length="30" content="${news.title }" /></h2>
											<div class="text"><a:trim length="50" content="${news.quote }" />
											</div>
											<div class="more">
												<a href="newDetail.html?articalId=${news.articalId }">【查看详情】</a>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
							</div>
							<div class="loadmore">
								<form action="activity.html" method="post" id="dataForm">
								<!-- 分页数据 -->
									<input type="hidden" name="pageIndex" value="${pageIndex+1 }">
									<input type="hidden" name="pageSize" value="${pageSize }">
									<input type="hidden" name="operator" value="ajax">
									<c:if test="${pageIndex<newPage.totalPage }">
									<button class="btn" type="button" id="dataSubmit"
									 totalPage="${newPage.totalPage }">查看更多</button>
									 </c:if>
								</form>
							</div>

						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

		<div class="row bgwhite">
			<!-- 广告位开始 -->
			<div class="abad"
				style="width: 100%; height: 100px;  margin-bottom: 30px;">
				<!-- 广告位 -->
				<a:output code="activitypage_2" descript="活动页面底部广告位" height="100"
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

			$("#dataSubmit").on("click",function(){
				var pageIndex=$("#dataForm input[name='pageIndex']").val();
				var totalPage=$(this).attr("totalPage");
				if(parseInt(pageIndex,"10")>parseInt(totalPage,"10")){
					layer.msg("没有更多活动了！");
					return;
				}
				var param=$("#dataForm").toData();
				var action=$("#dataForm")[0].action;
				$.post(action,param,function(json){
					if(json.status==1){
						//初始化下一页
						$("#dataForm input[name='pageIndex']").val(parseInt(json.data.pageIndex,"10")+1);
						//如果是最后一页
						if(json.data.pageIndex==json.data.totalPage){
							layer.msg("没有更多活动了！");
							$("#dataSubmit").remove();
						}
						
						var param=$("#eventspage .lists");
						$(json.data.param).each(function(){							
							var $div=$("<div>").addClass("item");
							var $div1=$("<div>").addClass("img artical_img");
							var $img1=$("<img>").attr("o-width",this.scanImgWidth)
								.attr("o-height",this.scanImgHeight).attr("src",this.scanImgPath+"-mini");
							$div1.append($img1);
							$div.append($div1);

							var $div2=$("<div>").addClass("contents");
							$div2.append($("<h2>").text(this.title));
							$div2.append($("<div>").addClass("text").text(this.quote));
							$div2.append($("<div>").addClass("more").append(
									$("<a>").attr("href","newDetail.html?articalId="+this.articalId).text("【查看详情】")
								));
							
							$div.append($div2);
							param.append($div);
							initImg($div1);
						});
						
						
					}
				},"json");
			})
		});
	</script>
</body>
</html>

