package com.qicai.dto;


/**
 * ���Ʋ˵�����--��������
 */
public class ZcarMenuDTO {
	private Integer menuId;//����
	private Integer parentId;//����ID��Ϊ-1��ʱ�򣬱�ʾ�ڶ��㣬not null
	private Integer position;//λ�á�2 ��parentId����Ψһ
	private String name;//����10
	private String uri;//����50
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
