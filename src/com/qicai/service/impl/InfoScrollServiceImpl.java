package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.InfoScroll;
import com.qicai.dao.InfoScrollDao;
import com.qicai.dto.InfoScrollDTO;
import com.qicai.dto.PageDTO;
import com.qicai.service.InfoScrollService;

@Service
public class InfoScrollServiceImpl implements InfoScrollService {
	@Resource
	protected InfoScrollDao infoScrollDao;
	@Override
	public void saveOrUpdate(InfoScroll bean) throws Exception {
		if(bean.getScrollId()!=null){
			infoScrollDao.update(bean);
		}else{
			infoScrollDao.save(bean);
		}
	}

	@Override
	public void delete(InfoScroll bean) {
		infoScrollDao.delete(bean);
	}

	@Override
	public InfoScrollDTO getById(Integer id) {
		if(id!=null){
			InfoScroll InfoScroll=new InfoScroll();
			InfoScroll.setScrollId(id);
			return infoScrollDao.getByParam(InfoScroll);
		}
		return null;
	}

	@Override
	public List<InfoScrollDTO> findAll() {
		return infoScrollDao.getListByParam(new InfoScroll());
	}

	@Override
	public List<InfoScrollDTO> findListByParam(InfoScroll param) {
		return infoScrollDao.getListByParam(param);
	}

	@Override
	public List<InfoScrollDTO> getListByPage(PageDTO<InfoScroll> page) {
		return infoScrollDao.getListByPage(page);
	}
}
