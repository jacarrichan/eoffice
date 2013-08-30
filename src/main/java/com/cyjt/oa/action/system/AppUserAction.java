package com.cyjt.oa.action.system;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.Constants;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.log.Action;
import com.cyjt.core.model.OnlineUser;
import com.cyjt.core.util.AppUtil;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.util.StringUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.Department;
import com.cyjt.oa.model.system.IndexDisplay;
import com.cyjt.oa.model.system.PanelItem;
import com.cyjt.oa.model.system.UserAgent;
import com.cyjt.oa.service.system.AppRoleService;
import com.cyjt.oa.service.system.AppUserService;
import com.cyjt.oa.service.system.DepartmentService;
import com.cyjt.oa.service.system.IndexDisplayService;
import com.cyjt.oa.service.system.UserAgentService;
import com.cyjt.oa.service.system.UserSubService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class AppUserAction extends BaseAction {
	private static Long SUPPER_MANAGER_ID = Long.valueOf(-1L);

	@Resource
	private AppUserService appUserService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private AppRoleService appRoleService;

	@Resource
	private UserSubService userSubService;

	@Resource
	private IndexDisplayService indexDisplayService;

	@Resource
	private UserAgentService userAgentService;
	private AppUser appUser;
	private Long userId;
	private Long depId;
	private Long roleId;

	public Long getDepId() {
		return this.depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getCurrent() {
		AppUser currentUser = ContextUtil.getCurrentUser();
		Department curDep = currentUser.getDepartment();
		if (curDep == null) {
			curDep = new Department();
			curDep.setDepId(Long.valueOf(0L));
			curDep.setDepName(AppUtil.getCompanyName());
		}
		Iterator<?> publicIds = AppUtil.getPublicMenuIds().iterator();
		StringBuffer publicIdSb = new StringBuffer();

		while (publicIds.hasNext()) {
			publicIdSb.append(",").append((String) publicIds.next());
		}
		List<IndexDisplay> list = this.indexDisplayService
				.findByUser(currentUser.getUserId());
		List<PanelItem> items = new ArrayList<PanelItem>();
		for (IndexDisplay id : list) {
			PanelItem pi = new PanelItem();
			pi.setPanelId(id.getPortalId());
			pi.setColumn(id.getColNum().intValue());
			pi.setRow(id.getRowNum().intValue());
			items.add(pi);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{success:true,user:{userId:'")
				.append(currentUser.getUserId()).append("',fullname:'")
				.append(currentUser.getFullname()).append("',depId:'")
				.append(curDep.getDepId()).append("',depName:'")
				.append(curDep.getDepName()).append("',rights:'");
		sb.append(currentUser.getRights().toString().replace("[", "")
				.replace("]", ""));
		if ((!"".equals(currentUser.getRights())) && (publicIdSb.length() > 0)) {
			sb.append(publicIdSb.toString());
		}
		Gson gson = new Gson();
		sb.append("',items:").append(gson.toJson(items).toString());
		sb.append("}}");
		this.setJsonString(sb.toString());
		return "success";
	}

	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_delFlag_SN_EQ", Constants.FLAG_UNDELETED.toString());
		List<AppUser> list = this.appUserService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "accessionTime" });
		buff.append(serializer.exclude(new String[] { "password" }).serialize(
				list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String unSubUser() {
		String fullname = this.getRequest().getParameter("fullname");
		String userId = this.getRequest().getParameter("userId");
		System.out.println("~~~~~~~~~~~" + userId);
		System.out.println("~~~~~~~~~~~" + fullname);
		PagingBean pb = this.getInitPagingBean();

		List<AppUser> list = this.appUserService.getUnSubUser(new Long(userId),
				fullname, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		Type type = new TypeToken<List<AppUser>>() {
		}.getType();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String unAgent() {
		String fullname = this.getRequest().getParameter("fullname");
		String userId = this.getRequest().getParameter("userId");
		PagingBean pb = this.getInitPagingBean();

		List<AppUser> list = this.appUserService.getUnAgents(new Long(userId),
				fullname, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		Type type = new TypeToken<List<AppUser>>() {
		}.getType();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String agent() {
		String userId = this.getRequest().getParameter("userId");
		System.out.println("~~~~~~~~" + userId);
		String isCombo = this.getRequest().getParameter("isCombo");

		List<UserAgent> list = new ArrayList<UserAgent>();

		if ("true".equals(isCombo)) {
			UserAgent userAgent = new UserAgent();
			AppUser curUser = ContextUtil.getCurrentUser();

			userAgent.setGrantFullname(curUser.getFullname());
			userAgent.setGrantUId(curUser.getUserId());

			list.add(userAgent);
		}

		List<UserAgent> userAgents = this.userAgentService
				.getByUserId(new Long(userId));
		list.addAll(userAgents);
		Gson gson = new Gson();
		Type type = new TypeToken<List<UserAgent>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,result:");

		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String saveAgent() {
		String userId = this.getRequest().getParameter("userId");

		String grantUIds = this.getRequest().getParameter("grantUIds");
		AppUser user = (AppUser) this.appUserService.get(new Long(userId));
		this.userAgentService.delUserGrants(user.getUserId());
		if (StringUtils.isNotEmpty(grantUIds)) {
			String[] uIds = grantUIds.split("[,]");
			if (uIds != null) {
				for (String uId : uIds) {
					AppUser grantUser = (AppUser) this.appUserService
							.get(new Long(uId));
					UserAgent userAgent = new UserAgent();
					userAgent.setFullname(user.getFullname());
					userAgent.setUserId(user.getUserId());
					userAgent.setGrantUId(grantUser.getUserId());
					userAgent.setGrantFullname(grantUser.getFullname());
					userAgent.setGrantTitle(new Integer(grantUser.getTitle()
							.shortValue()));
					this.userAgentService.save(userAgent);
				}
			}
		}
		this.setJsonString("{sucess:true}");
		return "success";
	}

	public String select() {
		PagingBean pb = this.getInitPagingBean();
		String strDepId = this.getRequest().getParameter("depId");

		String path = "0.";
		this.appUser = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.valueOf(Long.parseLong(strDepId));
			Department dep = (Department) this.departmentService.get(depId);
			if (dep != null) {
				path = dep.getPath();
			}
		} else {
			Department dep = this.appUser.getDepartment();
			if (dep != null) {
				path = dep.getPath();
			}
		}
		List<?> list = this.appUserService.findByDepartment(path, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "accessionTime" });
		buff.append(serializer.exclude(new String[] { "password" }).serialize(
				list));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String online() {
		Map<?, ?> onlineUsers = new HashMap<Object, Object>();
		Map<Object, OnlineUser> onlineUsersByDep = new HashMap<Object, OnlineUser>();
		Map<Object, OnlineUser> onlineUsersByRole = new HashMap<Object, OnlineUser>();

		onlineUsers = AppUtil.getOnlineUsers();

		if (this.depId != null) {
			for (Object sessionId : onlineUsers.keySet()) {
				OnlineUser onlineUser = new OnlineUser();
				onlineUser = (OnlineUser) onlineUsers.get(sessionId);

				String path = "";
				if (!onlineUser.getUserId().equals(AppUser.SUPER_USER)) {
					path = onlineUser.getDepPath();
				}
				if (!this.depId.equals(new Long(0L))) {
					if (Pattern.compile("." + this.depId + ".").matcher(path)
							.find()) {
						onlineUsersByDep.put(sessionId, onlineUser);
					}
				} else {
					onlineUsersByDep.put(sessionId, onlineUser);
				}
			}

		}

		if (this.roleId != null) {
			for (Object sessionId : onlineUsers.keySet()) {
				OnlineUser onlineUser = new OnlineUser();
				onlineUser = (OnlineUser) onlineUsers.get(sessionId);

				if (Pattern.compile("," + this.roleId + ",")
						.matcher(onlineUser.getRoleIds()).find()) {
					onlineUsersByRole.put(sessionId, onlineUser);
				}
			}
		}

		Type type = new TypeToken<Collection<OnlineUser>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(onlineUsers.size()).append(",result:");

		Gson gson = new Gson();
		if (this.depId != null) {
			buff.append(gson.toJson(onlineUsersByDep.values(), type));
		} else if (this.roleId != null) {
			buff.append(gson.toJson(onlineUsersByRole.values(), type));
		} else {
			buff.append(gson.toJson(onlineUsers.values(), type));
		}
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String find() {
		String strRoleId = this.getRequest().getParameter("roleId");
		PagingBean pb = this.getInitPagingBean();
		if (StringUtils.isNotEmpty(strRoleId)) {
			List<AppUser> userList = this.appUserService.findByRole(
					Long.valueOf(Long.parseLong(strRoleId)), pb);
			Type type = new TypeToken<List<AppUser>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(pb.getTotalItems()).append(",result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(userList, type));
			buff.append("}");

			this.jsonString = buff.toString();
		} else {
			this.jsonString = "{success:false}";
		}
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		StringBuffer buff = new StringBuffer("{success:true");
		if (ids != null) {
			buff.append(",msg:'");
			for (String id : ids) {
				AppUser delUser = (AppUser) this.appUserService
						.get(new Long(id));
				AppRole superManager = (AppRole) this.appRoleService
						.get(AppUserAction.SUPPER_MANAGER_ID);
				if (delUser.getRoles().contains(superManager)) {
					buff.append("员工:").append(delUser.getUsername())
							.append("是超级管理员,不能删除!<br><br/>");
				} else if (delUser.getUserId().equals(
						ContextUtil.getCurrentUserId())) {
					buff.append("不能删除自己!<br></br>");
				} else {
					delUser.setStatus(Constants.FLAG_DISABLE);
					delUser.setDelFlag(Constants.FLAG_DELETED);
					delUser.setUsername("__" + delUser.getUsername());
					this.appUserService.save(delUser);
				}
			}
			buff.append("'");
		}
		buff.append("}");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String get() {
		AppUser appUser = null;
		JSONSerializer json = JsonUtil
				.getJSONSerializer(new String[] { "accessionTime" });
		if (this.userId != null) {
			appUser = (AppUser) this.appUserService.get(this.userId);
		} else {
			json.exclude(new String[] { "accessionTime", "department",
					"password", "status", "position" });
			appUser = ContextUtil.getCurrentUser();
		}

		StringBuffer sb = new StringBuffer("{success:true,totalCounts:1,data:[");
		sb.append(JsonUtil.getJSONSerializer(new String[] { "accessionTime" })
				.serialize(appUser));
		sb.append("]}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Action(description = "添加或保存用户信息")
	public String save() {
		String rolesIds = this.getRequest().getParameter("AppUserRoles");
		String[] ids = rolesIds.split(",");
		Set<AppRole> roles = new HashSet<AppRole>();
		for (String id : ids) {
			if (!"".equals(id)) {
				AppRole role = (AppRole) this.appRoleService.get(new Long(id));
				roles.add(role);
			}
		}
		this.appUser.setRoles(roles);
		if (this.appUser.getUserId() != null) {
			AppUser old = (AppUser) this.appUserService.get(this.appUser
					.getUserId());
			this.appUser.setDelFlag(old.getDelFlag());
			this.appUser.setPassword(old.getPassword());
			this.appUserService.merge(this.appUser);
			this.setJsonString("{success:true}");
		} else if (this.appUserService.findByUserName(this.appUser
				.getUsername()) == null) {
			this.appUser.setDelFlag(Constants.FLAG_UNDELETED);
			this.appUser.setPassword(StringUtil.encryptSha256(this.appUser
					.getPassword()));
			this.appUserService.merge(this.appUser);
			this.setJsonString("{success:true}");
		} else {
			this.setJsonString("{success:false,msg:'用户登录账号:"
					+ this.appUser.getUsername() + "已存在,请重新输入账号.'}");
		}

		return "success";
	}

	public String selectedRoles() {
		if (this.userId != null) {
			this.setAppUser((AppUser) this.appUserService.get(this.userId));
			Set<AppRole> roles = this.appUser.getRoles();
			StringBuffer sb = new StringBuffer("[");
			for (AppRole role : roles) {
				sb.append("['" + role.getRoleId() + "','" + role.getRoleName()
						+ "'],");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			this.setJsonString(sb.toString());
		}
		return "success";
	}

	public String chooseRoles() {
		List<AppRole> chooseRoles = this.appRoleService.getAll();

		if (this.userId != null) {
			this.setAppUser((AppUser) this.appUserService.get(this.userId));
			Set<AppRole> selectedRoles = this.appUser.getRoles();
			for (AppRole role : selectedRoles) {
				chooseRoles.remove(role);
			}
		}
		StringBuffer sb = new StringBuffer("[");
		for (AppRole role : chooseRoles) {
			if (role.getStatus().shortValue() != 0) {
				sb.append("['" + role.getRoleId() + "','" + role.getRoleName()
						+ "'],");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Action(description = "修改密码")
	public String resetPassword() {
		String userId = this.getRequest().getParameter("appUserUserId");
		String oldPassword = StringUtil.encryptSha256(this.getRequest()
				.getParameter("oldPassword"));
		String newPassword = this.getRequest().getParameter("newPassword");
		String againPassword = this.getRequest().getParameter("againPassword");
		if (StringUtils.isNotEmpty(userId)) {
			this.setAppUser((AppUser) this.appUserService.get(new Long(userId)));
		} else {
			this.setAppUser(ContextUtil.getCurrentUser());
		}
		StringBuffer msg = new StringBuffer("{msg:'");
		boolean pass = false;
		if (oldPassword.equals(this.appUser.getPassword())) {
			if (newPassword.equals(againPassword)) {
				pass = true;
			} else {
				msg.append("两次输入不一致.'");
			}
		} else {
			msg.append("旧密码输入不正确.'");
		}
		if (pass) {
			this.appUser.setPassword(StringUtil.encryptSha256(newPassword));
			this.appUserService.save(this.appUser);
			this.setJsonString("{success:true}");
		} else {
			msg.append(",failure:true}");
			this.setJsonString(msg.toString());
		}
		return "success";
	}

	@Action(description = "重置密码")
	public String createPassword() {
		String userId = this.getRequest().getParameter("appUserUserId");
		String newPassword = this.getRequest().getParameter("newpassword");
		String password = this.getRequest().getParameter("password");
		StringBuffer msg = new StringBuffer("{msg:'");
		if (StringUtils.isNotEmpty(userId)) {
			this.setAppUser((AppUser) this.appUserService.get(new Long(userId)));
		} else {
			this.setAppUser(ContextUtil.getCurrentUser());
		}

		if (newPassword.equals(password)) {
			this.appUser.setPassword(StringUtil.encryptSha256(newPassword));
			this.appUserService.save(this.appUser);
			this.setJsonString("{success:true}");
		} else {
			msg.append("重置失败!,两次输入的密码不一致,请重新输入!.'");
			msg.append(",failure:true}");
			this.setJsonString(msg.toString());
		}

		return "success";
	}

	public String photo() {
		this.setAppUser((AppUser) this.appUserService.get(this.getUserId()));
		this.appUser.setPhoto("");
		this.appUserService.save(this.appUser);
		return "success";
	}

	public String subAdepartment() {
		PagingBean pb = this.getInitPagingBean();
		String strDepId = this.getRequest().getParameter("depId");
		String path = "0.";
		AppUser user = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.valueOf(Long.parseLong(strDepId));
			Department dep = (Department) this.departmentService.get(depId);
			if (dep != null) {
				path = dep.getPath();
			}
		} else {
			Department dep = user.getDepartment();
			if (dep != null) {
				path = dep.getPath();
			}
		}
		if ("0.".equals(path)) {
			path = null;
		}
		Long uId = user.getUserId();
		Set<Long> userIds = this.userSubService.findAllUpUser(uId);
		List<Long> userIdsL = this.userSubService.subUsers(uId);
		userIds.add(uId);
		for (Long l : userIdsL) {
			userIds.add(l);
		}
		List<AppUser> list = this.appUserService.findSubAppUser(path, userIds,
				pb);
		Type type = new TypeToken<List<AppUser>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String subArole() {
		String strRoleId = this.getRequest().getParameter("roleId");
		PagingBean pb = this.getInitPagingBean();
		AppUser user = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strRoleId)) {
			Long uId = user.getUserId();
			Set<Long> userIds = this.userSubService.findAllUpUser(uId);
			List<Long> userIdsL = this.userSubService.subUsers(uId);
			userIds.add(uId);
			for (Long l : userIdsL) {
				userIds.add(l);
			}
			List<AppUser> userList = this.appUserService.findSubAppUserByRole(
					new Long(strRoleId), userIds, pb);

			Type type = new TypeToken<List<AppUser>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(pb.getTotalItems()).append(",result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(userList, type));
			buff.append("}");
			this.jsonString = buff.toString();
		} else {
			this.jsonString = "{success:false}";
		}
		return "success";
	}

	public String onlineAsub() {
		Map<String, OnlineUser> onlineUsers = new HashMap<String, OnlineUser>();
		Map<Object, OnlineUser> onlineUsersBySub = new HashMap<Object, OnlineUser>();
		onlineUsers = AppUtil.getOnlineUsers();

		AppUser user = ContextUtil.getCurrentUser();
		Long uId = user.getUserId();
		Set<Long> userIds = this.userSubService.findAllUpUser(uId);
		userIds.add(uId);
		List<Long> userIdsL = this.userSubService.subUsers(uId);
		for (Long l : userIdsL) {
			userIds.add(l);
		}
		for (Object sessionId : onlineUsers.keySet()) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser = (OnlineUser) onlineUsers.get(sessionId);
			if (!userIds.contains(onlineUser.getUserId())) {
				onlineUsersBySub.put(sessionId, onlineUser);
			}
		}
		Type type = new TypeToken<Collection<OnlineUser>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(onlineUsers.size()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(onlineUsersBySub.values(), type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String upUser() {
		List<Long> ids = this.userSubService.upUser(ContextUtil
				.getCurrentUserId());
		List<AppUser> list = new ArrayList<AppUser>();
		for (Long l : ids) {
			list.add((AppUser) this.appUserService.get(l));
		}
		StringBuffer buff = new StringBuffer("[");
		for (AppUser user : list) {
			buff.append("['" + user.getUserId().toString() + "','"
					+ user.getFullname() + "'],");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}

	@Action(description = "修改个人资料")
	public String profile() {
		AppUser old = ContextUtil.getCurrentUser();
		try {
			BeanUtil.copyNotNullProperties(old, this.appUser);
		} catch (Exception e) {
			this.logger.info(e);
		}
		this.appUserService.save(old);
		this.jsonString = "{success:true}";
		return "success";
	}
}
