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
	 * �����ö���������������δѡ��ָ���ö���������
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

	// �����ö�����������µ�һ����Ϣ
	MainPageArticalDTO selectedOneByParamAndTopId(Artical artical, Integer topId);
	
	//�����ö�������Ҷ�����Ϣ
	List<ArticalDTO> selectedPageByParamAndTopId(PageDTO<Artical> page,Integer id);

	// ���ݷ�ҳ��Ϣ��ȡ�ö���ǩ�µ�����
	List<ArticalDTO> selectedByParamAndTagId(PageDTO<Artical> page,
			Integer tagId);

	// ��ȡ������������
	List<ArticalDTO> selectedHotArticalByPage(PageDTO<Artical> page);
	
	// ���ݷ�ҳ��Ϣ��ȡ��ʾ����ҳ�ı�ǩ������
	List<ArticalDTO> selectedByParamAndShowTag(PageDTO<Artical> page);
	//����tagId��ȡר������
	PageDTO<List<ArticalDTO>> selectedArticalByPageAndTag(PageDTO<Artical> page
			,List<Integer> tagIds);
	
	/**
	 * ����tag type��ȡר������
	 */
	PageDTO<List<ArticalDTO>> selectedArticalByPageAndTagType(PageDTO<Artical> page
				,Integer type);
	
	// �ղ�����
	void storeArtical(StoreArtical storeArtical) throws Exception;

	// �Ƴ��ղص�����
	void deleteArtical(StoreArtical storeArtical);

	// ��ȡ�ղ������б�
	List<ArticalDTO> getStoreListByParam(StoreArtical storeArtical);

	/**
	 *  ��ȡ�ղ�����
	 * @param storeArtical
	 * @return
	 */
	ArticalDTO getStoreByParam(StoreArtical storeArtical);
	
	/**
	 * ��ȡר������������Լ����µ�һƪ����
	 */
	PageDTO<List<ArticalDTO>> getAuthorArticalByPage(PageDTO<Artical> page);
	
	//�������߽�ɫ��ȡ����
	PageDTO<List<ArticalDTO>> getPageByParamAndAuthor(PageDTO<Artical> page) ;
	
	/**
	 * ��ȡ����Id��ȡ�������
	 */
	List<ArticalDTO> getNearArticalByPage(PageDTO<Artical> page);
	
	/**
	 * ������Ƶ����ȡ ��Ƶ����
	 */
	List<ArticalDTO> getVideoArticalByPageAndType(PageDTO<Artical> page,Integer type);
	ArticalDTO getVideoArticalById(Integer id);
	
	/**
	 * �������
	 */
	PageDTO<List<ArticalDTO>> getByKey(PageDTO<Artical> page,String key);
}
