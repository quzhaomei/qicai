package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.TopManager;
import com.qicai.bean.TopToArtical;
import com.qicai.dto.TopManagerDTO;

/**
 * ��ҳ�ö�����
 * @author Administrator
 */
public interface TopManagerDao {
	void save(@Param(value="top")TopManager top);
	void update(@Param(value="top")TopManager top);
	TopManagerDTO findByParam(@Param(value="top")TopManager top);
	/**
	 * ��ȡ���з�����Ϣ������������
	 * @return
	 */
	List<TopManagerDTO> getAllTopManager();
	/**
	 * Ϊ�ö������������
	 * @param articalId
	 * @param topId
	 */
	void addArtical(@Param(value="topToArtical") TopToArtical topToArtical);
	
	/**
	 * �������ö� 
	 */
	void articalToTop(@Param(value="topToArtical") TopToArtical topToArtical) throws Exception;
	/**
	 * �Ƴ��ö�����
	 * @param articalId
	 * @param topId
	 * @throws Exception
	 */
	void articalTopRemove(@Param(value="topToArtical") TopToArtical topToArtical) throws Exception;
}
