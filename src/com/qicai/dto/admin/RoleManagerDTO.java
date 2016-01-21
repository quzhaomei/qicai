package com.qicai.dto.admin;

import java.io.Serializable;
import java.util.Date;
/**
 * @author qzm
 *
 * @since 2015-6-10
 */
public class RoleManagerDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer roleId;//角色ID
	private String roleName;//角色名字
	private Integer status;//状态0-无效，1-有效
	private Date createDate;//
	private AdminUserDTO createUserDTO;//
	private Date updateDate;//
	private AdminUserDTO updateUserDTO;//
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public synchronized Integer getStatus() {
		return status;
	}
	public synchronized void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public AdminUserDTO getCreateUserDTO() {
		return createUserDTO;
	}
	public void setCreateUserDTO(AdminUserDTO createUserDTO) {
		this.createUserDTO = createUserDTO;
	}
	public AdminUserDTO getUpdateUserDTO() {
		return updateUserDTO;
	}
	public void setUpdateUserDTO(AdminUserDTO updateUserDTO) {
		this.updateUserDTO = updateUserDTO;
	}
	
	
}
