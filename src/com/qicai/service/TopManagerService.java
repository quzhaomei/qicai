package com.qicai.service;

import java.util.List;

import com.qicai.bean.TopManager;
import com.qicai.dto.TopManagerDTO;

/**
 * 首页置顶分类
 * @author Administrator
 */
public interface TopManagerService extends BaseService<TopManager, TopManagerDTO> {
	/**
	 * 获取所有分类信息，不包括文章
	 * @return
	 */
	List<TopManagerDTO> getAllTopManager();
	/**
	 * 为置顶分类添加文章
	 * @return
	 */
	void addArtical(Integer articalId,Integer topId) throws Exception;
	/**
	 * 把文章置顶 
	 */
	void articalToTop(Integer articalId,Integer topId) throws Exception;
	/**
	 * 移除置顶文章 
	 */
	void articalTopRemove(Integer articalId,Integer topId) throws Exception;
}
