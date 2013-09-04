package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.flow.ProcessForm;

import java.util.List;
import java.util.Map;

public abstract interface ProcessFormDao extends BaseDao<ProcessForm> {
	public abstract List getByRunId(Long paramLong);

	public abstract ProcessForm getByRunIdActivityName(Long paramLong,
			String paramString);

	public abstract Long getActvityExeTimes(Long paramLong, String paramString);

	public abstract Map getVariables(Long paramLong);
}
