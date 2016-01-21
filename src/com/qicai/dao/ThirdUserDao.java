package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.ThirdUser;
import com.qicai.dto.PageDTO;
import com.qicai.dto.ThirdUserDTO;
/**
 * 第三方登陆
 * @author Administrator
 */
public interface ThirdUserDao {
	ThirdUserDTO getByParam(@Param(value="user")ThirdUser user);
	void save(@Param(value="user")ThirdUser user);
	void update(@Param(value="user")ThirdUser user);
	void delete(@Param(value="user")ThirdUser user);
	List<ThirdUserDTO> getListByParam(@Param(value="user")ThirdUser user);
	List<ThirdUserDTO> getListByPage(@Param("user")PageDTO<ThirdUser> page);//查询数组
}
