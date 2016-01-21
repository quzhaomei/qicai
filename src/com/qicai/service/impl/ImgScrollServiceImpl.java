package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.ImgScroll;
import com.qicai.dao.ImgScrollDao;
import com.qicai.dto.ImgScrollDTO;
import com.qicai.dto.PageDTO;
import com.qicai.service.ImgScrollService;

@Service
public class ImgScrollServiceImpl implements ImgScrollService {
	@Resource
	protected ImgScrollDao imgScrollDao;
	@Override
	public void saveOrUpdate(ImgScroll bean) throws Exception {
		if(bean.getScrollId()!=null){
			imgScrollDao.update(bean);
		}else{
			imgScrollDao.save(bean);
		}
	}

	@Override
	public void delete(ImgScroll bean) {
		imgScrollDao.delete(bean);
	}

	@Override
	public ImgScrollDTO getById(Integer id) {
		if(id!=null){
			ImgScroll imgScroll=new ImgScroll();
			imgScroll.setScrollId(id);
			return imgScrollDao.getByParam(imgScroll);
		}
		return null;
	}

	@Override
	public List<ImgScrollDTO> findAll() {
		return imgScrollDao.getListByParam(new ImgScroll());
	}

	@Override
	public List<ImgScrollDTO> findListByParam(ImgScroll param) {
		return imgScrollDao.getListByParam(param);
	}

	@Override
	public List<ImgScrollDTO> getListByPage(PageDTO<ImgScroll> page) {
		return imgScrollDao.getListByPage(page);
	}
}
