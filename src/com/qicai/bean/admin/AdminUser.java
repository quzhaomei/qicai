package com.qicai.bean.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * ϵͳ��Ա�࣬һ��ϵͳ��Ա�����Զ�Ӧ�����ɫ
 * 
 * @author qzm
 * 
 * @since 2015-6-10
 */
public class AdminUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer adminUserId;// ����
	private String loginname;// ��¼�� 30 Ψһ
	private String password;// ��¼���� 50
	
	private String nickname;// �ǳ� 30   
	private String phone;// �绰 �������һ����� 11 Ψһ
	private String email;//	���� 50  Ψһ
	
	private Integer status;// ״̬��0-ɾ����1-��Ч,2-����
	private String description;//��ע 200
	
	private Date createDate;// ������ʱ��
	private Integer createUserId;// �����Ĺ���ԱID��
	private Date updateDate;// ����ʱ��
	private Integer updateUserId;// ���²����Ĺ���ԱID
	
	private Integer type;//�˺����͡�0-�Ż��˺ţ�1-�����˺ţ������˺�ͨ�ã��Ż��˺�ֻ�ܵ�½ǰ̨
	
	private Integer orinal;//0-���ˣ�1-��ҵ  ||2
	private String position;//��˾��ַ;50
	
	
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
