package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.ArticalTag;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.PageDTO;


public interface ArticalTagDao {
	void save(@Param(value="tag")ArticalTag tag);
	void update(@Param(value="tag")ArticalTag tag);
	List<ArticalTagDTO> getAllTag();
	List<ArticalTagDTO> getListByParam(@Param(value="tag")ArticalTag tag);
	ArticalTagDTO getTagByParam(@Param(value="tag")ArticalTag tag);
	
	List<ArticalTagDTO> getListByPage(@Param("page")PageDTO<ArticalTag> page);//查询数组
	int getCountByParam(@Param("param")ArticalTag param);//查询个数
	List<String> getBrandCharArr();
	
}
