package com.qicai.service;

import java.util.List;

import com.qicai.bean.Advertise;
import com.qicai.dto.AdvertiseDTO;

public interface AdvertiseService {
	void save(Advertise advertise);
	void update(Advertise advertise);
	AdvertiseDTO findByCode(String code);
	List<AdvertiseDTO> findAll();
	AdvertiseDTO findById(Integer id);

}
