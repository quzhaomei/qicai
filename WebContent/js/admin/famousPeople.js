$(function() {
	
	$(".addFamous").on("click",function(){ //增加名人此处用于演示
		var title="添加名人";
		index=layer.open({
            type: 2,
        	title:'<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>'+title+'</h2></div>',
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content:['add.html?operator=toAdd'],
            end:function(){closeInit()}
        });
        openInit();
    });
	
	$("#content").on("click",".detail",function(){ //此处用于演示
		var peopleId = $(this).attr("peopleId");
		if (!peopleId) {
			layer.msg("页面数据丢失，请刷新页面！");
			return;
		}
		var title="修改名人";
		index=layer.open({
            type: 2,
        	title:'<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>'+title+'</h2></div>',
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content:['list.html?operator=findById&peopleId='+peopleId],
            end:function(){closeInit()}
        });
        openInit();
    });
	
	$("#content").on("click",".editFamous",function(){ //此处用于演示
		var peopleId = $(this).attr("peopleId");
		if (!peopleId) {
			layer.msg("页面数据丢失，请刷新页面！");
			return;
		}
		var title="修改名人";
		index=layer.open({
            type: 2,
        	title:'<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>'+title+'</h2></div>',
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content:['update.html?operator=toEdit&peopleId='+peopleId],
            end:function(){closeInit()}
        });
        openInit();
    });
	
	// 切换状态
	$("#content").on("click",".changeStatu", function() {
		var status = $(this).attr("status");
		var peopleId = $(this).attr("peopleId");
		var param = {};
		param.peopleId = peopleId;
		param.operator = "edit";
		var tip = "";
		if (status == "2") {// 激活
			param.status = "1";
			tip = "确认激活吗?";
		} else if (status == "1") {// 冻结
			param.status = "2";
			tip = "确认冻结吗";
		}
		layer.confirm(tip, {
			title : "状态切换",
			btn : [ "确定", "返回" ], // 按钮
			shade : false
		// 不显示遮罩
		}, function() {
			$.post("status.html", param, function(data) {
				if (data.status == 1) {
					layer.msg(data.message, {
						icon : 1,
						time : 1000
					}, function() {
						window.location.reload();
					});
				} else {
					layer.msg(data.message);
				}
			}, "json");

		}, function() {

		});
	});

	// 置顶
	// 切换状态
	$("#content").on("click",".toTop", function() {
		var peopleId = $(this).attr("peopleId");
		var param = {};
		param.peopleId = peopleId;
		param.operator = "edit";
		param.toTop = "toTop";
		layer.confirm("确认置顶吗？", {
			title : "置顶",
			btn : [ "确定", "返回" ], // 按钮
			shade : false
		// 不显示遮罩
		}, function() {
			$.post("toTop.html", param, function(data) {
				if (data.status == 1) {
					layer.msg(data.message, {
						icon : 1,
						time : 1000
					}, function() {
						window.location.reload();
					});
				} else {
					layer.msg(data.message);
				}
			}, "json");

		}, function() {

		});
	});
	
	// 删除
	$("#content").on("click",".delete", function() {
		var peopleId = $(this).attr("peopleId");
		var param = {};
		param.peopleId = peopleId;
		layer.confirm("确认删除吗？", {
			title : "置顶",
			btn : [ "确定", "返回" ], // 按钮
			shade : false
		// 不显示遮罩
		}, function() {
			$.post("delete.html", param, function(data) {
				if (data.status == 1) {
					layer.msg(data.message, {
						icon : 1,
						time : 1000
					}, function() {
						window.location.reload();
					});
				} else {
					layer.msg(data.message);
				}
			}, "json");

		}, function() {

		});
	});


});

function openInit(){
	$("body").css("overflow","hidden");
}
function closeInit(){
	$("body").css("overflow","auto");
	layer.close(index);
}