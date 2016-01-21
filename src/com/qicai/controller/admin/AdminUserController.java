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
import com.qicai.controller.BaseController;
import com.qicai.dto.JsonDTO;
import com.qicai.dto.PageDTO;
import com.qicai.dto.admin.AdminUserDTO;
import com.qicai.dto.admin.RoleManagerDTO;
import com.qicai.util.JSONUtil;
import com.qicai.util.PasswordUtil;

@Controller
@RequestMapping(value = "user")
@LimitTag(uri = true)
public class AdminUserController extends BaseController {

	// 负责跳转菜单页面,并查出用户列表
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageIndex = request.getParameter("pageIndex");// 页码
		String pageSize = request.getParameter("pageSize");// 页容量
		String type = request.getParameter("type");// 0-管理账号，1-门户账号，
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
				if (type != null && type.matches("\\d+") && !"99".equals(type)) {
					selectParam.setType(Integer.parseInt(type));
				}

				PageDTO<List<AdminUserDTO>> pageDate = adminUserService
						.findPageDateByPageParam(page);
				// 查找所有角色
				List<RoleManagerDTO> roleList = roleManagerService.getAllRole();
				model.addAttribute("type", type);
				model.addAttribute("roles", roleList);
				model.addAttribute("pageResult", pageDate);
				return "admin/adminUser";
			}
		}
		return "admin/adminUser";
	}

	// 添加用户
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String operator = request.getParameter("operator");
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
		}
		// 进行增加操作
		AdminUserDTO adminUserDTO = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		JsonDTO json = new JsonDTO();
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
				saveParam.setType(1);// 后台管理账号
				saveParam.setOrinal(0);// 表示为个人账号

				saveParam.setCreateDate(new Date());
				saveParam.setCreateUserId(adminUserDTO.getAdminUserId());// 初始化创建人数
				saveParam.setDescription(description);
				saveParam.setStatus(1);

				try {
					adminUserService.saveUserAndRole(saveParam, roleIds);
					json.setStatus(1).setMessage("保存用户成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("保存用户失败:" + e.getMessage());
				}
				model.addAttribute("json", JSONUtil.object2json(json));
				return JSON;
			}
		}
		return null;
	}

	// 用户明细
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		JsonDTO json = new JsonDTO();
		if (FIND_BY_ID.equals(operator)) {// 根据ID加载
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
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String operator = request.getParameter("operator");
		AdminUserDTO adminUserDTO = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
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
		} else if (EDIT.equals(operator)) {// 更新
			String userId = request.getParameter("userId");
			String nickname = request.getParameter("nickname");

			String[] roleIds = request.getParameterValues("rolesId[]");

			String loginname = request.getParameter("loginname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String status = request.getParameter("status");
			String description = request.getParameter("description");

			String type = request.getParameter("type");// 账户类型 0-门户，1-管理
			// 数据校验
			if (userId != null && userId.matches("\\d+")
					&& userId.length() <= 11) {
				if ((nickname == null || nickname.length() <= 10)
						&& (loginname == null || loginname.length() <= 20)
						&& (phone == null || phone.matches("\\d{11}"))
						&& (email == null || email.length() <= 30)
						&& (status == null || status.matches("\\d{1}"))
						&& (description == null || description.length() <= 200)
						&& (type == null || type.matches("[01]"))) {
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
						updateParam.setUpdateUserId(adminUserDTO
								.getAdminUserId());// 更新者
						updateParam.setUpdateDate(new Date());
						updateParam.setNickname(nickname);
						updateParam.setDescription(description);
						updateParam.setLoginname(loginname);
						updateParam.setEmail(email);
						if (status != null) {
							updateParam.setStatus(Integer.parseInt(status));
						}
						if (type != null) {
							updateParam.setType(Integer.parseInt(type));
						}
						updateParam.setPhone(phone);
						try {
							adminUserService.updateUserAndRole(updateParam,
									roleIdsInt);
							json.setStatus(1).setMessage("更新用户成功");
							model.addAttribute("json",
									JSONUtil.object2json(json));
							// 如果更新的是自己信息，则更新session中的昵称
							if (userDTO.getAdminUserId().equals(
									adminUserDTO.getAdminUserId())) {
								if (nickname != null) {
									adminUserDTO.setNickname(nickname);
								}
							}

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
		}
		json.setStatus(0).setMessage("数据错误");
		model.addAttribute("json", JSONUtil.object2json(json));
		return JSON;
	}

	// 切换状态
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		AdminUserDTO adminUserDTO = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		if (userId != null && userId.matches("\\d+") && userId.length() <= 11
				&& status != null && status.matches("[12]")) {
			AdminUserDTO userDTO = adminUserService.getById(Integer
					.parseInt(userId));
			if (userDTO != null) {// 数据验证通过，开始更新
				AdminUser updateParam = new AdminUser();
				updateParam.setAdminUserId(userDTO.getAdminUserId());
				updateParam.setUpdateUserId(adminUserDTO.getAdminUserId());// 更新者
				updateParam.setUpdateDate(new Date());
				updateParam.setStatus(Integer.parseInt(status));
				try {
					adminUserService.updateUserAndRole(updateParam, null);
					json.setStatus(1).setMessage("更新用户成功");
					model.addAttribute("json", JSONUtil.object2json(json));
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("更新用户失败：" + e.getMessage());
					model.addAttribute("json", JSONUtil.object2json(json));
				}
			}
		}
		return JSON;
	}

	// 重置密码
	@RequestMapping(value = "/reset")
	public String reset(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AdminUserDTO adminUserDTO = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		JsonDTO json = new JsonDTO();
		String userId = request.getParameter("userId");
		if (userId != null && userId.matches("\\d+")) {
			Integer userIdInt = Integer.parseInt(userId);
			AdminUserDTO userDTO = adminUserService.getById(userIdInt);
			if (userDTO != null) {
				String email = userDTO.getEmail();
				AdminUser updateParam = new AdminUser();
				updateParam.setAdminUserId(userIdInt);
				updateParam.setPassword(PasswordUtil.MD5(email));
				updateParam.setUpdateUserId(adminUserDTO.getAdminUserId());// 更新者
				updateParam.setUpdateDate(new Date());
				try {
					adminUserService.updateUserAndRole(updateParam, null);
					json.setStatus(1).setMessage("密码已经成功重置为邮箱");
					model.addAttribute("json", JSONUtil.object2json(json));
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(1).setMessage("重置失败：" + e.getMessage());
					model.addAttribute("json", JSONUtil.object2json(json));
				}
				return JSON;
			}
		}
		return null;
	}

	// 删除用户
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JsonDTO json = new JsonDTO();
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		AdminUserDTO adminUserDTO = (AdminUserDTO) request.getSession()
				.getAttribute(ADMIN_USER_SESSION);
		if (userId != null && userId.matches("\\d+") && userId.length() <= 11
				&& status != null && status.matches("[0]")) {
			AdminUserDTO userDTO = adminUserService.getById(Integer
					.parseInt(userId));
			if (userDTO != null) {// 数据验证通过，开始更新
				AdminUser updateParam = new AdminUser();
				updateParam.setAdminUserId(userDTO.getAdminUserId());
				updateParam.setUpdateUserId(adminUserDTO.getAdminUserId());// 更新者
				updateParam.setUpdateDate(new Date());
				updateParam.setStatus(Integer.parseInt(status));
				try {
					adminUserService.updateUserAndRole(updateParam, null);
					json.setStatus(1).setMessage("删除用户成功");
					model.addAttribute("json", JSONUtil.object2json(json));
				} catch (Exception e) {
					e.printStackTrace();
					json.setStatus(0).setMessage("删除用户失败：" + e.getMessage());
					model.addAttribute("json", JSONUtil.object2json(json));
				}
			}
		}
		return JSON;
	}
}
