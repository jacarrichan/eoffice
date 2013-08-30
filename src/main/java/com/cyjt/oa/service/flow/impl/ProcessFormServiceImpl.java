package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.flow.ProcessFormDao;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.service.flow.ProcessFormService;
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
