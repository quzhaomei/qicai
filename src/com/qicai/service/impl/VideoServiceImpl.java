package com.qicai.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.Video;
import com.qicai.dao.VideoDao;
import com.qicai.dto.VideoDTO;
import com.qicai.service.VideoService;
@Service
public class VideoServiceImpl implements VideoService{
	@Resource
	protected VideoDao videoDao;
	
	@Override
	public void saveOrUpdate(Video bean) throws Exception {
		if(bean.getVideoId()!=null){
			videoDao.update(bean);
		}else{
			videoDao.save(bean);
		}
	}

	@Override
	public void delete(Video bean) {
	}

	@Override
	public VideoDTO getById(Integer id) {
		if(id!=null){
			Video bean=new Video();
			bean.setVideoId(id);
			return videoDao.getByParam(bean);
		}
		return null;
	}

}
