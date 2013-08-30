package com.cyjt.oa.service.flow;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.flow.ProcessForm;
import java.util.List;
import java.util.Map;

public abstract interface ProcessFormService extends BaseService<ProcessForm> {
	public abstract List getByRunId(Long paramLong);

	public abstract ProcessForm getByRunIdActivityName(Long paramLong,
			String paramString);

	public abstract Long getActvityExeTimes(Long paramLong, String paramString);

	public abstract Map getVariables(Long paramLong);
}
