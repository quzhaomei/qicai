package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.ImgScroll;
import com.qicai.dto.ImgScrollDTO;
import com.qicai.dto.PageDTO;

public interface ImgScrollDao {
	ImgScrollDTO getByParam(@Param(value="scroll")ImgScroll scroll);
	void save(@Param(value="scroll")ImgScroll scroll);
	void update(@Param(value="scroll")ImgScroll scroll);
	void delete(@Param(value="scroll")ImgScroll scroll);
	List<ImgScrollDTO> getListByParam(@Param(value="scroll")ImgScroll scroll);
	
	List<ImgScrollDTO> getListByPage(@Param("page")PageDTO<ImgScroll> page);//²éÑ¯Êý×é
}
