package com.qicai.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.admin.MenuManager;
import com.qicai.bean.admin.RoleToMenus;
import com.qicai.dao.MenuManagerDao;
import com.qicai.dao.RoleToMenusDao;
import com.qicai.dto.admin.MenuManagerDTO;
import com.qicai.dto.admin.RoleToMenusDTO;
import com.qicai.service.RoleToMenusService;

@Service
public class RoleToMenusServiceImpl implements RoleToMenusService {
	@Resource
	protected RoleToMenusDao roleToMenusDao;
	@Resource
	protected MenuManagerDao menuDao;
	@Override
	public void saveOrUpdate(RoleToMenus bean) {
		if(bean.getRoleId()!=null){
			roleToMenusDao.update(bean);
		}else{
			roleToMenusDao.save(bean);
		}
		
	}

	@Override
	public void delete(RoleToMenus bean) {
	}

	@Override
	public RoleToMenusDTO getById(Integer id) {
		return null;
	}

	@Override
	public List<MenuManagerDTO> getRoleMenusByRoleId(Integer id) {
		RoleToMenus roleToMenus=new RoleToMenus();
		roleToMenus.setRoleId(id);
		return roleToMenusDao.getMenuListByParam(roleToMenus);
	}

	@Override
	public void batchUpdateRoleMenus(String[] menuIds, String[] tempIds,
			Integer roleId) throws Exception {
		boolean dateCheck=true;
		List<Integer>menuIdList=null;
		List<Integer>tempIdList=null;
		//���ݼ��
			if(menuIds!=null){
				menuIdList=new ArrayList<Integer>();
				for(String menuId:menuIds){
					if(menuId.matches("\\d+")){
						Integer menuIdInt=Integer.parseInt(menuId);
						menuIdList.add(menuIdInt);
						
						MenuManager manager=new MenuManager();
						manager.setMenuId(menuIdInt);
						
						MenuManagerDTO tempMenu=menuDao.getByParam(manager);
						if(tempMenu==null){//��֤�Ƿ����
							dateCheck=false;
							break;
						}
						//��֤�Ƿ��Ѿ������
						RoleToMenus roleToMenus=new RoleToMenus();
						roleToMenus.setMenuId(menuIdInt);
						roleToMenus.setRoleId(roleId);
						RoleToMenusDTO roleToMenusDTO=roleToMenusDao.getByParam(roleToMenus);
						if(roleToMenusDTO!=null){//���������������ݴ���
							dateCheck=false;
							break;
						}
					}
				}
			}
			if(tempIds!=null){
				tempIdList=new ArrayList<Integer>();
				for(String tempId:tempIds){
					if(tempId.matches("\\d+")){
						Integer tempIdInt=Integer.parseInt(tempId);
						tempIdList.add(tempIdInt);
						
						RoleToMenus roleToMenus=new RoleToMenus();
						roleToMenus.setId(tempIdInt);
						roleToMenus.setRoleId(roleId);
						RoleToMenusDTO roleToMenusDTO=roleToMenusDao.getByParam(roleToMenus);
						if(roleToMenusDTO==null){
							dateCheck=false;
							break;
						}
					}
				}
			}
		if(!dateCheck){//���ݲ�����
			throw new Exception("���ݴ���,��ˢ������");
		}
		//��ʼ����,���Ӳ˵�
		if(menuIdList!=null){
			roleToMenusDao.saveBatch(menuIdList, roleId);
		}
		//��ʼ���£�ɾ���˵�
		if(tempIdList!=null){
			roleToMenusDao.delBatch(tempIdList, roleId);
		}
		
	}

	@Override
	public List<MenuManagerDTO> getUseFullMenusByRoleId(Integer id) {
		RoleToMenus roleToMenus=new RoleToMenus();
		roleToMenus.setRoleId(id);
		return roleToMenusDao.getMenuListByRole(roleToMenus);
	}
	
}
