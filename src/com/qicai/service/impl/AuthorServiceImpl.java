package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.admin.Author;
import com.qicai.dao.AuthorDao;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.PageDTO;
import com.qicai.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	@Resource
	private AuthorDao authorDao;
	@Override
	public void saveOrUpdate(Author bean) throws Exception {
		if(bean.getAuthorId()!=null){
			authorDao.update(bean);
		}else{
			authorDao.save(bean);
		}
	}

	@Override
	public void delete(Author bean) {
		
	}

	@Override
	public AuthorDTO getById(Integer id) {
		if(id!=null){
			Author author=new Author();
			author.setAuthorId(id);
			return authorDao.getByParam(author);
		}
		return null;
	}

	@Override
	public PageDTO<List<AuthorDTO>> findPageDateByPageParam(
			PageDTO<Author> page) {
		
		List<AuthorDTO> dateList = authorDao.getListByPage(page);
		PageDTO<List<AuthorDTO>> pageDate = new PageDTO<List<AuthorDTO>>();
		pageDate.setParam(dateList);
		pageDate.setPageIndex(page.getPageIndex());
		pageDate.setPageSize(page.getPageSize());
		Integer count = authorDao.getCountByParam(page.getParam());
		count = count % page.getPageSize() == 0 ? count / page.getPageSize()
				: count / page.getPageSize() + 1;
		pageDate.setTotalPage(count);
		return pageDate;
	}

	@Override
	public AuthorDTO getAuthorByUserId(Integer userId) {
		if(userId!=null){
			Author author=new Author();
			author.setUserId(userId);
			return authorDao.getByParam(author);
		}
		return null;
	}

	@Override
	public List<AuthorDTO> getAdminAuthor() {
		return authorDao.getAdminAuthor();
	}

}
