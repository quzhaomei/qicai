package com.qicai.jspTag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.qicai.controller.BaseController;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.dto.admin.RoleManagerDTO;

/**
 * 权限控制标签
 */
public class PowerTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String uri;// 绑定菜单
	private Integer roleId;// 绑定角色

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		AdminUserDTO user = (AdminUserDTO) request.getSession().getAttribute(
				BaseController.ADMIN_USER_SESSION);// 获取userSession
		String godPhone = request.getSession().getServletContext()
				.getInitParameter("godPhone");
		if (godPhone.equals(user.getPhone())) {// 如果是开发者账号，则不需要任何请求
			return super.doStartTag();
		} else if (roleId != null) {// 验证角色
			if (user.getRoles() != null) {
				for (RoleManagerDTO temp : user.getRoles()) {
					if (temp.getRoleId().equals(roleId)) {
						return super.doStartTag();
					}
				}
			}
			return SKIP_BODY;
		} else if (uri != null) {// 验证链接
			String tempUri = uri.substring(3);
			if (user.getMenuList().get(tempUri) != null) {
				return super.doStartTag();
			}
			return SKIP_BODY;
		} else {// 两者都为空
			return super.doStartTag();
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = bodyContent.getEnclosingWriter();
		// 取得标签体对象
		BodyContent body = getBodyContent();
		// 取得标签体的字符串内容
		String content = body.getString();
		try {
			out.print(content);
			body.clearBody();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 结束对标签体的处理
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public void release() {
		super.release();
	}
}
