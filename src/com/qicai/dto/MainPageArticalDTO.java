package com.qicai.dto;

/**
 * 首页分类置顶的DTO
 */
public class MainPageArticalDTO {
	private Integer topId;
	private String name;
	private ArticalDTO artical;
	public Integer getTopId() {
		return topId;
	}
	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	public ArticalDTO getArtical() {
		return artical;
	}
	public void setArtical(ArticalDTO artical) {
		this.artical = artical;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
