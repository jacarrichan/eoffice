package com.palmelf.eoffice.service.flow.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.flow.ProUserAssignDao;
import com.palmelf.eoffice.model.flow.ProUserAssign;
import com.palmelf.eoffice.service.flow.ProUserAssignService;

import java.util.List;

public class ProUserAssignServiceImpl extends BaseServiceImpl<ProUserAssign>
		implements ProUserAssignService {
	private ProUserAssignDao dao;

	public ProUserAssignServiceImpl(ProUserAssignDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ProUserAssign> getByDeployId(String deployId) {
		return this.dao.getByDeployId(deployId);
	}

	public ProUserAssign getByDeployIdActivityName(String deployId,
			String activityName) {
		return this.dao.getByDeployIdActivityName(deployId, activityName);
	}
}
