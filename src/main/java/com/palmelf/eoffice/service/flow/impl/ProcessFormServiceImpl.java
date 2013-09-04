package com.palmelf.eoffice.service.flow.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.flow.ProcessFormDao;
import com.palmelf.eoffice.model.flow.ProcessForm;
import com.palmelf.eoffice.service.flow.ProcessFormService;

import java.util.List;
import java.util.Map;

public class ProcessFormServiceImpl extends BaseServiceImpl<ProcessForm>
		implements ProcessFormService {
	private ProcessFormDao dao;

	public ProcessFormServiceImpl(ProcessFormDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List getByRunId(Long runId) {
		return this.dao.getByRunId(runId);
	}

	public ProcessForm getByRunIdActivityName(Long runId, String activityName) {
		return this.dao.getByRunIdActivityName(runId, activityName);
	}

	public Long getActvityExeTimes(Long runId, String activityName) {
		return this.dao.getActvityExeTimes(runId, activityName);
	}

	public Map getVariables(Long runId) {
		return this.dao.getVariables(runId);
	}
}
