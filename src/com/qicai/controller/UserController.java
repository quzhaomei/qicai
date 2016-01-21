package com.qicai.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.DoorTag;
import com.qicai.bean.Artical;
import com.qicai.bean.StoreArtical;
import com.qicai.dto.ArticalDTO;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.PageDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.util.JSONUtil;

@Controller
@RequestMapping(value = "/")
@DoorTag//登陆拦截
public class UserController extends BaseController { 
	//用户中心
	@RequestMapping(value = "/userIndex")
	public String userIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdminUserDTO user=(AdminUserDTO) request.getSession().getAttribute(USER_SESSION);
	//查询该用户的作者信息
		AuthorDTO author=authorService.getAuthorByUserId(user.getAdminUserId());
		if(author!=null){
			String pageIndex = request.getParameter("pageIndex");
			String pageSize = request.getParameter("pageSize");
			Integer pageIndexInt = 1;
			Integer pageSizeInt = 18;
			if (pageIndex != null && pageIndex.matches("\\d+")) {
				pageIndexInt = Integer.parseInt(pageIndex);
			}
			if (pageSize != null && pageSize.matches("\\d+")) {
				pageSizeInt = Integer.parseInt(pageSize);
			}
			//查询该用户的所有文章
			Artical param = new Artical();
			param.setStatus(1);// 正常状态
			param.setAuthorId(author.getAuthorId());//作者ID
			
			PageDTO<Artical> newParam = new PageDTO<Artical>();
			newParam.setPageIndex(pageIndexInt);
			newParam.setParam(param);
			newParam.setPageSize(pageSizeInt);
			PageDTO<List<ArticalDTO>> newPage = articalService
					.getPageByParam(newParam);
			model.addAttribute("newPage", newPage);
		}
		model.addAttribute("author", author);//作者信息
		model.addAttribute("user", user);//用户信息
		return "userIndex";
	}
	//收藏文章
	@RequestMapping(value = "/storeArtical")
	public String storeArtical(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdminUserDTO adminUser=(AdminUserDTO) request.getSession().getAttribute(BaseController.USER_SESSION);
		Integer userId=adminUser.getAdminUserId();
		String articalId=request.getParameter("articalId");
		JsonDTO json=new JsonDTO();
		if(articalId!=null&&articalId.matches("\\d+")){
			
			Integer articalIdInt=Integer.parseInt(articalId);
			StoreArtical storeArtical=new StoreArtical();
			storeArtical.setUserId(userId);
			storeArtical.setArticalId(articalIdInt);
			//检测文章是否已经收藏过
			ArticalDTO artical= articalService.getStoreByParam(storeArtical);
			if(artical!=null){
				json.setStatus(0).setMessage("文章已经收藏过");
			}else{
				try {
					articalService.storeArtical(storeArtical);
					json.setStatus(1).setMessage("收藏文章成功");
				} catch (Exception e) {
					json.setStatus(0).setMessage("收藏文章失败");
					e.printStackTrace();
				}
			}
		}else{
			json.setStatus(0).setMessage("数据错误");
		}
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}
}
