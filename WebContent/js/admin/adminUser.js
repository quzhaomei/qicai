//跳转某页
	function toPage(index){
		$("#pageIndex").val(index);
		$("#myform").submit();
	}
	//事件绑定
	$(function(){
	$("#tagType").on("change",function(){
		$("#pageIndex").val(1);
		$("#myform").submit();
	});
	initEmail($("#add_email"));//邮箱的自动填充
	initEmail($("#edit_email"));
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
	
	//新增用户的数据验证
	$("#addForm").validate({
		rules:{
			add_loginname:{
				required:true,
				isLoginname:true,
				remote:{//验证用户名重复
				    url: "add.html",     //后台处理程序
				    type: "post",               //数据发送方式
				    data: {                     //要传递的数据
				        loginname: function() {
				            return $("#add_loginname").val();
				        },
				        operator:"check"
				    }
				}
			},
			add_phone:{
				required:true,
				isPhone:true,
				remote:{//验证电话号码重复
				    url: "add.html",     //后台处理程序
				    type: "post",               //数据发送方式
				    data: {                     //要传递的数据
				        phone: function() {
				            return $("#add_phone").val();
				        },
				        operator:"check"
				    }
				}
			},
			add_nickname:{
				required:true				
			},
			add_email:{
				required:true,
				isEmail:true,
				remote:{//验证邮箱重复
				    url: "add.html",     //后台处理程序
				    type: "post",               //数据发送方式
				    data: {                     //要传递的数据
				        email: function() {
				            return $("#add_email").val();
				        },
				        operator:"check"
				    }
				}
			}
		},
		messages:{
			add_loginname:{
				required:"请输入用户名",
				isLoginname:"6-20位英文字符",
				maxlength:"字数过多",
				remote:"用户名已经存在！"
			},
			add_phone:{
				required:"请输入电话号码",
				maxlength:"字数过多",
				isPhone:"请输入正确号码",
				remote:"该号码已经存在"
			},
			add_nickname:{
				required:"请输入用户昵称",
				maxlength:"字数过多"
			},
			add_email:{
				required:"请输入邮箱",
				maxlength:"字数过多",
				isEmail:"请输入正确email",
				remote:"该邮箱已经注册过"
			}
		},
		errorPlacement: function(error, element) {  
   		 error.appendTo(element.parent().find("div.errorDiv"));  
		}
	});
	
	//修改用户的数据验证
	$("#editForm").validate({
		rules:{
			edit_loginname:{
				required:true,
				isLoginname:true,
				remote:{//验证用户名重复
				    url: "update.html",     //后台处理程序
				    type: "post",               //数据发送方式
				    data: {                     //要传递的数据
				        loginname: function() {
				            return $("#edit_loginname").val();
				        },
				        operator:"check"
				    }
				}
			},
			edit_phone:{
				required:true,
				isPhone:true,
				remote:{//验证电话号码重复
				    url: "update.html",     //后台处理程序
				    type: "post",               //数据发送方式
				    data: {                     //要传递的数据
				        phone: function() {
				            return $("#edit_phone").val();
				        },
				        operator:"check"
				    }
				}
			},
			edit_nickname:{
				required:true				
			},
			edit_email:{
				required:true,
				isEmail:true,
				remote:{//验证邮箱重复
				    url: "update.html",     //后台处理程序
				    type: "post",               //数据发送方式
				    data: {                     //要传递的数据
				        email: function() {
				            return $("#edit_email").val();
				        },
				        operator:"check"
				    }
				}
			}
		},
		messages:{
			edit_loginname:{
				required:"请输入用户名",
				isLoginname:"6-20位中英文字符",
				maxlength:"字数过多",
				remote:"用户名已经存在！"
			},
			edit_phone:{
				required:"请输入电话号码",
				maxlength:"字数过多",
				isPhone:"请输入正确号码",
				remote:"该号码已经存在"
			},
			edit_nickname:{
				required:"请输入用户昵称",
				maxlength:"字数过多"
			},
			edit_email:{
				required:"请输入邮箱",
				maxlength:"字数过多",
				isEmail:"请输入正确email",
				remote:"该邮箱已经注册过"
			}
		},
		errorPlacement: function(error, element) {  
   		 error.appendTo(element.parent().find("div.errorDiv"));  
		},
		onkeyup:false,
		onfocusout:false,
		 onclick:false,
		 onsubmit:false
	});

	
	$(".addSubmit").on("click",function(){//提交添加
			if($("#addForm").valid()){//验证通过！
				var param={};
				param.operator="add";
				param.nickname=$("#add_nickname").val();
				if($("#add_roleList").val()){
					param.roleList=$("#add_roleList").val();
				}
				param.loginname=$("#add_loginname").val();
				param.phone=$("#add_phone").val();
				param.email=$("#add_email").val();
				param.description=$("#description").val();
				$.post("add.html",param,function(json){//发送数据
					if(json.status==1){
						layer.msg(json.message,{ icon: 1,time: 1000 },function(){
						reset();
						});
					}else{
						layer.msg(json.message);
					}
				},"json");
			}
		});
	$(".updateSubmit").on("click",function(){//提交修改
		var param={};
		param.operator="edit";
		var isChange=false;
		//检测参数
		if($("#edit_userId").val()){
			param.userId=$("#edit_userId").val();
		}else{
			layer.msg("页面数据缺失");
			return;
		}
		if(!$("#edit_nickname").val()){
			layer.msg("昵称不能为空");
			return;
		}
		if($("#edit_nickname").val()!=bak.nickname){
			if($("#edit_nickname").valid()){
				param.nickname=$("#edit_nickname").val();
				isChange=true;
			}else{
				return;
			}
		}
		if(!$("#edit_loginname").val()){
			layer.msg("登陆名不能为空");
			return;
		}
		if($("#edit_loginname").val()!=bak.loginname){
			if($("#edit_loginname").valid()){
				param.loginname=$("#edit_loginname").val();
				isChange=true;
			}else{
				return;
			}
		}
		if(!$("#edit_phone")){
			layer.msg("电话号码不能为空");
			return;
		}
		if($("#edit_phone").val()!=bak.phone){
			if($("#edit_phone").valid()){
				param.phone=$("#edit_phone").val();
				isChange=true;
			}else{
				return;
			}
		}
		if(!$("#edit_email")){
			layer.msg("电话号码不能为空");
			return;
		}
		if($("#edit_email").val()!=bak.email){
			if($("#edit_email").valid()){
				param.email=$("#edit_email").val();
				isChange=true;
			}else{
				return;
			}
		}
		if($("#edit_description").val()!=bak.description){
			param.description=$("#edit_description").val();
			isChange=true;
		}
		if($(".userType:checked").val()!=bak.type){
			param.type=$(".userType:checked").val();
			isChange=true;
		}
		var roleChange=false;
		if($("#edit_roleArr").val()||bak.length!=0){//角色,
			if($("#edit_roleArr").val()&&bak){//比较俩数组
				if($("#edit_roleArr").val().length!=bak.length){
					roleChange=true;
					param.rolesId=$("#edit_roleArr").val();
				}else{//长度一样，比较内容
					var size=$("#edit_roleArr").val().length;
					for(var index=0;index<size;index++){
						for(var inner=0;inner<size;inner++){
							if($("#edit_roleArr").val()[index]==bak[inner]){
								break;
							}
							if($("#edit_roleArr").val()[index]!=bak[inner]&&inner==size-1){
								roleChange=true;
								break;
							}
						}
					}
					if(roleChange){
						param.rolesId=$("#edit_roleArr").val();
					}
				}
			}else{//有变化
				param.rolesId=$("#edit_roleArr").val();
				roleChange=true;
			}
		}
		if(!isChange&&!roleChange){
			layer.msg("未检测到数据变化");
			return;
		}
		
		$.post("update.html",param,function(json){//发送数据
			if(json.status==1){
				layer.msg(json.message,{ icon: 1,time: 1000 },function(){
					reset();
					});
				}else{
				layer.msg(json.message);	
				}	
			},"json");
	
	});
});
	//切换状态
	$(".changeStatu").click(function(){
		var status=$(this).attr("status");
		var userId=$(this).attr("userId");
		if(!status||!userId){
			layer.msg("页面数据丢失");
			return;
		}
		var param={};
		param.userId=userId;
		var tip="";
		if(status=="2"){//激活操作
			param.status="1";
			tip="确认激活吗？";
		}else if(status=="1"){//冻结操作
			param.status="2";
			tip="确认冻结吗？";
		}else if(status=="0"){//删除操作
			param.status="0";
			tip="确认删除吗？";
		}
		layer.confirm(tip, {
			title:"状态切换",
		    btn: ["确定","返回"], //按钮
		    shade: false //不显示遮罩
		}, function(){
			$.post("status.html",param,function(data){
			if(data.status==1){
				layer.msg(data.message,{ icon: 1,time: 1000 },function(){
					reset();
				});
			}else{
				layer.msg(data.message);
			}
		},"json");
		
		}, function(){
			
		});
	});
	//切换状态
	$(".delete").click(function(){
		var status=$(this).attr("status");
		var userId=$(this).attr("userId");
		if(!status||!userId){
			layer.msg("页面数据丢失");
			return;
		}
		var param={};
		param.userId=userId;
		var tip="确认删除吗？";
		param.status=status;
		layer.confirm(tip, {
			title:"状态切换",
			btn: ["确定","返回"], //按钮
			shade: false //不显示遮罩
		}, function(){
			$.post("delete.html",param,function(data){
				if(data.status==1){
					layer.msg(data.message,{ icon: 1,time: 1000 },function(){
						reset();
					});
				}else{
					layer.msg(data.message);
				}
			},"json");
			
		}, function(){
			
		});
	});
	
	//重置密码 
	$(".reset").click(function(){
		var userId=$(this).attr("userId");
		var param={};
		param.userId=userId;
		if(!userId){
			layer.msg("页面数据丢失");
			return;
		}
		layer.confirm("确定重置密码吗？", {
			title:"密码重置",
		    btn: ["确定","返回"], //按钮
		    shade: false //不显示遮罩
		}, function(){
			$.post("reset.html",param,function(data){
			if(data.status==1){
				layer.msg(data.message,{ icon: 1,time: 1000 },function(){
					reset();
				});
			}else{
				layer.msg(data.message);
			}
		},"json");
		
		}, function(){
			
		});
	});
	
	//增加
	$(".addUser").click(function(){		
		$("#addModal").modal("show");//展示modal
	});
	
	var bak;//修改时，信息备份
	//修改
	$(".editUser").click(function(){
		var userId=$(this).attr("userId");
	 	if(userId){
	 		var param={};
	 		param.operator="findById";
	 		param.userId=userId;
			$.post("update.html",param,function(json){
				if(json.status==1){
					bak=json.data;
						//修改框 数据初始化
					$("#edit_nickname").val(bak.nickname);
					$("#edit_userId").val(bak.adminUserId);
					$("#edit_loginname").val(bak.loginname);
					$("#edit_phone").val(bak.phone);
					$("#edit_email").val(bak.email);
					$("#edit_description").val(bak.description);
					
					$("#updateModal .userType").removeAttr("checked");
					$("#updateModal .userType").parent("span").removeClass("checked");
					if(bak.type==1){//管理账号
						$("#updateModal .userType[value='1']").attr("checked","checked");
						$("#updateModal .userType[value='1']").parent("span").addClass("checked");
					}else if(bak.type==0){//门户账号
						$("#updateModal .userType[value='0']").attr("checked","checked");
						$("#updateModal .userType[value='0']").parent("span").addClass("checked");
					}
					$("#edit_roleArr option").removeAttr("selected");
					var roleIds=[];
					$("#edit_roleArr option").each(function(){
						var _this=this;
						$(bak.roles).each(function(){
							if(this.roleId==$(_this).val()){
								$(_this).attr("selected","selected");
								roleIds.push(this.roleId);
							}
						});
					});
					
					$("#edit_roleArr").trigger("liszt:updated");
					$("#updateModal").modal("show");
				}else{
					layer.msg(json.message);
				}
				}
			,"json");		
		} else{
			layer.msg("页面数据错误！");
		}
	});
	
	//刷新页面
	function reset(){
		$("#myform").submit();
	}
	
	function initEmail($email){
    var MailList = ["qq.com", 
                    "hotmail.com",
					"163.com",
					"yahoo.com",
					"sina.com","126.com","sina.cn"];
    $email.autocomplete({
        autoFocus: true,
        source: function (request, response) {
            var KeyValue = request.term,
                index = KeyValue.indexOf("@"),
                Address = KeyValue,
                host = "",
                result = [];

            result.push(KeyValue);
            if (index > -1) {
                Address = KeyValue.slice(0, index);
                host = KeyValue.slice(index + 1);
            }
            if (Address) {
                var findedHosts = (host ? $.grep(MailList, function (value) {
                    return value.indexOf(host) > -1;
                }) : MailList),
                findedResults = $.map(findedHosts, function (value) {
                    return Address + "@" + value;
                });
                result = result.concat($.makeArray(findedResults));
            }
            response(result);
        }
    });
	}
	
	$.validator.addMethod("isLoginname", function(value, element) {   
   	 var name = /^[\d\w]{6,20}$/;
   	 return this.optional(element) || (name.test(value));
	});
	$.validator.addMethod("isPhone", function(value, element) {   
   	 var tel = /^1[3|4|5|8][0-9]\d{4,8}$/;
   	 return this.optional(element) || (tel.test(value)&&value.length==11);
	});
	$.validator.addMethod("isEmail", function(value, element) {   
   	 var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
   	 return this.optional(element) || (email.test(value));
	});