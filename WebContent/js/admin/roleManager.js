
	$(function(){
		//增加
		$(".addRole").click(function(){
			$("#add_menuName").val("");//重置名字
			$("#addModal .alert").hide();//隐藏提示框
			$("#addModal").modal("show");//展示modal
		});
		//提交新增
		$(".addSubmit").on("click",
		function(){
			var param={};
			param.operator="add";
			param.roleName=$("#add_menuName").val();
			if(!param.roleName||$.trim(param.roleName)==""){
				layer.msg("角色名不能为空！");
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
		$("#content").on("click",".editRole",function(){
			var roleId=$(this).attr("roleId");
			var param={};
			param.operator="findById";
			param.roleId=roleId;
			if(roleId){
			$.post("update.html",param,function(data){
				if(data.status==1){
					bak=data.data;
				$("#roleName").val(bak.roleName);
				$("#roleId").val(bak.roleId);
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
			
			param.roleId=$("#roleId").val();
			param.status=$(".roleStatus:checked").val();
			var isChange=false;
			if($("#roleName").val()!=bak.roleName){
				param.roleName=$("#roleName").val();
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
		
		//删除
		$(".changeStatu").click(function(){
			var status=$(this).attr("status");
			var roleId=$(this).attr("roleid");
			var param={};
			param.roleId=roleId;
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