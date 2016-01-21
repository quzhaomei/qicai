package com.qicai.bean.admin;

import java.io.Serializable;
import java.util.Date;
/**
 * @author qzm
 *
 * @since 2015-6-10
 */
public class RoleManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer roleId;//��ɫID
	private String roleName;//��ɫ����
	private Integer status;//״̬0-��Ч��1-���
	private Date createDate;//
	private Integer createUserId;//
	private Date updateDate;//
	private Integer updateUserId;//
	
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

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	
}
