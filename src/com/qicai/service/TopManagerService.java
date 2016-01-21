package com.qicai.service;

import java.util.List;

import com.qicai.bean.TopManager;
import com.qicai.dto.TopManagerDTO;

/**
 * ��ҳ�ö�����
 * @author Administrator
 */
public interface TopManagerService extends BaseService<TopManager, TopManagerDTO> {
	/**
	 * ��ȡ���з�����Ϣ������������
	 * @return
	 */
	List<TopManagerDTO> getAllTopManager();
	/**
	 * Ϊ�ö������������
	 * @return
	 */
	void addArtical(Integer articalId,Integer topId) throws Exception;
	/**
	 * �������ö� 
	 */
	void articalToTop(Integer articalId,Integer topId) throws Exception;
	/**
	 * �Ƴ��ö����� 
	 */
	void articalTopRemove(Integer articalId,Integer topId) throws Exception;
}
