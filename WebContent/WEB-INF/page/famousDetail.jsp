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
<title>${people.name }－汽车财经</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/fontello.css">

<link rel="stylesheet" href="css/main.css">
<style>
.img .artical_img {
	position: relative;
	overflow: hidden;
	height: 100%;
}

.img .artical_img img {
	min-height: 100%;
	min-width: 100%;
	position: relative;
}

.img  img {
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
		<a:output code="famousDetail_1" descript="名人详细页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->
	<jsp:include page="/menus.html"></jsp:include>



	<div id="main" class="container nohome">
		<div id="famousdetail" class="row bgwhite">


			<div class="basicinfo clearfix">
				<span class="img">
					<div class="artical_img">
						<img o-width="${people.imgWidth }" o-height="${people.imgHeight }"
							src="${people.imgPath }" />
					</div>
				</span>
				<p class="basic">
					<span class="title name">${people.name }
					<c:if test="${people.ename!=null }">
					（${people.ename }）</span>
					</c:if>
					 <br>
					中文名：${people.name }<br> 
					<c:if test="${people.ename!=null }">
					外文名：${people.ename }<br>
					</c:if>
					<c:if test="${people.nationality!=null }">
					 国 籍：${people.nationality }<br> 
					</c:if>
					<c:if test="${people.birthPlace!=null }">
					 出生地：${people.birthPlace } <br>
					</c:if>
					<c:if test="${people.birthDay!=null }">
					出生日期：<fmt:formatDate value="${people.birthDay }"
					 pattern="yyyy年MM月yy日" /><br>
					</c:if>
					 职 业：${people.job }
				</p>
				<p class="info">
					<span class="title">个人简介</span><br>
					 ${people.introduce }
				
				</p>
			</div>

		</div>

		<!-- 分栏开始 -->
		<div class="row bgwhite">
			<div class="col-lg-8">
				<!-- 资讯列表开始 -->
				<div id="newslist">
					<c:forEach items="${onwerList }" var="onwer" varStatus="status">
						<div class="item ${status.index%2!=0?"alt":"" }">
							<div class="contents">
								<h2>
									<a:trim length="18" content="${onwer.title }"></a:trim>
								</h2>
								<div class="text">
									<a:trim length="80" content="${onwer.quote }"></a:trim>
								</div>
								<div class="mis">
									<div class="more">
										<a href="newDetail.html?articalId=${onwer.articalId }">【详细】</a>
									</div>
									<div class="datetime">
										<fmt:formatDate value="${onwer.createDate }"
											pattern="yyyy年MM月dd日 HH:mm" />
									</div>
								</div>

							</div>
							<div class="img">
								<div class="artical_img">
									<img o-width="${onwer.scanImgWidth }"
										o-height="${onwer.scanImgHeight }" src="${onwer.scanImgPath }" />
								</div>
							</div>
						</div>
					</c:forEach>





				</div>
				<!-- 资讯列表结束 -->

			<c:if test="${fn:length(onwerList)>5 }">
					<div class="loadmore">
						<form action="famousDetail.html?peopleId=${people.peopleId }" method="post" id="dataForm">
							<!-- 分页数据 -->
							<input type="hidden" name="pageIndex" value="${pageIndex+1 }">
							<input type="hidden" name="pageSize" value="${pageSize }">
							<input type="hidden" name="operator" value="ajax">
							<button class="btn" type="button" id="dataSubmit"
									>查看更多</button>
						</form>
					</div>
				</c:if>
			</div>



			<div class="col-lg-4">

				<div id="sidebar">

					<!-- 侧边栏搜索开始 -->
					<div class="search sec">
						<div class="title">
							<span></span>
							<h2>名人搜索</h2>

						</div>

						<div class="contents">
							 <form action="famousSearch.html" method="post" 
                                 onsubmit="if(!(document.getElementById('searchKey').value)){return false;}">
                                     <input type="text" name="key" id="searchKey" value="${key }" maxlength="20">
                                    <button type="submit"><i class="icon-search-1"></i></button>
                                    </form>
						</div>
					</div>
					<!-- 侧边栏搜索结束 -->

					<!-- 相关名人开始 -->
					<div class="morefamous sec">
						<div class="title">
							<span></span>
							<h2>相关名人</h2>
						</div>
						<div class="list clearfix">
							<c:forEach items="${hotPeople.param }" var="people">
								<div class="item">
									<a href="famousDetail.html?peopleId=${people.peopleId }">
										<div class="img">
											<div class="artical_img">
												<img o-width="${people.imgWidth }"
													o-height="${people.imgHeight }" src="${people.imgPath }" />
											</div>
										</div>
										<div class="overlay">
											${people.job } <br> ${people.name }
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- 相关名人结束 -->


					<!-- 猜你喜欢开始 -->
					<div class="reco sec hidedetail">
						<div class="title">
							<span></span>
							<h2>猜你喜欢</h2>
						</div>
						<ul class="list">
							<c:forEach items="${hotList }" var="hot">
								<li class="item"><a
									href="newDetail.html?articalId=${hot.articalId }"> <a:trim
											length="13" content="${hot.title }"></a:trim>
								</a>
									<div class="contents">
										<div class="img">
											<div class="artical_img">
												<img style="width:100%;height:100%;"
													src="${hot.scanImgPath }" />
											</div>
										</div>
										<div class="text">
											<a:trim length="40" content="${hot.quote }"></a:trim>
										</div>
									</div></li>

							</c:forEach>
						</ul>
					</div>
					<!-- 猜你喜欢结束 -->


					<!-- 广告位开始 -->
					<div class="abad" style="width: 100%; height: 250px;">
						<a:output code="famousDetail_2" descript="名人详细右侧广告位" height="250"
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
				style="width: 100%; height: 100px;  margin-bottom: 30px;">
				<!-- 广告位 -->
				<a:output code="famousDetail_3" descript="名人详细页面底部广告位" height="100"
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

			$(".morefamous").on('mouseover', '.item', function(event) {
				$('.overlay', this).show();
			}).on('mouseout', '.item', function(event) {
				$('.overlay', this).hide();
			});
			
			//ajax加载文章
			$("#dataSubmit").on("click",function(){
				var param=$("#dataForm").toData();
				var action=$("#dataForm")[0].action;
				var pageIndex=parseInt($("#dataForm input[name='pageIndex']").val(),"10");
				$.post(action,param,function(html){
					if(html){
						//初始化下一页
						$("#dataForm input[name='pageIndex']").val(pageIndex+1);
							//如果是最后一页
							if(!$.trim(html)){
								layer.msg("没有更多文章了！");
								$("#dataSubmit").remove();
							}else{
								$("#newslist").append(html)
								initImg($("#newslist .img"));
							}
						}
				});
			});
	});
	</script>
</body>
</html>

