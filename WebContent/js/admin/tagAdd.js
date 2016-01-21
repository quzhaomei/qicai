$(".btn-back").click(function() {
	if (window.parent.closeInit) {
		window.parent.closeInit();
	}
});
// 保存操作
$(".btn-submit").on("click", function() {
	var operator = $("#operator").val();
	var param = {};
	param.operator = operator;
	if (operator == "add") {// 如果是增加操作
		var tagName = $("#tagName").val();// 名字
		var href = $("#href").val();// 链接
		var type = $("#type").val();// 类别
		
		var imgPath = $("#imgPath").val();// logo
		var imgWidth = $("#imgWidth").val();// logo宽度
		var imgHeight = $("#imgHeight").val();// logo高度
		var introduce = editor.html();// 正文
		
		if (!tagName) {
			layer.msg("请输入标签。");
			return;
		}
		if (!href) {
			layer.msg("请输入标签链接，暂无用#号");
			return;
		}
		if (!type) {
			layer.msg("请选择标签类别");
			return;
		}
		if (!imgPath) {
			layer.msg("请选择标签图片");
			return;
		}
		if (!imgWidth) {
			layer.msg("请输入图片宽度");
			return;
		}
		if (!imgHeight) {
			layer.msg("请输入图片高度");
			return;
		}
		
		if (!/^\d{1,4}$/.test(imgWidth)) {
			layer.msg("请输入正确格式的缩略图宽度，为1-4位数字");
			return;
		}
		if (!/^\d{1,4}$/.test(imgHeight)) {
			layer.msg("请输入正确格式的缩略图高度，为1-4位数字");
			return;
		}
		
		if (!introduce) {
			layer.msg("请输入内容");
			return;
		}
		
		param.tagName = tagName;
		param.type = type;
		param.href = href;
		param.imgPath = imgPath;
		param.imgWidth = imgWidth;
		param.imgHeight = imgHeight;
		param.introduce=introduce;
		$.post("add.html", param, function(data) {
			if (data.status == 1) {
				layer.msg(data.message, window.parent.location.reload());
			} else {
				layer.msg(data.message);
			}
		}, "json");
	}else if(operator == "edit"){//更新
		var tagId = $("#tagId").val();// 名字
		var tagName = $("#tagName").val();// 名字
		var type = $("#type").val();// logo
		
		var href = $("#href").val();// 链接
		var imgPath = $("#imgPath").val();// logo
		var imgWidth = $("#imgWidth").val();// logo宽度
		var imgHeight = $("#imgHeight").val();// logo高度
		var introduce = editor.html();// 正文
		if(!tagId){
			layer.msg("页面数据丢失，请刷新页面");
			return;
		}
		var param={};
		param.operator=operator;
		param.tagId=tagId;
		if(!tagName){
			layer.msg("请输入标签名字");
			return;
		}
		if(!href){
			layer.msg("请输入标签链接，暂无用#号代替");
			return;
		}
		if (!type) {
			layer.msg("请选择标签类别");
			return;
		}
		if(!imgPath){
			layer.msg("请上传标签图片");
			return;
		}
		if(!imgWidth){
			layer.msg("请输入标签图片宽度");
			return;
		}
		if(!imgHeight){
			layer.msg("请输入标签图片高度");
			return;
		}
		if (!/^\d{1,4}$/.test(imgWidth)) {
			layer.msg("请输入正确格式的缩略图宽度，为1-4位数字");
			return;
		}
		if (!/^\d{1,4}$/.test(imgHeight)) {
			layer.msg("请输入正确格式的缩略图高度，为1-4位数字");
			return;
		}
		if(!introduce){
			layer.msg("请输入品牌介绍");
			return;
		}
		var isChange=false;
		if(tagName!=bak.tagName){
			param.tagName=tagName;
			isChange=true;
		}
		if(href!=bak.href){
			param.href=href;
			isChange=true;
		}
		if(type!=bak.type){
			param.type=type;
			isChange=true;
		}
		if(imgPath!=bak.imgPath){
			param.imgPath=imgPath;
			isChange=true;
		}
		if(imgWidth!=bak.imgWidth){
			param.imgWidth=imgWidth;
			isChange=true;
		}
		if(imgHeight!=bak.imgHeight){
			param.imgHeight=imgHeight;
			isChange=true;
		}
		if(introduce!=bak.introduce){
			param.introduce=introduce;
			isChange=true;
		}
		if(!isChange){
			layer.msg("数据没有任何变化！");
			return;
		}
		$.post("update.html", param, function(data) {
			if (data.status == 1) {
				layer.msg(data.message, window.parent.location.reload());
			} else {
				layer.msg(data.message);
			}
		}, "json");
		
	}
});



$(".reviewImg").on("change", "#scanImg", function() {
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
		fileElementId : 'scanImg',// file控件id
		dataType : 'json',
		success : function(data, status) {
			if (data.error == 1) {
				layer.msg(data.message);
				$(".reviewImg #scanImg").value = "";
				$(".reviewImg #scanImg").files = "";
				$(".reviewImg  .filename").text("");
				$(".reviewImg img").remove();// 移除预览图片
				$("#imgPath").val("");
				$("#imgWidth").val("");
				$("#imgHeight").val("");
			} else {
				prevImg(data.url);
				$("#imgPath").val(data.url);
				 $("#imgWidth").val(data.width);
				 $("#imgHeight").val(data.height);
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
});

function prevImg(url) {
	if ($(".reviewImg img")[0]) {
		$(".reviewImg img").attr("src", url)
	} else {
		$(".reviewImg").append($("<img>").attr("src", url));
	}
}

var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea.kindeditor', {
		resizeType : 1,
		allowImageUpload : true,
		items : [ 'fontname', 'fontsize', 'formatblock', '|', 'forecolor',
				'hilitecolor', 'bold', 'italic', 'underline', 'removeformat',
				'lineheight', 'indent', 'outdent', '|', 'justifyleft',
				'justifycenter', 'justifyright', 'quickformat',
				'insertorderedlist', 'insertunorderedlist', '|', 'image',
				'link', '|', 'source', 'fullscreen' ]

	});
});

// 验证后缀名
function checkName(name) {
	// 获取欲上传的文件路径
	var filepath = name;
	// 为了避免转义反斜杠出问题，这里将对其进行转换
	var re = /(\\+)/g;
	var filename = filepath.replace(re, "#");
	// 对路径字符串进行剪切截取
	var one = filename.split("#");
	// 获取数组中最后一个，即文件名
	var two = one[one.length - 1];
	// 再对文件名进行截取，以取得后缀名
	var three = two.split(".");
	// 获取截取的最后一个字符串，即为后缀名
	var last = three[three.length - 1].toLocaleLowerCase();
	// 添加需要判断的后缀名类型
	var tp = "jpg,gif,bmp,gif,bmp,jpeg,png";
	// 返回符合条件的后缀名在字符串中的位置
	var rs = tp.indexOf(last);
	// 如果返回的结果大于或等于0，说明包含允许上传的文件类型
	if (rs != -1) {
		return true;
	} else {
		return false;
	}
}
