package com.qicai.dto;

import java.util.Date;

/**
 * ����Ʒ�ƹ���
 * @author Administrator
 */
public class ZcarBrandDTO {
	private Integer brandId;//Ʒ��ID��
	
	private String brandName;//10
	private String href;//����
	private String imgPath;//ͼƬ����
	private Integer imgWidth;//ͼƬ��� 5
	private Integer imgHeight;//ͼƬ�߶� 5
	
	private String introduce;//Ʒ�ƽ���
	
	private Date createDate;//����ʱ��
	private Date topDate;//��λʱ��
	private Integer status;//״̬0-���ᣬ1-������
	
	
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
