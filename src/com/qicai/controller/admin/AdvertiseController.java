package com.qicai.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.Advertise;
import com.qicai.controller.BaseController;
import com.qicai.dto.AdvertiseDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.util.JSONUtil;

/**
 * 广告管理
 * 
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value = "advertise")
@LimitTag(uri = true)
public class AdvertiseController extends BaseController {
	// 首页查询所有角色
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<AdvertiseDTO> ads = advertiseService.findAll();
		model.addAttribute("ads", ads);
		return "admin/advertiseManager";
	}

	@RequestMapping(value = "/saveOrUpdate")
	public String saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {
			String advertiseId = request.getParameter("advertiseId");
			if (advertiseId != null && advertiseId.matches("\\d+")) {
				Integer advertiseIdInt = Integer.parseInt(advertiseId);
				AdvertiseDTO advertiseDTO = advertiseService
						.findById(advertiseIdInt);
				json.setStatus(1).setData(advertiseDTO);

				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (ADD.equals(operator)) {

		} else if (EDIT.equals(operator)) {
			String advertiseId = request.getParameter("advertiseId");
			String imgPath = request.getParameter("imgPath");
			String href = request.getParameter("href");
			if (advertiseId != null && advertiseId.matches("\\d+")
					&& (imgPath != null || href != null)) {
				// 检测
				Integer advertiseIdInt = Integer.parseInt(advertiseId);
				Advertise updateParam = new Advertise();
				updateParam.setAdvertiseId(advertiseIdInt);
				updateParam.setImgPath(imgPath);
				updateParam.setHref(href);
				try {
					advertiseService.update(updateParam);
					json.setStatus(1).setMessage("更新广告成功！");
					model.addAttribute("json", JSONUtil.object2json(json));
				} catch (Exception e) {
					json.setStatus(0).setMessage("更新广告失败：" + e.getMessage());
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

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String advertiseId = request.getParameter("advertiseId");
		String operator = request.getParameter("operator");
		JsonDTO jsonDTO = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {
			if (advertiseId != null && advertiseId.matches("\\d+")) {
				Integer advertiseIdInt = Integer.parseInt(advertiseId);
				AdvertiseDTO advertiseDTO = advertiseService
						.findById(advertiseIdInt);
				jsonDTO.setStatus(1).setData(advertiseDTO);

				model.addAttribute("json", JSONUtil.object2json(jsonDTO));
				return JSON;
			}
		}
		jsonDTO.setStatus(0).setMessage("参数异常");
		model.addAttribute("json", JSONUtil.object2json(jsonDTO));
		return JSON;
	}
}
