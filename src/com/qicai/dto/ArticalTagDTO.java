package com.qicai.dto;

import java.util.Date;

/**
 * ���±�ǩ
 */
public class ArticalTagDTO {
	private Integer tagId;//Ʒ��ID��
	private String tagName;//20��ǩ����
	private Date createDate;//����ʱ��
	private Integer status;//״̬0-���ᣬ1-������
	
	private String pinying;//ƴ��  50
	private String href;//���� 50
	private String imgPath;//ͼƬ���� 50
	private Integer imgWidth;//ͼƬ��� 5
	private Integer imgHeight;//ͼƬ�߶� 5
	private String introduce;//Ʒ�ƽ��� 
	private Date topDate;//��λʱ��
	private Integer type;//1:Ʒ�ƣ�2-ר�⣬3-������
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
