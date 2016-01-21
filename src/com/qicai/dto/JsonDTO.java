package com.qicai.dto;
/**
 * json 数据封装类
 * @author Administrator
 *
 */
public class JsonDTO {
	/**
	 * 0-失败，1-成功，2-无权限，3-未登录
	 */
	private Integer status;
	/**
	 * 表示需要返回的提示信息。
	 */
	private String message;
	/**
	 * 表示返回的数据
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
