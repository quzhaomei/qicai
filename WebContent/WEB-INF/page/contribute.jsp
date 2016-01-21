<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/adtag" prefix="a"%>
<!doctype html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>投稿-汽车财经</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/fontello.css">

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<style type="text/css">
</style>
</head>
<body>
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

	<!-- 广告位开始 -->
	<div class="abad" style="width: 1060px; height: 100px;  margin: auto;">
		<!-- 广告位 -->
		<a:output code="contribute_1" descript="投稿页面头部广告位" height="100"
			width="1060" href="#" />
	</div>
	<!-- 广告位结束 -->

	<jsp:include page="/menus.html"></jsp:include>


	<div id="main" class="container nohome">


		<div class="panel" id="contribute">
			<div class="title">
				<h1>投稿须知</h1>
			</div>

			<div class="contents">

				<h2>有稿自远方来，不亦乐乎</h2>
				<p>
					汽车财经网作为一个信息分享和交流的平台，欢迎各位有识之士积极投稿，加入我们的大家庭。请将文章直接发送至<a
						href="mailto:qichecaijing@163.com">qichecaijing@163.com</a>，小编会对选用的稿件进行邮件通知或电话确认。
				</p>
				<h2>我们是“实事求是派”</h2>
				<p>围绕“汽车”的话题，欢乐吐槽亦可，资讯分享亦可，时事评论亦可，但请务必实事求是，有感而发，真实用心的作品永远是我们的“座上宾”。</p>
				<h2>礼轻情意重</h2>
				<p>为表达对您的感谢和尊重，我们每月会依据各项客观指标对文章进行评比，优秀的文章我们将给予奖金激励。</p>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
<script type="text/javascript">
</script>
</html>

