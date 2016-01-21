package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.RoleManager;
import com.qicai.dto.admin.RoleManagerDTO;

public interface RoleManagerDao {
	void save(@Param(value="role") RoleManager role);//��
	void update(@Param(value="role")RoleManager role);//��
	void delete(RoleManager role);//ɾ
	List<RoleManagerDTO> getListByParam(@Param(value="role")RoleManager role);//��ѯ����
	RoleManagerDTO getByParam(@Param(value="role")RoleManager role);//��ѯ����
	int getCountByParam(RoleManager role);//��ѯ����

}
