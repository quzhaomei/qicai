package com.qicai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.qicai.bean.Artical;
import com.qicai.bean.ArticalTag;
import com.qicai.bean.FamousPeople;
import com.qicai.bean.FriendHref;
import com.qicai.bean.ImgScroll;
import com.qicai.bean.InfoScroll;
import com.qicai.bean.StoreArtical;
import com.qicai.bean.ThirdUser;
import com.qicai.bean.admin.AdminUser;
import com.qicai.bean.admin.Author;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.FamousPeopleDTO;
import com.qicai.dto.FriendHrefDTO;
import com.qicai.dto.ImgScrollDTO;
import com.qicai.dto.InfoScrollDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.MainPageArticalDTO;
import com.qicai.dto.PageDTO;
import com.qicai.dto.ThirdUserDTO;
import com.qicai.dto.ZcarMenuDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.util.CookieUtil;
import com.qicai.util.JSONUtil;
import com.qicai.util.PasswordUtil;
import com.qicai.util.RequestUtil;
import com.qicai.util.UuidUtils;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;

/**
 * ��ҳ
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/")
public class MainController extends BaseController {
	protected static final Integer TOP_NEW = 15;// "ͷ��";
	protected static final Integer GENERAL_NEW = 16;// "Ҫ��";
	protected static final Integer QUICK_NEW = 17;// "��Ѷ";
	protected static final Integer ORIGNAL_NEW = 18;// "ԭ��";

	protected static final Integer TOPIC_TAG_ID = 2;// "ר��";
	protected static final Integer BRAND_TAG_ID = 1;// "Ʒ��";

	@RequestMapping(value = "/menus")
	public String menus(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<ZcarMenuDTO> menus = zcarMenuService.getMainMenu();
		model.addAttribute("menus", menus);
		return "navigation";
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String tag=request.getParameter("tag");//PHONE PC
		
		// �������в˵�
		List<ZcarMenuDTO> menus = zcarMenuService.getMainMenu();
		model.addAttribute("menus", menus);

		// ��ѯ��һ��ͷ��
		MainPageArticalDTO top = articalService.selectedOneByParamAndTopId(
				null, TOP_NEW);
		model.addAttribute("top", top);
		// ��ѯ��һ��Ҫ��
		MainPageArticalDTO general = articalService.selectedOneByParamAndTopId(
				null, GENERAL_NEW);
		model.addAttribute("general", general);
		// ��ѯ��һ����Ѷ
		MainPageArticalDTO quick = articalService.selectedOneByParamAndTopId(
				null, QUICK_NEW);
		model.addAttribute("quick", quick);
		// ��ѯ��һ��ԭ��
		MainPageArticalDTO orignal = articalService.selectedOneByParamAndTopId(
				null, ORIGNAL_NEW);
		model.addAttribute("orignal", orignal);

		// ��������������Ѷ
		Artical param = new Artical();
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		param.setStatus(1);// ����״̬
		newParam.setPageIndex(1);
		newParam.setParam(param);

		param.setType(1);// 1����������Ѷ
		newParam.setPageSize(5);
		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// ������������ר��,����ʱ������
		PageDTO<List<ArticalDTO>> authorPage = articalService
				.getAuthorArticalByPage(newParam);// �����������µ�����
		model.addAttribute("authorPage", authorPage);

		// ���������
		param.setType(5);// 5����
		newParam.setPageSize(2);// ֻ������
		PageDTO<List<ArticalDTO>> livePage = articalService
				.getPageByParam(newParam);
		model.addAttribute("livePage", livePage);

		// ����ʮ����������
		param.setType(3);// 3���������
		newParam.setPageSize(12);// 12��
		PageDTO<List<ArticalDTO>> showPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("showPage", showPage);

		// ��������������Ƶ
		param.setType(6);// 6������Ƶ
		newParam.setPageSize(4);// 12��
		PageDTO<List<ArticalDTO>> videoPage = articalService
				.getPageByParam(newParam);
		// ��ʼ����Ƶ
		for (ArticalDTO artical : videoPage.getParam()) {
			artical.setVideo(videoService.getById(artical.getReferId()));
		}
		model.addAttribute("videoPage", videoPage);

		// ��ѯ5�����µľ���������
		param.setType(4); // 4��������
		newParam.setPageSize(5);

		List<ArticalDTO> merchantPage = articalService.getPageByParam(newParam)
				.getParam();
		model.addAttribute("merchantPage", merchantPage);

		// ����ʮ��������
		PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
		peoplePage.setPageIndex(1);
		peoplePage.setPageSize(12);// 12��
		FamousPeople people = new FamousPeople();
		people.setStatus(1);// ����������Ч��
		peoplePage.setParam(people);
		List<FamousPeopleDTO> peoples = famousPeopleService.getListByPage(
				peoplePage).getParam();
		model.addAttribute("famousPage", peoples);

		// ����ǰ8������ҳչʾ�ľ���ר��
		/*
		 * List<ArticalDTO> tagPage = articalService
		 * .selectedArticalByPageAndTagType(newParam, TOPIC_TAG_ID) .getParam();
		 */
		ArticalTag tagParam = new ArticalTag();
		tagParam.setType(TOPIC_TAG_ID);
		tagParam.setStatus(1);
		List<ArticalTagDTO> tagPage = tagService.getListByParam(tagParam);
		model.addAttribute("tagPage", tagPage);

		// ��ѯ10������������
		param.setType(null);
		newParam.setPageSize(10);
		List<ArticalDTO> hotPage = articalService
				.selectedHotArticalByPage(newParam);
		model.addAttribute("hotPage", hotPage);

		// ��ѯǰ10������Ʒ��BRAND_TAG_ID
		PageDTO<ArticalTag> pagetag = new PageDTO<ArticalTag>();
		pagetag.setPageIndex(1);
		pagetag.setPageSize(10);// 12��
		ArticalTag articalTag = new ArticalTag();
		articalTag.setStatus(1);// ����������Ч��
		articalTag.setType(BRAND_TAG_ID);
		pagetag.setParam(articalTag);
		List<ArticalTagDTO> brandDTOs = tagService.getListByPage(pagetag)
				.getParam();
		model.addAttribute("brands", brandDTOs);

		// ��ѯ������Ч����������
		FriendHref bean = new FriendHref();
		bean.setStatus(1);
		List<FriendHrefDTO> hrefs = hrefService.findListByParam(bean);
		model.addAttribute("hrefs", hrefs);

		// ��ѯǰ����banner�ֲ�
		PageDTO<ImgScroll> imgPage = new PageDTO<ImgScroll>();
		ImgScroll scroll = new ImgScroll();
		imgPage.setPageIndex(1);
		imgPage.setPageSize(3);
		scroll.setStatus(1);
		imgPage.setParam(scroll);
		List<ImgScrollDTO> scrolls = scrollService.getListByPage(imgPage);
		model.addAttribute("scrolls", scrolls);

		// ��ѯǰ3��������Ѷ
		PageDTO<InfoScroll> infoPage = new PageDTO<InfoScroll>();
		InfoScroll info = new InfoScroll();
		infoPage.setPageIndex(1);
		infoPage.setPageSize(3);
		info.setStatus(1);
		infoPage.setParam(info);
		List<InfoScrollDTO> infoScrolls = infoScrollService
				.getListByPage(infoPage);
		model.addAttribute("infoScrolls", infoScrolls);
		// ��ѯ7���ö�����
		PageDTO<Artical> pageDTO = new PageDTO<Artical>();
		pageDTO.setPageIndex(1);
		pageDTO.setPageSize(7);
		List<ArticalDTO> topScroll = articalService
				.selectedPageByParamAndTopId(pageDTO, TOP_NEW);
		model.addAttribute("topScroll", topScroll);

		if (RequestUtil.isMobileDevice(request)&&!PC_TAG.equals(tag)) {// ������ֻ�����,�Ҳ����л���PC��
			return "mobile/mobile-index";
		}

		return "index";

	}

	/**
	 * �ֻ�ҳ��
	 */
	@RequestMapping(value = "/mobileList")
	public String mobileList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// �������������� 1-��ͨ��Ѷ��2-���˶Ի���3-������,4-������,5-�,6-��Ƶ,7-����
		String type = request.getParameter("type");// ��ȡ��������
		String tagId = request.getParameter("tagId");// ��ȡ��������
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 4;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		// ����4��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		if (type != null && type.matches("\\d+"))
			param.setType(Integer.parseInt(type));
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);
		if (type != null && type.matches("\\d+")) {

			PageDTO<List<ArticalDTO>> newPage = articalService
					.getPageByParam(newParam);
			model.addAttribute("newPage", newPage);
		} else if (tagId != null && tagId.matches("\\d+")) {// ר��
			List<Integer> tagIds = new ArrayList<Integer>();
			tagIds.add(Integer.parseInt(tagId));
			PageDTO<List<ArticalDTO>> newPage = articalService
					.selectedArticalByPageAndTag(newParam, tagIds);
			ArticalTagDTO tag = tagService.getById(Integer.parseInt(tagId));
			model.addAttribute("key", tag.getTagName());
			model.addAttribute("newPage", newPage);
		} else {// ����ר��
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getAuthorArticalByPage(newParam);// �����������µ�����
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("type", type);
		return "mobile/mobile-list";
	}

	/**
	 * �ֻ�����ҳ��
	 */
	@RequestMapping(value = "/mobileSearch")
	public String mobileSearch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String key = request.getParameter("key");// ��ȡ��������
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 4;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		// ����4��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);
		PageDTO<List<ArticalDTO>> newPage = articalService.getByKey(newParam,
				key);
		model.addAttribute("newPage", newPage);
		model.addAttribute("key", key);

		return "mobile/mobile-search";
	}

	@RequestMapping(value = "/mobileMoreFamous")
	public String mobileMoreFamous(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 4;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		FamousPeople param = new FamousPeople();
		param.setStatus(1);// ����״̬
		PageDTO<FamousPeople> newParam = new PageDTO<FamousPeople>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);
		PageDTO<List<FamousPeopleDTO>> famous = famousPeopleService
				.getListByPage(newParam);
		model.addAttribute("json", JSONUtil.object2json(famous));
		return JSON;
	}

	@RequestMapping(value = "/mobileLoadMore")
	public String mobileLoadMore(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// �������������� 1-��ͨ��Ѷ��2-���˶Ի���3-������,4-������,5-�,6-��Ƶ,7-����
		String type = request.getParameter("type");// ��ȡ��������
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String key = request.getParameter("key");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 4;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		// ����4��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		if (type != null && type.matches("\\d"))
			param.setType(Integer.parseInt(type));
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);
		if (key != null) {// �������
			PageDTO<List<ArticalDTO>> newPage = articalService.getByKey(
					newParam, key);
			model.addAttribute("newPage", newPage);
		} else if (type != null && type.matches("\\d")) {

			PageDTO<List<ArticalDTO>> newPage = articalService
					.getPageByParam(newParam);
			model.addAttribute("newPage", newPage);
		} else {// ����ר��
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getAuthorArticalByPage(newParam);// �����������µ�����
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("type", type);
		return "mobile/newsMore";
	}

	/**
	 * �����ֻ���ϸҳ��
	 */
	@RequestMapping(value = "/mobilefamousDetail")
	public String mobilefamousDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String peopleId = request.getParameter("peopleId");
		if (peopleId != null && peopleId.matches("\\d+")) {
			FamousPeopleDTO people = famousPeopleService.getById(Integer
					.parseInt(peopleId));
			model.addAttribute("people", people);
			// ������������ϲ��
			Artical hot = new Artical();
			PageDTO<Artical> hotParam = new PageDTO<Artical>();
			hotParam.setParam(hot);
			hotParam.setPageIndex(1);
			hotParam.setPageSize(10);
			hot.setStatus(1);
			hot.setType(2);
			// ����10�������˵ĶԻ�
			hot.setReferId(Integer.parseInt(peopleId));
			
			List<ArticalDTO> onwerList = articalService
					.selectedHotArticalByPage(hotParam);
			model.addAttribute("onwerList", onwerList);
		}
		return "mobile/famous-detail";
	}

	/**
	 * �ֻ���ϸҳ��
	 */
	@RequestMapping(value = "/mobileDetail")
	public String mobileDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getById(Integer.parseInt(articalId));
			// �Ķ���+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5����
				// ���·�����
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				updateParam.setSeeNum(articalDTO.getSeeNum() + 1);
				try {
					articalService.saveOrUpdate(updateParam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("artical", articalDTO);
		return "mobile/mobile-detail";
	}

	// ��������
	@RequestMapping(value = "/searchArtical")
	public String searchArtical(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String key = request.getParameter("key");
		if (key == null) {
			return null;
		}
		// �������⣬��ǩ�����ԣ���Դ
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����18���������
		Artical param = new Artical();
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService.getByKey(newParam,
				key);
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setStartDate(getLastDay());// ������һ
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// ����5�������ڵĵ�����������
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// ��ʾ����������
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		model.addAttribute("key", key);
		return "news";
	}

	// ��ת��������Ѷҳ��
	@RequestMapping(value = "/toNews")
	public String toNews(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����18��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(1);// 1����������Ѷ
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(1);
		hot.setStartDate(getLastDay());// ������һ
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// ����5�������ڵĵ�����������
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// ��ʾ����������
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "news";
	}

	// ��ת������ҳ��
	@RequestMapping(value = "/toData")
	public String toData(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����18������
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(7);// 1������������
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(7);
		hot.setStartDate(getLastDay());// ������һ
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// ����5�������ڵĵ�����������
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// ��ʾ����������
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "datas";
	}

	// ��ת����Ѷ��ϸҳ��
	@RequestMapping(value = "/newDetail")
	public String newDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getById(Integer.parseInt(articalId));
			// �Ķ���+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5����
				// ���·�����
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				updateParam.setSeeNum(articalDTO.getSeeNum() + 1);
				try {
					articalService.saveOrUpdate(updateParam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			return null;
		}
		// �����������
		int type = articalDTO.getType();
		// ����10����������ͬ���ĵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(type);
		hot.setStartDate(getLastDay());// ������һ
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// ����5��������������
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// ���ݵ���������
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("nowList", nowList);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}

		AdminUserDTO user = (AdminUserDTO) request.getSession().getAttribute(
				USER_SESSION);
		// �鿴�����Ƿ��Ѿ��ղع���
		if (user != null) {
			StoreArtical storeArtical = new StoreArtical();
			storeArtical.setArticalId(articalDTO.getArticalId());
			storeArtical.setUserId(user.getAdminUserId());
			ArticalDTO store = articalService.getStoreByParam(storeArtical);
			if (store != null) {
				model.addAttribute("store", true);
			}
		}
		// ���ݱ�ǩ����𣬸��ݻ��ȶ����򣬲���6����ص�����Id
		hotParam.setPageSize(6);
		hot.setType(type);
		hot.setArticalId(articalDTO.getArticalId());
		List<ArticalDTO> nearArtical = articalService
				.getNearArticalByPage(hotParam);
		model.addAttribute("nearArtical", nearArtical);

		model.addAttribute("artical", articalDTO);
		return "newsDetail";
	}

	// support
	@RequestMapping(value = "/up")
	public synchronized String up(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		JsonDTO jsonDTO = new JsonDTO();
		if (articalId != null && articalId.matches("\\d+")) {
			int articalIdInt = Integer.parseInt(articalId);// �õ�ID
			if (CookieUtil.getCookieByName(request, "right_" + articalId) != null) {// �Ƿ�����
				jsonDTO.setStatus(0).setMessage("�Ѿ�������ˣ�");
			} else {
				ArticalDTO articalDTO = articalService.getById(articalIdInt);
				if (articalDTO != null) {
					int num = articalDTO.getRightNum();
					num++;
					Artical updateParam = new Artical();
					updateParam.setArticalId(articalIdInt);
					updateParam.setRightNum(num);
					try {
						articalService.saveOrUpdate(updateParam);
						jsonDTO.setStatus(1).setMessage("���޳ɹ���").setData(num);// ����cookie��һ��
						CookieUtil.addCookie(response, "right_" + articalId,
								"true", 3600 * 24);
					} catch (Exception e) {
						jsonDTO.setStatus(0).setMessage("����ʱϵͳ��æ�����Ժ����ԣ�");
						e.printStackTrace();
					}
				} else {
					jsonDTO.setStatus(0).setMessage("���²����ڣ�");
				}
			}
		} else {
			jsonDTO.setStatus(0).setMessage("���ݴ���");
		}
		model.addAttribute("json", JSONUtil.object2json(jsonDTO));
		return JSON;
	}

	// support
	@RequestMapping(value = "/down")
	public synchronized String down(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		JsonDTO jsonDTO = new JsonDTO();
		if (articalId != null && articalId.matches("\\d+")) {
			int articalIdInt = Integer.parseInt(articalId);// �õ�ID
			if (CookieUtil.getCookieByName(request, "wrong_" + articalId) != null) {// �Ƿ�����
				jsonDTO.setStatus(0).setMessage("�Ѿ��ȹ��ˣ�");
			} else {
				ArticalDTO articalDTO = articalService.getById(articalIdInt);
				if (articalDTO != null) {
					int num = articalDTO.getWrongNum();
					num++;
					Artical updateParam = new Artical();
					updateParam.setArticalId(articalIdInt);
					updateParam.setWrongNum(num);
					try {
						articalService.saveOrUpdate(updateParam);
						jsonDTO.setStatus(1).setMessage("�����ɹ���").setData(num);// ����cookie��һ��
						CookieUtil.addCookie(response, "wrong_" + articalId,
								"true", 3600 * 24);
					} catch (Exception e) {
						jsonDTO.setStatus(0).setMessage("����ʱϵͳ��æ�����Ժ����ԣ�");
						e.printStackTrace();
					}
				} else {
					jsonDTO.setStatus(0).setMessage("���²����ڣ�");
				}
			}
		} else {
			jsonDTO.setStatus(0).setMessage("���ݴ���");
		}
		model.addAttribute("json", JSONUtil.object2json(jsonDTO));
		return JSON;
	}

	// ��ת���Ի�������Ѷҳ��
	@RequestMapping(value = "/toTalks")
	public String toTalks(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		// ����9��������Ϣ���ö�
		PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
		peoplePage.setPageIndex(1);
		peoplePage.setPageSize(9);// 12��
		FamousPeople people = new FamousPeople();
		people.setStatus(1);// ����������Ч��
		peoplePage.setParam(people);
		List<FamousPeopleDTO> peoples = famousPeopleService.getListByPage(
				peoplePage).getParam();
		model.addAttribute("famousPage", peoples);

		// ����18��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(2);// 2����Ի�����
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		// ��ʼ������
		for (ArticalDTO artical : newPage.getParam()) {
			Integer referId = artical.getReferId();
			artical.setFamousPeople(famousPeopleService.getById(referId));
		}
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(2);// 2����Ի�����
		hot.setStartDate(getLastDay());// ������һ
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// ����5�������ڵĵ�����������
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}

		model.addAttribute("nowList", nowList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "talks";
	}

	// ��ת�������б�ҳ��
	@RequestMapping(value = "/famous")
	public String famous(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String charIndex = request.getParameter("charIndex");// ����
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 8;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
		peoplePage.setPageIndex(pageIndexInt);
		peoplePage.setPageSize(pageSizeInt);//
		FamousPeople people = new FamousPeople();
		people.setPinying(charIndex);
		people.setStatus(1);// ����������Ч��
		peoplePage.setParam(people);
		PageDTO<List<FamousPeopleDTO>> peoples = famousPeopleService
				.getListByPage(peoplePage);
		// ��ѯ����������ĸ
		List<String> charIndexs = famousPeopleService.getCharArr();//

		model.addAttribute("charIndex", charIndex);
		model.addAttribute("charIndexs", charIndexs);
		model.addAttribute("pageIndex", pageIndexInt);
		model.addAttribute("pageSize", pageSizeInt);
		model.addAttribute("famousPage", peoples);
		return "famous";
	}

	// ��ת�����������б�ҳ��
	@RequestMapping(value = "/famousSearch")
	public String famousSearch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String key = request.getParameter("key");// ����
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 8;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
		peoplePage.setPageIndex(pageIndexInt);
		peoplePage.setPageSize(pageSizeInt);//
		FamousPeople people = new FamousPeople();
		people.setName(key);
		people.setStatus(1);// ����������Ч��
		peoplePage.setParam(people);
		PageDTO<List<FamousPeopleDTO>> peoples = famousPeopleService
				.getListByPage(peoplePage);

		model.addAttribute("key", key);
		model.addAttribute("pageIndex", pageIndexInt);
		model.addAttribute("pageSize", pageSizeInt);
		model.addAttribute("famousPage", peoples);
		return "famous";
	}

	// ������ϸҳ��
	@RequestMapping(value = "/famousDetail")
	public String famousDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String operator = request.getParameter("operator");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 5;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		String peopleId = request.getParameter("peopleId");// ��ȡ����ID
		if (peopleId != null && peopleId.matches("\\d+")) {
			Integer id = Integer.parseInt(peopleId);
			FamousPeopleDTO people = famousPeopleService.getById(id);
			if (people != null) {// ajax����
				if (AJAX.equals(operator)) {
					Artical hot = new Artical();
					PageDTO<Artical> hotParam = new PageDTO<Artical>();
					hotParam.setPageIndex(pageIndexInt);
					hotParam.setPageSize(pageSizeInt);
					hotParam.setParam(hot);
					hot.setStatus(1);
					hot.setType(2);
					hot.setReferId(id);
					hotParam.setPageIndex(pageIndexInt);
					hotParam.setPageSize(pageSizeInt);
					List<ArticalDTO> onwerList = articalService
							.selectedHotArticalByPage(hotParam);
					model.addAttribute("index", pageIndexInt * pageSizeInt);
					model.addAttribute("onwerList", onwerList);
					return "famousDetailAjax";
				}
			}
			model.addAttribute("people", people);
			// ����12������
			PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
			peoplePage.setPageIndex(1);
			peoplePage.setPageSize(12);//
			FamousPeople param = new FamousPeople();
			param.setStatus(1);// ����������Ч��
			peoplePage.setParam(param);
			PageDTO<List<FamousPeopleDTO>> hotPeople = famousPeopleService
					.getListByPage(peoplePage);
			model.addAttribute("hotPeople", hotPeople);

			// ������������ϲ��
			Artical hot = new Artical();
			PageDTO<Artical> hotParam = new PageDTO<Artical>();
			hotParam.setPageIndex(1);
			hotParam.setPageSize(5);
			hotParam.setParam(hot);
			hot.setStatus(1);
			hot.setType(2);
			List<ArticalDTO> hotList = articalService
					.selectedHotArticalByPage(hotParam);
			model.addAttribute("hotList", hotList);

			// �������������˵ĶԻ�
			hot.setReferId(id);
			hotParam.setPageIndex(pageIndexInt);
			hotParam.setPageSize(pageSizeInt);
			List<ArticalDTO> onwerList = articalService
					.selectedHotArticalByPage(hotParam);
			model.addAttribute("onwerList", onwerList);
			model.addAttribute("pageSize", pageSizeInt);
			model.addAttribute("pageIndex", pageIndexInt);
		}
		return "famousDetail";
	}

	// ��ת�����������б�ҳ��
	@RequestMapping(value = "/brandSearch")
	public String brandSearch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String key = request.getParameter("key");// ����
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 8;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		PageDTO<ArticalTag> brandPage = new PageDTO<ArticalTag>();
		brandPage.setPageIndex(pageIndexInt);
		brandPage.setPageSize(pageSizeInt);//

		ArticalTag brand = new ArticalTag();
		brand.setPinying(key);
		brand.setTagName(key);// ����
		brand.setStatus(1);// ����������Ч��
		brand.setType(1);// 1����Ʒ��
		brandPage.setParam(brand);

		PageDTO<List<ArticalTagDTO>> result = tagService
				.getListByPage(brandPage);

		model.addAttribute("key", key);
		model.addAttribute("pageIndex", pageIndexInt);
		model.addAttribute("pageSize", pageSizeInt);
		model.addAttribute("brandPage", result);
		return "carBrand";
	}

	// ��ת������Ʒ���б�ҳ��
	@RequestMapping(value = "/carBrand")
	public String carBrand(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String charIndex = request.getParameter("charIndex");// ����
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 8;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		PageDTO<ArticalTag> brandPage = new PageDTO<ArticalTag>();
		brandPage.setPageIndex(pageIndexInt);
		brandPage.setPageSize(pageSizeInt);//

		ArticalTag brand = new ArticalTag();
		brand.setPinying(charIndex);
		brand.setStatus(1);// ����������Ч��
		brand.setType(1);// 1����Ʒ��
		brandPage.setParam(brand);

		PageDTO<List<ArticalTagDTO>> result = tagService
				.getListByPage(brandPage);
		// ��ѯ����������ĸ
		List<String> charIndexs = tagService.getBrandCharArr();

		model.addAttribute("charIndex", charIndex);
		model.addAttribute("charIndexs", charIndexs);
		model.addAttribute("brandPage", result);
		return "carBrand";
	}

	// ��ת��Ʒ����ϸҳ��
	@RequestMapping(value = "/carBrandDetail")
	public String carBrandDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String operator = request.getParameter("operator");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 5;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		String brandId = request.getParameter("brandId");// ��ȡbrandID
		if (brandId != null && brandId.matches("\\d+")) {
			Integer id = Integer.parseInt(brandId);
			ArticalTagDTO brandTag = tagService.getById(id);
			if (brandTag != null) {// ajax����
				if (AJAX.equals(operator)) {
					Artical hot = new Artical();
					PageDTO<Artical> hotParam = new PageDTO<Artical>();
					hotParam.setPageIndex(pageIndexInt);
					hotParam.setPageSize(pageSizeInt);
					hotParam.setParam(hot);
					hot.setStatus(1);

					// ����������Ʒ�Ƶ���Ѷ
					List<ArticalDTO> onwerList = articalService
							.selectedByParamAndTagId(hotParam, id);
					model.addAttribute("onwerList", onwerList);
					return "carBrandDetailAjax";
				}
			}
			model.addAttribute("brand", brandTag);
			// ����12��Ʒ��
			PageDTO<ArticalTag> pageParam = new PageDTO<ArticalTag>();
			pageParam.setPageIndex(1);
			pageParam.setPageSize(12);//
			ArticalTag param = new ArticalTag();
			param.setStatus(1);// ����������Ч��
			param.setType(1);
			pageParam.setParam(param);
			PageDTO<List<ArticalTagDTO>> hotBrand = tagService
					.getListByPage(pageParam);
			model.addAttribute("hotBrand", hotBrand);

			Artical hot = new Artical();
			PageDTO<Artical> hotParam = new PageDTO<Artical>();
			hotParam.setPageIndex(pageIndexInt);
			hotParam.setPageSize(pageSizeInt);
			hotParam.setParam(hot);
			hot.setStatus(1);

			if (brandId != null && brandId.matches("\\d+")) {
				// ����������Ʒ�Ƶ���Ѷ
				List<ArticalDTO> onwerList = articalService
						.selectedByParamAndTagId(hotParam,
								Integer.parseInt(brandId));
				model.addAttribute("onwerList", onwerList);
			}

			model.addAttribute("pageSize", pageSizeInt);
			model.addAttribute("pageIndex", pageIndexInt);
		}
		return "carBrandDetail";
	}

	// ��ת������ר��ҳ��
	@RequestMapping(value = "/authors")
	public String authors(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����18��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getAuthorArticalByPage(newParam);// �����������µ�����
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setStartDate(getLastDay());// ������һ
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// ����5�������ڵ���������
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// ���ݵ���������
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());

		return "authors";
	}

	// ����ר����ϸҳ��
	@RequestMapping(value = "/authorIndex")
	public String userIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String authorId = request.getParameter("authorId");
		Integer authorIdInt = Integer.parseInt(authorId);
		// ��ѯ���û���������Ϣ
		AuthorDTO author = authorService.getById(authorIdInt);
		if (author != null) {
			String pageIndex = request.getParameter("pageIndex");
			String pageSize = request.getParameter("pageSize");
			Integer pageIndexInt = 1;
			Integer pageSizeInt = 18;
			if (pageIndex != null && pageIndex.matches("\\d+")) {
				pageIndexInt = Integer.parseInt(pageIndex);
			}
			if (pageSize != null && pageSize.matches("\\d+")) {
				pageSizeInt = Integer.parseInt(pageSize);
			}
			// ��ѯ���û�����������
			Artical param = new Artical();
			param.setStatus(1);// ����״̬
			param.setAuthorId(author.getAuthorId());// ����ID

			PageDTO<Artical> newParam = new PageDTO<Artical>();
			newParam.setPageIndex(pageIndexInt);
			newParam.setParam(param);
			newParam.setPageSize(pageSizeInt);
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getPageByParam(newParam);
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("author", author);// ������Ϣ
		return "authorIndex";
	}

	// ��ת��������ҳ��
	@RequestMapping(value = "/show")
	public String show(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 5;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(3);// 3���������
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(3);
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "show";
	}

	// ��ת����������ϸ
	@RequestMapping(value = "/showDetail")
	public String showDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");

		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getById(Integer.parseInt(articalId));
			// �Ķ���+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5����
				// ���·�����
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				updateParam.setSeeNum(articalDTO.getSeeNum() + 1);
				try {
					articalService.saveOrUpdate(updateParam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("artical", articalDTO);
		// ����4������ϲ��
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(3);// 3���������
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(1);
		newParam.setPageSize(4);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		Artical hot = new Artical();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(6);
		hotParam.setParam(hot);
		hot.setSeeNum(1);
		hot.setStatus(1);
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "showDetail";
	}

	// ��ת��Ͷ��ҳ��
	@RequestMapping(value = "/contribute")
	public String contribute(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "contribute";
	}

	// ��ת���ҳ��
	@RequestMapping(value = "/activity")
	public String activity(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String operator = request.getParameter("operator");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 5;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����5��������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(5);// 5����
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		if (AJAX.equals(operator)) {
			JsonDTO ajaxDate = new JsonDTO();
			ajaxDate.setStatus(1).setData(newPage);
			model.addAttribute("json", JSONUtil.object2json(ajaxDate));
			return JSON;
		} else {
			model.addAttribute("newPage", newPage);
			model.addAttribute("pageSize", newParam.getPageSize());
			model.addAttribute("pageIndex", newParam.getPageIndex());
			return "activity";
		}
	}

	// ��ת��ר����ϸҳ��,���ݱ�ǩ����
	@RequestMapping(value = "/topicDetail")
	public String topicDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String tagId = request.getParameter("tagId");// ��ǩ
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		List<Integer> tagIds = new ArrayList<Integer>();
		tagIds.add(Integer.parseInt(tagId));

		// ����18������ר����Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		if (tagId != null && tagId.matches("\\d+")) {
			ArticalTagDTO tags = tagService.getById(Integer.parseInt(tagId));
			List<Integer> list = new ArrayList<Integer>();
			list.add(tags.getTagId());
			model.addAttribute("tagName", tags.getTagName());
			param.setTagId(list);
		}
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);
		PageDTO<List<ArticalDTO>> newPage = articalService
				.selectedArticalByPageAndTag(newParam, tagIds);

		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setSeeNum(1);// ���ҵ������ߵ�

		List<ArticalDTO> hotList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		model.addAttribute("hotList", hotList);

		// ����5�������ڵ���������
		hotParam.setPageSize(5);
		hot.setSeeNum(null);
		hot.setStartDate(null);
		hot.setRightNum(1);
		// ���ݵ���������
		List<ArticalDTO> nowList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}

		model.addAttribute("nowList", nowList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "news";
	}

	// ��ת��ר��ҳ��,���ݱ�ǩ����
	@RequestMapping(value = "/topics")
	public String topics(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		List<Integer> tagIds = new ArrayList<Integer>();
		// ����ר���ǩ
		ArticalTag showTag = new ArticalTag();
		showTag.setType(TOPIC_TAG_ID);// ר���ǩ
		PageDTO<ArticalTag> tagParam = new PageDTO<ArticalTag>();
		tagParam.setPageIndex(pageIndexInt);
		tagParam.setPageSize(pageSizeInt);
		tagParam.setParam(showTag);

		PageDTO<List<ArticalTagDTO>> tags = tagService.getListByPage(tagParam);

		for (ArticalTagDTO tag : tags.getParam()) {
			tagIds.add(tag.getTagId());
		}
		model.addAttribute("tags", tags);

		// ����10����һ���ڵ���������
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setSeeNum(1);// ���ҵ������ߵ�

		List<ArticalDTO> hotList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		model.addAttribute("hotList", hotList);

		// ����5�������ڵ���������
		hotParam.setPageSize(5);
		hot.setSeeNum(null);
		hot.setStartDate(null);
		hot.setRightNum(1);// ���ݵ���������
		List<ArticalDTO> nowList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}

		model.addAttribute("nowList", nowList);
		return "topics";
	}

	// ��ת��������ҳ��,type=4
	@RequestMapping(value = "/merchant")
	public String merchant(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 18;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����18�����¾�������Ѷ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(4);// 4��������
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		// ����
		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);

		model.addAttribute("newPage", newPage);

		// ����10����һ���ڵ���������
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(param);
		param.setStartDate(getLastDay());
		param.setType(4);
		param.setSeeNum(1);// ���ҵ������ߵ�

		List<ArticalDTO> hotList = articalService.getPageByParam(hotParam)
				.getParam();
		model.addAttribute("hotList", hotList);

		// ����5�������ڵ���������
		hotParam.setPageSize(5);
		param.setSeeNum(null);
		param.setStartDate(null);
		param.setRightNum(1);// ���ݵ���������
		List<ArticalDTO> nowList = articalService.getPageByParam(newParam)
				.getParam();
		if (nowList == null || nowList.size() == 0) {
			param.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		model.addAttribute("pageSize", newParam.getPageSize());
		model.addAttribute("pageIndex", newParam.getPageIndex());
		return "merchant";
	}

	// ��Ƶҳ�� type=6
	@RequestMapping(value = "/video")
	public String video(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Integer pageIndexInt = 1;
		Integer pageSizeInt = 100;
		if (pageIndex != null && pageIndex.matches("\\d+")) {
			pageIndexInt = Integer.parseInt(pageIndex);
		}
		if (pageSize != null && pageSize.matches("\\d+")) {
			pageSizeInt = Integer.parseInt(pageSize);
		}

		// ����18��ԭ����Ƶ
		Artical param = new Artical();
		param.setStatus(1);// ����״̬
		param.setType(4);// 4��������
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		// ��100��ԭ����Ƶ
		List<ArticalDTO> ownVideo = articalService
				.getVideoArticalByPageAndType(newParam, 0);
		model.addAttribute("ownVideo", ownVideo);
		// ��100���Լ���Ƶ
		List<ArticalDTO> testVideo = articalService
				.getVideoArticalByPageAndType(newParam, 1);
		model.addAttribute("testVideo", testVideo);

		// ����10����һ���ڵ���������
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(param);
		param.setStartDate(getLastDay());
		param.setType(null);
		param.setSeeNum(1);// ���ҵ������ߵ�

		List<ArticalDTO> hotList = articalService.getPageByParam(hotParam)
				.getParam();
		model.addAttribute("hotList", hotList);

		return "video";
	}

	// ��Ƶ��ϸҳ�� type=6
	@RequestMapping(value = "/videoDetail")
	public String videoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getVideoArticalById(Integer
					.parseInt(articalId));
			// �Ķ���+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5����
				// ���·�����
				Artical updateParam = new Artical();
				updateParam.setArticalId(articalDTO.getArticalId());
				updateParam.setSeeNum(articalDTO.getSeeNum() + 1);
				try {
					articalService.saveOrUpdate(updateParam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("artical", articalDTO);

		// ����10����һ���ڵ���������
		Artical param = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(param);
		param.setStartDate(getLastDay());
		param.setType(null);
		param.setSeeNum(1);// ���ҵ������ߵ�

		List<ArticalDTO> hotList = articalService.getPageByParam(hotParam)
				.getParam();
		model.addAttribute("hotList", hotList);

		// ����5���Ƽ��ĵ���������
		hotParam.setPageSize(5);
		param.setSeeNum(null);
		param.setStartDate(null);
		param.setRightNum(1);// ���ݵ���������
		List<ArticalDTO> nowList = articalService.getPageByParam(hotParam)
				.getParam();
		if (nowList == null || nowList.size() == 0) {
			param.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		return "videoDetail";
	}

	// QQ��֤
	private com.qq.connect.oauth.Oauth qqOauth = new com.qq.connect.oauth.Oauth();

	// QQ��½
	@RequestMapping(value = "/qqLogin")
	public String qqLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String code=request.getParameter("code");
		try {
			String qqToken = null;
			String openId = null;
			AccessToken token = qqOauth.getAccessTokenByRequest(request);
			if (token.getAccessToken().equals("")) {
				// ��Ȩʧ��
				System.out.print("û�л�ȡ����Ӧ����" + token);
			} else {
				qqToken = token.getAccessToken();// ��ȡtoken
				OpenID openIDObj = new OpenID(qqToken);
				openId = openIDObj.getUserOpenID();
				// ����openIdȥ���Ҷ���
				ThirdUserDTO thirdUser = thirdService.getByOpenId(openId);

				if (thirdUser != null) {// ����û����ڡ���ƥ����û����� ��½�ɹ�
					AdminUserDTO user = adminUserService.getById(thirdUser
							.getUserId());
					if (user != null) {
						request.getSession().setAttribute(USER_SESSION, user);
					}
				} else {// ����û�������
					UserInfo qzoneUserInfo = new UserInfo(qqToken, openId);
					UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
					String photo = null;
					String nickname = null;
					if (userInfoBean.getRet() == 0) {
						photo = userInfoBean.getAvatar().getAvatarURL100();// ͷ��
						nickname = userInfoBean.getNickname();// ����
					}
					AdminUser saveParam = new AdminUser();
					saveParam.setLoginname(UuidUtils.getObjectUUID("qq"));
					saveParam.setNickname(nickname);
					saveParam.setOrinal(0);//
					saveParam.setEmail(UuidUtils.getObjectUUID("qq"));
					saveParam.setCreateDate(new Date());
					saveParam.setStatus(1);// 1-����
					saveParam.setPassword(nickname);
					saveParam.setType(0);// �Ż��û�
					saveParam.setDescription("QQ��������½");
					adminUserService.saveOrUpdate(saveParam);
					// �����û�ͷ��
					Author author = new Author();
					author.setPenName(nickname);
					author.setUserId(saveParam.getAdminUserId());
					author.setCreateDate(new Date());
					author.setPhotoPath(photo);
					author.setStatus(1);
					authorService.saveOrUpdate(author);
					// �������������
					ThirdUser saveThird = new ThirdUser();
					saveThird.setCreateDate(new Date());
					saveThird.setUserId(saveParam.getAdminUserId());
					saveThird.setName(nickname);
					saveThird.setOpenId(openId);
					saveThird.setPhoto(photo);
					thirdService.save(saveThird);

					AdminUserDTO user = adminUserService.getById(saveParam
							.getAdminUserId());
					request.getSession().setAttribute(USER_SESSION, user);
				}
				return "thirdLoginSuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// sina��֤
	private weibo4j.Oauth oauth_sina = new weibo4j.Oauth();

	// ���˵�½
	@RequestMapping(value = "/sinaLogin")
	public String sinaLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String code = request.getParameter("code");
		try {
			String uid = null;
			weibo4j.http.AccessToken token = oauth_sina
					.getAccessTokenByCode(code);
			uid = token.getUid();// ��ȡuid
			// ����uiIdȥ���Ҷ���
			ThirdUserDTO thirdUser = thirdService.getByOpenId(uid);
			if (thirdUser != null) {// ����û����ڡ���ƥ����û����� ��½�ɹ�
				AdminUserDTO user = adminUserService.getById(thirdUser
						.getUserId());
				if (user != null) {
					request.getSession().setAttribute(USER_SESSION, user);
				}
			} else {// ����û�������
				Users um = new Users(token.getAccessToken());
				User user_sina = um.showUserById(uid);
				String photo = null;
				String nickname = null;
				photo = user_sina.getAvatarLarge();// ͷ��
				nickname = user_sina.getScreenName();// ����
				AdminUser saveParam = new AdminUser();
				saveParam.setLoginname(UuidUtils.getObjectUUID("qq"));
				saveParam.setNickname(nickname);
				saveParam.setOrinal(0);//
				saveParam.setEmail(UuidUtils.getObjectUUID("qq"));
				saveParam.setCreateDate(new Date());
				saveParam.setStatus(1);// 1-����
				saveParam.setPassword(nickname);
				saveParam.setType(0);// �Ż��û�
				saveParam.setDescription("sina��������½");
				adminUserService.saveOrUpdate(saveParam);
				// �����û�ͷ��
				Author author = new Author();
				author.setPenName(nickname);
				author.setIntroduce(user_sina.getDescription());
				author.setSinaPath(user_sina.getUrl());
				author.setUserId(saveParam.getAdminUserId());
				author.setCreateDate(new Date());
				author.setPhotoPath(photo);
				author.setStatus(1);
				authorService.saveOrUpdate(author);
				// �������������
				ThirdUser saveThird = new ThirdUser();
				saveThird.setCreateDate(new Date());
				saveThird.setUserId(saveParam.getAdminUserId());
				saveThird.setName(nickname);
				saveThird.setOpenId(uid);
				saveThird.setPhoto(photo);
				thirdService.save(saveThird);

				AdminUserDTO user = adminUserService.getById(saveParam
						.getAdminUserId());
				request.getSession().setAttribute(USER_SESSION, user);
			}
			return "thirdLoginSuccess";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ��½ҳ��
	@RequestMapping(value = "/toLogin")
	public String toLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String qqUri = null;
		try {
			qqUri = (qqOauth.getAuthorizeURL(request));
			model.addAttribute("qqUri", qqUri);
		} catch (QQConnectException e1) {
			e1.printStackTrace();
		}
		String sinaUri = null;
		try {
			sinaUri = oauth_sina.authorize("code");
			model.addAttribute("sinaUri", sinaUri);
		} catch (WeiboException e1) {
			e1.printStackTrace();
		}
		String password = request.getParameter("password");
		String loginname = request.getParameter("loginname");
		String orinal = request.getParameter("orinal");// 0-���ˣ�1-��ҵ
		String loginTag = request.getParameter("loginTag");// ��ס��½״̬��

		model.addAttribute("loginname", loginname);
		// ��¼����֤
		if (loginname == null || loginname.equals("")) {
			model.addAttribute("loginname_info", "��¼������Ϊ��");
		} else if (loginname.length() < 6) {
			model.addAttribute("loginname_info", "��¼��̫��");
		} else if (loginname.length() > 30) {
			model.addAttribute("loginname_info", "��¼������̫��");
		}
		// �����ʽ��֤
		if (password == null || password.equals("")) {
			model.addAttribute("password_info", "���벻��Ϊ��");
		} else if (password.length() < 6) {
			model.addAttribute("password_info", "���볤��̫��");
		} else if (password.length() > 50) {
			model.addAttribute("password_info", "���볤��̫��");
		}
		int orinalInt = 0;
		if (orinal != null && orinal.matches("\\d{1}")) {
			orinalInt = Integer.parseInt(orinal);
		}
		model.addAttribute("orinal", orinal);// ������Դ

		AdminUser adminUser = new AdminUser();
		adminUser.setLoginname(loginname);
		adminUser.setOrinal(orinalInt);
		AdminUserDTO user = adminUserService.mengHuLogin(adminUser);
		if (user == null) {
			if (orinalInt == 0) {
				model.addAttribute("loginname_info", "�û���������");
			} else {
				model.addAttribute("loginname_info", "����ҵ�Ų�����");
			}
		} else if (!PasswordUtil.MD5(password).equalsIgnoreCase(
				user.getPassword())) {
			model.addAttribute("password_info", "�������");
		} else if (user.getStatus() == 2) {
			model.addAttribute("loginname_info", "���˺ű�����");
		} else {// ��½�ɹ�
			request.getSession().setAttribute(USER_SESSION, user);// ����session
			// ����б����¼״̬
			if (loginTag != null) {
				CookieUtil.addCookie(response, LOGIN_TAG, user.getAdminUserId()
						+ "", 60 * 60 * 24 * 7);// ����һ������
			}
			try {
				response.sendRedirect("index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return "login";
	}

	// �ǳ�ϵͳ
	@RequestMapping(value = "/out")
	public String loginout(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		AdminUserDTO user = (AdminUserDTO) session.getAttribute(USER_SESSION);
		if (user != null) {
			session.removeAttribute(USER_SESSION);
			session.invalidate();// �ݻ�session;
			CookieUtil.removeCookie(response, LOGIN_TAG);// �Ƴ�cookie
		}
		JsonDTO json = new JsonDTO();
		json.setStatus(1);
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// ��תע��ҳ��
	@RequestMapping(value = "/toRegister")
	public String toRegister(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String qqUri = null;
		try {
			qqUri = (qqOauth.getAuthorizeURL(request));
			model.addAttribute("qqUri", qqUri);
		} catch (QQConnectException e1) {
			e1.printStackTrace();
		}
		String sinaUri;
		try {
			sinaUri = oauth_sina.authorize("code");
			model.addAttribute("sinaUri", sinaUri);
		} catch (WeiboException e1) {
			e1.printStackTrace();
		}
		return "register";
	}

	// ������֤
	@RequestMapping(value = "/valid")
	public void valid(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String email = request.getParameter("email");// ��֤����
		String phone = request.getParameter("phone");// ��֤�ֻ�
		String loginname = request.getParameter("loginname");// ��֤�ǳ�
		AdminUser param = new AdminUser();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if (email != null) {
				param.setEmail(email);
			} else if (phone != null) {
				param.setPhone(phone);
			} else if (loginname != null) {
				param.setLoginname(loginname);
			}
			int count = adminUserService.checkUserCount(param);
			if (count == 0) {
				pw.write("true");
			} else {
				pw.write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	// ע��
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String nickname = request.getParameter("nickname");// 30
		String loginname = request.getParameter("loginname");// 30��¼��
		String position = request.getParameter("position");// 50λ��
		String orinal = request.getParameter("orinal");// 0-���ˣ�1-��ҵ
		String email = request.getParameter("email");// 50
		String phone = request.getParameter("phone");// 11
		String password = request.getParameter("password");// 6-50
		String registerCode = request.getParameter("registerCode");// 6
		String registerCode_1 = request.getParameter("registerCode_1");// 6
		JsonDTO json = new JsonDTO();
		if ((nickname == null || nickname.length() <= 30)// ��֤�ǳ�
				&& (loginname != null && loginname.length() <= 30)
				&& (position == null || position.length() <= 50)
				&& (orinal != null && orinal.matches("\\d{1}"))
				&& (email != null && email.length() <= 50)
//				&& (phone == null || phone.matches("\\d{11}"))
				&& (password != null && password.matches(".{6,50}"))
				&& (registerCode != null || registerCode_1 != null)) {
			int orinalInt = Integer.parseInt(orinal);
			if ((orinalInt == 0 && registerCode
					.equalsIgnoreCase((String) request.getSession()
							.getAttribute("registerCode")))
					|| (orinalInt == 1 && registerCode_1
							.equalsIgnoreCase((String) request.getSession()
									.getAttribute("registerCode_1")))) {
				// �����֤��
				if (orinalInt == 0) {
					request.getSession().removeAttribute("registerCode");
				} else if (orinalInt == 1) {
					request.getSession().removeAttribute("registerCode_1");
				}
				AdminUser saveParam = new AdminUser();
				saveParam.setPhone(phone);
				saveParam.setLoginname(loginname);
				if (nickname != null) {
					saveParam.setNickname(nickname);
				} else {
					saveParam.setNickname(loginname);
				}
				saveParam.setOrinal(orinalInt);// 0���� 1-��ҵ
				saveParam.setPosition(position);// ��ҵλ��
				saveParam.setEmail(email);
				saveParam.setCreateDate(new Date());
				saveParam.setStatus(1);// 1-����
				saveParam.setPassword(PasswordUtil.MD5(password));
				saveParam.setType(0);// �Ż��û�
				saveParam.setDescription("�Ż���վ�Խ�");
				try {
					adminUserService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("�����û��ɹ�");
				} catch (Exception e) {
					json.setStatus(0).setMessage("�����û�ʧ�ܣ�" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				json.setStatus(0).setMessage("��֤�����");
			}
		} else {
			json.setStatus(0).setMessage("���ݴ���");
		}
		model.addAttribute("json", json);
		return "registerResult";
	}

	// �һ�����
	@RequestMapping(value = "/findps")
	public String fingps(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "findps";
	}

	// ��������
	@RequestMapping(value = "/introduce")
	public String introduce(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "introduce";
	}

	// ����ͬһʱ��
	@SuppressWarnings("deprecation")
	private Date getLastDay() {
		Date temp = new Date();
		temp.setDate(temp.getDate() - 7);
		return temp;
	}

}
