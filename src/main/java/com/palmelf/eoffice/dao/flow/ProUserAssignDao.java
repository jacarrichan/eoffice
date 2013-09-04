package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.flow.ProUserAssign;

import java.util.List;

public abstract interface ProUserAssignDao extends BaseDao<ProUserAssign> {
	public abstract List<ProUserAssign> getByDeployId(String paramString);

	public abstract ProUserAssign getByDeployIdActivityName(
			String paramString1, String paramString2);
}
