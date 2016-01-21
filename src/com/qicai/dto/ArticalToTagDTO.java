package com.qicai.dto;
/**
 * 文章-标签，中间表
 */
public class ArticalToTagDTO {
	private Integer id;
	private Integer articalId;//文章ID
	private Integer tagId;//标签ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getArticalId() {
		return articalId;
	}
	public void setArticalId(Integer articalId) {
		this.articalId = articalId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
}
