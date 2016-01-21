function initMenuEvent(){
	$('.tree li:has(ul)').addClass('parent_li');
	$('.tree li.parent_li > span.item-main').on("selectstart", function() {
		return false;
	})
	$('.tree li.parent_li > span.item-main').on(
			'dblclick',
			function(e) {
				var children = $(this).parent('li.parent_li')
						.find(' > ul > li');
				if (children.is(":visible")) {
					children.hide('fast');
					$(this).find('.item > i').addClass(
							'icon-folder-close').removeClass(
							'icon-folder-open');
				} else {
					children.show('fast');
					$(this).find('.item > i').addClass(
							'icon-folder-open').removeClass(
							'icon-folder-close');
				}
				e.stopPropagation();
			});
	
		//菜单操作判断
		$("#li_edit").on("click",function(){
			$("#operator").val("edit");
		});
		$("#li_del").on("click",function(){
			$("#operator").val("del");
		});
		$("#li_add").on("click",function(){
			$("#operator").val("add");
		});
		
		//保存操作结果
		$(".saveOrUpdate").click(function(){
			var operator=$("#operator").val();
			var param={};
			param.operator=operator;
			param.menuId=$("#menuId").val();
			if(operator=="add"){//新建,名称 类型，链接
				var menuName=$("#add_menuName").val();
				var uri=$("#add_uri").val();
				var type=$("input[name='add_type']:checked").val();
				if(!menuName){
					layer.msg("请填写菜单名字");
					return;
				}else if(!uri){
					layer.msg("请填写链接");
					return;
				}else if(!type){
					layer.msg("请选择菜单类型");
					return;
				}
				param.menuName=menuName;
				param.uri=uri;
				param.type=type;
				$.post("add.html",param,function(json){
					if(json.status==1){
						layer.msg(json.message,window.location.reload());
					}else{
						layer.msg(json.message);
					}
				},"json");
			}else if(operator=="edit"){//修改，名词。链接
				var menuName=$("#edit_menuName").val();
				var uri=$("#edit_uri").val();
				if(!menuName){
					layer.msg("请填写菜单名字");
					return;
				}else if(!uri){
					layer.msg("请填写链接");
					return;
				}
				var hasChange=false;
				if($("#edit_menuName").val()!=$("#edit_menuName").attr("bak")){
					param.menuName=menuName;
					hasChange=true;
				}
				if($("#edit_uri").val()!=$("#edit_uri").attr("bak")){
					param.uri=uri;
					hasChange=true;
				}
				if(!hasChange){
					layer.msg("菜单没有任何改变");
					return;
				}
				$.post("update.html",param,function(json){
					if(json.status==1){
						layer.msg(json.message,window.location.reload());
					}else{
						layer.msg(json.message);
					}
				},"json");
			}else if(operator=="del"){//删除，
				var repeat=$("#repeat").text();
				var del_repeat=$("#del_repeat").val();
				if(!del_repeat){
					layer.msg("请输入提示内容");
					return;
				}else if(del_repeat!=repeat){
					layer.msg("请按照提示内容输入！");
					return;
				}
				$.post("delete.html",param,function(json){
					if(json.status==1){
						layer.msg(json.message,window.location.reload());
					}else{
						layer.msg(json.message);
					}
				},"json");
			}
		});
	}
	
	$("body").on("click", ".editTitle", function() {
		var menuId=$(this).attr("menuId");
		$("#menuId").val(menuId);
		var type=$(this).attr("type");
		var param={};
		param.operator="findById";
		param.menuId=menuId;
		if(menuId==-1){//直接初始化
			initModal(type,menuId);
			$("#myModal").modal("show");
		}else{//修改
			//请求数据
			$.post("list.html",param,function(json){
				if(json.status==1){
					json=json.data;
					initModal(json.type,menuId);
					$("#edit_menuName").val(json.menuName).attr("bak",json.menuName);
					$("#edit_uri").val(json.uri).attr("bak",json.uri);
					$("#myModal").modal("show");
				}else{
					layer.msg(json.message);
				}
			},"json");
		}
	});
	//初始化model，
	function initModal(type,menuId){
		var $edit=$("#li_edit");
		var $del=$("#li_del");
		var $add=$("#li_add");
		$(".type_radio").parent().removeClass("checked");
		$("#myTabContent .tab-pane").removeClass("active in");
		if(menuId==-1){//只能增加子链接
			$edit.hide().removeClass("active");
			$del.hide().removeClass("active");
			$add.show().addClass("active").click();
			$("#add").addClass("active in");
			//子链接操作为。增加菜单组或者页面
			$(".type_0").parents("label").show();
			$(".type_1").parents("label").show();
			$(".type_2").parents("label").hide();
			$(".type_0").attr("checked","checked");
			$(".type_0").parent().addClass("checked");
		}else if(type==0){//为菜单组,能修改 删除 添加
			$edit.show().addClass("active").click();
			$del.show().removeClass("active");
			$add.show().removeClass("active");
			$("#edit").addClass("active in");
			//只能添加菜单页面
			$(".type_0").parents("label").hide();
			$(".type_1").parents("label").show();
			$(".type_2").parents("label").hide();
			$(".type_1").attr("checked","checked");
			$(".type_1").parent().addClass("checked");
		}else if(type==1){//菜单页，能修改，删除，添加
			$edit.show().addClass("active").click();
			$del.show().removeClass("active");
			$add.show().removeClass("active");
			
			$("#edit").addClass("active in");
			//只能添加操作菜单
			$(".type_0").parents("label").hide();
			$(".type_1").parents("label").hide();
			$(".type_2").parents("label").show();
			$(".type_2").attr("checked","checked");
			$(".type_2").parent().addClass("checked");
		}else if(type==2){//操作,只能修改删除
			$edit.show().addClass("active").click();
			$del.show().removeClass("active");
			$add.hide().removeClass("active");
			$("#myTabContent .tab-pane").removeClass("active in");
			$("#edit").addClass("active in");
		}
	}
	
	//加载菜单
	$(function() {
		var param={};
		param.operator="list";
		$.post("index.html",param,function(jsonArr) {
			
			writeMenuList(jsonArr, -1);
			initMenuEvent();
		}, "json");

		function writeMenuList(jsonArr, parentId, $li) {
			var $newul = $("<ul>");
			if (parentId == -1 && !$li) {
				var $newli = $("<li>").append("<span class='item-main'>"+
						"<span class='item text-error'><i class='icon-home'></i> "
							+"系统后台管理</span><button class='editTitle' menuId='-1' >"+
							"<i class='icon-pencil'> </i> 编辑</button></span>");
					$newul.append($newli);
				writeMenuList(jsonArr, -1,$newli);
				$('.tree').append($newul);
			} else {
				$(jsonArr).each(function() {
							if (this.parentId == parentId&& this.menuId != -1) {
									var claszz = "";
								if (this.type == 1) {//页面菜单
										clazz = "globe";
									} else if (this.type == 0) {//菜单组
											clazz = "folder-open";
										}else if(this.type==2){//页面操作
											clazz="leaf";
										}
										var $newli = $("<li>")
												.append(
														"<span class='item-main'><span class='item "
																+ (this.type == "-1" ? "text-error"
																		: "")
																+ "'><i class='icon-"+clazz+"'></i> "
																+ this.menuName
																+ "</span>"
																+ "<button  class='editTitle'  menuId="+this.menuId+" type="+this.type+" ><i class='icon-pencil'> </i> 编辑 </button></span>");
										$newul.append($newli);
										$li.append($newul);
										if (this.type == 0||this.type==1) {
											writeMenuList(jsonArr, this.menuId,
													$newli);
										}
									}
								});
					}

		}

	});