package com.palmelf.eoffice.action.system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.SystemLog;
import com.palmelf.eoffice.service.system.SystemLogService;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class SystemLogAction extends BaseAction {

	@Resource
	private SystemLogService systemLogService;
	private SystemLog systemLog;
	private Long logId;

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public SystemLog getSystemLog() {
		return this.systemLog;
	}

	public void setSystemLog(SystemLog systemLog) {
		this.systemLog = systemLog;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<SystemLog> list = this.systemLogService.getAll(filter);

		Type type = new TypeToken<List<SystemLog>>() {
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
				this.systemLogService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SystemLog systemLog = this.systemLogService.get(this.logId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(systemLog));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.systemLogService.save(this.systemLog);
		setJsonString("{success:true}");
		return "success";
	}
}
