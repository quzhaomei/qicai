package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.FamousPeople;
import com.qicai.dto.FamousPeopleDTO;
import com.qicai.dto.PageDTO;


public interface FamousPeopleDao {
	void save(@Param(value="people")FamousPeople people);
	void update(@Param(value="people")FamousPeople people);
	List<FamousPeopleDTO> getAllPeople();
	List<FamousPeopleDTO> getListByParam(@Param(value="people")FamousPeople people);
	FamousPeopleDTO getPeopleByParam(@Param(value="people")FamousPeople people);
	List<FamousPeopleDTO> getListByPage(@Param("page")PageDTO<FamousPeople> page);//��ѯ����
	int getCountByParam(@Param("people")FamousPeople people);//��ѯ����
	List<String> getCharArr();//��ѯ����ĸ
}
