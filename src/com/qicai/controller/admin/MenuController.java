package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.admin.MenuManager;
import com.qicai.controller.BaseController;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.util.JSONUtil;

@Controller
@RequestMapping(value = "menu")
@LimitTag(uri = true)
public class MenuController extends BaseController {
	// 负责跳转菜单页面
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		if (LIST.equals(operator)) {// 获取所有
			List<MenuManagerDTO> menus = menuManagerService.getAllMenu();// 获取所有的menu
			model.addAttribute("json", JSONUtil.object2json(menus));
			return JSON;
		}
		return "admin/menuManager";
	}

	// 负责所有的查询逻辑以及ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			String menuId = request.getParameter("menuId");
			if (menuId != null && menuId.matches("\\d+")) {
				Integer id = Integer.parseInt(menuId);
				MenuManagerDTO menu = menuManagerService.getById(id);
				json.setStatus(1).setData(menu);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("参数异常");
		}
		return JSON;
	}

	// 增加菜单
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String menuName = request.getParameter("menuName");
		String type = request.getParameter("type");
		String uri = request.getParameter("uri");
		String menuId = request.getParameter("menuId");
		JsonDTO json = new JsonDTO();
		if (menuId != null && menuId.matches("-?\\d+") && menuName != null
				&& menuName.length() <= 10 && type != null
				&& type.matches("\\d+") && uri != null && uri.length() < 50) {// 增
			Integer id = Integer.parseInt(menuId);
			Integer typeInt = Integer.parseInt(type);
			if (id == -1 && (typeInt == 0 || typeInt == 1)) {// 只能增加子页面,或菜单组
				MenuManager saveParam = new MenuManager();
				saveParam.setParentId(id);
				saveParam.setType(typeInt);
				saveParam.setUri(uri);
				saveParam.setCreateDate(new Date());
				saveParam.setStatus(1);
				saveParam.setMenuName(menuName);
				try {
					menuManagerService.saveOrUpdate(saveParam);
					json.setStatus(1)// 操作成功
							.setMessage("插入菜单成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0)// 操作失败
							.setMessage("插入菜单时，系统异常:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			} else if (id != -1 && (typeInt == 1 || typeInt == 2)) {
				MenuManagerDTO menuDTO = menuManagerService.getById(id);
				if (menuDTO != null) {// 菜单不为空，则才可插入
					Integer menuType = menuDTO.getType();
					MenuManager saveParam = new MenuManager();
					if (menuType == 0 && typeInt == 1) {// 只能为新增页面
						saveParam.setParentId(id);
						saveParam.setType(typeInt);
						saveParam.setUri(uri);
						saveParam.setCreateDate(new Date());
						saveParam.setStatus(1);
						saveParam.setMenuName(menuName);
						try {
							menuManagerService.saveOrUpdate(saveParam);
							json.setStatus(1);// 操作成功
							json.setMessage("插入菜单成功");
						} catch (Exception e) {
							e.printStackTrace();
							json.setStatus(0)// 操作失败
									.setMessage("插入菜单时，系统异常:" + e.getMessage());
						}
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					} else if (menuType == 1 && typeInt == 2) {// 只能为新增操作
						saveParam.setParentId(id);
						saveParam.setType(typeInt);
						saveParam.setUri(uri);
						saveParam.setCreateDate(new Date());
						saveParam.setStatus(1);
						saveParam.setMenuName(menuName);
						try {
							menuManagerService.saveOrUpdate(saveParam);
							json.setStatus(1)// 操作成功
									.setMessage("插入菜单成功");
						} catch (Exception e) {
							e.printStackTrace();
							json.setStatus(0)// 操作失败
									.setMessage("插入菜单时，系统异常:" + e.getMessage());
						}
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					}
				}
			}
		}
		return null;
	}

	// 更新菜单
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String menuName = request.getParameter("menuName");
		String uri = request.getParameter("uri");
		String menuId = request.getParameter("menuId");
		JsonDTO json = new JsonDTO();
		if (menuId != null && menuId.matches("\\d+")) {// 改
			Integer id = Integer.parseInt(menuId);
			MenuManagerDTO menuDTO = menuManagerService.getById(id);
			if ((menuName != null || uri != null) && menuDTO != null) {
				if (uri == null || uri.length() < 50) {
					MenuManager updateParam = new MenuManager();
					updateParam.setMenuId(id);
					updateParam.setUri(uri);
					updateParam.setUpdateDate(new Date());
					updateParam.setMenuName(menuName);
					try {
						menuManagerService.saveOrUpdate(updateParam);
						json.setStatus(1)// 操作成功
								.setMessage("更新菜单成功");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0)// 操作失败
								.setMessage("更新菜单时，系统异常:" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}
		return null;
	}

	// 删除菜单
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String menuId = request.getParameter("menuId");
		JsonDTO json = new JsonDTO();
		if (menuId != null && menuId.matches("\\d+")) {// 删
			// 当含有子菜单的时候 不允许删除。
			Integer id = Integer.parseInt(menuId);
			MenuManager selectParam = new MenuManager();
			selectParam.setParentId(id);
			List<MenuManagerDTO> childMenu = menuManagerService
					.getListByParam(selectParam);
			if (childMenu.size() != 0) {
				json.setStatus(0);// 操作失败
				json.setMessage("请先删除该菜单下的子菜单");
			} else {
				MenuManager delParam = new MenuManager();
				delParam.setMenuId(id);
				try {
					menuManagerService.delete(delParam);
					json.setStatus(1)// 操作成功
							.setMessage("删除菜单成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0)// 操作失败
							.setMessage("删除该菜单时，系统异常");
				}
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}
		return null;
	}

}
