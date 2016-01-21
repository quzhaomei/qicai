package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.FriendHref;
import com.qicai.controller.BaseController;
import com.qicai.dto.FriendHrefDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.util.JSONUtil;

/**
 *友情链接管理
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value = "href")
@LimitTag(uri = true)
public class FriendHrefController extends BaseController {
	// 首页查询所有角色
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<FriendHrefDTO> hrefs = hrefService.findAll();
		model.addAttribute("hrefs", hrefs);
		return "admin/hrefManager";
	}

	// 负责所有的查询逻辑以及ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			String hrefId = request.getParameter("hrefId");
			if (hrefId != null && hrefId.matches("\\d+")) {

				Integer id = Integer.parseInt(hrefId);
				FriendHrefDTO hrefDTO = hrefService.getById(id);
				json.setStatus(1).setData(hrefDTO);
				model.addAttribute("json", JSONUtil.object2json(json));
			}
		} else {
			json.setStatus(0).setMessage("参数异常");
			model.addAttribute("json", JSONUtil.object2json(json));
		}
		return JSON;
	}

	// 新增友情链接
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (ADD.equals(operator)) {
			String name = request.getParameter("name");
			String href = request.getParameter("href");
			if (name != null && name.length() <= 10 && href != null
					&& href.length() <= 50) {
				FriendHref saveParam = new FriendHref();
				saveParam.setName(name);
				FriendHrefDTO hrefDTO = hrefService.findByParam(saveParam);
				if (hrefDTO != null) {
					json.setStatus(0).setMessage("名字已经存在");
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}

				saveParam.setHref(href);
				saveParam.setStatus(1);
				saveParam.setCreateDate(new Date());
				try {
					hrefService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("保存友情链接成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage(
							"保存友情链接时，发生错误:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// 新增友情链接
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			String hrefId = request.getParameter("hrefId");
			if (hrefId != null && hrefId.matches("\\d+")) {

				Integer id = Integer.parseInt(hrefId);
				FriendHrefDTO hrefDTO = hrefService.getById(id);
				json.setStatus(1).setData(hrefDTO);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}else
		if (EDIT.equals(operator)) {
			String name = request.getParameter("name");
			String hrefId = request.getParameter("hrefId");
			String href = request.getParameter("href");
			String status = request.getParameter("status");
			if (hrefId != null
					&& hrefId.matches("\\d+")
					&& ((name != null && name.length() <= 10)
							|| (href != null && href.length() <= 50) || (status != null && status
							.matches("\\d{1}")))) {
				FriendHref updateParam = new FriendHref();
				updateParam.setName(name);
				if (name != null) {
					FriendHrefDTO friendHrefDTO = hrefService
							.findByParam(updateParam);
					if (friendHrefDTO != null) {
						json.setStatus(0).setMessage("名字已经存在");
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					}
				}
				updateParam.setHrefId(Integer.parseInt(hrefId));
				if (status != null) {
					updateParam.setStatus(Integer.parseInt(status));
				}
				updateParam.setHref(href);
				try {
					hrefService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("更新链接成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("更新链接失败：" + e.getMessage());
					e.printStackTrace();
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return "json";
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";

	}

	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String hrefId = request.getParameter("hrefId");
		String status = request.getParameter("status");
		if (hrefId != null && hrefId.matches("\\d+") && status != null
				&& status.matches("[01]")) {
			FriendHref updateParam = new FriendHref();
			updateParam.setHrefId(Integer.parseInt(hrefId));
			if (status != null) {
				updateParam.setStatus(Integer.parseInt(status));
			}
			try {
				hrefService.saveOrUpdate(updateParam);
				json.setStatus(1).setMessage("更新链接成功");
			} catch (Exception e) {
				json.setStatus(0).setMessage("更新链接失败：" + e.getMessage());
				e.printStackTrace();
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return "json";
		}
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}
}
