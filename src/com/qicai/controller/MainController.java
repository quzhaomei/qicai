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
 * 首页
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/")
public class MainController extends BaseController {
	protected static final Integer TOP_NEW = 15;// "头条";
	protected static final Integer GENERAL_NEW = 16;// "要闻";
	protected static final Integer QUICK_NEW = 17;// "快讯";
	protected static final Integer ORIGNAL_NEW = 18;// "原创";

	protected static final Integer TOPIC_TAG_ID = 2;// "专题";
	protected static final Integer BRAND_TAG_ID = 1;// "品牌";

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
		
		// 查找所有菜单
		List<ZcarMenuDTO> menus = zcarMenuService.getMainMenu();
		model.addAttribute("menus", menus);

		// 查询第一条头条
		MainPageArticalDTO top = articalService.selectedOneByParamAndTopId(
				null, TOP_NEW);
		model.addAttribute("top", top);
		// 查询第一条要闻
		MainPageArticalDTO general = articalService.selectedOneByParamAndTopId(
				null, GENERAL_NEW);
		model.addAttribute("general", general);
		// 查询第一条快讯
		MainPageArticalDTO quick = articalService.selectedOneByParamAndTopId(
				null, QUICK_NEW);
		model.addAttribute("quick", quick);
		// 查询第一条原创
		MainPageArticalDTO orignal = articalService.selectedOneByParamAndTopId(
				null, ORIGNAL_NEW);
		model.addAttribute("orignal", orignal);

		// 查找五条最新资讯
		Artical param = new Artical();
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		param.setStatus(1);// 正常状态
		newParam.setPageIndex(1);
		newParam.setParam(param);

		param.setType(1);// 1代表最新资讯
		newParam.setPageSize(5);
		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// 查找五条作者专栏,根据时间排序
		PageDTO<List<ArticalDTO>> authorPage = articalService
				.getAuthorArticalByPage(newParam);// 查找作者最新的文章
		model.addAttribute("authorPage", authorPage);

		// 查找两条活动
		param.setType(5);// 5代表活动
		newParam.setPageSize(2);// 只查两条
		PageDTO<List<ArticalDTO>> livePage = articalService
				.getPageByParam(newParam);
		model.addAttribute("livePage", livePage);

		// 查找十二条封面秀
		param.setType(3);// 3代表封面秀
		newParam.setPageSize(12);// 12条
		PageDTO<List<ArticalDTO>> showPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("showPage", showPage);

		// 查找四条精彩视频
		param.setType(6);// 6代表视频
		newParam.setPageSize(4);// 12条
		PageDTO<List<ArticalDTO>> videoPage = articalService
				.getPageByParam(newParam);
		// 初始化视频
		for (ArticalDTO artical : videoPage.getParam()) {
			artical.setVideo(videoService.getById(artical.getReferId()));
		}
		model.addAttribute("videoPage", videoPage);

		// 查询5条最新的经销商文章
		param.setType(4); // 4代表经销商
		newParam.setPageSize(5);

		List<ArticalDTO> merchantPage = articalService.getPageByParam(newParam)
				.getParam();
		model.addAttribute("merchantPage", merchantPage);

		// 查找十二条名人
		PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
		peoplePage.setPageIndex(1);
		peoplePage.setPageSize(12);// 12条
		FamousPeople people = new FamousPeople();
		people.setStatus(1);// 查找所有有效的
		peoplePage.setParam(people);
		List<FamousPeopleDTO> peoples = famousPeopleService.getListByPage(
				peoplePage).getParam();
		model.addAttribute("famousPage", peoples);

		// 查找前8条在首页展示的精彩专题
		/*
		 * List<ArticalDTO> tagPage = articalService
		 * .selectedArticalByPageAndTagType(newParam, TOPIC_TAG_ID) .getParam();
		 */
		ArticalTag tagParam = new ArticalTag();
		tagParam.setType(TOPIC_TAG_ID);
		tagParam.setStatus(1);
		List<ArticalTagDTO> tagPage = tagService.getListByParam(tagParam);
		model.addAttribute("tagPage", tagPage);

		// 查询10条最热门文章
		param.setType(null);
		newParam.setPageSize(10);
		List<ArticalDTO> hotPage = articalService
				.selectedHotArticalByPage(newParam);
		model.addAttribute("hotPage", hotPage);

		// 查询前10的汽车品牌BRAND_TAG_ID
		PageDTO<ArticalTag> pagetag = new PageDTO<ArticalTag>();
		pagetag.setPageIndex(1);
		pagetag.setPageSize(10);// 12条
		ArticalTag articalTag = new ArticalTag();
		articalTag.setStatus(1);// 查找所有有效的
		articalTag.setType(BRAND_TAG_ID);
		pagetag.setParam(articalTag);
		List<ArticalTagDTO> brandDTOs = tagService.getListByPage(pagetag)
				.getParam();
		model.addAttribute("brands", brandDTOs);

		// 查询所有有效的友情链接
		FriendHref bean = new FriendHref();
		bean.setStatus(1);
		List<FriendHrefDTO> hrefs = hrefService.findListByParam(bean);
		model.addAttribute("hrefs", hrefs);

		// 查询前三条banner轮播
		PageDTO<ImgScroll> imgPage = new PageDTO<ImgScroll>();
		ImgScroll scroll = new ImgScroll();
		imgPage.setPageIndex(1);
		imgPage.setPageSize(3);
		scroll.setStatus(1);
		imgPage.setParam(scroll);
		List<ImgScrollDTO> scrolls = scrollService.getListByPage(imgPage);
		model.addAttribute("scrolls", scrolls);

		// 查询前3条滚动资讯
		PageDTO<InfoScroll> infoPage = new PageDTO<InfoScroll>();
		InfoScroll info = new InfoScroll();
		infoPage.setPageIndex(1);
		infoPage.setPageSize(3);
		info.setStatus(1);
		infoPage.setParam(info);
		List<InfoScrollDTO> infoScrolls = infoScrollService
				.getListByPage(infoPage);
		model.addAttribute("infoScrolls", infoScrolls);
		// 查询7条置顶滚动
		PageDTO<Artical> pageDTO = new PageDTO<Artical>();
		pageDTO.setPageIndex(1);
		pageDTO.setPageSize(7);
		List<ArticalDTO> topScroll = articalService
				.selectedPageByParamAndTopId(pageDTO, TOP_NEW);
		model.addAttribute("topScroll", topScroll);

		if (RequestUtil.isMobileDevice(request)&&!PC_TAG.equals(tag)) {// 如果是手机请求,且不是切换至PC的
			return "mobile/mobile-index";
		}

		return "index";

	}

	/**
	 * 手机页面
	 */
	@RequestMapping(value = "/mobileList")
	public String mobileList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 文章所属的类型 1-普通资讯，2-名人对话，3-封面秀,4-经销商,5-活动,6-视频,7-数据
		String type = request.getParameter("type");// 获取文章类型
		String tagId = request.getParameter("tagId");// 获取文章类型
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
		// 查找4条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
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
		} else if (tagId != null && tagId.matches("\\d+")) {// 专题
			List<Integer> tagIds = new ArrayList<Integer>();
			tagIds.add(Integer.parseInt(tagId));
			PageDTO<List<ArticalDTO>> newPage = articalService
					.selectedArticalByPageAndTag(newParam, tagIds);
			ArticalTagDTO tag = tagService.getById(Integer.parseInt(tagId));
			model.addAttribute("key", tag.getTagName());
			model.addAttribute("newPage", newPage);
		} else {// 作者专栏
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getAuthorArticalByPage(newParam);// 查找作者最新的文章
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("type", type);
		return "mobile/mobile-list";
	}

	/**
	 * 手机搜索页面
	 */
	@RequestMapping(value = "/mobileSearch")
	public String mobileSearch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String key = request.getParameter("key");// 获取文章类型
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
		// 查找4条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
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
		param.setStatus(1);// 正常状态
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
		// 文章所属的类型 1-普通资讯，2-名人对话，3-封面秀,4-经销商,5-活动,6-视频,7-数据
		String type = request.getParameter("type");// 获取文章类型
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
		// 查找4条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		if (type != null && type.matches("\\d"))
			param.setType(Integer.parseInt(type));
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);
		if (key != null) {// 搜索结果
			PageDTO<List<ArticalDTO>> newPage = articalService.getByKey(
					newParam, key);
			model.addAttribute("newPage", newPage);
		} else if (type != null && type.matches("\\d")) {

			PageDTO<List<ArticalDTO>> newPage = articalService
					.getPageByParam(newParam);
			model.addAttribute("newPage", newPage);
		} else {// 作者专栏
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getAuthorArticalByPage(newParam);// 查找作者最新的文章
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("type", type);
		return "mobile/newsMore";
	}

	/**
	 * 名人手机详细页面
	 */
	@RequestMapping(value = "/mobilefamousDetail")
	public String mobilefamousDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String peopleId = request.getParameter("peopleId");
		if (peopleId != null && peopleId.matches("\\d+")) {
			FamousPeopleDTO people = famousPeopleService.getById(Integer
					.parseInt(peopleId));
			model.addAttribute("people", people);
			// 查找五条猜你喜欢
			Artical hot = new Artical();
			PageDTO<Artical> hotParam = new PageDTO<Artical>();
			hotParam.setParam(hot);
			hotParam.setPageIndex(1);
			hotParam.setPageSize(10);
			hot.setStatus(1);
			hot.setType(2);
			// 查找10条该名人的对话
			hot.setReferId(Integer.parseInt(peopleId));
			
			List<ArticalDTO> onwerList = articalService
					.selectedHotArticalByPage(hotParam);
			model.addAttribute("onwerList", onwerList);
		}
		return "mobile/famous-detail";
	}

	/**
	 * 手机详细页面
	 */
	@RequestMapping(value = "/mobileDetail")
	public String mobileDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getById(Integer.parseInt(articalId));
			// 阅读量+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5分钟
				// 更新访问量
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

	// 搜索文章
	@RequestMapping(value = "/searchArtical")
	public String searchArtical(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String key = request.getParameter("key");
		if (key == null) {
			return null;
		}
		// 搜索标题，标签，引言，来源
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

		// 查找18条搜索结果
		Artical param = new Artical();
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService.getByKey(newParam,
				key);
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setStartDate(getLastDay());// 上星期一
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的点赞最多的五条
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// 表示按点赞排序
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

	// 跳转到最新资讯页面
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

		// 查找18条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(1);// 1代表最新资讯
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(1);
		hot.setStartDate(getLastDay());// 上星期一
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的点赞最多的五条
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// 表示按点赞排序
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

	// 跳转到数据页面
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

		// 查找18条数据
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(7);// 1代表最新数据
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(7);
		hot.setStartDate(getLastDay());// 上星期一
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的点赞最多的五条
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// 表示按点赞排序
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

	// 跳转到资讯详细页面
	@RequestMapping(value = "/newDetail")
	public String newDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getById(Integer.parseInt(articalId));
			// 阅读量+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5分钟
				// 更新访问量
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
		// 查找文章类别
		int type = articalDTO.getType();
		// 查找10条，上星期同类别的的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(type);
		hot.setStartDate(getLastDay());// 上星期一
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// 查找5条点赞最多的文章
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// 根据点赞来排序
		List<ArticalDTO> nowList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("nowList", nowList);
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}

		AdminUserDTO user = (AdminUserDTO) request.getSession().getAttribute(
				USER_SESSION);
		// 查看文章是否已经收藏过。
		if (user != null) {
			StoreArtical storeArtical = new StoreArtical();
			storeArtical.setArticalId(articalDTO.getArticalId());
			storeArtical.setUserId(user.getAdminUserId());
			ArticalDTO store = articalService.getStoreByParam(storeArtical);
			if (store != null) {
				model.addAttribute("store", true);
			}
		}
		// 根据标签和类别，根据火热度排序，查找6条相关的文章Id
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
			int articalIdInt = Integer.parseInt(articalId);// 得到ID
			if (CookieUtil.getCookieByName(request, "right_" + articalId) != null) {// 是否点过赞
				jsonDTO.setStatus(0).setMessage("已经点过赞了！");
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
						jsonDTO.setStatus(1).setMessage("点赞成功！").setData(num);// 加入cookie，一天
						CookieUtil.addCookie(response, "right_" + articalId,
								"true", 3600 * 24);
					} catch (Exception e) {
						jsonDTO.setStatus(0).setMessage("点赞时系统繁忙，请稍后再试！");
						e.printStackTrace();
					}
				} else {
					jsonDTO.setStatus(0).setMessage("文章不存在！");
				}
			}
		} else {
			jsonDTO.setStatus(0).setMessage("数据错误");
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
			int articalIdInt = Integer.parseInt(articalId);// 得到ID
			if (CookieUtil.getCookieByName(request, "wrong_" + articalId) != null) {// 是否点过赞
				jsonDTO.setStatus(0).setMessage("已经踩过了！");
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
						jsonDTO.setStatus(1).setMessage("操作成功！").setData(num);// 加入cookie，一天
						CookieUtil.addCookie(response, "wrong_" + articalId,
								"true", 3600 * 24);
					} catch (Exception e) {
						jsonDTO.setStatus(0).setMessage("操作时系统繁忙，请稍后再试！");
						e.printStackTrace();
					}
				} else {
					jsonDTO.setStatus(0).setMessage("文章不存在！");
				}
			}
		} else {
			jsonDTO.setStatus(0).setMessage("数据错误");
		}
		model.addAttribute("json", JSONUtil.object2json(jsonDTO));
		return JSON;
	}

	// 跳转到对话名人资讯页面
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
		// 查找9个名人信息按置顶
		PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
		peoplePage.setPageIndex(1);
		peoplePage.setPageSize(9);// 12条
		FamousPeople people = new FamousPeople();
		people.setStatus(1);// 查找所有有效的
		peoplePage.setParam(people);
		List<FamousPeopleDTO> peoples = famousPeopleService.getListByPage(
				peoplePage).getParam();
		model.addAttribute("famousPage", peoples);

		// 查找18条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(2);// 2代表对话名人
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		// 初始化名人
		for (ArticalDTO artical : newPage.getParam()) {
			Integer referId = artical.getReferId();
			artical.setFamousPeople(famousPeopleService.getById(referId));
		}
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setType(2);// 2代表对话名人
		hot.setStartDate(getLastDay());// 上星期一
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的点赞最多的五条
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

	// 跳转到名人列表页面
	@RequestMapping(value = "/famous")
	public String famous(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String charIndex = request.getParameter("charIndex");// 检索
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
		people.setStatus(1);// 查找所有有效的
		peoplePage.setParam(people);
		PageDTO<List<FamousPeopleDTO>> peoples = famousPeopleService
				.getListByPage(peoplePage);
		// 查询检索的首字母
		List<String> charIndexs = famousPeopleService.getCharArr();//

		model.addAttribute("charIndex", charIndex);
		model.addAttribute("charIndexs", charIndexs);
		model.addAttribute("pageIndex", pageIndexInt);
		model.addAttribute("pageSize", pageSizeInt);
		model.addAttribute("famousPage", peoples);
		return "famous";
	}

	// 跳转到名人搜索列表页面
	@RequestMapping(value = "/famousSearch")
	public String famousSearch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String key = request.getParameter("key");// 检索
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
		people.setStatus(1);// 查找所有有效的
		peoplePage.setParam(people);
		PageDTO<List<FamousPeopleDTO>> peoples = famousPeopleService
				.getListByPage(peoplePage);

		model.addAttribute("key", key);
		model.addAttribute("pageIndex", pageIndexInt);
		model.addAttribute("pageSize", pageSizeInt);
		model.addAttribute("famousPage", peoples);
		return "famous";
	}

	// 名人详细页面
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
		String peopleId = request.getParameter("peopleId");// 获取名人ID
		if (peopleId != null && peopleId.matches("\\d+")) {
			Integer id = Integer.parseInt(peopleId);
			FamousPeopleDTO people = famousPeopleService.getById(id);
			if (people != null) {// ajax加载
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
			// 查找12个名人
			PageDTO<FamousPeople> peoplePage = new PageDTO<FamousPeople>();
			peoplePage.setPageIndex(1);
			peoplePage.setPageSize(12);//
			FamousPeople param = new FamousPeople();
			param.setStatus(1);// 查找所有有效的
			peoplePage.setParam(param);
			PageDTO<List<FamousPeopleDTO>> hotPeople = famousPeopleService
					.getListByPage(peoplePage);
			model.addAttribute("hotPeople", hotPeople);

			// 查找五条猜你喜欢
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

			// 查找五条该名人的对话
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

	// 跳转到名人搜索列表页面
	@RequestMapping(value = "/brandSearch")
	public String brandSearch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String key = request.getParameter("key");// 检索
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
		brand.setTagName(key);// 名字
		brand.setStatus(1);// 查找所有有效的
		brand.setType(1);// 1代表品牌
		brandPage.setParam(brand);

		PageDTO<List<ArticalTagDTO>> result = tagService
				.getListByPage(brandPage);

		model.addAttribute("key", key);
		model.addAttribute("pageIndex", pageIndexInt);
		model.addAttribute("pageSize", pageSizeInt);
		model.addAttribute("brandPage", result);
		return "carBrand";
	}

	// 跳转到汽车品牌列表页面
	@RequestMapping(value = "/carBrand")
	public String carBrand(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String charIndex = request.getParameter("charIndex");// 检索
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
		brand.setStatus(1);// 查找所有有效的
		brand.setType(1);// 1代表品牌
		brandPage.setParam(brand);

		PageDTO<List<ArticalTagDTO>> result = tagService
				.getListByPage(brandPage);
		// 查询检索的首字母
		List<String> charIndexs = tagService.getBrandCharArr();

		model.addAttribute("charIndex", charIndex);
		model.addAttribute("charIndexs", charIndexs);
		model.addAttribute("brandPage", result);
		return "carBrand";
	}

	// 跳转到品牌详细页面
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
		String brandId = request.getParameter("brandId");// 获取brandID
		if (brandId != null && brandId.matches("\\d+")) {
			Integer id = Integer.parseInt(brandId);
			ArticalTagDTO brandTag = tagService.getById(id);
			if (brandTag != null) {// ajax加载
				if (AJAX.equals(operator)) {
					Artical hot = new Artical();
					PageDTO<Artical> hotParam = new PageDTO<Artical>();
					hotParam.setPageIndex(pageIndexInt);
					hotParam.setPageSize(pageSizeInt);
					hotParam.setParam(hot);
					hot.setStatus(1);

					// 查找五条该品牌的资讯
					List<ArticalDTO> onwerList = articalService
							.selectedByParamAndTagId(hotParam, id);
					model.addAttribute("onwerList", onwerList);
					return "carBrandDetailAjax";
				}
			}
			model.addAttribute("brand", brandTag);
			// 查找12个品牌
			PageDTO<ArticalTag> pageParam = new PageDTO<ArticalTag>();
			pageParam.setPageIndex(1);
			pageParam.setPageSize(12);//
			ArticalTag param = new ArticalTag();
			param.setStatus(1);// 查找所有有效的
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
				// 查找五条该品牌的资讯
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

	// 跳转到作者专栏页面
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

		// 查找18条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getAuthorArticalByPage(newParam);// 查找作者最新的文章
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setStartDate(getLastDay());// 上星期一
		List<ArticalDTO> hotList = articalService
				.selectedHotArticalByPage(hotParam);
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的热门文章
		hotParam.setPageSize(5);
		hot.setStartDate(null);
		hot.setRightNum(1);// 根据点赞数来排
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

	// 作者专栏详细页面
	@RequestMapping(value = "/authorIndex")
	public String userIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String authorId = request.getParameter("authorId");
		Integer authorIdInt = Integer.parseInt(authorId);
		// 查询该用户的作者信息
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
			// 查询该用户的所有文章
			Artical param = new Artical();
			param.setStatus(1);// 正常状态
			param.setAuthorId(author.getAuthorId());// 作者ID

			PageDTO<Artical> newParam = new PageDTO<Artical>();
			newParam.setPageIndex(pageIndexInt);
			newParam.setParam(param);
			newParam.setPageSize(pageSizeInt);
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getPageByParam(newParam);
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("author", author);// 作者信息
		return "authorIndex";
	}

	// 跳转到封面秀页面
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
		param.setStatus(1);// 正常状态
		param.setType(3);// 3代表封面秀
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
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

	// 跳转到封面秀详细
	@RequestMapping(value = "/showDetail")
	public String showDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");

		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getById(Integer.parseInt(articalId));
			// 阅读量+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5分钟
				// 更新访问量
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
		// 查找4条猜你喜欢
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(3);// 3代表封面秀
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(1);
		newParam.setPageSize(4);
		newParam.setParam(param);

		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);
		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
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

	// 跳转到投稿页面
	@RequestMapping(value = "/contribute")
	public String contribute(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "contribute";
	}

	// 跳转到活动页面
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

		// 查找5条最新资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(5);// 5代表活动
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

	// 跳转到专题详细页面,根据标签查找
	@RequestMapping(value = "/topicDetail")
	public String topicDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String tagId = request.getParameter("tagId");// 标签
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

		// 查找18条最新专题资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
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

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setSeeNum(1);// 查找点击量最高的

		List<ArticalDTO> hotList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的热门文章
		hotParam.setPageSize(5);
		hot.setSeeNum(null);
		hot.setStartDate(null);
		hot.setRightNum(1);
		// 根据点赞数来排
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

	// 跳转到专题页面,根据标签查找
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
		// 查找专题标签
		ArticalTag showTag = new ArticalTag();
		showTag.setType(TOPIC_TAG_ID);// 专题标签
		PageDTO<ArticalTag> tagParam = new PageDTO<ArticalTag>();
		tagParam.setPageIndex(pageIndexInt);
		tagParam.setPageSize(pageSizeInt);
		tagParam.setParam(showTag);

		PageDTO<List<ArticalTagDTO>> tags = tagService.getListByPage(tagParam);

		for (ArticalTagDTO tag : tags.getParam()) {
			tagIds.add(tag.getTagId());
		}
		model.addAttribute("tags", tags);

		// 查找10条，一周内的热门文章
		Artical hot = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(hot);
		hot.setStatus(1);
		hot.setSeeNum(1);// 查找点击量最高的

		List<ArticalDTO> hotList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的热门文章
		hotParam.setPageSize(5);
		hot.setSeeNum(null);
		hot.setStartDate(null);
		hot.setRightNum(1);// 根据点赞数来排
		List<ArticalDTO> nowList = articalService.selectedArticalByPageAndTag(
				hotParam, tagIds).getParam();
		if (nowList == null || nowList.size() == 0) {
			hot.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}

		model.addAttribute("nowList", nowList);
		return "topics";
	}

	// 跳转到经销商页面,type=4
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

		// 查找18条最新经销商资讯
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(4);// 4代表经销商
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		// 查找
		PageDTO<List<ArticalDTO>> newPage = articalService
				.getPageByParam(newParam);

		model.addAttribute("newPage", newPage);

		// 查找10条，一周内的热门文章
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(param);
		param.setStartDate(getLastDay());
		param.setType(4);
		param.setSeeNum(1);// 查找点击量最高的

		List<ArticalDTO> hotList = articalService.getPageByParam(hotParam)
				.getParam();
		model.addAttribute("hotList", hotList);

		// 查找5条本星期的热门文章
		hotParam.setPageSize(5);
		param.setSeeNum(null);
		param.setStartDate(null);
		param.setRightNum(1);// 根据点赞数来排
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

	// 视频页面 type=6
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

		// 查找18条原创视频
		Artical param = new Artical();
		param.setStatus(1);// 正常状态
		param.setType(4);// 4代表经销商
		PageDTO<Artical> newParam = new PageDTO<Artical>();
		newParam.setPageIndex(pageIndexInt);
		newParam.setPageSize(pageSizeInt);
		newParam.setParam(param);

		// 查100条原创视频
		List<ArticalDTO> ownVideo = articalService
				.getVideoArticalByPageAndType(newParam, 0);
		model.addAttribute("ownVideo", ownVideo);
		// 查100条试驾视频
		List<ArticalDTO> testVideo = articalService
				.getVideoArticalByPageAndType(newParam, 1);
		model.addAttribute("testVideo", testVideo);

		// 查找10条，一周内的热门文章
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(param);
		param.setStartDate(getLastDay());
		param.setType(null);
		param.setSeeNum(1);// 查找点击量最高的

		List<ArticalDTO> hotList = articalService.getPageByParam(hotParam)
				.getParam();
		model.addAttribute("hotList", hotList);

		return "video";
	}

	// 视频详细页面 type=6
	@RequestMapping(value = "/videoDetail")
	public String videoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String articalId = request.getParameter("articalId");
		ArticalDTO articalDTO = null;
		if (articalId != null && articalId.matches("\\d+")) {
			articalDTO = articalService.getVideoArticalById(Integer
					.parseInt(articalId));
			// 阅读量+1
			String cookieName = "artical_" + articalId;
			Cookie hasSeen = CookieUtil.getCookieByName(request, cookieName);
			if (hasSeen == null) {
				CookieUtil.addCookie(response, cookieName, "see", 60 * 5);// 5分钟
				// 更新访问量
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

		// 查找10条，一周内的热门文章
		Artical param = new Artical();
		PageDTO<Artical> hotParam = new PageDTO<Artical>();
		hotParam.setPageIndex(1);
		hotParam.setPageSize(10);
		hotParam.setParam(param);
		param.setStartDate(getLastDay());
		param.setType(null);
		param.setSeeNum(1);// 查找点击量最高的

		List<ArticalDTO> hotList = articalService.getPageByParam(hotParam)
				.getParam();
		model.addAttribute("hotList", hotList);

		// 查找5条推荐的的热门文章
		hotParam.setPageSize(5);
		param.setSeeNum(null);
		param.setStartDate(null);
		param.setRightNum(1);// 根据点赞数来排
		List<ArticalDTO> nowList = articalService.getPageByParam(hotParam)
				.getParam();
		if (nowList == null || nowList.size() == 0) {
			param.setType(null);
			nowList = articalService.selectedHotArticalByPage(hotParam);
		}
		model.addAttribute("nowList", nowList);

		return "videoDetail";
	}

	// QQ验证
	private com.qq.connect.oauth.Oauth qqOauth = new com.qq.connect.oauth.Oauth();

	// QQ登陆
	@RequestMapping(value = "/qqLogin")
	public String qqLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String code=request.getParameter("code");
		try {
			String qqToken = null;
			String openId = null;
			AccessToken token = qqOauth.getAccessTokenByRequest(request);
			if (token.getAccessToken().equals("")) {
				// 授权失败
				System.out.print("没有获取到响应参数" + token);
			} else {
				qqToken = token.getAccessToken();// 获取token
				OpenID openIDObj = new OpenID(qqToken);
				openId = openIDObj.getUserOpenID();
				// 根据openId去查找对象
				ThirdUserDTO thirdUser = thirdService.getByOpenId(openId);

				if (thirdUser != null) {// 如果用户存在。则匹配该用户数据 登陆成功
					AdminUserDTO user = adminUserService.getById(thirdUser
							.getUserId());
					if (user != null) {
						request.getSession().setAttribute(USER_SESSION, user);
					}
				} else {// 如果用户不存在
					UserInfo qzoneUserInfo = new UserInfo(qqToken, openId);
					UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
					String photo = null;
					String nickname = null;
					if (userInfoBean.getRet() == 0) {
						photo = userInfoBean.getAvatar().getAvatarURL100();// 头像
						nickname = userInfoBean.getNickname();// 姓名
					}
					AdminUser saveParam = new AdminUser();
					saveParam.setLoginname(UuidUtils.getObjectUUID("qq"));
					saveParam.setNickname(nickname);
					saveParam.setOrinal(0);//
					saveParam.setEmail(UuidUtils.getObjectUUID("qq"));
					saveParam.setCreateDate(new Date());
					saveParam.setStatus(1);// 1-激活
					saveParam.setPassword(nickname);
					saveParam.setType(0);// 门户用户
					saveParam.setDescription("QQ第三方登陆");
					adminUserService.saveOrUpdate(saveParam);
					// 保存用户头像
					Author author = new Author();
					author.setPenName(nickname);
					author.setUserId(saveParam.getAdminUserId());
					author.setCreateDate(new Date());
					author.setPhotoPath(photo);
					author.setStatus(1);
					authorService.saveOrUpdate(author);
					// 存入第三方对象
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

	// sina验证
	private weibo4j.Oauth oauth_sina = new weibo4j.Oauth();

	// 新浪登陆
	@RequestMapping(value = "/sinaLogin")
	public String sinaLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String code = request.getParameter("code");
		try {
			String uid = null;
			weibo4j.http.AccessToken token = oauth_sina
					.getAccessTokenByCode(code);
			uid = token.getUid();// 获取uid
			// 根据uiId去查找对象
			ThirdUserDTO thirdUser = thirdService.getByOpenId(uid);
			if (thirdUser != null) {// 如果用户存在。则匹配该用户数据 登陆成功
				AdminUserDTO user = adminUserService.getById(thirdUser
						.getUserId());
				if (user != null) {
					request.getSession().setAttribute(USER_SESSION, user);
				}
			} else {// 如果用户不存在
				Users um = new Users(token.getAccessToken());
				User user_sina = um.showUserById(uid);
				String photo = null;
				String nickname = null;
				photo = user_sina.getAvatarLarge();// 头像
				nickname = user_sina.getScreenName();// 姓名
				AdminUser saveParam = new AdminUser();
				saveParam.setLoginname(UuidUtils.getObjectUUID("qq"));
				saveParam.setNickname(nickname);
				saveParam.setOrinal(0);//
				saveParam.setEmail(UuidUtils.getObjectUUID("qq"));
				saveParam.setCreateDate(new Date());
				saveParam.setStatus(1);// 1-激活
				saveParam.setPassword(nickname);
				saveParam.setType(0);// 门户用户
				saveParam.setDescription("sina第三方登陆");
				adminUserService.saveOrUpdate(saveParam);
				// 保存用户头像
				Author author = new Author();
				author.setPenName(nickname);
				author.setIntroduce(user_sina.getDescription());
				author.setSinaPath(user_sina.getUrl());
				author.setUserId(saveParam.getAdminUserId());
				author.setCreateDate(new Date());
				author.setPhotoPath(photo);
				author.setStatus(1);
				authorService.saveOrUpdate(author);
				// 存入第三方对象
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

	// 登陆页面
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
		String orinal = request.getParameter("orinal");// 0-个人，1-企业
		String loginTag = request.getParameter("loginTag");// 记住登陆状态，

		model.addAttribute("loginname", loginname);
		// 登录名验证
		if (loginname == null || loginname.equals("")) {
			model.addAttribute("loginname_info", "登录名不能为空");
		} else if (loginname.length() < 6) {
			model.addAttribute("loginname_info", "登录名太短");
		} else if (loginname.length() > 30) {
			model.addAttribute("loginname_info", "登录名长度太长");
		}
		// 密码格式验证
		if (password == null || password.equals("")) {
			model.addAttribute("password_info", "密码不能为空");
		} else if (password.length() < 6) {
			model.addAttribute("password_info", "密码长度太短");
		} else if (password.length() > 50) {
			model.addAttribute("password_info", "密码长度太长");
		}
		int orinalInt = 0;
		if (orinal != null && orinal.matches("\\d{1}")) {
			orinalInt = Integer.parseInt(orinal);
		}
		model.addAttribute("orinal", orinal);// 保存来源

		AdminUser adminUser = new AdminUser();
		adminUser.setLoginname(loginname);
		adminUser.setOrinal(orinalInt);
		AdminUserDTO user = adminUserService.mengHuLogin(adminUser);
		if (user == null) {
			if (orinalInt == 0) {
				model.addAttribute("loginname_info", "用户名不存在");
			} else {
				model.addAttribute("loginname_info", "该企业号不存在");
			}
		} else if (!PasswordUtil.MD5(password).equalsIgnoreCase(
				user.getPassword())) {
			model.addAttribute("password_info", "密码错误");
		} else if (user.getStatus() == 2) {
			model.addAttribute("loginname_info", "该账号被冻结");
		} else {// 登陆成功
			request.getSession().setAttribute(USER_SESSION, user);// 保存session
			// 如果有保存登录状态
			if (loginTag != null) {
				CookieUtil.addCookie(response, LOGIN_TAG, user.getAdminUserId()
						+ "", 60 * 60 * 24 * 7);// 保存一个星期
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

	// 登出系统
	@RequestMapping(value = "/out")
	public String loginout(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		AdminUserDTO user = (AdminUserDTO) session.getAttribute(USER_SESSION);
		if (user != null) {
			session.removeAttribute(USER_SESSION);
			session.invalidate();// 摧毁session;
			CookieUtil.removeCookie(response, LOGIN_TAG);// 移除cookie
		}
		JsonDTO json = new JsonDTO();
		json.setStatus(1);
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 跳转注册页面
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

	// 参数验证
	@RequestMapping(value = "/valid")
	public void valid(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String email = request.getParameter("email");// 验证邮箱
		String phone = request.getParameter("phone");// 验证手机
		String loginname = request.getParameter("loginname");// 验证昵称
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

	// 注册
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String nickname = request.getParameter("nickname");// 30
		String loginname = request.getParameter("loginname");// 30登录名
		String position = request.getParameter("position");// 50位置
		String orinal = request.getParameter("orinal");// 0-个人，1-企业
		String email = request.getParameter("email");// 50
		String phone = request.getParameter("phone");// 11
		String password = request.getParameter("password");// 6-50
		String registerCode = request.getParameter("registerCode");// 6
		String registerCode_1 = request.getParameter("registerCode_1");// 6
		JsonDTO json = new JsonDTO();
		if ((nickname == null || nickname.length() <= 30)// 验证昵称
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
				// 清除验证码
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
				saveParam.setOrinal(orinalInt);// 0个人 1-企业
				saveParam.setPosition(position);// 企业位置
				saveParam.setEmail(email);
				saveParam.setCreateDate(new Date());
				saveParam.setStatus(1);// 1-激活
				saveParam.setPassword(PasswordUtil.MD5(password));
				saveParam.setType(0);// 门户用户
				saveParam.setDescription("门户网站自建");
				try {
					adminUserService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("创建用户成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("创建用户失败：" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				json.setStatus(0).setMessage("验证码错误");
			}
		} else {
			json.setStatus(0).setMessage("数据错误");
		}
		model.addAttribute("json", json);
		return "registerResult";
	}

	// 找回密码
	@RequestMapping(value = "/findps")
	public String fingps(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "findps";
	}

	// 关于我们
	@RequestMapping(value = "/introduce")
	public String introduce(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "introduce";
	}

	// 上周同一时刻
	@SuppressWarnings("deprecation")
	private Date getLastDay() {
		Date temp = new Date();
		temp.setDate(temp.getDate() - 7);
		return temp;
	}

}
