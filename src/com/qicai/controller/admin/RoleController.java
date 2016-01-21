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
 * 角色管理
 * 
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value = "role")
@LimitTag(uri = true)
public class RoleController extends BaseController {
	// 首页查询所有角色
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<RoleManagerDTO> roles = roleManagerService.getAllRole();
		model.addAttribute("roles", roles);
		return "admin/roleManager";
	}

	// 负责所有的查询逻辑以及ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			String roleId = request.getParameter("roleId");
			if (roleId != null && roleId.matches("\\d+")) {

				Integer id = Integer.parseInt(roleId);
				RoleManagerDTO role = roleManagerService.getById(id);
				json.setStatus(1).setData(role);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("参数异常");
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
				json.setStatus(1).setMessage("保存角色成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setStatus(0).setMessage("保存角色时，发生错误:" + e.getMessage());
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
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			if (roleId != null && roleId.matches("\\d+")) {

				Integer id = Integer.parseInt(roleId);
				RoleManagerDTO role = roleManagerService.getById(id);
				json.setStatus(1).setData(role);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (roleId != null && roleId.matches("\\d+")
				&& (status != null || roleName != null)) {
			// 检测
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
					json.setStatus(1).setMessage("更新角色成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("更新角色失败:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// 切换状态
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String roleId = request.getParameter("roleId");
		String status = request.getParameter("status");
		if (roleId != null && roleId.matches("\\d+") && status != null
				&& status.matches("[01]")) {
			// 检测
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
				json.setStatus(1).setMessage("更新角色成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setStatus(0).setMessage("更新角色失败:" + e.getMessage());
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return "json";
		}
		return null;
	}

}
