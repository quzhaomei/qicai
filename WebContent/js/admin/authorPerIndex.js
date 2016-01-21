	$("#imgLabel").on("click",function(){
			$(".btn-edit").click();
		});
		
		$(".btn-edit").on("click",function(){
			$(".box-content").removeClass("onbord").addClass("bord");
			$(".btn-save").fadeIn();
			$(".btn-cancel").fadeIn();
			$(".btn-edit").hide();
		});
		$(".btn-cancel").on("click",function(){
			$(".box-content").removeClass("bord").addClass("onbord");
			$(".btn-save").hide();
			$(".btn-cancel").hide();
			$(".btn-edit").fadeIn();
		});

		$(".imgzone").on("change",".imgFile",function(){
			var file=this.files[0];
			var _this=this;
			if(file.type&&!file.type.match(/image.*/)){//如果支持type，验证图片
				layer.msg("请选择图片文件");
				_this.files="";
				_this.value="";
				return;
			}
			if(!(file.type)){//不支持type，则进行后缀名匹配
				if(!checkName(_this.value)){
					layer.msg("请选择图片文件");
					_this.value="";
					_this.files="";
					return;
				}
			}
			$.ajaxFileUpload({
		        url:'../welcome/uploadImg.html',//处理图片脚本
		        secureuri :false,
		        fileElementId :'imgFile',//file控件id
		        dataType : 'json',
		        success : function (data, status){
		        	if(data.error==1){
		        		layer.msg(data.message);
		        		$(".imgzone .imgFile").value="";
		        		$(".imgzone .imgFile").files="";
		        		$(".imgzone label img").remove();//移除预览图片
		        		$(".place").show();
		        		$("#photoPath").val("");
		        	}else{
		        		$(".imgzone label img").remove();//移除预览图片
		        		$(".imgzone label").append($("<img>").attr("src",data.url));//预览图片
		        		$(".place").hide();
		        		$("#photoPath").val(data.url);
		        	}
		        },
		        error: function(data, status, e){
		            alert(e);
		        }
			});
		});
		
		$(".btn-save").on("click",function(){
			var penName=$("#penName").val();//笔名
			var introduce=$("#introduce").val();//介绍
			var sinaPath=$("#sinaPath").val();
			var txPath=$("#txPath").val();
			var photoPath=$("#photoPath").val();
			if(!penName){
				layer.msg("请输入笔名");
				return;
			}
			if(!photoPath){
				layer.msg("请上传头像");
				return;
			}
			if(introduce&&introduce.length>200){
				layer.msg("个人介绍200字以内");
				return;
			}
			var param={};
			 param.operator=$("#operator").val();
			if(param.operator=="add"){//如果是添加，
			 param.penName=penName;
			 param.introduce=introduce;
			 param.sinaPath=sinaPath;
			 param.txPath=txPath;
			 param.photoPath=photoPath;
				$.post("perSaveOrUpdate.html",param,function(json){
					if(json.status==1){
						layer.msg(json.message,{ icon: 1,time: 1000 },function(){
							window.location.reload();
							});
					}else{
						layer.msg(json.message);
					}
				},"json");
				
			}else if(param.operator=="edit"){
				param.authorId=$("#authorId").val();
				if(!param.authorId){
					layer.msg("页面数据缺失，请刷新页面！");
					return;
				}
				var isChange=false;
				if(penName!=bak.penName){
					param.penName=penName;
					isChange=true;
				}
				if(introduce!=bak.introduce){
					param.introduce=introduce;
					isChange=true;
				}
				if(sinaPath!=bak.sinaPath){
					param.sinaPath=sinaPath;
					isChange=true;
				}
				if(txPath!=bak.txPath){
					param.txPath=txPath;
					isChange=true;
				}
				if(photoPath!=bak.photoPath){
					param.photoPath=photoPath;
					isChange=true;
				}
				if(!isChange){
					layer.msg("页面没有任何变化");
					return;
				}else{
					$.post("perSaveOrUpdate.html",param,function(json){
						if(json.status==1){
							layer.msg(json.message,{ icon: 1,time: 1000 },function(){
								window.location.reload();
								});
						}else{
							layer.msg(json.message);
						}
					},"json");
					
				}
				
			}else{
				layer.msg("页面数据丢失。请刷新页面！")
			}
		});
