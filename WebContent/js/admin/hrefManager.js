
	$(function(){
		//增加
		$(".addRole").click(function(){
			$("#add_name").val("");//重置名字
			$("#add_href").val("");//重置链接
			$("#addModal").modal("show");//展示modal
		});
		//提交新增
		$(".addSubmit").on("click",
		function(){
			var param={};
			param.operator="add";
			param.name=$("#add_name").val();
			param.href=$("#add_href").val();
			if(!param.name){
				layer.msg("名称不能为空！");
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
		
		var bak;//数据备份对象
		//修改
		$("#content").on("click",".editRole",function(){
			var hrefId=$(this).attr("hrefId");
			var param={};
			param.operator="findById";
			param.hrefId=hrefId;
			if(hrefId){
			$.post("update.html",param,function(data){
				if(data.status==1){
					bak=data.data;
					$("#edit_name").val(bak.name);
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
			param.hrefId=bak.hrefId;
			
			if(!$("#edit_name").val()){
				layer.msg("名字不能为空");
				return;
			}
			if(!$("#edit_href").val()){
				layer.msg("链接不能为空");
				return;
			}
			var isChange=false;
			
			if($("#edit_name").val()!=bak.name){
				param.name=$("#edit_name").val();
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
			var hrefId=$(this).attr("hrefId");
			var param={};
			param.hrefId=hrefId;
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