package com.qicai.dto.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdminUserDTO {
	private Integer adminUserId;// 主键
	private String loginname;// 登录名
	private String password;// 登录密码
	
	private String nickname;// 昵称
	private String phone;// 电话 ：用于找回密码
	private String email;// email
	
	private String description;//备注
	private Integer status;// 状态，0-删除，1-有效,2-冻结
	private Date createDate;// 创建的时间
	private AdminUserDTO createUserDTO;// 创建的管理员
	private Date updateDate;// 更新时间
	private AdminUserDTO updateUserDTO;// 更新操作的管理员
	
	private List<RoleManagerDTO> roles;// 用户对应的角色ID字符串。id,id 隔开的字符串
	private Map<String,MenuManagerDTO> menuList;//用户可以访问的菜单
	private Integer type;//账号类型。0-门户账号，1-管理账号，管理账号通用，门户账号只能登陆前台
	
	private Integer orinal;//0-个人，1-企业  ||2
	private String position;//公司地址;50
	
	
	
	
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
