package com.qicai.service;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.ThirdUser;
import com.qicai.dto.ThirdUserDTO;
/**
 * ��������½
 * @author Administrator
 */
public interface ThirdUserService {
	ThirdUserDTO getByOpenId(String openId);
	void save(@Param(value="user")ThirdUser user);
}
