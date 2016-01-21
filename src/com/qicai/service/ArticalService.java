package com.qicai.service;

import java.util.Date;
import java.util.List;

import com.qicai.bean.Artical;
import com.qicai.bean.StoreArtical;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.MainPageArticalDTO;
import com.qicai.dto.PageDTO;

public interface ArticalService extends BaseService<Artical, ArticalDTO> {
	PageDTO<List<ArticalDTO>> getPageByParam(PageDTO<Artical> page);

	// void saveArticalAndTag(Artical artical,List<Integer> tagIds) throws
	// Exception;
	// void updateArticalAndTag(Artical artical,List<Integer> tagIds) throws
	// Exception;
	/**
	 * 根据置顶类别和条件，查找未选定指定置顶类别的文章
	 * 
	 * @param page
	 * @return
	 */
	PageDTO<List<ArticalDTO>> chonseUnselectArticalByParamAndTop(
			PageDTO<Artical> page, Integer topId, Integer tagId,
			Date startTime, Date endTime);

	PageDTO<List<ArticalDTO>> selectedArticalByParamAndTop(
			PageDTO<Artical> page, Integer topId);

	MainPageArticalDTO selectedOneByParamAndTopName(Artical artical, String name);

	// 根据置顶分类查找最新的一条信息
	MainPageArticalDTO selectedOneByParamAndTopId(Artical artical, Integer topId);
	
	//根据置顶分类查找多条信息
	List<ArticalDTO> selectedPageByParamAndTopId(PageDTO<Artical> page,Integer id);

	// 根据分页信息获取置顶标签下的文章
	List<ArticalDTO> selectedByParamAndTagId(PageDTO<Artical> page,
			Integer tagId);

	// 获取热门文章排行
	List<ArticalDTO> selectedHotArticalByPage(PageDTO<Artical> page);
	
	// 根据分页信息获取显示在首页的标签的文章
	List<ArticalDTO> selectedByParamAndShowTag(PageDTO<Artical> page);
	//根据tagId获取专题文章
	PageDTO<List<ArticalDTO>> selectedArticalByPageAndTag(PageDTO<Artical> page
			,List<Integer> tagIds);
	
	/**
	 * 根据tag type获取专题文章
	 */
	PageDTO<List<ArticalDTO>> selectedArticalByPageAndTagType(PageDTO<Artical> page
				,Integer type);
	
	// 收藏文章
	void storeArtical(StoreArtical storeArtical) throws Exception;

	// 移除收藏的文章
	void deleteArtical(StoreArtical storeArtical);

	// 获取收藏文章列表
	List<ArticalDTO> getStoreListByParam(StoreArtical storeArtical);

	/**
	 *  获取收藏文章
	 * @param storeArtical
	 * @return
	 */
	ArticalDTO getStoreByParam(StoreArtical storeArtical);
	
	/**
	 * 获取专栏下面的作者以及最新的一篇文章
	 */
	PageDTO<List<ArticalDTO>> getAuthorArticalByPage(PageDTO<Artical> page);
	
	//根据作者角色获取文章
	PageDTO<List<ArticalDTO>> getPageByParamAndAuthor(PageDTO<Artical> page) ;
	
	/**
	 * 获取文章Id获取相近内容
	 */
	List<ArticalDTO> getNearArticalByPage(PageDTO<Artical> page);
	
	/**
	 * 根据视频类别获取 视频文章
	 */
	List<ArticalDTO> getVideoArticalByPageAndType(PageDTO<Artical> page,Integer type);
	ArticalDTO getVideoArticalById(Integer id);
	
	/**
	 * 搜索结果
	 */
	PageDTO<List<ArticalDTO>> getByKey(PageDTO<Artical> page,String key);
}
