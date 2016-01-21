<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/adtag" prefix="a"%>
<c:forEach items="${onwerList }" var="onwer" varStatus="status">
	<div class="item ${(status.index+index)%2!=0?"alt":"" }">
		<div class="contents">
			<h2>
				<a:trim length="18" content="${onwer.title }"></a:trim>
			</h2>
			<div class="text">
				<a:trim length="80" content="${onwer.quote }"></a:trim>
			</div>
			<div class="mis">
				<div class="more">
					<a href="newDetail.html?articalId=${onwer.articalId }">【详细】</a>
				</div>
				<div class="datetime">
					<fmt:formatDate value="${onwer.createDate }"
						pattern="yyyy年MM月dd日 HH:mm" />
				</div>
			</div>

		</div>
		<div class="img">
			<div class="artical_img">
				<img o-width="${onwer.scanImgWidth }"
					o-height="${onwer.scanImgHeight }" src="${onwer.scanImgPath }" />
			</div>
		</div>
	</div>
</c:forEach>
