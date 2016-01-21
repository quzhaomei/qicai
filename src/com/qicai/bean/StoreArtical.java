package com.qicai.bean;

/**
 * 文章收藏
 */
public class StoreArtical {
	private Integer storeId;
	private Integer articalId;//文章ID
	private Integer userId;//用户ID
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getArticalId() {
		return articalId;
	}
	public void setArticalId(Integer articalId) {
		this.articalId = articalId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
