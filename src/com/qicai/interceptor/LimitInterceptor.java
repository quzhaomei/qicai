package com.qicai.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qicai.annotation.DoorTag;
import com.qicai.annotation.LimitTag;
import com.qicai.controller.BaseController;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.service.AdminUserService;
import com.qicai.util.CookieUtil;
import com.qicai.util.JSONUtil;

/**
 * 权限拦截
 */
public class LimitInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();

		//如果是未登录状态，则获取cookie，自动登陆
		if(session.getAttribute(BaseController.USER_SESSION)==null){
			Cookie login=CookieUtil.getCookieByName(request, BaseController.LOGIN_TAG);
			if(login!=null){
				String userId=login.getValue();
				  ServletContext servletContext = request.getSession().getServletContext();
			        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			        AdminUserService userService = (AdminUserService) wac.getBean("adminUserService");
			        if(userId!=null&&userId.matches("\\d+")){
			        	AdminUserDTO adminUserDTO=userService.getById(Integer.parseInt(userId));
			        	if(adminUserDTO!=null){
			        		session.setAttribute(BaseController.USER_SESSION, adminUserDTO);
			        	}else{//销毁cookie
			        		CookieUtil.addCookie(response, BaseController.LOGIN_TAG,
			        				null, 0);
			        	}
			        }else{//销毁cookie
			        	CookieUtil.addCookie(response, BaseController.LOGIN_TAG,
		        				null, 0);
			        }
			}
		}
		
		//门户网站操作 登陆拦截
		if(handler.getClass().getAnnotation(DoorTag.class)!=null
				&&session.getAttribute(BaseController.USER_SESSION)==null){
			String requestType = request.getHeader("X-Requested-With"); 
			if(requestType!=null){//ajax请求
					JsonDTO json=new JsonDTO();
					json.setStatus(3).setMessage("请先登录");//未登录
					response.getOutputStream().write(JSONUtil.object2json(json).getBytes("utf-8"));
				}else{
					String servletName=request.getSession().getServletContext().getServletContextName();
					response.sendRedirect("/"+servletName+"/toLogin.html");//登陆
				}
			return false;
		}
		
		
		request.setAttribute("pageUri", request.getRequestURI());//页面uri绑定
		if(handler.getClass().getAnnotation(LimitTag.class)!=null){
			LimitTag tag=handler.getClass().getAnnotation(LimitTag.class);
			boolean adminLogin=tag.adminLogin();//拦截后台登录
			boolean uri=tag.uri();//拦截uri
			if(adminLogin){//拦截后台登录
				AdminUserDTO adminUser=(AdminUserDTO) request.getSession().getAttribute(BaseController.ADMIN_USER_SESSION);
				if(adminUser!=null){
					String godPhone=request.getSession().getServletContext().getInitParameter("godPhone");
					if(uri&&(adminUser.getPhone()==null||!adminUser.getPhone().equals(godPhone))){//如果不为超级管理员，则拦截uri
						String uriStr=request.getRequestURI();//获取uri
						if(uriStr.matches(".*.+/.+/.+")){//验证uri格式
							uriStr=uriStr.substring(uriStr.lastIndexOf("/",uriStr.lastIndexOf("/")-1)+1);
							//得到绝对路径
							Map<String,MenuManagerDTO> menus=adminUser.getMenuList();
							if(menus!=null&&menus.get(uriStr)!=null){
								return true;
							}
						}
						//没有权限。
						String requestType = request.getHeader("X-Requested-With"); 
						if(requestType!=null){
						//ajax请求
							JsonDTO json=new JsonDTO();
							json.setStatus(2).setMessage("无权限");//
							response.getOutputStream().write(JSONUtil.object2json(json).getBytes("utf-8"));
						}else{
							String servletName=request.getSession().getServletContext().getServletContextName();
							response.sendRedirect("/"+servletName+"/welcome/index.html");//首页
						}
						
						return false;
					}
					return true;
				}else{
					//没有权限。
					String requestType = request.getHeader("X-Requested-With"); 
					if(requestType!=null){
					//ajax请求
						JsonDTO json=new JsonDTO();
						json.setStatus(3).setMessage("请先登录");//未登录
						response.getOutputStream().write(JSONUtil.object2json(json).getBytes("utf-8"));
					}else{
						String servletName=request.getSession().getServletContext().getServletContextName();
						response.sendRedirect("/"+servletName+"/login.html");//重新登录
					}
					return false;
				}
			}
		}
		return true;
	}
}
