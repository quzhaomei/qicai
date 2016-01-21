package com.qicai.bean;
/**
 * 文章-标签，中间表
 */
public class ArticalToTag {
	private Integer id;
	private Integer articaId;//文章ID
	private Integer tagId;//标签ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticaId() {
		return articaId;
	}
	public void setArticaId(Integer articaId) {
		this.articaId = articaId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
}
