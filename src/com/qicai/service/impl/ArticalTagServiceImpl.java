package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.ArticalTag;
import com.qicai.dao.ArticalTagDao;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.PageDTO;
import com.qicai.service.ArticalTagService;
@Service
public class ArticalTagServiceImpl implements ArticalTagService {

	@Resource
	private ArticalTagDao tagDao;
	@Override
	public void saveOrUpdate(ArticalTag bean) {
		if(bean.getTagId()!=null){
			tagDao.update(bean);
		}else{
			tagDao.save(bean);
		}
	}

	@Override
	public void delete(ArticalTag bean) {
		
	}

	@Override
	public ArticalTagDTO getById(Integer id) {
		if(id==null){
			return null;
		}
		ArticalTag articalTag=new ArticalTag();
		articalTag.setTagId(id);
		return tagDao.getTagByParam(articalTag);
	}

	@Override
	public List<ArticalTagDTO> getAllTag() {
		return tagDao.getAllTag();
	}

	@Override
	public List<ArticalTagDTO> getListByParam(ArticalTag tag) {
		return tagDao.getListByParam(tag);
	}

	@Override
	public ArticalTagDTO getArticalParam(ArticalTag tag) {
		return tagDao.getTagByParam(tag);
	}

	@Override
	public PageDTO<List<ArticalTagDTO>> getListByPage(PageDTO<ArticalTag> page) {
		List<ArticalTagDTO> data=tagDao.getListByPage(page);
		PageDTO<List<ArticalTagDTO>> result=new PageDTO<List<ArticalTagDTO>>();
		result.setParam(data);
		result.setPageIndex(page.getPageIndex());
		result.setPageSize(page.getPageSize());
		int count=tagDao.getCountByParam(page.getParam());
		count=count%result.getPageSize()==0?count/result.getPageSize():count/result.getPageSize()+1;
		result.setTotalPage(count);
		return result;
	}

	@Override
	public List<String> getBrandCharArr() {
		return tagDao.getBrandCharArr();
	}

}
