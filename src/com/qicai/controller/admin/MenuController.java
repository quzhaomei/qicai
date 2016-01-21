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
	// ������ת�˵�ҳ��
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		if (LIST.equals(operator)) {// ��ȡ����
			List<MenuManagerDTO> menus = menuManagerService.getAllMenu();// ��ȡ���е�menu
			model.addAttribute("json", JSONUtil.object2json(menus));
			return JSON;
		}
		return "admin/menuManager";
	}

	// �������еĲ�ѯ�߼��Լ�ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			String menuId = request.getParameter("menuId");
			if (menuId != null && menuId.matches("\\d+")) {
				Integer id = Integer.parseInt(menuId);
				MenuManagerDTO menu = menuManagerService.getById(id);
				json.setStatus(1).setData(menu);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("�����쳣");
		}
		return JSON;
	}

	// ���Ӳ˵�
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
				&& type.matches("\\d+") && uri != null && uri.length() < 50) {// ��
			Integer id = Integer.parseInt(menuId);
			Integer typeInt = Integer.parseInt(type);
			if (id == -1 && (typeInt == 0 || typeInt == 1)) {// ֻ��������ҳ��,��˵���
				MenuManager saveParam = new MenuManager();
				saveParam.setParentId(id);
				saveParam.setType(typeInt);
				saveParam.setUri(uri);
				saveParam.setCreateDate(new Date());
				saveParam.setStatus(1);
				saveParam.setMenuName(menuName);
				try {
					menuManagerService.saveOrUpdate(saveParam);
					json.setStatus(1)// �����ɹ�
							.setMessage("����˵��ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0)// ����ʧ��
							.setMessage("����˵�ʱ��ϵͳ�쳣:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			} else if (id != -1 && (typeInt == 1 || typeInt == 2)) {
				MenuManagerDTO menuDTO = menuManagerService.getById(id);
				if (menuDTO != null) {// �˵���Ϊ�գ���ſɲ���
					Integer menuType = menuDTO.getType();
					MenuManager saveParam = new MenuManager();
					if (menuType == 0 && typeInt == 1) {// ֻ��Ϊ����ҳ��
						saveParam.setParentId(id);
						saveParam.setType(typeInt);
						saveParam.setUri(uri);
						saveParam.setCreateDate(new Date());
						saveParam.setStatus(1);
						saveParam.setMenuName(menuName);
						try {
							menuManagerService.saveOrUpdate(saveParam);
							json.setStatus(1);// �����ɹ�
							json.setMessage("����˵��ɹ�");
						} catch (Exception e) {
							e.printStackTrace();
							json.setStatus(0)// ����ʧ��
									.setMessage("����˵�ʱ��ϵͳ�쳣:" + e.getMessage());
						}
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					} else if (menuType == 1 && typeInt == 2) {// ֻ��Ϊ��������
						saveParam.setParentId(id);
						saveParam.setType(typeInt);
						saveParam.setUri(uri);
						saveParam.setCreateDate(new Date());
						saveParam.setStatus(1);
						saveParam.setMenuName(menuName);
						try {
							menuManagerService.saveOrUpdate(saveParam);
							json.setStatus(1)// �����ɹ�
									.setMessage("����˵��ɹ�");
						} catch (Exception e) {
							e.printStackTrace();
							json.setStatus(0)// ����ʧ��
									.setMessage("����˵�ʱ��ϵͳ�쳣:" + e.getMessage());
						}
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					}
				}
			}
		}
		return null;
	}

	// ���²˵�
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String menuName = request.getParameter("menuName");
		String uri = request.getParameter("uri");
		String menuId = request.getParameter("menuId");
		JsonDTO json = new JsonDTO();
		if (menuId != null && menuId.matches("\\d+")) {// ��
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
						json.setStatus(1)// �����ɹ�
								.setMessage("���²˵��ɹ�");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0)// ����ʧ��
								.setMessage("���²˵�ʱ��ϵͳ�쳣:" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}
		return null;
	}

	// ɾ���˵�
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String menuId = request.getParameter("menuId");
		JsonDTO json = new JsonDTO();
		if (menuId != null && menuId.matches("\\d+")) {// ɾ
			// �������Ӳ˵���ʱ�� ������ɾ����
			Integer id = Integer.parseInt(menuId);
			MenuManager selectParam = new MenuManager();
			selectParam.setParentId(id);
			List<MenuManagerDTO> childMenu = menuManagerService
					.getListByParam(selectParam);
			if (childMenu.size() != 0) {
				json.setStatus(0);// ����ʧ��
				json.setMessage("����ɾ���ò˵��µ��Ӳ˵�");
			} else {
				MenuManager delParam = new MenuManager();
				delParam.setMenuId(id);
				try {
					menuManagerService.delete(delParam);
					json.setStatus(1)// �����ɹ�
							.setMessage("ɾ���˵��ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0)// ����ʧ��
							.setMessage("ɾ���ò˵�ʱ��ϵͳ�쳣");
				}
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}
		return null;
	}

}
