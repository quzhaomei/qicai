package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.ArticalResource;
import com.qicai.dao.ArticalResourceDao;
import com.qicai.dto.ArticalResourceDTO;
import com.qicai.service.ArticalResourceService;

@Service
public class ArticalResourceServiceImpl implements ArticalResourceService {
	@Resource
	private ArticalResourceDao resourceDao;
	@Override
	public void saveOrUpdate(ArticalResource resource) {
		if(resource.getResourceId()!=null){
			resourceDao.update(resource);
		}else{
			resourceDao.save(resource);
		}
	}

	@Override
	public List<ArticalResourceDTO> getAllArtical() {
		return resourceDao.getAllArtical();
	}

	@Override
	public List<ArticalResourceDTO> getListByParam(ArticalResource resource) {
		return resourceDao.getListByParam(resource);
	}

	@Override
	public ArticalResourceDTO getArticalParam(ArticalResource resource) {
		return resourceDao.getArticalByParam(resource);
	}

	@Override
	public void delete(ArticalResource bean) {
		// TODO Auto-generated method stub
	}

	@Override
	public ArticalResourceDTO getById(Integer id) {
		if(id==null){
			return null;
		}
		ArticalResource articalResource=new ArticalResource();
		articalResource.setResourceId(id);
		return resourceDao.getArticalByParam(articalResource);
	}


}
