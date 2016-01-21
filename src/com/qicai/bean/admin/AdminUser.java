package com.qicai.bean.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统人员类，一个系统人员，可以对应多个角色
 * 
 * @author qzm
 * 
 * @since 2015-6-10
 */
public class AdminUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer adminUserId;// 主键
	private String loginname;// 登录名 30 唯一
	private String password;// 登录密码 50
	
	private String nickname;// 昵称 30   
	private String phone;// 电话 ：用于找回密码 11 唯一
	private String email;//	邮箱 50  唯一
	
	private Integer status;// 状态，0-删除，1-有效,2-冻结
	private String description;//备注 200
	
	private Date createDate;// 创建的时间
	private Integer createUserId;// 创建的管理员ID。
	private Date updateDate;// 更新时间
	private Integer updateUserId;// 更新操作的管理员ID
	
	private Integer type;//账号类型。0-门户账号，1-管理账号，管理账号通用，门户账号只能登陆前台
	
	private Integer orinal;//0-个人，1-企业  ||2
	private String position;//公司地址;50
	
	
	public Integer getOrinal() {
		return orinal;
	}
	public void setOrinal(Integer orinal) {
		this.orinal = orinal;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getEmail() {
		return email;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
