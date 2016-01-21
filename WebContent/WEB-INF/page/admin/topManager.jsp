<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/adtag" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>汽车财经网站系统后台管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="header.jsp"></jsp:include>

<link  href="../css/admin/topManager.css" rel="stylesheet">
<!-- start: 网站logo -->
<link rel="shortcut icon" href="../img/avatar.jpg">
<!-- end: Favicon -->
<style type="text/css">
.modal-body {
	max-height: 580px;
}
</style>
</head>

<body>
	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a> <a class="brand" href="../welcome/index.html"><span>汽车财经网站系统后台管理</span>
				</a>
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i>
								${sessionScope.adminUserSession.nickname }<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title"><span>操作</span></li>
								<li><a href="#" id="updateUserPsw"><i
										class="halflings-icon edit"></i> 修改密码</a></li>
								<li><a href="../loginout.html"><i
										class="halflings-icon user"></i> 切换用户</a></li>
								<li><a href="../loginout.html"><i
										class="halflings-icon off"></i> 安全退出</a></li>
							</ul></li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
			</div>
		</div>
	</div>
	<!-- start: Header -->

	<div class="container-fluid-full">
		<div class="row-fluid">

			<!-- start: Main Menu -->
			<c:import url="/welcome/menus.html"></c:import>
			<!-- end: Main Menu -->

			<!-- start: Content -->
			<div id="content" class="span10">
				<ul class="breadcrumb">
					<li><a href="#">首页置顶分类管理<i class="icon-angle-right"></i>
					</a></li>
				</ul>
				<c:if test="${tops==null||fn:length(tops)==0 }">
					<ad:power uri="../topManager/add.html">
					<div class="notopBtn">
						<span class="btn btn-success btn-lg new-top">新建分类</span>
						<div class="info">暂无系统分类置顶，快来添加第一个首页的置顶分类吧</div>
					</div>
					</ad:power>
				</c:if>
				<c:if test="${tops!=null&&fn:length(tops)!=0 }">
				<ad:power uri="../topManager/add.html">
					<div class="topBtn">
						<span class="btn btn-success btn-middle new-top">新建分类</span>
					</div>
					</ad:power>
				</c:if>
				<c:forEach items="${tops }" var="top">
					<div class="row-fluid sortable" id="top${top.topId }">
						<div class="box span12">
							<div class="box-header" style="height:25px;">
								<h2 class="title ${top.status==1?'show':'' }">
									<i class="halflings-icon book"></i><span class="break"></span>
									<span class="topname">${top.name }</span> <span
										class="topstatus"> [冻结中]</span>
								</h2>
								<div class="box-icon">
								<ad:power uri="../topManager/update.html">
									<span topid="${top.topId }"
										class="btn btn-small btn-success topnameEdit">修改</span>
									</ad:power>	
									
									<ad:power uri="../topManager/delete.html">
										 <span
										topid="${top.topId }"
										class="btn btn-small btn-important topnameDel">删除</span> 
										</ad:power>
										
										<ad:power uri="../topManager/fillTop.html">
										<span
										topid="${top.topId }"
										class="btn btn-small label-important topnameAdd">添加文章</span>
										</ad:power>
								</div>
							</div>
							<div class="box-content">
								<table
									class="table table-striped table-bordered bootstrap-datatable">
									<thead>
										<tr>
											<th width=5%>序列</th>
											<th width=10%>来源</th>
											<th width=10%>文章作者</th>
											<th width=15%>创建时间</th>
											<th width=15%>标题</th>
											<th width=30%>缩略图</th>
											<th width=15%>操作</th>
										</tr>
									</thead>
									<tbody class="mark" id="topdata${top.topId }">
										<c:choose>
											<c:when
												test="${top.articals.param==null||fn:length(top.articals.param)==0}">
												<tr>
													<td colspan="7" class="noartical">暂无文章</td>
												</tr>

											</c:when>
											<c:otherwise>
												<c:forEach items="${top.articals.param }" var="artical"
													varStatus="step">
													<tr>
														<td>${step.count }</td>
														<td>${fn:escapeXml(artical.resource.name) }</td>
														<td>${fn:escapeXml(artical.author.penName) }</td>
														<td><fmt:formatDate value="${artical.createDate }"
																pattern="yyyy-MM-dd HH:mm:ss" /></td>
														<td>${fn:escapeXml(artical.title) }</td>
														<td>
															<div style="width:100%;height:80px;overflow:hidden;">
																<img src="${artical.scanImgPath }" style="width:100%;"/>
															</div>
														</td>
														<td><c:if test="${step.count!=1 }">
														
														<ad:power uri="../topManager/fillTop.html">
																<span class="btn btn-mini label-important toTop" topid="${top.topId }" articalId=${artical.articalId }>
																	<i class="halflings-icon bookmark white"></i> 
																	置顶
																</span>
																</ad:power>
																
															</c:if>
															<ad:power uri="../topManager/fillTop.html">
															<span class="btn btn-mini  toDel" topid="${top.topId }" articalId=${artical.articalId }>
																	移除
																</span>
																</ad:power>
															</td>
													</tr>
												</c:forEach>
												<c:if test="${top.articals.param ==null }">
													<c:set var="begin" value="1"></c:set>
												</c:if>
												<c:if test="${top.articals.param !=null }">
													<c:set var="begin"
														value="${fn:length(top.articals.param )+1 }"></c:set>
												</c:if>
												<c:if test="${begin <= (top.articals.pageSize) }">
													<c:forEach begin="${begin}"
														end="${(top.articals.pageSize) }">
														<tr>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
															<td>&nbsp;</td>
														</tr>
													</c:forEach>
												</c:if>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${top.articals.param!=null&&fn:length(top.articals.param)!=0}">
									
							<!--分页控制  -->
							<c:set var="pageResult" value="${top.articals }" ></c:set>
							
							<div class="row-fluid">
								<div class="span12 center">
									<div class="dataTables_paginate paging_bootstrap pagination">
										<ul class="page-controller" topid="${top.topId }">
											<li class="prev"><a href="#">上一页</a>
											</li>
											<c:forEach begin="1" end="${pageResult.totalPage}" var="index">
											<li index="${index}" 
											class="${index==pageResult.pageIndex?"active":"" }" style="
											 ${(index>=pageResult.pageIndex-4&&index<=pageResult.pageIndex+4)?"":"display:none;" }"
											
											><a href="#">${index}</a>
											</li>
											</c:forEach>
											<li class="next"><a href="#">下一页</a>
											</li>
											<li ><a href="#" style="border:none;">共 ${pageResult.totalPage } 页</a></li>
										</ul>
										
									</div>
								</div>
							</div>
							
								</c:if>
								
							</div>
						</div>
						<!--/span-->
					</div>
				</c:forEach>

			</div>

		</div>
	</div>

	<div class="clearfix"></div>
	<footer>
		<p>
			<span style="text-align:left;float:left">&copy; 2015 <a
				href="#">quzhaomei@com</a>
			</span>
		</p>
	</footer>
	<!-- start: JavaScript-->
	<c:import url="javascript.jsp"></c:import>
	<!-- end: JavaScript-->
	<script type="text/javascript" src="../js/admin/topManager.js">

	</script>
</body>
<!-- 新增modal -->
<div class="modal hide fade" id="addModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>首页置顶分类管理</h3>
	</div>
	<div class="modal-body">
		<p>
			<small class="text-success">新建置顶分类</small>
		</p>
		<div class="input-prepend" title="菜单名字">
			<div class="alert alert-error alert-dismissable" style="display:none">
				<span class="content"></span>
			</div>
			<span class="add-on"><i class="halflings-icon user"></i> 名 称 </span>
			<input class="input-large span5" id="add_name" type="text"
				placeholder="请输入分类名称" maxlength="10">
		</div>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">返回</a> <a href="#"
			class="btn btn-primary addSubmit">保存</a>
	</div>
</div>

<!-- 修改modal -->
<div class="modal hide fade" id="updateModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>首页置顶分类管理</h3>
	</div>
	<div class="modal-body">
		<p>
			<small class="text-success">修改置顶分类</small>
		</p>
		<div class="alert alert-error alert-dismissable" style="display:none">
			<span class="content"></span>
		</div>
		<table>
			<tr>
				<td><span class="add-on"><i class="halflings-icon user"></i>
						名 称 </span> <input type="hidden" id="topId"></td>
				<td><input class="input-large span5" id="edit_name" type="text"
					placeholder="请输入角色名称" maxlength="20"></td>
			</tr>
			<tr>
				<td><span class="add-on"><i class="halflings-icon user"></i>
						状态 </span></td>
				<td><label class="radio" style="padding:0 0;"> <input
						type="radio" name="status" class="type_radio edit_status"
						value="1"> <span class="label label-success">激活 </span>
				</label> <label class="radio" style="padding:0 0;"> <input
						type="radio" name="status" class="type_radio edit_status"
						value="0" checked=""> <span class="label label-important">冻结
					</span>
				</label></td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">返回</a> <a href="#"
			class="btn btn-primary updateSubmit">保存</a>
	</div>
</div>
</html>
