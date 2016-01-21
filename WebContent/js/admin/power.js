$(function() {
	$("#searchBtn").click(function(){
			var roleId=$("#pageSelect").val();
			if(roleId=="-1"){
				layer.msg("请选择角色");
				return;
			}
			//TODO 查找是否已经存在,存在就取到第一位来
			if($("#role"+roleId)[0]){
				$("#role"+roleId+" .box").hide();
				$("#role"+roleId).insertAfter($("#searchDiv"));	
				$("#role"+roleId+" .box").fadeIn(600);
				return;
			}
			if(!roleId){
				layer.msg("角色不存在");
				return;
			}
			var param={};
			param.operator="findById";
			param.roleId=roleId;
			//IF不存在查找对应权限
			$.post("list.html",param,function(json){
				if(json.status==1){
					json=json.data;
					createUserPowerTree(json.menus,json.role);
				}else{
					layer.msg(json.message);
				}
			},"json");
		});	
});
	function createUserPowerTree(menus,role){
		var $div=$("<div>").addClass("row-fluid sortable").css("display","none").attr("roleId",role.roleId).attr("id","role"+role.roleId);
		var $div1=$("<div>").addClass("box span12")
		var $div2=$("<div>").addClass("box-header");
		var $div3=$("<div>").addClass("box-content");
		 $div.append($div1);
		 $div1.append($div2).append($div3);
		 $div3.append("<div class='tree well'></div>");
		 $div2.append("<h2><i class='halflings-icon user'></i><span class='break'></span>"+
								"<span class='roleName'>"+role.roleName+"</span></h2>");
		 $div2.append("<div class='box-icon'>"+
		 		"<a href='javascript:;' class='btn-save' style='display:none;'><i class='halflings-icon ok'></i> </a>"+//保存
				"<a href='javascript:;' class='btn-refresh'><i class='halflings-icon refresh'></i> </a>"+//刷新
				"<a href='javascript:;' class='btn-minimize'><i class='halflings-icon chevron-up'></i></a>"+//最小化
				"<a href='javascript:;' class='btn-close'><i class='halflings-icon remove'></i></a></div>");//关闭
		$div.insertAfter($("#searchDiv"));	
		$div.fadeIn(600);
		
		initRoleEdit(role.roleId);//初始化图标操作事件
		
		writeMenuList(menus,-1,null,$('#role'+role.roleId+' .tree'));//画出权限树-1表示从根画起
    	initMenuEvent();//绑定树的事件	
		//绑定值单个菜单权限的改变事件
		$("#role"+role.roleId+" input:checkbox").click(function(){
			$(this).toggleClass("change");
		});
		//绑定整体值的改变事件
		$("#role"+role.roleId).click(function(){
			if($("#role"+role.roleId+" input:checkbox.change").get(0)){//值改变了
				$("#role"+role.roleId+" .tree").css("backgroundColor","rgb(232, 255, 221)");
				$("#role"+role.roleId+" .btn-save").show();
			}else{
				$("#role"+role.roleId+" .tree").css("backgroundColor","#fff");
				$("#role"+role.roleId+" .btn-save").hide();
			}
		});
			
	}

	function writeMenuList(jsonArr, parentId, $li,$position) {
		var $newul = $("<ul>");
		if (parentId == -1&&!$li) {//先画出根目录
			var $newli = $("<li>").append(
				" <span class='item text-error'><i class='icon-home'></i> "
							+ "系统后台管理	 </span>");
			$newul.append($newli);
			writeMenuList(jsonArr, -1,$newli);
			$position.append($newul);
		} else {//画出其余的节点
			$(jsonArr)
					.each(
							function() {
								if (this.parentId == parentId) {
									var claszz = "";
									if (this.type == 0) {//是菜单组
										clazz = "folder-open";
									} else if (this.type == 1) {//是页面菜单
										clazz = "globe";
									} else if(this.type==2){//是操作
										clazz = "leaf";
									}

									var $newli = $("<li>")
											.append(
													"<input type='checkbox' "+((this.tempId?"checked tempId='"+this.tempId+"'":""))+" menuId='"+this.menuId+"'/> <span class='item "
															+ "'><i class='icon-"+clazz+"'></i> "
															+ this.menuName
															+ "</span>");
									$newul.append($newli);
									$li.append($newul);
									if (this.type == 0||this.type==1) {//是目录或是页面菜单就再次递归一遍
										writeMenuList(jsonArr, this.menuId,
												$newli);
									}
								}
							});
		}

	}
	function initMenuEvent() {
		$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr(
				'title', '点击关闭');
		$('.tree li.parent_li > span').on("selectstart", function() {
			return false;
		})
		$('.tree li.parent_li > span').on(
				'dblclick',
				function(e) {
					var children = $(this).parent('li.parent_li').find(
							' > ul > li');
					if (children.is(":visible")) {
						children.hide('fast');
						$(this).attr('title', '点击展开').find(' > i')
								.addClass('icon-folder-close').removeClass(
										'icon-folder-open');
					} else {
						children.show('fast');
						$(this).attr('title', '点击关闭').find(' > i')
								.addClass('icon-folder-open').removeClass(
										'icon-folder-close');
					}
					e.stopPropagation();
				});
	}
	//刷新角色权限
	function refresh(roleId,msg){
		var param={};
		param.roleId=roleId;
		param.operator="findById";
		if(!roleId){
			layer.msg("数据错误");
			return;
		}
		$.post("list.html",param,function(json){
			if(json.status==0){
				layer.msg(json.message);
				return;
			}
			json=json.data;
				//先清空里面的内容
				var role=json.role;//绑定角色
				var jsonArr=json.menus;//绑定菜单
				var $div=$("#role"+roleId);
					$div.empty().hide();//清空内容
				var $div1=$("<div>").addClass("box span12")
				var $div2=$("<div>").addClass("box-header");
				var $div3=$("<div>").addClass("box-content");
				 $div.append($div1);
				 $div1.append($div2).append($div3);
				 $div3.append("<div class='tree well'></div>");
				 $div2.append("<h2><i class='halflings-icon user'></i><span class='break'></span>"+
										"<span class='roleName'>"+role.roleName+"</span></h2>");
				 $div2.append("<div class='box-icon'>"+
				 		"<a href='javascript:;' class='btn-save' style='display:none;'><i class='halflings-icon ok'></i> </a>"+//保存
						"<a href='javascript:;' class='btn-refresh'><i class='halflings-icon refresh'></i> </a>"+//刷新
						"<a href='javascript:;' class='btn-minimize'><i class='halflings-icon chevron-up'></i></a>"+//最小化
						"<a href='javascript:;' class='btn-close'><i class='halflings-icon remove'></i></a></div>");//关闭
				$div.fadeIn(600);
			//事件初始化	
				initRoleEdit(role.roleId);
				
				writeMenuList(jsonArr,"-1",null,$('#role'+role.roleId+' .tree'));//画出权限树-1表示从根画起
				initMenuEvent();//绑定树的事件	
				//绑定值单个菜单权限的改变事件
				$("#role"+role.roleId+" input:checkbox").click(function(){
					$(this).toggleClass("change");
				});
				//绑定整体值的改变事件
				$("#role"+role.roleId).click(function(){
					if($("#"+role.roleId+" input:checkbox.change").get(0)){//值改变了
						$("#"+role.roleId+" .tree").css("backgroundColor","rgb(208, 239, 255)");
						$("#"+role.roleId+" .btn-save").show();
					}else{
						$("#"+role.roleId+" .tree").css("backgroundColor","#fff");
						$("#"+role.roleId+" .btn-save").hide();
					}
				});
				if(msg){
					layer.msg(msg);
				}else{
					layer.msg("刷新成功！");
				}
			},"json");
	}
	
	function initRoleEdit(roleId){
	//事件初始化	
		$('#role'+roleId).sortable({
			revert:true,
			cancel:'.btn,.box-content,.nav-header',
			update:function(event,ui){
			}
		});	
		$('#role'+roleId+' .btn-close').click(function(e){//关闭事件
				e.preventDefault();
				$(this).parent().parent().parent().fadeOut();
			});
		$('#role'+roleId+' .btn-minimize').click(function(e){//最小化事件
				e.preventDefault();
				var $target = $(this).parent().parent().next('.box-content');
				if($target.is(':visible')) $('i',$(this)).removeClass('chevron-up').addClass('chevron-down');
				else 					   $('i',$(this)).removeClass('chevron-down').addClass('chevron-up');
				$target.slideToggle();
			});
		$('#role'+roleId+' .btn-refresh').click(function(e){//刷新事件
			
			//有值改变，做提醒
			if($("#role"+roleId+" input:checkbox.change").get(0)){//值改变了
				layer.confirm("检测到有值改变了，确定不保存直接刷新吗？",{title:"警告：有值改变了"},function(){
					refresh(roleId);
				},function(){});
				return;
			}
				refresh(roleId);
			});
		$('#role'+roleId+' .btn-save').click(function(e){//保存
				var param={};
				param.roleId=roleId;//角色
				param.operator="edit";
				param.menuIds=[];//新增的菜单ID
				param.tempIds=[];//中间表ID，需要删除的
				if(!$("#role"+roleId+" input:checkbox.change")[0]){
					layer.msg("未检测到变化！");
					return;
				}
				//获取改变的事件
				$("#role"+roleId+" input:checkbox.change").each(//初始化参数
						function(){
							if($(this).attr("checked")){
								param.menuIds.push($(this).attr("menuId"));
							}else{
								param.tempIds.push($(this).attr("tempId"));
							}
						}
					);
				
					$.post( "saveOrUpdate.html", param,  
			        	 function(json){
			            	if(json.status==1){
			            		refresh(roleId,"更新成功！！");
			            	}else{
			            		layer.msg(json.message);
			            	}
			            }  
			        ,"json");
			});
	}