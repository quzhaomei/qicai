package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.RoleToMenus;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleToMenusDTO;

public interface RoleToMenusDao {
	void save(@Param(value="roleToMenus") RoleToMenus roleToMenus);//��
	void update(@Param(value="roleToMenus")RoleToMenus roleToMenus);//��
	List<MenuManagerDTO> getMenuListByParam(@Param(value="roleToMenus") RoleToMenus roleToMenus);//��ѯ����
	List<MenuManagerDTO> getMenuListByRole(@Param(value="roleToMenus") RoleToMenus roleToMenus);//��ѯ����
	RoleToMenusDTO getByParam(@Param(value="roleToMenus")RoleToMenus roleToMenus);//��ѯ����
	int getCountByParam(RoleToMenus roleToMenus);//��ѯ����
	void delBatch(@Param(value="tempIds")List<Integer> tempIds,@Param(value="roleId")Integer roleId);//����ɾ��
	void saveBatch(@Param(value="menuIds")List<Integer> menuIds,@Param(value="roleId")Integer roleId);//��������
}
