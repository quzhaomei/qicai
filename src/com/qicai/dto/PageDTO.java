package com.qicai.dto;

import java.io.Serializable;


/**
 * 分页业务对象
 * @author qzm
 *
 * @since 2015-5-14
 */
public class PageDTO<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer pageSize;//页面的容量
	private Integer pageIndex;//页码
	private Integer totalPage;
	private T param;//需要传递的参数类
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
