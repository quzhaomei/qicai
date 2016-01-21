
	$(function(){
		//增加
		$(".addResource").click(function(){
			$("#add_name").val("");//重置名字
			$("#addModal").modal("show");//展示modal
		});
		//提交新增
		$(".addSubmit").on("click",
		function(){
			var param={};
			param.operator="add";
			param.name=$("#add_name").val();
			if(!param.name||$.trim(param.name)==""){
				layer.msg("来源名字不能为空");
				return;
			}
			param.url=$("#add_url").val();
			if(!param.url||$.trim(param.url)==""){
				layer.msg("来源链接不能为空");
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
		var bak;//数据备份
		//修改
		$("#content").on("click",".editResource",function(){
			var resourceId=$(this).attr("resourceId");
			var param={};
			param.operator="findById";
			param.resourceId=resourceId;
			if(resourceId){
			$.post("update.html",param,function(data){
				if(data.status==1){
					bak=data.data;
				$("#edit_name").val(bak.name);
				$("#edit_url").val(bak.url);
				
				$("#resourceId").val(bak.resourceId);
				
				$(".roleStatus").removeAttr("checked").parent("span").removeClass("checked")
				$(".roleStatus").each(function(){
					if($(this).val()==bak.status){
						$(this).attr("checked","checked").parent("span").addClass("checked");
					}
				});
				$("#updateModal").modal("show");
				}else{
					layer.msg(data.message);
				}
			},"json");		
			}
		});
		
		//提交更新
		$(".updateSubmit").click(
		function(){
			var param={};
			param.operator="edit";
			
			param.resourceId=$("#resourceId").val();
			
			var isChange=false;
			if($("#edit_name").val()!=bak.name){
				param.name=$("#edit_name").val();
				isChange=true;
			}
			if($("#edit_url").val()!=bak.url){
				param.url=$("#edit_url").val();
				isChange=true;
			}
			if($(".roleStatus:checked").val()!=bak.status){
				param.status=$(".roleStatus:checked").val();
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
		
		//切换状态
		$("#content").on("click",".changeStatu",function(){
			var status=$(this).attr("status");
			var resourceId=$(this).attr("resourceId");
			var param={};
			param.resourceId=resourceId;
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