package com.qicai.bean;

import java.util.Date;

/**
 * ������Դ����
 * @author Administrator
 *
 */
public class ArticalResource {
	private Integer resourceId;//ID 
	private String name;//��Դ����  20
	private String url;//����  50
	private Date createDate;//����ʱ��
	private Integer status;//0-���ᣬ1-����
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
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
	
	
}
