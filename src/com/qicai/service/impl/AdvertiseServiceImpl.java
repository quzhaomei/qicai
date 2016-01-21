package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.Advertise;
import com.qicai.dao.AdvertiseDao;
import com.qicai.dto.AdvertiseDTO;
import com.qicai.service.AdvertiseService;

@Service(value="advertiseService")
public class AdvertiseServiceImpl implements AdvertiseService {
	@Resource
	protected AdvertiseDao adDao;
	@Override
	public void save(Advertise advertise) {
		adDao.save(advertise);
	}

	@Override
	public void update(Advertise advertise) {
		adDao.update(advertise);
	}

	@Override
	public List<AdvertiseDTO> findAll() {
		return adDao.findAll();
	}

	@Override
	public AdvertiseDTO findByCode(String code) {
		return adDao.findByCode(code);
	}

	@Override
	public AdvertiseDTO findById(Integer id) {
		return adDao.findById(id);
	}

}
