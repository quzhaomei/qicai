		$(".new-top").click(function() {
			$("#addModal").modal("show");
		});
		
		function getHtml($tbody,jsonList,start,size,topId){
			for(var i=0;i<size;i++){
				var $tr=$("<tr>");
				if(i<jsonList.length){
				var artical=jsonList[i];
				$tr.append($("<td>").text(start+i+1));
				$tr.append($("<td>").text(artical.resource.name));
				$tr.append($("<td>").text(artical.author.penName));
				$tr.append($("<td>").text(artical.createDateStr));
				$tr.append($("<td>").text(artical.title));
				$tr.append($("<td>").append('<div style="height:50px;overflow:hidden;"><img src="'+artical.scanImgPath+'" /></div>'));
				var $span='';
				if(start+i+1!=1){
					$span+=' <span class="btn btn-mini label-important toTop" topid="'+topId +
					'" articalId="'+artical.articalId+'"><i class="halflings-icon bookmark white"></i> 置顶</span>';
				}
				$span+=' <span class="btn btn-mini  toDel" topid="'+topId +
				'" articalId="'+artical.articalId+'"> 移除 </span>';
				$tr.append($("<td>").append($span));
			}else{
				$tr.append($("<td>").html("&nbsp;")).append($("<td>").html("&nbsp;"))
				.append($("<td>").html("&nbsp;")).append($("<td>").html("&nbsp;"))
				.append($("<td>").html("&nbsp;")).append($("<td>").html("&nbsp;")).append($("<td>").html("&nbsp;"));
			}
				$tbody.append($tr);
			}
				
		}
		
		$(function() {
			//上一页
			$(".page-controller li.prev").on("click",function(){
				var $li=$(this).siblings("li.active").prev();
				if($li.attr("index")){
					$li.click();
				};
			});

			//下一页
			$(".page-controller .next").on("click",function(){
				var $li=$(this).siblings("li.active").next();
				if($li.attr("index")){
					$li.click();
				};
			});
			
			//分页.
			$(".page-controller li").on("click",function(){
				var topId=$(this).parent("ul").attr("topId");
				var index=$(this).attr("index");
				var _this=this;
				
				if(!topId||!index){
					return;
				}
				var param={};
				param.operator="list";
				param.index=index;
				param.topId=topId;
				$.post("index.html",param,function(json){
						if(json.status==1){
				$(_this).siblings("li").removeClass("active");
				$(_this).addClass("active");
				
							var pageDate=json.data.param;
							var pageSize=json.data.pageSize;
							var pageIndex=parseInt( json.data.pageIndex,"10");
					//只留出八个。
					var min=pageIndex-4;
					var max=pageIndex+4;
					$(_this).siblings("li").each(function(){
							var temp=$(this).attr("index");
							if(temp){
								var tempIndex=parseInt(temp,"10");
								if(tempIndex>=min&&tempIndex<=max){
									$(this).show();
								}else{
									$(this).hide();
								}
							}
					});
							//设置param;
							var start=(pageIndex-1)*pageSize;
							$("#topdata"+topId).html("");
							getHtml($("#topdata"+topId),pageDate,start,pageSize,topId);
						}else{
							layer.msg(json.message);
						}			
				},"json");
			});
			//添加分类
			$(".addSubmit").click(
					function() {
						var name = $("#add_name").val();
						if (!name) {
							layer.msg("请输入新建置顶的名称");
							return;
						}
						var param = {};
						param.operator = "add";
						param.name = name;
						$.post("add.html", param, function(json) {
							$("#add_name").val("");
							if (json.status == 1) {
								layer.msg(json.message);
								//获取回传的json，并初始化
								addNewTop(json.data);

								$(".notopBtn .info").remove();
								$(".notopBtn").toggleClass("topBtn notopBtn")
										.hide().fadeIn("fast");

								$("#addModal").modal("hide");
							} else {
								layer.msg(json.message);
							}
						}, "json");
					});

			//修改分类
			$("#content").on(
					"click",
					".topnameEdit",
					function() {
						var topId = $(this).attr("topId");
						if (topId) {
							var param = {};
							param.operator = "findById";
							param.topId = topId;

							$.post("update.html", param,
									function(json) {
										if (json.status == 1) {
											var data = json.data;
											$("#topId").val(topId);
											$("#edit_name").attr("bak",
													data.name).val(data.name);
											$(".edit_status").removeAttr(
													"checked").parent("span")
													.removeClass("checked")
											$(".edit_status").attr("bak",
													data.status);
											$(
													".edit_status[value='"
															+ data.status
															+ "']").attr(
													"checked", "checked")
													.parent("span").addClass(
															"checked");
										} else {
											layer.msg(json.message);
										}
									}, "json");
							$("#updateModal").modal("show");

						} else {
							layer.msg("页面数据丢失，请刷新页面！");
						}
					});
			//保存修改
			$(".updateSubmit")
					.on(
							"click",
							function() {
								var param = {};
								var topId = $("#topId").val();
								param.topId = topId;
								param.operator = "edit";
								if (!topId) {
									layer.msg("页面数据丢失，请刷新页面！");
									return;
								}
								if (!$("#edit_name").val()) {
									layer.msg("请输入置顶分类的名字");
									return;
								}
								var isChange = false;
								if ($("#edit_name").val() != $("#edit_name")
										.attr("bak")) {
									param.name = $("#edit_name").val();
									isChange = true;
								}
								if ($(".edit_status:checked").val() != $(
										".edit_status").attr("bak")[0]) {
									param.status = $(".edit_status:checked")
											.val();
									isChange = true;
								}
								if (!isChange) {
									layer.msg("数据没有任何变化");
									return;
								} else {
									$
											.post(
													"update.html",
													param,
													function(json) {
														$("#updateModal")
																.modal("hide");
														if (json.status == 1) {
															var data = json.data;
															if (data.status == 1) {
																$(
																		"#top"
																				+ data.topId
																				+ " .title")
																		.addClass(
																				"show");
															} else {
																$(
																		"#top"
																				+ data.topId
																				+ " .title")
																		.removeClass(
																				"show");
															}
															$(
																	"#top"
																			+ data.topId
																			+ " .topname")
																	.text(
																			data.name);
															layer
																	.msg(json.message);
														} else {
															layer
																	.msg(json.message);
														}
													}, "json");
								}

							});

			//置顶
			$(".mark").on("click", ".toTop", function() {
				var topId = $(this).attr("topId");
				var articalId=$(this).attr("articalId");
				var param={};
				param.topId=topId;
				param.articalId=articalId;
				if(!topId||!articalId){
					layer.msg("页面数据缺失，请刷新页面");
					return;
				}
				param.operator="edit";
				layer.confirm("确定选为置顶吗？", {
					title : "文章选择",
					btn : [ "确定", "返回" ], //按钮
					shade : false
				//不显示遮罩
				}, function() {
					$.post("fillTop.html", param, function(data) {
						if (data.status == 1) {
							layer.msg(data.message, {
								icon : 1,
								time : 1000
							}, function() {
								if (window.parent) {
									window.parent.location.reload();
								}
							});
						} else {
							layer.msg(data.message);
						}
					}, "json");

				}, function() {
				});
			});

		
		//移除
			$(".mark").on("click", ".toDel", function() {
				var topId = $(this).attr("topId");
				var articalId=$(this).attr("articalId");
				var param={};
				param.topId=topId;
				param.articalId=articalId;
				if(!topId||!articalId){
					layer.msg("页面数据缺失，请刷新页面");
					return;
				}
				param.operator="del";
				layer.confirm("确定删除吗？", {
					title : "文章选择",
					btn : [ "确定", "返回" ], //按钮
					shade : false
				//不显示遮罩
				}, function() {
					$.post("fillTop.html", param, function(data) {
						if (data.status == 1) {
							layer.msg(data.message, {
								icon : 1,
								time : 1000
							}, function() {
								if (window.parent) {
									window.parent.location.reload();
								}
							});
						} else {
							layer.msg(data.message);
						}
					}, "json");

				}, function() {
				});
			});

		$("#content").on("click", ".topnameDel", function() {
			var _this = this;
			layer.confirm("确定删除吗？", {
				title : "删除分类",
				btn : [ "确定", "返回" ], //按钮
				shade : false
			//不显示遮罩
			}, function() {
				var topId = $(_this).attr("topId");
				if (topId) {
					var param = {};
					param.topId = topId;
					param.operator = "edit";
					param.status = 2;
					$.post("delete.html", param, function(json) {
						if (json.status == 1) {
							var data = json.data;
							if (data.status == 2) {
								$("#top" + data.topId).fadeOut("fast");
							}
							layer.msg(json.message);
						} else {
							layer.msg(json.message);
						}
					}, "json");
				} else {
					layer.msg("页面数据错误。请刷新页面");
				}

			}, function() {

			});
		});
		
			});
		//添加新分类
		function addNewTop(json) {
			var html = [
					'<div class="row-fluid sortable" id="top'+json.topId+'">',
					'<div class="box span12">',
					'<div class="box-header" style="height:25px;">',
					'<h2>',
					'<i class="halflings-icon book"></i><span class="break"></span>',
					json.name,
					'</h2>',
					'<div class="box-icon">',
					'<span topid="'+json.topId+'" class="btn btn-small btn-success topnameEdit">修改</span> ',
					'<span topid="'+json.topId+'" class="btn btn-small btn-important topnameDel">删除</span> ',
					'<span topid="'+json.topId+'" class="btn btn-small label-important topnameAdd">添加文章</span>',
					'</div></div>',
					'<div class="box-content">',
					'	<table class="table table-striped table-bordered bootstrap-datatable">',
					'	  <thead> <tr><th width=10%>序列</th><th width=20%>文章作者</th><th width=30%>创建时间</th>',
					'			  <th width=10%>标题</th><th width=30%>操作</th></tr>',
					'	  </thead><tbody><td colspan="5" class="noartical">暂无文章</td></tbody>',
					' </table></div></div></div>', ];
			$("#content").append(html.join(""));
		}

		//新增分类置顶文章
		$("#content")
				.on(
						"click",
						".topnameAdd",
						function() {
							var topId = $(this).attr("topId");
							if (!topId) {
								layer.msg("页面数据丢失，请刷新页面。");
							}
							;

							var title = "新增分类";
							index = layer
									.open({
										type : 2,
										title : '<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>添加'
												+ title + '</h2></div>',
										shade : 0.8,
										maxmin : true, //开启最大化最小化按钮
										area : [ '1150px', '650px' ],
										content : [ 'fillTop.html?operator=toAdd&topId='
												+ topId ],
										end : function() {
											closeInit()
										}
									});
							openInt();
						});

		function closeInit() {
			$("body").css("overflow", "auto");
		}

		function openInt() {
			$("body").css("overflow", "hidden");
		}