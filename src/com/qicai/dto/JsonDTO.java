package com.qicai.dto;
/**
 * json ���ݷ�װ��
 * @author Administrator
 *
 */
public class JsonDTO {
	/**
	 * 0-ʧ�ܣ�1-�ɹ���2-��Ȩ�ޣ�3-δ��¼
	 */
	private Integer status;
	/**
	 * ��ʾ��Ҫ���ص���ʾ��Ϣ��
	 */
	private String message;
	/**
	 * ��ʾ���ص�����
	 */
	private Object data;
	
	public synchronized Integer getStatus() {
		return status;
	}
	public synchronized JsonDTO setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public JsonDTO setMessage(String message) {
		this.message = message;
		return this;
	}
	public Object getData() {
		return data;
	}
	public JsonDTO setData(Object data) {
		this.data = data;
		return this;
	}
}
