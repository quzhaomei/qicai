package com.qicai.service;


/**
 * ����ע��dao���Լ�����ģ�巽��
 *
 */
public interface  BaseService<Bean,DTO> {
	 void saveOrUpdate(Bean bean) throws Exception;
	 void delete(Bean bean);
	 DTO getById(Integer id);
}
