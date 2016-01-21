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
</head>
<style>
.condition {
	font-weight: bold;
	vertical-align: middle;
}
.table td.condition{text-align:right;}
</style>
<body>
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Content -->
			<div id="content" style="margin:0 0% !important;width:100%;">
				<div class="row-fluid">
					<div class="box span12" style="padding:0 10px;z-index: 99;position:relative; ">
						<!-- 条件选择 -->
						<div class="box-content">

							<form id="select_artical" action="fillTop.html" style="margin: 0 20px;">
								<fieldset>
								<input type="hidden" name="operator" value="toAdd"/>
								
								<input type="hidden" id="topId" name="topId" value="${topId }"/>
								<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }"/>
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }"/>
									<h2 class="label">文章搜索</h2>
									<table class="table">
										<tr>
											<td width="8%" class="condition">类型</td>
											<td width="25%"><select rel="chosen" style="width:70%;"
												data-placeholder="文章类型" name="typeId">
													<option >不限</option>
													<option ${typeId==1?"selected='selected'":"" } value="1">新闻资讯</option>
													<option ${typeId==2?"selected='selected'":"" } value="2">名人对话</option>
													<option ${typeId==3?"selected='selected'":"" } value="3">封面秀</option>
													<option ${typeId==4?"selected='selected'":"" } value="4">作者专栏</option>
													<option ${typeId==5?"selected='selected'":"" } value="5">活动</option>
													<option ${typeId==6?"selected='selected'":"" } value="6">视频</option>
													<option ${typeId==7?"selected='selected'":"" } value="7">数据</option>
											</select></td>
											
											<td width="8%" class="condition">标签</td>
											<td width="25%"><select name="tagId" rel="chosen"
												style="width:70%;" data-placeholder="文章标签">
													<option value="">不限</option>
													<c:forEach items="${tags}" var="tag">
														<option  ${tagId==tag.tagId?"selected='selected'":""}  value="${tag.tagId }">${fn:escapeXml(tag.tagName) }</option>
													</c:forEach>
											</select></td>
											
											<td width="8%" class="condition">来源</td>
											<td width="25%" ><select name="resourceId" rel="chosen"
												style="width:70%" data-placeholder="文章标签">
													<option>不限</option>
													<c:forEach items="${resources}" var="resource">
														<option value="${resource.resourceId }">${fn:escapeXml(resource.name) }</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<td class="condition">起始时间</td>
											<td ><input type="text" class="datepicker"
												placeholder="文章发布时间，所选时间的0点" value="${startTime }" name="startTime" onkeyup="return false;"/></td>
												
											<td class="condition">截止时间</td>
											<td ><input type="text" class="datepicker"
												placeholder="截止时间，当日的0点" value="${endTime }" name="endTime" onkeyup="return false;"/></td>
											<td colspan="2" style="text-align:center;">
											<input type="submit"
												class="btn btn-middle btn-success" value="查询" />
											</td>
										</tr>
									</table>
								</fieldset>
							</form>
						</div>
					</div>
					<!--/span-->
					<div class="box span12" style="padding:0 10px; ">
						<!-- 数据展示 -->
						<div class="box-content">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th style="width:10%;">类型</th>
										<th style="width:10%;">来源</th>
										<th style="width:15%;">作者</th>
										<th style="width:15%;">时间</th>
										<th style="width:15%;">标题</th>
										<th style="width:20%;">缩略图</th>
										<th style="width:15%;">操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${pageResult.param }" var="artical">
									<tr>
										<td>
										<c:choose>
											<c:when test="${artical.type==1 }">
												新闻资讯
											</c:when>
											<c:when test="${artical.type==2 }">
												名人对话
											</c:when>
											<c:when test="${artical.type==3 }">
												封面秀
											</c:when>
											<c:when test="${artical.type==4 }">
												经销商
											</c:when>
											<c:when test="${artical.type==5 }">
												活动
											</c:when>
											<c:when test="${artical.type==6 }">
												视频
											</c:when>
										</c:choose>
										</td>
										<td>${fn:escapeXml(artical.resource.name) }</td>
										<td>${fn:escapeXml(artical.author.penName) }</td>
										<td>
										<fmt:formatDate value="${artical.createDate }"
										pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>${fn:escapeXml(artical.title) }
										</td>
										<td style="width:20%;">
										<div style="height:50px;overflow: hidden;">
										<img src="${artical.scanImgPath}"/>
										</div>
										<td>
											<a class="btn btn-success btn-mini addArtical" articalId="${artical.articalId }" href="#"> 
												<i class="halflings-icon white edit "></i> 选择</a> 
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
							<!--分页控制  -->
							<div class="row-fluid">
								<div class="span12">
									<div class="dataTables_info text-left" style="padding-left:60px;" id="DataTables_Table_0_info">
										<table >
										<tr>
										<td>总共有  <span class="text-error">${pageResult.totalPage }</span> 页，当前显示的是第 
										 <span class="text-success">${pageResult.pageIndex } </span> 页,
										 跳转至 <i class="halflings-icon share-alt"></i> </td>
										<td>
										<select id="pageSelect" rel="chosen" style='width:60px;'>
															<c:forEach begin="1" end="${pageResult.totalPage }" varStatus="index">
																<option  value="${index.count }" ${index.count==pageResult.pageIndex?"selected":"" }> ${index.count }</option>
															</c:forEach>
												</select>
												</td>
										<td> 页 </td>
										</tr>
										</table>
										
									</div>
								</div>
								<div class="span12 center">
									<div class="dataTables_paginate paging_bootstrap pagination">
										<ul id="page">
											<li index= ${pageResult.pageIndex-1>0?(pageResult.pageIndex-1):"'' class='disabled'" }><a href="#">上一页</a>
											</li>
											<c:forEach begin="${pageResult.pageIndex-4>0?(pageResult.pageIndex-4):1 }"
											 end="${pageResult.pageIndex+4>pageResult.totalPage?pageResult.totalPage:pageResult.pageIndex+4 }"  var="index">
											<li index="${pageResult.pageIndex!=index?index:"" }" class="${index==pageResult.pageIndex?"active":"" }"><a href="#">${index}</a>
											</li>
											</c:forEach>
											<li index=${pageResult.pageIndex+1<=pageResult.totalPage?pageResult.pageIndex+1:"'' class='disabled'" } class="next"><a href="#">下一页</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							
						
						</div>
					</div>

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
	<script>
		$(".select_artical").on("submit",function(){
		});
		$("#pageSelect").on("change",function(){
			var pageIndex=$(this).val();
			$("#pageIndex").val(pageIndex);
			$("#select_artical").submit();
		});
		$("#page li").on("click",function(){
			var index=$(this).attr("index");
			if(index){
				$("#pageIndex").val(index);
				$("#select_artical").submit();
			}
		});
		$(".addArtical").on("click",function(){
			var articalId=$(this).attr("articalId");
			var topId=$("#topId").val();
			var param={};
			param.operator="add";
			param.topId=topId;
			param.articalId=articalId;
			if(articalId&&topId){
				layer.confirm("确定选为置顶吗？", {
					title:"文章选择",
				    btn: ["确定","返回"], //按钮
				    shade: false //不显示遮罩
				}, function(){
					$.post("fillTop.html",param,function(data){
					if(data.status==1){
						layer.msg(data.message,{ icon: 1,time: 1000 },function(){
							if(window.parent){
								window.parent.location.reload();
							}
						});
					}else{
						layer.msg(data.message);
					}
				},"json");
				
				}, function(){});
			}else{
				layer.msg("页面数据丢失，请刷新页面！");
			}
		});
	</script>
</body>
</html>
