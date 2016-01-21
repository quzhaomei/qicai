/*******************************************************************************
 * 
 * qzm--个人
 */
$(function() {
	var index;
	$(document).ajaxStart(function() {
		index = layer.load();
	});
	$(document).ajaxStop(function() {
		layer.close(index);
	});
	$(document).ajaxError(function() {
		layer.msg("系统运行出现异常。");
	});

	// 导航初始化
	$(".navigation a").each(function() {
		var url = window.location.href;
		var href = this.href;
		if (url.indexOf(href) == 0 && href.indexOf("#") == -1) {
			$(this).addClass("active");
		}
	});

	// 图片居中
	$(".artical_img").each(function() {
		var img=$(this).find("img")[0];
		// 从缓存获取时候的处理过程
		
		loadInitImg(img);
		
		// 第一次加载完毕事件
		img.onload = function() {
			loadInitImg(this);
		}
		// 事件结束
	});

	// 登出
	$("#logout").on("click", function() {
		layer.confirm("确认退出吗？", {
			title : "汽车财经提示",
			btn : [ "确认", "返回" ],
			shade : false
		}, function() {
			$.post("out.html", function(json) {
				if (json.status == 1) {
					layer.msg("登出成功", {
						time : 1000
					}, function() {
						window.location.href = "index.html";
					});
				}
			}, "json")

		}, function() {

		});
	});

	// 支持文章
	$(".artical-up").on("click", function() {
		var $i = $(this).find("i");
		var $num = $(this).find("strong");
		var param = {};
		param.articalId = $(this).attr("articalId");
		$.post("up.html", param, function(data) {
			if (data.status == 0) {
				layer.msg(data.message, {
					time : 400
				});
			} else if (data.status == 1) {// 点赞成功
				layer.msg(data.message, {
					time : 400
				});
				$i.addClass("clicked");
				$num.text(data.data);
			}
		}, "json");
	});

	// 反对文章
	$(".artical-down").on("click", function() {
		var $i = $(this).find("i");
		var $num = $(this).find("strong");
		var param = {};
		param.articalId = $(this).attr("articalId");
		$.post("down.html", param, function(data) {
			if (data.status == 0) {
				layer.msg(data.message, {
					time : 400
				});
			} else if (data.status == 1) {
				layer.msg(data.message, {
					time : 400
				});
				$i.addClass("clicked");
				$num.text(data.data);
			}
		}, "json");
	});

	// 收藏文章
	$(".storeArtical").on("click", function() {
		var $i = $(this).find("i");
		var param = {};
		param.articalId = $(this).attr("articalId");
		$.post("storeArtical.html", param, function(json) {
			if (json.status == 1) {// 操作成功
				layer.msg(json.message, {
					time : 400
				});
				$i.addClass("clicked");
			} else if (json.status == 0) {// 操作出错
				layer.msg(json.message);
			} else if (json.status == 3) {// 请先登陆
				layer.msg(json.message);
			}
		}, "json");
	});
	// 分页
	$(".pagination li").on("click", function() {
		var index = $(this).attr("index");
		if (index) {
			$("#pageIndex").val(index);
			$("#newsForm").submit();
		}
	});
	
	  //第三方登陆
    $(".otherLogin").on("click",function(){
  	  var uri=$(this).attr("data-uri");
  	  var height=$(this).attr("data-height");
  	  var width=$(this).attr("data-width");
  	  var iTop = (window.screen.availHeight-30-height)/2;       //获得窗口的垂直位置;
  	  var iLeft = (window.screen.availWidth-10-width)/2;           //获得窗口的水平位置;
  	  window.open (uri,"QQLOGIN",
  	"height="+height+",width="+width+",top="+iTop+",left="+iLeft+",toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no") ;
  	  
    });

});
// 把form转换为ajax提交，接受json数据
jQuery.fn.extend({
	toData : function() {
		var param = {};
		// 提取form中的参数
		this.find("input[name]").each(function() {
			var name = this.name;
			var value = this.value;
			param[name] = value;
		});
		return param;
	}
});

function initImg($img) {
	// 图片居中
	$img.each(function() {
		var $img = $(this).find("img");
		var width1 = $(this).width();
		var height1 = $(this).height();

		var width2 = $img.attr("o-width");
		var height2 = $img.attr("o-height");
		if (width2 > width1 && height2 > height1) {
			if ((width2 / width1) > (height2 / height1)) {
				$img.css("height", "100%");
				width2 = width2 * (height1 / height2);
				var left = (width2 - width1) / 2;
				$img.css("left", "-" + left + "px");
			} else {
				$img.css("width", "100%");
				height2 = height2 * (width1 / width2);
				var top = (height2 - height1) / 2;
				$img.css("top", "-" + top + "px");
			}
		} else {
			if (width2 < width1) {
				width2 = $img.width();
				height2 = height2 * (width1 / width2);
			} else {
				width2 = width2 * (height1 / height2);
				height2 = $img.height();
			}
			if (width1 - width2 > -10 && width1 - width2 < 10) {
				var top = (height2 - height1) / 2;
				$img.css("top", "-" + top + "px");
			} else if (height1 - height2 > -10 && height1 - height2 < 10) {
				var left = (width2 - width1) / 2;
				$img.css("left", "-" + left + "px");
			}
		}
	});
}

function loadInitImg(obj) {
	var $img = $(obj);
	var width1 = $(obj).parents(".artical_img").width();
	var height1 = $(obj).parents(".artical_img").height();
	var width2 = 0;
	var height2 = 0;
	
	var widthDate = $img.attr("o-width");
	var heightDate = $img.attr("o-height");

	if (!widthDate || !heightDate) {
		return;
	}
	if ((widthDate - 100) <= 0 && (heightDate - 100) <= 0) {
		var img = new Image();
		img.src = $img[0].src;
		width2 = img.width;
		height2 = img.height;
	} else {
		width2 = widthDate;
		height2 = heightDate;
	}

	if (width2 > width1 && height2 > height1) {
		if ((width2 / width1) > (height2 / height1)) {
			$img.css("height", "100%");
			width2 = width2 * (height1 / height2);
			var left = (width2 - width1) / 2;
			$img.css("left", "-" + left + "px");
		} else {
			$img.css("width", "100%");
			height2 = height2 * (width1 / width2);
			var top = (height2 - height1) / 2;
			$img.css("top", "-" + top + "px");
		}
	} else {
		if (width2 < width1) {
			width2 = $img.width();
			height2 = height2 * (width1 / width2);
		} else {
			width2 = width2 * (height1 / height2);
			height2 = $img.height();
		}
		if (width1 - width2 > -10 && width1 - width2 < 10) {
			var top = (height2 - height1) / 2;
			$img.css("top", "-" + top + "px");
		} else if (height1 - height2 > -10 && height1 - height2 < 10) {
			var left = (width2 - width1) / 2;
			$img.css("left", "-" + left + "px");
		}
	}
}