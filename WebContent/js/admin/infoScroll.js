
	$(function(){
		//增加
		$(".addScroll").click(function(){
			$("#add_title").val("");//
			$("#add_href").val("");//
			$("#addModal").modal("show");//展示modal
		});
		//提交新增
		$(".addSubmit").on("click",
		function(){
			var param={};
			param.operator="add";
			param.title=$("#add_title").val();
			param.href=$("#add_href").val();
			if(!param.title){
				layer.msg("标题不能为空！");
				return;
			}
			if(!param.href){
				layer.msg("链接不能为空！");
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
			if($("#edit_title").val()!=bak.title){
				param.title=$("#edit_title").val();
				isChange=true;
			}
			if($("#edit_href").val()!=bak.href){
				param.href=$("#edit_href").val();
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
	
	
