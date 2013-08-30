package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.flow.ProDefinitionDao;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.service.flow.ProDefinitionService;

public class ProDefinitionServiceImpl extends BaseServiceImpl<ProDefinition>
		implements ProDefinitionService {
	private ProDefinitionDao dao;

	public ProDefinitionServiceImpl(ProDefinitionDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ProDefinition getByDeployId(String deployId) {
		return this.dao.getByDeployId(deployId);
	}

	public ProDefinition getByName(String name) {
		return this.dao.getByName(name);
	}
}
