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
	void batchSave(@Param("user")AdminUser user,@Param("roleIds")List<Integer> roleIds);//批量保存权限
	void batchEmpty(@Param("user")AdminUser user);
	void save(UserToRole user);//增
	void update(UserToRole user);//改
	List<UserToRoleDTO> getListByParam(UserToRole menu);//查询数组
	UserToRoleDTO getByParam(UserToRole menut);//查询单个
	int getCountByParam(UserToRole menu);//查询数量
}
