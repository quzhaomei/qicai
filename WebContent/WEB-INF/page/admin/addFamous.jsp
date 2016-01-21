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
						
						<!-- right-span -->
						<div class="box span3 right-content">
						<div class="control-group">
											<label class="control-label" style="font-weight:bold;padding:5px 20px 0px 20px;" for="typeahead">缩略图 </label>
											<div class="controls">
											<input
													type="hidden" id="imgPath" name="imgPath" value="${people.imgPath }">
											</div>
										</div>
							<div class="reviewImg">
							<label>
							<i class="halflings-icon plus"></i>明人头像
							<input type="file" id="scanImg" name="imgFile"
									style="display:none;"	class="input-file uniform_on" accept="image/*">
							</label>
							<img src="${people.imgPath }"/>
							</div>
							<div style="padding:10px 5px;">
							<label> 宽: <input type="text" id="imgWidth" value="${people.imgWidth }" maxlength="4" placeholder="请输入缩略图宽度"/></label>
							<label> 高: <input type="text" id="imgHeight" value="${people.imgHeight }" maxlength="4" placeholder="请输入缩略图高度"/></label>
							<small style="color:red;display:block;" class="text-right">单位：px</small>
							</div>
						</div>
						
						<div class="box span8">
							<!-- 数据展示 -->
							<div class="box-content">

								<form class="form-horizontal">
									<fieldset>
										<div class="control-group">
											<label class="control-label" for="typeahead">中文名字 </label>
											<div class="controls">
												<input type="text" id="name" class="span6 typeahead"
													maxlength="20" placeholder="请输入中文名字" value="${people.name }"/>
													 <input type="hidden" id="operator"  value="${operator }"/>
													<input type="hidden" id="peopleId"  value="${people.peopleId }"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="typeahead">英文名字 </label>
											<div class="controls">
												<input type="text" id="ename" class="span6 typeahead" value="${people.ename }"
													maxlength="50" placeholder="请输入英文名字" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="typeahead">国籍 </label>
											<div class="controls">
												<input type="text" id="nationality" class="span6 typeahead" value="${people.nationality }"
													maxlength="50" placeholder="请输入国籍" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="typeahead">出生地 </label>
											<div class="controls">
												<input type="text" id="birthPlace" class="span6 typeahead" value="${people.birthPlace }"
													maxlength="50" placeholder="请输入出生地" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="typeahead">出生日期 </label>
											<div class="controls">
												<input type="text" id="birthDay" placeholder="请选择出生年月"  readonly="readonly" class="datepicker" 
												value='<fmt:formatDate value="${people.birthDay}" pattern="yyyy-MM-dd" />'
												bak='<fmt:formatDate value="${people.birthDay}" pattern="yyyy-MM-dd" />'
												/> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="typeahead">职业 </label>
											<div class="controls">
												<input type="text" id="job" placeholder="请输入职业"  value="${people.job }" /> 
											</div>
										</div>
										
										<div class="control-group hidden-phone">
											<label class="control-label" for="textarea2">个人介绍</label>
											<div class="controls">
												<textarea class="kindeditor"
													style="width:100%;height:150px;resize:none;" id="introduce"
													rows="3">${people.introduce }</textarea>
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
	<script type="text/javascript" src="../js/admin/addFamous.js"></script>
	<script>
	var bak = ${bak};
	</script>
</body>
</html>
