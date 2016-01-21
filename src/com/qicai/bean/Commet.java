package com.qicai.bean;

import java.util.Date;

/**
 * 文章评论
 */
public class Commet {
	private Integer commetId;
	private String content;//评论内容
	private Integer type;//评论类别
	private Integer replyId;//评论ID
	private Integer userId;//评论的Id
	private Date createDate;//创建时间
	private Integer status;//状态
	public Integer getCommetId() {
		return commetId;
	}
	public void setCommetId(Integer commetId) {
		this.commetId = commetId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
