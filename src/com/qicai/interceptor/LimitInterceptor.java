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
 * Ȩ������
 */
public class LimitInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();

		//�����δ��¼״̬�����ȡcookie���Զ���½
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
			        	}else{//����cookie
			        		CookieUtil.addCookie(response, BaseController.LOGIN_TAG,
			        				null, 0);
			        	}
			        }else{//����cookie
			        	CookieUtil.addCookie(response, BaseController.LOGIN_TAG,
		        				null, 0);
			        }
			}
		}
		
		//�Ż���վ���� ��½����
		if(handler.getClass().getAnnotation(DoorTag.class)!=null
				&&session.getAttribute(BaseController.USER_SESSION)==null){
			String requestType = request.getHeader("X-Requested-With"); 
			if(requestType!=null){//ajax����
					JsonDTO json=new JsonDTO();
					json.setStatus(3).setMessage("���ȵ�¼");//δ��¼
					response.getOutputStream().write(JSONUtil.object2json(json).getBytes("utf-8"));
				}else{
					String servletName=request.getSession().getServletContext().getServletContextName();
					response.sendRedirect("/"+servletName+"/toLogin.html");//��½
				}
			return false;
		}
		
		
		request.setAttribute("pageUri", request.getRequestURI());//ҳ��uri��
		if(handler.getClass().getAnnotation(LimitTag.class)!=null){
			LimitTag tag=handler.getClass().getAnnotation(LimitTag.class);
			boolean adminLogin=tag.adminLogin();//���غ�̨��¼
			boolean uri=tag.uri();//����uri
			if(adminLogin){//���غ�̨��¼
				AdminUserDTO adminUser=(AdminUserDTO) request.getSession().getAttribute(BaseController.ADMIN_USER_SESSION);
				if(adminUser!=null){
					String godPhone=request.getSession().getServletContext().getInitParameter("godPhone");
					if(uri&&(adminUser.getPhone()==null||!adminUser.getPhone().equals(godPhone))){//�����Ϊ��������Ա��������uri
						String uriStr=request.getRequestURI();//��ȡuri
						if(uriStr.matches(".*.+/.+/.+")){//��֤uri��ʽ
							uriStr=uriStr.substring(uriStr.lastIndexOf("/",uriStr.lastIndexOf("/")-1)+1);
							//�õ�����·��
							Map<String,MenuManagerDTO> menus=adminUser.getMenuList();
							if(menus!=null&&menus.get(uriStr)!=null){
								return true;
							}
						}
						//û��Ȩ�ޡ�
						String requestType = request.getHeader("X-Requested-With"); 
						if(requestType!=null){
						//ajax����
							JsonDTO json=new JsonDTO();
							json.setStatus(2).setMessage("��Ȩ��");//
							response.getOutputStream().write(JSONUtil.object2json(json).getBytes("utf-8"));
						}else{
							String servletName=request.getSession().getServletContext().getServletContextName();
							response.sendRedirect("/"+servletName+"/welcome/index.html");//��ҳ
						}
						
						return false;
					}
					return true;
				}else{
					//û��Ȩ�ޡ�
					String requestType = request.getHeader("X-Requested-With"); 
					if(requestType!=null){
					//ajax����
						JsonDTO json=new JsonDTO();
						json.setStatus(3).setMessage("���ȵ�¼");//δ��¼
						response.getOutputStream().write(JSONUtil.object2json(json).getBytes("utf-8"));
					}else{
						String servletName=request.getSession().getServletContext().getServletContextName();
						response.sendRedirect("/"+servletName+"/login.html");//���µ�¼
					}
					return false;
				}
			}
		}
		return true;
	}
}
