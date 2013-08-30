package com.cyjt.oa.service.flow;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.action.flow.FlowRunInfo;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.model.flow.ProcessRun;
import java.util.List;
import org.jbpm.api.ProcessInstance;

public abstract interface ProcessRunService extends BaseService<ProcessRun> {
	public abstract ProcessRun initNewProcessRun(
			ProDefinition paramProDefinition);

	public abstract void saveProcessRun(ProcessRun paramProcessRun,
			ProcessForm paramProcessForm, FlowRunInfo paramFlowRunInfo);

	public abstract ProcessRun getByExeId(String paramString);

	public abstract ProcessRun getByTaskId(String paramString);

	public abstract ProcessRun getByPiId(String paramString);

	public abstract ProcessInstance saveAndNextStep(FlowRunInfo paramFlowRunInfo);

	public abstract void removeByDefId(Long paramLong);

	public abstract List<ProcessRun> getByUserIdSubject(Long paramLong,
			String paramString, PagingBean paramPagingBean);
}
