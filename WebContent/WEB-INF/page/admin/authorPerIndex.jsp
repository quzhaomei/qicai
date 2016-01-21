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
<style type="text/css">
.modal-body {
	max-height: 580px;
}
.imgzone{padding:5px 5px;height:200px;text-align:center;}
.imgzone img{width:150px;height:150px;vartical-align:middle;cursor:pointer;border:1px solid #888;}
.onbord table input{border:1px solid #fff !important;}
.onbord table textarea{border:1px solid #fff !important;}
.bord table input{border:1px solid #888 !important}
.bord table textarea{border:1px solid #888 !important}

.box-content table tr td{vertical-align: top;}
.imgzone .place{height:120px;width:120px;margin:0 auto;line-height:110px; border:1px solid #aaa;font-weight:bold;}
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

			<!-- start: Content -->
			<div id="content" class="span10">
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.html">首页</a></li>
					<li><a href="#">作者中心<i class="icon-angle-right"></i>
					</a></li>
				</ul>
				<div class="row-fluid sortable">
					<div class="box" style="overflow:hidden">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon edit"></i><span class="break"></span>作者个人中心
							</h2>
						</div>
						<div class="box-content onbord"  style="margin:20px 150px 0 150px;border:2px solid #888;height:450px;">
							<h1 style="text-align:center;padding-bottom:50px;">作者个人信息</h1>
							<div class="span6">
							<table style="width:100%;">
								<tr>
								<td>
									<span class="add-on"><i class="halflings-icon user"></i> 
									笔 名 </span>
								</td>
								<td>
									<input class="input-large span12" id="penName" placeholder="#未设置" 
									type="text" maxlength="10" value="${fn:escapeXml(author.penName) }" >
									<input type="hidden" id="operator" value="${author==null?'add':'edit' }"/>
									<input type="hidden" id="authorId" value="${author.authorId }"/>
								</td>
								</tr>
								<tr>
								<td>
									<span class="add-on"><i class="halflings-icon music"></i> 个人介绍 </span>
								</td>
								<td>
									<textarea id="introduce" placeholder="#未设置" style="width:95%;height:100px;resize:none;" 
									maxlength="200" 
									>${fn:escapeXml(author.introduce) }</textarea>
								</td>
								</tr>
								<tr>
								<td>
									<span class="add-on"><i class="halflings-icon globe"></i> 新浪微博 </span>
								</td>
								<td>
										<input class="input-large span12" id="sinaPath"  
										type="text" maxlength="50" placeholder="#未设置" value="${author.sinaPath }" >
								</td>
								</tr>
								<tr>
								<td>
									<span class="add-on"><i class="halflings-icon globe"></i> 腾讯微博</span>
								</td>
								<td>
										<input class="input-large span12" id="txPath"  
										type="text" maxlength="50" placeholder="#未设置" value="${author.txPath }" >
								</td>
								</tr>
							</table>
							</div>
							<div class="span5">
								<div class="imgzone">
								<label id="imgLabel">
								<c:if test="${author.photoPath!=null }">
									<img src="${author.photoPath }" title="点击更换"/> 
								</c:if>
								<div class="place" 
								<c:if test="${author!=null&&author.photoPath!=null }">
									style="display:none;" 
								</c:if>
								>
										上传
									</div>
									<input type="file" class="imgFile" id="imgFile" name="imgFile" style="display:none;" accept="image/*"/>
									<input type="hidden" id="photoPath" value="${author.photoPath }"/>
								</label>
								<p style="font-size:12px;color:#444;">作者头像</p>
								</div>
								<div style="text-align:center;">
								<ad:power uri="../author/perSaveOrUpdate.html">
									<button class="btn btn-success btn-edit">编辑个人信息</button>
									</ad:power>
									<span style="display:none;" class="btn btn-success btn-small btn-save">保存</span>
									<span style="display:none;" class="btn btn-danger btn-small btn-cancel">取消</span>
								</div>
							</div>
						</div>
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
		<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="../js/admin/authorPerIndex.js"></script>
		<script>
		var bak;
		<c:if test="${author!=null}">
		bak=${bak};
		</c:if>
			</script>
</body>
</html>
