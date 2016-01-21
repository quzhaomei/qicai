package com.qicai.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.ZcarMenu;
import com.qicai.controller.BaseController;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.ZcarMenuDTO;
import com.qicai.util.JSONUtil;

@Controller
@RequestMapping(value = "zcarMenu")
@LimitTag(uri = true)
public class ZcarMenuController extends BaseController {
	// 菜单管理页面
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		if (LIST.equals(operator)) {// ajax查找所有菜单
			List<ZcarMenuDTO> menus = zcarMenuService.getAllMenu();
			model.addAttribute("json", JSONUtil.object2json(menus));
			return JSON;
		}
		return "admin/zcarMenu";
	}

	// 菜单明细
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (CHECK.equals(operator)) {
			String menuId = request.getParameter("menuId");
			String position = request.getParameter("position");
			if (menuId != null && menuId.matches("-?\\d+") && position != null
					&& position.matches("\\d{1,2}")) {
				Integer menuIdInt = Integer.parseInt(menuId);
				Integer positionInt = Integer.parseInt(position);
				ZcarMenu zcarMenu = new ZcarMenu();
				zcarMenu.setParentId(menuIdInt);
				zcarMenu.setPosition(positionInt);
				ZcarMenuDTO menu = zcarMenuService.getMenuByParam(zcarMenu);
				if (menu != null) {
					try {
						response.getWriter().write("false");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().write("true");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		} else if (FIND_BY_ID.equals(operator)) {
			String menuId = request.getParameter("menuId");
			if (menuId != null && menuId.matches("-?\\d+")) {
				Integer menuIdInt = Integer.parseInt(menuId);
				ZcarMenuDTO menu = zcarMenuService.getById(menuIdInt);
				json.setStatus(1).setData(menu);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 菜单更新逻辑
	@RequestMapping(value = "/saveOrUpdate")
	public String saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (ADD.equals(operator)) {
			String menuId = request.getParameter("menuId");
			String uri = request.getParameter("uri");
			String menuName = request.getParameter("menuName");
			String position = request.getParameter("position");
			if (menuId != null && menuId.matches("-?\\d+") && uri != null
					&& uri.length() <= 50 && menuName != null
					&& menuName.length() <= 10 && position != null
					&& position.matches("\\d+{1,2}")) {
				Integer menuIdInt = Integer.parseInt(menuId);
				Integer positionInt = Integer.parseInt(position);
				ZcarMenu zcarMenu = new ZcarMenu();
				zcarMenu.setParentId(menuIdInt);
				zcarMenu.setName(menuName);
				zcarMenu.setUri(uri);
				zcarMenu.setPosition(positionInt);
				zcarMenu.setCreateDate(new Date());
				try {
					zcarMenuService.saveOrUpdate(zcarMenu);
					json.setStatus(1).setMessage("保存菜单成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("保存菜单失败：" + e.getMessage());
					e.printStackTrace();
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (DEL.equals(operator)) {
			String menuId = request.getParameter("menuId");
			if (menuId != null && menuId.matches("\\d+")) {
				Integer menuIdInt = Integer.parseInt(menuId);
				ZcarMenuDTO menu = zcarMenuService.getById(menuIdInt);
				if (menu != null) {// 检测是否含有子菜单
					List<ZcarMenuDTO> menus = zcarMenuService
							.getMenuByParentId(menuIdInt);
					if (menus == null || menus.size() == 0) {
						ZcarMenu delParam = new ZcarMenu();
						delParam.setMenuId(menuIdInt);
						try {
							zcarMenuService.delete(delParam);
							json.setStatus(1).setMessage("删除成功");
						} catch (Exception e) {
							e.printStackTrace();
							json.setStatus(0).setMessage(
									"删除失败：" + e.getMessage());
						}
					} else {
						json.setStatus(0).setMessage("请先删除该菜单下的所有子菜单");
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		} else if (EDIT.equals(operator)) {// 如果是修改
			String menuId = request.getParameter("menuId");
			String uri = request.getParameter("uri");
			String menuName = request.getParameter("menuName");
			String position = request.getParameter("position");
			if (menuId != null && menuId.matches("-?\\d+")
					&& (uri == null || uri.length() <= 50)
					&& (menuName == null || menuName.length() <= 10)
					&& (position == null || position.matches("\\d+{1,2}"))) {
				Integer menuIdInt = Integer.parseInt(menuId);
				Integer positionInt = null;
				if(position!=null){
					positionInt=Integer.parseInt(position);
				}
				ZcarMenu zcarMenu = new ZcarMenu();
				zcarMenu.setMenuId(menuIdInt);
				zcarMenu.setName(menuName);
				zcarMenu.setUri(uri);
				zcarMenu.setPosition(positionInt);
				try {
					zcarMenuService.saveOrUpdate(zcarMenu);
					json.setStatus(1).setMessage("更新菜单成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("更新菜单失败：" + e.getMessage());
					e.printStackTrace();
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}

		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}
}
