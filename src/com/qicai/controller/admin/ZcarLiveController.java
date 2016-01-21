package com.qicai.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 *�
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "zcarLive")
@LimitTag(uri = true)
public class ZcarLiveController extends BaseController {
	protected static final Integer TYPE = 5;// 5:����

	// ��Ѷ����ҳ��
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");// ҳ��
		String pageSize = request.getParameter("pageSize");// ҳ����
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
				// selectParam.setStatus(1);// ����δɾ����
				page.setParam(selectParam);
				PageDTO<List<ArticalDTO>> pageDate = articalService
						.getPageByParam(page);
				// �������н�ɫ
				model.addAttribute("pageResult", pageDate);
				model.addAttribute("type", TYPE);
				return "admin/zcarLive";
			}
		}
		return "admin/zcarLive";
	}

	// ��Ѷ��ϸҳ��
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
				model.addAttribute("type", TYPE);
				model.addAttribute("artical", articalDTO);
			}
			return "admin/zcarNewsDetail";
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// ��Ѷ���ҳ��
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdminUserDTO adminUser = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_ADD.equals(operator)) {// ��ת������ҳ��
			// ����������Ч����Դ
			ArticalResource selectParam = new ArticalResource();
			selectParam.setStatus(1);
			List<ArticalResourceDTO> resources = resourceService
					.getListByParam(selectParam);
			// �������еı�ǩ
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
		} else if (ADD.equals(operator)) {// ��������
			String title = request.getParameter("title");// ����
			String quote = request.getParameter("quote");// ����
			String scanImgPath = request.getParameter("scanImgPath");// ����ͼ
			String scanImgWidth = request.getParameter("scanImgWidth");// ����ͼ���
			String scanImgHeight = request.getParameter("scanImgHeight");// ����ͼ�߶�

			String resourceId = request.getParameter("resourceId");// ������ԴID
			String[] tagIds = request.getParameterValues("tagIds[]");// ��ǩID
			String content = request.getParameter("content");// ����

			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String locationPath = request.getParameter("locationPath");
			// �����û�������Ϣ
			AuthorDTO author = authorService.getAuthorByUserId(adminUser
					.getAdminUserId());
			if (author == null) {
				json.setStatus(0).setMessage("����ǰ����������������������������Ϣ��");
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
			if (title != null && title.length() <= 50 && quote != null
					&& quote.length() <= 200 && scanImgPath != null
					&& scanImgPath.length() < 50 && resourceId != null
					&& resourceId.matches("\\d+") && content != null
					&& startDate != null && endDate != null
					&& scanImgWidth != null && scanImgWidth.matches("\\d{1,4}")
					&& scanImgHeight != null
					&& scanImgHeight.matches("\\d{1,4}")) {
				List<Integer> tagIdList = null;
				if (tagIds != null) {
					tagIdList = new ArrayList<Integer>();
					for (String tagId : tagIds) {
						if (tagId.matches("\\d+")) {
							tagIdList.add(Integer.parseInt(tagId));
						} else {
							json.setStatus(0).setMessage("�����쳣");
							model.addAttribute("json",
									JSONUtil.object2json(json));
							return JSON;
						}
					}
				}
				Date startDateValue = null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					startDateValue = format.parse(startDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
					json.setStatus(0).setMessage("ʱ���ʽ����");
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
				Date endDateValue = null;
				try {
					endDateValue = format.parse(endDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
					json.setStatus(0).setMessage("ʱ���ʽ����");
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
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
				saveParam.setStartDate(startDateValue);
				saveParam.setEndDate(endDateValue);
				saveParam.setSourcePath(locationPath);

				// ��ʼ��Ĭ�ϲ���
				saveParam.setCommitNum(0);
				saveParam.setCreateDate(new Date());
				saveParam.setRightNum(0);
				saveParam.setWrongNum(0);
				saveParam.setSeeNum(0);
				saveParam.setStatus(2);//��ʼ��Ϊ����״̬
				try {
					articalService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("�������³ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("��������ʱ�����" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;

			}
		}
		return null;
	}

	// ��Ѷҳ�����
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_EDIT.equals(operator)) {

			String articalId = request.getParameter("articalId");
			if (articalId != null && articalId.matches("\\d+")) {
				Integer articalIdInt = Integer.parseInt(articalId);
				ArticalDTO articalDTO = articalService.getById(articalIdInt);
				model.addAttribute("artical", articalDTO);
				model.addAttribute("bak", JSONUtil.object2json(articalDTO));// ҳ�����ݱ���

				// ����������Ч����Դ
				ArticalResource selectParam = new ArticalResource();
				selectParam.setStatus(1);
				List<ArticalResourceDTO> resources = resourceService
						.getListByParam(selectParam);
				// ���Һ�̨�û�������
				List<AuthorDTO> authors=authorService.getAdminAuthor();
				model.addAttribute("authors", authors);
				// �������еı�ǩ
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
			String articalId = request.getParameter("articalId");// ����ID
			String title = request.getParameter("title");// ����
			String quote = request.getParameter("quote");// ����
			String scanImgPath = request.getParameter("scanImgPath");// ����ͼ
			String scanImgWidth = request.getParameter("scanImgWidth");// ����ͼ���
			String scanImgHeight = request.getParameter("scanImgHeight");// ����ͼ�߶�
			String status = request.getParameter("status");// ״̬ 0-ɾ����1-������2-����
			
			String authorId = request.getParameter("authorId");// ����ID
			String resourceId = request.getParameter("resourceId");// ������ԴID
			String[] tagIds = request.getParameterValues("tagIds[]");// ��ǩID
			String content = request.getParameter("content");// ����

			String topDate = request.getParameter("topDate");// �Ƿ��ö� 0-�ö� 1-ȡ��

			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String locationPath = request.getParameter("locationPath");

			// ��֤����
			if (articalId != null
					&& articalId.matches("\\d+")
					&& (title == null || title.length() <= 50)
					&& (quote == null || quote.length() <= 200)
					&& (authorId == null || authorId.matches("\\d+"))
					&& (scanImgPath == null || scanImgPath.length() <= 50)
					&& (resourceId == null || resourceId.matches("\\d+"))
					&& (startDate == null || startDate.length() == 10)
					&& (endDate == null || endDate.length() == 10)
					&& (locationPath == null || locationPath.length() <= 50)
					&& (scanImgWidth == null || scanImgWidth
							.matches("\\d{1,4}"))
					&& (scanImgHeight == null || scanImgHeight
							.matches("\\d{1,4}"))
					&& (status == null || status.matches("\\d{1}"))
					&& (topDate == null || topDate.matches("\\d+"))) {
				List<Integer> tagIdList = null;
				if (tagIds != null) {
					tagIdList = new ArrayList<Integer>();
					if (tagIds.length != 0
							&& !(tagIds.length == 1 && tagIds[0].equals("0"))) {// ��Ϊ��ձ�־

						for (String temp : tagIds) {
							if (!temp.matches("\\d+")) {
								json.setStatus(0).setMessage("�����쳣");
								model.addAttribute("json",
										JSONUtil.object2json(json));
								return JSON;
							} else {
								tagIdList.add(Integer.parseInt(temp));
							}
						}
					}

				}
				Date startDateValue = null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				if (startDate != null) {
					try {
						startDateValue = format.parse(startDate);
					} catch (ParseException e1) {
						e1.printStackTrace();
						json.setStatus(0).setMessage("��ʼʱ���ʽ����");
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					}
				}
				Date endDateValue = null;
				if (endDate != null) {
					try {
						endDateValue = format.parse(endDate);
					} catch (ParseException e1) {
						e1.printStackTrace();
						json.setStatus(0).setMessage("����ʱ���ʽ����");
						model.addAttribute("json", JSONUtil.object2json(json));
						return JSON;
					}
				}

				// ��֤
				ArticalDTO articalDTO = articalService.getById(Integer
						.parseInt(articalId));
				if (articalDTO != null) {
					Artical updateParam = new Artical();
					updateParam.setArticalId(articalDTO.getArticalId());

					// �ö�����
					if ("1".equals(topDate)) {// �ö�
						updateParam.setTopDate(1);
					} else if ("0".equals(topDate)) {// ȡ��
						updateParam.setTopDate(0);
					}
					if(authorId!=null){
						updateParam.setAuthorId(Integer.parseInt(authorId));
					}
					updateParam.setTitle(title);
					updateParam.setQuote(quote);
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
					updateParam.setStartDate(startDateValue);
					updateParam.setEndDate(endDateValue);
					updateParam.setSourcePath(locationPath);
					try {
						articalService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("���³ɹ�");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0).setMessage("��������ʱʧ��");
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		} else if (DEL.equals(operator)) {
			String articalId = request.getParameter("articalId");// ����ID
			if (articalId != null && articalId.matches("\\d+")) {
				Integer articalIdInt = Integer.parseInt(articalId);
				// ��֤
				ArticalDTO articalDTO = articalService.getById(Integer
						.parseInt(articalId));
				if (articalDTO != null) {
					Artical updateParam = new Artical();
					updateParam.setArticalId(articalIdInt);
					updateParam.setStatus(0);
					try {
						articalService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("ɾ���ɹ�");
					} catch (Exception e) {
						json.setStatus(0).setMessage(
								"ɾ��ʱ�������쳣��" + e.getMessage());
						e.printStackTrace();
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// ��Ѷ�л�״̬
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");// ����ID
		String status = request.getParameter("status");// ״̬ 0-ɾ����1-������2-����
		JsonDTO json = new JsonDTO();
		// ��֤����
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
					json.setStatus(1).setMessage("���³ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("��������ʱʧ��");
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// ɾ����Ѷ
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");// ����ID
		JsonDTO json = new JsonDTO();
		// ��֤����
		if (articalId != null && articalId.matches("\\d+")) {
			ArticalDTO articalDTO = articalService.getById(Integer
					.parseInt(articalId));
			if (articalDTO != null) {
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				updateParam.setStatus(0);
				try {
					articalService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("���³ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("��������ʱʧ��");
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// ��Ѷ���ҳ��
	@RequestMapping(value = "/toTop")
	public String toTop(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String articalId = request.getParameter("articalId");// ����ID

		String topDate = request.getParameter("topDate");// �Ƿ��ö� 1-�ö� 0-ȡ��

		// ��֤����
		if (articalId != null && articalId.matches("\\d+")
				&& (topDate == null || topDate.matches("\\d+"))) {
			// ��֤
			ArticalDTO articalDTO = articalService.getById(Integer
					.parseInt(articalId));
			if (articalDTO != null) {
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				// �ö�����
				if ("1".equals(topDate)) {// �ö�
					updateParam.setTopDate(1);
				} else if ("0".equals(topDate)) {// ȡ��
					updateParam.setTopDate(0);
				}

				try {
					articalService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("���³ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("��������ʱʧ��");
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
