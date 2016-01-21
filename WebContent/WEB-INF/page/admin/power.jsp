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

<link href="../css/admin/menuManager.css" rel="stylesheet">

<!-- start: 网站logo -->
<link rel="shortcut icon" href="../img/avatar.jpg">
<!-- end: Favicon -->
<style type="text/css">
.icon-leaf{color:green;}
</style>
</head>
<!-- start: Header -->
<div class="navbar">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse"> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a> <a class="brand" href="../welcome/index.html"><span>汽车财经网站系统后台管理</span> </a>
			<div class="nav-no-collapse header-nav">
				<ul class="nav pull-right">
					<!-- start: User Dropdown -->
					<li class="dropdown"><a class="btn dropdown-toggle"
						data-toggle="dropdown" href="#"> <i
							class="halflings-icon white user"></i> ${sessionScope.adminUserSession.nickname }<span class="caret"></span>
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
					<li><i class="icon-home"></i> <a href="index.html">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">系统设置<i class="icon-angle-right"></i> </a>
					</li>
					<li><a href="#">权限管理</a>
					</li>
				</ul>
				<p>
					<small class="text-error">-角色权限分配 <br />-请先选择需要更改权限的角色，再进行角色权限操作！
					</small>
				</p>
				<div id="searchDiv">
				<table>
				<tr>
				<td class="text-center text-success"><select id="pageSelect" data-rel="chosen" style='width:180px;'>
							<option value="-1">- 请 选 择 -</option>
						<c:forEach items="${allRoles}" var="role">
							<option value="${role.roleId }">${role.roleName }</option>
						</c:forEach>
						</select>						
				</td>
				<td><button class="btn btn-primary btn-small" id="searchBtn">
				<i class="halflings-icon search white"></i></button></td>
				</tr>
				</table>
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
<c:import url="javascript.jsp" charEncoding="utf-8"></c:import>
<!-- end: JavaScript-->
<script type="text/javascript"  src="../js/admin/power.js" charset="utf-8">

</script>

</body>
</html>

