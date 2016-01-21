<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/adtag" prefix="a"%>
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
					<li><i class="icon-home"></i> <a href="index.html">首页</a></li>
					<!-- 	<li><a href="#">系统设置<i class="icon-angle-right"></i> </a> -->
					</li>
				</ul>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon user"></i><span class="break"></span>文章标签管理
							</h2>
							<div class="box-icon">
							<a:power uri="../tag/add.html">
								<a href="#" class="addTag"><i class="halflings-icon plus"></i>
								</a> 
								</a:power>
								<a href="#" class="btn-minimize"><i
									class="halflings-icon chevron-up"></i> </a> <a href="#"
									class="btn-close"><i class="halflings-icon remove"></i> </a>
							</div>
						</div>
						<div class="box-content">
						<form action="index.html" method="post" id="myform">
						<input name="pageIndex" id="pageIndex" type="hidden" value="${pageResult.pageIndex}"/>
						<input name="pageSize" type="hidden" value="${pageResult.pageSize}"/>
						<select name="type" rel="chosen" style="width:80px;" id="tagType">
							<option value="0" ${type=='0'?"selected='selected'":"" }>-所有-</option>
							<option value="1" ${type=='1'?"selected='selected'":"" }>-品牌-</option>
							<option value="2" ${type=='2'?"selected='selected'":"" }>-专题-</option>
							<option value="3" ${type=='3'?"selected='selected'":"" }>-普通-</option>
						</select>
						</form>
							<table
								class="table table-striped table-bordered ">
								<thead>
									<tr>
										<th width=5%>序列</th>
										<th width=10%>标签名</th>
										<th width=10%>标签类别</th>
										<th width=10%>链接</th>
										<th width=15%>创建时间</th>
										<th width=20%>标签介绍</th>
										<th width=20%>图片</th>
										<th width=10%>状态</th>
										<th width=10%>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageResult.param }" var="tag" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td class="center">${fn:escapeXml(tag.tagName) }</td>
											<td class="center">
											<c:choose>
												<c:when test="${tag.type==1 }">
													<span class="label label-success">品牌</span>
												</c:when>
												<c:when test="${tag.type==2 }">
													<span class="label label-important">专题</span>
												</c:when>
												<c:when test="${tag.type==3 }">
													<span class="label label-success">普通</span>
												</c:when>
											</c:choose>
											</td>
											<td class="center">${fn:escapeXml(tag.href) }</td>
											<td class="center"><fmt:formatDate
													value="${tag.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><a:trim length="50" content="${tag.introduce }"></a:trim>
											</td>
											<td class="center"><div style="width:100%;">
													<img style="width:100%;" src="${tag.imgPath }-mini" />
												</div>
											</td>
											<td class="center"><c:if test="${tag.status==0 }">
													<span class="label label-important">冻结 0</span>
												</c:if> <c:if test="${tag.status==1 }">
													<span class="label label-success">激活 1</span>
												</c:if></td>

											<td class="center">
											<a:power uri="../tag/update.html">
											 <a class="btn btn-info btn-mini editTag" href="#"
												tagId="${tag.tagId }"> <i
													class="halflings-icon white edit "></i> 修改
											</a>
											</a:power>
											<a:power uri="../tag/status.html">
											 <a class="btn btn-danger btn-mini changeStatu" href="#"
												tagId="${tag.tagId }" status="${tag.status }"> <i
													class="halflings-icon white refresh"></i> 切换状态
											</a>
											</a:power>
											<a:power uri="../tag/toTop.html">
											</a> <a class="btn btn-danger btn-mini toTop" href="#"
												tagId="${tag.tagId }" status="${tag.status }"> <i
													class="halflings-icon white"></i> 置顶
											</a>
											</a:power>
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
					<!--/span-->

				</div>
				<!--/row-->
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
	<script type="text/javascript" src="../js/admin/articalTag.js"
		charset="utf-8"></script>
</body>
</html>
