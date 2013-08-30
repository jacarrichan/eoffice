package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.flow.FormDefDao;
import com.cyjt.oa.model.flow.FormDef;
import com.cyjt.oa.service.flow.FormDefService;
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
