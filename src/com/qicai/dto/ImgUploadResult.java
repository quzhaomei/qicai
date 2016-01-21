package com.qicai.dto;

/**
 * 图片上传结果
 * @author Administrator
 *
 */
public class ImgUploadResult {
	private Integer error;//0-成功，
	private String message;//
	
	private String url;//
	private String width;//
	private String height;//
	private String align;//
	private String title;//说明
	
	public  String getWidth() {
		return width;
	}
	public  void setWidth(String width) {
		this.width = width;
	}
	public  String getHeight() {
		return height;
	}
	public  void setHeight(String height) {
		this.height = height;
	}
	public  String getAlign() {
		return align;
	}
	public  void setAlign(String align) {
		this.align = align;
	}
	public  String getTitle() {
		return title;
	}
	public  void setTitle(String title) {
		this.title = title;
	}
	public  String getMessage() {
		return message;
	}
	public  void setMessage(String message) {
		this.message = message;
	}
	public  Integer getError() {
		return error;
	}
	public  void setError(Integer error) {
		this.error = error;
	}
	public  String getUrl() {
		return url;
	}
	public  void setUrl(String url) {
		this.url = url;
	}
	
}
