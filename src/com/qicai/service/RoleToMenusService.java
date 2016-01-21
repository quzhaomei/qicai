package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.RoleToMenus;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleToMenusDTO;


public interface RoleToMenusService extends BaseService<RoleToMenus, RoleToMenusDTO>{
	/**
	 * ��ѯ���в˵���������Ȩ�ޱ�־tempId��
	 * @param id ��ɫID
	 * @return ��Ȩ�ޡ���tempIdΪ�м��ID
	 */
	List<MenuManagerDTO> getRoleMenusByRoleId(Integer id);
	/**
	 * ��ѯ��ɫ���õĲ˵���
	 * @param id ��ɫID
	 * @return ��Ȩ�ޡ���tempIdΪ�м��ID
	 */
	List<MenuManagerDTO> getUseFullMenusByRoleId(Integer id);
	/**
	 * �������½�ɫȨ��
	 * @param menuIds ��Ҫ��ӵĲ˵�ID
	 * @param tempIds ��Ҫɾ�����м��ID
	 * @param roleId ��ɫID
	 */
	void batchUpdateRoleMenus(String[]menuIds,String[] tempIds,Integer roleId) throws Exception;
}
