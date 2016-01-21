package com.qicai.dto;

import java.util.Date;

/**
 * 汽车品牌管理
 * @author Administrator
 */
public class ZcarBrandDTO {
	private Integer brandId;//品牌ID；
	
	private String brandName;//10
	private String href;//链接
	private String imgPath;//图片链接
	private Integer imgWidth;//图片宽度 5
	private Integer imgHeight;//图片高度 5
	
	private String introduce;//品牌介绍
	
	private Date createDate;//创建时间
	private Date topDate;//排位时间
	private Integer status;//状态0-冻结，1-正常，
	
	
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
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getTopDate() {
		return topDate;
	}
	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
