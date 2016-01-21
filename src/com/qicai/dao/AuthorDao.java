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
	void save(@Param("author")Author author);//增
	void update(@Param("author")Author author);//改
	AuthorDTO getByParam(@Param("author")Author author);//查询单个
	
	List<AuthorDTO> getAdminAuthor();//查询所有后台用户的作者角色
	
	int getCountByParam(@Param("author")Author author);//查询数量
	List<AuthorDTO> getListByPage(@Param("page")PageDTO<Author> page);//查询数组
	
	/**
	 * 查询有文章的作者列表
	 */
	List<AuthorDTO> getHasArticalAuthorByPage(@Param("page")PageDTO<Author> page);
	int getHasArticalAuthorCount(@Param("author")Author author);
}
