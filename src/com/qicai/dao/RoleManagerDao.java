package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.RoleManager;
import com.qicai.dto.admin.RoleManagerDTO;

public interface RoleManagerDao {
	void save(@Param(value="role") RoleManager role);//增
	void update(@Param(value="role")RoleManager role);//改
	void delete(RoleManager role);//删
	List<RoleManagerDTO> getListByParam(@Param(value="role")RoleManager role);//查询数组
	RoleManagerDTO getByParam(@Param(value="role")RoleManager role);//查询单个
	int getCountByParam(RoleManager role);//查询数量

}
