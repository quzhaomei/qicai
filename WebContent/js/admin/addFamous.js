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
		var name = $("#name").val();// 名字
		var ename = $("#ename").val();// 英文名字
		var nationality = $("#nationality").val();//国籍
		var birthPlace = $("#birthPlace").val();//出生地
		var birthDay = $("#birthDay").val();//出生日期
		var job = $("#job").val();//出生日期
		
		var imgPath = $("#imgPath").val();// logo
		var imgWidth = $("#imgWidth").val();// logo宽度
		var imgHeight = $("#imgHeight").val();// logo高度
		var introduce = editor.html();// 正文
		
		if (!name) {
			layer.msg("请输入中文名字");
			return;
		}
		if (!job) {
			layer.msg("请输入职业");
			return;
		}
		if (!imgPath) {
			layer.msg("请上传名人头像");
			return;
		}
		if (!imgWidth) {
			layer.msg("请设置头像宽度");
			return;
		}
		if (!imgHeight) {
			layer.msg("请设置头像宽度");
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
			layer.msg("请输入名人介绍");
			return;
		}
		
		param.name = name;
		param.ename = ename;
		param.nationality = nationality;
		param.birthPlace = birthPlace;
		param.birthDay = birthDay;
		param.job=job;
		
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
		var peopleId=$("#peopleId").val();
		var name = $("#name").val();// 名字
		var ename = $("#ename").val();// 英文名字
		var nationality = $("#nationality").val();//国籍
		var birthPlace = $("#birthPlace").val();//出生地
		var birthDay = $("#birthDay").val();//出生日期
		var job = $("#job").val();//出生日期
		
		var imgPath = $("#imgPath").val();// logo
		var imgWidth = $("#imgWidth").val();// logo宽度
		var imgHeight = $("#imgHeight").val();// logo高度
		var introduce = editor.html();// 正文
		
		param.peopleId=peopleId;
		if (!peopleId) {
			layer.msg("页面数据丢失，请刷新页面");
			return;
		}
		if (!name) {
			layer.msg("请输入中文名字");
			return;
		}
		if (!job) {
			layer.msg("请输入职业");
			return;
		}
		if (!imgPath) {
			layer.msg("请上传名人头像");
			return;
		}
		if (!imgWidth) {
			layer.msg("请设置头像宽度");
			return;
		}
		if (!imgHeight) {
			layer.msg("请设置头像宽度");
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
			layer.msg("请输入名人介绍");
			return;
		}
		
		var isChange=false;
		if(introduce!=bak.introduce){
			param.introduce=introduce;
			isChange=true;
		}
		if(job!=bak.job){
			param.job=job;
			isChange=true;
		}
		if(birthDay!=$("#birthDay").attr("bak")){
			param.birthDay=birthDay;
			isChange=true;
		}
		if(birthPlace!=bak.birthPlace){
			param.birthPlace=birthPlace;
			isChange=true;
		}
		if(nationality!=bak.nationality){
			param.nationality=nationality;
			isChange=true;
		}
		if(ename!=bak.ename){
			param.ename=ename;
			isChange=true;
		}
		if(name!=bak.name){
			param.name=name;
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
