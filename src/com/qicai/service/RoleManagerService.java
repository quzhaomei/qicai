package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.RoleManager;
import com.qicai.dto.admin.RoleManagerDTO;

public interface RoleManagerService extends BaseService<RoleManager, RoleManagerDTO>{
	/**
	 * ��ȡ���н�ɫ
	 * @return �˵�list
	 */
	List<RoleManagerDTO> getAllRole();

}
