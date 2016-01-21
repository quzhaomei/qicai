$(".btn-back").click(function(){
		if(window.parent.closeInit){
			window.parent.closeInit();
		}
	});
//上传文件
$(".pdfSource").on("change","#pdfSource",function(){
	$.ajaxFileUpload({
		url : '../welcome/uploadSource.html',// 处理图片脚本
		secureuri : false,
		fileElementId : 'pdfSource',// file控件id
		dataType : 'json',
		success : function(data, status) {
			if (data.error == 1) {
				$(".pdfSource").parent("div").find("a.source-a").remove();
				$(".pdfSource em").text("上传");
				layer.msg(info+"上传失败，请重新上传");
				$("#sourcePath").val("");
			} else {
				$(".pdfSource").parent("div").find("a.source-a").remove();
				var info=$("#sourcePath").attr("rel-info");
				$(".pdfSource").parent("div").prepend($("<a>").text(data.url)
						.attr("href",data.url).addClass('source-a').attr("target","_blank"));
				$(".pdfSource em").text("重新选择");
				layer.msg(info+"上传成功")
				$("#sourcePath").val(data.url);
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
});



	//保存操作
	$(".btn-submit").on("click",function(){
		var operator=$("#operator").val();
		var articalId=$("#articalId").val();
		var param={};
		param.operator=operator;
		param.articalId=articalId;
		if(operator=="edit"&&articalId){//如果是增加操作
			var title=$("#title").val();//标题
			var resourceId=$("#resource").val();//来源
			var authorId = $("#authorId").val();// 作者
			var tagIds=$("#tag").val();//标签
			var quote=$("#quote").val();//引言
			var scanImgPath=$("#scanImgPath").val();//缩略图
			var scanImgWidth = $("#scanImgWidth").val();// 缩略图
			var scanImgHeight = $("#scanImgHeight").val();// 缩略图
		
			var content=editor.html();//正文
			
			var videoType=$("#videoType").val();//视频种类
			//非空检测
			if(!title){layer.msg("请输入资讯标题。");return;}
			if(!resourceId){layer.msg("请选择文章来源。");return;}
//			if(!tag){layer.msg("请选择文章标签。");return;}
			if(!quote){layer.msg("请输入引言。");return;}
			if(!scanImgPath){layer.msg("请上传缩略图。");return;}
			if(!scanImgWidth){layer.msg("请输入缩略图宽度");return;}
			if(!scanImgHeight){layer.msg("请输入缩略图高度");return;}
			
			if($("#videoType")[0]&&!videoType){layer.msg("请选择视频种类。");return;}
			
			if (scanImgWidth&&!/^\d{1,4}$/.test(scanImgWidth)) {
				layer.msg("请输入正确格式的缩略图宽度，为1-4位数字");
				return;
			}
			if (scanImgHeight&&!/^\d{1,4}$/.test(scanImgHeight)) {
				layer.msg("请输入正确格式的缩略图高度，为1-4位数字");
				return;
			}
			
			if(!content){layer.msg("请输入内容");return;}
			if($("#people")[0]&&!$("#people").val()){layer.msg("请选择名人");return;}
			if($("#startDate")[0]&&!$("#startDate").val()){
				var info=$("#startDate").attr("rel-info");
				if(info){
					layer.msg("请选择"+info);
				}else{
					layer.msg("请选择起始时间")
				}
				return;}
			if($("#endDate")[0]&&!$("#endDate").val()){
				var info=$("#endDate").attr("rel-info");
				if(info){
					layer.msg("请选择"+info);
				}else{
					layer.msg("请选择结束")
				}
				return;}
			if($("#sourcePath")[0]&&!$("#sourcePath").val()){
				var info=$("#sourcePath").attr("rel-info");
				if(info){
					layer.msg("请上传"+info);
				}else{
					layer.msg("请上传文件");
				}
				return;}
			//改变检测
			var isChange=false;
			if(title!=bak.title){
				param.title=title;
				isChange=true;
			}
			if(resourceId!=bak.resource.resourceId){
				param.resourceId=resourceId;
				isChange=true;
			}
			if(authorId!=bak.author.authorId){
				param.authorId=authorId;
				isChange=true;
			}
			if($("#people")[0]){//检测名人
				if($("#people").val()!=bak.famousPeople.peopleId){
					param.peopleId=$("#people").val();
					isChange=true;
				}	
			}
			if($("#videoType")[0]){//检测视频状态
				if($("#videoType").val()!=bak.video.type){
					param.videoType=$("#videoType").val();
					isChange=true;
				}	
			}
			
			if($("#startDate")[0]){
				if($("#startDate").val()!=$("#startDate").attr("bak")){
					param.startDate=$("#startDate").val();
					isChange=true;
				}
			}
			if($("#endDate")[0]){
				if($("#endDate").val()!=$("#endDate").attr("bak")){
					param.endDate=$("#endDate").val();
					isChange=true;
				}
			}
			if($("#sourcePath")[0]){
				if($("#sourcePath").val()!=bak.sourcePath){
					param.sourcePath=$("#sourcePath").val();
					isChange=true;
				}
			}
			if($("#locationPath")[0]){
				if($("#locationPath").val()!=bak.sourcePath){
					param.locationPath=$("#locationPath").val();
					isChange=true;
				}
			}
			
			
			var tagChange=false;
			//标签检测开始
			if((!bak.tagList||bak.tagList.length==0)&&!tagIds){//都为空
				tagChange=false;
			}else if(tagIds&&bak.tagList.length!=0&&bak.tagList.length==tagIds.length){//长度相同，开始比较内容
				for(var outer=0;outer< bak.tagList.length;outer++){
					for(var inner=0; inner<tagIds.length;inner++){
						var innerId=tagIds[inner];
						var outId=bak.tagList[outer].tagId;
						if(outId==innerId){
							break;
						}else if(innerId!=outId&&inner==tagIds.length-1){
							tagChange=true;
							break;
						}
					}
				}
			}else{
				tagChange=true;
			}
			//标签检测结束
			if(tagChange){
				if(tagIds){
					param.tagIds=tagIds;
				}else{
					param.tagIds=[0];
				}
			}
			if(quote!=bak.quote){
				param.quote=quote;
				isChange=true;
			}
			if(scanImgPath!=bak.scanImgPath){
				param.scanImgPath=scanImgPath;
				isChange=true;
			}
			if(scanImgHeight!=bak.scanImgHeight){
				param.scanImgHeight=scanImgHeight;
				isChange=true;
			}
			if(scanImgWidth!=bak.scanImgWidth){
				param.scanImgWidth=scanImgWidth;
				isChange=true;
			}
			if(content!=bak.content){
				param.content=content;
				isChange=true;
			}
			if(!isChange&&!tagChange){
				layer.msg("未检测到变化");
				return;
			}
			
//			param.title=title;
//			param.resourceId=resourceId;
//			param.tagIds=tagIds;
//			param.quote=quote;
//			param.scanImgPath=scanImgPath;
//			param.content=content;
			$.post("update.html",param,function(data){
				if(data.status==1){
					layer.msg(data.message,window.parent.location.reload());
				}else{
					layer.msg(data.message);
				}
			},"json");
		}else{
			layer.msg("页面数据缺失，请刷新页面");
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
					$("#scanImgPath").val("");
					$("#scanImgWidth").val("");
					$("#scanImgHeight").val("");
				} else {
					prevImg(data.url);
					$("#scanImgPath").val(data.url);
					 $("#scanImgWidth").val(data.width);
					 $("#scanImgHeight").val(data.height);
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	});
	
	function prevImg(url){
		if($(".reviewImg img")[0]){
			$(".reviewImg img").attr("src",url)	
		}else{
    		$(".reviewImg").append($("<img>").attr("src",url));
		}
	}
	
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea.kindeditor', {
			resizeType : 1,
			allowImageUpload : true,
			items : [
				'fontname', 'fontsize', 'formatblock','|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat','lineheight','indent','outdent', '|', 'justifyleft', 'justifycenter', 'justifyright','quickformat', 'insertorderedlist',
				'insertunorderedlist', '|',  'image', 'link','|', 'source','fullscreen']
				
		});
	});
	
	//验证后缀名
	 function checkName(name){
		 //获取欲上传的文件路径
		var filepath = name; 
		//为了避免转义反斜杠出问题，这里将对其进行转换
		var re = /(\\+)/g; 
		var filename=filepath.replace(re,"#");
		//对路径字符串进行剪切截取
		var one=filename.split("#");
		//获取数组中最后一个，即文件名
		var two=one[one.length-1];
		//再对文件名进行截取，以取得后缀名
		var three=two.split(".");
		 //获取截取的最后一个字符串，即为后缀名
		var last=three[three.length-1].toLocaleLowerCase();
		//添加需要判断的后缀名类型
		var tp ="jpg,gif,bmp,gif,bmp,jpeg,png";
		//返回符合条件的后缀名在字符串中的位置
		var rs=tp.indexOf(last);
		//如果返回的结果大于或等于0，说明包含允许上传的文件类型
		if(rs!=-1){
		 return true;
		 }else{ 
		 return false;
	  }
  }

