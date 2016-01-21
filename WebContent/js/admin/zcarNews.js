var index;
var type=$("#articalType").val();
var title="资讯";
if(type==1){
	title="资讯"
}else if(type==2){
	title="名人对话"
}else if(type==3){
	title="封面秀"
}else if(type==4){
	title="作者专栏"
}else if(type==5){
	title="活动"
}else if(type==6){
	title="视频"
}
$(function(){
	$(".addNew").on("click",function(){ //此处用于演示
		index=layer.open({
            type: 2,
        	title:'<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>添加'+title+'</h2></div>',
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content:['add.html?operator=toAdd'],
            end:function(){closeInit()}
        });
        openInit();
    });
	//详细
	$(".findById").on("click",function(){
		var articalId=$(this).attr("articalId");
		if(!articalId){
			layer.msg("网页数据缺失,请刷新页面！");
			return;
		}
		
		index=layer.open({
            type: 2,
        	title:'<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>查看'+title+'</h2></div>',
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content:['list.html?operator=findById&articalId='+articalId],
            end:function(){closeInit()}
        });
		 openInit();
	});
	//修改
	$(".editNews").on("click",function(){
		var articalId=$(this).attr("articalId");
		if(!articalId){
			layer.msg("网页数据缺失,请刷新页面！");
			return;
		}
		
		index=layer.open({
            type: 2,
        	title:'<div class="box-header" data-original-title><h2><i class="halflings-icon book"></i><span class="break"></span>修改'+title+'</h2></div>',
            shade: 0.8,
            maxmin: true, //开启最大化最小化按钮
            area: ['1150px', '650px'],
            content:['update.html?operator=toEdit&articalId='+articalId],
            end:function(){closeInit()}
        });
		 openInit();
	});
	//删除
	$(".delNews").on("click",function(){
		var articalId=$(this).attr("articalId");
		if(!articalId){
			layer.msg("网页数据缺失,请刷新页面！");
			return;
		}
		var param={};
		param.articalId=articalId;
		param.operator="del";
		layer.confirm("确认删除吗？", {
			title:"删除资讯",
		    btn: ["确定","返回"], //按钮
		    shade: false //不显示遮罩
		}, function(){
			$.post("delete.html",param,function(data){
			if(data.status==1){
				layer.msg(data.message,{ icon: 1,time: 1000 },function(){
					$("#myform").submit();
				});
			}else{
				layer.msg(data.message);
			}
		},"json");
		
		}, function(){
			
		});
	
	});
	//切换状态
	$(".changestatus").on("click",function(){
		var articalId=$(this).attr("articalId");
		var status=$(this).attr("status");
		var title="";
		if(!articalId){
			layer.msg("网页数据缺失,请刷新页面！");
			return;
		}
		if(status==1){
			title="确认冻结吗？"
			status=2;
		}else if(status==2){
			title="确认展示吗？";
			status=1;
		}
		var param={};
		param.articalId=articalId;
		param.operator="edit";
		param.status=status;
		layer.confirm(title, {
			title:"状态切换",
		    btn: ["确定","返回"], //按钮
		    shade: false //不显示遮罩
		}, function(){
			$.post("status.html",param,function(data){
			if(data.status==1){
				layer.msg(data.message,{ icon: 1,time: 1000 },function(){
					$("#myform").submit();
				});
			}else{
				layer.msg(data.message);
			}
		},"json");
		
		}, function(){
		});
	});
	
	//置顶
	$(".toTop").on("click",function(){
		var articalId=$(this).attr("articalId");
		var topDate=$(this).attr("topDate");
		var title="";
		if(!articalId){
			layer.msg("网页数据缺失,请刷新页面！");
			return;
		}
		if(topDate){
			title="确认取消置顶吗？"
			topDate=0;//取消
		}else{
			title="确认置顶吗？";
			topDate=1;//置顶
		}
		var param={};
		param.articalId=articalId;
		param.operator="edit";
		param.topDate=topDate;
		layer.confirm(title, {
			title:"置顶切换",
		    btn: ["确定","返回"], //按钮
		    shade: false //不显示遮罩
		}, function(){
			$.post("toTop.html",param,function(data){
			if(data.status==1){
				layer.msg(data.message,{ icon: 1,time: 1000 },function(){
					$("#myform").submit();
				});
			}else{
				layer.msg(data.message);
			}
		},"json");
		
		}, function(){
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
	function toPage(index){
		$("#pageIndex").val(index);
		$("#myform").submit();
	}
	$("#page li").click(function(){
		var index=$(this).attr("index");
		if(index){
			toPage(index);
		}
	});
	$("#pageSelect").on("change",function(){
		var index=$("#pageSelect").val();
		toPage(index);
	});
