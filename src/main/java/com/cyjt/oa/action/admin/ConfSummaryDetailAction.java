package com.cyjt.oa.action.admin;

import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.ConfSummary;
import com.cyjt.oa.service.admin.ConfSummaryService;
import javax.annotation.Resource;

public class ConfSummaryDetailAction extends BaseAction {

	@Resource
	private ConfSummaryService confSummaryService;
	private Long sumId;
	private ConfSummary confSummary;

	public Long getSumId() {
		return this.sumId;
	}

	public void setSumId(Long sumId) {
		this.sumId = sumId;
	}

	public ConfSummary getConfSummary() {
		return this.confSummary;
	}

	public void setConfSummary(ConfSummary confSummary) {
		this.confSummary = confSummary;
	}

	@Override
	public String execute() throws Exception {
		this.confSummary = (this.confSummaryService.get(this.sumId));
		return "success";
	}
}
