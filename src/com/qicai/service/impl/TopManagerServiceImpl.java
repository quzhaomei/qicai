package com.qicai.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.TopManager;
import com.qicai.bean.TopToArtical;
import com.qicai.dao.ArticalDao;
import com.qicai.dao.TopManagerDao;
import com.qicai.dto.TopManagerDTO;
import com.qicai.service.TopManagerService;

@Service
public class TopManagerServiceImpl  implements TopManagerService {
	@Resource
	protected TopManagerDao topManagerDao;
	@Resource
	protected ArticalDao articalDao;
	@Override
	public void saveOrUpdate(TopManager bean) throws Exception {
		if(bean.getTopId()==null){
			topManagerDao.save(bean);
		}else{
			topManagerDao.update(bean);
		}
	}

	@Override
	public void delete(TopManager bean) {
		
	}

	@Override
	public TopManagerDTO getById(Integer id) {
		if(id!=null){
			TopManager top=new TopManager();
			top.setTopId(id);
			return topManagerDao.findByParam(top);
		}
		return null;
	}

	@Override
	public List<TopManagerDTO> getAllTopManager() {
		return topManagerDao.getAllTopManager();
	}

	@Override
	public void addArtical(Integer articalId, Integer topId)
			throws Exception {
		TopToArtical saveParam=new TopToArtical();
		saveParam.setArticalId(articalId);
		saveParam.setTopId(topId);
		saveParam.setTopDate(new Date());
		topManagerDao.addArtical(saveParam);
	}

	@Override
	public void articalToTop(Integer articalId, Integer topId) throws Exception {
		if(articalId!=null&&topId!=null){
			TopToArtical upParam=new TopToArtical();
			upParam.setArticalId(articalId);
			upParam.setTopId(topId);
			upParam.setTopDate(new Date());
			topManagerDao.articalToTop(upParam);
		}
	}

	@Override
	public void articalTopRemove(Integer articalId, Integer topId)
			throws Exception {
		if(articalId!=null&&topId!=null){
			TopToArtical topToArtical=new TopToArtical();
			topToArtical.setArticalId(articalId);
			topToArtical.setTopId(topId);
			topManagerDao.articalTopRemove(topToArtical);
		}
	}
}
