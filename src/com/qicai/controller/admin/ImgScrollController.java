package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.ImgScroll;
import com.qicai.controller.BaseController;
import com.qicai.dto.ImgScrollDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.util.JSONUtil;

/**
 * banner�ֲ�����
 * 
 * @author qzm
 */
@Controller
@RequestMapping(value = "scroll")
@LimitTag(uri = true)
public class ImgScrollController extends BaseController {
	// ��ҳ��ѯ���н�ɫ
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<ImgScrollDTO> scrolls = scrollService.findAll();
		model.addAttribute("scrolls", scrolls);
		return "admin/imgScroll";
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
				ImgScrollDTO scroll = scrollService.getById(id);
				json.setStatus(1).setData(scroll);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("�����쳣");
			model.addAttribute("json", JSONUtil.object2json(json));
		}
		return JSON;
	}

	// ����banner
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (ADD.equals(operator)) {
			String title = request.getParameter("title");
			String imgPath = request.getParameter("imgPath");
			String href = request.getParameter("href");
			if (title != null && title.length() <= 40 && imgPath != null
					&& imgPath.length() <= 50 && href != null
					&& href.length() <= 200) {
				ImgScroll saveParam = new ImgScroll();
				saveParam.setTitle(title);
				saveParam.setStatus(1);
				saveParam.setHref(href);
				saveParam.setImgPath(imgPath);
				saveParam.setCreateDate(new Date());
				try {
					scrollService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("�����ֲ�ͼƬ�ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage(
							"�����ֲ�ͼƬʱ����������:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			String scrollId = request.getParameter("scrollId");
			if (scrollId != null && scrollId.matches("\\d+")) {

				Integer id = Integer.parseInt(scrollId);
				ImgScrollDTO scroll = scrollService.getById(id);
				json.setStatus(1).setData(scroll);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (EDIT.equals(operator)) {
			String scrollId = request.getParameter("scrollId");
			String status = request.getParameter("status");
			String title = request.getParameter("title");
			String imgPath = request.getParameter("imgPath");
			String href = request.getParameter("href");
			if (scrollId != null && scrollId.matches("\\d+")) {
				// ���
				if ((title == null || title.length() <= 40)
						&& (status == null || status.matches("\\d"))
						&& (imgPath == null || imgPath.length() <= 50)
						&& (href == null || href.length() <= 200)) {
					Integer scrollIdInt = Integer.parseInt(scrollId);

					ImgScroll updateParam = new ImgScroll();
					updateParam.setHref(href);
					updateParam.setScrollId(scrollIdInt);
					updateParam.setTitle(title);
					updateParam.setImgPath(imgPath);
					if (status != null) {
						updateParam.setStatus(Integer.parseInt(status));
					}
					try {
						scrollService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("�����ֲ��ɹ�");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0)
								.setMessage("�����ֲ�ʧ��:" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}
		return JSON;
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

				ImgScroll updateParam = new ImgScroll();
				updateParam.setScrollId(scrollIdInt);
				if (status != null) {
					updateParam.setStatus(Integer.parseInt(status));
				}
				try {
					scrollService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("�����ֲ��ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("�����ֲ�ʧ��:" + e.getMessage());
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
