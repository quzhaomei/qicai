package com.qicai.dto;

public class VideoDTO {
	private Integer videoId;
	private String path;//200
	private Integer type;//0-ԭ����1-�Լ���Ƶ
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
