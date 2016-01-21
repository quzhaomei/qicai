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
	void batchSave(@Param("bean")Artical bean,@Param("tagIds")List<Integer> tagIds);//批量保存权限
	void batchEmpty(@Param("bean")Artical bean);
	void save(ArticalToTag bean);//增
	void update(ArticalToTag bean);//改
	List<ArticalToTagDTO> getListByParam(ArticalToTag bean);//查询数组
	ArticalToTagDTO getByParam(ArticalToTag bean);//查询单个
	int getCountByParam(ArticalToTag bean);//查询数量
}
