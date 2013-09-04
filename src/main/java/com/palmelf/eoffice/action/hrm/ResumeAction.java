package com.palmelf.eoffice.action.hrm;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.hrm.Resume;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.hrm.ResumeService;
import com.palmelf.eoffice.service.system.FileAttachService;

public class ResumeAction extends BaseAction {

	@Resource
	private ResumeService resumeService;
	private Resume resume;

	@Resource
	private FileAttachService fileAttachService;
	private Long resumeId;

	public Long getResumeId() {
		return this.resumeId;
	}

	public void setResumeId(Long resumeId) {
		this.resumeId = resumeId;
	}

	public Resume getResume() {
		return this.resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<Resume> list = this.resumeService.getAll(filter);

		Type type = new TypeToken<List<Resume>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.resumeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Resume resume = this.resumeService.get(this.resumeId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(resume));
		sb.append("]}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String fileIds = this.getRequest().getParameter("fileIds");
		if (this.resume.getResumeId() == null) {
			AppUser appUser = ContextUtil.getCurrentUser();
			this.resume.setRegistor(appUser.getFullname());
			this.resume.setRegTime(new Date());
		}
		if (StringUtils.isNotEmpty(fileIds)) {
			this.resume.getResumeFiles().clear();
			String[] ids = fileIds.split(",");
			for (String id : ids) {
				FileAttach fileAttach = this.fileAttachService.get(new Long(id));
				this.resume.getResumeFiles().add(fileAttach);
			}
		}
		this.resumeService.save(this.resume);
		this.setJsonString("{success:true}");
		return "success";
	}

	public String delphoto() {
		String strResumeId = this.getRequest().getParameter("resumeId");
		if (StringUtils.isNotEmpty(strResumeId)) {
			this.resume = (this.resumeService.get(new Long(strResumeId)));
			this.resume.setPhoto("");
			this.resumeService.save(this.resume);
			this.setJsonString("{success:true}");
		}
		return "success";
	}
}
