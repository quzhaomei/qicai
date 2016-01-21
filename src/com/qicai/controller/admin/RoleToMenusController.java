package com.qicai.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.controller.BaseController;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.util.JSONUtil;
/**
 * ��ɫȨ�޿���
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value="power")
@LimitTag(uri=true)
public class RoleToMenusController extends BaseController {
	//��ת��ҳ��
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model){
		//��ѯ���е�role
		List<RoleManagerDTO> allRoles= roleManagerService.getAllRole();
		model.addAttribute("allRoles", allRoles);
		return "admin/power";
	}
	//����˵��Ĳ�ѯ
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model){
		String operator=request.getParameter("operator");
		JsonDTO json=new JsonDTO();
		if(FIND_BY_ID.equals(operator)){//���ص����˵�
			String roleId=request.getParameter("roleId");
			if(roleId!=null&&roleId.matches("\\d+")){
				Integer roleIdInt=Integer.parseInt(roleId);
				RoleManagerDTO role=roleManagerService.getById(roleIdInt);
				if(role!=null){
					List<MenuManagerDTO> menus=roleToMenusService.getRoleMenusByRoleId(roleIdInt);
					Map<String,Object> result=new HashMap<String, Object>();
					result.put("role", role);
					result.put("menus", menus);
					json.setStatus(1).setData(result);
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
			
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}
	//��½����ת��ҳ��
	@RequestMapping(value="/saveOrUpdate")
	public String saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Model model){
		String operator=request.getParameter("operator");
		String roleId=request.getParameter("roleId");
		JsonDTO json=new JsonDTO();
		if(EDIT.equals(operator)&&roleId!=null&&roleId.matches("\\d+")){
			Integer roleIdInt=Integer.parseInt(roleId);
			String[] menuIds=request.getParameterValues("menuIds[]");
			String[] tempIds=request.getParameterValues("tempIds[]");
			RoleManagerDTO role=roleManagerService.getById(roleIdInt);
			if(role!=null){//���role�������
				try {
					roleToMenusService.batchUpdateRoleMenus(menuIds, tempIds, roleIdInt);
					json.setStatus(1).setMessage("���³ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("����ʧ��:"+e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		json.setStatus(0).setMessage("��������");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}
}
