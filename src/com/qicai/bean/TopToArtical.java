package com.qicai.bean;

import java.util.Date;

/**
 * �ö�����-�����м��
 */
public class TopToArtical {
	private Integer topId;
	private Integer articalId;
	private Date topDate;//�ö�ʱ��
	public Integer getTopId() {
		return topId;
	}
	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	public Integer getArticalId() {
		return articalId;
	}
	public void setArticalId(Integer articalId) {
		this.articalId = articalId;
	}
	public Date getTopDate() {
		return topDate;
	}
	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}
}
