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
label.error{
	display: inline-block;
	*display: inline;
	*zoom:1;
	margin-left:15px;
	color:#2D89EF;
	background: #e2e2e2;
	padding:5px 10px;
	}
label.error:before{
  content:"×";
  color:#2D89EF;
}
.errorDiv{
display: inline-block;
	*display: inline;
	*zoom:1;
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
					class="icon-bar"></span> </a> <a class="brand" href="../welcome/index.html"><span>汽车财经网站系统后台管理</span>
				</a>
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> 	${sessionScope.adminUserSession.nickname } <span class="caret"></span>
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
					<li><i class="icon-home"></i> <a href="index.html">首页</a> <i
						class="icon-angle-right"></i></li>
				<!-- 	<li><a href="#">系统设置<i class="icon-angle-right"></i> </a> -->
					<li><a href="#">系统用户管理</a>
					</li>
				</ul>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon user"></i><span class="break"></span>系统用户管理
							</h2>
							<div class="box-icon">
							<!-- 新增用户， -->
							<ad:power  uri="../user/add.html">
								<a href="#" class="addUser"><i
									class="halflings-icon plus"></i> </a> 
							</ad:power>	
									<a href="#"
									class="btn-minimize"><i class="halflings-icon chevron-up"></i>
								</a> <a href="#" class="btn-close"><i
									class="halflings-icon remove"></i> </a>
							</div>
						</div>
						
						<!-- 数据展示 -->
						<div class="box-content">
						<!-- table框架 -->
						<form action="index.html" method="post" id="myform">
						<input name="pageIndex" id="pageIndex" type="hidden" value="${pageResult.pageIndex}"/>
						<input name="pageSize" type="hidden" value="${pageResult.pageSize}"/>
					<select name="type" rel="chosen" style="width:150px;" id="tagType">
							<option value="99" ${type=='99'?"selected='selected'":"" }>-所 有-</option>
							<option value="0" ${type=='0'?"selected='selected'":"" }>-门户账号-</option>
							<option value="1" ${type=='1'?"selected='selected'":"" }>-管理账号-</option>
						</select>
						</form>
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th >昵称</th>
										<th >登陆名</th>
										<th >电话号码</th>
										<th style="width: 120px;">角色</th>
										<th>创建者</th>
										<th>创建时间</th>
										<th>更新者</th>
										<th>更新时间</th>
										<th >账号类型</th>
										<th >状态</th>
										<th style="width: 150px;">操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${pageResult.param }" var="user">
									<tr>
										<td>${fn:escapeXml(user.nickname) }</td>
										<td>${user.loginname }</td>
										<td>${user.phone }</td>
										</td>
										<td>
											<c:forEach items="${user.roles }" var="role">
												<c:if test="${role.status==1 }">
													<span class='label label-success'>${role.roleName }</span>
												</c:if>
												<c:if test="${role.status!=1 }">
													<span class='label'>${role.roleName }</span>
												</c:if>
											</c:forEach>
										</td>
										<td>${user.createUserDTO.nickname }</td>
										<td><fmt:formatDate value="${user.createDate }" pattern="yyyy/MM/dd"/> </td>
										<td>${user.updateUserDTO.nickname }</td>
										<td><fmt:formatDate value="${user.updateDate }" pattern="yyyy/MM/dd"/></td>
										<td>
										  <c:choose>
											  <c:when test="${user.type==0 }">
											  		<span class="label">门户账号</span>
											  </c:when>
											  <c:when test="${user.type==1 }">
											  		<span class="label label-success">管理账号</span>
											  </c:when>
										  </c:choose>
										</td>
										<td>${user.status==1?"<span class='label label-success'>激活</span>":
										"<span class='label label-error'>冻结</span>" }</td>
										<td>
										<!-- 更新用户 -->
										<ad:power  uri="../user/update.html">
											<a class="btn btn-success btn-mini editUser" userid="${user.adminUserId }" href="#"> 
												<i class="halflings-icon white edit"></i> 修 改</a>
										</ad:power>
										<!-- 状态切换 -->
										<ad:power  uri="../user/status.html">		 
											<a class="btn btn-warn orange  btn-mini changeStatu" userid="${user.adminUserId  }" status="${user.status }" href="#"> 
												<i class="halflings-icon refresh white"></i>状 态</a>
										</ad:power>
											
										<!-- 重置密码 -->
										<ad:power  uri="../user/reset.html">		
											<a class="btn btn-info  btn-mini reset" userid="${user.adminUserId  }"  href="#"> 
												<i class="halflings-icon  halflings-icon repeat white"></i>重置密码</a>
										</ad:power>
										
										<!-- 删除用户 -->
										<ad:power   uri="../user/delete.html">		
											<a class="btn btn-danger  btn-mini delete" userid="${user.adminUserId }" status="0" href="#"> 
												<i class="halflings-icon trash white"></i> 删除</a>
										</ad:power>
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
				href="#">quzhaomei@com</a> </span>
		</p>
	</footer>
	<!-- start: JavaScript-->
	<c:import url="javascript.jsp"></c:import>
	<!-- end: JavaScript-->
	<script type="text/javascript" src="../js/admin/adminUser.js?v=1" >
	
	</script>
</body>

<!-- 新增modal -->
	<div class="modal hide fade" id="addModal" style="top:5%;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>系统用户管理</h3>
		</div>
		<div class="modal-body">
			<p><small class="text-success">新建系统用户</small></p>
				
				<form action="#" id="addForm">
				<table class="table table-bordered">
				<tr>
					<td style="width:90px"><span class="add-on"> * 昵称  </span></td>
					<td><input class="input-large span3" id="add_nickname" name="add_nickname"  type="text" placeholder="请输入昵称" maxlength="10" > 
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"><i class="halflings-icon list"></i> 权限赋予  </span> </td>
					<td><select multiple rel="chosen" name="add_roleList" id="add_roleList" data-placeholder="请选择用户角色（可多选）" style="width:200px;">
					<c:forEach items="${roles }" var="role">
						<option value="${role.roleId }">${fn:escapeXml(role.roleName) }</option>
					</c:forEach>
					</select> 
					 </td>
				</tr>
				<tr>
					<td> <span class="add-on">* 登录名  </span></td>
					<td><input class="input-large span3" id="add_loginname"  name="add_loginname" type="text" placeholder="请输入登录名" maxlength="20" >
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"> * 电话号码  </span></td>
					<td><input class="input-large span3" id="add_phone" name="add_phone" type="text" placeholder="请输入电话号码" maxlength="11" /> 
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"> * 邮箱</span></td>
					<td><input class="input-large span3" id="add_email" name="add_email" type="text" placeholder="请输入用户邮箱"  maxlength="30"/>
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"> 备注</span></td>
					<td colspan="1"><textarea id="description" maxlength="200" cols="8" rows="3" style="width:95%;height:95%;resize:none;"></textarea></td>
				</tr>
				</table>
				</form>
				<div>
				<small class="text-error">新建用户的密码，初始化为该用户的手机号码,<span class="text-arror"> *</span> 为必填</small>
				</div>				
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">返回</a> <a href="#"
				class="btn btn-primary addSubmit">保存</a>
		</div>
	</div>
	
	<!-- 修改modal -->
	<div class="modal hide fade" id="updateModal" style="top:0%;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>系统用户管理</h3>
		</div>
		<div class="modal-body">
			<p><small class="text-success">修改管理员</small></p>			
				<form action="#" id="editForm">
				<input type="hidden" id="edit_userId" />
				<table class="table table-bordered">
				<tr>
					<td style="width:90px"><span class="add-on"> 昵称  </span></td>
					<td><input class="input-large span3" id="edit_nickname" name="edit_nickname"  type="text" placeholder="请输入昵称" maxlength="10" > 
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"><i class="halflings-icon list"></i> 权限赋予  </span> </td>
					<td><select multiple class="roleSelect" name="edit_roleArr" rel="chosen" id="edit_roleArr" data-placeholder="请选择用户角色（可多选）" style="width:200px;">
							<c:forEach items="${roles }" var="role">
								<option value="${role.roleId }">${fn:escapeXml(role.roleName) }</option>
							</c:forEach>
					</select> 
					 </td>
				</tr>
				<tr>
					<td> <span class="add-on"> 登录名  </span></td>
					<td><input class="input-large span3 "  id="edit_loginname"  name="edit_loginname" type="text" placeholder="请输入登录名" maxlength="20" />
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"> 电话号码  </span></td>
					<td><input class="input-large span3" id="edit_phone" name="edit_phone" type="text" maxlength="11"/> 
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td><span class="add-on">邮箱</span></td>
					<td><input class="input-large span3" id="edit_email" name="edit_email" type="text" placeholder="请输入角色邮箱"  maxlength="30"/>
					<div class="errorDiv"></div>
					</td>
				</tr>
				<tr>
					<td>
						<span class="add-on"> 账号类型 </span>
					</td>
					<td>
						<label class="label" >门户账号： <input type="radio" name="status" class="userType"  value="0"/></label>
						<label class="label label-success" >管理账号： <input type="radio" name="status" class="userType"  value="1"/></label>
					</td>
				</tr>
				<tr>
					<td><span class="add-on"> 备注</span></td>
					<td colspan="1"><textarea id="edit_description" maxlength="200" cols="8" rows="3" style="width:95%;height:95%;resize:none;"></textarea></td>
				</tr>
				</table>
				</form>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">返回</a> <a href="#"
				class="btn btn-primary updateSubmit">保存</a>
		</div>
	</div>
</html>
