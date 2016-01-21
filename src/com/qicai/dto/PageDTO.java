package com.qicai.dto;

import java.io.Serializable;


/**
 * ��ҳҵ�����
 * @author qzm
 *
 * @since 2015-5-14
 */
public class PageDTO<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer pageSize;//ҳ�������
	private Integer pageIndex;//ҳ��
	private Integer totalPage;
	private T param;//��Ҫ���ݵĲ�����
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}

	
}
