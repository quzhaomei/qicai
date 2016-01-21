package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.ZcarMenu;
import com.qicai.dao.ZcarMenuDao;
import com.qicai.dto.ZcarMenuDTO;
import com.qicai.service.ZcarMenuService;
@Service
public class ZcarMenuServiceImpl implements ZcarMenuService{
	@Resource
	protected ZcarMenuDao zcarMenuDao;
	@Override
	public void saveOrUpdate(ZcarMenu bean) {
		if(bean.getMenuId()!=null){
			zcarMenuDao.update(bean);
		}else{
			zcarMenuDao.save(bean);
		}
	}

	@Override
	public void delete(ZcarMenu bean) {
		zcarMenuDao.delete(bean);
	}

	@Override
	public ZcarMenuDTO getById(Integer id) {
		if(id==null){
			return null;
		}
		ZcarMenu zcarMenu=new ZcarMenu();
		zcarMenu.setMenuId(id);
		return zcarMenuDao.getByParam(zcarMenu);
	}

	@Override
	public List<ZcarMenuDTO> getMenuByParentId(Integer parentId) {
		ZcarMenu zcarMenu=new ZcarMenu();
		zcarMenu.setParentId(parentId);
		return zcarMenuDao.getListByParam(zcarMenu);
	}

	@Override
	public List<ZcarMenuDTO> getAllMenu() {
		return zcarMenuDao.getListByParam(new ZcarMenu());
	}

	@Override
	public ZcarMenuDTO getMenuByParam(ZcarMenu zcarMenu) {
		return zcarMenuDao.getByParam(zcarMenu);
	}

	@Override
	public List<ZcarMenuDTO> getMainMenu() {
		ZcarMenu param=new ZcarMenu();
		param.setParentId(-1);
		return zcarMenuDao.getListByParam(param);
	}

}
