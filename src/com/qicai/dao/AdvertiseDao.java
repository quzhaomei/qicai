package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.Advertise;
import com.qicai.dto.AdvertiseDTO;

public interface AdvertiseDao {
	void save(@Param(value="advertise")Advertise advertise);
	void update(@Param(value="advertise")Advertise advertise);
	List<AdvertiseDTO> findAll();
	AdvertiseDTO findByCode(@Param(value="code")String code);
	AdvertiseDTO findById(@Param(value="id")Integer id);
}
