package com.qicai.service;

import java.util.List;

import com.qicai.bean.ImgScroll;
import com.qicai.dto.ImgScrollDTO;
import com.qicai.dto.PageDTO;

public interface ImgScrollService extends BaseService<ImgScroll, ImgScrollDTO> {
	List<ImgScrollDTO> findAll();
	List<ImgScrollDTO> findListByParam(ImgScroll param);
	List<ImgScrollDTO> getListByPage(PageDTO<ImgScroll> page);//²éÑ¯Êý×é
}
