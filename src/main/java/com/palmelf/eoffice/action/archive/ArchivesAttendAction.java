package com.palmelf.eoffice.action.archive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.archive.Archives;
import com.palmelf.eoffice.model.archive.ArchivesAttend;
import com.palmelf.eoffice.service.archive.ArchivesAttendService;
import com.palmelf.eoffice.service.archive.ArchivesService;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class ArchivesAttendAction extends BaseAction {

	@Resource
	private ArchivesAttendService archivesAttendService;

	@Resource
	private ArchivesService archivesService;
	private ArchivesAttend archivesAttend;
	private Long attendId;
	private Archives archives;

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	public Long getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Long attendId) {
		this.attendId = attendId;
	}

	public ArchivesAttend getArchivesAttend() {
		return this.archivesAttend;
	}

	public void setArchivesAttend(ArchivesAttend archivesAttend) {
		this.archivesAttend = archivesAttend;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ArchivesAttend> list = this.archivesAttendService.getAll(filter);

		Type type = new TypeToken<List<ArchivesAttend>>() {
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
				this.archivesAttendService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArchivesAttend archivesAttend = this.archivesAttendService
				.get(this.attendId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(archivesAttend));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String archivesStatus = getRequest().getParameter("archivesStatus");
		this.archives = (this.archivesService
				.get(this.archives.getArchivesId()));
		if (StringUtils.isNotEmpty(archivesStatus)) {
			this.archives.setStatus(Short.valueOf(Short
					.parseShort(archivesStatus)));
		}
		this.archivesService.save(this.archives);

		this.archivesAttend.setArchives(this.archives);
		this.archivesAttend.setUserID(ContextUtil.getCurrentUserId());
		this.archivesAttend.setFullname(ContextUtil.getCurrentUser()
				.getFullname());
		this.archivesAttend.setExecuteTime(new Date());
		this.archivesAttendService.save(this.archivesAttend);

		setJsonString("{success:true,attendId:"
				+ this.archivesAttend.getAttendId() + "}");
		return "success";
	}

	public String proof() {
		String archivesId = getRequest().getParameter("archivesId");
		String status = getRequest().getParameter("status");
		Archives archives = this.archivesService.get(new Long(archivesId));
		archives.setStatus(Short.valueOf(Short.parseShort(status)));

		this.archivesService.save(archives);

		this.archivesAttend.setArchives(archives);
		this.archivesAttend.setExecuteTime(new Date());
		this.archivesAttend.setUserID(ContextUtil.getCurrentUserId());
		this.archivesAttend.setFullname(ContextUtil.getCurrentUser()
				.getFullname());
		this.archivesAttendService.save(this.archivesAttend);

		setJsonString("{success:true}");
		return "success";
	}
}
