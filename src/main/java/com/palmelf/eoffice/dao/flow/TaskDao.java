package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.flow.JbpmTask;

import java.util.List;
import org.jbpm.pvm.internal.task.TaskImpl;

public abstract interface TaskDao extends BaseDao<TaskImpl> {
	public abstract List<TaskImpl> getTasksByUserId(String paramString,
			PagingBean paramPagingBean);

	public abstract List<JbpmTask> getByActivityNameVarKeyLongVal(
			String paramString1, String paramString2, Long paramLong);

	public abstract List<Long> getGroupByTask(Long paramLong);

	public abstract List<Long> getUserIdByTask(Long paramLong);

	public abstract List<TaskImpl> getCandidateTasks(String paramString,
			PagingBean paramPagingBean);

	public abstract List<TaskImpl> getPersonTasks(String paramString,
			PagingBean paramPagingBean);
}
