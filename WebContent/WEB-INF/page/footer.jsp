<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="container">
	<!-- 底部信息开始 -->
	<div class="panel nohome" id="info">
		<div class="sec sec1">
			第一家汽车财经高端媒体<br> 首家汽车财经上市公司
		</div>
		<div class="sec sec2">
			<img src="images/qrcode_web.png" alt="">
			<h2>汽车财经网</h2>
			<br> <span> 关注我们<br> <strong>官方微博</strong>
			</span>
		</div>
		<div class="sec sec3">
			<img src="images/qrcode_wechat.png" alt="">
			<h2>
				微信号:<br>
				<span>Autofortune2014</span>
			</h2>
			<br> <span> 关注我们<br> <strong>官方微博</strong>
			</span>
		</div>
		<div class="sec sec4">
			021-39007315<br> <span> 24小时服务热线 </span>
		</div>
	</div>
	<!-- 底部信息结束 -->
</div>
<div id="footer">
	<div class="container">
		<div class="logo">
			<img src="images/logofooter.png" alt="">
		</div>

		<div class="contents">
			<div class="links">
				<a href="introduce.html#aboutus">关于我们</a> | <a href="introduce.html#contact">联系我们</a> | <a href="introduce.html#reveal">新闻爆料</a> | <a
						href="introduce.html#joinus">加入我们</a>
			</div>
			<div class="copyright">
			<% Date date=new Date();request.setAttribute("date", date);%>
				Copyright © 2015 <fmt:formatDate value="${date }" pattern="yyyy"/><a href="">auto-biz.cn</a>
			</div>
		</div>

	</div>

</div>
<span class="statistic">
<script language="javascript" type="text/javascript" src="http://js.users.51.la/18666544.js"></script>
<noscript>
<a href="http://www.51.la/?18666544" target="_blank">
<img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/18666544.asp" style="border:none" />
</a></noscript>
</span>

<script src="js/vendor/jquery-1.11.3.min.js"></script>
<script src="js/vendor/jquery.als-1.2.min.js"></script>
<script src="js/vendor/jquery.bxslider.min.js"></script>
<script src="js/vendor/jquery.vticker.min.js"></script>
<script src="js/main.js"></script>

<script src="js/layer/layer.js"></script><!-- layer.msg -->

