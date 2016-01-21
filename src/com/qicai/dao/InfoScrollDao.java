package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.InfoScroll;
import com.qicai.dto.InfoScrollDTO;
import com.qicai.dto.PageDTO;

public interface InfoScrollDao {
	InfoScrollDTO getByParam(@Param(value="scroll")InfoScroll scroll);
	void save(@Param(value="scroll")InfoScroll scroll);
	void update(@Param(value="scroll")InfoScroll scroll);
	void delete(@Param(value="scroll")InfoScroll scroll);
	List<InfoScrollDTO> getListByParam(@Param(value="scroll")InfoScroll scroll);
	
	List<InfoScrollDTO> getListByPage(@Param("page")PageDTO<InfoScroll> page);//²éÑ¯Êý×é
}
