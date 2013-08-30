package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.flow.ProUserAssignDao;
import com.cyjt.oa.model.flow.ProUserAssign;
import com.cyjt.oa.service.flow.ProUserAssignService;
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
