package com.qicai.dto;

import java.util.Date;

/**
 * ������Դ����
 * @author Administrator
 *
 */
public class ArticalResourceDTO {
	private Integer resourceId;//ID
	private String name;//��Դ����
	private String url;//����
	private Date createDate;//����ʱ��
	private Integer status;//0-���ᣬ1-����
	
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
	public  Integer getResourceId() {
		return resourceId;
	}
	public  void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
