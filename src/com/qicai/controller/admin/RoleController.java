package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.admin.RoleManager;
import com.qicai.controller.BaseController;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.util.JSONUtil;

/**
 * ��ɫ����
 * 
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value = "role")
@LimitTag(uri = true)
public class RoleController extends BaseController {
	// ��ҳ��ѯ���н�ɫ
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<RoleManagerDTO> roles = roleManagerService.getAllRole();
		model.addAttribute("roles", roles);
		return "admin/roleManager";
	}

	// �������еĲ�ѯ�߼��Լ�ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			String roleId = request.getParameter("roleId");
			if (roleId != null && roleId.matches("\\d+")) {

				Integer id = Integer.parseInt(roleId);
				RoleManagerDTO role = roleManagerService.getById(id);
				json.setStatus(1).setData(role);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("�����쳣");
			model.addAttribute("json", JSONUtil.object2json(json));
		}
		return JSON;
	}

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JsonDTO json = new JsonDTO();
		String roleName = request.getParameter("roleName");
		if (roleName != null && roleName.length() <= 20) {
			RoleManager saveParam = new RoleManager();
			saveParam.setRoleName(roleName);
			saveParam.setStatus(1);
			saveParam.setCreateDate(new Date());
			try {
				roleManagerService.saveOrUpdate(saveParam);
				json.setStatus(1).setMessage("�����ɫ�ɹ�");
			} catch (Exception e) {
				e.printStackTrace();
				json.setStatus(0).setMessage("�����ɫʱ����������:" + e.getMessage());
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}

		return null;
	}

	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		String roleName = request.getParameter("roleName");
		String roleId = request.getParameter("roleId");
		String status = request.getParameter("status");
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			if (roleId != null && roleId.matches("\\d+")) {

				Integer id = Integer.parseInt(roleId);
				RoleManagerDTO role = roleManagerService.getById(id);
				json.setStatus(1).setData(role);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (roleId != null && roleId.matches("\\d+")
				&& (status != null || roleName != null)) {
			// ���
			if ((roleName == null || roleName.length() <= 20)
					&& (status == null || status.matches("\\d"))) {
				Integer roleIdInt = Integer.parseInt(roleId);
				Integer statusInt = null;
				if (status != null) {
					statusInt = Integer.parseInt(status);
				}
				RoleManager updateParam = new RoleManager();
				updateParam.setRoleId(roleIdInt);
				updateParam.setStatus(statusInt);
				updateParam.setRoleName(roleName);
				try {
					roleManagerService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("���½�ɫ�ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("���½�ɫʧ��:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// �л�״̬
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String roleId = request.getParameter("roleId");
		String status = request.getParameter("status");
		if (roleId != null && roleId.matches("\\d+") && status != null
				&& status.matches("[01]")) {
			// ���
			Integer roleIdInt = Integer.parseInt(roleId);
			Integer statusInt = null;
			if (status != null) {
				statusInt = Integer.parseInt(status);
			}
			RoleManager updateParam = new RoleManager();
			updateParam.setRoleId(roleIdInt);
			updateParam.setStatus(statusInt);
			try {
				roleManagerService.saveOrUpdate(updateParam);
				json.setStatus(1).setMessage("���½�ɫ�ɹ�");
			} catch (Exception e) {
				e.printStackTrace();
				json.setStatus(0).setMessage("���½�ɫʧ��:" + e.getMessage());
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return "json";
		}
		return null;
	}

}
