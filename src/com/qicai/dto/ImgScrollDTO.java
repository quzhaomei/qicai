package com.qicai.dto;

import java.util.Date;

/**
 *首页轮播图片管理
 */
public class ImgScrollDTO {
	private Integer scrollId;//ID
	private String imgPath;//图片路径
	private String title;//滚动标题 40
	private String href;//链接  50
	private String description;//100
	private Integer status;//0-冻结，1-正常
	private Date createDate;
	public Integer getScrollId() {
		return scrollId;
	}
	public void setScrollId(Integer scrollId) {
		this.scrollId = scrollId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
