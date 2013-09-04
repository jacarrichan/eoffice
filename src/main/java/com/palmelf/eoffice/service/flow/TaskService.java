package com.palmelf.eoffice.service.flow;

import com.palmelf.core.jbpm.pv.TaskInfo;
import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;

import java.util.List;
import java.util.Set;
import org.jbpm.pvm.internal.task.TaskImpl;

public abstract interface TaskService extends BaseService<TaskImpl> {
	public abstract List<TaskImpl> getTasksByUserId(String paramString,
			PagingBean paramPagingBean);

	public abstract List<TaskInfo> getTaskInfosByUserId(String paramString,
			PagingBean paramPagingBean, String reportType);

	public abstract Set<Long> getHastenByActivityNameVarKeyLongVal(
			String paramString1, String paramString2, Long paramLong);

	public abstract List<TaskImpl> getCandidateTasks(String paramString,
			PagingBean paramPagingBean);

	public abstract List<TaskImpl> getPersonTasks(String paramString,
			PagingBean paramPagingBean);
}
