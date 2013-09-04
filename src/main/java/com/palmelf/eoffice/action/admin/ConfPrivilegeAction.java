package com.palmelf.eoffice.action.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.admin.ConfPrivilege;
import com.palmelf.eoffice.service.admin.ConfPrivilegeService;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class ConfPrivilegeAction extends BaseAction {

	@Resource
	private ConfPrivilegeService confPrivilegeService;
	private ConfPrivilege confPrivilege;
	private Long privilegeId;

	public Long getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public ConfPrivilege getConfPrivilege() {
		return this.confPrivilege;
	}

	public void setConfPrivilege(ConfPrivilege confPrivilege) {
		this.confPrivilege = confPrivilege;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ConfPrivilege> list = this.confPrivilegeService.getAll(filter);

		Type type = new TypeToken<List<ConfPrivilege>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.confPrivilegeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ConfPrivilege confPrivilege = this.confPrivilegeService
				.get(this.privilegeId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(confPrivilege));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.confPrivilegeService.save(this.confPrivilege);
		setJsonString("{success:true}");
		return "success";
	}

	public String allowView() {
		String confId = getRequest().getParameter("confId");
		Short st = this.confPrivilegeService.getPrivilege(new Long(confId),
				(short) 1);
		if (st.shortValue() == 1)
			setJsonString("{success:true}");
		else
			setJsonString("{failure:true,msg:'对不起，您没有权限查看该会议内容，请原谅！'}");
		return "success";
	}

	public String allowUpdater() {
		String confId = getRequest().getParameter("confId");
		Short st = this.confPrivilegeService.getPrivilege(new Long(confId),
				(short) 2);
		if (st.shortValue() == 2)
			setJsonString("{success:true}");
		else
			setJsonString("{failure:true,msg:'对不起，您没有权限编辑该会议内容，请原谅！'}");
		return "success";
	}
}
