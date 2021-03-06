package com.qicai.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.Artical;
import com.qicai.bean.ArticalResource;
import com.qicai.bean.ArticalTag;
import com.qicai.bean.Video;
import com.qicai.controller.BaseController;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.ArticalResourceDTO;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.PageDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.util.JSONUtil;

/**
 * 视频
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "zcarVideo")
@LimitTag(uri = true)
public class ZcarVideoController extends BaseController {
	protected static final Integer TYPE = 6;// 6:代表视频

	// 资讯管理页面
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");// 页码
		String pageSize = request.getParameter("pageSize");// 页容量
		if (pageIndex == null) {
			pageIndex = "1";
		}
		if (pageSize == null) {
			pageSize = "10";
		}
		if (pageIndex.matches("\\d+") && pageSize.matches("\\d+")) {
			Integer pageIndexInt = Integer.parseInt(pageIndex);
			Integer pageSizeInt = Integer.parseInt(pageSize);
			PageDTO<Artical> page = new PageDTO<Artical>();
			if (pageIndexInt != 0 && pageSizeInt != 0) {
				page.setPageIndex(pageIndexInt);
				page.setPageSize(pageSizeInt);
				Artical selectParam = new Artical();
				selectParam.setType(TYPE);
				// selectParam.setStatus(1);//查找未删除的
				page.setParam(selectParam);
				PageDTO<List<ArticalDTO>> pageDate = articalService
						.getPageByParam(page);
				// 查找所有角色
				model.addAttribute("pageResult", pageDate);
				model.addAttribute("type", TYPE);
				return "admin/zcarVideo";
			}
		}
		return "admin/zcarVideo";
	}

	// 视频详细页面
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {
			String articalId = request.getParameter("articalId");
			if (articalId != null && articalId.matches("\\d+")) {
				Integer articalIdInt = Integer.parseInt(articalId);
				ArticalDTO articalDTO = articalService.getById(articalIdInt);
				// 处理视频
				Integer videoId = articalDTO.getReferId();
				articalDTO.setVideo(videoService.getById(videoId));

				model.addAttribute("type", TYPE);
				model.addAttribute("artical", articalDTO);
			}
			return "admin/zcarNewsDetail";
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 资讯添加页面
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		AdminUserDTO adminUser = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_ADD.equals(operator)) {// 跳转到新增页面
			// 查找所有有效的来源
			ArticalResource selectParam = new ArticalResource();
			selectParam.setStatus(1);
			List<ArticalResourceDTO> resources = resourceService
					.getListByParam(selectParam);
			// 查找所有的标签
			ArticalTag selectTagParam = new ArticalTag();
			selectTagParam.setStatus(1);
			List<ArticalTagDTO> tags = tagService
					.getListByParam(selectTagParam);
			AuthorDTO author = authorService.getAuthorByUserId(adminUser
					.getAdminUserId());
			model.addAttribute("author", author);
			model.addAttribute("operator", ADD);
			model.addAttribute("resources", resources);
			model.addAttribute("tags", tags);
			model.addAttribute("type", TYPE);
			return "admin/zcarNewsAdd";
		} else if (ADD.equals(operator)) {// 新增操作
			String title = request.getParameter("title");// 标题
			String quote = request.getParameter("quote");// 引用
			String scanImgPath = request.getParameter("scanImgPath");// 缩略图
			String scanImgWidth = request.getParameter("scanImgWidth");// 缩略图宽度
			String scanImgHeight = request.getParameter("scanImgHeight");// 缩略图高度

			String resourceId = request.getParameter("resourceId");// 文章来源ID
			String[] tagIds = request.getParameterValues("tagIds[]");// 标签ID
			String content = request.getParameter("content");// 正文
			String sourcePath = request.getParameter("sourcePath");// 视频路径
			String videoType = request.getParameter("videoType");// 视频类别
			// 看该用户作者信息
			AuthorDTO author = authorService.getAuthorByUserId(adminUser
					.getAdminUserId());
			if (author == null) {
				json.setStatus(0).setMessage("请先前往您的作者中心设置您的作者信息！");
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
			if (title != null && title.length() <= 50 && quote != null
					&& quote.length() <= 200 && scanImgPath != null
					&& scanImgPath.length() < 50 && resourceId != null
					&& resourceId.matches("\\d+") && content != null
					&& sourcePath != null && scanImgWidth != null
					&& scanImgWidth.matches("\\d{1,4}")
					&& scanImgHeight != null
					&& scanImgHeight.matches("\\d{1,4}") && videoType != null
					&& videoType.matches("[01]")) {
				List<Integer> tagIdList = null;
				if (tagIds != null) {
					tagIdList = new ArrayList<Integer>();
					for (String tagId : tagIds) {
						if (tagId.matches("\\d+")) {
							tagIdList.add(Integer.parseInt(tagId));
						} else {
							json.setStatus(0).setMessage("数据异常");
							model.addAttribute("json",
									JSONUtil.object2json(json));
							return JSON;
						}
					}
				}
				Artical saveParam = new Artical();
				saveParam.setTitle(title);
				saveParam.setQuote(quote);
				saveParam.setAuthorId(author.getAuthorId());
				saveParam.setScanImgPath(scanImgPath);
				saveParam.setScanImgHeight(Integer.parseInt(scanImgHeight));
				saveParam.setScanImgWidth(Integer.parseInt(scanImgWidth));

				saveParam.setContent(content);
				saveParam.setResourceId(Integer.parseInt(resourceId));
				saveParam.setTagId(tagIdList);
				saveParam.setType(TYPE);
				// 先保存视频
				Video video = new Video();
				video.setPath(sourcePath);// 视频路径
				video.setType(Integer.parseInt(videoType));// 视频类别
				try {
					videoService.saveOrUpdate(video);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// saveParam.setSourcePath(sourcePath);
				saveParam.setReferId(video.getVideoId());
				// 初始化默认参数
				saveParam.setCommitNum(0);
				saveParam.setCreateDate(new Date());
				saveParam.setRightNum(0);
				saveParam.setWrongNum(0);
				saveParam.setSeeNum(0);
				saveParam.setStatus(2);// 生成时候为冻结状态
				try {
					articalService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("生成文章成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("生成文章时候错误：" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;

			}
		}
		return null;
	}

	// 资讯添加页面
	@RequestMapping(value = "/update")
	public String saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_EDIT.equals(operator)) {

			String articalId = request.getParameter("articalId");
			if (articalId != null && articalId.matches("\\d+")) {
				Integer articalIdInt = Integer.parseInt(articalId);
				ArticalDTO articalDTO = articalService.getById(articalIdInt);

				// 处理视频
				Integer videoId = articalDTO.getReferId();
				articalDTO.setVideo(videoService.getById(videoId));

				model.addAttribute("artical", articalDTO);
				model.addAttribute("bak", JSONUtil.object2json(articalDTO));// 页面数据备份

				// 查找所有有效的来源
				ArticalResource selectParam = new ArticalResource();
				selectParam.setStatus(1);
				List<ArticalResourceDTO> resources = resourceService
						.getListByParam(selectParam);
				// 查找后台用户的作者
				List<AuthorDTO> authors=authorService.getAdminAuthor();
				model.addAttribute("authors", authors);
				
				// 查找所有的标签
				ArticalTag selectTagParam = new ArticalTag();
				selectTagParam.setStatus(1);
				List<ArticalTagDTO> tags = tagService
						.getListByParam(selectTagParam);
				model.addAttribute("operator", "edit");
				model.addAttribute("resources", resources);
				model.addAttribute("tags", tags);
				model.addAttribute("type", TYPE);
			}
			return "admin/zcarNewsEdit";

		} else if (EDIT.equals(operator)) {
			String articalId = request.getParameter("articalId");// 文章ID
			String title = request.getParameter("title");// 标题
			String quote = request.getParameter("quote");// 引用
			String scanImgPath = request.getParameter("scanImgPath");// 缩略图
			String scanImgWidth = request.getParameter("scanImgWidth");// 缩略图宽度
			String scanImgHeight = request.getParameter("scanImgHeight");// 缩略图高度
			String status = request.getParameter("status");// 状态 0-删除，1-正常，2-冻结
			
			String authorId = request.getParameter("authorId");// 作者ID
			String resourceId = request.getParameter("resourceId");// 文章来源ID
			String[] tagIds = request.getParameterValues("tagIds[]");// 标签ID
			String content = request.getParameter("content");// 正文

			String sourcePath = request.getParameter("sourcePath");// 视频路径
			String videoType = request.getParameter("videoType");// 视频类别

			String topDate = request.getParameter("topDate");// 是否置顶 0-置顶 1-取消
			// 验证数据
			if (articalId != null
					&& articalId.matches("\\d+")
					&& (title == null || title.length() <= 50)
					&& (authorId == null || authorId.matches("\\d+"))
					&& (quote == null || quote.length() <= 200)
					&& (scanImgPath == null || scanImgPath.length() <= 50)
					&& (resourceId == null || resourceId.matches("\\d+"))
					&& (scanImgWidth == null || scanImgWidth
							.matches("\\d{1,4}"))
					&& (scanImgHeight == null || scanImgHeight
							.matches("\\d{1,4}"))
					&& (sourcePath == null || sourcePath.length() <= 50)
					&& (status == null || status.matches("\\d{1}"))
					&& (topDate == null || topDate.matches("\\d+"))
					&& (videoType == null || videoType.matches("[01]"))) {
				List<Integer> tagIdList = null;
				if (tagIds != null) {
					tagIdList = new ArrayList<Integer>();
					if (tagIds.length != 0
							&& !(tagIds.length == 1 && tagIds[0].equals("0"))) {// 不为清空标志

						for (String temp : tagIds) {
							if (!temp.matches("\\d+")) {
								json.setStatus(0).setMessage("数据异常");
								model.addAttribute("json",
										JSONUtil.object2json(json));
								return JSON;
							} else {
								tagIdList.add(Integer.parseInt(temp));
							}
						}
					}
				}
				// 验证
				ArticalDTO articalDTO = articalService.getById(Integer
						.parseInt(articalId));
				if (articalDTO != null) {
					Artical updateParam = new Artical();
					updateParam.setArticalId(articalDTO.getArticalId());
					// 置顶设置
					if ("1".equals(topDate)) {// 置顶
						updateParam.setTopDate(1);
					} else if ("0".equals(topDate)) {// 取消
						updateParam.setTopDate(0);
					}
					if(authorId!=null){
						updateParam.setAuthorId(Integer.parseInt(authorId));
					}

					updateParam.setTitle(title);
					updateParam.setQuote(quote);
					// 处理视频
					if (sourcePath != null || videoType != null) {
						Integer videoId = articalDTO.getReferId();
						Video upVideo = new Video();
						upVideo.setVideoId(videoId);
						if (videoType != null)
							upVideo.setType(Integer.parseInt(videoType));
						upVideo.setPath(sourcePath);
						try {
							videoService.saveOrUpdate(upVideo);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					// updateParam.setSourcePath(sourcePath);
					if (status != null) {
						updateParam.setStatus(Integer.parseInt(status));
					}
					if (resourceId != null) {
						updateParam.setResourceId(Integer.parseInt(resourceId));
					}
					updateParam.setScanImgPath(scanImgPath);
					if (scanImgHeight != null) {
						updateParam.setScanImgHeight(Integer
								.parseInt(scanImgHeight));
					}
					if (scanImgWidth != null) {
						updateParam.setScanImgWidth(Integer
								.parseInt(scanImgWidth));
					}
					updateParam.setTagId(tagIdList);
					updateParam.setContent(content);
					try {
						articalService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("更新成功");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0).setMessage("更新文章时失败");
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		} else if (DEL.equals(operator)) {
			String articalId = request.getParameter("articalId");// 文章ID
			if (articalId != null && articalId.matches("\\d+")) {
				Integer articalIdInt = Integer.parseInt(articalId);
				// 验证
				ArticalDTO articalDTO = articalService.getById(Integer
						.parseInt(articalId));
				if (articalDTO != null) {
					Artical updateParam = new Artical();
					updateParam.setArticalId(articalIdInt);
					updateParam.setStatus(0);
					try {
						articalService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("删除成功");
					} catch (Exception e) {
						json.setStatus(0).setMessage(
								"删除时，出现异常：" + e.getMessage());
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

	// 资讯切换状态
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");// 文章ID
		String status = request.getParameter("status");// 状态 0-删除，1-正常，2-冻结
		JsonDTO json = new JsonDTO();
		// 验证数据
		if (articalId != null && articalId.matches("\\d+")
				&& (status == null || status.matches("[12]"))) {
			ArticalDTO articalDTO = articalService.getById(Integer
					.parseInt(articalId));
			if (articalDTO != null) {
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				if (status != null) {
					updateParam.setStatus(Integer.parseInt(status));
				}
				try {
					articalService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("更新成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("更新文章时失败");
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// 删除资讯
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");// 文章ID
		JsonDTO json = new JsonDTO();
		// 验证数据
		if (articalId != null && articalId.matches("\\d+")) {
			ArticalDTO articalDTO = articalService.getById(Integer
					.parseInt(articalId));
			if (articalDTO != null) {
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				updateParam.setStatus(0);
				try {
					articalService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("更新成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("更新文章时失败");
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// 资讯添加页面
	@RequestMapping(value = "/toTop")
	public String toTop(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String articalId = request.getParameter("articalId");// 文章ID

		String topDate = request.getParameter("topDate");// 是否置顶 1-置顶 0-取消

		// 验证数据
		if (articalId != null && articalId.matches("\\d+")
				&& (topDate == null || topDate.matches("\\d+"))) {
			// 验证
			ArticalDTO articalDTO = articalService.getById(Integer
					.parseInt(articalId));
			if (articalDTO != null) {
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				// 置顶设置
				if ("1".equals(topDate)) {// 置顶
					updateParam.setTopDate(1);
				} else if ("0".equals(topDate)) {// 取消
					updateParam.setTopDate(0);
				}

				try {
					articalService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("更新成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("更新文章时失败");
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
