package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.ZcarMenu;
import com.qicai.dto.ZcarMenuDTO;

/**
 *
 * @author Administrator
 *
 */
public interface ZcarMenuDao {
	void save(@Param("zcarMenu")ZcarMenu zcarMenu);//增
	void delete(@Param("zcarMenu")ZcarMenu zcarMenu);//增
	void update(@Param("zcarMenu")ZcarMenu zcarMenu);//改
	List<ZcarMenuDTO> getListByParam(@Param("zcarMenu")ZcarMenu zcarMenu);//查询数组
	ZcarMenuDTO getByParam(@Param("zcarMenu")ZcarMenu zcarMenu);//查询单个
}
