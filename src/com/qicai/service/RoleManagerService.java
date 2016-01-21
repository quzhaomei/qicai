package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.RoleManager;
import com.qicai.dto.admin.RoleManagerDTO;

public interface RoleManagerService extends BaseService<RoleManager, RoleManagerDTO>{
	/**
	 * 获取所有角色
	 * @return 菜单list
	 */
	List<RoleManagerDTO> getAllRole();

}
