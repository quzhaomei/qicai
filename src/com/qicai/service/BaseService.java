package com.qicai.service;


/**
 * 负责注入dao，以及定义模板方法
 *
 */
public interface  BaseService<Bean,DTO> {
	 void saveOrUpdate(Bean bean) throws Exception;
	 void delete(Bean bean);
	 DTO getById(Integer id);
}
