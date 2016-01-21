package com.qicai.dto;

import java.util.Date;
import java.util.List;

/**
 * 文章 
 */
public class ArticalDTO {
	private Integer articalId;//主键
	private AuthorDTO author;//作者
	private String title;//标题  50
	private String quote;//引言   200
	private String scanImgPath;//缩略图 50
	private Integer scanImgWidth;//缩略图宽度
	private Integer scanImgHeight;//缩略图高度
	
	private ArticalResourceDTO resource;//文章来源ID
	private List<ArticalTagDTO> tagList;//标签ID数组
	private String content;//内容
	private Integer type;//文章所属的类型  0-普通资讯
	
	public AuthorDTO getAuthor() {
		return author;
	}
	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	private Integer rightNum;//顶的人数
	private Integer wrongNum;//踩的人数
	
	private Integer seeNum;//阅读人数
	private Integer commitNum;//评论数目
	private Integer referId;//referId,关联的外键ID
	private String sourcePath;//资源路径 100
	private Date startDate;
	private Date endDate;
	
	private Date createDate;//创建时间
	private String createDateStr;//创建时间字符串
	private Integer status;//状态 0-删除，1-正常
	
	private FamousPeopleDTO famousPeople;//名人
	private VideoDTO video;//视频
	
	private Date topDate;//置顶时间
	
	public Date getTopDate() {
		return topDate;
	}
	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}
	public Integer getScanImgWidth() {
		return scanImgWidth;
	}
	
	public VideoDTO getVideo() {
		return video;
	}
	public void setVideo(VideoDTO video) {
		this.video = video;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
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
	public FamousPeopleDTO getFamousPeople() {
		return famousPeople;
	}
	public void setFamousPeople(FamousPeopleDTO famousPeople) {
		this.famousPeople = famousPeople;
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
	
	public ArticalResourceDTO getResource() {
		return resource;
	}
	public void setResource(ArticalResourceDTO resource) {
		this.resource = resource;
	}
	public List<ArticalTagDTO> getTagList() {
		return tagList;
	}
	public void setTagList(List<ArticalTagDTO> tagList) {
		this.tagList = tagList;
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
