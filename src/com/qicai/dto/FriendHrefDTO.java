package com.qicai.dto;

import java.util.Date;

/**
 * �������ӹ���
 * @author Administrator
 */
public class FriendHrefDTO {
	private Integer hrefId;
	private String name;//10
	private String href;//50
	private Integer status;//0:���ᣬ1-����
	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getHrefId() {
		return hrefId;
	}
	public void setHrefId(Integer hrefId) {
		this.hrefId = hrefId;
	}
	
}
