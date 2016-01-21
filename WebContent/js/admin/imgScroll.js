
	$(function(){
		//增加
		$(".addScroll").click(function(){
			$("#add_title").val("");//
			$("#add_href").val("");//
			$("#add_imgPath").val("");
			$("#addModal").modal("show");//展示modal
		});
		$("#add_img_label").on("change", "#add_img", function() {
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
				fileElementId : 'add_img',// file控件id
				dataType : 'json',
				success : function(data, status) {
					if (data.error == 1) {
						layer.msg(data.message);
						$("#add_imgPath").value = "";
						$("#add_imgPath").files = "";
						$("#add_img_label img").remove();// 移除预览图片
						$("#add_imgPath").val("");
					} else {
						if($("#add_img_label img")[0]){
							$("#add_img_label img").attr("src",data.url);
						}else{
							$("#add_img_label").append(
									$("<img>").attr("src",data.url).
									css({"width":"100px","height":"80px"})
							);
						}
						$("#add_imgPath").val(data.url);
					}
				},
				error : function(data, status, e) {
					alert(e);
				}
			});
		});
		//提交新增
		$(".addSubmit").on("click",
		function(){
			var param={};
			param.operator="add";
			param.title=$("#add_title").val();
			param.href=$("#add_href").val();
			param.imgPath=$("#add_imgPath").val();
			if(!param.title){
				layer.msg("标题不能为空！");
				return;
			}
			if(!param.href){
				layer.msg("链接不能为空！");
				return;
			}
			if(!param.imgPath){
				layer.msg("图片不能为空！");
				return;
			}
			$.post("add.html",param,function(data){
				if(data.status==1){
					layer.msg(data.message);
					window.location.reload();
				}else{
					layer.msg(data.message);
				}
			},"json");
			}
		);
		
		var bak;//修改数据备份
		//修改
		$("#content").on("click",".editScroll",function(){
			var scrollId=$(this).attr("scrollId");
			var param={};
			param.operator="findById";
			param.scrollId=scrollId;
			if(scrollId){
			$.post("update.html",param,function(data){
				if(data.status==1){
					bak=data.data;
				$("#edit_title").val(bak.title);
				$("#scrollId").val(bak.scrollId);
				$("#edit_imgPath").val(bak.imgPath);
				$("#edit_img_label img").attr("src",bak.imgPath);
				$("#edit_href").val(bak.href);
				$("#updateModal").modal("show");
				}else{
					layer.msg(data.message);
				}
			},"json");		
			}else{
				layer.msg("页面数据丢失，请刷新页面");
			}
		});
		
		$("#edit_img_label").on("change", "#edit_img", function() {
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
				fileElementId : 'edit_img',// file控件id
				dataType : 'json',
				success : function(data, status) {
					if (data.error == 1) {
						layer.msg(data.message);
						$("#edit_imgPath").value = "";
						$("#edit_imgPath").files = "";
						$("#edit_img_label img").remove();// 移除预览图片
						$("#edit_imgPath").val("");
					} else {
						if($("#edit_img_label img")[0]){
							$("#edit_img_label img").attr("src",data.url);
						}else{
							$("#edit_img_label").append(
									$("<img>").attr("src",data.url).
									css({"width":"100px","height":"80px"})
							);
						}
						$("#edit_imgPath").val(data.url);
					}
				},
				error : function(data, status, e) {
					alert(e);
				}
			});
		});
		
		//提交更新
		$(".updateSubmit").click(
		function(){
			var param={};
			param.operator="edit";
			param.scrollId=bak.scrollId;
			var isChange=false;
			if(!$("#edit_title").val()){
				layer.msg("标题不能为空！");
				return;
			}
			if(!$("#edit_href").val()){
				layer.msg("链接不能为空");
				return;
			}
			if(!$("#edit_imgPath").val()){
				layer.msg("图片不能为空");
				return;
			}
			if($("#edit_title").val()!=bak.title){
				param.title=$("#edit_title").val();
				isChange=true;
			}
			if($("#edit_href").val()!=bak.href){
				param.href=$("#edit_href").val();
				isChange=true;
			}
			if($("#edit_imgPath").val()!=bak.imgPath){
				param.imgPath=$("#edit_imgPath").val();
				isChange=true;
			}
			if(!isChange){
				layer.msg("数据没有任何变化");
				return;
			}
			$.post("update.html",param,function(data){
				if(data.status==1){
					layer.msg(data.message,window.location.reload());
				}else{
					layer.msg(data.message);
				}
			},"json");
		}
		);
		
		//删除
		$("#content").on("click",".changeStatu",function(){
			var status=$(this).attr("status");
			var scrollId=$(this).attr("scrollId");
			var param={};
			param.scrollId=scrollId;
			param.operator="edit";
			var tip="";
			if(status=="0"){//激活
				param.status="1";
				tip="确认激活吗?";
			}else if(status=="1"){//冻结
				param.status="0";
				tip="确认冻结吗";
			}
			layer.confirm(tip, {
				title:"状态切换",
			    btn: ["确定","返回"], //按钮
			    shade: false //不显示遮罩
			}, function(){
				$.post("status.html",param,function(data){
				if(data.status==1){
					layer.msg(data.message,{ icon: 1,time: 1000 },function(){
						window.location.reload();
					});
				}else{
					layer.msg(data.message);
				}
			},"json");
			
			}, function(){
				
			});
			
			
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