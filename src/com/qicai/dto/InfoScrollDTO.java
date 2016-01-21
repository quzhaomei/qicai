package com.qicai.dto;

import java.util.Date;

/**
 * 首页滚动资讯
 * @author Administrator
 */
public class InfoScrollDTO {
	private Integer scrollId;
	private String title;//16
	private String href;//50 链接
	private Integer status;//状态
	private Date createDate;
	public Integer getScrollId() {
		return scrollId;
	}
	public void setScrollId(Integer scrollId) {
		this.scrollId = scrollId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
