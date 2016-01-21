package com.qicai.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.Artical;
import com.qicai.bean.StoreArtical;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.MainPageArticalDTO;
import com.qicai.dto.PageDTO;


public interface ArticalDao {
	void save(@Param(value="artical")Artical artical);
	void update(@Param(value="artical")Artical artical);
	/**
	 * 根据ID数组 批量查询
	 * @param ids
	 * @return
	 */
	@Deprecated
	List<ArticalDTO> getListByIds(@Param(value="ids")List<Integer> ids);
	
	List<ArticalDTO> getListByPage(@Param(value="page")PageDTO<Artical> page);
	
	/**
	 * 根据作者角色查找文章
	 */
	List<ArticalDTO> getListByPageAndAuthor(@Param(value="page")PageDTO<Artical> page);
	/**
	 * 根据作者角色查找文章数量
	 */
	int getCountByParamAndAuthor(@Param(value="artical")Artical page);
	
	
	ArticalDTO getArticalByParam(@Param(value="artical")Artical artical);
	
	int getCountByParam(@Param("artical")Artical artical);//查询数量
	List<ArticalDTO> chonseUnselectArticalByParamAndTop(
			@Param(value="page")PageDTO<Artical> page,@Param(value="topId") Integer topId,
			@Param(value="tagId") Integer tagId
			,@Param(value="start")Date startTime,@Param(value="end")Date endTime);
	int chonseUnselectCountByParamAndTop(
			@Param(value="page")PageDTO<Artical> page,@Param(value="topId") Integer topId,
			@Param(value="tagId") Integer tagId
			,@Param(value="start")Date startTime,@Param(value="end")Date endTime);
	
	List<ArticalDTO> selectedArticalByParamAndTop
	(@Param(value="page")PageDTO<Artical> page,@Param(value="topId")Integer topId);
	int selectedCountByParamAndTop
	(@Param(value="page")PageDTO<Artical> page,@Param(value="topId")Integer topId);
	
	MainPageArticalDTO selectedOneByParamAndTopName(@Param(value="artical")Artical artical,
			@Param(value="name")String name);
	
	
	MainPageArticalDTO selectedOneByParamAndTopId(@Param(value="artical")Artical artical,
					@Param(value="id")Integer id);
	
	//查询多条头条
	List<ArticalDTO> selectedPageByParamAndTopId(@Param(value="page")PageDTO<Artical> page,
			@Param(value="id")Integer id);
	
	List<ArticalDTO> selectedByParamAndTagId(@Param(value="page")PageDTO<Artical> page,
			@Param(value="tagId")Integer tagId);
	
	/**
	 *根据类别 状态查找最热门的文章
	 */
	List<ArticalDTO> selectedHotArticalByPage(@Param(value="page")PageDTO<Artical> page);

	
	/**
	 * 根据标签类别获取文章
	 */
	List<ArticalDTO> selectedArticalByPageAndTagType(@Param(value="page")PageDTO<Artical> page
					,@Param(value="type")Integer type);
	/**
	 * 获取数目
	 */
	int selectedCountByPageAndTagType(@Param(value="artical")Artical artical
			,@Param(value="type")Integer type);
	
	List<ArticalDTO> selectedByParamAndShowTag(@Param(value="page")PageDTO<Artical> page);
	/**
	 *根据类别 状态 标签查找最热门的文章
	 */
	List<ArticalDTO> selectedArticalByPageAndTag(@Param(value="page")PageDTO<Artical> page
			,@Param(value="tagIds")List<Integer> tagIds);
	/**
	 *根据类别 状态 标签查找最热门的文章数量
	 */
	int selectedArticalCountByTag(@Param(value="artical")Artical artical
			,@Param(value="tagIds")List<Integer> tagIds);
	
	//收藏文章
	void storeArtical(@Param(value="store")StoreArtical storeArtical);
	//移除文章
	void deleteArtical(@Param(value="store")StoreArtical storeArtical);
	//获取收藏文章列表
	List<ArticalDTO> getStoreListByParam(@Param(value="store")StoreArtical storeArtical);
	//获取收藏文章
	ArticalDTO getStoreByParam(@Param(value="store")StoreArtical storeArtical);
	
	/**
	 * 获取指定作者的文章
	 */
	List<ArticalDTO> getAuthorArticalByPage(@Param(value="page")PageDTO<Artical> page);
	
	/**
	 * 获取相近的文章Id,根据type tagIds
	 */
	List<ArticalDTO> getNearArticalByPage(@Param(value="page")PageDTO<Artical> page);
	
	/**
	 * 根据视频类别获取 视频文章
	 */
	List<ArticalDTO> getVideoArticalByPageAndType(@Param(value="page")PageDTO<Artical> page,
			@Param(value="type")Integer type);
	
	/***根据视频类别获取 视频文章*/
	ArticalDTO getVideoArticalById(@Param(value="id")Integer id);
	
	/**
	 * 根据关键字搜索
	 */
	List<ArticalDTO> getByKey(@Param(value="page")PageDTO<Artical> page,@Param(value="key")String key);
	/**
	 * 根据关键字,总共条数
	 */
	int getCountByKey(@Param(value="Artical")Artical artical,@Param(value="key")String key);
	
}
