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

<!-- start: 网站logo -->
<link rel="shortcut icon" href="../img/avatar.jpg">
<!-- end: Favicon -->
<link href="../css/admin/carNew.css" rel="stylesheet">
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
								${sessionScope.adminUserSession.nickname } <span class="caret"></span>
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

			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<!-- start: Content -->
			<div id="content" class="span10">
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.html">首页</a></li>
					<li><a href="#"> <c:choose>
								<c:when test="${type==1 }">
								资讯
								</c:when>
								<c:when test="${type==2 }">
								名人对话
								</c:when>
								<c:when test="${type==3}">
								封面秀
								</c:when>
								<c:when test="${type==4}">
								作者专栏
								</c:when>
								<c:when test="${type==5}">
								活动
								</c:when>
								<c:when test="${type==6}">
								视频
								</c:when>
							</c:choose> <i class="icon-angle-right"></i>
					</a></li>
				</ul>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon user"></i><span class="break"></span>
								<c:choose>
									<c:when test="${type==1 }">
								资讯列表
								</c:when>
									<c:when test="${type==2 }">
								名人对话列表
								</c:when>
									<c:when test="${type==3}">
								封面秀列表
								</c:when>
									<c:when test="${type==4}">
								作者专栏列表
								</c:when>
									<c:when test="${type==5}">
								活动列表
								</c:when>
									<c:when test="${type==6}">
								视频列表
								</c:when>
								</c:choose>
							</h2>
							<div class="box-icon">
							<ad:power uri="../zcarFamous/add.html">
								<a href="#" class="addNew"><i class="halflings-icon plus"></i>
								</a>
							</ad:power>	
								 <a href="#" class="btn-minimize"><i
									class="halflings-icon chevron-up"></i> </a> <a href="#"
									class="btn-close"><i class="halflings-icon remove"></i> </a>
							</div>
						</div>

						<!-- 数据展示 -->
						<div class="box-content">
							<!-- table框架 -->
							<form action="index.html" method="post" id="myform">
								<input name="pageIndex" id="pageIndex" type="hidden"
									value="${pageResult.pageIndex}" /> <input name="pageSize"
									type="hidden" value="${pageResult.pageSize}" />
							</form>
							<input id="articalType" type="hidden" value="${type}" />
							<table class="zcarmsg table table-striped table-bordered">
								<thead>
									<tr>
										<th width="5%">序列</th>
										<th width="10%">作者</th>
										<c:choose>
											<c:when test="${type==2 }"><!-- 名人对话 -->
												<th width="10%">名人</th>
											</c:when>
											<c:when test="${type==3 }"><!-- 封面秀 -->
												<th width="10%">发行时间</th>
											</c:when>
											<c:when test="${type==5 }"><!-- 活动 -->
												<th width="10%">开始时间</th>
												<th width="10%">结束时间</th>
											</c:when>
										</c:choose>
										<th width="20%">内容</th>
										<th width="20%">缩略图</th>
										<th width="10%">状态</th>
										<th width="10%">置顶状态</th>
										<th width="20%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageResult.param }" var="artical"
										varStatus="status">
										<tr>
											<td  class="index">${status.count }</td>
											<td  class="author">${fn:escapeXml(artical.author.penName)}</td>
											<c:choose>
												<c:when test="${type==2}">
													<td>${fn:escapeXml(artical.famousPeople.name) }</td>
												</c:when>
												<c:when test="${type==3}">
													<td><fmt:formatDate value="${artical.startDate }"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
												</c:when>
												<c:when test="${type==5}">
													<td><fmt:formatDate value="${artical.startDate }"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td><fmt:formatDate value="${artical.endDate }"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
												</c:when>
											</c:choose>
											<td class="info">
												<div class="title">
												<c:if test="${fn:length(artical.quote)>20}">
													${fn:escapeXml(fn:substring(artical.quote, 0, 20))}...
												</c:if>
													</div>
												<div class="content">
													<c:if test="${fn:length(artical.quote)>50}">
													${fn:escapeXml(fn:substring(artical.quote, 0, 50))}...
												</c:if>
													<c:if test="${fn:length(artical.quote)<=50}">
													${fn:escapeXml(artical.quote) }
												</c:if>
												</div>
												<div class="time">
													<i class="icon-edit"></i>
													<fmt:formatDate value="${artical.createDate }"
														pattern="yyyy-MM-dd HH:mm:ss" />

												</div>
											</td>
											<td  class="img"><div style="width:100%;">
													<img style="width:100%;" src="${artical.scanImgPath }-mini" />
												</div></td>
											<td>
												<c:choose>
													<c:when test="${artical.status==2 }">
														<span class="label">隐藏中</span>
													</c:when>
													<c:when test="${artical.status==1 }">
														<span class="label label-success">展示中</span>
													</c:when>
												</c:choose>
											</td>
											<td>
											
											<c:if test="${artical.topDate!=null }">
													<span class="label label-important">置顶中</span>
													</c:if>
													
													</td>
											<td  class="operator">
											<ad:power uri="../zcarFamous/list.html">
											<label
												class="label label-success findById" articalId="${artical.articalId }">查看</label>
											</ad:power>
											
											<ad:power uri="../zcarFamous/update.html">	
												<label class="label label-success editNews"
												articalId="${artical.articalId }">修改</label> 
												</ad:power>
												
												<ad:power uri="../zcarFamous/status.html">
												<label class="label label-success changestatus"
												 articalId="${artical.articalId }" status="${artical.status }">切换状态</label>
												</ad:power>
												
												<ad:power uri="../zcarFamous/toTop.html">
												 <label class="label label-success toTop"
												 articalId="${artical.articalId }" topDate="${artical.topDate }">
												  ${artical.topDate==null?"置顶":"取消置顶" }
												 </label>
												 </ad:power>
												 
												 <ad:power uri="../zcarFamous/delete.html">
												 <label
												class="label label-success delNews " articalId="${artical.articalId }">删除</label>
												</ad:power>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!--分页控制  -->
							<div class="row-fluid">
								<div class="span12">
									<div class="dataTables_info text-left"
										style="padding-left:60px;" id="DataTables_Table_0_info">
										<table>
											<tr>
												<td>总共有 <span class="text-error">${pageResult.totalPage
														}</span> 页，当前显示的是第 <span class="text-success">${pageResult.pageIndex
														} </span> 页, 跳转至 <i class="halflings-icon share-alt"></i>
												</td>
												<td><select id="pageSelect" rel="chosen"
													style='width:60px;'>
														<c:forEach begin="1" end="${pageResult.totalPage }"
															varStatus="index">
															<option value="${index.count }"
																${index.count==pageResult.pageIndex?"selected":"" }>
																${index.count }</option>
														</c:forEach>
												</select></td>
												<td>页</td>
											</tr>
										</table>

									</div>
								</div>
								<div class="span12 center">
									<div class="dataTables_paginate paging_bootstrap pagination">
										<ul id="page">
											<li
												index=${pageResult.pageIndex-1>0?(pageResult.pageIndex-1):"'' class='disabled'" }><a
												href="#">上一页</a></li>
											<c:forEach
												begin="${pageResult.pageIndex-4>0?(pageResult.pageIndex-4):1 }"
												end="${pageResult.pageIndex+4>pageResult.totalPage?pageResult.totalPage:pageResult.pageIndex+4 }"
												var="index">
												<li index="${pageResult.pageIndex!=index?index:"
													" }" class="${index==pageResult.pageIndex?"active":"" }"><a
													href="#">${index}</a></li>
											</c:forEach>
											<li
												index=${pageResult.pageIndex+1<=pageResult.totalPage?pageResult.pageIndex+1:"'' class='disabled'" }
												class="next"><a href="#">下一页</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- 数据展示结束 -->

					</div>
					<!--/span-->
				</div>

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
	<script type="text/javascript" src="../js/admin/zcarNews.js"></script>
	<script type="text/javascript">

	</script>
</body>
</html>
