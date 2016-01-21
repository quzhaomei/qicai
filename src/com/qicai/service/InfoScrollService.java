package com.qicai.service;

import java.util.List;

import com.qicai.bean.InfoScroll;
import com.qicai.dto.InfoScrollDTO;
import com.qicai.dto.PageDTO;

public interface InfoScrollService extends BaseService<InfoScroll, InfoScrollDTO> {
	List<InfoScrollDTO> findAll();
	List<InfoScrollDTO> findListByParam(InfoScroll param);
	List<InfoScrollDTO> getListByPage(PageDTO<InfoScroll> page);//²éÑ¯Êý×é
}
