package com.qicai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicai.bean.admin.Author;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.PageDTO;

/**
 *
 * @author Administrator
 *
 */
public interface AuthorDao {
	void save(@Param("author")Author author);//��
	void update(@Param("author")Author author);//��
	AuthorDTO getByParam(@Param("author")Author author);//��ѯ����
	
	List<AuthorDTO> getAdminAuthor();//��ѯ���к�̨�û������߽�ɫ
	
	int getCountByParam(@Param("author")Author author);//��ѯ����
	List<AuthorDTO> getListByPage(@Param("page")PageDTO<Author> page);//��ѯ����
	
	/**
	 * ��ѯ�����µ������б�
	 */
	List<AuthorDTO> getHasArticalAuthorByPage(@Param("page")PageDTO<Author> page);
	int getHasArticalAuthorCount(@Param("author")Author author);
}
