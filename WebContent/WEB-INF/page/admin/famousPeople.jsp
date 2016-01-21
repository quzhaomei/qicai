<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
.modal-body{max-height: 580px;}
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
					class="icon-bar"></span> </a> <a class="brand" href="../welcome/index.html"><span>汽车财经网站系统后台管理</span>
				</a>
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> ${sessionScope.adminUserSession.nickname }<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title"><span>操作</span></li>
								<li><a href="#" id="updateUserPsw"><i class="halflings-icon edit"></i>
										修改密码</a>
								</li>
								<li><a href="../loginout.html"><i class="halflings-icon user"></i>
										切换用户</a>
								</li>
								<li><a href="../loginout.html"><i class="halflings-icon off"></i>
										安全退出</a>
								</li>
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
								<i class="halflings-icon user"></i><span class="break"></span>名人管理
							</h2>
							<div class="box-icon">
							<a:power uri="../famous/add.html">
								<a href="#" class="addFamous"><i
									class="halflings-icon plus"></i> </a>
							</a:power>		
									 <a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i>
								</a> <a href="#" class="btn-close"><i
									class="halflings-icon remove"></i> </a>
							</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width=5%>序列</th>
								  <th width=10%>名字</th>
								  <th width=10%>职业</th>
								  <th width=20%>头像</th>
								  <th width=18%>介绍</th>
								  <th width=15%>创建时间</th>
								  <th width=7%>状态</th>
								  <th width=15%>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
						  <c:forEach items="${peoples }" var="people" varStatus="status">
						  	<tr>
								<td>${status.count }</td>
								<td class="center">${fn:escapeXml(people.name) }</td>
								<td class="center">${fn:escapeXml(people.job) }</td>
								<td class="center">
								<div style="width:100%;height:80px;overflow: hidden;">
								<img style="width:100%;"src="${people.imgPath }"></div>
								</td>
								<td class="center"><a:trim length="50" content="${people.introduce}"></a:trim> </td>
								<td class="center">
									<fmt:formatDate value="${people.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								
								<td class="center">
								<c:choose>
									<c:when test="${people.status==1 }">
									<span class="label label-success">激活</span>
									</c:when>
									<c:when test="${people.status==2 }">
									<span class="label">冻结</span>
									</c:when>
								</c:choose>
								</td>
								
								<td class="center">
								<a:power uri="../famous/list.html">
									<a class="btn btn-success btn-mini detail" href="#" peopleId="${people.peopleId }">
										<i class="halflings-icon white edit "></i>  详细
									</a>
									</a:power>
									
									<a:power uri="../famous/update.html">
									<a class="btn btn-success btn-mini editFamous" href="#" peopleId="${people.peopleId }">
										<i class="halflings-icon white edit "></i>  修改
									</a>
									</a:power>
									<a:power uri="../famous/status.html">
									<a class="btn btn-success btn-mini changeStatu" href="#" peopleid="${people.peopleId }" 
									status="${people.status }" >
										<i class="halflings-icon white refresh"></i> 切换状态
									</a>
									</a:power>
									<a:power uri="../famous/toTop.html">
									<a class="btn btn-success btn-mini toTop" href="#" peopleid="${people.peopleId }" 
									status="${people.status }" >
										<i class="halflings-icon white"></i> 置顶
									</a>
									</a:power>
									<a:power uri="../famous/delete.html">
									<a class="btn btn-success btn-mini delete" href="#" peopleid="${people.peopleId }" 
									 >
										<i class="halflings-icon white"></i> 删除
									</a>
									</a:power>
								</td>
							</tr>
						  </c:forEach>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			</div>
			
		</div>
	</div>
	
	<div class="clearfix"></div>
	<footer>
		<p>
			<span style="text-align:left;float:left">&copy; 2015 <a
				href="#">quzhaomei@com</a> </span>
		</p>
	</footer>
	<!-- start: JavaScript-->
	<c:import url="javascript.jsp"></c:import>
	<!-- end: JavaScript-->
	<script type="text/javascript" src="../js/ajaxfileupload.js" charset="utf-8"></script>
	<script type="text/javascript" src="../js/admin/famousPeople.js" charset="utf-8"></script>
</body>
</html>
