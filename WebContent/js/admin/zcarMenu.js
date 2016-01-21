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
		//绑定菜单位置验证事件

	
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
				var position=$("#add_position").val();
				if(!menuName){
					layer.msg("请填写菜单名字");
					return;
				}else if(!uri){
					layer.msg("请填写链接");
					return;
				}else if(!position){
					layer.msg("请输入菜单显示序列1~99");
					return;
				}else if(!/^\d{1,2}$/.test(position)){
					layer.msg("请输入正确的菜单的显示序列");
					return;
				}else{
					//验证是否重复
					var check={};
					check.position=position;
					check.operator="check";
					check.menuId=$("#menuId").val();
					var isOk;
					$.ajax({
						url:"list.html",
						type:"post",
						data:check,
						async:false,
						success:function(json){
							isOk=json;
						}
					});
					if(isOk!="true"){
						layer.msg("序列已存在");
						return;
					}
				}
				param.menuName=menuName;
				param.uri=uri;
				param.position=position;
				$.post("saveOrUpdate.html",param,function(json){
					if(json.status==1){
						layer.msg(json.message,window.location.reload());
					}else{
						layer.msg(json.message);
					}
				},"json");
			}else if(operator=="edit"){//修改，名词。链接
				var menuName=$("#edit_menuName").val();
				var uri=$("#edit_uri").val();
				var position=$("#edit_position").val();
				if(!menuName){
					layer.msg("请填写菜单名字");
					return;
				}else if(!uri){
					layer.msg("请填写链接");
					return;
				}else if(!position){
					layer.msg("请填写菜单的顺序");
					return;
				}else if(!/^\d{1,2}$/.test(position)){
					layer.msg("请输入正确的菜单的显示序列");
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
				if($("#edit_position").val()!=$("#edit_position").attr("bak")){
					//验证是否重复
					var check={};
					check.position=position;
					check.operator="check";
					check.menuId=$("#menuId").val();
					var isOk;
					$.ajax({
						url:"list.html",
						type:"post",
						data:check,
						async:false,
						success:function(json){
							isOk=json;
						}
					});
					if(isOk!="true"){
						layer.msg("序列已存在");
						return;
					}
					param.position=position;
					hasChange=true;
				}
				if(!hasChange){
					layer.msg("菜单没有任何改变");
					return;
				}
				$.post("saveOrUpdate.html",param,function(json){
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
				$.post("saveOrUpdate.html",param,function(json){
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
		initModal(menuId);
		if(menuId==-1){//直接初始化
			$("#myModal").modal("show");
		}else{//修改
			//请求数据
			$.post("list.html",param,function(json){
				if(json.status==1){
					json=json.data;
					$("#edit_menuName").val(json.name).attr("bak",json.name);
					$("#edit_uri").val(json.uri).attr("bak",json.uri);
					$("#edit_position").val(json.position).attr("bak",json.position);
					$("#myModal").modal("show");
				}else{
					layer.msg(json.message);
				}
			},"json");
		}
	});
	
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
							+"门户首页</span><button class='editTitle' menuId='-1' >"+
							"<i class='icon-pencil'> </i> 编辑</button></span>");
					$newul.append($newli);
				writeMenuList(jsonArr, -1,$newli);
				$('.tree').append($newul);
			} else {
				$(jsonArr).each(function() {
							if (this.parentId == parentId&& this.menuId != -1) {
										clazz = "globe";
									
										var $newli = $("<li>")
												.append(
														"<span class='item-main'><span class='item "
																+ "'><i class='icon-"+clazz+"'></i> "
																+ this.name+ " ("+this.position+")"
																+ "</span>" 
																+ "<button  class='editTitle'  menuId="+this.menuId+"  ><i class='icon-pencil'> </i> 编辑 </button></span>");
										$newul.append($newli);
										$li.append($newul);
									writeMenuList(jsonArr, this.menuId,
													$newli);
									}
								});
					}

		}

	});
	//初始化model，
	function initModal(menuId){
		var $edit=$("#li_edit");
		var $del=$("#li_del");
		var $add=$("#li_add");
		$(".type_radio").parent().removeClass("checked");
		$("#myTabContent .tab-pane").removeClass("active in");
		if(menuId==-1){//只能增加子链接
			$edit.hide().removeClass("active");
			$del.hide().removeClass("active");
			$add.show().addClass("active").click();
			$("#add").addClass("active in")
		}else{//为菜单组,能修改 删除 添加
			$edit.show().addClass("active").click();
			$del.show().removeClass("active");
			$add.show().removeClass("active");
			$("#edit").addClass("active in");
		}
	}
	