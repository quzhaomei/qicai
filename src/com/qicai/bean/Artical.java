package com.qicai.bean;

import java.util.Date;
import java.util.List;

/**
 * ���� 
 */
public class Artical {
	private Integer articalId;//����
	private String title;//����  50
	private String quote;//����   200
	private Integer authorId;//����ID
	private String scanImgPath;//����ͼ 50
	private Integer scanImgWidth;//����ͼ���
	private Integer scanImgHeight;//����ͼ�߶�
	private Integer resourceId;//������ԴID
	private List<Integer> tagId;//��ǩID����
	private String content;//����
	private Integer type;
	//��������������  1-��ͨ��Ѷ��2-���˶Ի���3-������,4-������,5-�,6-��Ƶ,7-����
	
	private Integer rightNum;//��������
	private Integer wrongNum;//�ȵ�����
	
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	private Integer seeNum;//�Ķ�����
	private Integer commitNum;//������Ŀ
	private Integer referId;//referId,���������ID  ����ID��
	private String sourcePath;//��Դ·�� 100��pdf�ļ���||�ص�
	private Date startDate;//����ʱ�� ||����ʱ��
	private Date endDate;//��ʼʱ��
	
	private Date createDate;//����ʱ��
	private Integer status;//״̬ 0-ɾ����1-����,2-����
	
	private Integer topDate;//�ö�����  1�ö���0ȡ��
	
	public Integer getScanImgWidth() {
		return scanImgWidth;
	}
	public void setScanImgWidth(Integer scanImgWidth) {
		this.scanImgWidth = scanImgWidth;
	}
	public Integer getScanImgHeight() {
		return scanImgHeight;
	}
	public void setScanImgHeight(Integer scanImgHeight) {
		this.scanImgHeight = scanImgHeight;
	}
	
	public Integer getTopDate() {
		return topDate;
	}
	public void setTopDate(Integer topDate) {
		this.topDate = topDate;
	}
	public Integer getCommitNum() {
		return commitNum;
	}
	public void setCommitNum(Integer commitNum) {
		this.commitNum = commitNum;
	}
	public Integer getRightNum() {
		return rightNum;
	}
	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}
	public Integer getWrongNum() {
		return wrongNum;
	}
	public void setWrongNum(Integer wrongNum) {
		this.wrongNum = wrongNum;
	}
	
	public Integer getArticalId() {
		return articalId;
	}
	public void setArticalId(Integer articalId) {
		this.articalId = articalId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getScanImgPath() {
		return scanImgPath;
	}
	public void setScanImgPath(String scanImgPath) {
		this.scanImgPath = scanImgPath;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public List<Integer> getTagId() {
		return tagId;
	}
	public void setTagId(List<Integer> tagId) {
		this.tagId = tagId;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getReferId() {
		return referId;
	}
	public void setReferId(Integer referId) {
		this.referId = referId;
	}
	
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public Integer getSeeNum() {
		return seeNum;
	}
	public void setSeeNum(Integer seeNum) {
		this.seeNum = seeNum;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
