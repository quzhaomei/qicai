package com.qicai.dto;
import java.util.List;


/**
 * ��ҳ�ö��������DTO
 */
public class TopManagerDTO {
	private Integer topId;//10
	private String name;//10
	private Integer status;//0-���ᣬ1-ʹ���У�2-ɾ��
	
	private PageDTO<List<ArticalDTO>> articals;//�ö�����������
	
	public PageDTO<List<ArticalDTO>> getArticals() {
		return articals;
	}
	public void setArticals(PageDTO<List<ArticalDTO>> articals) {
		this.articals = articals;
	}
	public Integer getTopId() {
		return topId;
	}
	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
