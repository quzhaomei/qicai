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
								<i class="halflings-icon user"></i><span class="break"></span>系统广告管理
							</h2>
							<div class="box-icon">
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
								  <th width=10%>描述</th>
								  <th width=10%>页面链接</th>
								  <th width=10%>广告链接</th>
								  <th width=5%>宽度</th>
								  <th width=5%>高度</th>
								  <th width=25%>图片</th>
								  <th width=20%>创建时间</th>
								  <th width=10%>操作</th>
							  </tr>
						  </thead>   
						  <tbody>
						  <c:forEach items="${ads }" var="ad" varStatus="status">
						  	<tr>
								<td>${status.count }</td>
								<td class="center">${ad.descript }</td>
								<td class="center">
								<a href="${ad.pageUrl }" target="_blank">
									${ad.pageUrl }
								</a>
								</td>
								<td class="center">
								<a href="${ad.href }" target="_blank">
									${ad.href }
								</a>
								</td>
								<td class="center">${ad.width }</td>
								<td class="center">${ad.height }</td>
								<td class="center">
								<div style="width:100%;">
								<img src='${ad.imgPath }' style="width:100%;"/>
								</div>
								</td>
								<td class="center">
								<fmt:formatDate value="${ad.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td class="center">
								<ad:power uri="../advertise/saveOrUpdate.html">
								<span class="btn btn-success btn-mini ad_edit" 
								advertiseId="${ad.advertiseId }">设置</span>
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
	<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
	<script>
	$(function(){
		var bak;//数据备份
		$("#content").on("click",".ad_edit",function(){
			var advertiseId=$(this).attr("advertiseId");
			if(!advertiseId){
				layer.msg("页面数据丢失，请刷新页面！")
			}else{
				var param={};
				param.operator="findById";
				param.advertiseId=advertiseId;
				$.post("saveOrUpdate.html",param,function(json){
					if(json.status==1){
						bak=json.data;
						$("#advertiseId").val(bak.advertiseId);
						$("#update_href").val(bak.href);
						$("#update_imgPath").val(bak.imgPath);
						$("#updata_img").attr("src",bak.imgPath);
						$("#updateModal").modal("show");
						
					}else{
						layer.msg(json.message);
					}
				},"json");
			}
		});
		
		//更换广告图片
		$("#update_img_file").on("change", function() {
			var file = this.files[0];
			if (file.type && !file.type.match(/image.*/)) {// 如果支持type，验证图片
				layer.msg("请选择图片文件");
				return;
			}
			if (!(file.type)) {// 不支持type，则进行后缀名匹配
				if (!checkName(_this.value)) {
					layer.msg("请选择图片文件");
					return;
				}
			}
			$.ajaxFileUpload({
				url : '../welcome/uploadImg.html',// 处理图片脚本
				secureuri : false,
				fileElementId : 'update_img_file',// file控件id
				dataType : 'json',
				success : function(data, status) {
					if (data.error == 1) {

						var bak=$("#update_imgPath").attr("bak");
						$("#updata_img").attr("src",bak);
						$("#update_imgPath").val(bak);
					} else {
						$("#updata_img").attr("src",data.url);
						$("#update_imgPath").val(data.url);
					}
				},
				error : function(data, status, e) {
					alert(e);
				}
			});
		});
		
		//提交更新
		$("#updateModal .updateSubmit").on("click",function(){
			var advertiseId=$("#advertiseId").val();
			var imgPath=$("#update_imgPath").val();
			var href=$("#update_href").val();
			if(!advertiseId){
				layer.msg("页面数据丢失，请刷新页面！");
				return;
			}
			if(!href){
				layer.msg("链接不能为空！");
				return;
			}
			if(!imgPath){
				layer.msg("广告图片不能为空！");
				return;
			}
			var param={};
			var isChange=false;
			if(href!=bak.href){
				param.href=href;
				isChange=true;
			}
			if(imgPath!=bak.imgPath){
				param.imgPath=imgPath;
				isChange=true;
			}
			if(!isChange){
				layer.msg("数据没有变化！");
				return;
			}
			param.advertiseId=advertiseId;
			param.operator="edit";
			$.post("saveOrUpdate.html",param,function(json){
				if(json.status==1){
					layer.msg(json.message);
					window.location.reload();
				}else{
					layer.msg(json.message);
				}
			},"json");
		});
		
	});
	</script>
</body>
	
	<!-- 修改modal -->
	<div class="modal hide fade" id="updateModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>广告系统</h3>
		</div>
		<div class="modal-body">
			<p><small class="text-success">修改广告</small></p>			
					<table>
					<tr>
						<td>
							<span class="add-on"><i class="halflings-icon globe"></i> 链接 </span>
						</td>
						<td>
							<input type="hidden" id="advertiseId" >
							<input class="input-large span5" id="update_href"  type="text" placeholder="请输入链接" maxlength="50" >
						</td>
					</tr>
					<tr>
					<td>
						<span class="add-on" >
						<i class="icon-picture"></i>
						 图片 
						 </span>
						<input type="hidden" id="update_imgPath" >

						
					</td>
					<td>
						<label title="点击替换">
						<div style="width:290px;height:160px;">
						<input type="file" id="update_img_file" name="imgFile" style="display:none;" accept="image/*" /> 
						<img style="width:100%;height:100%;" id="updata_img" />
						</div>
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
