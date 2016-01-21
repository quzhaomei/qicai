package com.qicai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.FamousPeople;
import com.qicai.dao.FamousPeopleDao;
import com.qicai.dto.FamousPeopleDTO;
import com.qicai.dto.PageDTO;
import com.qicai.service.FamousPeopleService;
@Service
public class FamousPeopleServiceImpl implements FamousPeopleService {
	@Resource
	private FamousPeopleDao peopleDao;
	@Override
	public void saveOrUpdate(FamousPeople bean) throws Exception {
		if(bean.getPeopleId()!=null){
			peopleDao.update(bean);
		}else{
			peopleDao.save(bean);
		}
	}

	@Override
	public void delete(FamousPeople bean) {
		
	}

	@Override
	public FamousPeopleDTO getById(Integer id) {
		if(id!=null){
			FamousPeople selectParam=new FamousPeople();
			selectParam.setPeopleId(id);
			return peopleDao.getPeopleByParam(selectParam);
		}
		return null;
	}

	@Override
	public List<FamousPeopleDTO> getAllPeople() {
		return peopleDao.getAllPeople();
	}

	@Override
	public List<FamousPeopleDTO> getListByParam(FamousPeople people) {
		return peopleDao.getListByParam(people);
	}

	@Override
	public FamousPeopleDTO getFamousParam(FamousPeople people) {
		return peopleDao.getPeopleByParam(people);
	}

	@Override
	public PageDTO<List<FamousPeopleDTO>> getListByPage(PageDTO<FamousPeople> page) {
		List<FamousPeopleDTO> dateList = peopleDao.getListByPage(page);
		PageDTO<List<FamousPeopleDTO>> pageDate = new PageDTO<List<FamousPeopleDTO>>();
		pageDate.setParam(dateList);
		pageDate.setPageIndex(page.getPageIndex());
		pageDate.setPageSize(page.getPageSize());
		Integer count = peopleDao.getCountByParam(page.getParam());
		count = count % page.getPageSize() == 0 ? count / page.getPageSize()
				: count / page.getPageSize() + 1;
		pageDate.setTotalPage(count);
		return pageDate;
	}

	@Override
	public List<String> getCharArr() {
		return peopleDao.getCharArr();
	}

}
