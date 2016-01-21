package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.RoleToMenus;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleToMenusDTO;

public interface RoleToMenusDao {
	void save(@Param(value="roleToMenus") RoleToMenus roleToMenus);//增
	void update(@Param(value="roleToMenus")RoleToMenus roleToMenus);//改
	List<MenuManagerDTO> getMenuListByParam(@Param(value="roleToMenus") RoleToMenus roleToMenus);//查询数组
	List<MenuManagerDTO> getMenuListByRole(@Param(value="roleToMenus") RoleToMenus roleToMenus);//查询数组
	RoleToMenusDTO getByParam(@Param(value="roleToMenus")RoleToMenus roleToMenus);//查询单个
	int getCountByParam(RoleToMenus roleToMenus);//查询数量
	void delBatch(@Param(value="tempIds")List<Integer> tempIds,@Param(value="roleId")Integer roleId);//批量删除
	void saveBatch(@Param(value="menuIds")List<Integer> menuIds,@Param(value="roleId")Integer roleId);//批量增加
}
