package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.InfoScroll;
import com.qicai.controller.BaseController;
import com.qicai.dto.InfoScrollDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.util.JSONUtil;

/**
 * banner�ֲ�����
 * 
 * @author qzm
 */
@Controller
@RequestMapping(value = "infoScroll")
@LimitTag(uri = true)
public class InfoScrollController extends BaseController {
	// ��ҳ��ѯ���н�ɫ
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<InfoScrollDTO> scrolls = infoScrollService.findAll();
		model.addAttribute("scrolls", scrolls);
		return "admin/infoScroll";
	}

	// �������еĲ�ѯ�߼��Լ�ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			String scrollId = request.getParameter("scrollId");
			if (scrollId != null && scrollId.matches("\\d+")) {

				Integer id = Integer.parseInt(scrollId);
				InfoScrollDTO scroll = infoScrollService.getById(id);
				json.setStatus(1).setData(scroll);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("�����쳣");
			model.addAttribute("json", JSONUtil.object2json(json));
		}
		return JSON;
	}

	// ����������Ѷ
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (ADD.equals(operator)) {
			String title = request.getParameter("title");
			String href = request.getParameter("href");
			if (title != null && title.length() <= 16 && href != null
					&& href.length() <= 50) {
				InfoScroll saveParam = new InfoScroll();
				saveParam.setTitle(title);
				saveParam.setStatus(1);
				saveParam.setHref(href);
				saveParam.setCreateDate(new Date());
				try {
					infoScrollService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("�����ֲ���Ѷ���ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage(
							"�����ֲ���Ѷ���ʱ����������:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// �����ֲ���Ѷ
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			String scrollId = request.getParameter("scrollId");
			if (scrollId != null && scrollId.matches("\\d+")) {

				Integer id = Integer.parseInt(scrollId);
				InfoScrollDTO scroll = infoScrollService.getById(id);
				json.setStatus(1).setData(scroll);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (EDIT.equals(operator)) {
			String scrollId = request.getParameter("scrollId");
			String status = request.getParameter("status");
			String title = request.getParameter("title");
			String href = request.getParameter("href");
			if (scrollId != null && scrollId.matches("\\d+")) {
				// ���
				if ((title == null || title.length() <= 16)
						&& (status == null || status.matches("\\d"))
						&& (href == null || href.length() <= 50)) {
					Integer scrollIdInt = Integer.parseInt(scrollId);

					InfoScroll updateParam = new InfoScroll();
					updateParam.setHref(href);
					updateParam.setScrollId(scrollIdInt);
					updateParam.setTitle(title);
					if (status != null) {
						updateParam.setStatus(Integer.parseInt(status));
					}
					try {
						infoScrollService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("�����ֲ���Ѷ���ɹ�");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0).setMessage(
								"�����ֲ���Ѷ���ʧ��:" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return "json";
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();

		String scrollId = request.getParameter("scrollId");
		String status = request.getParameter("status");
		if (scrollId != null && scrollId.matches("\\d+")) {
			// ���
			if (status != null && status.matches("[01]")) {
				Integer scrollIdInt = Integer.parseInt(scrollId);

				InfoScroll updateParam = new InfoScroll();
				updateParam.setScrollId(scrollIdInt);
				if (status != null) {
					updateParam.setStatus(Integer.parseInt(status));
				}
				try {
					infoScrollService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("�����ֲ���Ѷ���ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0)
							.setMessage("�����ֲ���Ѷ���ʧ��:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}
}
