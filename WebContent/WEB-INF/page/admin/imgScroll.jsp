<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
								<i class="halflings-icon user"></i><span class="break"></span>首页轮播管理
							</h2>
							<div class="box-icon">
							<ad:power uri="../scroll/add.html">
								<a href="#" class="addScroll"><i
									class="halflings-icon plus"></i> </a> 
									</ad:power>
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
								  <th width=10%>序列</th>
								  <th width=15%>标题</th>
								  <th width=10%>链接</th>
								  <th width=25%>图片</th>
								  <th width=10%>创建时间</th>
								  <th width=10%>状态</th>
								  <th width=20%>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
						  <c:forEach items="${scrolls }" var="temp" varStatus="status">
						  	<tr>
								<td>${status.count }</td>
								<td class="center">${fn:escapeXml(temp.title) }</td>
								<td class="center">
								<a href="${temp.href}" target="_blank">${temp.href }</a> </td>
								<td class="center">
								<div style="width:100%;height:80px;overflow:hidden;">
								<img src="${fn:escapeXml(temp.imgPath) }" style="width:100%;"/>
								</div>
								</td>
								<td class="center">
									<fmt:formatDate value="${temp.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td class="center">
								<c:if test="${temp.status==0 }">
									<span class="label label-important">冻结 0</span>
								</c:if>
								<c:if test="${temp.status==1 }">
									<span class="label label-success">激活 1</span>
								</c:if>
								</td>
								
								<td class="center">
									<%--<a class="btn btn-success" href="#">
										<i class="halflings-icon white zoom-in"></i>  
									</a>--%>
									<ad:power uri="../scroll/update.html">
									<a class="btn btn-info btn-mini editScroll" href="#" scrollId="${temp.scrollId }">
										<i class="halflings-icon white edit "></i>  修改
									</a>
									</ad:power>
									<ad:power uri="../scroll/status.html">
									<a class="btn btn-danger btn-mini changeStatu" href="#" scrollid="${temp.scrollId }" status="${temp.status }" >
										<i class="halflings-icon white refresh"></i> 切换状态
									</a>
									</ad:power>
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
	<script type="text/javascript" src="../js/admin/imgScroll.js" charset="utf-8"></script>
</body>
<!-- 新增modal -->
	<div class="modal hide fade" id="addModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>首页轮播管理</h3>
		</div>
		<div class="modal-body">
			<p><small class="text-success">新建轮播</small></p>
				<table style="width:100%;">
					<tr>
					<td style="width:20%;">
						<span class="add-on"><i class="halflings-icon globe"></i> 标题 </span>
					</td>
					<td>
						<input class="input-large span5" id="add_title"  type="text" placeholder="请输入滚动标题" maxlength="40" >
					</td>
					</tr>
					<tr>
						<td>
						<span class="add-on"><i class="halflings-icon globe"></i> 链接 </span>
					</td>
					<td>
						<input class="input-large span5" id="add_href"  type="text" placeholder="请输入滚动标题" maxlength="200" >
					</td>
					</tr>
					<tr>
						<td>
						<span class="add-on"> 图片 </span>
						
					</td>
					<td>
						<label title="点击添加图片" id="add_img_label">
						<i class="halflings-icon plus"> </i>
						<input type="file" style="display:none;" name="imgFile" id="add_img" accept="image/*"/>
						<input class="input-large span5" id="add_imgPath"  type="hidden" >
						</label>
					</td>
					</tr>
					</table>
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
			<h3>首页轮播管理</h3>
		</div>
		<div class="modal-body">
			<p><small class="text-success">修改轮播</small></p>			
					<table>
					<tr>
					<td>
						<span class="add-on"><i class="halflings-icon user"></i> 标题 </span>
						<input type="hidden" id="roleId" >
					</td>
					<td>
						<input class="input-large span5" id="edit_title" 
						 type="text" placeholder="请输入标题" maxlength="40" >
					</td>
					</tr>
					<tr>
					<tr>
					<td>
						<span class="add-on"><i class="halflings-icon user"></i> 链接</span>
						<input type="hidden" id="roleId" >
					</td>
					<td>
						<input class="input-large span5" id="edit_href"  type="text"
						 placeholder="请输入链接" maxlength="200" >
					</td>
					</tr>
					<tr>
						<td>
						<span class="add-on"> 图片 </span>
						
					</td>
					<td>
						<label title="点击添加图片" id="edit_img_label">
						<i class="halflings-icon plus"> </i>
						<input type="file" style="display:none;" name="imgFile" id="edit_img" accept="image/*"/>
						<input class="input-large span5" id="edit_imgPath"  type="hidden" >
						<img src="#" style="width:100px;height:80px;"/>
						</label>
					</td>
					</tr>					
					</table>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">返回</a> <a href="#"
				class="btn btn-primary updateSubmit">保存</a>
		</div>
	</div>
</html>
