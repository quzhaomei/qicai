<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="sidebar-left" class="span2">
	<div class="nav-collapse sidebar-nav">
		<ul class="nav nav-tabs nav-stacked main-menu">
			<c:forEach items="${pageMenus}" var="menu">
				<c:set var="menuId" value="${menu.menuId }"/>
				<c:if test="${menu.parentId==-1}">
					<li>
						<a href="${menu.uri }" class="dropmenu">
						<c:if test="${menu.type==0 }"><!-- 菜单组 -->
						<i class="icon-folder-open"></i> 
						</c:if>
						<c:if test="${menu.type==1 }"><!-- 页面菜单 -->
						<i class="icon-globe"></i> 
						</c:if>
						<span class="hidden-tablet"> ${fn:escapeXml(menu.menuName) } </span> 
						 </a>
						 <c:if test="${menu.type==0}"><!-- 不为操作 -->
						 <ul>
						 	<c:forEach items="${pageMenus}" var="innerMenu">
						 		<c:set var="innerId" value="${innerMenu.menuId }"/>
						 		<c:if test="${innerMenu.parentId==menu.menuId}">
						 			<li>
										<a href="${innerMenu.uri }"> <i class="icon-globe"></i>  ${fn:escapeXml(innerMenu.menuName) }</span> </a></li>
						 		</c:if>
						 	</c:forEach>
						 </ul>
						 </c:if>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>

