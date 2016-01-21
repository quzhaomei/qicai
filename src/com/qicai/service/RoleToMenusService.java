package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.RoleToMenus;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleToMenusDTO;


public interface RoleToMenusService extends BaseService<RoleToMenus, RoleToMenusDTO>{
	/**
	 * 查询所有菜单，并带有权限标志tempId，
	 * @param id 角色ID
	 * @return 有权限。则tempId为中间表ID
	 */
	List<MenuManagerDTO> getRoleMenusByRoleId(Integer id);
	/**
	 * 查询角色可用的菜单，
	 * @param id 角色ID
	 * @return 有权限。则tempId为中间表ID
	 */
	List<MenuManagerDTO> getUseFullMenusByRoleId(Integer id);
	/**
	 * 批量更新角色权限
	 * @param menuIds 需要添加的菜单ID
	 * @param tempIds 需要删除的中间表ID
	 * @param roleId 角色ID
	 */
	void batchUpdateRoleMenus(String[]menuIds,String[] tempIds,Integer roleId) throws Exception;
}
