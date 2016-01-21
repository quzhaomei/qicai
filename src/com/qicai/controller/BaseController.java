package com.qicai.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.qicai.service.AdminUserService;
import com.qicai.service.AdvertiseService;
import com.qicai.service.ArticalResourceService;
import com.qicai.service.ArticalService;
import com.qicai.service.ArticalTagService;
import com.qicai.service.AuthorService;
import com.qicai.service.FamousPeopleService;
import com.qicai.service.FriendHrefService;
import com.qicai.service.ImgScrollService;
import com.qicai.service.InfoScrollService;
import com.qicai.service.MenuManagerService;
import com.qicai.service.RoleManagerService;
import com.qicai.service.RoleToMenusService;
import com.qicai.service.ThirdUserService;
import com.qicai.service.TopManagerService;
import com.qicai.service.VideoService;
import com.qicai.service.ZcarMenuService;
import com.qicai.util.SqlTimeTest;

/**
 * @author qzm
 * @since 2015-5-13
 */
@Controller
public class BaseController {
	@Resource
	protected SystemInit init;//系统初始化设定
	//sql性能测试
	protected SqlTimeTest sqlTime;
	
	/*** 常量开始*/
	public static final String  TO_ADD="toAdd";
	public static final String  ADD="add";
	public static final String TO_EDIT="toEdit";
	public static final String  EDIT="edit";
	public static final String  DEL="del";
	public static final String  JSON="json";
	public static final String  LIST="list";
	public static final String  CHECK="check";
	public static final String  RESET="reset";
	public static final String  FIND_BY_ID="findById";
	
	public static final String  PC_TAG="PC";
	public static final String  PHONE_TAG="PHONE";
	
	public static final String  ADMIN_USER_SESSION="adminUserSession";
	public static final String  USER_SESSION="userSession";
	public static final String  LOGIN_TAG="loginTag";//登陆状态cookie。
	public static final String  AJAX="ajax";//ajax访问。
	
	public static final String AUTHOR_ROLE_ID="2";//作者ID
	/*** 常量结束*/
	
	
	@Resource
	protected MenuManagerService menuManagerService;//菜单管理
	
	@Resource
	protected RoleManagerService roleManagerService;//角色管理
	
	@Resource
	protected RoleToMenusService roleToMenusService;//权限管理
	
	@Resource
	protected AdminUserService adminUserService;//后台用户管理
	
	@Resource
	protected ZcarMenuService zcarMenuService;//门户首页菜单管理
	
	@Resource
	protected ArticalResourceService resourceService;//文章来源管理
	
	@Resource
	protected ArticalTagService tagService;//文章标签管理
	
	@Resource
	protected FamousPeopleService famousPeopleService;//名人管理
	
	@Resource
	protected ArticalService articalService;//文章管理
	
	@Resource
	protected AuthorService authorService;//作者管理
	
	@Resource
	protected TopManagerService topManagerService;//置顶分类管理
	
	@Resource
	protected AdvertiseService advertiseService;//广告管理
	
	@Resource
	protected FriendHrefService hrefService;//友情链接管理
	
	@Resource
	protected ImgScrollService scrollService;//首页banner轮播管理
	
	@Resource
	protected InfoScrollService infoScrollService;//滚动资讯管理
	
	@Resource
	protected VideoService videoService;//视频管理
	
	@Resource
	protected ThirdUserService thirdService;//第三方登陆对象
	
}
