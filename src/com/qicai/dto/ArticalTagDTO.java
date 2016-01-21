package com.qicai.dto;

import java.util.Date;

/**
 * 文章标签
 */
public class ArticalTagDTO {
	private Integer tagId;//品牌ID；
	private String tagName;//20标签名字
	private Date createDate;//创建时间
	private Integer status;//状态0-冻结，1-正常，
	
	private String pinying;//拼音  50
	private String href;//链接 50
	private String imgPath;//图片链接 50
	private Integer imgWidth;//图片宽度 5
	private Integer imgHeight;//图片高度 5
	private String introduce;//品牌介绍 
	private Date topDate;//排位时间
	private Integer type;//1:品牌，2-专题，3-制造商
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPinying() {
		return pinying;
	}
	public void setPinying(String pinying) {
		this.pinying = pinying;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}
	public Integer getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getTopDate() {
		return topDate;
	}
	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
