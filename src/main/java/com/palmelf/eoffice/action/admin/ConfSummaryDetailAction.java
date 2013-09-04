package com.palmelf.eoffice.action.admin;

import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.admin.ConfSummary;
import com.palmelf.eoffice.service.admin.ConfSummaryService;

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
