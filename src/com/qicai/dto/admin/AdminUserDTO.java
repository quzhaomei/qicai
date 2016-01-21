package com.qicai.dto.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdminUserDTO {
	private Integer adminUserId;// ����
	private String loginname;// ��¼��
	private String password;// ��¼����
	
	private String nickname;// �ǳ�
	private String phone;// �绰 �������һ�����
	private String email;// email
	
	private String description;//��ע
	private Integer status;// ״̬��0-ɾ����1-��Ч,2-����
	private Date createDate;// ������ʱ��
	private AdminUserDTO createUserDTO;// �����Ĺ���Ա
	private Date updateDate;// ����ʱ��
	private AdminUserDTO updateUserDTO;// ���²����Ĺ���Ա
	
	private List<RoleManagerDTO> roles;// �û���Ӧ�Ľ�ɫID�ַ�����id,id �������ַ���
	private Map<String,MenuManagerDTO> menuList;//�û����Է��ʵĲ˵�
	private Integer type;//�˺����͡�0-�Ż��˺ţ�1-�����˺ţ������˺�ͨ�ã��Ż��˺�ֻ�ܵ�½ǰ̨
	
	private Integer orinal;//0-���ˣ�1-��ҵ  ||2
	private String position;//��˾��ַ;50
	
	
	
	
	public Integer getOrinal() {
		return orinal;
	}
	public void setOrinal(Integer orinal) {
		this.orinal = orinal;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public AdminUserDTO getCreateUserDTO() {
		return createUserDTO;
	}
	public void setCreateUserDTO(AdminUserDTO createUserDTO) {
		this.createUserDTO = createUserDTO;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public AdminUserDTO getUpdateUserDTO() {
		return updateUserDTO;
	}
	public void setUpdateUserDTO(AdminUserDTO updateUserDTO) {
		this.updateUserDTO = updateUserDTO;
	}
	
	public List<RoleManagerDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleManagerDTO> roles) {
		this.roles = roles;
	}
	public Map<String, MenuManagerDTO> getMenuList() {
		return menuList;
	}
	public void setMenuList(Map<String, MenuManagerDTO> menuList) {
		this.menuList = menuList;
	}
	
}
