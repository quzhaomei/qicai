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
 * Ȩ�޿��Ʊ�ǩ
 */
public class PowerTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String uri;// �󶨲˵�
	private Integer roleId;// �󶨽�ɫ

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
				BaseController.ADMIN_USER_SESSION);// ��ȡuserSession
		String godPhone = request.getSession().getServletContext()
				.getInitParameter("godPhone");
		if (godPhone.equals(user.getPhone())) {// ����ǿ������˺ţ�����Ҫ�κ�����
			return super.doStartTag();
		} else if (roleId != null) {// ��֤��ɫ
			if (user.getRoles() != null) {
				for (RoleManagerDTO temp : user.getRoles()) {
					if (temp.getRoleId().equals(roleId)) {
						return super.doStartTag();
					}
				}
			}
			return SKIP_BODY;
		} else if (uri != null) {// ��֤����
			String tempUri = uri.substring(3);
			if (user.getMenuList().get(tempUri) != null) {
				return super.doStartTag();
			}
			return SKIP_BODY;
		} else {// ���߶�Ϊ��
			return super.doStartTag();
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = bodyContent.getEnclosingWriter();
		// ȡ�ñ�ǩ�����
		BodyContent body = getBodyContent();
		// ȡ�ñ�ǩ����ַ�������
		String content = body.getString();
		try {
			out.print(content);
			body.clearBody();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// �����Ա�ǩ��Ĵ���
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
