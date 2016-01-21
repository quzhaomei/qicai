package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.AdminUser;
import com.qicai.bean.admin.UserToRole;
import com.qicai.dto.admin.UserToRoleDTO;

/**
 * @author Administrator
 */
public interface UserToRoleDao {
	void batchSave(@Param("user")AdminUser user,@Param("roleIds")List<Integer> roleIds);//��������Ȩ��
	void batchEmpty(@Param("user")AdminUser user);
	void save(UserToRole user);//��
	void update(UserToRole user);//��
	List<UserToRoleDTO> getListByParam(UserToRole menu);//��ѯ����
	UserToRoleDTO getByParam(UserToRole menut);//��ѯ����
	int getCountByParam(UserToRole menu);//��ѯ����
}
