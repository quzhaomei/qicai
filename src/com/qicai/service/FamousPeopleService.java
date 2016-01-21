package com.qicai.service;

import java.util.List;

import com.qicai.bean.FamousPeople;
import com.qicai.dto.FamousPeopleDTO;
import com.qicai.dto.PageDTO;

public interface FamousPeopleService extends BaseService<FamousPeople, FamousPeopleDTO> {
	List<FamousPeopleDTO> getAllPeople();
	List<FamousPeopleDTO> getListByParam(FamousPeople people);
	FamousPeopleDTO getFamousParam(FamousPeople people);
	PageDTO<List<FamousPeopleDTO>> getListByPage(PageDTO<FamousPeople> page);//查询数组
	List<String> getCharArr();//查询首字母
}
