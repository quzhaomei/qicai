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
	 * ����ID���� ������ѯ
	 * @param ids
	 * @return
	 */
	@Deprecated
	List<ArticalDTO> getListByIds(@Param(value="ids")List<Integer> ids);
	
	List<ArticalDTO> getListByPage(@Param(value="page")PageDTO<Artical> page);
	
	/**
	 * �������߽�ɫ��������
	 */
	List<ArticalDTO> getListByPageAndAuthor(@Param(value="page")PageDTO<Artical> page);
	/**
	 * �������߽�ɫ������������
	 */
	int getCountByParamAndAuthor(@Param(value="artical")Artical page);
	
	
	ArticalDTO getArticalByParam(@Param(value="artical")Artical artical);
	
	int getCountByParam(@Param("artical")Artical artical);//��ѯ����
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
	
	//��ѯ����ͷ��
	List<ArticalDTO> selectedPageByParamAndTopId(@Param(value="page")PageDTO<Artical> page,
			@Param(value="id")Integer id);
	
	List<ArticalDTO> selectedByParamAndTagId(@Param(value="page")PageDTO<Artical> page,
			@Param(value="tagId")Integer tagId);
	
	/**
	 *������� ״̬���������ŵ�����
	 */
	List<ArticalDTO> selectedHotArticalByPage(@Param(value="page")PageDTO<Artical> page);

	
	/**
	 * ���ݱ�ǩ����ȡ����
	 */
	List<ArticalDTO> selectedArticalByPageAndTagType(@Param(value="page")PageDTO<Artical> page
					,@Param(value="type")Integer type);
	/**
	 * ��ȡ��Ŀ
	 */
	int selectedCountByPageAndTagType(@Param(value="artical")Artical artical
			,@Param(value="type")Integer type);
	
	List<ArticalDTO> selectedByParamAndShowTag(@Param(value="page")PageDTO<Artical> page);
	/**
	 *������� ״̬ ��ǩ���������ŵ�����
	 */
	List<ArticalDTO> selectedArticalByPageAndTag(@Param(value="page")PageDTO<Artical> page
			,@Param(value="tagIds")List<Integer> tagIds);
	/**
	 *������� ״̬ ��ǩ���������ŵ���������
	 */
	int selectedArticalCountByTag(@Param(value="artical")Artical artical
			,@Param(value="tagIds")List<Integer> tagIds);
	
	//�ղ�����
	void storeArtical(@Param(value="store")StoreArtical storeArtical);
	//�Ƴ�����
	void deleteArtical(@Param(value="store")StoreArtical storeArtical);
	//��ȡ�ղ������б�
	List<ArticalDTO> getStoreListByParam(@Param(value="store")StoreArtical storeArtical);
	//��ȡ�ղ�����
	ArticalDTO getStoreByParam(@Param(value="store")StoreArtical storeArtical);
	
	/**
	 * ��ȡָ�����ߵ�����
	 */
	List<ArticalDTO> getAuthorArticalByPage(@Param(value="page")PageDTO<Artical> page);
	
	/**
	 * ��ȡ���������Id,����type tagIds
	 */
	List<ArticalDTO> getNearArticalByPage(@Param(value="page")PageDTO<Artical> page);
	
	/**
	 * ������Ƶ����ȡ ��Ƶ����
	 */
	List<ArticalDTO> getVideoArticalByPageAndType(@Param(value="page")PageDTO<Artical> page,
			@Param(value="type")Integer type);
	
	/***������Ƶ����ȡ ��Ƶ����*/
	ArticalDTO getVideoArticalById(@Param(value="id")Integer id);
	
	/**
	 * ���ݹؼ�������
	 */
	List<ArticalDTO> getByKey(@Param(value="page")PageDTO<Artical> page,@Param(value="key")String key);
	/**
	 * ���ݹؼ���,�ܹ�����
	 */
	int getCountByKey(@Param(value="Artical")Artical artical,@Param(value="key")String key);
	
}
