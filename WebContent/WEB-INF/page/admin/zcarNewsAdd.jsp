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
.warn-tips {
	text-align: center;
	margin: 80px 300px;
	padding: 150px 0 100px 0;
	border: 2px solid #888;
}
</style>
<body>
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Content -->
			<div id="content" style="margin:0 0% !important;width:100%;">
				<div class="row-fluid">
					<c:if test="${empty author }">
						<h1 class="warn-tips">
							请先去作者个人中心设置您的作者信息！<br />
							<br />
							<br />
							<button type="button" class="btn btn-danger btn-back">返回</button>
						</h1>
					</c:if>
					<c:if test="${not empty author }">
						
						<!-- right-span -->
						<div class="box span3 right-content">
						<div class="control-group">
											<label class="control-label" style="font-weight:bold;padding:5px 20px 0px 20px;" for="typeahead">缩略图 </label>
											<div class="controls">
											<input
													type="hidden" id="scanImgPath" name="scanImgPath" value="">
											</div>
										</div>
							<div class="reviewImg">
							<label>
							<i class="halflings-icon plus"></i>缩略图
							<input type="file" id="scanImg" name="imgFile"
									style="display:none;"	class="input-file uniform_on" accept="image/*">
							</label>
							</div>
							<div style="padding:10px 5px;">
							<label> 宽: <input type="text" id="scanImgWidth" placeholder="请输入缩略图宽度"/></label>
							<label> 高: <input type="text" id="scanImgHeight" placeholder="请输入缩略图高度"/></label>
							<small style="color:red;display:block;" class="text-right">单位：px</small>
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
													maxlength="50" placeholder="请输入标题" /> <input type="hidden"
													id="operator" value="${operator }" />
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="date01">来源</label>
											<div class="controls">
												<select rel="chosen" style="width:150px;" id="resource">
													<c:forEach items="${resources }" var="resource">
														<option value="${resource.resourceId }">${fn:escapeXml(resource.name)}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<c:choose>
											<c:when test="${type==2 }">
												<!-- 名人对话 -->
												<div class="control-group">
													<label class="control-label" for="date01">名人</label>
													<div class="controls">
														<select rel="chosen" style="width:150px;" id="people">
														<c:forEach items="${peoples }" var="people">
															<option value="${people.peopleId }">${fn:escapeXml(people.name) }</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</c:when>
											<c:when test="${type==3 }">
												<!-- 封面秀 -->
												<div class="control-group carShow">
													<label class="control-label" for="date01">发行时间</label>
													<div class="controls">
													<input type="text" id="startDate" onkeyup="return false;" class="datepicker"
														maxlength="50" rel-info="发行时间"/>
													</div>
													<label class="control-label" for="date01">PDF附件</label>
													<div class="controls">
													<label class="label label-success pdfSource" style="padding:5px 5px;"> <em>上传 </em>
													<input type="file" id="pdfSource" name="sourceFile" class="span6 typeahead" style="display:none;"/>
													<input type="hidden" rel-info="PDF附件" id="sourcePath" ref-info="PDF"/>
													</label>
													</div>
												</div>
											</c:when>
											<c:when test="${type==5 }">
												<!-- 活动 -->
												<div class="control-group carShow">
													<label class="control-label" for="date01">开始时间</label>
													<div class="controls">
													<input type="text" id="startDate" onkeyup="return false;" class="datepicker"
														maxlength="50" rel-info="开始时间" />
													</div>
													<label class="control-label" for="date01">结束时间</label>
													<div class="controls" rel-info="结束时间">
													<input type="text" id="endDate" onkeyup="return false;" class="datepicker"
														maxlength="50" />
													</div>
													<label class="control-label" for="date01">地点</label>
													<div class="controls">
													<input type="text" id="locationPath" class="span6 input-xlarge"
														maxlength="50" rel-info="地点"/>
													</div>
												</div>
											</c:when>
											<c:when test="${type==6 }">
												<!-- 视频秀 -->
												<div class="control-group carShow">
													<label class="control-label" for="date01">视频</label>
													<div class="controls">
													</br>
													<input rel-info="视频" id="sourcePath" class="span8" placeholder="请输入视频地址" ref-info="视频" value=""/>
													<label class="label label-success pdfSource" style="padding:5px 5px;"> 上传本地视频
													<input type="file" id="pdfSource" name="sourceFile" class="span6 typeahead" style="display:none;"/>
													<input type="hidden" rel-info="视频" id="sourcePath" ref-info="视频"/>
													</label>
												</div>
												</div>
												<!-- 视频种类 -->
												<div class="control-group">
													<label class="control-label" for="date01">视频类别</label>
													<div class="controls">
														<select rel="chosen" style="width:150px;" id="videoType">
															<option value="0">原创视频</option>
															<option value="1">试驾视频</option>
														</select>
													</div>
												</div>
											</c:when>
										</c:choose>

										<div class="control-group">
											<label class="control-label" for="fileInput">标签</label>
											<div class="controls">
												<select multiple="multiple" id="tag" rel="chosen"
													style="width:70%;" data-placeholder="请选择文章标签（可多选）">
													<c:forEach items="${tags }" var="tag">
														<option value="${tag.tagId }">${fn:escapeXml(tag.tagName) }</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="typeahead">引言 </label>
											<div class="controls">
												<textarea id="quote" style="resize:none;width:70%;" rows="3"
													maxlength="200" placeholder="请输入引言"></textarea>
											</div>
										</div>

										<div class="control-group hidden-phone">
											<label class="control-label" for="textarea2">正文</label>
											<div class="controls">
												<textarea class="kindeditor"
													style="width:100%;height:200px;resize:none;" id="textarea2"
													rows="3"></textarea>
											</div>
										</div>
										<div class="form-actions">
											<button type="button" class="btn btn-primary btn-submit">保存</button>
											<button type="button" class="btn btn-lg btn-back">返回</button>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
						<!--/span-->


					</c:if>
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
	<!-- end: JavaScript-->
	<script type="text/javascript" src="../js/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="../js/admin/newsAdd.js"></script>
	<script>
		
	</script>
</body>
</html>
