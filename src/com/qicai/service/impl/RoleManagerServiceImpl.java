package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.admin.RoleManager;
import com.qicai.dao.RoleManagerDao;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.service.RoleManagerService;
@Service
public class RoleManagerServiceImpl implements RoleManagerService{
	@Resource
	protected RoleManagerDao roleManagerDao;
	@Override
	public void saveOrUpdate(RoleManager bean) {
		Integer id=bean.getRoleId();
		if(id==null){
			roleManagerDao.save(bean);
		}else{
			roleManagerDao.update(bean);
		}
	}

	@Override
	public void delete(RoleManager bean) {
		
	}

	@Override
	public RoleManagerDTO getById(Integer id) {
		if(id==null){
			return null;
		}
		RoleManager manager=new RoleManager();
		manager.setRoleId(id);
		return roleManagerDao.getByParam(manager);
	}

	@Override
	public List<RoleManagerDTO> getAllRole() {
		return roleManagerDao.getListByParam(new RoleManager());
	}

}
