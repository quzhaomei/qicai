package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.FriendHref;
import com.qicai.dao.FriendHrefDao;
import com.qicai.dto.FriendHrefDTO;
import com.qicai.service.FriendHrefService;

@Service
public class FriendHrefServiceImpl implements FriendHrefService{
	@Resource
	protected FriendHrefDao friendHrefDao;
	
	@Override
	public void saveOrUpdate(FriendHref bean) throws Exception {
		if(bean.getHrefId()!=null){
			friendHrefDao.update(bean);
		}else{
			friendHrefDao.save(bean);
		}
	}

	@Override
	public void delete(FriendHref bean) {
		
	}

	@Override
	public FriendHrefDTO getById(Integer id) {
		if(id!=null){
			FriendHref param=new FriendHref();
			param.setHrefId(id);
			return friendHrefDao.getByParam(param);
		}
		return null;
	}

	@Override
	public List<FriendHrefDTO> findAll() {
		return friendHrefDao.getListByParam(new FriendHref());
	}

	@Override
	public List<FriendHrefDTO> findListByParam(FriendHref bean) {
		return friendHrefDao.getListByParam(bean);
	}

	@Override
	public FriendHrefDTO findByParam(FriendHref bean) {
		return friendHrefDao.getByParam(bean);
	}

}
