package com.qicai.dto;

import java.util.Date;

/**
 * ������
 * @author Administrator
 */
public class AdvertiseDTO {
	private Integer advertiseId;
	private String code;//�����ҳ��ı�־ Ψһ��unique  50
	private String pageUrl;//ҳ���url  50
	private String href;//������� 50
	private String imgPath;//ͼƬ·��  50
	private Integer width;//���   5
	private Integer height;//�߶� 5
	private String descript;//�������  100
	private Date createDate;//����ʱ��
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(Integer advertiseId) {
		this.advertiseId = advertiseId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
