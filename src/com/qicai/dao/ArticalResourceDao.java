package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.ArticalResource;
import com.qicai.dto.ArticalResourceDTO;


public interface ArticalResourceDao {
	void save(@Param(value="resource")ArticalResource resource);
	void update(@Param(value="resource")ArticalResource resource);
	List<ArticalResourceDTO> getAllArtical();
	List<ArticalResourceDTO> getListByParam(@Param(value="resource")ArticalResource resource);
	ArticalResourceDTO getArticalByParam(@Param(value="resource")ArticalResource resource);
}
