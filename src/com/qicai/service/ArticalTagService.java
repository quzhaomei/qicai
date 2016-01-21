package com.qicai.service;

import java.util.List;

import com.qicai.bean.ArticalTag;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.PageDTO;

public interface ArticalTagService extends BaseService<ArticalTag, ArticalTagDTO>{
	List<ArticalTagDTO> getAllTag();
	List<ArticalTagDTO> getListByParam(ArticalTag tag);
	ArticalTagDTO getArticalParam(ArticalTag tag);
	PageDTO<List<ArticalTagDTO>> getListByPage(PageDTO<ArticalTag> page);//²éÑ¯Êý×é
	
	List<String> getBrandCharArr();
}
