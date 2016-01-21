package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.TopManager;
import com.qicai.bean.TopToArtical;
import com.qicai.dto.TopManagerDTO;

/**
 * 首页置顶分类
 * @author Administrator
 */
public interface TopManagerDao {
	void save(@Param(value="top")TopManager top);
	void update(@Param(value="top")TopManager top);
	TopManagerDTO findByParam(@Param(value="top")TopManager top);
	/**
	 * 获取所有分类信息，不包括文章
	 * @return
	 */
	List<TopManagerDTO> getAllTopManager();
	/**
	 * 为置顶分类添加文章
	 * @param articalId
	 * @param topId
	 */
	void addArtical(@Param(value="topToArtical") TopToArtical topToArtical);
	
	/**
	 * 把文章置顶 
	 */
	void articalToTop(@Param(value="topToArtical") TopToArtical topToArtical) throws Exception;
	/**
	 * 移除置顶文章
	 * @param articalId
	 * @param topId
	 * @throws Exception
	 */
	void articalTopRemove(@Param(value="topToArtical") TopToArtical topToArtical) throws Exception;
}
