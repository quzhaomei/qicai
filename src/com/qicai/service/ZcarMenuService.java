package com.qicai.service;

import java.util.List;

import com.qicai.bean.ZcarMenu;
import com.qicai.dto.ZcarMenuDTO;

public interface ZcarMenuService extends BaseService<ZcarMenu, ZcarMenuDTO> {
	List<ZcarMenuDTO> getMenuByParentId(Integer parentId);
	List<ZcarMenuDTO> getAllMenu();
	List<ZcarMenuDTO> getMainMenu();//获取首页链接
	ZcarMenuDTO getMenuByParam(ZcarMenu zcarMenu);
}
