<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>汽车财经网站系统后台管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="header.jsp"></jsp:include>

<!-- start: 网站logo -->
<link rel="shortcut icon" href="../img/avatar.jpg">
<!-- end: Favicon -->
<link href="../css/admin/carNew.css" rel="stylesheet">
</head>
<style>
</style>
<body>
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Content -->
			<div id="content" style="margin:0 0% !important;width:100%;">
				<div class="row-fluid">
				<!-- right-span -->
					<div class="box span3 right-content">
						<h3>缩略图</h3>
						<div class="reviewImg">
							<img src="${artical.scanImgPath }" />
						</div>
							<div style="padding:10px 5px;">
							<label> 宽: ${artical.scanImgWidth } px | 高:  ${artical.scanImgHeight } px </label>
							</div>
					</div>
					
					<div class="box span8">
						<!-- 数据展示 -->
						<div class="box-content">
							<form class="form-horizontal">
								<fieldset>
									<div class="control-group">
										<label class="control-label" for="typeahead">标题 </label>
										<div class="controls">
											<input type="text" id="title" class="span6 typeahead"
												maxlength="50" placeholder="请输入标题" readonly="readonly"
												value="${fn:escapeXml(artical.title) }" />
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="date01">来源</label>
										<div class="controls">
											<span class="label"> ${fn:escapeXml(artical.resource.name) }</span>
										</div>
									</div>
									<c:choose>
										<c:when test="${type==2 }">
											<div class="control-group">
												<label class="control-label" for="date01">名人</label>
												<div class="controls">
													<span class="label"> ${fn:escapeXml(artical.famousPeople.name) }</span>
												</div>
											</div>
										</c:when>
										<c:when test="${type==3 }">
											<div class="control-group">
												<label class="control-label" for="date01">发行时间</label>
												<div class="controls">
													<fmt:formatDate value="${artical.startDate}"
														pattern="yyyy-MM-dd" />
												</div>
												<br/>
												<label class="control-label" for="date01">PDF附件地址</label>
												<div class="controls">
													<label><a href="${artical.sourcePath }" target="_blank">${artical.sourcePath }</a>
												</div>
											</div>
										</c:when>
										<c:when test="${type==5 }">
											<div class="control-group">
												<label class="control-label" for="date01">起始时间</label>
												<div class="controls">
													<fmt:formatDate value="${artical.startDate}"
														pattern="yyyy-MM-dd" />

												</div>
												<br style="clear:both;"/>
												<label class="control-label" for="date01">结束时间</label>
												<div class="controls">
													<fmt:formatDate value="${artical.endDate}"
														pattern="yyyy-MM-dd" />
												</div>
												<br style="clear:both;"/>
												<c:if test="${artical.sourcePath!=null }">
												<label class="control-label" for="date01">地点</label>
												<div class="controls">
													${fn:escapeXml(artical.sourcePath)}
												</div>
												</c:if>
											</div>
										</c:when>
										<c:when test="${type==6 }">
											<div class="control-group">
												<label class="control-label" for="date01">视频地址</label>
												<div class="controls">
													<label><a href="${artical.video.path }" target="_blank">${artical.video.path  }</a>
													</label>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="date01">视频类型</label>
												<div class="controls">
												<span class="label">
												<c:choose>
													<c:when test="${artical.video.type==0 }">
													原创视频
													</c:when>
													<c:when test="${artical.video.type==1 }">
													试驾视频
													</c:when>
												</c:choose>
													</span>
												</div>
											</div>
										</c:when>
									</c:choose>

									<div class="control-group">
										<label class="control-label" for="fileInput">标签</label>
										<div class="controls">
											<c:forEach items="${artical.tagList }" var="tag">
												<span class="label"> ${fn:escapeXml(tag.tagName) }</span>
											</c:forEach>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="typeahead">引言 </label>
										<div class="controls">
											<div id="quote"
												style="width:70%;height:100px;border:1px solid #aaa;overflow:auto">
												${fn:escapeXml(artical.quote) }</div>
										</div>
									</div>

									<div class="control-group hidden-phone">
										<label class="control-label" for="textarea2">正文</label>
										<div class="controls">
											<div
												style="width:100%;height:200px;overflow:auto;border:1px solid #aaa;">
												${artical.content }</div>
										</div>
									</div>
									<div class="form-actions">
										<button type="button" class="btn btn-lg btn-back">返回</button>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
					<!--/span-->


				</div>
				<!--/row-->
				<!--/span-->
			</div>

		</div>

	</div>
	</div>

	<div class="clearfix"></div>
	<!-- start: JavaScript-->
	<c:import url="javascript.jsp"></c:import>
	<!-- end: JavaScript
	<script type="text/javascript" src="../js/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="../js/ajaxfileupload.js"></script>-->
	<script type="text/javascript">
		$(".btn-back").click(function() {
			if (window.parent.closeInit) {
				window.parent.closeInit();
			}
		});
	</script>
</body>
</html>
