package com.palmelf.eoffice.service.flow.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.flow.ProDefinitionDao;
import com.palmelf.eoffice.model.flow.ProDefinition;
import com.palmelf.eoffice.service.flow.ProDefinitionService;

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
