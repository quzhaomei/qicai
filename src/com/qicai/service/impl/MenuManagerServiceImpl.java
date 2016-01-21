package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.admin.MenuManager;
import com.qicai.dao.MenuManagerDao;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.service.MenuManagerService;
@Service
public class MenuManagerServiceImpl implements MenuManagerService{
	@Resource
	protected MenuManagerDao menuManagerDao;
	@Override
	public void saveOrUpdate(MenuManager bean) {
		if(bean!=null){
			if(bean.getMenuId()!=null){
				menuManagerDao.update(bean);
			}else{
				menuManagerDao.save(bean);
			}
		}
	}

	@Override
	public void delete(MenuManager bean) {
		menuManagerDao.delete(bean);
	}

	@Override
	public MenuManagerDTO getById(Integer id) {
		if(id==null){
			return null;
		}
		MenuManager menu=new MenuManager();
		menu.setMenuId(id);
		return menuManagerDao.getByParam(menu);
	}

	@Override
	public List<MenuManagerDTO> getAllMenu() {
		MenuManager menu=new MenuManager();
		menu.setStatus(1);
		return menuManagerDao.getListByParam(menu);
	}

	@Override
	public List<MenuManagerDTO> getNavMenu() {
		return menuManagerDao.getNavMenu();
	}

	@Override
	public List<MenuManagerDTO> getListByParam(MenuManager menu) {
		
		return menuManagerDao.getListByParam(menu);
	}

}
