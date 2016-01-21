package com.qicai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qicai.bean.Artical;
import com.qicai.bean.ArticalResource;
import com.qicai.bean.ArticalTag;
import com.qicai.bean.StoreArtical;
import com.qicai.bean.admin.Author;
import com.qicai.dao.ArticalDao;
import com.qicai.dao.ArticalResourceDao;
import com.qicai.dao.ArticalTagDao;
import com.qicai.dao.ArticalToTagDao;
import com.qicai.dao.AuthorDao;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.ArticalResourceDTO;
import com.qicai.dto.ArticalTagDTO;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.MainPageArticalDTO;
import com.qicai.dto.PageDTO;
import com.qicai.service.ArticalService;

@Service
public class ArticalServiceImpl implements ArticalService {

	@Resource
	protected ArticalDao articalDao;
	@Resource
	protected ArticalResourceDao resourceDao;
	@Resource
	protected ArticalToTagDao articalToTagDao;
	@Resource
	protected ArticalTagDao articalTagDao;
	
	@Resource
	protected AuthorDao authorDao;
	
	@Override
	public void saveOrUpdate(Artical bean) throws Exception {
		if(bean.getArticalId()!=null){
			List<Integer> tagIds=bean.getTagId();
			Integer sourceId=bean.getResourceId();
			if(sourceId!=null){
				ArticalResource source=new ArticalResource();
				source.setResourceId(sourceId);
				ArticalResourceDTO sourceDTO=resourceDao.getArticalByParam(source);
				if(sourceDTO==null){
					throw new Exception("文章来源不存在");
				}
			}	
			updateArticalAndTag(bean, tagIds);//更新标签
			articalDao.update(bean);
		}else{
			List<Integer> tagIds=bean.getTagId();
			//校验有没有sourceId
			Integer sourceId=bean.getResourceId();
			if(sourceId!=null){
				ArticalResource source=new ArticalResource();
				source.setResourceId(sourceId);
				ArticalResourceDTO sourceDTO=resourceDao.getArticalByParam(source);
				if(sourceDTO!=null){
					articalDao.save(bean);
					saveArticalAndTag(bean, tagIds);//保存标签
					return;
				}
			}
			
			throw new Exception("文章来源不存在");
			
		}
	}

	@Override
	public void delete(Artical bean) {

	}

	@Override
	public ArticalDTO getById(Integer id) {
		if (id != null) {
			Artical param = new Artical();
			param.setArticalId(id);
			return articalDao.getArticalByParam(param);
		}
		return null;
	}

	@Override
	public PageDTO<List<ArticalDTO>> getPageByParam(PageDTO<Artical> page) {
		List<ArticalDTO> dateList = articalDao.getListByPage(page);
		PageDTO<List<ArticalDTO>> pageDate = new PageDTO<List<ArticalDTO>>();
		pageDate.setParam(dateList);
		pageDate.setPageIndex(page.getPageIndex());
		pageDate.setPageSize(page.getPageSize());
		Integer count = articalDao.getCountByParam(page.getParam());
		count = count % page.getPageSize() == 0 ? count / page.getPageSize()
				: count / page.getPageSize() + 1;
		pageDate.setTotalPage(count);
		return pageDate;

	}
	@Override
	public PageDTO<List<ArticalDTO>> getPageByParamAndAuthor(PageDTO<Artical> page) {
		List<ArticalDTO> dateList = articalDao.getListByPageAndAuthor(page);
		PageDTO<List<ArticalDTO>> pageDate = new PageDTO<List<ArticalDTO>>();
		pageDate.setParam(dateList);
		pageDate.setPageIndex(page.getPageIndex());
		pageDate.setPageSize(page.getPageSize());
		Integer count = articalDao.getCountByParamAndAuthor(page.getParam());
		count = count % page.getPageSize() == 0 ? count / page.getPageSize()
				: count / page.getPageSize() + 1;
		pageDate.setTotalPage(count);
		return pageDate;

	}

	protected void saveArticalAndTag(Artical artical, List<Integer> tagIds)
			throws Exception {
		// 验证数据，
		if (tagIds != null) {
			for (Integer tagId : tagIds) {
				ArticalTag articalTag = new ArticalTag();
				articalTag.setTagId(tagId);
				ArticalTagDTO tagDTO = articalTagDao.getTagByParam(articalTag);
				if (tagDTO == null) {
					throw new Exception("文章标签不存在");
				}
			}
		}
		if (artical.getArticalId() != null) {// 保存
			// 验证数据，登录名，电话号码，邮箱，不能重复
			if (tagIds!=null&&tagIds.size() > 0) {
				articalToTagDao.batchSave(artical, tagIds);
			}
		}

	}

	protected void updateArticalAndTag(Artical artical, List<Integer> tagIds)
			throws Exception {
		// 验证数据，
		if (tagIds != null) {
			for (Integer tagId : tagIds) {
				ArticalTag articalTag = new ArticalTag();
				articalTag.setTagId(tagId);
				ArticalTagDTO tagDTO = articalTagDao.getTagByParam(articalTag);
				if (tagDTO == null) {
					throw new Exception("文章标签不存在");
				}
			}
		}
		if (artical.getArticalId() != null && tagIds != null) {// 保存
			// 清空用户的所有角色，
			articalToTagDao.batchEmpty(artical);
			if (tagIds.size() > 0) {
				// 再保存
				articalToTagDao.batchSave(artical, tagIds);
			}
		}
	}

	@Override
	public PageDTO<List<ArticalDTO>> chonseUnselectArticalByParamAndTop(
			PageDTO<Artical> page, Integer topId,Integer tagId,Date startTime,Date endTime) {
		List<ArticalDTO> result=articalDao.chonseUnselectArticalByParamAndTop
				(page, topId,tagId, startTime, endTime);
		PageDTO<List<ArticalDTO>> pageDTO=new PageDTO<List<ArticalDTO>>();
		pageDTO.setParam(result);
		pageDTO.setPageIndex(page.getPageIndex());
		pageDTO.setPageSize(page.getPageSize());
		int count=articalDao.chonseUnselectCountByParamAndTop(page, topId, tagId, startTime, endTime);
		count=count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1;
		pageDTO.setTotalPage(count);
		return pageDTO;
	}

	@Override
	public PageDTO<List<ArticalDTO>> selectedArticalByParamAndTop(
			PageDTO<Artical> page, Integer topId) {
		if(topId==null){
			return null;
		}
		List<ArticalDTO> result=articalDao.selectedArticalByParamAndTop
				(page, topId);
		PageDTO<List<ArticalDTO>> pageDTO=new PageDTO<List<ArticalDTO>>();
		pageDTO.setParam(result);
		pageDTO.setPageIndex(page.getPageIndex());
		pageDTO.setPageSize(page.getPageSize());
		int count=articalDao.selectedCountByParamAndTop(page, topId);
		count=count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1;
		pageDTO.setTotalPage(count);
		return pageDTO;
	}

	@Override
	public MainPageArticalDTO selectedOneByParamAndTopName(Artical artical, String name) {
		return articalDao.selectedOneByParamAndTopName(artical, name);
	}
	@Override
	public MainPageArticalDTO selectedOneByParamAndTopId(Artical artical, Integer id) {
		return articalDao.selectedOneByParamAndTopId(artical, id);
	}

	@Override
	public List<ArticalDTO>selectedByParamAndShowTag(
			PageDTO<Artical> page) {
		return articalDao.selectedByParamAndShowTag(page);
	}

	@Override
	public List<ArticalDTO> selectedByParamAndTagId(PageDTO<Artical> page,
			Integer tagId) {
		return articalDao.selectedByParamAndTagId(page, tagId);
	}
	@Override
	public List<ArticalDTO> selectedHotArticalByPage(PageDTO<Artical> page) {
		return articalDao.selectedHotArticalByPage(page);
	}

	@Override
	public void storeArtical(StoreArtical storeArtical) {
		articalDao.storeArtical(storeArtical);
	}

	@Override
	public void deleteArtical(StoreArtical storeArtical) {
		articalDao.deleteArtical(storeArtical);
	}

	@Override
	public List<ArticalDTO> getStoreListByParam(StoreArtical storeArtical) {
		return articalDao.getStoreListByParam(storeArtical);
	}

	@Override
	public ArticalDTO getStoreByParam(StoreArtical storeArtical) {
		return articalDao.getStoreByParam(storeArtical);
	}

	@Override
	public PageDTO<List<ArticalDTO>> getAuthorArticalByPage(
			PageDTO<Artical> page) {
		//先查询所有有文章的作者
		PageDTO<Author> authorPage=new PageDTO<Author>();
		authorPage.setPageIndex(page.getPageIndex());
		authorPage.setPageSize(page.getPageSize());
		Author author=new Author();
		author.setStatus(1);
		authorPage.setParam(author);
		//获取作者数据
		List<AuthorDTO> authorDTOs=authorDao.getHasArticalAuthorByPage(authorPage);
		//获取总数目
		int count=authorDao.getHasArticalAuthorCount(author);
		//转换为总页数
		count=count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1;
		PageDTO<List<ArticalDTO>> resultPage=new PageDTO<List<ArticalDTO>>();
		List<ArticalDTO> resultList=new ArrayList<ArticalDTO>();
		resultPage.setParam(resultList);
		resultPage.setPageIndex(page.getPageIndex());
		resultPage.setPageSize(page.getPageSize());
		resultPage.setTotalPage(count);
		
		
		//根据作者获取第一条数据
		PageDTO<Artical> tempPage=new PageDTO<Artical>();
		tempPage.setPageIndex(1);
		tempPage.setPageSize(1);
		Artical tempArtical=new Artical();
		tempPage.setParam(tempArtical);
		for(AuthorDTO authorTemp:authorDTOs){
			tempArtical.setAuthorId(authorTemp.getAuthorId());
			List<ArticalDTO> tempList=articalDao.getAuthorArticalByPage(tempPage);
			resultList.addAll(tempList);//存入数组
		}
		return resultPage;
	}

	@Override
	public List<ArticalDTO> getNearArticalByPage(PageDTO<Artical> page) {
		Integer id=page.getParam().getArticalId();
		if(id!=null){
			ArticalDTO articalDto= articalDao.getArticalByParam(page.getParam());
			if(articalDto!=null){//获取id序列
				Artical idParam=new Artical();
				page.setParam(idParam);
				idParam.setType(articalDto.getType());
				//获取标签ID
				if(articalDto.getTagList()!=null&&articalDto.getTagList().size()>0){
					List<Integer> ids=new ArrayList<Integer>();
					for(ArticalTagDTO tagTemp:articalDto.getTagList()){
						ids.add(tagTemp.getTagId());
					}
					idParam.setTagId(ids);
				}
				idParam.setArticalId(id);//排除原文章
				
				List<ArticalDTO> idList=articalDao.getNearArticalByPage(page);
				if(idList.size()>0){
					List<ArticalDTO> result=new ArrayList<ArticalDTO>();
					for(ArticalDTO temp:idList){
						Artical param=new Artical();
						param.setArticalId(temp.getArticalId());
						result.add(articalDao.getArticalByParam(param));
					}
					return result;
				}
//				return articalDao.getListByIds(ids);
			}
		}
		return null;
	}

	@Override
	public PageDTO<List<ArticalDTO>> selectedArticalByPageAndTag(
			PageDTO<Artical> page, List<Integer> tagIds) {
		if(tagIds==null||tagIds.size()>0){
		List<ArticalDTO> dates=articalDao.selectedArticalByPageAndTag(page, tagIds);
		PageDTO<List<ArticalDTO>> result=new PageDTO<List<ArticalDTO>>();
		
		if(dates.size()>0){
			List<ArticalDTO> resultDate=new ArrayList<ArticalDTO>();
			for(ArticalDTO temp:dates){
				Artical param=new Artical();
				param.setArticalId(temp.getArticalId());
				resultDate.add(articalDao.getArticalByParam(param));
			}
			result.setParam(resultDate);
		}
		
		result.setPageIndex(page.getPageIndex());
		result.setPageSize(page.getPageSize());
		int count=articalDao.selectedArticalCountByTag(page.getParam(), tagIds);
		count=count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1;
		result.setTotalPage(count);
		return result;
		}
		return null;
	}

	@Override
	public PageDTO<List<ArticalDTO>> selectedArticalByPageAndTagType(
			PageDTO<Artical> page, Integer type) {
		if(type!=null){
			List<ArticalDTO> dates=articalDao.selectedArticalByPageAndTagType(page, type);
			PageDTO<List<ArticalDTO>> result=new PageDTO<List<ArticalDTO>>();
			if(dates.size()>0){
				List<ArticalDTO> resultDate=new ArrayList<ArticalDTO>();
				for(ArticalDTO temp:dates){
					Artical param=new Artical();
					param.setArticalId(temp.getArticalId());
					resultDate.add(articalDao.getArticalByParam(param));
				}
				result.setParam(resultDate);
			}
			
			result.setPageIndex(page.getPageIndex());
			result.setPageSize(page.getPageSize());
			int count=articalDao.selectedCountByPageAndTagType(page.getParam(), type);
			count=count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1;
			result.setTotalPage(count);
			return result;
		}
		return null;
	}

	@Override
	public List<ArticalDTO> getVideoArticalByPageAndType(PageDTO<Artical> page,
			Integer type) {
		return articalDao.getVideoArticalByPageAndType(page, type);
	}

	@Override
	public List<ArticalDTO> selectedPageByParamAndTopId(
			PageDTO<Artical> page, Integer id) {
		return articalDao.selectedPageByParamAndTopId(page, id);
	}

	@Override
	public PageDTO<List<ArticalDTO>> getByKey(PageDTO<Artical> page, String key) {
		List<ArticalDTO> dateList = articalDao.getByKey(page,key);
		PageDTO<List<ArticalDTO>> pageDate = new PageDTO<List<ArticalDTO>>();
		pageDate.setParam(dateList);
		pageDate.setPageIndex(page.getPageIndex());
		pageDate.setPageSize(page.getPageSize());
		Integer count = articalDao.getCountByKey(page.getParam(), key);
		count = count % page.getPageSize() == 0 ? count / page.getPageSize()
				: count / page.getPageSize() + 1;
		pageDate.setTotalPage(count);
		return pageDate;
	}

	@Override
	public ArticalDTO getVideoArticalById(Integer id) {
		return articalDao.getVideoArticalById(id);
	}

}
