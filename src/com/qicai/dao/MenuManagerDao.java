package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.MenuManager;
import com.qicai.dto.admin.MenuManagerDTO;

public interface MenuManagerDao{
	void save(@Param(value="menu") MenuManager menu);//��
	void update(@Param(value="menu")MenuManager menu);//��
	void delete(@Param(value="menu")MenuManager menu);//ɾ
	List<MenuManagerDTO> getListByParam(@Param(value="menu")MenuManager menu);//��ѯ����
	MenuManagerDTO getByParam(@Param(value="menu")MenuManager menut);//��ѯ����
	List<MenuManagerDTO> getNavMenu();//��ѯ�����˵�
}
