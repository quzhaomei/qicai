package com.qicai.bean;

import java.util.Date;
import java.util.List;

/**
 * 文章 
 */
public class Artical {
	private Integer articalId;//主键
	private String title;//标题  50
	private String quote;//引言   200
	private Integer authorId;//作者ID
	private String scanImgPath;//缩略图 50
	private Integer scanImgWidth;//缩略图宽度
	private Integer scanImgHeight;//缩略图高度
	private Integer resourceId;//文章来源ID
	private List<Integer> tagId;//标签ID数组
	private String content;//内容
	private Integer type;
	//文章所属的类型  1-普通资讯，2-名人对话，3-封面秀,4-经销商,5-活动,6-视频,7-数据
	
	private Integer rightNum;//顶的人数
	private Integer wrongNum;//踩的人数
	
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	private Integer seeNum;//阅读人数
	private Integer commitNum;//评论数目
	private Integer referId;//referId,关联的外键ID  名人ID，
	private String sourcePath;//资源路径 100，pdf文件；||地点
	private Date startDate;//发行时间 ||结束时间
	private Date endDate;//开始时间
	
	private Date createDate;//创建时间
	private Integer status;//状态 0-删除，1-正常,2-冻结
	
	private Integer topDate;//置顶操作  1置顶。0取消
	
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
