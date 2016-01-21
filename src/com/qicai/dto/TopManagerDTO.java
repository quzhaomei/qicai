package com.qicai.dto;
import java.util.List;


/**
 * 首页置顶分类管理DTO
 */
public class TopManagerDTO {
	private Integer topId;//10
	private String name;//10
	private Integer status;//0-冻结，1-使用中，2-删除
	
	private PageDTO<List<ArticalDTO>> articals;//置顶的文章数据
	
	public PageDTO<List<ArticalDTO>> getArticals() {
		return articals;
	}
	public void setArticals(PageDTO<List<ArticalDTO>> articals) {
		this.articals = articals;
	}
	public Integer getTopId() {
		return topId;
	}
	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
