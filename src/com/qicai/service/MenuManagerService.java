package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.MenuManager;
import com.qicai.dto.admin.MenuManagerDTO;

public interface MenuManagerService extends BaseService<MenuManager, MenuManagerDTO>{
	/**
	 * ��ȡ������Ч�Ĳ˵�
	 * @return �˵�list
	 */
	List<MenuManagerDTO> getAllMenu();
	/**
	 * ��ȡ����ҳ��˵�
	 * @return �˵�list
	 */
	List<MenuManagerDTO> getNavMenu();
	
	/**
	 * ��ȡ���������Ĳ˵�
	 * @return �˵�list
	 */
	List<MenuManagerDTO> getListByParam(MenuManager menu);
	
}
