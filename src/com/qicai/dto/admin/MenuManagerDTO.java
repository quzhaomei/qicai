package com.qicai.dto.admin;
/**
 * @author qzm
 *
 * @since 2015-6-10
 */
public class MenuManagerDTO {
	private Integer menuId;//�˵�ID
	private String menuName;//�˵�����
	private String uri;//
	private Integer parentId;//
	private Integer type;//0-ҳ��˵���1-�����˵�
	private Integer status;//1-����
	
	private Integer tempId;//�м��ID
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTempId() {
		return tempId;
	}
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	
	
	
}
