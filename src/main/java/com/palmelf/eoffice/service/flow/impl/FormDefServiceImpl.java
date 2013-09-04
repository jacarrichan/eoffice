package com.palmelf.eoffice.service.flow.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.flow.FormDefDao;
import com.palmelf.eoffice.model.flow.FormDef;
import com.palmelf.eoffice.service.flow.FormDefService;

import java.util.List;

public class FormDefServiceImpl extends BaseServiceImpl<FormDef> implements
		FormDefService {
	private FormDefDao dao;

	public FormDefServiceImpl(FormDefDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<FormDef> getByDeployId(String deployId) {
		return this.dao.getByDeployId(deployId);
	}

	public FormDef getByDeployIdActivityName(String deployId,
			String activityName) {
		return this.dao.getByDeployIdActivityName(deployId, activityName);
	}
}
