package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.flow.ProDefinition;

public abstract interface ProDefinitionDao extends BaseDao<ProDefinition> {
	public abstract ProDefinition getByDeployId(String paramString);

	public abstract ProDefinition getByName(String paramString);
}
