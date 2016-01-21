package com.qicai.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.ArticalTag;
import com.qicai.controller.BaseController;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.PageDTO;
import com.qicai.util.ChineseToCharUtil;
import com.qicai.util.JSONUtil;

/**
 * 文章标签管理
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "tag")
@LimitTag(uri = true)
public class ZcarArticalTagController extends BaseController {
	// 菜单管理页面
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");// 页码
		String pageSize = request.getParameter("pageSize");// 页容量
		String type = request.getParameter("type");
		if (pageIndex == null) {
			pageIndex = "1";
		}
		if (pageSize == null) {
			pageSize = "7";
		}
		if (pageIndex.matches("\\d+") && pageSize.matches("\\d+")) {
			ArticalTag param = new ArticalTag();
			if (type != null && type.matches("\\d+") && !"0".equals(type))
				param.setType(Integer.parseInt(type));

			PageDTO<ArticalTag> page = new PageDTO<ArticalTag>();
			page.setPageIndex(Integer.parseInt(pageIndex));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setParam(param);
			PageDTO<List<ArticalTagDTO>> tagList = tagService
					.getListByPage(page);// 获取所有的来源
			model.addAttribute("pageResult", tagList);
			model.addAttribute("type", type);
		}
		return "admin/articalTag";
	}

	// 菜单明细
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {
			String tagId = request.getParameter("tagId");
			if (tagId != null && tagId.matches("-?\\d+")) {
				Integer tagIdInt = Integer.parseInt(tagId);
				ArticalTagDTO TagDTO = tagService.getById(tagIdInt);
				json.setStatus(1).setData(TagDTO);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 增加文章标签
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_ADD.equals(operator)) {
			model.addAttribute("operator", ADD);
			return "admin/tagAdd";
		} else if (ADD.equals(operator)) {
			String tagName = request.getParameter("tagName");
			String type = request.getParameter("type");
			String href = request.getParameter("href");
			String imgPath = request.getParameter("imgPath");
			String imgWidth = request.getParameter("imgWidth");
			String imgHeight = request.getParameter("imgHeight");
			String introduce = request.getParameter("introduce");

			if (tagName != null && tagName.length() <= 20 && type != null
					&& type.matches("\\d") && href != null
					&& href.length() <= 50 && imgPath != null
					&& imgWidth != null && imgWidth.matches("\\d{1,5}")
					&& imgHeight != null && imgHeight.matches("\\d{1,5}")
					&& introduce != null) {
				ArticalTag saveParam = new ArticalTag();
				saveParam.setTagName(tagName);
				// 拼音
				String pinying = ChineseToCharUtil.converterToSpell(tagName);
				saveParam.setPinying(pinying);
				saveParam.setType(Integer.parseInt(type));
				saveParam.setImgHeight(Integer.parseInt(imgHeight));
				saveParam.setImgWidth(Integer.parseInt(imgWidth));
				saveParam.setImgPath(imgPath);
				saveParam.setStatus(1);
				saveParam.setCreateDate(new Date());
				saveParam.setIntroduce(introduce);
				saveParam.setHref(href);
				try {
					tagService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("保存标签成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("保存来源失败：" + e.getMessage());
					e.printStackTrace();
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// 文章标签更新
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_EDIT.equals(operator)) {// 如果是跳转修改页面
			String tagId = request.getParameter("tagId");// 如果是修改标签
			if (tagId != null && tagId.matches("\\d+")) {
				Integer tagIdInt = Integer.parseInt(tagId);// 转化为整数；
				ArticalTagDTO tag = tagService.getById(tagIdInt);
				if (tag != null) {
					model.addAttribute("tag", tag);
					model.addAttribute("bak", JSONUtil.object2json(tag));
					model.addAttribute("operator", "edit");// 操作为修改
					return "admin/tagAdd";
				}
			}
		} else if (EDIT.equals(operator)) {// 如果是修改
			String tagId = request.getParameter("tagId");
			String tagName = request.getParameter("tagName");
			String type = request.getParameter("type");
			String status = request.getParameter("status");
			String href = request.getParameter("href");
			String imgPath = request.getParameter("imgPath");
			String imgWidth = request.getParameter("imgWidth");
			String imgHeight = request.getParameter("imgHeight");
			String introduce = request.getParameter("introduce");
			String toTop = request.getParameter("toTop");// 置顶

			if (tagId != null && tagId.matches("-?\\d+")
					&& (tagName == null || tagName.length() <= 20)
					&& (type == null || type.matches("\\d{1,2}"))
					&& (status == null || status.matches("\\d{1,2}"))
					&& (href == null || href.length() <= 50)
					&& (imgPath == null || imgPath.length() <= 50)
					&& (imgWidth == null || imgWidth.matches("\\d{1,5}"))
					&& (imgHeight == null || imgHeight.matches("\\d{1,5}"))) {
				Integer tagIdInt = Integer.parseInt(tagId);
				ArticalTagDTO tagDTO = tagService.getById(tagIdInt);
				if (tagDTO != null) {
					ArticalTag articalTag = new ArticalTag();
					articalTag.setTagId(tagIdInt);
					articalTag.setTagName(tagName);
					if (tagName != null) {
						articalTag.setPinying(ChineseToCharUtil
								.converterToSpell(tagName));
					}
					articalTag.setHref(href);
					articalTag.setImgPath(imgPath);
					if (imgHeight != null)
						articalTag.setImgHeight(Integer.parseInt(imgHeight));
					if (imgWidth != null)
						articalTag.setImgWidth(Integer.parseInt(imgWidth));
					if (type != null)
						articalTag.setType(Integer.parseInt(type));
					if (status != null) {
						articalTag.setStatus(Integer.parseInt(status));
					}
					if (toTop != null) {
						articalTag.setTopDate(new Date());
					}
					articalTag.setIntroduce(introduce);
					try {
						tagService.saveOrUpdate(articalTag);
						json.setStatus(1).setMessage("更新标签成功");
					} catch (Exception e) {
						json.setStatus(0).setMessage(
								"更新标签来源失败：" + e.getMessage());
						e.printStackTrace();
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}

		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 文章标签状态切换
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String tagId = request.getParameter("tagId");
		String status = request.getParameter("status");

		if (tagId != null && tagId.matches("-?\\d+")
				&& (status != null && status.matches("[01]"))) {
			Integer tagIdInt = Integer.parseInt(tagId);
			ArticalTagDTO tagDTO = tagService.getById(tagIdInt);
			if (tagDTO != null) {
				ArticalTag articalTag = new ArticalTag();
				articalTag.setTagId(tagIdInt);
				if (status != null) {
					articalTag.setStatus(Integer.parseInt(status));
				}
				try {
					tagService.saveOrUpdate(articalTag);
					json.setStatus(1).setMessage("更新标签成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("更新标签来源失败：" + e.getMessage());
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

	// 文章标签置顶
	@RequestMapping(value = "/toTop")
	public String toTop(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String tagId = request.getParameter("tagId");
		String toTop = request.getParameter("toTop");

		if (tagId != null && tagId.matches("-?\\d+") && toTop != null) {
			Integer tagIdInt = Integer.parseInt(tagId);
			ArticalTagDTO tagDTO = tagService.getById(tagIdInt);
			if (tagDTO != null) {
				ArticalTag articalTag = new ArticalTag();
				articalTag.setTagId(tagIdInt);
				articalTag.setTopDate(new Date());
				try {
					tagService.saveOrUpdate(articalTag);
					json.setStatus(1).setMessage("置顶标签成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("置顶标签来源失败：" + e.getMessage());
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
