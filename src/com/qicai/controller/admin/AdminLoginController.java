package com.qicai.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.bean.admin.AdminUser;
import com.qicai.controller.BaseController;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.util.PasswordUtil;
@Controller
@RequestMapping(value="/")
public class AdminLoginController extends BaseController{
	//登陆后跳转的页面
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model){
		//检测是否已经登录，登录过，则直接跳转欢迎页面
		AdminUserDTO loginUser=(AdminUserDTO) request.getSession().getAttribute(ADMIN_USER_SESSION);
		if(loginUser!=null){
			try {
				response.sendRedirect("welcome/index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		String loginname=request.getParameter("loginname");//用户
		String password=request.getParameter("password");//密码
		if(loginname!=null&&password!=null){
			//开始检测
			if(password.length()<=30&&password.length()>=6){
				//开始验证。
				AdminUser adminUser=new AdminUser();
				adminUser.setLoginname(loginname);
				adminUser.setType(1);//管理账号
				 loginUser=adminUserService.getUserByParam(adminUser);
				if(loginUser==null){
					model.addAttribute("info", "用户名错误");
				}else if(!loginUser.getPassword().equalsIgnoreCase(PasswordUtil.MD5(password))){
					model.addAttribute("info", "密码错误");
				}
				else if(loginUser.getStatus()!=1){
					model.addAttribute("info", "该账号已被冻结");
				}else{
					//获取用户的角色
					List<RoleManagerDTO>roles= loginUser.getRoles();
					//处理角色菜单
					if(roles!=null){
						Map<String,MenuManagerDTO> menuList=new HashMap<String, MenuManagerDTO>();
						for(RoleManagerDTO role:roles){
							Integer roleId=role.getRoleId();
							List<MenuManagerDTO> menus=roleToMenusService.getUseFullMenusByRoleId(roleId);
							for(MenuManagerDTO menu:menus){
								//处理uri,../menu/index.html类似
								String uri=menu.getUri();
								if(uri.matches("\\.\\./\\w+/\\w+\\.html")){
									menuList.put(uri.substring(3), menu);
								}else{
									menuList.put(uri+menu.getMenuId(), menu);
								}
							}
						}
						loginUser.setMenuList(menuList);
					}
					request.getSession().setAttribute(ADMIN_USER_SESSION, loginUser);
					try {
						response.sendRedirect("welcome/index.html");
						return null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else{
				model.addAttribute("info", "数据错误");
			}
		}
		return "admin/adminLogin";
	}
	//安全退出
	@RequestMapping(value="/loginout")
	public String loginout(HttpServletRequest request,
			HttpServletResponse response, Model model){
		HttpSession session=request.getSession();
		session.invalidate();//销毁session
		
		 return "admin/adminLogin";
	}
}
