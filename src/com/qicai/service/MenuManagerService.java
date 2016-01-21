package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.MenuManager;
import com.qicai.dto.admin.MenuManagerDTO;

public interface MenuManagerService extends BaseService<MenuManager, MenuManagerDTO>{
	/**
	 * 获取所有有效的菜单
	 * @return 菜单list
	 */
	List<MenuManagerDTO> getAllMenu();
	/**
	 * 获取所有页面菜单
	 * @return 菜单list
	 */
	List<MenuManagerDTO> getNavMenu();
	
	/**
	 * 获取满足条件的菜单
	 * @return 菜单list
	 */
	List<MenuManagerDTO> getListByParam(MenuManager menu);
	
}
