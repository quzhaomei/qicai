package com.qicai.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.Artical;
import com.qicai.bean.TopManager;
import com.qicai.controller.BaseController;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.ArticalResourceDTO;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.PageDTO;
import com.qicai.dto.TopManagerDTO;
import com.qicai.util.JSONUtil;

/**
 * 首页置顶分类管理
 * 
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value = "topManager")
@LimitTag(uri = true)
public class TopManagerController extends BaseController {
	private static final Integer PAGE_INDEX = 1;
	private static final Integer PAGE_SIZE = 5;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// 首页查询所有角色
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		if (LIST.equals(operator)) {// 查找top分类下的文章
			JsonDTO json = new JsonDTO();
			String topId = request.getParameter("topId");
			String index = request.getParameter("index");
			if (topId != null && topId.matches("\\d+") && index != null
					&& index.matches("\\d+")) {
				Integer topIdInt = Integer.parseInt(topId);
				Integer indexInt = Integer.parseInt(index);
				PageDTO<Artical> page = new PageDTO<Artical>();
				page.setPageIndex(indexInt);
				page.setPageSize(PAGE_SIZE);
				page.setParam(new Artical());
				PageDTO<List<ArticalDTO>> result = articalService
						.selectedArticalByParamAndTop(page, topIdInt);
				for (ArticalDTO artical : result.getParam()) {
					artical.setCreateDateStr(dateFormat.format(artical
							.getCreateDate()));
				}

				json.setStatus(1).setData(result);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
			json.setStatus(0).setMessage("参数异常");
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}
		List<TopManagerDTO> tops = topManagerService.getAllTopManager();

		PageDTO<Artical> page = new PageDTO<Artical>();
		page.setPageIndex(PAGE_INDEX);
		page.setPageSize(PAGE_SIZE);
		page.setParam(new Artical());
		// 查询每一个置顶分类中的前5文章
		for (TopManagerDTO temp : tops) {
			PageDTO<List<ArticalDTO>> result = articalService
					.selectedArticalByParamAndTop(page, temp.getTopId());
			temp.setArticals(result);
		}
		model.addAttribute("tops", tops);
		return "admin/topManager";
	}

	// 负责所有的查询逻辑以及ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			String topId = request.getParameter("topId");
			if (topId != null && topId.matches("\\d+")) {

				Integer id = Integer.parseInt(topId);
				TopManagerDTO managerDTO = topManagerService.getById(id);
				if (managerDTO != null) {
					json.setStatus(1).setData(managerDTO);
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}

		json.setStatus(0).setMessage("参数异常");
		model.addAttribute("json", JSONUtil.object2json(json));

		return JSON;
	}

	// 置顶分类添加文章
	@RequestMapping(value = "/fillTop")
	public String fillTop(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_ADD.equals(operator)) {// 跳转到新增的界面
			String topId = request.getParameter("topId");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String tagId = request.getParameter("tagId");
			String pageIndex = request.getParameter("pageIndex");
			String pageSize = request.getParameter("pageSize");
			String resourceId = request.getParameter("resourceId");
			String typeId = request.getParameter("typeId");

			Integer tagIdInt = null;
			Integer pageIndexInt = null;
			Integer pageSizeInt = null;
			Integer typeIdInt = null;
			Integer resourceIdInt = null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (startTime != null && startTime.matches("\\d{4}-\\d{2}-\\d{2}")) {
				try {
					startDate = format.parse(startTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (endTime != null && endTime.matches("\\d{4}-\\d{2}-\\d{2}")) {
				try {
					endDate = format.parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (typeId != null && typeId.matches("\\d+")) {
				typeIdInt = Integer.parseInt(typeId);
			}
			if (resourceId != null && resourceId.matches("\\d+")) {
				resourceIdInt = Integer.parseInt(resourceId);
			}
			if (tagId != null && tagId.matches("\\d+")) {
				tagIdInt = Integer.parseInt(tagId);
			}
			if (pageIndex != null && pageIndex.matches("\\d+")) {
				pageIndexInt = Integer.parseInt(pageIndex);
			} else {
				pageIndexInt = 1;
			}
			if (pageSize != null && pageSize.matches("\\d+")) {
				pageSizeInt = Integer.parseInt(pageSize);
			} else {
				pageSizeInt = 5;
			}
			if (topId != null && topId.matches("\\d+")) {
				Artical param = new Artical();
				param.setType(typeIdInt);
				param.setResourceId(resourceIdInt);

				PageDTO<Artical> selectPage = new PageDTO<Artical>();
				selectPage.setPageIndex(pageIndexInt);
				selectPage.setPageSize(pageSizeInt);
				selectPage.setParam(param);

				Integer topIdInt = Integer.parseInt(topId);
				PageDTO<List<ArticalDTO>> result = articalService
						.chonseUnselectArticalByParamAndTop(selectPage,
								topIdInt, tagIdInt, startDate, endDate);
				// 查询所有的标签
				List<ArticalTagDTO> tags = tagService.getAllTag();
				model.addAttribute("tags", tags);

				// 查询所有的来源
				List<ArticalResourceDTO> resources = resourceService
						.getAllArtical();
				model.addAttribute("resources", resources);

				model.addAttribute("tagId", tagIdInt);
				model.addAttribute("topId", topIdInt);
				model.addAttribute("startTime", startTime);
				model.addAttribute("endTime", endTime);
				model.addAttribute("pageIndex", pageIndex);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("resourceId", resourceIdInt);
				model.addAttribute("typeId", typeIdInt);
				model.addAttribute("pageResult", result);
			}
			return "admin/addTop";
		} else if (ADD.equals(operator)) {// 新增置顶分类
			String topId = request.getParameter("topId");
			String articalId = request.getParameter("articalId");
			if (topId != null && topId.matches("\\d+") && articalId != null
					&& articalId.matches("\\d+")) {
				Integer topIdInt = Integer.parseInt(topId);
				Integer articalIdInt = Integer.parseInt(articalId);
				try {
					topManagerService.addArtical(articalIdInt, topIdInt);
					json.setStatus(1).setMessage("置顶文章成功！");
				} catch (Exception e) {
					json.setStatus(0).setMessage("置顶文章失败：" + e.getMessage());
					e.printStackTrace();
				}

				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}

		} else if (EDIT.equals(operator)) {
			String topId = request.getParameter("topId");
			String articalId = request.getParameter("articalId");
			if (topId != null && topId.matches("\\d+") && articalId != null
					&& articalId.matches("\\d+")) {
				Integer topIdInt = Integer.parseInt(topId);
				Integer articalIdInt = Integer.parseInt(articalId);
				try {
					topManagerService.articalToTop(articalIdInt, topIdInt);
					json.setStatus(1).setMessage("置顶文章成功！");
				} catch (Exception e) {
					json.setStatus(0).setMessage("置顶文章失败：" + e.getMessage());
					e.printStackTrace();
				}

			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		} else if (DEL.equals(operator)) {
			String topId = request.getParameter("topId");
			String articalId = request.getParameter("articalId");
			if (topId != null && topId.matches("\\d+") && articalId != null
					&& articalId.matches("\\d+")) {
				Integer topIdInt = Integer.parseInt(topId);
				Integer articalIdInt = Integer.parseInt(articalId);
				try {
					topManagerService.articalTopRemove(articalIdInt, topIdInt);
					json.setStatus(1).setMessage("移除置顶文章成功！");
				} catch (Exception e) {
					json.setStatus(0).setMessage("移除文章失败：" + e.getMessage());
					e.printStackTrace();
				}

			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}

		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 新增置顶分类
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JsonDTO json = new JsonDTO();
		String name = request.getParameter("name");
		if (name != null && name.length() <= 10) {
			TopManager saveParam = new TopManager();
			saveParam.setName(name);
			saveParam.setStatus(1);
			try {
				topManagerService.saveOrUpdate(saveParam);
				json.setStatus(1).setMessage("新添分类成功！").setData(saveParam);
			} catch (Exception e) {
				e.printStackTrace();
				json.setStatus(0).setMessage("新添分类时，发生错误:" + e.getMessage());
			}
			model.addAttribute("json", JSONUtil.object2json(json));
			return JSON;
		}
		return null;
	}

	// 更新置顶分类
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String operator = request.getParameter("operator");
		if (FIND_BY_ID.equals(operator)) {// 获取当个
			String topId = request.getParameter("topId");
			if (topId != null && topId.matches("\\d+")) {
				
				Integer id = Integer.parseInt(topId);
				TopManagerDTO managerDTO = topManagerService.getById(id);
				if (managerDTO != null) {
					json.setStatus(1).setData(managerDTO);
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}
		String name = request.getParameter("name");
		String topId = request.getParameter("topId");
		String status = request.getParameter("status");
		if (topId != null && topId.matches("\\d+")
				&& (status != null || name != null)) {
			// 检测
			if ((name == null || name.length() <= 10)
					&& (status == null || status.matches("[01]"))) {
				Integer topIdInt = Integer.parseInt(topId);
				Integer statusInt = null;
				if (status != null) {
					statusInt = Integer.parseInt(status);
				}
				TopManager updateParam = new TopManager();
				updateParam.setTopId(topIdInt);
				updateParam.setStatus(statusInt);
				updateParam.setName(name);
				try {
					topManagerService.saveOrUpdate(updateParam);
					TopManagerDTO newDate = topManagerService.getById(topIdInt);
					json.setStatus(1).setData(newDate).setMessage("更新成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("更新失败:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}

	// 删除置顶分类
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String topId = request.getParameter("topId");
		String status = request.getParameter("status");
		if (topId != null && topId.matches("\\d+") && status != null) {
			// 检测
			if ((status == null || status.matches("[2]"))) {
				Integer topIdInt = Integer.parseInt(topId);
				Integer statusInt = null;
				if (status != null) {
					statusInt = Integer.parseInt(status);
				}
				TopManager updateParam = new TopManager();
				updateParam.setTopId(topIdInt);
				updateParam.setStatus(statusInt);
				try {
					topManagerService.saveOrUpdate(updateParam);
					TopManagerDTO newDate = topManagerService.getById(topIdInt);
					json.setStatus(1).setData(newDate).setMessage("删除成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("删除失败:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}

}
