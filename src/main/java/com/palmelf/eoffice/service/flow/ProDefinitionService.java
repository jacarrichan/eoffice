package com.palmelf.eoffice.service.flow;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.flow.ProDefinition;

public abstract interface ProDefinitionService extends
		BaseService<ProDefinition> {
	public abstract ProDefinition getByDeployId(String paramString);

	public abstract ProDefinition getByName(String paramString);
}
