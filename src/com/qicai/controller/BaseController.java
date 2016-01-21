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
	protected SystemInit init;//ϵͳ��ʼ���趨
	//sql���ܲ���
	protected SqlTimeTest sqlTime;
	
	/*** ������ʼ*/
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
	public static final String  LOGIN_TAG="loginTag";//��½״̬cookie��
	public static final String  AJAX="ajax";//ajax���ʡ�
	
	public static final String AUTHOR_ROLE_ID="2";//����ID
	/*** ��������*/
	
	
	@Resource
	protected MenuManagerService menuManagerService;//�˵�����
	
	@Resource
	protected RoleManagerService roleManagerService;//��ɫ����
	
	@Resource
	protected RoleToMenusService roleToMenusService;//Ȩ�޹���
	
	@Resource
	protected AdminUserService adminUserService;//��̨�û�����
	
	@Resource
	protected ZcarMenuService zcarMenuService;//�Ż���ҳ�˵�����
	
	@Resource
	protected ArticalResourceService resourceService;//������Դ����
	
	@Resource
	protected ArticalTagService tagService;//���±�ǩ����
	
	@Resource
	protected FamousPeopleService famousPeopleService;//���˹���
	
	@Resource
	protected ArticalService articalService;//���¹���
	
	@Resource
	protected AuthorService authorService;//���߹���
	
	@Resource
	protected TopManagerService topManagerService;//�ö��������
	
	@Resource
	protected AdvertiseService advertiseService;//������
	
	@Resource
	protected FriendHrefService hrefService;//�������ӹ���
	
	@Resource
	protected ImgScrollService scrollService;//��ҳbanner�ֲ�����
	
	@Resource
	protected InfoScrollService infoScrollService;//������Ѷ����
	
	@Resource
	protected VideoService videoService;//��Ƶ����
	
	@Resource
	protected ThirdUserService thirdService;//��������½����
	
}
