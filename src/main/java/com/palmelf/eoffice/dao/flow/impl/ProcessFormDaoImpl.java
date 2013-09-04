package com.palmelf.eoffice.dao.flow.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.flow.ProcessFormDao;
import com.palmelf.eoffice.model.flow.FormData;
import com.palmelf.eoffice.model.flow.ProcessForm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessFormDaoImpl extends BaseDaoImpl<ProcessForm> implements
		ProcessFormDao {
	public ProcessFormDaoImpl() {
		super(ProcessForm.class);
	}

	public List getByRunId(Long runId) {
		String hql = "from ProcessForm pf where pf.processRun.runId=? order by pf.formId asc";
		return findByHql(hql, new Object[] { runId });
	}

	public ProcessForm getByRunIdActivityName(Long runId, String activityName) {
		Integer maxSn = Integer.valueOf(getActvityExeTimes(runId, activityName)
				.intValue());
		String hql = "from ProcessForm pf where pf.processRun.runId=? and pf.activityName=? and pf.sn=?";
		return (ProcessForm) findUnique(hql, new Object[] { runId,
				activityName, maxSn });
	}

	public Map getVariables(Long runId) {
		Map variables = new HashMap();
		String hql = "from ProcessForm pf where pf.processRun.runId=? order by pf.createtime desc";
		List<ProcessForm> forms = findByHql(hql, new Object[] { runId });

		for (ProcessForm form : forms) {
			Iterator formDataIt = form.getFormDatas().iterator();
			while (formDataIt.hasNext()) {
				FormData formData = (FormData) formDataIt.next();
				if (!variables.containsKey(formData.getFieldName())) {
					variables.put(formData.getFieldName(), formData.getVal());
				}
			}
		}
		return variables;
	}

	public Long getActvityExeTimes(Long runId, String activityName) {
		String hql = "select count(pf.formId) from ProcessForm pf where pf.processRun.runId=? and pf.activityName=? ";
		return (Long) findUnique(hql, new Object[] { runId, activityName });
	}
}
