package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.ArticalResource;
import com.qicai.controller.BaseController;
import com.qicai.dto.ArticalResourceDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.util.JSONUtil;

/**
 * ������Դ����
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "resource")
@LimitTag(uri = true)
public class ZcarArticalResourceController extends BaseController {
	// �˵�����ҳ��
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<ArticalResourceDTO> resourceList = resourceService.getAllArtical();// ��ȡ���е���Դ
		model.addAttribute("resources", resourceList);
		return "admin/articalResource";
	}

	// �˵���ϸ
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {
			String resourceId = request.getParameter("resourceId");
			if (resourceId != null && resourceId.matches("-?\\d+")) {
				Integer resourceIdInt = Integer.parseInt(resourceId);
				ArticalResourceDTO resourceDTO = resourceService
						.getById(resourceIdInt);
				json.setStatus(1).setData(resourceDTO);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// ������Դ����
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JsonDTO json = new JsonDTO();
		String url = request.getParameter("url");
		String name = request.getParameter("name");
		if (name != null && name.length() <= 20 && url != null
				&& url.length() <= 50) {
			ArticalResource saveParam = new ArticalResource();
			saveParam.setName(name);
			saveParam.setUrl(url);
			saveParam.setStatus(1);
			saveParam.setCreateDate(new Date());
			try {
				resourceService.saveOrUpdate(saveParam);
				json.setStatus(1).setMessage("������Դ�ɹ�");
			} catch (Exception e) {
				json.setStatus(0).setMessage("������Դʧ�ܣ�" + e.getMessage());
				e.printStackTrace();
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}
		return null;
	}

	// ������Դ�����߼�
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {
			String resourceId = request.getParameter("resourceId");
			if (resourceId != null && resourceId.matches("-?\\d+")) {
				Integer resourceIdInt = Integer.parseInt(resourceId);
				ArticalResourceDTO resourceDTO = resourceService
						.getById(resourceIdInt);
				json.setStatus(1).setData(resourceDTO);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (EDIT.equals(operator)) {// ������޸�
			String resourceId = request.getParameter("resourceId");
			String url = request.getParameter("url");
			String name = request.getParameter("name");
			String status = request.getParameter("status");
			if (resourceId != null && resourceId.matches("-?\\d+")
					&& (url == null || url.length() <= 50)
					&& (name == null || name.length() <= 20)
					&& (status == null || status.matches("\\d+{1,2}"))) {
				Integer resourceIdInt = Integer.parseInt(resourceId);
				ArticalResourceDTO resourceDTO = resourceService
						.getById(resourceIdInt);
				if (resourceDTO != null) {
					ArticalResource articalResource = new ArticalResource();
					articalResource.setResourceId(resourceIdInt);
					articalResource.setName(name);
					articalResource.setUrl(url);
					if (status != null) {
						articalResource.setStatus(Integer.parseInt(status));
					}
					try {
						resourceService.saveOrUpdate(articalResource);
						json.setStatus(1).setMessage("����������Դ�ɹ�");
					} catch (Exception e) {
						json.setStatus(0).setMessage(
								"����������Դʧ�ܣ�" + e.getMessage());
						e.printStackTrace();
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}

		}
		return null;
	}

	// ������Դ�����߼�
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String resourceId = request.getParameter("resourceId");
		String status = request.getParameter("status");
		if (resourceId != null && resourceId.matches("-?\\d+")
				&& (status == null || status.matches("[01]"))) {
			Integer resourceIdInt = Integer.parseInt(resourceId);
			ArticalResourceDTO resourceDTO = resourceService
					.getById(resourceIdInt);
			if (resourceDTO != null) {
				ArticalResource articalResource = new ArticalResource();
				articalResource.setResourceId(resourceIdInt);
				if (status != null) {
					articalResource.setStatus(Integer.parseInt(status));
				}
				try {
					resourceService.saveOrUpdate(articalResource);
					json.setStatus(1).setMessage("����������Դ�ɹ�");
				} catch (Exception e) {
					json.setStatus(0).setMessage("����������Դʧ�ܣ�" + e.getMessage());
					e.printStackTrace();
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}
}
