package com.qicai.service;

import java.util.List;

import com.qicai.bean.ArticalResource;
import com.qicai.dto.ArticalResourceDTO;

public interface ArticalResourceService extends BaseService<ArticalResource, ArticalResourceDTO> {
	List<ArticalResourceDTO> getAllArtical();
	List<ArticalResourceDTO> getListByParam(ArticalResource resource);
	ArticalResourceDTO getArticalParam(ArticalResource resource);
}
