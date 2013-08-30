package com.cyjt.oa.dao.flow;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.flow.ProDefinition;

public abstract interface ProDefinitionDao extends BaseDao<ProDefinition> {
	public abstract ProDefinition getByDeployId(String paramString);

	public abstract ProDefinition getByName(String paramString);
}
