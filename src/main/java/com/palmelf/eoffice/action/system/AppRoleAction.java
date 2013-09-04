package com.palmelf.eoffice.action.system;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.AppUtil;
import com.palmelf.core.util.BeanUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.AppFunction;
import com.palmelf.eoffice.model.system.AppRole;
import com.palmelf.eoffice.service.system.AppFunctionService;
import com.palmelf.eoffice.service.system.AppRoleService;

public class AppRoleAction extends BaseAction {

	@Resource
	private AppFunctionService appFunctionService;
	private static String IS_COPY = "1";

	@Resource
	private AppRoleService appRoleService;
	private AppRole appRole;
	private Long roleId;

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public AppRole getAppRole() {
		return this.appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<AppRole> list = this.appRoleService.getAll(filter);

		Type type = new TypeToken<List<AppRole>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();

		return "success";
	}

	public String tree() {
		StringBuffer buff = new StringBuffer("[");
		List<AppRole> listRole = this.appRoleService.getAll();
		for (AppRole role : listRole) {
			buff.append("{id:'" + role.getRoleId() + "',text:'"
					+ role.getRoleName() + "',leaf:true},");
		}
		if (!listRole.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				AppRole appRole = this.appRoleService.get(new Long(id));
				appRole.getAppUsers().remove(appRole);
				appRole.getFunctions().remove(appRole);
				this.appRoleService.remove(appRole);
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String grant() {
		AppRole appRole = this.appRoleService.get(this.roleId);
		String rights = this.getRequest().getParameter("rights");

		if (rights == null) {
			rights = "";
		}

		appRole.setRights(rights);

		appRole.getFunctions().clear();

		String[] funs = rights.split("[,]");

		for (String fun : funs) {
			if (fun.startsWith("_")) {
				AppFunction af = this.appFunctionService.getByKey(fun);
				if (af != null) {
					appRole.getFunctions().add(af);
				}
			}

		}

		this.appRoleService.save(appRole);

		AppUtil.reloadSecurityDataSource();

		return "success";
	}

	public String grantXml() {
		Document grantMenuDoc = AppUtil.getGrantMenuDocument();
		this.setJsonString(grantMenuDoc.asXML());
		return "success";
	}

	public String get() {
		AppRole appRole = this.appRoleService.get(this.roleId);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(appRole));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String isCopy = this.getRequest().getParameter("isCopy");
		if ((StringUtils.isNotEmpty(isCopy))
				&& (AppRoleAction.IS_COPY.equals(isCopy))) {
			AppRole role = new AppRole();
			role.setIsDefaultIn(Short.valueOf((short) 0));
			role.setRoleDesc(this.appRole.getRoleDesc());
			role.setStatus(this.appRole.getStatus());
			role.setRoleName(this.appRole.getRoleName());
			this.appRole = (this.appRoleService.get(this.appRole.getRoleId()));
			Set set = new HashSet(this.appRole.getFunctions());
			role.setFunctions(set);
			role.setRights(this.appRole.getRights());
			this.appRoleService.save(role);
		} else if (this.appRole.getRoleId() == null) {
			this.appRole.setIsDefaultIn(Short.valueOf((short) 0));
			this.appRoleService.save(this.appRole);
		} else {
			AppRole orgRole = this.appRoleService.get(new Long(this.appRole
					.getRoleId().longValue()));
			try {
				BeanUtil.copyNotNullProperties(orgRole, this.appRole);
				this.appRoleService.save(orgRole);
			} catch (Exception localException) {
			}
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String check() {
		String roleName = this.getRequest().getParameter("roleName");
		AppRole appRole = this.appRoleService.getByRoleName(roleName);
		if (appRole == null) {
			this.setJsonString("{success:true}");
		} else {
			this.setJsonString("{success:false}");
		}
		return "success";
	}
}
