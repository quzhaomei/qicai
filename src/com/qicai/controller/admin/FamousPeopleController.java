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
import com.qicai.bean.FamousPeople;
import com.qicai.controller.BaseController;
import com.qicai.dto.FamousPeopleDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.util.ChineseToCharUtil;
import com.qicai.util.JSONUtil;

/**
 * ���˹���
 * 
 * @author qzm
 * @since 2015-8-31
 */
@Controller
@RequestMapping(value = "famous")
@LimitTag(uri = true)
public class FamousPeopleController extends BaseController {
	// ��ҳ��ѯ���н�ɫ
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		FamousPeople people = new FamousPeople();
		List<FamousPeopleDTO> peoples = famousPeopleService
				.getListByParam(people);
		model.addAttribute("peoples", peoples);
		return "admin/famousPeople";
	}

	// �������еĲ�ѯ�߼��Լ�ajax
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		if (FIND_BY_ID.equals(operator)) {// ��ȡ����
			String peopleId = request.getParameter("peopleId");
			if (peopleId != null && peopleId.matches("\\d+")) {

				Integer id = Integer.parseInt(peopleId);
				FamousPeopleDTO people = famousPeopleService.getById(id);
				model.addAttribute("people", people);
				model.addAttribute("operator", "edit");// ����Ϊ�޸�
			}
		} 
		return "admin/famousDetail";
	}

	// ��������
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_ADD.equals(operator)) {
			model.addAttribute("operator", ADD);
			return "admin/addFamous";
		} else if (ADD.equals(operator)) {
			String name = request.getParameter("name");
			String ename = request.getParameter("ename");
			String nationality = request.getParameter("nationality");
			String birthPlace = request.getParameter("birthPlace");
			String birthDay = request.getParameter("birthDay");
			String introduce = request.getParameter("introduce");
			String job = request.getParameter("job");
			String imgPath = request.getParameter("imgPath");
			String imgWidth = request.getParameter("imgWidth");
			String imgHeight = request.getParameter("imgHeight");

			if (name != null && name.length() <= 20 && introduce != null
					&& imgPath != null && imgPath.length() <= 50
					&& imgWidth != null && imgWidth.matches("\\d{1,5}")
					&& imgHeight != null && imgHeight.matches("\\d{1,5}")
					&& (job == null || job.length() <= 50)) {
				FamousPeople famousPeople = new FamousPeople();
				famousPeople.setCreateDate(new Date());
				famousPeople.setImgPath(imgPath);
				famousPeople.setImgHeight(Integer.parseInt(imgHeight));
				famousPeople.setImgWidth(Integer.parseInt(imgWidth));
				famousPeople.setIntroduce(introduce);
				famousPeople.setEname(ename);
				famousPeople.setNationality(nationality);
				famousPeople.setBirthPlace(birthPlace);
				try {
					famousPeople.setBirthDay(new SimpleDateFormat("yyyy-MM-dd")
							.parse(birthDay));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				famousPeople.setStatus(2);// Ϊ����
				famousPeople.setName(name);
				famousPeople.setPinying(ChineseToCharUtil
						.converterToSpell(name));
				famousPeople.setJob(job);
				try {
					famousPeopleService.saveOrUpdate(famousPeople);
					json.setStatus(1).setMessage("�������˳ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0)
							.setMessage("��������ʱ����������:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
		}
		return null;
	}

	// ��������
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (TO_EDIT.equals(operator)) {
			String peopleId = request.getParameter("peopleId");// ������޸ı�ǩ
			if (peopleId != null && peopleId.matches("\\d+")) {
				Integer peopleIdInt = Integer.parseInt(peopleId);// ת��Ϊ������
				FamousPeopleDTO people = famousPeopleService
						.getById(peopleIdInt);
				if (people != null) {
					model.addAttribute("people", people);
					model.addAttribute("bak", JSONUtil.object2json(people));
					model.addAttribute("operator", "edit");// ����Ϊ�޸�
					return "admin/addFamous";
				}
			}

		} else if (EDIT.equals(operator)) {
			String peopleId = request.getParameter("peopleId");

			String name = request.getParameter("name");
			String ename = request.getParameter("ename");
			String nationality = request.getParameter("nationality");
			String birthPlace = request.getParameter("birthPlace");
			String birthDay = request.getParameter("birthDay");
			String introduce = request.getParameter("introduce");
			String job = request.getParameter("job");
			String imgPath = request.getParameter("imgPath");
			String imgWidth = request.getParameter("imgWidth");
			String imgHeight = request.getParameter("imgHeight");

			String status = request.getParameter("status");
			String toTop = request.getParameter("toTop");// �ö�

			if (peopleId != null
					&& peopleId.matches("\\d+")
					&& (name == null || name.length() <= 20)
					&& (ename == null || ename.length() <= 50)
					&& (nationality == null || nationality.length() <= 50)
					&& (birthPlace == null || birthPlace.length() <= 50)
					&& (imgWidth == null || imgWidth.matches("\\d{1,5}"))
					&& (birthDay == null || birthDay
							.matches("\\d{4}-\\d{2}-\\d{2}"))
					&& (imgHeight == null || imgHeight.matches("\\d{1,5}"))
					&& (imgPath == null || imgPath.length() <= 50)
					&& (status == null || status.matches("\\d{1,2}"))) {
				Integer peopleIdInt = Integer.parseInt(peopleId);
				FamousPeopleDTO famousPeopleDTO = famousPeopleService
						.getById(peopleIdInt);
				if (famousPeopleDTO != null) {
					FamousPeople updateParam = new FamousPeople();
					updateParam.setPeopleId(peopleIdInt);
					updateParam.setName(name);
					if (name != null)
						updateParam.setPinying(ChineseToCharUtil
								.converterToSpell(name));
					updateParam.setEname(ename);
					updateParam.setImgPath(imgPath);
					if (imgHeight != null)
						updateParam.setImgHeight(Integer.parseInt(imgHeight));
					if (imgWidth != null)
						updateParam.setImgWidth(Integer.parseInt(imgWidth));
					updateParam.setBirthPlace(birthPlace);
					updateParam.setNationality(nationality);
					if (toTop != null) {
						updateParam.setTopDate(new Date());
					}
					if (birthDay != null) {
						try {
							updateParam.setBirthDay(new SimpleDateFormat(
									"yyyy-MM-dd").parse(birthDay));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					updateParam.setJob(job);
					updateParam.setImgPath(imgPath);
					if (status != null)
						updateParam.setStatus(Integer.parseInt(status));
					updateParam.setIntroduce(introduce);
					try {
						famousPeopleService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("�������˳ɹ�");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0)
								.setMessage("��������ʧ��:" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return "json";
				}
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}

	// �л�״̬
	@RequestMapping(value = "/status")
	public String stauts(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String peopleId = request.getParameter("peopleId");

		String status = request.getParameter("status");
		if (peopleId != null && peopleId.matches("\\d+")
				&& (status != null && status.matches("[12]"))) {
			Integer peopleIdInt = Integer.parseInt(peopleId);
			FamousPeopleDTO famousPeopleDTO = famousPeopleService
					.getById(peopleIdInt);
			if (famousPeopleDTO != null) {
				FamousPeople updateParam = new FamousPeople();
				updateParam.setPeopleId(peopleIdInt);
				updateParam.setStatus(Integer.parseInt(status));
				try {
					famousPeopleService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("�������˳ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("��������ʧ��:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}

	// �ö�
	@RequestMapping(value = "/toTop")
	public String toTop(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String peopleId = request.getParameter("peopleId");
		String toTop = request.getParameter("toTop");// �ö�
		if (peopleId != null && peopleId.matches("\\d+") && toTop != null) {
			Integer peopleIdInt = Integer.parseInt(peopleId);
			FamousPeopleDTO famousPeopleDTO = famousPeopleService
					.getById(peopleIdInt);
			if (famousPeopleDTO != null) {
				FamousPeople updateParam = new FamousPeople();
				updateParam.setPeopleId(peopleIdInt);
				updateParam.setTopDate(new Date());
				try {
					famousPeopleService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("�������˳ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("��������ʧ��:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return "json";
			}
		}
		json.setStatus(0).setMessage("�����쳣");
		model.addAttribute("json", JSONUtil.object2json(json));
		return "json";
	}

	// ɾ��
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String peopleId = request.getParameter("peopleId");
		if (peopleId != null && peopleId.matches("\\d+")) {
			Integer peopleIdInt = Integer.parseInt(peopleId);
			FamousPeopleDTO famousPeopleDTO = famousPeopleService
					.getById(peopleIdInt);
			if (famousPeopleDTO != null) {
				FamousPeople updateParam = new FamousPeople();
				updateParam.setPeopleId(peopleIdInt);
				updateParam.setStatus(0);
				try {
					famousPeopleService.saveOrUpdate(updateParam);
					json.setStatus(1).setMessage("ɾ�����˳ɹ�");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("ɾ������ʧ��:" + e.getMessage());
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
