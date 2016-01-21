package com.qicai.service.impl;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.qicai.bean.ThirdUser;
import com.qicai.dao.ThirdUserDao;
import com.qicai.dto.ThirdUserDTO;
import com.qicai.service.ThirdUserService;
@Service
public class ThirdUserServiceImpl implements ThirdUserService {
	@Resource
	protected ThirdUserDao thirdUserDao;
	@Override
	public ThirdUserDTO getByOpenId(String openId) {
		ThirdUser param=new ThirdUser();
		param.setOpenId(openId);
		if(openId!=null){
			return thirdUserDao.getByParam(param);
		}
		return null;
	}

	@Override
	public void save(@Param("user") ThirdUser user) {
		thirdUserDao.save(user);
	}

}
