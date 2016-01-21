package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.Artical;
import com.qicai.bean.ArticalToTag;
import com.qicai.dto.ArticalToTagDTO;

/**
 * @author Administrator
 */
public interface ArticalToTagDao {
	void batchSave(@Param("bean")Artical bean,@Param("tagIds")List<Integer> tagIds);//��������Ȩ��
	void batchEmpty(@Param("bean")Artical bean);
	void save(ArticalToTag bean);//��
	void update(ArticalToTag bean);//��
	List<ArticalToTagDTO> getListByParam(ArticalToTag bean);//��ѯ����
	ArticalToTagDTO getByParam(ArticalToTag bean);//��ѯ����
	int getCountByParam(ArticalToTag bean);//��ѯ����
}
