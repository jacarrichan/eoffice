package com.cyjt.oa.service.flow;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.flow.ProDefinition;

public abstract interface ProDefinitionService extends
		BaseService<ProDefinition> {
	public abstract ProDefinition getByDeployId(String paramString);

	public abstract ProDefinition getByName(String paramString);
}
