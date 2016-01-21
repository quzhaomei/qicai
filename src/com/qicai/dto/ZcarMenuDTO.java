package com.qicai.dto;


/**
 * 汽财菜单管理--文章类型
 */
public class ZcarMenuDTO {
	private Integer menuId;//主键
	private Integer parentId;//父类ID，为-1的时候，表示在顶层，not null
	private Integer position;//位置。2 与parentId联合唯一
	private String name;//名字10
	private String uri;//链接50
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
