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
	void save(@Param("zcarMenu")ZcarMenu zcarMenu);//��
	void delete(@Param("zcarMenu")ZcarMenu zcarMenu);//��
	void update(@Param("zcarMenu")ZcarMenu zcarMenu);//��
	List<ZcarMenuDTO> getListByParam(@Param("zcarMenu")ZcarMenu zcarMenu);//��ѯ����
	ZcarMenuDTO getByParam(@Param("zcarMenu")ZcarMenu zcarMenu);//��ѯ����
}
