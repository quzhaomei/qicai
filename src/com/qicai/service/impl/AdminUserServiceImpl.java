package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.admin.AdminUser;
import com.qicai.bean.admin.RoleManager;
import com.qicai.dao.AdminUserDao;
import com.qicai.dao.RoleManagerDao;
import com.qicai.dao.UserToRoleDao;
import com.qicai.dto.PageDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.service.AdminUserService;

@Service(value="adminUserService")
public class AdminUserServiceImpl implements AdminUserService {
	@Resource
	protected AdminUserDao adminUserDao;
	@Resource
	protected UserToRoleDao userToRoleDao;
	@Resource
	protected RoleManagerDao roleManagerDao;

	@Override
	public void saveUserAndRole(AdminUser user, List<Integer> roleIds)
			throws Exception {
		// 验证数据，
		if (roleIds != null) {
			for (Integer roleId : roleIds) {
				RoleManager role = new RoleManager();
				role.setRoleId(roleId);
				RoleManagerDTO roleManagerDTO = roleManagerDao.getByParam(role);
				if (roleManagerDTO == null) {
					throw new Exception("角色不存在");
				}
			}
		}
		if (user.getAdminUserId() == null) {// 保存
			// 验证数据，登录名，电话号码，邮箱，不能重复
			adminUserDao.save(user);
			if(roleIds!=null&&roleIds.size()>0){
				userToRoleDao.batchSave(user, roleIds);
			}
		}
	}

	@Override
	public void updateUserAndRole(AdminUser user, List<Integer> roleIds)
			throws Exception {
		// 验证数据，
		if (roleIds != null) {
			for (Integer roleId : roleIds) {
				RoleManager role = new RoleManager();
				role.setRoleId(roleId);
				RoleManagerDTO roleManagerDTO = roleManagerDao.getByParam(role);
				if (roleManagerDTO == null) {
					throw new Exception("角色不存在");
				}
			}
		}
		if (user.getAdminUserId() != null) {// 保存
			adminUserDao.update(user);
			if(roleIds!=null){
			//清空用户的所有角色，
			userToRoleDao.batchEmpty(user);
			if(roleIds.size()>0){
				//再保存
				userToRoleDao.batchSave(user, roleIds);
			}
			}
		} 
	}

	@Override
	public PageDTO<List<AdminUserDTO>> findPageDateByPageParam(
			PageDTO<AdminUser> page) {
		List<AdminUserDTO> dateList = adminUserDao.getListByPage(page);
		PageDTO<List<AdminUserDTO>> pageDate = new PageDTO<List<AdminUserDTO>>();
		pageDate.setParam(dateList);
		pageDate.setPageIndex(page.getPageIndex());
		pageDate.setPageSize(page.getPageSize());
		Integer count = adminUserDao.getCountByParam(page.getParam());
		count = count % page.getPageSize() == 0 ? count / page.getPageSize()
				: count / page.getPageSize() + 1;
		pageDate.setTotalPage(count);
		return pageDate;
	}

	@Override
	public void saveOrUpdate(AdminUser bean) {
		if(bean.getAdminUserId()==null){
			adminUserDao.save(bean);
		}else{
			adminUserDao.update(bean);
		}
	}

	@Override
	public void delete(AdminUser bean) {
	}

	@Override
	public AdminUserDTO getById(Integer id) {
		if(id==null){
			return null;
		}
		AdminUser adminUser = new AdminUser();
		adminUser.setAdminUserId(id);
		return adminUserDao.getByParam(adminUser);
	}

	@Override
	public int checkUserCount(AdminUser user) {
		return adminUserDao.checkUserCount(user);
	}

	@Override
	public AdminUserDTO getUserByParam(AdminUser adminUser) {
		return adminUserDao.getByParam(adminUser);
	}

	@Override
	public AdminUserDTO mengHuLogin(AdminUser adminUser) {
		return adminUserDao.mengHuLogin(adminUser);
	}

}
