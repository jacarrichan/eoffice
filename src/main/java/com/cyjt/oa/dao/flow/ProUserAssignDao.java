package com.cyjt.oa.dao.flow;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.flow.ProUserAssign;
import java.util.List;

public abstract interface ProUserAssignDao extends BaseDao<ProUserAssign> {
	public abstract List<ProUserAssign> getByDeployId(String paramString);

	public abstract ProUserAssign getByDeployIdActivityName(
			String paramString1, String paramString2);
}
