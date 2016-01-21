package com.qicai.dto.admin;


/**
 * ½ÇÉ«-Â·¾¶
 * @author qzm
 *
 * @since 2015-6-10
 */
public class RoleToMenusDTO  {
	private Integer id;//
	private Integer roleId;//
	private Integer menuId;//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
