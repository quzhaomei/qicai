package com.qicai.dto;
/**
 * ����-��ǩ���м��
 */
public class ArticalToTagDTO {
	private Integer id;
	private Integer articalId;//����ID
	private Integer tagId;//��ǩID
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
