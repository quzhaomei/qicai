package com.qicai.service;

import java.util.List;

import com.qicai.bean.admin.Author;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.PageDTO;

public interface AuthorService extends BaseService<Author, AuthorDTO>{
	PageDTO<List<AuthorDTO>> findPageDateByPageParam(PageDTO<Author> page);
	AuthorDTO getAuthorByUserId(Integer userId);
	List<AuthorDTO> getAdminAuthor();
}
