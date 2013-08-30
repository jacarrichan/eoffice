package com.cyjt.oa.action.archive;

import com.google.gson.Gson;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.archive.ArchivesDep;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.archive.ArchivesDepService;
import flexjson.JSONSerializer;
import java.util.List;
import javax.annotation.Resource;

public class ArchivesDepAction extends BaseAction {

	@Resource
	private ArchivesDepService archivesDepService;
	private ArchivesDep archivesDep;
	private Long archDepId;

	public Long getArchDepId() {
		return this.archDepId;
	}

	public void setArchDepId(Long archDepId) {
		this.archDepId = archDepId;
	}

	public ArchivesDep getArchivesDep() {
		return this.archivesDep;
	}

	public void setArchivesDep(ArchivesDep archivesDep) {
		this.archivesDep = archivesDep;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());

		AppUser curUser = ContextUtil.getCurrentUser();

		filter.addFilter("Q_department.depId_L_EQ", curUser.getDepartment()
				.getDepId().toString());
		List list = this.archivesDepService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer json = JsonUtil
				.getJSONSerializer(new String[] { "archives.createtime" });
		buff.append(json.serialize(list));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.archivesDepService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchivesDep archivesDep = this.archivesDepService.get(this.archDepId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archivesDep));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.archivesDepService.save(this.archivesDep);
		setJsonString("{success:true}");
		return "success";
	}
}
