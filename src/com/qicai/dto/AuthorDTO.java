package com.qicai.dto;

import java.util.Date;

/**���߹���
 * @author Administrator
 */
public class AuthorDTO  {
	private Integer authorId;
	private Integer userId;//��̨�û�ID  
	private String penName;//����  10
	private String sinaPath;//50
	private String txPath;//50
	private String introduce;//���˽��� 200
	private String photoPath;//ͷ��  50
	private String status;//0-���ᣬ1-��Ч
	private Date createDate;//  50
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPenName() {
		return penName;
	}
	public void setPenName(String penName) {
		this.penName = penName;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSinaPath() {
		return sinaPath;
	}
	public void setSinaPath(String sinaPath) {
		this.sinaPath = sinaPath;
	}
	public String getTxPath() {
		return txPath;
	}
	public void setTxPath(String txPath) {
		this.txPath = txPath;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}
