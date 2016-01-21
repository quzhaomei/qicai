package com.qicai.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qicai.annotation.LimitTag;
import com.qicai.bean.admin.AdminUser;
import com.qicai.bean.admin.Author;
import com.qicai.controller.BaseController;
import com.qicai.dto.AuthorDTO;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.PageDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.util.JSONUtil;
import com.qicai.util.PasswordUtil;

@Controller
@RequestMapping(value = "author")
@LimitTag(uri = true)
public class AuthorController extends BaseController {
	// 作者个人中心页面
	@RequestMapping(value = "/perIndex")
	public String perIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdminUserDTO adminUser = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		Integer userId = adminUser.getAdminUserId();
		// 获取用户的作者信息
		AuthorDTO author = authorService.getAuthorByUserId(userId);
		model.addAttribute("author", author);
		model.addAttribute("bak", JSONUtil.object2json(author));
		return "admin/authorPerIndex";
	}

	// 作者个人中心更新
	@RequestMapping(value = "/perSaveOrUpdate")
	public String perSaveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdminUserDTO adminUser = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		Integer userId = adminUser.getAdminUserId();
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (ADD.equals(operator)) {
			// 如果是保存
			String penName = request.getParameter("penName");
			String introduce = request.getParameter("introduce");
			String sinaPath = request.getParameter("sinaPath");
			String txPath = request.getParameter("txPath");
			String photoPath = request.getParameter("photoPath");
			// penName不能为空
			if (penName != null && penName.length() <= 10 && photoPath != null
					&& photoPath.length() <= 50
					&& (introduce == null || introduce.length() <= 200)) {
				Author saveParam = new Author();
				saveParam.setUserId(userId);
				saveParam.setStatus(1);
				saveParam.setCreateDate(new Date());
				saveParam.setIntroduce(introduce);
				saveParam.setPenName(penName);
				saveParam.setPhotoPath(photoPath);
				saveParam.setSinaPath(sinaPath);
				saveParam.setTxPath(txPath);
				try {
					authorService.saveOrUpdate(saveParam);
					json.setStatus(1).setMessage("保存作者信息成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("保存作者信息失败：" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		} else if (EDIT.equals(operator)) {// 如果是修改
			// 如果是保存
			String penName = request.getParameter("penName");
			String introduce = request.getParameter("introduce");
			String sinaPath = request.getParameter("sinaPath");
			String txPath = request.getParameter("txPath");
			String photoPath = request.getParameter("photoPath");
			String authorId = request.getParameter("authorId");
			// penName不能为空
			if ((penName == null || penName.length() <= 10)
					&& (photoPath == null || photoPath.length() <= 50)
					&& (introduce == null || introduce.length() <= 200)
					&& authorId != null && authorId.matches("\\d+")) {
				Integer authorIdInt = Integer.parseInt(authorId);
				AuthorDTO authorDTO = authorService.getById(authorIdInt);
				if (authorDTO != null) {
					Author updateParam = new Author();
					updateParam.setAuthorId(authorIdInt);
					updateParam.setIntroduce(introduce);
					updateParam.setPenName(penName);
					updateParam.setPhotoPath(photoPath);
					updateParam.setSinaPath(sinaPath);
					updateParam.setTxPath(txPath);
					try {
						authorService.saveOrUpdate(updateParam);
						json.setStatus(1).setMessage("修改作者信息成功");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0).setMessage(
								"修改作者信息失败：" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		}
		json.setStatus(0).setMessage("数据异常");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 负责跳转菜单页面,并查出用户列表
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");// 页码
		String pageSize = request.getParameter("pageSize");// 页容量
		if (pageIndex == null) {
			pageIndex = "1";
		}
		if (pageSize == null) {
			pageSize = "10";
		}
		if (pageIndex.matches("\\d+") && pageSize.matches("\\d+")) {
			Integer pageIndexInt = Integer.parseInt(pageIndex);
			Integer pageSizeInt = Integer.parseInt(pageSize);
			PageDTO<AdminUser> page = new PageDTO<AdminUser>();
			if (pageIndexInt != 0 && pageSizeInt != 0) {
				page.setPageIndex(pageIndexInt);
				page.setPageSize(pageSizeInt);
				AdminUser selectParam = new AdminUser();
				page.setParam(selectParam);
				PageDTO<List<AdminUserDTO>> pageDate = adminUserService
						.findPageDateByPageParam(page);
				// 查找所有角色
				List<RoleManagerDTO> roleList = roleManagerService.getAllRole();
				model.addAttribute("roles", roleList);
				model.addAttribute("pageResult", pageDate);
				return "admin/adminUser";
			}
		}
		return "admin/adminUser";
	}

	// 负责跳转菜单页面,并查出用户列表
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (CHECK.equals(operator)) {// 如果是验证，
			String loginname = request.getParameter("loginname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			AdminUser selectParam = new AdminUser();
			boolean result = false;
			if (loginname != null && loginname.length() <= 20
					&& loginname.matches("[\\d\\w]{6,20}")) {// 检测登录名
				selectParam.setLoginname(loginname);
				int count = adminUserService.checkUserCount(selectParam);
				if (count == 0) {
					result = true;
				}

			} else if (phone != null) {// 检测手机
				selectParam.setPhone(phone);
				int count = adminUserService.checkUserCount(selectParam);
				if (count == 0) {
					result = true;
				}
			} else if (email != null) {// 检测邮箱
				selectParam.setEmail(email);
				int count = adminUserService.checkUserCount(selectParam);
				if (count == 0) {
					result = true;
				}
			}
			try {
				response.getWriter().print(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		} else if (FIND_BY_ID.equals(operator)) {// 根据ID加载
			String userId = request.getParameter("userId");
			if (userId != null && userId.matches("\\d+")
					&& userId.length() <= 11) {
				Integer userIdInt = Integer.parseInt(userId);
				AdminUserDTO userDTO = adminUserService.getById(userIdInt);
				json.setStatus(1).setData(userDTO);
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		json.setStatus(0).setMessage("参数错误");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 更新用户
	@RequestMapping(value = "/saveOrUpdate")
	public String saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (ADD.equals(operator)) {
			String nickname = request.getParameter("nickname");
			String[] roleList = request.getParameterValues("roleList[]");
			String loginname = request.getParameter("loginname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String description = request.getParameter("description");
			if (nickname != null && loginname != null && phone != null
					&& email != null) {// 数据验证
				boolean isOk = true;
				List<Integer> roleIds = new ArrayList<Integer>();
				if (nickname.length() > 10) {// 验证昵称，
					isOk = false;
				} else if (roleList != null) {// 验证角色ID
					for (String roleId : roleList) {
						if (!roleId.matches("\\d+")) {
							isOk = false;
							break;
						} else {
							roleIds.add(Integer.parseInt(roleId));
						}
					}
				} else if (loginname.length() > 20
						|| !loginname.matches("[\\d\\w]{6,20}")) {// 验证登录名
					isOk = false;
				} else if (email.length() > 30) {// 验证邮箱
					isOk = false;
				} else if (description != null && description.length() > 200) {
					isOk = false;
				} else if (phone.length() > 11) {
					isOk = false;
				}
				if (isOk) {
					AdminUser saveParam = new AdminUser();
					saveParam.setLoginname(loginname);
					saveParam.setPassword(PasswordUtil.MD5(phone));// 初始密码为手机号

					saveParam.setEmail(email);
					saveParam.setNickname(nickname);
					saveParam.setPhone(phone);

					saveParam.setCreateDate(new Date());
					saveParam.setDescription(description);
					saveParam.setStatus(1);

					try {
						adminUserService.saveUserAndRole(saveParam, roleIds);
						json.setStatus(1).setMessage("保存用户成功");
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(0)
								.setMessage("保存用户失败:" + e.getMessage());
					}
					model.addAttribute("json", JSONUtil.object2json(json));
					return JSON;
				}
			}
		} else if (EDIT.equals(operator)) {// 更新
			String userId = request.getParameter("userId");
			String nickname = request.getParameter("nickname");

			String[] roleIds = request.getParameterValues("rolesId[]");

			String loginname = request.getParameter("loginname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String status = request.getParameter("status");
			String description = request.getParameter("description");
			// 数据校验
			if (userId != null && userId.matches("\\d+")
					&& userId.length() <= 11) {
				if ((nickname == null || nickname.length() <= 10)
						&& (loginname == null || loginname.length() <= 20)
						&& (phone == null || phone.matches("\\d{11}"))
						&& (email == null || email.length() <= 30)
						&& (status == null || status.matches("\\d{1}"))
						&& (description == null || description.length() <= 200)) {
					boolean checkOk = true;
					List<Integer> roleIdsInt = new ArrayList<Integer>();
					AdminUserDTO userDTO = adminUserService.getById(Integer
							.parseInt(userId));
					if (roleIds != null) {
						for (String roleId : roleIds) {
							if (roleId.matches("\\d+")) {
								roleIdsInt.add(Integer.parseInt(roleId));
							} else {
								checkOk = false;
								break;
							}
						}
					}
					if (checkOk && userDTO != null) {// 数据验证通过，开始更新
						AdminUser updateParam = new AdminUser();
						updateParam.setAdminUserId(userDTO.getAdminUserId());
						updateParam.setNickname(nickname);
						updateParam.setDescription(description);
						updateParam.setLoginname(loginname);
						updateParam.setEmail(email);
						if (status != null) {
							updateParam.setStatus(Integer.parseInt(status));
						}
						updateParam.setPhone(phone);
						try {
							adminUserService.updateUserAndRole(updateParam,
									roleIdsInt);
							json.setStatus(1).setMessage("更新用户成功");
							model.addAttribute("json",
									JSONUtil.object2json(json));
						} catch (Exception e) {
							e.printStackTrace();
							json.setStatus(0).setMessage(
									"更新用户失败：" + e.getMessage());
							model.addAttribute("json",
									JSONUtil.object2json(json));
						}
						return JSON;
					}
				}
			}
		} else if (RESET.equals(operator)) {// 重置密码
			String userId = request.getParameter("userId");
			if (userId != null && userId.matches("\\d+")) {
				Integer userIdInt = Integer.parseInt(userId);
				AdminUserDTO userDTO = adminUserService.getById(userIdInt);
				if (userDTO != null) {
					String phone = userDTO.getPhone();
					AdminUser updateParam = new AdminUser();
					updateParam.setAdminUserId(userIdInt);
					updateParam.setPassword(PasswordUtil.MD5(phone));
					try {
						adminUserService.updateUserAndRole(updateParam, null);
						json.setStatus(1).setMessage("密码已经成功重置为手机号码");
						model.addAttribute("json", JSONUtil.object2json(json));
					} catch (Exception e) {
						e.printStackTrace();
						json.setStatus(1).setMessage("重置失败：" + e.getMessage());
						model.addAttribute("json", JSONUtil.object2json(json));
					}
					return JSON;
				}
			}
		}
		json.setStatus(0).setMessage("数据错误");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}
}
