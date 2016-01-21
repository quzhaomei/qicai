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
	//��½����ת��ҳ��
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model){
		//����Ƿ��Ѿ���¼����¼������ֱ����ת��ӭҳ��
		AdminUserDTO loginUser=(AdminUserDTO) request.getSession().getAttribute(ADMIN_USER_SESSION);
		if(loginUser!=null){
			try {
				response.sendRedirect("welcome/index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		String loginname=request.getParameter("loginname");//�û�
		String password=request.getParameter("password");//����
		if(loginname!=null&&password!=null){
			//��ʼ���
			if(password.length()<=30&&password.length()>=6){
				//��ʼ��֤��
				AdminUser adminUser=new AdminUser();
				adminUser.setLoginname(loginname);
				adminUser.setType(1);//�����˺�
				 loginUser=adminUserService.getUserByParam(adminUser);
				if(loginUser==null){
					model.addAttribute("info", "�û�������");
				}else if(!loginUser.getPassword().equalsIgnoreCase(PasswordUtil.MD5(password))){
					model.addAttribute("info", "�������");
				}
				else if(loginUser.getStatus()!=1){
					model.addAttribute("info", "���˺��ѱ�����");
				}else{
					//��ȡ�û��Ľ�ɫ
					List<RoleManagerDTO>roles= loginUser.getRoles();
					//�����ɫ�˵�
					if(roles!=null){
						Map<String,MenuManagerDTO> menuList=new HashMap<String, MenuManagerDTO>();
						for(RoleManagerDTO role:roles){
							Integer roleId=role.getRoleId();
							List<MenuManagerDTO> menus=roleToMenusService.getUseFullMenusByRoleId(roleId);
							for(MenuManagerDTO menu:menus){
								//����uri,../menu/index.html����
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
				model.addAttribute("info", "���ݴ���");
			}
		}
		return "admin/adminLogin";
	}
	//��ȫ�˳�
	@RequestMapping(value="/loginout")
	public String loginout(HttpServletRequest request,
			HttpServletResponse response, Model model){
		HttpSession session=request.getSession();
		session.invalidate();//����session
		
		 return "admin/adminLogin";
	}
}
