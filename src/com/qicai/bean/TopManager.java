package com.qicai.bean;


/**
 * 首页置顶分类管理
 */
public class TopManager {
	private Integer topId;
	private String name;//10
	private Integer status;//0-冻结，1-使用中，2-删除
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
